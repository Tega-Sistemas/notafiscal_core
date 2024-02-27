package br.com.tegasistemas.documentofiscal.cte300.classes.evento.cancelamento;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.CTeEventoAutorizadoMDFe;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class CTeDetalhamentoEventoCancelamento extends DFBase {
    private static final long serialVersionUID = 8502078404626629549L;

    @Attribute(name = "versaoEvento")
    private String versaoEvento;

    @Element(name = "evCancCTe", required = false)
    private CTeEnviaEventoCancelamento eventoCancelamento;

    @Element(name = "evCTeAutorizadoMDFe", required = false)
    private CTeEventoAutorizadoMDFe evCTeAutorizadoMDFe;

    public void setVersaoEvento(final BigDecimal versaoEvento) {
        this.versaoEvento = BigDecimalValidador.tamanho5Com2CasasDecimais(versaoEvento, "Versao do Evento");
    }

    public String getVersaoEvento() {
        return this.versaoEvento;
    }

    public CTeEnviaEventoCancelamento getEventoCancelamento() {
        return this.eventoCancelamento;
    }

    public void setEventoCancelamento(final CTeEnviaEventoCancelamento eventoCancelamento) {
        this.eventoCancelamento = eventoCancelamento;
    }

    public CTeEventoAutorizadoMDFe getEvCTeAutorizadoMDFe() {
        return evCTeAutorizadoMDFe;
    }

    public void setEvCTeAutorizadoMDFe(CTeEventoAutorizadoMDFe evCTeAutorizadoMDFe) {
        this.evCTeAutorizadoMDFe = evCTeAutorizadoMDFe;
    }

}