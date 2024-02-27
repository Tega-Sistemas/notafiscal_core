package br.com.tegasistemas.documentofiscal.gnre200;

import org.apache.http.impl.client.CloseableHttpClient;

import java.security.*;

public class ClientFactory {

//    private static final String CERTIFICADO_CLIENTE_PATH    = config.getCertificadoPath();
//    private static final String CERTIFICADO_CLIENTE_PASSWD  = config.getCertificadoSenha();
//    private static final String KEYSTORE_SERVICO_PATH       = config.getCacertsPath();
//    private static final String KEYSTORE_SERVICO_PASSDW     = config.getCadeiaCertificadosSenha();

    public static CloseableHttpClient getInstance(GnreConfig config) throws KeyStoreException {
    /*
        try (
                FileInputStream certificadoClienteIS = new FileInputStream(new File(config.getCertificadoPath()));
                FileInputStream keyStoreServicoIS = new FileInputStream(String.valueOf(config.getCadeiaCertificadosKeyStore()))
        ) {
            KeyStore keyStoreCliente = KeyStore.getInstance("PKCS12");
            keyStoreCliente.load(certificadoClienteIS, config.getCertificadoSenha().toCharArray());

            KeyStore keyStoreServico = KeyStore.getInstance("JKS");
            keyStoreServico.load(keyStoreServicoIS, config.getCadeiaCertificadosSenha().toCharArray());

            SSLContext sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStoreCliente, config.getCertificadoSenha().toCharArray())
                    .loadTrustMaterial(keyStoreServico, new TrustAllStrategy())
                    .build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier());

            return HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
        } catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            out.println(ex.getMessage());
        }
        */
        return null;
    }
}
