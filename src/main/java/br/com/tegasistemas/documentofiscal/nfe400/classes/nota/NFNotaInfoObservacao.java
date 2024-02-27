package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class NFNotaInfoObservacao extends DFBase {
    private static final long serialVersionUID = 7420062278238333996L;
    
    @Attribute(name = "xCampo")
    private String identificacaoCampo;
    
    @Element(name = "xTexto")
    private String conteudoCampo;

    public void setIdentificacaoCampo(final String identificacaoCampo) {
        DFStringValidador.tamanho20(identificacaoCampo, "Identificacao Campo Obs");
        this.identificacaoCampo = identificacaoCampo;
    }

    public void setConteudoCampo(final String conteudoCampo) {
        DFStringValidador.tamanho60(conteudoCampo, "Conteudo Campo Obs");
        this.conteudoCampo = conteudoCampo;
    }

    public String getConteudoCampo() {
        return this.conteudoCampo;
    }

    public String getIdentificacaoCampo() {
        return this.identificacaoCampo;
    }
}