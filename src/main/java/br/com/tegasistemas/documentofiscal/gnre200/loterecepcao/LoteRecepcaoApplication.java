package br.com.tegasistemas.documentofiscal.gnre200.loterecepcao;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.gnre200.GnreConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class LoteRecepcaoApplication {

    private static final String HOM_SERVICE_URL = "https://www.testegnre.pe.gov.br:444/gnreWS/services/GnreLoteRecepcao";
    private static final String HOM_SERVICE_ACTION = "https://www.testegnre.pe.gov.br:444/gnreWS/services/GnreLoteRecepcao/processar";
    private static final String PROD_SERVICE_URL = "https://www.gnre.pe.gov.br/gnreWS/services/GnreLoteRecepcao";
    private static final String PROD_SERVICE_ACTION = "https://www.gnre.pe.gov.br/gnreWS/services/GnreLoteRecepcao/processar";
    private static final String VERSAO = "1.00";
    private static String logs;
    private final GnreConfig config;

    public LoteRecepcaoApplication(GnreConfig config) {
        this.config = config;
    }

    private static String getRequestEnvelope(String xml) {
        return new StringBuilder()
                .append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:gnr=\"http://www.gnre.pe.gov.br/webservice/GnreLoteRecepcao\">")
                .append("<soap:Header>")
                .append("<gnr:gnreCabecMsg>")
                .append("<gnr:versaoDados>").append("2.00").append("</gnr:versaoDados>")
                .append("</gnr:gnreCabecMsg>")
                .append("</soap:Header>")
                .append("<soap:Body>")
                .append("<gnr:gnreDadosMsg>")
                .append(xml)
                .append("</gnr:gnreDadosMsg>")
                .append("</soap:Body>")
                .append("</soap:Envelope>")
                .toString();
    }

    public String TransmitirGnre(String xml, String certificado, String keystore, String senhaCertificado, String senhaKeystore, int ambiente) throws Exception {

        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", certificado);
        System.setProperty("javax.net.ssl.keyStorePassword", senhaCertificado);

        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", keystore);
        System.setProperty("javax.net.ssl.trustStorePassword", senhaKeystore);

        String requestEnvelope = getRequestEnvelope(xml);
        String SERVICE_URL = null;
        String SERVICE_ACTION = null;
        DefaultHttpClient httpclient = new DefaultHttpClient();

        if (ambiente == Integer.parseInt(DFAmbiente.PRODUCAO.getCodigo())) {
            SERVICE_URL = PROD_SERVICE_URL;
            SERVICE_ACTION = PROD_SERVICE_ACTION;
        } else {
            SERVICE_URL = HOM_SERVICE_URL;
            SERVICE_ACTION = HOM_SERVICE_ACTION;
        }

        //System.out.println(requestEnvelope);

        try {
            HttpPost httpPost = new HttpPost(SERVICE_URL);
            httpPost.setHeader(new BasicHeader("Content-Type", "application/soap+xml;charset=UTF-8"));
            httpPost.setHeader(new BasicHeader("SOAPAction", SERVICE_ACTION));
            StringEntity s = new StringEntity(requestEnvelope, "UTF-8");
            httpPost.setEntity(s);
            FileInputStream instream = null;
            FileInputStream instreamTrust = null;

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            instream = new FileInputStream(new File(certificado));
            keyStore.load(instream, senhaCertificado.toCharArray());

            KeyStore trustStore = KeyStore.getInstance("JKS");
            instreamTrust = new FileInputStream(new File(keystore));
            trustStore.load(instreamTrust, senhaKeystore.toCharArray());


            SSLContext sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, senhaCertificado.toCharArray())
                    .loadTrustMaterial(trustStore, new TrustAllStrategy())
                    .build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext,
                    new DefaultHostnameVerifier()
            );

            CloseableHttpClient httpclientSLL = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

            HttpResponse response = httpclientSLL.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //System.out.println("Response content length: " + entity.getContentLength());
                String retornoStr = EntityUtils.toString(entity);
                //System.out.println("Response  " + retornoStr);
                logs = retornoStr;
                //System.out.println("ResponseOBJ  " + logs);
            }
            if (entity != null) {
                entity.consumeContent();
            }
            httpclient.getConnectionManager().shutdown();

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException |
                 KeyManagementException ex) {
            logs = ex.getMessage();
        }

//        try (
//                FileInputStream certificadoClienteIS = new FileInputStream(new File(certificado));
//                FileInputStream keyStoreServicoIS = new FileInputStream(new File(keystore))
//        ) {
//
//            KeyStore keyStoreCliente = KeyStore.getInstance("PKCS12");
//            keyStoreCliente.load(certificadoClienteIS, senhaCertificado.toCharArray());
//
//            KeyStore keyStoreServico = KeyStore.getInstance("JKS");
//            keyStoreServico.load(keyStoreServicoIS, senhaKeystore.toCharArray());
//
//            SSLContext sslContext = SSLContexts.custom()
//                    .loadKeyMaterial(keyStoreCliente, senhaCertificado.toCharArray())
//                    .loadTrustMaterial(keyStoreServico, new TrustAllStrategy())
//                    .build();
//
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier());
//            httpCLient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//
//            try {
//                System.out.println("Iniciando envio");
//                HttpPost httpPost = new HttpPost(SERVICE_URL);
//                httpPost.setHeader(new BasicHeader("Content-Type", "application/soap+xml;charset=UTF-8"));
//                httpPost.setHeader(new BasicHeader("SOAPAction", SERVICE_ACTION));
//                httpPost.setEntity(new StringEntity(requestEnvelope, "UTF-8"));
//
//                try (CloseableHttpResponse response = httpCLient.execute(httpPost)) {
//                    logs = EntityUtils.toString(response.getEntity());
//                }
//            } catch (UnsupportedCharsetException | IOException | ParseException e) {
//                logs = e.getMessage();
//            }
//        } catch (Exception e) {
//            logs = e.getMessage();
//        }
        return logs;
    }
}
