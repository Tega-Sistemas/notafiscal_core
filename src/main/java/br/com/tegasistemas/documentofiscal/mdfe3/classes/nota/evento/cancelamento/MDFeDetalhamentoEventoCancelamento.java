package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.evento.cancelamento;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class MDFeDetalhamentoEventoCancelamento extends DFBase {
    private static final long serialVersionUID = 3638398807163771387L;

    @Attribute(name = "versaoEvento", required = false)
    private String versaoEvento;

    @Element(name = "evCancMDFe")
    private MDFeEnviaEventoCancelamento eventoCancelamento;

    public void setVersaoEvento(final BigDecimal versaoEvento) {
        this.versaoEvento = BigDecimalValidador.tamanho5Com2CasasDecimais(versaoEvento, "Versao do Evento");
    }

    public String getVersaoEvento() {
        return this.versaoEvento;
    }

    public MDFeEnviaEventoCancelamento getEventoCancelamento() {
        return this.eventoCancelamento;
    }

    public void setEventoCancelamento(final MDFeEnviaEventoCancelamento eventoCancelamento) {
        this.eventoCancelamento = eventoCancelamento;
    }
}