package br.com.tegasistemas.documentofiscal.cte300.classes.evento.cartaCorrecao;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class CTeDetalhamentoEventoCartaCorrecao extends DFBase {
    private static final long serialVersionUID = 8502078404626629549L;
    
    @Attribute(name = "versaoEvento")
    private String versaoEvento;
    
    @Element(name = "evCCeCTe")
    private CTeEnviaEventoCartaCorrecao eventoCartaCorrecao;

    public void setVersaoEvento(final BigDecimal versaoEvento) {
        this.versaoEvento = BigDecimalValidador.tamanho5Com2CasasDecimais(versaoEvento, "Versao do Evento");
        System.out.println(versaoEvento);
    }

    public String getVersaoEvento() {
        return this.versaoEvento;
    }

    public CTeEnviaEventoCartaCorrecao getEventoCancelamento() {
        return this.eventoCartaCorrecao;
    }

    public void setEventoCancelamento(final CTeEnviaEventoCartaCorrecao eventoCartaCorrecao) {
        this.eventoCartaCorrecao = eventoCartaCorrecao;
    }
}