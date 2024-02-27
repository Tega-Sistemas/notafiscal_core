package br.com.tegasistemas.documentofiscal.cte400.classes.evento;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class CTeTipoEvento extends DFBase {
    private static final long serialVersionUID = -6842953941510024781L;
    
    @Element(name = "descEvento")
    private String descricaoEvento;

    public void setDescricaoEvento(final String descricaoEvento) {
        DFStringValidador.tamanho5a60(descricaoEvento, "Descricao do Evento");
        this.descricaoEvento = descricaoEvento;
    }

    public String getDescricaoEvento() {
        return this.descricaoEvento;
    }
}