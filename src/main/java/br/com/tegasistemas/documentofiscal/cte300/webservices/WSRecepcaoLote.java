package br.com.tegasistemas.documentofiscal.cte300.webservices;

import br.com.tegasistemas.documentofiscal.cte300.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte300.classes.CTAutorizador31;
import br.com.tegasistemas.documentofiscal.cte300.webservices.recepcao.CteRecepcaoStub;
import br.com.tegasistemas.documentofiscal.cte300.parsers.CTChaveParser;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLote;
import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLoteRetorno;
import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLoteRetornoDados;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.validadores.XMLValidador;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.Iterator;

class WSRecepcaoLote implements DFLog {

    private static final String CTE_ELEMENTO = "CTe";
    private final CTeConfig config;

    WSRecepcaoLote(final CTeConfig config) {
        this.config = config;
    }

    public CTeEnvioLoteRetornoDados envioRecepcao(CTeEnvioLote cteRecepcaoLote) throws Exception {
        final String documentoAssinado  = new DFAssinaturaDigital(this.config).assinarDocumento(cteRecepcaoLote.toString(), "infCte");
        final CTeEnvioLote loteAssinado = this.config.getPersister().read(CTeEnvioLote.class, documentoAssinado);
        final CTChaveParser chaveParser = new CTChaveParser(loteAssinado.getNota().get(0).getCteNotaInfo().getChaveAcesso());
        String tipoEnvio = chaveParser.getFormaEmissao().getCodigo();
        final CTeEnvioLoteRetorno retorno = comunicaLote(documentoAssinado, tipoEnvio);
        return new CTeEnvioLoteRetornoDados(retorno, loteAssinado);
    }

    private CTeEnvioLoteRetorno comunicaLote(final String loteAssinadoXml, final String TipoEnvio) throws Exception {
        //valida o lote assinado, para verificar se o xsd foi satisfeito, antes de comunicar com a sefaz
        XMLValidador.validaLoteCTe(loteAssinadoXml);
        //envia o lote para a sefaz
        final OMElement omElement = this.cteToOMElement(loteAssinadoXml);
        final CteRecepcaoStub.CteDadosMsg dados = new CteRecepcaoStub.CteDadosMsg();
        dados.setExtraElement(omElement);
        final CteRecepcaoStub.CteCabecMsgE cabecalhoSOAP = this.getCabecalhoSOAP();
        this.getLogger().debug(omElement.toString());
        final CTAutorizador31 autorizador = CTAutorizador31.valueOfTipoEmissao(TipoEnvio, this.config.getCUF());
        final String endpoint = autorizador.getCteRecepcao(this.config.getAmbiente());
        if (endpoint == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para Recepcao, autorizador " + autorizador.name() + ", UF " + this.config.getCUF().name());
        }
        final CteRecepcaoStub.CteRecepcaoLoteResult autorizacaoLoteResult = new CteRecepcaoStub(endpoint).cteRecepcaoLote(dados, cabecalhoSOAP);
        final CTeEnvioLoteRetorno retorno = this.config.getPersister().read(CTeEnvioLoteRetorno.class, autorizacaoLoteResult.getExtraElement().toString());
        this.getLogger().debug(retorno.toString());
        return retorno;
    }

    private CteRecepcaoStub.CteCabecMsgE getCabecalhoSOAP() {
        final CteRecepcaoStub.CteCabecMsg cabecalho = new CteRecepcaoStub.CteCabecMsg();
        cabecalho.setCUF(this.config.getCUF().getCodigoIbge());
        cabecalho.setVersaoDados(CTeConfig.VERSAO);
        final CteRecepcaoStub.CteCabecMsgE cabecalhoSOAP = new CteRecepcaoStub.CteCabecMsgE();
        cabecalhoSOAP.setCteCabecMsg(cabecalho);
        return cabecalhoSOAP;
    }

    private OMElement cteToOMElement(final String documento) throws XMLStreamException {
        final XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_COALESCING, false);
        XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(documento));
        StAXOMBuilder builder = new StAXOMBuilder(reader);
        final OMElement ome = builder.getDocumentElement();
        final Iterator<?> children = ome.getChildrenWithLocalName(WSRecepcaoLote.CTE_ELEMENTO);
        while (children.hasNext()) {
            final OMElement omElement = (OMElement) children.next();
            if ((omElement != null) && (WSRecepcaoLote.CTE_ELEMENTO.equals(omElement.getLocalName()))) {
                omElement.addAttribute("xmlns", CTeConfig.NAMESPACE, null);
            }
        }
        return ome;
    }
}
