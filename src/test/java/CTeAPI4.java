import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte300.classes.CTTipoEmissao;
//import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLote;
//import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLoteRetornoDados;
import br.com.tegasistemas.documentofiscal.cte400.classes.nota.CTeProcessado;
import br.com.tegasistemas.documentofiscal.cte400.classes.nota.consulta.CTeNotaConsultaRetorno;
import br.com.tegasistemas.documentofiscal.cte400.classes.consultastatusservico.CTeConsStatServRet;
//import br.com.tegasistemas.documentofiscal.cte400.classes.enviolote.CTeEnvioLote;
//import br.com.tegasistemas.documentofiscal.cte400.classes.enviolote.CTeEnvioLoteRetornoDados;
//import br.com.tegasistemas.documentofiscal.cte400.classes.enviolote.consulta.CTeConsultaRecLoteRet;
//import br.com.tegasistemas.documentofiscal.cte400.classes.evento.cancelamento.CTeRetornoCancelamento;
import br.com.tegasistemas.documentofiscal.cte400.classes.envio.CTeEnvioRetorno;
import br.com.tegasistemas.documentofiscal.cte400.classes.envio.CTeEnvioRetornoDados;
import br.com.tegasistemas.documentofiscal.cte400.classes.nota.CTeNota;
import br.com.tegasistemas.documentofiscal.cte400.parsers.CTChaveParser;
import br.com.tegasistemas.documentofiscal.cte400.utils.CTeGeraChave;
import br.com.tegasistemas.documentofiscal.cte400.utils.CTeGeraQRCode;
import br.com.tegasistemas.documentofiscal.cte400.webservices.WSFacade;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

public class CTeAPI4 {
    private DFUnidadeFederativa sEstado;
    private DFAmbiente sAmbiente;
    private String sCadeiaCertificadoSenha;
    private String sCadeiaCertificadoCaminho;
    private String sCaminhoCertificado;
    private String sCertificadoSenha;
    private String logs;
    private final CTeConfig config = new CTeConfig() {
        private KeyStore keyStoreCertificado = null;
        private KeyStore keyStoreCadeia = null;

        @Override
        public String getCertificadoSenha() {
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
            return sEstado;
        }

        @Override
        public DFAmbiente getAmbiente() {
            return sAmbiente;
        }

//        @Override
//        public CTTipoEmissao getTipoEmissao() {
//            return CTTipoEmissao.EMISSAO_NORMAL;
//        }

    };

    public void configurar(String CertificadoSenha, String CadeiaCertificadoSenha, String CaminhoCertificado, String CaminhoCadeiaCertificado, String Estado, String Ambiente) {
        CTeConfiguracao sconfiguracao = new CTeConfiguracao();
        sconfiguracao.getCertificadoKeyStore = CaminhoCertificado;
        sconfiguracao.getCertificadoSenha = CertificadoSenha;
        sconfiguracao.getCadeiaCertificadosKeyStore = CaminhoCadeiaCertificado;
        sconfiguracao.getCadeiaCertificadosSenha = CadeiaCertificadoSenha;
        sconfiguracao.DFUnidadeFederativa = BuscaUnidadeFederativa(Estado);
        sconfiguracao.DFAmbiente = buscaAmbiente(Ambiente);
        sAmbiente = buscaAmbiente(Ambiente);
        sEstado = BuscaUnidadeFederativa(Estado);
        sCadeiaCertificadoCaminho = CaminhoCadeiaCertificado;
        sCadeiaCertificadoSenha = CadeiaCertificadoSenha;
        sCaminhoCertificado = CaminhoCertificado;
        sCertificadoSenha = CertificadoSenha;
    }

    private DFUnidadeFederativa BuscaUnidadeFederativa(String UF) {
        return DFUnidadeFederativa.valueOfCodigo(UF);
    }

    private DFAmbiente buscaAmbiente(String Ambiente) {
        DFAmbiente dfambiente;
        switch (Ambiente) {
            case "1":
                dfambiente = DFAmbiente.PRODUCAO;
                break;
            case "2":
            default:
                dfambiente = DFAmbiente.HOMOLOGACAO;
                break;
        }
        return dfambiente;
    }

    public String consultaStatusServico(String UF) {
        try {
            String status = String.valueOf(new WSFacade(config).consultaStatus(BuscaUnidadeFederativa(UF)));
            System.out.println(status);
            CTeConsStatServRet cteStatusServiceRetorno = new DFPersister().read(CTeConsStatServRet.class, status);
            return cteStatusServiceRetorno.getMotivo() + "," + cteStatusServiceRetorno.getCodigoStatus();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String transmitir(String xmlRec) {

        xmlRec = xmlRec.replaceAll("\r", "");
        xmlRec = xmlRec.replaceAll("\t", "");
        xmlRec = xmlRec.replaceAll("\\s{2,}", "");
        xmlRec = xmlRec.replaceAll("\n", "");
        xmlRec = xmlRec.replaceAll("&gt;", ">");
        xmlRec = xmlRec.replaceAll("&lt;", "<");

        CTeNota c;

        try {
            //Tenta realizar a transmissão do objeto de lote a receita
            String retorno = new WSFacade(config).enviaCTeXML(xmlRec);
            logs = retorno.replaceAll("&amp;", "&");
        } catch (Exception e) {
            logs = "error na transmissão do CTe: " + e.getMessage();
        }
        return logs;
    }

    public String consultar(String chave) {
        try {
            return String.valueOf(new WSFacade(config).consultaNota(chave));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String cancelar(String chave, String protocolo, String justificativa) {
        try {
            return new WSFacade(config).cancelaNota(chave, protocolo, justificativa);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String correcao(String chaveAcesso, String grupoAlterado, String campoAlterado, String valorAlterado, String nroItemAlterado, int sequencialEvento) {
        if ((chaveAcesso.length() == 44) && (grupoAlterado.length() <= 20) && (campoAlterado.length() <= 20) && (valorAlterado.length() <= 500) && (nroItemAlterado != null)) {
            try {
                return String.valueOf(new WSFacade(config).corrigeNotaXML(chaveAcesso, grupoAlterado, campoAlterado, valorAlterado, nroItemAlterado, sequencialEvento));

            } catch (Exception e) {
                return "ERRO: " + e.getMessage();
            }
        } else {
            return "Favor, verificar as informações fornecidas - chave: " + chaveAcesso + " grupo alterado: " + grupoAlterado + " campo alterado: " + campoAlterado + " valor alterado: " + valorAlterado + " número item alterado: " + nroItemAlterado;
        }
    }

    public String consultarSituacao(String chave, String xml) {
        CTeNotaConsultaRetorno notaRetorno;
        if (!chave.isEmpty()) {
            try {
                String retorno = String.valueOf(new WSFacade(config).consultaNota(chave));
                notaRetorno = new DFPersister().read(CTeNotaConsultaRetorno.class, retorno);
                if (notaRetorno.toString().contains("CT-e nao consta na base de dados da SEFAZ")) {
                    return notaRetorno.toString();
                } else {
                    CTeProcessado cteProcessado = new CTeProcessado();
                    cteProcessado.setVersao("4.00");
                    cteProcessado.setProtocolo(notaRetorno.getProtocolo());
                    cteProcessado.setCteReplace("replace");
                    return cteProcessado.toString().replaceAll("<CTeReplace>replace</CTeReplace>", xml);
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        } else {
            return "Erro, dados informados estão incorretos! Favor verificar.";
        }
    }

    public String geraQrCode(String xml) {
        xml = xml.replaceAll("\r", "");
        xml = xml.replaceAll("\t", "");
        xml = xml.replaceAll("\\s{2,}", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("&gt;", ">");
        xml = xml.replaceAll("&lt;", "<");

        CTeNota cte = null;
        try {
            cte = new DFPersister().read(CTeNota.class, xml);
            logs = new CTeGeraQRCode(config).getQRCode(cte);
        } catch (Exception e) {
            logs = "Erro ao gerar QRcode CTe: " + e.getMessage();
        }
        return logs;
    }

/*    public String geraChaveCTe(String xml) {
        xml = xml.replaceAll("\r", "");
        xml = xml.replaceAll("\t", "");
        xml = xml.replaceAll("\\s{2,}", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("&gt;", ">");
        xml = xml.replaceAll("&lt;", "<");
        CTeNota cte = null;
        try {
            cte = new DFPersister().read(CTeNota.class, xml);
            CTeGeraChave chave = new CTeGeraChave(cte);
            logs = chave.getChaveAcesso();
        } catch (Exception e) {
            logs = "Erro: " + e.getMessage();
        }
        return logs;
    }*/

    public String geraLinkQrCode(String chave, String ambiente, String UF) {
        String link;
        boolean generated = true;
        switch (UF) {
            case "MT":
                if (ambiente == "1") {
                    link = "<![CDATA[https://www.sefaz.mt.gov.br/cte/qrcode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                    generated = true;
                } else {
                    if (ambiente == "2") {
                        link = "<![CDATA[https://homologacao.sefaz.mt.gov.br/cte/qrcode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                        generated = true;
                    } else {
                        link = "ERRO AO GERAR LINK, AMBIENTE INVÁLIDO - amb: " + ambiente;
                        generated = false;
                    }
                }
                break;
            case "MS":
                if (ambiente == "1" || ambiente == "2") {
                    link = "<![CDATA[http://www.dfe.ms.gov.br/cte/qrcode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                    generated = true;
                } else {
                    link = "ERRO AO GERAR LINK, AMBIENTE INVÁLIDO - amb: " + ambiente;
                    generated = false;
                }
                break;
            case "MG":
                if (ambiente == "1" || ambiente == "2") {
                    link = "<![CDATA[https://cte.fazenda.mg.gov.br/portalcte/sistema/qrcode.xhtml?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                    generated = true;
                } else {
                    link = "ERRO AO GERAR LINK, AMBIENTE INVÁLIDO - amb: " + ambiente;
                    generated = false;
                }
                break;
            case "PR":
                if (ambiente == "1" || ambiente == "2") {
                    link = "<![CDATA[http://www.fazenda.pr.gov.br/cte/qrcode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                    generated = true;
                } else {
                    link = "ERRO AO GERAR LINK, AMBIENTE INVÁLIDO - amb: " + ambiente;
                    generated = false;
                }
                break;
            //Serviço SVSP - Sefaz são paulo
            case "SP":
            case "AP":
            case "PE":
            case "RR":
                if (ambiente == "1") {
                    link = "<![CDATA[https://nfe.fazenda.sp.gov.br/CTeConsulta/qrCode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                } else {
                    if (ambiente == "2") {
                        link = "<![CDATA[https://homologacao.nfe.fazenda.sp.gov.br/CTeConsulta/qrCode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                    } else {
                        link = "ERRO AO GERAR LINK, AMBIENTE INVÁLIDO - amb: " + ambiente;
                    }
                }
                break;
            //Servico RS/SVRS - Sefaz Rio Grande do Sul
            case "RS":
            case "AC":
            case "AL":
            case "AM":
            case "BA":
            case "CE":
            case "DF":
            case "ES":
            case "GO":
            case "MA":
            case "PA":
            case "PB":
            case "PI":
            case "RJ":
            case "RN":
            case "RO":
            case "SC":
            case "SE":
            case "TO":
                if (ambiente == "1" || ambiente == "2") {
                    link = "<![CDATA[https://dfe-portal.svrs.rs.gov.br/cte/qrCode?chCTe=" + chave + "&tpAmb=" + ambiente + "]]>";
                    generated = true;
                } else {
                    link = "ERRO AO GERAR LINK, AMBIENTE INVÁLIDO - amb: " + ambiente;
                    generated = false;
                }
                break;
            default:
                link = "ERRO AO GERAR LINK DE CONSULTA CTe, UF " + UF + " NÃO ENCONTRADA!";
                break;
        }
        return link;
    }

    public String assinarXml(String xmlRec) {

        xmlRec = xmlRec.replaceAll("\r", "");
        xmlRec = xmlRec.replaceAll("\t", "");
        xmlRec = xmlRec.replaceAll("\\s{2,}", "");
        xmlRec = xmlRec.replaceAll("\n", "");
        xmlRec = xmlRec.replaceAll("&gt;", ">");
        xmlRec = xmlRec.replaceAll("&lt;", "<");

        try {
            return new DFAssinaturaDigital(this.config).assinarDocumento(xmlRec, "infCte");
        } catch (Exception e) {
            return "error ao assinar CTe: " + e.getMessage();
        }
    }

}