package br.com.tegasistemas.documentofiscal.cte200.classes.cte;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import br.com.tegasistemas.documentofiscal.DFBase;

@Root(name = "ObsCont")
public class CTInfoObservacaoContribuinte extends DFBase {
    private static final long serialVersionUID = 4590687379510866778L;

    @Attribute(name = "xCampo")
    private String identificador;

    @Element(name = "xTexto")
    private String conteudo;

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(final String identificador) {
        this.identificador = identificador;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }

}
