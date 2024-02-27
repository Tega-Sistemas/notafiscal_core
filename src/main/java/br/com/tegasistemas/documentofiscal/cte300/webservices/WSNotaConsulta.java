package br.com.tegasistemas.documentofiscal.cte300.webservices;

import br.com.tegasistemas.documentofiscal.cte300.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte300.classes.CTAutorizador31;
import br.com.tegasistemas.documentofiscal.cte300.webservices.consulta.CteConsultaStub;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.cte300.classes.nota.consulta.CTeNotaConsulta;
import br.com.tegasistemas.documentofiscal.cte300.classes.nota.consulta.CTeNotaConsultaRetorno;
import br.com.tegasistemas.documentofiscal.cte300.parsers.CTChaveParser;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.math.BigDecimal;

class WSNotaConsulta implements DFLog {
    private static final String NOME_SERVICO = "CONSULTAR";
    private static final String VERSAO_SERVICO = "3.00";
    private final CTeConfig config;

    WSNotaConsulta(final CTeConfig config) {
        this.config = config;
    }

    public CTeNotaConsultaRetorno consultaNota(final String chaveDeAcesso) throws Exception {
        final OMElement omElementConsulta = AXIOMUtil.stringToOM(this.gerarDadosConsulta(chaveDeAcesso).toString());
        this.getLogger().debug(omElementConsulta.toString());
        final OMElement omElementRetorno = this.efetuaConsulta(omElementConsulta, chaveDeAcesso);
        this.getLogger().debug(omElementRetorno.toString());
        final CTeNotaConsultaRetorno retorno = new DFPersister(false).read(CTeNotaConsultaRetorno.class, omElementRetorno.toString()); //this.config.getPersisterFalse().read();
        this.getLogger().debug(retorno.toString());
        return retorno;
    }

    public String consultaNotaString(final String chaveDeAcesso) throws Exception {
        final OMElement omElementConsulta = AXIOMUtil.stringToOM(this.gerarDadosConsulta(chaveDeAcesso).toString());
        this.getLogger().debug(omElementConsulta.toString());
        final OMElement omElementRetorno = this.efetuaConsulta(omElementConsulta, chaveDeAcesso);
        this.getLogger().debug(omElementRetorno.toString());
        return omElementRetorno.toString();
    }

    public String consultaNotaChave(final String chaveDeAcesso) throws Exception {
        final OMElement omElementConsulta = AXIOMUtil.stringToOM(this.gerarDadosConsulta(chaveDeAcesso).toString());
        this.getLogger().debug(omElementConsulta.toString());
        final OMElement omElementRetorno = this.efetuaConsulta(omElementConsulta, chaveDeAcesso);
        this.getLogger().debug(omElementRetorno.toString());
        return omElementRetorno.toString();
    }

    private OMElement efetuaConsulta(final OMElement omElementConsulta, final String chaveDeAcesso) throws Exception {
        final CTChaveParser ctChaveParser = new CTChaveParser(chaveDeAcesso);

        final CteConsultaStub.CteCabecMsg cabec = new CteConsultaStub.CteCabecMsg();

        cabec.setCUF(ctChaveParser.getNFUnidadeFederativa().getCodigoIbge());
        cabec.setVersaoDados(WSNotaConsulta.VERSAO_SERVICO);

        final CteConsultaStub.CteCabecMsgE cabecE = new CteConsultaStub.CteCabecMsgE();
        cabecE.setCteCabecMsg(cabec);

        final CteConsultaStub.CteDadosMsg dados = new CteConsultaStub.CteDadosMsg();
        dados.setExtraElement(omElementConsulta);

        this.getLogger().debug(cabec.toString());

        final CTChaveParser chaveParser = new CTChaveParser(chaveDeAcesso);
        String tipoEnvio = chaveParser.getFormaEmissao().getCodigo();

        final CTAutorizador31 autorizador = CTAutorizador31.valueOfTipoEmissao(tipoEnvio, this.config.getCUF());
        final String endpoint = autorizador.getCteConsultaProtocolo(this.config.getAmbiente());
        if (endpoint == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para Consulta, autorizador " + autorizador.name() + ", UF " + this.config.getCUF().name());
        }
        this.getLogger().debug(endpoint);
        final CteConsultaStub.CteConsultaCTResult cteConsultaCTResult = new CteConsultaStub(endpoint).cteConsultaCT(dados, cabecE);
        return cteConsultaCTResult.getExtraElement();
    }

    private CTeNotaConsulta gerarDadosConsulta(final String chaveDeAcesso) {
        final CTeNotaConsulta notaConsulta = new CTeNotaConsulta();
        notaConsulta.setAmbiente(this.config.getAmbiente());
        notaConsulta.setChave(chaveDeAcesso);
        notaConsulta.setServico(WSNotaConsulta.NOME_SERVICO);
        notaConsulta.setVersao(new BigDecimal(WSNotaConsulta.VERSAO_SERVICO));
        return notaConsulta;
    }
}
