package br.com.tegasistemas.documentofiscal.cte300.webservices;

import br.com.tegasistemas.documentofiscal.cte300.classes.CTAutorizador31;
import br.com.tegasistemas.documentofiscal.cte300.webservices.inutilizacao.CteInutilizacaoStub;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.inutilizacao.*;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.cte300.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte300.parsers.CTChaveParser;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;


class WSInutilizacao implements DFLog {

    private static final String DESCRICAO_EVENTO = "INUTILIZAR";
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("3.00");
    private static final String VERSAO_SERVICO = "3.00";
    private static final String NOME_SERVICO = "INUTILIZAR";
    private static final Logger LOGGER = LoggerFactory.getLogger(WSInutilizacao.class);
    private final CTeConfig config;



    WSInutilizacao(final CTeConfig config) {
        this.config = config;
    }

    CTeRetornoEventoInutilizacao inutilizaNotaAssinada(final String eventoAssinadoXml, final DFModelo modelo) throws Exception {
        final OMElement omElementResult = this.efetuaInutilizacao(eventoAssinadoXml, modelo);
        return new DFPersister().read(CTeRetornoEventoInutilizacao.class, omElementResult.toString());
    }

    CTeRetornoEventoInutilizacao inutilizaNota(final int anoInutilizacaoNumeracao, final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal, final String justificativa, final DFModelo modelo) throws Exception {
        final String inutilizacaoXMLAssinado = getXmlAssinado(anoInutilizacaoNumeracao, cnpjEmitente, serie, numeroInicial, numeroFinal, justificativa, modelo);
        final OMElement omElementResult = this.efetuaInutilizacao(inutilizacaoXMLAssinado, modelo);
        return new DFPersister().read(CTeRetornoEventoInutilizacao.class, omElementResult.toString());
    }

    String getXmlAssinado(final int anoInutilizacaoNumeracao, final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal, final String justificativa, final DFModelo modelo) throws Exception {
        final String inutilizacaoXML = this.geraDadosInutilizacao(anoInutilizacaoNumeracao, cnpjEmitente, serie, numeroInicial, numeroFinal, justificativa, modelo).toString();
        return new DFAssinaturaDigital(this.config).assinarDocumento(inutilizacaoXML);
    }

    String inutilizaCte(String id, String chaveAcesso, String xServ, String cteInit, String cteFinal, String justificativa, String ano, String mod, String serie) throws Exception {
        final CTChaveParser chaveParser = new CTChaveParser(chaveAcesso);
        int intAno = Integer.parseInt(ano);
        DFModelo modelo = DFModelo.valueOfCodigo(mod);
        String inutilizacaoNotaXML;

        if (DFUnidadeFederativa.SP == chaveParser.getNFUnidadeFederativa()) { //Envia o XML de acordo com sa normas do WebService de SP.
            inutilizacaoNotaXML = this.geraDadosInutilizacao(intAno,chaveParser.getCnpjEmitente(),serie,cteInit,cteFinal,justificativa,modelo).toString();
        } else {
            inutilizacaoNotaXML = this.gerarDadosInutilizacao(id, chaveAcesso, xServ, justificativa, ano, cteInit, cteFinal, mod, serie).toString();
        }

        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(inutilizacaoNotaXML);
        final OMElement omElementResult = this.efetuaInutilizacao(xmlAssinado,modelo);
        return omElementResult.toString();
    }

    private OMElement efetuaInutilizacao(final String inutilizacaoXMLAssinado, final DFModelo modelo) throws Exception {
        final CteInutilizacaoStub.CteDadosMsg dados = new CteInutilizacaoStub.CteDadosMsg();
        final CteInutilizacaoStub.CteCabecMsgE cabec = new CteInutilizacaoStub.CteCabecMsgE();
        CteInutilizacaoStub.CteCabecMsg param = new CteInutilizacaoStub.CteCabecMsg();
        param.setCUF(this.config.getCUF().getCodigoIbge());
        param.setVersaoDados(VERSAO_SERVICO);
        cabec.setCteCabecMsg(param);
        final OMElement omElement = AXIOMUtil.stringToOM(inutilizacaoXMLAssinado);
        WSInutilizacao.LOGGER.debug(omElement.toString());
        dados.setExtraElement(omElement);

        final CTAutorizador31 autorizador = CTAutorizador31.valueOfCodigoUF(this.config.getCUF());
        final String urlWebService = autorizador.getCteInutilizacao(this.config.getAmbiente());
        final CteInutilizacaoStub.CteInutilizacaoCTResult nf4Result = new CteInutilizacaoStub(urlWebService, config).cteInutilizacaoCT(dados, cabec);
        final OMElement dadosRetorno = nf4Result.getExtraElement();
        WSInutilizacao.LOGGER.debug(dadosRetorno.toString());
        return dadosRetorno;
    }

    private CTEnviaEventoInutilizacao geraDadosInutilizacao(final int anoInutilizacaoNumeracao, final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal, final String justificativa, final DFModelo modelo) {
        final CTEnviaEventoInutilizacao inutilizacao = new CTEnviaEventoInutilizacao();
        final CTEventoInutilizacaoDados dados = new CTEventoInutilizacaoDados();
        dados.setAmbiente(this.config.getAmbiente());
        dados.setAno(anoInutilizacaoNumeracao);
        dados.setCnpj(cnpjEmitente);
        dados.setJustificativa(justificativa);
        dados.setModeloDocumentoFiscal(modelo.getCodigo());
        dados.setNomeServico(WSInutilizacao.NOME_SERVICO);
        dados.setNumeroCTInicial(Integer.valueOf(numeroInicial).toString());
        dados.setNumeroCTFinal(Integer.valueOf(numeroFinal).toString());
        dados.setSerie(Integer.valueOf(serie).toString());
        dados.setUf(this.config.getCUF());

        final String numeroInicialTamanhoMaximo = StringUtils.leftPad(numeroInicial, 9, "0");
        final String numeroFinalTamanhoMaximo = StringUtils.leftPad(numeroFinal, 9, "0");
        final String serieTamanhoMaximo = StringUtils.leftPad(serie, 3, "0");

        dados.setIdentificador("ID" + this.config.getCUF().getCodigoIbge() + cnpjEmitente + modelo.getCodigo() + serieTamanhoMaximo + numeroInicialTamanhoMaximo + numeroFinalTamanhoMaximo);
        inutilizacao.setVersao(new BigDecimal(WSInutilizacao.VERSAO_SERVICO));
        inutilizacao.setDados(dados);

        return inutilizacao;
    }

    private CTeProtocoloEventoInutilizacao gerarDadosInutilizacao(String id, String chaveAcesso, String xServ, String justificativa, String ano, String cteInit, String cteFinal, String mod, String serie) {
        final CTChaveParser chaveParser = new CTChaveParser(chaveAcesso);
        final CTeEventoInutilizacao infoEvento = new CTeEventoInutilizacao();
        infoEvento.setAmbiente(this.config.getAmbiente());
        infoEvento.setxServ(xServ);
        infoEvento.setOrgao(chaveParser.getNFUnidadeFederativa());
        infoEvento.setAno(ano);
        infoEvento.setCnpj(chaveParser.getCnpjEmitente());
        /* Faz a conversão de string para integer afim de remover os 0 à esquerda */
        infoEvento.setCteInit(Integer.toString(Integer.parseInt(cteInit)));
        infoEvento.setCteFinal(Integer.toString(Integer.parseInt(cteFinal)));
        infoEvento.setxJust(justificativa);
        infoEvento.setId(id);
        infoEvento.setMod(mod);
        infoEvento.setSerie(serie.replaceAll("0", ""));
        CTeEventoInutilizacao cteEventoInutilizacao = new CTeEventoInutilizacao();
        CTeProtocoloEventoInutilizacao cTeProtocoloEventoCancelamento = new CTeProtocoloEventoInutilizacao();
        cTeProtocoloEventoCancelamento.setVersao(WSInutilizacao.VERSAO_LEIAUTE);
        cTeProtocoloEventoCancelamento.setEvento(infoEvento);
        return cTeProtocoloEventoCancelamento;
    }
}
