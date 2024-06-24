import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.nfe.NFTipoEmissao;
import br.com.tegasistemas.documentofiscal.nfe.NFeConfig;
import br.com.tegasistemas.documentofiscal.nfe.classes.distribuicao.NFDistribuicaoIntRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.classes.cadastro.NFRetornoConsultaCadastro;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.NFEnviaEventoRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.cancelamento.NFProtocoloEventoCancelamento;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.cartacorrecao.NFProtocoloEventoCartaCorrecao;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.consulta.NFLoteConsultaRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvio;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvioRetornoDados;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvioRetornoDadosNew;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteIndicadorProcessamento;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNota;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNotaProcessada;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.consulta.NFNotaConsultaRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao.NFProtocoloInutilizacao;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao.NFRetornoEventoInutilizacao;
import br.com.tegasistemas.documentofiscal.nfe400.classes.statusservico.consulta.NFStatusServicoConsultaRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.utils.NFGeraQRCode;
import br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCode20;
import br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCodeContingenciaOffline20;
import br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCodeEmissaoNormal20;
import br.com.tegasistemas.documentofiscal.nfe400.webservices.WSFacade;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.utils.DFCadeiaCertificados;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.validadores.XMLValidador;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class NfeApi {

    private NFeConfiguracao sconfiguracao;
    private NFLoteIndicadorProcessamento modoprocessamento;

    private String sCadeiaCertificadoSenha;
    private String sCadeiaCertificadoCaminho;
    private String sCaminhoCertificado;
    private String sCertificadoSenha;
    private String sVersao;
    private String sCodigoSegurancaContribuinte;
    private Integer sCodigoSegurancaContribuinteID;

    private DFUnidadeFederativa sEstado;
    private DFAmbiente sAmbiente;

    private String xmlretorno;

    private final NFeConfig config = new NFeConfig() {

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
                try (InputStream certificadoStream = Files.newInputStream(Paths.get(sCaminhoCertificado))) {
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
            return sCadeiaCertificadoSenha;
        }

        @Override
        public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
            if (this.keyStoreCadeia == null) {
                this.keyStoreCadeia = KeyStore.getInstance("JKS");
                try (InputStream cadeia = Files.newInputStream(Paths.get(sCadeiaCertificadoCaminho))) {
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

    public void NfeConfigurar(String CertificadoSenha, String CadeiaCertificadoSenha, String CaminhoCertificado, String CaminhoCadeiaCertificado, String Estado, String Ambiente, String CodigoSegurancaContribuinte, String IdentificadorSegurancaContribuinte, String Versao) {

        sconfiguracao = new NFeConfiguracao();
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
        sVersao = Versao;
        sCodigoSegurancaContribuinte = CodigoSegurancaContribuinte;

        try {
            sCodigoSegurancaContribuinteID = Integer.valueOf(IdentificadorSegurancaContribuinte);
        } catch (NumberFormatException e) {
            sCodigoSegurancaContribuinteID = 0;
        }
    }

    //
    public String NFConsultaDFe(String cnpj, String unidadefederativa, String chaveAcesso, String nsu, String ultNSU) {

        DFUnidadeFederativa dfunidadefederativa = BuscaUnidadeFederativa(unidadefederativa);
        String retorno = null;

        try {
            NFDistribuicaoIntRetorno retDFe = new WSFacade(config).consultarDistribuicaoDFe(cnpj, dfunidadefederativa, chaveAcesso, nsu, ultNSU);
            retorno = retDFe.toString();
        } catch (Exception e) {
            retorno = e.getMessage();
        }

        return retorno;
    }

    public String NfConsultaCNPJ(String cnpj, String unidadefederativa) {

        DFUnidadeFederativa dfunidadefederativa = BuscaUnidadeFederativa(unidadefederativa);
        String retorno = null;

        try {
            NFRetornoConsultaCadastro nfretornoconsultacadastro = new WSFacade(config).consultaCadastro(cnpj, dfunidadefederativa);
            retorno = nfretornoconsultacadastro.toString();
        } catch (Exception e) {
            retorno = e.getMessage();
        }

        return retorno;
    }

    public String NfeValidar(String xml) {

        xml = xml.replaceAll("\r", "");
        xml = xml.replaceAll("\t", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("\n", "");
        xml = xml.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&gt;", ">");

        xml = xml.trim();

        Boolean valido = null;
        String retorno = null;

        try {

            valido = XMLValidador.validaNota400(xml);
            retorno = valido.toString();

        } catch (Exception e) {

            retorno = "Error: " + e.getMessage();

        }

        return retorno;
    }

    public String NfeConsultaLote(String idlote, String Modelo) {

        String retorno = null;
        DFModelo dfmodelo = BuscarModelo(Modelo);
        NFLoteConsultaRetorno retc;

        try {
            retc = new WSFacade(config).consultaLote(idlote.trim(), dfmodelo);
            retorno = retc.toString();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    public String NfeConsultaNota(String chave) {

        NFNotaConsultaRetorno nfloteconsultaretorno = null;
        String retorno = null;

        try {
            nfloteconsultaretorno = new WSFacade(config).consultaNota(chave);
            retorno = nfloteconsultaretorno.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;

    }

    public String NfSituacaoNota(String chave, String xml) {

        NFNota notaRecuperadaAssinada = null;
        NFNotaConsultaRetorno notaRetorno = null;

        try {
            notaRetorno = new WSFacade(config).consultaNota(chave);

            notaRecuperadaAssinada = new DFPersister().read(NFNota.class, xml);

            NFNotaProcessada notaProcessada = new NFNotaProcessada();
            notaProcessada.setVersao(new BigDecimal(config.getVersao()));
            notaProcessada.setProtocolo(notaRetorno.getProtocolo());
            notaProcessada.setNfreplace("replace");
            //notaProcessada.setNota(notaRecuperadaAssinada);
            String NFprocessada = notaProcessada.toString();
            NFprocessada = NFprocessada.replace("<nfereplace>replace</nfereplace>", xml);
            return NFprocessada;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String NfSituacaoNotaXfd(String chave, String xml) {
        NFNotaConsultaRetorno notaRetorno = null;
        NFNota notaFiscal = null;

        try {

            notaRetorno = new WSFacade(config).consultaNota(chave);

            NFNotaProcessada notaProcessada = new NFNotaProcessada();

            notaProcessada.setVersao(new BigDecimal(config.getVersao()));
            notaProcessada.setProtocolo(notaRetorno.getProtocolo());
            notaProcessada.setNota(notaFiscal);
            String auxretorno = "<nfeProc versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">" + xml + notaRetorno.getProtocolo() + "</nfeProc>";
            String retorno = new DFAssinaturaDigital(this.config).assinarDocumento(auxretorno);
            return retorno;

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String NFeEmitir(String XML, String LoteID, String Modulo, String ModoProcessamento) {

        XML = XML.replaceAll("\r", "");
        XML = XML.replaceAll("\t", "");
        XML = XML.replaceAll("\n", "");
        XML = XML.replaceAll("\n", "");
        XML = XML.replaceAll("&lt;", "<");
        XML = XML.replaceAll("&gt;", ">");

        NFLoteIndicadorProcessamento modoprocessamento;
        modoprocessamento = DFBuscarModoProcessamento(ModoProcessamento);

        String value = null;

        xmlretorno = null;

        value = new String(XML.getBytes(StandardCharsets.UTF_8));
        NFNota notaRecuperadaAssinada = null;

        try {
            notaRecuperadaAssinada = new DFPersister().read(NFNota.class, value);
        } catch (Exception e1) {
            xmlretorno = "Error: " + e1.getMessage();
        }

        NFLoteEnvioRetornoDados LoteEnvioRetornoDados;

        // Lote
        NFLoteEnvio lote = new NFLoteEnvio();
        List<NFNota> notas = new ArrayList<>();

        notas.add(notaRecuperadaAssinada);
        lote.setNotas(notas);

        if (LoteID.length() > 1) {

            String loteid = LoteID.replaceAll("\\s+", " ");

            StringValidador.tamanho15N(loteid, "ID do Lote");
            lote.setIdLote(LoteID);
        }

        lote.setVersao(sVersao);
        lote.setIndicadorProcessamento(modoprocessamento);

        if (xmlretorno == null) {
            try {
                config.setTipoEmissao(lote.getNotas().get(0).getInfo().getIdentificacao().getTipoEmissao());
                LoteEnvioRetornoDados = new WSFacade(config).enviaLote(lote);
                xmlretorno = LoteEnvioRetornoDados.getRetorno().toString();
            } catch (Exception e) {
                xmlretorno = "Error: " + e.getMessage();
            }
        }

        return xmlretorno;
    }

    public String NFeEmitirLote(String XML, String modelo, String ModoProcessamento) {
        while (XML.contains(" <")) {
            XML = XML.replaceAll(" <", "<");
        }
        XML = XML.replaceAll("\r", "");
        XML = XML.replaceAll("\t", "");
        XML = XML.replaceAll("\n", "");
        XML = XML.replaceAll("\n", "");
        XML = XML.replaceAll("&lt;", "<");
        XML = XML.replaceAll("&gt;", ">");
        NFTipoEmissao tpEmis;

        try {
            tpEmis = getTpEmisFromXML(XML);
            if (!(tpEmis == null)) {
                config.setTipoEmissao(tpEmis);
            } else {
                return "Erro ao recuperar o tipo de envio da NF";
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            return e.getMessage();
        }

        DFModelo model = null;

        try {
            model = BuscarModelo(modelo);
        } catch (Exception e) {
            return "Erro ao encontrar modelo informado (modelo:" + modelo + ")";
        }
        String xmlConvertido = null;
        NFLoteEnvioRetornoDadosNew LoteEnvioRetornoDados;
        xmlConvertido = new String(XML.getBytes(StandardCharsets.UTF_8));
        try {
            LoteEnvioRetornoDados = new WSFacade(config).enviaLoteString(xmlConvertido, model);
            return LoteEnvioRetornoDados.getRetorno().toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String NfeAssinar(String xmlNotaRecuperada) {

        xmlNotaRecuperada = xmlNotaRecuperada.replaceAll("\r", "");
        xmlNotaRecuperada = xmlNotaRecuperada.replaceAll("\t", "");
        xmlNotaRecuperada = xmlNotaRecuperada.replaceAll("\n", "");
        xmlNotaRecuperada = xmlNotaRecuperada.replaceAll("\n", "");
        xmlNotaRecuperada = xmlNotaRecuperada.replaceAll("&lt;", "<");
        xmlNotaRecuperada = xmlNotaRecuperada.replaceAll("&gt;", ">");
        String xmlNotaRecuperadaAssinada = null;
        try {
            xmlNotaRecuperadaAssinada = new DFAssinaturaDigital(config).assinarDocumento(xmlNotaRecuperada);
        } catch (Exception e) {
            xmlNotaRecuperadaAssinada += "error :" + e.getMessage();
        }
        return xmlNotaRecuperadaAssinada;
    }

    public String NfeValidade(String CertificadoKeyStore, String senha) {

        String info = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(Files.newInputStream(Paths.get(CertificadoKeyStore)), senha.toCharArray());
            Enumeration<String> eAliases = keyStore.aliases();

            while (eAliases.hasMoreElements()) {
                String alias = eAliases.nextElement();

                Certificate certificado = keyStore.getCertificate(alias);
                X509Certificate c = (X509Certificate) certificado;

                try {
                    c.checkValidity();
                    info = "true_" + sdf.format(c.getNotAfter()) + "_" + c.getSubjectAlternativeNames() + "_" + c.getSubjectDN();
                } catch (CertificateExpiredException e) {
                    info = "false_" + sdf.format(c.getNotAfter()) + "_" + c.getSubjectAlternativeNames() + "_" + c.getSubjectDN();
                }
            }
        } catch (Exception e) {
            info = e.getLocalizedMessage();
        }


        return info;
    }

    public String NfeWebServiceStatus(String UF, String Modelo, String tpEmis) {
        DFUnidadeFederativa dfuf = BuscaUnidadeFederativa(UF);
        DFModelo dfmodelo = BuscarModelo(Modelo);
        String retorno = null;
        NFTipoEmissao nfTpEmis = NFTipoEmissao.valueOfCodigo(tpEmis);

        try {
            NFStatusServicoConsultaRetorno nfstatusservicoconsultatretorno = new WSFacade(config).consultaStatus(dfuf, dfmodelo, nfTpEmis);
            retorno = nfstatusservicoconsultatretorno.getStatus() + "," + nfstatusservicoconsultatretorno.getMotivo() + "," + nfstatusservicoconsultatretorno.getObservacao();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    public String NfCancelar(String chaveDeAcessoDaNota, String protocoloDaNota, String motivoCancelamento) {

        NFProtocoloEventoCancelamento nfenviaeventoretorno = null;
        String logs;
        try {
            nfenviaeventoretorno = new WSFacade(config).cancelaNota(chaveDeAcessoDaNota, protocoloDaNota, motivoCancelamento);
            logs = nfenviaeventoretorno.toString();
        } catch (Exception e) {
            logs = e.getMessage();
        }

        return nfenviaeventoretorno.toString();
    }

    public String NfCorrigirNota(String chaveDeAcessoDaNota, String textoCorrecao, int sequencialEventoDaNota) {

        //NFEnviaEventoRetorno nfenviaeventoretorno = null;
        NFProtocoloEventoCartaCorrecao protocolado = null;
        String xmlEnvio = null;
        String retorno;
        try {
            //nfenviaeventoretorno = new WSFacade(config).corrigeNota(chaveDeAcessoDaNota, textoCorrecao, sequencialEventoDaNota);
            xmlEnvio = new WSFacade(config).geraXMLCorrecaoAssinado(chaveDeAcessoDaNota, textoCorrecao, sequencialEventoDaNota);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(xmlEnvio);
            //nfenviaeventoretorno = new WSFacade(config).corrigeNotaAssinada(xmlEnvio);
            protocolado = new WSFacade(config).corrigeNotaAssinadaProtocolo(xmlEnvio);
            retorno = protocolado.toString();
            //corrigeNota(chaveDeAcessoDaNota, textoCorrecao, sequencialEventoDaNota);
        } catch (Exception e) {
            retorno = e.getMessage();
        }

        return retorno;
    }

    public String NFInutilizarXML(String xmlassinado, String modelo) {

        DFModelo dfmodelo = BuscarModelo(modelo);
        String retorno = null;

        NFRetornoEventoInutilizacao nfretornoeventoinutilizacao;
        try {
            nfretornoeventoinutilizacao = new WSFacade(config).inutilizaNotaAssinada(xmlassinado, dfmodelo);
            retorno = nfretornoeventoinutilizacao.toString();
        } catch (Exception e) {
            retorno = e.getMessage();
        }

        return retorno;
    }

    public String NFInutilizar(int anoInutilizacaoNumeracao, String cnpjEmitente, String serie, String numeroInicial, String numeroFinal, String justificativa, String modelo) {

        String retorno = null;

        DFModelo dfmodelo = BuscarModelo(modelo);

        try {
            NFProtocoloInutilizacao nfretornoeventoinutilizacao = new WSFacade(config).inutilizaNota(anoInutilizacaoNumeracao, cnpjEmitente, serie, numeroInicial, numeroFinal, justificativa, dfmodelo);
            retorno = nfretornoeventoinutilizacao.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public String NFCadeiaCertificado(String pathprod, String senhaprod, String pathhomol, String senhahomol) {

        String retorno = null;

        try {
            FileUtils.writeByteArrayToFile(new File(pathhomol), DFCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.HOMOLOGACAO, senhahomol));
            FileUtils.writeByteArrayToFile(new File(pathprod), DFCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.PRODUCAO, senhaprod));
            retorno = "Atualizado cadeia de certificado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;

    }

    public String NFManifestaDestNotaAssinada(String chaveAcesso, String eventoAssinadoXml) {

        NFEnviaEventoRetorno nfenviaeventoretorno;
        String retorno = null;

        try {
            nfenviaeventoretorno = new WSFacade(config).manifestaDestinatarioNotaAssinada(chaveAcesso, eventoAssinadoXml);
            retorno = nfenviaeventoretorno.toString();
        } catch (Exception e) {
            retorno = e.getMessage();
        }


        return retorno;
    }

    public String NFGeraURLString(String xml) {
        String retorno = null;
        try {
            NFNota notaRecuperadaAssinada = new DFPersister().read(NFNota.class, xml);
            NFNotaProcessada notaProcessada = new NFNotaProcessada();
            notaProcessada.setVersao(new BigDecimal(config.getVersao()));
            notaProcessada.setNota(notaRecuperadaAssinada);
            NFGeraQRCode nfgeraqrcode = new NFGeraQRCode(notaProcessada.getNota(), config);
            retorno = nfgeraqrcode.getQRCodev2();
        } catch (Exception e) {
            retorno = e.getMessage();
        }

        return retorno;
    }

    public String NFDateTime(String local) {


        String DATE_FORMAT = "yyyy-MM-dd HH:mm:s";

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone(local));

        return sdf.format((new Date()));
    }

    public String NFDanfeImprimir() {

        return null;

    }

    public String NFCeGerarLinkQRcode(String XML) {
        XML = XML.replaceAll("\r", "");
        XML = XML.replaceAll("\t", "");
        XML = XML.replaceAll("\n", "");
        XML = XML.replaceAll("\n", "");
        XML = XML.replaceAll("&lt;", "<");
        XML = XML.replaceAll("&gt;", ">");
        String value = null;
        xmlretorno = null;
        try {
            value = new String(XML.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            xmlretorno = "Error na conversão do xml para UTF-8: " + e2.getMessage();
        }
        NFNota nota = null;
        try {
            nota = new DFPersister().read(NFNota.class, value);
        } catch (Exception e1) {
            xmlretorno = "Error ao converter dados da NF: " + e1.getMessage();
        }
        NFGeraQRCode20 geraQRCode = getNfGeraQRCode20(nota);
        try {
            xmlretorno = "<![CDATA[" + geraQRCode.getQRCode() + "]]>;" + geraQRCode.urlConsultaChaveAcesso();
        } catch (Exception e) {
            xmlretorno = "Erro ao retornar dados do QR Code " + e;
        }

        return xmlretorno;
    }

    private NFGeraQRCode20 getNfGeraQRCode20(NFNota nota) {
        if (NFTipoEmissao.EMISSAO_NORMAL.equals(nota.getInfo().getIdentificacao().getTipoEmissao())) {
            return new NFGeraQRCodeEmissaoNormal20(nota, this.config);
        } else if (NFTipoEmissao.CONTIGENCIA_OFFLINE.equals(nota.getInfo().getIdentificacao().getTipoEmissao())) {
            return new NFGeraQRCodeContingenciaOffline20(nota, this.config);
        } else {
            throw new IllegalArgumentException("QRCode 2.0 Tipo Emissao nao implementado: " + nota.getInfo().getIdentificacao().getTipoEmissao().getDescricao());
        }
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

    private DFModelo BuscarModelo(String Modelo) {

        DFModelo modelo;

        //'01' '04' '55' '65' '57' '58' '67'

        switch (Modelo) {
            case "01":
                modelo = DFModelo.AVULSA;
                break;
            case "57":
                modelo = DFModelo.CTE;
                break;
            case "67":
                modelo = DFModelo.CTeOS;
                break;
            case "58":
                modelo = DFModelo.MDFE;
                break;
            case "65":
                modelo = DFModelo.NFCE;
                break;
            case "55":
                modelo = DFModelo.NFE;
                break;
            case "04":
                modelo = DFModelo.PRODUTOR;
                break;
            default:
                modelo = DFModelo.NFE;
        }

        return modelo;
    }

    private NFLoteIndicadorProcessamento DFBuscarModoProcessamento(String ModoProcessamento) {

        if (ModoProcessamento.equals("1")) {
            modoprocessamento = NFLoteIndicadorProcessamento.PROCESSAMENTO_SINCRONO;
        } else {
            modoprocessamento = NFLoteIndicadorProcessamento.PROCESSAMENTO_ASSINCRONO;
        }

        return modoprocessamento;
    }

    @Nullable
    public static NFTipoEmissao getTpEmisFromXML(@NotNull String xmlString)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(xmlString.getBytes());
        Document doc = builder.parse(input);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("tpEmis");
        Node node = nodeList.item(0);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            return NFTipoEmissao.valueOfCodigo(element.getTextContent());
        }

        return null;

    }

}