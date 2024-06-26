import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.mdfe3.MDFeConfig;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.MDFProtocolo;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.MDFProtocoloInfo;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.consultaRecibo.MDFeConsultaReciboRetorno;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.consultanaoencerrados.MDFeConsultaNaoEncerradosRetorno;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.lote.envio.MDFEnvioLote;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.lote.envio.MDFEnvioLoteRetornoDados;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.MDFProcessado;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.MDFe;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.consulta.MDFeNotaConsultaRetorno;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.envio.MDFEnvioRetornoDados;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.evento.MDFeRetorno;
import br.com.tegasistemas.documentofiscal.mdfe3.utils.MDFGeraQRCode;
import br.com.tegasistemas.documentofiscal.mdfe3.webservices.WSFacade;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class MDFeAPI {

    private MDfeConfiguracao sconfiguracao;
    private DFUnidadeFederativa sEstado;
    private DFAmbiente sAmbiente;

    private String sCadeiaCertificadoSenha;
    private String sCadeiaCertificadoCaminho;
    private String sCaminhoCertificado;
    private String sCertificadoSenha;

    private String logs;

    private MDFeConfig config = new MDFeConfig() {

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
                    throw new KeyStoreException("Nao foi possivel montar o KeyStore com a cadeia de certificados", e);
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
        public DFAmbiente getAmbiente() {
            return sAmbiente;
        }


    };

    public void Configurar(String CertificadoSenha,
                           String CadeiaCertificadoSenha,
                           String CaminhoCertificado,
                           String CaminhoCadeiaCertificado,
                           String Estado,
                           String Ambiente) {

        sconfiguracao = new MDfeConfiguracao();

        sconfiguracao.getCertificadoKeyStore = CaminhoCertificado;
        sconfiguracao.getCertificadoSenha = CertificadoSenha;

        sconfiguracao.getCadeiaCertificadosKeyStore = CaminhoCadeiaCertificado;
        sconfiguracao.getCadeiaCertificadosSenha = CadeiaCertificadoSenha;

        sconfiguracao.DFUnidadeFederativa = BuscaUnidadeFederativa(Estado);
        sconfiguracao.DFAmbiente = BuscaAmbiente(Ambiente);

        sAmbiente = BuscaAmbiente(Ambiente);
        sEstado = BuscaUnidadeFederativa(Estado);

        sCadeiaCertificadoCaminho = CaminhoCadeiaCertificado;
        sCadeiaCertificadoSenha = CadeiaCertificadoSenha;

        sCaminhoCertificado = CaminhoCertificado;
        sCertificadoSenha = CertificadoSenha;


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

    public String transmitir(String xml) {

        xml = xml.replaceAll("\r", "");
        xml = xml.replaceAll("\t", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&gt;", ">");

        String value = null;

        try {
            value = new String(xml.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            System.out.println(e2.getMessage());
        }

        try {

            MDFe mdfe = new DFPersister().read(MDFe.class, value);
            MDFEnvioRetornoDados mdfEnvioLoteRetornoDados = new WSFacade(config).envioRecepcaoSinc(mdfe);


            if(mdfEnvioLoteRetornoDados.getRetorno().getStatus().equals("100")) {
                logs = consultaXMLProcMDFe("",
                        mdfEnvioLoteRetornoDados.getRetorno().getMdfProtocolo().getProtocoloInfo().getNumeroProtocolo(),
                        mdfEnvioLoteRetornoDados.getRetorno().getMdfProtocolo().getProtocoloInfo().getChave(),
                        mdfEnvioLoteRetornoDados.getMDFEAssinado().toString());
            } else {
                logs = mdfEnvioLoteRetornoDados.toString();
            }

        } catch (Exception e) {
            logs = "error: " + e.getMessage();
        }

        return logs;

    }

    public String cancelar(String chave, String protocolo, String justificativa) {

        try {
            MDFeRetorno mdfEnvioLoteRetornoDados = new WSFacade(config).cancelaMdfe(chave, protocolo, justificativa);
            logs = mdfEnvioLoteRetornoDados.getEventoRetorno().toString();
        } catch (Exception e) {
            logs = e.getMessage();
        }
        return logs;
    }

    public String encerrar(String chave, String protocolo, String codigomunicipio, String dataencerramento) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dataencerramento, formatter);

        try {
            MDFeRetorno mdfEnvioLoteRetornoDados = new WSFacade(config).encerramento(chave, protocolo, codigomunicipio, localDate, sEstado);
            logs = mdfEnvioLoteRetornoDados.getEventoRetorno().toString();
        } catch (Exception e) {
            logs = e.getMessage();
        }
        return logs;
    }

    public String naoencerrada(String cnpj) {

        try {

            MDFeConsultaNaoEncerradosRetorno mdfeconsultanaorncerradosretorno = new WSFacade(config).consultaNaoEncerrados(cnpj);

            logs = mdfeconsultanaorncerradosretorno.toString();
        } catch (Exception e) {
            logs = e.getMessage();
        }
        return logs;

    }

    public String consultar(String chave) {

        try {
            MDFeNotaConsultaRetorno mdfEnvioLoteRetornoDados = new WSFacade(config).consultaMdfe(chave);
            logs = mdfEnvioLoteRetornoDados.toString();
        } catch (Exception e) {
            logs = e.getMessage();
        }
        return logs;

    }

    public String consultarrecibo(String recibo) {

        try {
            return new WSFacade(config).consultaRecibo(recibo);
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    public String gerarQRCode(String xml) {


        xml = xml.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&gt;", ">");

        MDFe mdfe = null;

        try {
            mdfe = new DFPersister().read(MDFe.class, xml);
            MDFGeraQRCode qrcode = new MDFGeraQRCode(mdfe, config);
            logs = qrcode.getQRCode();
        } catch (Exception e) {
            logs = "error: " + e.getMessage();
        }

        return logs;
    }

    public String incluirCondutor(String chave, String nomeCondutor, String cpfCondutor, String sequencialEvento) {
        try {
            MDFeRetorno mdfEnvioLoteRetornoDados = new WSFacade(config).incluirCondutor(chave, nomeCondutor, cpfCondutor, sequencialEvento);
            logs = mdfEnvioLoteRetornoDados.getEventoRetorno().toString();
        } catch (Exception e) {
            logs = e.getMessage();
        }
        return logs;
    }

    public String consultaXMLProcMDFe(String recibo, String chave, String numprotocolo, String xml) {
        try {
            DFModelo dfmodelo;
            BigDecimal VERSAO_LEIAUTE = new BigDecimal("3.00");
            MDFProcessado mdfe = new MDFProcessado();
            MDFe mdfeEnvio = new MDFe();
            MDFeConsultaReciboRetorno mdfecons = new MDFeConsultaReciboRetorno();
            MDFeNotaConsultaRetorno mdfEnvioLoteRetornoDados = new MDFeNotaConsultaRetorno();

            boolean continuar = false;
            try {
                mdfeEnvio = new DFPersister(false).read(MDFe.class, xml);
            } catch (Exception e) {
                logs = e.getMessage();
            }

            try {
                mdfEnvioLoteRetornoDados = new WSFacade(config).consultaMdfe(chave);
            } catch (Exception e) {
                logs = e.getMessage();
            }

            if (mdfEnvioLoteRetornoDados.getStatus().equals("100")) {
//                try {
//                    mdfecons = new DFPersister(false).read(MDFeConsultaReciboRetorno.class,  new WSFacade(config).consultaRecibo(recibo));
//                    continuar = true;
//                } catch (Exception e) {
//                    logs = e.getMessage();
//                }
                //if (continuar) {
                try {
                    //MDFProtocoloInfo m = mdfecons.getMdfProtocolo().getProtocoloInfo();
                    MDFProtocoloInfo m = mdfEnvioLoteRetornoDados.getProtocolo().getProtocoloInfo();
                    //System.out.println(mdfecons.getMdfProtocolo().getProtocoloInfo().toString());
                    m.setStatus(mdfEnvioLoteRetornoDados.getStatus());
                    //System.out.println(mdfEnvioLoteRetornoDados.getStatus().toString());
                    m.setMotivo(mdfEnvioLoteRetornoDados.getMotivo());
                    //System.out.println(mdfEnvioLoteRetornoDados.getMotivo().toString());
                    m.setNumeroProtocolo(numprotocolo);
                    //System.out.println(numprotocolo);
                    MDFProtocolo prot = new MDFProtocolo();
                    prot.setProtocoloInfo(m);
                    prot.setVersao("3.00");

                    MDFProtocoloInfo mdh = new MDFProtocoloInfo();
                    mdh = prot.getProtocoloInfo();

                    if (mdh.getDataRecebimento() == null) {
                        mdh.setDataRecebimento(ZonedDateTime.now());
                    }

                    mdfe.setMdfe(mdfeEnvio);
                    mdfe.setVersao(VERSAO_LEIAUTE);
                    mdfe.setProtocolo(prot);
                    logs = mdfe.toString();
                } catch (Exception e) {
                    logs = e.getMessage();
                }
            } else {
                logs = "Não foi possível receber o protocolo";
            }
//            }
        } catch (Exception e) {
            System.out.println("a");
            logs = e.getMessage();
        }

        return logs;
    }

    public String teste() {
        System.out.println(ZonedDateTime.now(this.config.getTimeZone().toZoneId()));
        return "";
    }

}

