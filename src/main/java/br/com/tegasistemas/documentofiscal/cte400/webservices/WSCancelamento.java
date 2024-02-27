package br.com.tegasistemas.documentofiscal.cte400.webservices;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeEvento;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeEventoProc;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeEventoRetorno;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.cancelamento.CTeEnviaEventoCancelamento;
import br.com.tegasistemas.documentofiscal.cte400.classes.nota.consulta.CTeNotaConsultaRetorno;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.utils.DFPersister;
import br.com.tegasistemas.documentofiscal.validadores.DFXMLValidador;
import br.com.tegasistemas.documentofiscal.DFModelo;
import org.apache.axiom.om.OMElement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class WSCancelamento extends WSRecepcaoEvento {
    private static final String DESCRICAO_EVENTO = "Cancelamento";
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("4.00");
    private static final String EVENTO_CANCELAMENTO = "110111";
    private static final List<DFModelo> modelosPermitidos = Arrays.asList(DFModelo.CTE, DFModelo.CTeOS);
    
    WSCancelamento(final CTeConfig config) {
        super(config, modelosPermitidos);
    }
    
    CTeEventoRetorno cancelaNotaAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        final OMElement omElementResult = super.efetuaEvento(eventoAssinadoXml, chaveAcesso, VERSAO_LEIAUTE);
        return this.config.getPersister().read(CTeEventoRetorno.class, omElementResult.toString());
    }

    String cancelaNota(final String chaveAcesso, final String numeroProtocolo, final String motivo) throws Exception {
        final String xmlAssinado = this.getXmlAssinado(chaveAcesso, numeroProtocolo, motivo);

        //CTeEvento even = null;
        //System.out.println(xmlAssinado);
        //even = new DFPersister().read(CTeEvento.class, xmlAssinado);

        CTeEventoRetorno retorno = cancelaNotaAssinada(chaveAcesso, xmlAssinado);
        System.out.println(retorno);
        CTeEventoProc ret = new CTeEventoProc();
        ret.setEvento("replace");
        ret.setRetorno(retorno);
        String sretorno = ret.toString();
        System.out.println(sretorno);
        return sretorno.replace("<eventoCTe>replace</eventoCTe>", xmlAssinado);
    }

    String getXmlAssinado(final String chaveAcesso, final String numeroProtocolo, final String motivo) throws Exception {
        final String xml = this.gerarDadosCancelamento(chaveAcesso, numeroProtocolo, motivo).toString();
        return new DFAssinaturaDigital(this.config).assinarDocumento(xml);
    }

    private CTeEvento gerarDadosCancelamento(final String chaveAcesso, final String numeroProtocolo, final String motivo) throws Exception {
        final CTeEnviaEventoCancelamento cancelamento = new CTeEnviaEventoCancelamento();
        cancelamento.setDescricaoEvento(DESCRICAO_EVENTO);
        cancelamento.setJustificativa(motivo.trim());
        cancelamento.setProtocoloAutorizacao(numeroProtocolo);

        DFXMLValidador.validaEventoCancelamentoCTe400(cancelamento.toString());

        return super.gerarEvento(chaveAcesso, VERSAO_LEIAUTE, cancelamento, EVENTO_CANCELAMENTO, null, 1);
    }
}
