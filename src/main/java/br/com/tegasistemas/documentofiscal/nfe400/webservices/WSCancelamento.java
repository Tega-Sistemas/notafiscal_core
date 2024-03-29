package br.com.tegasistemas.documentofiscal.nfe400.webservices;

import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.cancelamento.*;
import br.com.tegasistemas.documentofiscal.nfe400.webservices.gerado.NFeRecepcaoEvento4Stub;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;

import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.nfe.NFeConfig;
import br.com.tegasistemas.documentofiscal.nfe400.NotaFiscalChaveParser;
import br.com.tegasistemas.documentofiscal.nfe400.classes.NFAutorizador400;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.NFEnviaEventoRetorno;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

class WSCancelamento implements DFLog {

    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("1.00");
    private static final String DESCRICAO_EVENTO = "Cancelamento";
    private static final String EVENTO_CANCELAMENTO = "110111";
    private static final String DESCRICAO_EVENTO_CANCELAMENTO_POR_SUBSTITUICAO = "Cancelamento por substituicao";
    private static final String EVENTO_CANCELAMENTO_POR_SUBSTITUICAO = "110112";
    private final NFeConfig config;

    WSCancelamento(final NFeConfig config) {
        this.config = config;
    }

    NFEnviaEventoRetorno cancelaNotaAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        final OMElement omElementResult = this.efetuaCancelamento(eventoAssinadoXml, chaveAcesso);
        return this.config.getPersister().read(NFEnviaEventoRetorno.class, omElementResult.toString());
    }

    NFProtocoloEventoCancelamento cancelaNota(final String chaveAcesso, final String numeroProtocolo, final String motivo) throws Exception {
        final String cancelamentoNotaXML = this.gerarDadosCancelamento(chaveAcesso, numeroProtocolo, motivo).toString();
        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(cancelamentoNotaXML);
        final OMElement omElementResult = this.efetuaCancelamento(xmlAssinado, chaveAcesso);
        //Trata retorno dentro da estrutura do procEvento
        final NFEnviaEventoCancelamento objXmlAssinado = new DFPersister().read(NFEnviaEventoCancelamento.class, xmlAssinado);
        final NFEnviaEventoRetorno objRetornoEvento = new DFPersister().read(NFEnviaEventoRetorno.class, omElementResult.toString());
        NFProtocoloEventoCancelamento procEvento = new NFProtocoloEventoCancelamento();
        procEvento.setVersao("1.00");
        if (objXmlAssinado.getEvento().size() > 0) {
            procEvento.setEvento(objXmlAssinado.getEvento().get(0));
        }
        if (objRetornoEvento.getEventoRetorno().size() > 0) {
            procEvento.setEventoRetorno(objRetornoEvento.getEventoRetorno().get(0));
        }
        return procEvento;
    }

    private OMElement efetuaCancelamento(final String xmlAssinado, final String chaveAcesso) throws Exception {
        final NFeRecepcaoEvento4Stub.NfeDadosMsg dados = new NFeRecepcaoEvento4Stub.NfeDadosMsg();
        final OMElement omElementXML = AXIOMUtil.stringToOM(xmlAssinado);
        this.getLogger().debug(omElementXML.toString());
        dados.setExtraElement(omElementXML);

        final NotaFiscalChaveParser parser = new NotaFiscalChaveParser(chaveAcesso);
        final NFAutorizador400 autorizador = NFAutorizador400.valueOfChaveAcesso(chaveAcesso);
        final String urlWebService = DFModelo.NFCE.equals(parser.getModelo()) ? autorizador.getNfceRecepcaoEvento(this.config.getAmbiente()) : autorizador.getRecepcaoEvento(this.config.getAmbiente());
        if (urlWebService == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para RecepcaoEvento " + parser.getModelo().name() + ", autorizador " + autorizador.name());
        }

        final NFeRecepcaoEvento4Stub.NfeResultMsg nfeRecepcaoEvento = new NFeRecepcaoEvento4Stub(urlWebService).nfeRecepcaoEvento(dados);
        final OMElement omElementResult = nfeRecepcaoEvento.getExtraElement();
        this.getLogger().debug(omElementResult.toString());
        return omElementResult;
    }

    private NFEnviaEventoCancelamento gerarDadosCancelamento(final String chaveAcesso, final String numeroProtocolo, final String motivo) {
        final NotaFiscalChaveParser chaveParser = new NotaFiscalChaveParser(chaveAcesso);

        final NFInfoCancelamento cancelamento = new NFInfoCancelamento();
        cancelamento.setDescricaoEvento(WSCancelamento.DESCRICAO_EVENTO);
        cancelamento.setVersao(WSCancelamento.VERSAO_LEIAUTE);
        cancelamento.setJustificativa(motivo);
        cancelamento.setProtocoloAutorizacao(numeroProtocolo);

        final NFInfoEventoCancelamento infoEvento = new NFInfoEventoCancelamento();
        infoEvento.setAmbiente(this.config.getAmbiente());
        infoEvento.setChave(chaveAcesso);
        //infoEvento.setCpf(chaveParser.getCpfEmitente());
        infoEvento.setCnpj(chaveParser.getCnpjEmitente());
        infoEvento.setDataHoraEvento(ZonedDateTime.now(this.config.getTimeZone().toZoneId()));
        infoEvento.setId(String.format("ID%s%s0%s", WSCancelamento.EVENTO_CANCELAMENTO, chaveAcesso, "1"));
        infoEvento.setNumeroSequencialEvento(1);
        infoEvento.setOrgao(chaveParser.getNFUnidadeFederativa());
        infoEvento.setCodigoEvento(WSCancelamento.EVENTO_CANCELAMENTO);
        infoEvento.setVersaoEvento(WSCancelamento.VERSAO_LEIAUTE);
        infoEvento.setCancelamento(cancelamento);

        final NFEventoCancelamento evento = new NFEventoCancelamento();
        evento.setInfoEvento(infoEvento);
        evento.setVersao(WSCancelamento.VERSAO_LEIAUTE);

        final NFEnviaEventoCancelamento enviaEvento = new NFEnviaEventoCancelamento();
        enviaEvento.setEvento(Collections.singletonList(evento));
        enviaEvento.setIdLote(Long.toString(ZonedDateTime.now(this.config.getTimeZone().toZoneId()).toInstant().toEpochMilli()));
        enviaEvento.setVersao(WSCancelamento.VERSAO_LEIAUTE);
        return enviaEvento;
    }

    NFEnviaEventoRetorno cancelaNotaPorSubstituicao(final String chaveAcesso, final String numeroProtocolo, final String motivo, final String versaoAplicativoAutorizador, final String chaveSubstituta) throws Exception {
        final String cancelamentoNotaXML = this.gerarDadosCancelamentoPorSubstituicao(chaveAcesso, numeroProtocolo, motivo, versaoAplicativoAutorizador, chaveSubstituta).toString();
        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(cancelamentoNotaXML);
        final OMElement omElementResult = this.efetuaCancelamento(xmlAssinado, chaveAcesso);
        return this.config.getPersister().read(NFEnviaEventoRetorno.class, omElementResult.toString());
    }

    private NFEnviaEventoCancelamento gerarDadosCancelamentoPorSubstituicao(final String chaveAcesso, final String numeroProtocolo, final String motivo, final String versaoAplicativoAutorizador, final String chaveSubstituta) {
        final NotaFiscalChaveParser chaveParser = new NotaFiscalChaveParser(chaveAcesso);

        if (DFModelo.NFE.equals(chaveParser.getModelo()))
            throw new IllegalArgumentException("Evento nao permitido para modelo 55 - NFe!");

        final NFInfoCancelamento cancelamento = new NFInfoCancelamento();
        cancelamento.setDescricaoEvento(WSCancelamento.DESCRICAO_EVENTO_CANCELAMENTO_POR_SUBSTITUICAO);
        cancelamento.setUfAutorizador(chaveParser.getNFUnidadeFederativa());
        cancelamento.setTipoAutorizador("1");//como orientado no manual
        cancelamento.setVersaoAplicativo(versaoAplicativoAutorizador);
        cancelamento.setVersao(WSCancelamento.VERSAO_LEIAUTE);
        cancelamento.setJustificativa(motivo);
        cancelamento.setProtocoloAutorizacao(numeroProtocolo);
        cancelamento.setChaveAcessoSubstituta(chaveSubstituta);

        final NFInfoEventoCancelamento infoEvento = new NFInfoEventoCancelamento();
        infoEvento.setAmbiente(this.config.getAmbiente());
        infoEvento.setChave(chaveAcesso);
        infoEvento.setCpf(chaveParser.getCpfEmitente());
        infoEvento.setCnpj(chaveParser.getCnpjEmitente());
        infoEvento.setDataHoraEvento(ZonedDateTime.now(this.config.getTimeZone().toZoneId()));
        infoEvento.setId(String.format("ID%s%s0%s", WSCancelamento.EVENTO_CANCELAMENTO_POR_SUBSTITUICAO, chaveAcesso, "1"));
        infoEvento.setNumeroSequencialEvento(1);
        infoEvento.setOrgao(chaveParser.getNFUnidadeFederativa());
        infoEvento.setCodigoEvento(WSCancelamento.EVENTO_CANCELAMENTO_POR_SUBSTITUICAO);
        infoEvento.setVersaoEvento(WSCancelamento.VERSAO_LEIAUTE);
        infoEvento.setCancelamento(cancelamento);

        final NFEventoCancelamento evento = new NFEventoCancelamento();
        evento.setInfoEvento(infoEvento);
        evento.setVersao(WSCancelamento.VERSAO_LEIAUTE);

        final NFEnviaEventoCancelamento enviaEvento = new NFEnviaEventoCancelamento();
        enviaEvento.setEvento(Collections.singletonList(evento));
        enviaEvento.setIdLote(Long.toString(ZonedDateTime.now(this.config.getTimeZone().toZoneId()).toInstant().toEpochMilli()));
        enviaEvento.setVersao(WSCancelamento.VERSAO_LEIAUTE);
        return enviaEvento;
    }
}
