package br.com.tegasistemas.documentofiscal.nfe400.webservices;

import br.com.tegasistemas.documentofiscal.nfe400.webservices.gerado.NFeInutilizacao4Stub;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao.NFProtocoloInutilizacao;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.nfe.NFeConfig;
import br.com.tegasistemas.documentofiscal.nfe400.classes.NFAutorizador400;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao.NFEnviaEventoInutilizacao;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao.NFEventoInutilizacaoDados;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao.NFRetornoEventoInutilizacao;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

class WSInutilizacao implements DFLog {

    private static final String VERSAO_SERVICO = "4.00";
    private static final String NOME_SERVICO = "INUTILIZAR";
    private final NFeConfig config;

    WSInutilizacao(final NFeConfig config) {
        this.config = config;
    }

    NFRetornoEventoInutilizacao inutilizaNotaAssinada(final String eventoAssinadoXml, final DFModelo modelo) throws Exception {
        final OMElement omElementResult = this.efetuaInutilizacao(eventoAssinadoXml, modelo);
        return this.config.getPersister().read(NFRetornoEventoInutilizacao.class, omElementResult.toString());
    }

    NFProtocoloInutilizacao inutilizaNota(final int anoInutilizacaoNumeracao, final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal, final String justificativa, final DFModelo modelo) throws Exception {
        final String inutilizacaoXML = this.geraDadosInutilizacao(anoInutilizacaoNumeracao, cnpjEmitente, serie, numeroInicial, numeroFinal, justificativa, modelo).toString();
        final String inutilizacaoXMLAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(inutilizacaoXML);
        final OMElement omElementResult = this.efetuaInutilizacao(inutilizacaoXMLAssinado, modelo);

        final NFEnviaEventoInutilizacao objXmlAssinado = new DFPersister().read(NFEnviaEventoInutilizacao.class, inutilizacaoXMLAssinado);
        final NFRetornoEventoInutilizacao objRetornoEvento = new DFPersister().read(NFRetornoEventoInutilizacao.class, omElementResult.toString());
        NFProtocoloInutilizacao procEvento = new NFProtocoloInutilizacao();
        procEvento.setVersao(VERSAO_SERVICO);
        procEvento.setEvento(objXmlAssinado);
        procEvento.setEventoRetorno(objRetornoEvento);
        return procEvento;
        //return this.config.getPersister().read(NFRetornoEventoInutilizacao.class, omElementResult.toString());
    }

    private OMElement efetuaInutilizacao(final String inutilizacaoXMLAssinado, final DFModelo modelo) throws Exception {
        final NFeInutilizacao4Stub.NfeDadosMsg dados = new NFeInutilizacao4Stub.NfeDadosMsg();
        final OMElement omElement = AXIOMUtil.stringToOM(inutilizacaoXMLAssinado);
        this.getLogger().debug(omElement.toString());
        dados.setExtraElement(omElement);

        final NFAutorizador400 autorizador = NFAutorizador400.valueOfCodigoUF(this.config.getCUF());
        final String urlWebService = DFModelo.NFE.equals(modelo) ? autorizador.getNfeInutilizacao(this.config.getAmbiente()) : autorizador.getNfceInutilizacao(this.config.getAmbiente());
        final NFeInutilizacao4Stub.NfeResultMsg nf4Result = new NFeInutilizacao4Stub(urlWebService).nfeInutilizacaoNF(dados);
        final OMElement dadosRetorno = nf4Result.getExtraElement();
        this.getLogger().debug(dadosRetorno.toString());
        return dadosRetorno;
    }

    private NFEnviaEventoInutilizacao geraDadosInutilizacao(final int anoInutilizacaoNumeracao, final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal, final String justificativa, final DFModelo modelo) {
        final NFEnviaEventoInutilizacao inutilizacao = new NFEnviaEventoInutilizacao();
        final NFEventoInutilizacaoDados dados = new NFEventoInutilizacaoDados();
        dados.setAmbiente(this.config.getAmbiente());
        dados.setAno(anoInutilizacaoNumeracao);
        dados.setCnpj(cnpjEmitente);
        dados.setJustificativa(justificativa);
        dados.setModeloDocumentoFiscal(modelo.getCodigo());
        dados.setNomeServico(WSInutilizacao.NOME_SERVICO);
        dados.setNumeroNFInicial(numeroInicial);
        dados.setNumeroNFFinal(numeroFinal);
        dados.setSerie(serie);
        dados.setUf(this.config.getCUF());
        final String numeroInicialTamanhoMaximo = StringUtils.leftPad(numeroInicial, 9, "0");
        final String numeroFinalTamanhoMaximo = StringUtils.leftPad(numeroFinal, 9, "0");
        final String serieTamanhoMaximo = StringUtils.leftPad(serie, 3, "0");
        dados.setIdentificador("ID" + this.config.getCUF().getCodigoIbge() + anoInutilizacaoNumeracao + cnpjEmitente + modelo.getCodigo() + serieTamanhoMaximo + numeroInicialTamanhoMaximo + numeroFinalTamanhoMaximo);
        inutilizacao.setVersao(new BigDecimal(WSInutilizacao.VERSAO_SERVICO));
        inutilizacao.setDados(dados);
        return inutilizacao;
    }
}
