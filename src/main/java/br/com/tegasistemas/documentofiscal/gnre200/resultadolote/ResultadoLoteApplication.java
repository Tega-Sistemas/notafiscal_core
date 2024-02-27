package br.com.tegasistemas.documentofiscal.gnre200.resultadolote;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.gnre200.configuf.ConfigUfModel;
import br.com.tegasistemas.documentofiscal.gnre200.GnreConfiguracao;
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

public class ResultadoLoteApplication extends org.apache.axis2.client.Stub {

    private static final String HOM_SERVICE_URL = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreResultadoLote";
    private static final String HOM_SERVICE_ACTION = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreResultadoLote/consultar";
    private static final String PROD_SERVICE_URL = "https://www.gnre.pe.gov.br/gnreWS/services/GnreResultadoLote";
    private static final String PROD_SERVICE_ACTION = "https://www.gnre.pe.gov.br/gnreWS/services/GnreResultadoLote/consultar";
    private static final String VERSAO = "1.00";
    private static String AMBIENTE = null;
    private static String logs;
    private static GnreConfiguracao sconfiguracao;

    private static int counter = 0;
    protected org.apache.axis2.description.AxisOperation[] _operations;

    public static String consultaLote(String numeroRecibo, String certificado, String pathKeystore, String senhaCertificado, String senhaKeystore, int ambiente) throws Exception {

        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", certificado);
        System.setProperty("javax.net.ssl.keyStorePassword", senhaCertificado);

        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", pathKeystore);
        System.setProperty("javax.net.ssl.trustStorePassword", senhaKeystore);


        String SERVICE_URL = null;
        String SERVICE_ACTION = null;
        DefaultHttpClient httpclient = new DefaultHttpClient();

        if (ambiente == Integer.parseInt(DFAmbiente.PRODUCAO.getCodigo())) {
            AMBIENTE = ConfigUfModel.AMBIENTE_PRODUCAO;
            SERVICE_URL = PROD_SERVICE_URL;
            SERVICE_ACTION = PROD_SERVICE_ACTION;
        } else {
            AMBIENTE = ConfigUfModel.AMBIENTE_HOMOLOGACAO;
            SERVICE_URL = HOM_SERVICE_URL;
            SERVICE_ACTION = HOM_SERVICE_ACTION;
        }

        ResultadoLoteModel model = new ResultadoLoteModel(AMBIENTE, numeroRecibo);
        String requestEnvelope = getRequestEnvelope(model);

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
            instreamTrust = new FileInputStream(new File(pathKeystore));
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

        } catch (UnsupportedEncodingException ex) {
            logs = ex.getMessage();
        } catch (KeyStoreException ex) {
            logs = ex.getMessage();
        } catch (FileNotFoundException ex) {
            logs = ex.getMessage();
        } catch (IOException ex) {
            logs = ex.getMessage();
        } catch (NoSuchAlgorithmException ex) {
            logs = ex.getMessage();
        } catch (CertificateException ex) {
            logs = ex.getMessage();
        } catch (KeyManagementException ex) {
            logs = ex.getMessage();
        }
        return logs;
    }

    private static String getRequestEnvelope(ResultadoLoteModel model) {
        return new StringBuilder()
                .append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:gnr=\"http://www.gnre.pe.gov.br/webservice/GnreResultadoLote\">")
                .append("<soap:Header>")
                .append("<gnr:gnreCabecMsg>")
                .append("<gnr:versaoDados>").append(VERSAO).append("</gnr:versaoDados>")
                .append("</gnr:gnreCabecMsg>")
                .append("</soap:Header>")
                .append("<soap:Body>")
                .append("<gnr:gnreDadosMsg>")
                .append("<TConsLote_GNRE xmlns=\"http://www.gnre.pe.gov.br\">").append(model.toString()).append("</TConsLote_GNRE>")
                .append("</gnr:gnreDadosMsg>")
                .append("</soap:Body>")
                .append("</soap:Envelope>")
                .toString();
    }
}