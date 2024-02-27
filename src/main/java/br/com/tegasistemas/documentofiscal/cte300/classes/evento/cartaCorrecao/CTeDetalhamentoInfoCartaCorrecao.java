package br.com.tegasistemas.documentofiscal.cte300.classes.evento.cartaCorrecao;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

public class CTeDetalhamentoInfoCartaCorrecao extends DFBase {
    private static final long serialVersionUID = 8502078404626629549L;
    
    @Element(name = "grupoAlterado")
    private String grupoAlterado;

    @Element(name = "campoAlterado")
    private String campoAlterado;

    @Element(name = "valorAlterado")
    private String valorAlterado;

    @Element(name = "nroItemAlterado")
    private String nroItemAlterado;

    public String getGrupoAlterado() {
        return grupoAlterado;
    }

    public void setGrupoAlterado(String grupoAlterado) {
        this.grupoAlterado = grupoAlterado;
    }

    public String getCampoAlterado() {
        return campoAlterado;
    }

    public void setCampoAlterado(String campoAlterado) {
        this.campoAlterado = campoAlterado;
    }

    public String getValorAlterado() {
        return valorAlterado;
    }

    public void setValorAlterado(String valorAlterado) {
        this.valorAlterado = valorAlterado;
    }

    public String getNroItemAlterado() {
        return nroItemAlterado;
    }

    public void setNroItemAlterado(String nroItemAlterado) {
        this.nroItemAlterado = nroItemAlterado;
    }
}