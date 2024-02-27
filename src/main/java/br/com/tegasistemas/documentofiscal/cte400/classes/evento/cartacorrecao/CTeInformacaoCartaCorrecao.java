package br.com.tegasistemas.documentofiscal.cte400.classes.evento.cartacorrecao;

import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "infCorrecao")
public class CTeInformacaoCartaCorrecao {
    @Element(name = "grupoAlterado")
    private String grupoAlterado;

    @Element(name = "campoAlterado")
    private String campoAlterado;

    @Element(name = "valorAlterado")
    private String valorAlterado;

    @Element(name = "nroItemAlterado", required = false)
    private String numeroItemAlterado;

    public String getGrupoAlterado() {
        return grupoAlterado;
    }

    public void setGrupoAlterado(String grupoAlterado) {
        DFStringValidador.tamanho20(grupoAlterado, "Grupo Alterado");
        this.grupoAlterado = grupoAlterado;
    }

    public String getCampoAlterado() {
        return campoAlterado;
    }

    public void setCampoAlterado(String campoAlterado) {
        DFStringValidador.tamanho20(grupoAlterado, "Campo Alterado");
        this.campoAlterado = campoAlterado;
    }

    public String getValorAlterado() {
        return valorAlterado;
    }

    public void setValorAlterado(String valorAlterado) {
        DFStringValidador.tamanho500(grupoAlterado, "Valor Alterado");
        this.valorAlterado = valorAlterado;
    }

    public String getNumeroItemAlterado() {
        return numeroItemAlterado;
    }

    public void setNumeroItemAlterado(String numeroItemAlterado) {
        this.numeroItemAlterado = numeroItemAlterado;
    }
}
