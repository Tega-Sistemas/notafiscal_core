import br.com.tegasistemas.documentofiscal.gnre200.GnreConfig;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.gnre200.configuf.ConfigUFApplication;
import br.com.tegasistemas.documentofiscal.gnre200.loterecepcao.LoteRecepcaoApplication;
import br.com.tegasistemas.documentofiscal.gnre200.resultadolote.ResultadoLoteApplication;
import org.apache.axis2.client.ServiceClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class GnreAPI {

    //private ConfigUFApplication cUf;
    private GnreConfiguracao sconfiguracao;

    private String sCadeiaCertificadoSenha;
    private String sCadeiaCertificadoCaminho;
    private String sCaminhoCertificado;
    private String sCertificadoSenha;
    private String sVersao;
    private String sCodigoSegurancaContribuinte;
    private Integer sCodigoSegurancaContribuinteID;

    private DFUnidadeFederativa sEstado;
    private DFAmbiente sAmbiente;

    private String logs = "";

    private final GnreConfig config = new GnreConfig() {

        private KeyStore keyStoreCertificado = null;
        private KeyStore keyStoreCadeia = null;

        @Override
        public String getCertificadoSenha() {
            // TODO Auto-generated method stub
            return sCertificadoSenha;
        }

        @Override
        public KeyStore getCertificadoKeyStore() throws KeyStoreException {
            if (this.keyStoreCertificado == null) {
                this.keyStoreCertificado = KeyStore.getInstance("PKCS12");
                try (InputStream certificadoStream = new FileInputStream(sCaminhoCertificado)) {
                    this.keyStoreCertificado.load(certificadoStream, this.getCertificadoSenha().toCharArray());
                } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                    this.keyStoreCadeia = null;
                    throw new KeyStoreException("Nao foi possibel montar o KeyStore com a cadeia de certificados", e);
                }
            }
            return this.keyStoreCertificado;
        }

        @Override
        public String getCadeiaCertificadosSenha() {
            // TODO Auto-generated method stub
            return sCadeiaCertificadoSenha;
        }

        @Override
        public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
            if (this.keyStoreCadeia == null) {
                this.keyStoreCadeia = KeyStore.getInstance("JKS");
                try (InputStream cadeia = new FileInputStream(sCadeiaCertificadoCaminho)) {
                    this.keyStoreCadeia.load(cadeia, sCadeiaCertificadoSenha.toCharArray());
                } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                    this.keyStoreCadeia = null;
                    throw new KeyStoreException("Nao foi possibel montar o KeyStore com o certificado", e);
                }
            }
            return this.keyStoreCadeia;
        }

        @Override
        public DFUnidadeFederativa getCUF() {
            // TODO Auto-generated method stub
            return sEstado;
        }

        @Override
        public String getVersao() {
            return sVersao;
        }

        @Override
        public DFAmbiente getAmbiente() {
            return sAmbiente;
        }

        @Override
        public String getCodigoSegurancaContribuinte() {
            return sCodigoSegurancaContribuinte;
        }

        @Override
        public Integer getCodigoSegurancaContribuinteID() {
            return sCodigoSegurancaContribuinteID;
        }
    };

    public void configurar(String CertificadoSenha, String CadeiaCertificadoSenha, String CaminhoCertificado, String CaminhoCadeiaCertificado, String Estado, String Receita, String Ambiente, String Versao) {

//        System.out.println("--------------------------- Config GNRE ------------------------");
//        System.out.println("|-------------------------- Certificado ------------------------");
//        System.out.println("| Caminho Cert.: " + CaminhoCertificado);
//        System.out.println("| Senha Cert...: " + CertificadoSenha);
//        System.out.println("|--------------------- Cadeia de Certificado -------------------");
//        System.out.println("| Cacerts Cert.: " + CaminhoCadeiaCertificado);
//        System.out.println("| Senha Cert...: " + CadeiaCertificadoSenha);
//        System.out.println("|-------------------- Ambiente / Uf / Versão  ------------------");
//        System.out.println("| UF...........: " + Estado);
//        System.out.println("| Estado.......: " + BuscaUnidadeFederativa(Estado));
//        System.out.println("| Ambiente.....: " + BuscaAmbiente(Ambiente));
//        System.out.println("| Versao.......: " + Versao);
//        System.out.println("----------------------- Config To String -----------------------");

        sconfiguracao = new GnreConfiguracao();

        sconfiguracao.setCacertPath(CaminhoCadeiaCertificado);
        sconfiguracao.setCacertSenha(CadeiaCertificadoSenha);
        sconfiguracao.setCertificadoPath(CaminhoCertificado);
        sconfiguracao.setCertificadoSenha(CertificadoSenha);
        sconfiguracao.getCadeiaCertificadosKeyStore = CaminhoCadeiaCertificado;
        sconfiguracao.getCadeiaCertificadosSenha = CadeiaCertificadoSenha;
        sconfiguracao.getCertificadoKeyStore = CaminhoCertificado;
        sconfiguracao.getCertificadoSenha = CertificadoSenha;
        sconfiguracao.DFUnidadeFederativa = BuscaUnidadeFederativa(Estado);
        sCadeiaCertificadoCaminho = CaminhoCadeiaCertificado;
        sconfiguracao.DFAmbiente = BuscaAmbiente(Ambiente);
        sCadeiaCertificadoSenha = CadeiaCertificadoSenha;
        sCaminhoCertificado = CaminhoCertificado;
        sCertificadoSenha = CertificadoSenha;
        sAmbiente = BuscaAmbiente(Ambiente);
        sEstado = BuscaUnidadeFederativa(Estado);
        sVersao = Versao;
    }

    public String BuscaConfigUF(String uf, String receita, String certificado, String keystore, String senhaCertificado, String senhaKeystore, int ambiente) throws Exception {
        String urlWebService;
        DFUnidadeFederativa dfUF = BuscaUnidadeFederativa(uf);
        if (ambiente == 1) {
            urlWebService = dfUF.getGnreProducao();
        } else {
            urlWebService = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreConfigUF/consultar";
        }
        System.out.println("aaaaaaaaaa");
        ServiceClient serviceClient = new ServiceClient();
        try {
            logs = new ConfigUFApplication().ConfigUF(uf, receita, certificado, keystore, senhaCertificado, senhaKeystore, ambiente);
        } catch (Exception e) {
            logs = e.getMessage();
            //javaee-endorsed-api-6.0
        }

        return logs;
    }

    public String transmitirXml(String xml, String certificado, String keystore, String senhaCertificado, String senhaKeystore, int ambiente) {

        xml = xml.replaceAll("\r", "");
        xml = xml.replaceAll("\t", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&gt;", ">");

        try {
            logs = new LoteRecepcaoApplication(this.config).TransmitirGnre(xml, certificado, keystore, senhaCertificado, senhaKeystore, ambiente);
        } catch (Exception e) {
            logs = e.getMessage();
        }
        return logs;
    }

    public String consultar(String numeroRecibo, String certificado, String keystore, String senhaCertificado, String senhaKeystore, int ambiente) {
        try {
            //logs = ResultadoLoteApplication.consultaLote(numeroRecibo, certificado, keystore, senhaCertificado, senhaKeystore, ambiente);
            logs = new ResultadoLoteApplication().consultaLote(numeroRecibo, certificado, keystore, senhaCertificado, senhaKeystore, ambiente);
        } catch (Exception e) {
            logs = e.getMessage();
            e.getStackTrace();
        }
        return logs;
    }

    private DFUnidadeFederativa BuscaUnidadeFederativa(String UF) {

        DFUnidadeFederativa unidadefederativa;

        switch (UF) {
            case "PR":
                unidadefederativa = DFUnidadeFederativa.PR;
                break;
            case "RR":
                unidadefederativa = DFUnidadeFederativa.RR;
                break;
            case "AM":
                unidadefederativa = DFUnidadeFederativa.AM;
                break;
            case "AC":
                unidadefederativa = DFUnidadeFederativa.AC;
                break;
            case "RO":
                unidadefederativa = DFUnidadeFederativa.RO;
                break;
            case "AP":
                unidadefederativa = DFUnidadeFederativa.AP;
                break;
            case "PA":
                unidadefederativa = DFUnidadeFederativa.PA;
                break;
            case "MT":
                unidadefederativa = DFUnidadeFederativa.MT;
                break;
            case "TO":
                unidadefederativa = DFUnidadeFederativa.TO;
                break;
            case "MA":
                unidadefederativa = DFUnidadeFederativa.MA;
                break;
            case "PI":
                unidadefederativa = DFUnidadeFederativa.PI;
                break;
            case "MS":
                unidadefederativa = DFUnidadeFederativa.MS;
                break;
            case "GO":
                unidadefederativa = DFUnidadeFederativa.GO;
                break;
            case "DF":
                unidadefederativa = DFUnidadeFederativa.DF;
                break;
            case "MG":
                unidadefederativa = DFUnidadeFederativa.MG;
                break;
            case "RJ":
                unidadefederativa = DFUnidadeFederativa.RJ;
                break;
            case "SP":
                unidadefederativa = DFUnidadeFederativa.SP;
                break;
            case "SC":
                unidadefederativa = DFUnidadeFederativa.SC;
                break;
            case "RS":
                unidadefederativa = DFUnidadeFederativa.RS;
                break;
            case "ES":
                unidadefederativa = DFUnidadeFederativa.ES;
                break;
            case "BA":
                unidadefederativa = DFUnidadeFederativa.BA;
                break;
            case "RN":
                unidadefederativa = DFUnidadeFederativa.RN;
                break;
            case "CE":
                unidadefederativa = DFUnidadeFederativa.CE;
                break;
            case "AL":
                unidadefederativa = DFUnidadeFederativa.AL;
                break;
            case "PE":
                unidadefederativa = DFUnidadeFederativa.PE;
                break;
            case "PB":
                unidadefederativa = DFUnidadeFederativa.PB;
                break;
            case "SE":
                unidadefederativa = DFUnidadeFederativa.SE;
                break;
            default:
                unidadefederativa = DFUnidadeFederativa.PR;
                break;
        }

        return unidadefederativa;
    }

    private DFAmbiente BuscaAmbiente(String Ambiente) {
        DFAmbiente dfambiente;
        switch (Ambiente) {
            case "2":
                dfambiente = DFAmbiente.HOMOLOGACAO;
                break;
            case "1":
                dfambiente = DFAmbiente.PRODUCAO;
                break;
            default:
                dfambiente = DFAmbiente.HOMOLOGACAO;
                break;
        }
        return dfambiente;
    }

}