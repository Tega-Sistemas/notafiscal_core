package br.com.tegasistemas.documentofiscal.cte300.webservices;

import br.com.tegasistemas.documentofiscal.cte300.classes.CTAutorizador31;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.cartaCorrecao.*;

import br.com.tegasistemas.documentofiscal.cte300.webservices.recepcaoevento.RecepcaoEventoStub;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.cte300.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte300.parsers.CTChaveParser;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

class WSCartaCorrecao implements DFLog {

    private static final String DESCRICAO_EVENTO = "Carta de Correcao";
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("3.00");
    private static final String EVENTO_CARTACORRECAO = "110110";
    private final CTeConfig config;

    WSCartaCorrecao(final CTeConfig config) {
        this.config = config;
    }

    CTeRetornoCartaCorrecao cartaCorrecaoAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        final OMElement omElementResult = this.efetuaCorrecao(eventoAssinadoXml, chaveAcesso);
        return this.config.getPersister().read(CTeRetornoCartaCorrecao.class, omElementResult.toString());
    }

    CTeRetornoCartaCorrecao corrigeCte(final String chaveAcesso, final String numeroProtocolo, final String grupoAlterado, final String campoAlterado, final String valorAlterado, final String nroItemAlterado, final String condicaoUso) throws Exception {
        final String cancelamentoNotaXML = this.gerarDadosCorrecao(chaveAcesso, numeroProtocolo, grupoAlterado, campoAlterado, valorAlterado, nroItemAlterado, condicaoUso).toString();
        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(cancelamentoNotaXML);
        final OMElement omElementResult = this.efetuaCorrecao(xmlAssinado, chaveAcesso);
        return this.config.getPersister().read(CTeRetornoCartaCorrecao.class, omElementResult.toString());
    }

    String corrigeCteString(final String chaveAcesso, final String numeroProtocolo, final String grupoAlterado, final String campoAlterado, final String valorAlterado, final String nroItemAlterado, final String condicaoUso) throws Exception {
        final String cartaCorrecaoXML = this.gerarDadosCorrecao(chaveAcesso, numeroProtocolo, grupoAlterado, campoAlterado, valorAlterado, nroItemAlterado, condicaoUso).toString();
        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(cartaCorrecaoXML);
        final OMElement omElementResult = this.efetuaCorrecao(xmlAssinado, chaveAcesso);
        return omElementResult.toString() + ";" + xmlAssinado;
    }

    private OMElement efetuaCorrecao(final String xmlAssinado, final String chaveAcesso) throws Exception {
        final CTChaveParser ctChaveParser = new CTChaveParser(chaveAcesso);
        final RecepcaoEventoStub.CteCabecMsg cabec = new RecepcaoEventoStub.CteCabecMsg();
        cabec.setCUF(ctChaveParser.getNFUnidadeFederativa().getCodigoIbge());
        cabec.setVersaoDados(BigDecimalValidador.tamanho5Com2CasasDecimais(VERSAO_LEIAUTE, "Versao do Evento"));

        final RecepcaoEventoStub.CteCabecMsgE cabecE = new RecepcaoEventoStub.CteCabecMsgE();
        cabecE.setCteCabecMsg(cabec);

        final RecepcaoEventoStub.CteDadosMsg dados = new RecepcaoEventoStub.CteDadosMsg();
        final OMElement omElementXML = AXIOMUtil.stringToOM(xmlAssinado);

        this.getLogger().debug(omElementXML.toString());
        dados.setExtraElement(omElementXML);

        final CTAutorizador31 autorizador = CTAutorizador31.valueOfChaveAcesso(chaveAcesso);
        final String urlWebService = autorizador.getRecepcaoEvento(this.config.getAmbiente());
        if (urlWebService == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para RecepcaoEvento " + ctChaveParser.getModelo().name() + ", autorizador " + autorizador.name());
        }

        RecepcaoEventoStub.CteRecepcaoEventoResult cteRecepcaoEventoResult = new RecepcaoEventoStub(urlWebService, this.config).cteRecepcaoEvento(dados, cabecE);
        final OMElement omElementResult = cteRecepcaoEventoResult.getExtraElement();
        this.getLogger().debug(omElementResult.toString());
        return omElementResult;
    }

    private CTeEventoCartaCorrecao gerarDadosCorrecao(final String chaveAcesso, final String numeroProtocolo, final String grupoAlterado, final String campoAlterado, final String valorAlterado, final String nroItemAlterado, final String condicaoUso) {
        final CTChaveParser chaveParser = new CTChaveParser(chaveAcesso);

        //Tag infCorrecao, essa tag possui as informações do que foi alterada pela carta de correção
        final CTeDetalhamentoInfoCartaCorrecao2 infoCorrecao = new CTeDetalhamentoInfoCartaCorrecao2();
        infoCorrecao.setGrupoAlterado(grupoAlterado);
        infoCorrecao.setCampoAlterado(campoAlterado);
        infoCorrecao.setValorAlterado(valorAlterado);
        infoCorrecao.setNroItemAlterado("01");

        final CTeEnviaEventoCartaCorrecao correcao = new CTeEnviaEventoCartaCorrecao();
        correcao.setDescricaoEvento(WSCartaCorrecao.DESCRICAO_EVENTO);
        correcao.setInfoCorrecao(infoCorrecao);
        correcao.setCondUso(condicaoUso);
        CTeDetalhamentoEventoCartaCorrecao cTeDetalhamentoEventoCancelamento = new CTeDetalhamentoEventoCartaCorrecao();
        cTeDetalhamentoEventoCancelamento.setVersaoEvento(WSCartaCorrecao.VERSAO_LEIAUTE);

        final CTeDetalhamentoEventoCartaCorrecao evCC = new CTeDetalhamentoEventoCartaCorrecao();
        evCC.setVersaoEvento(WSCartaCorrecao.VERSAO_LEIAUTE);
        evCC.setEventoCancelamento(correcao);

        //Tag infEvento, dados da informação do evento
        final CTeInfoEventoCartaCorrecao infoEvento = new CTeInfoEventoCartaCorrecao();
        infoEvento.setAmbiente(this.config.getAmbiente());
        infoEvento.setChave(chaveAcesso);
        infoEvento.setCnpj(chaveParser.getCnpjEmitente());
        infoEvento.setDataHoraEvento(ZonedDateTime.now(this.config.getTimeZone().toZoneId()));
        String nroItemToId = nroItemAlterado.length() == 1 ? "0" + nroItemAlterado : nroItemAlterado.length() == 2 ? nroItemAlterado : "SEQUENCIAL.INVALIDO-" + nroItemAlterado;
        infoEvento.setId(String.format("ID%s%s%s", WSCartaCorrecao.EVENTO_CARTACORRECAO, chaveAcesso, nroItemToId));
        infoEvento.setNumeroSequencialEvento(Integer.parseInt(nroItemAlterado));
        infoEvento.setOrgao(chaveParser.getNFUnidadeFederativa());
        infoEvento.setCodigoEvento(WSCartaCorrecao.EVENTO_CARTACORRECAO);
        infoEvento.setCartaCorrecao(evCC);

        CTeEventoCartaCorrecao cTeEventoCancelamento = new CTeEventoCartaCorrecao();
        cTeEventoCancelamento.setInfoEvento(infoEvento);
        cTeEventoCancelamento.setVersao(WSCartaCorrecao.VERSAO_LEIAUTE);

//        CTeProtocoloEventoCartaCorrecao cTeProtocoloEventoCancelamento = new CTeProtocoloEventoCartaCorrecao();
//        cTeProtocoloEventoCancelamento.setVersao(WSCartaCorrecao.VERSAO_LEIAUTE);
//        cTeProtocoloEventoCancelamento.setEvento(cTeEventoCancelamento);
        return cTeEventoCancelamento;
    }
}
