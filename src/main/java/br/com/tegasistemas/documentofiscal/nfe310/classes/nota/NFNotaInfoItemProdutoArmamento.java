package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoItemProdutoArmamentoTipo;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemProdutoArmamento extends DFBase {
    private static final long serialVersionUID = -4438932795183476289L;
    
    @Element(name = "tpArma")
    private NFNotaInfoItemProdutoArmamentoTipo tipo;
    
    @Element(name = "nSerie")
    private String numeroSerieArma;
    
    @Element(name = "nCano")
    private String numeroSerieCano;
    
    @Element(name = "descr")
    private String descricao;

    public void setTipo(final NFNotaInfoItemProdutoArmamentoTipo tipo) {
        this.tipo = tipo;
    }

    public void setNumeroSerieArma(final String numeroSerieArma) {
        StringValidador.tamanho15(numeroSerieArma, "Numero Serie Arma Armamento");
        this.numeroSerieArma = numeroSerieArma;
    }

    public void setNumeroSerieCano(final String numeroSerieCano) {
        StringValidador.tamanho15(numeroSerieCano, "Numero Serie Cano Armamento");
        this.numeroSerieCano = numeroSerieCano;
    }

    public void setDescricao(final String descricao) {
        StringValidador.tamanho256(descricao, "Descricao Armamento");
        this.descricao = descricao;
    }

    public NFNotaInfoItemProdutoArmamentoTipo getTipo() {
        return this.tipo;
    }

    public String getNumeroSerieArma() {
        return this.numeroSerieArma;
    }

    public String getNumeroSerieCano() {
        return this.numeroSerieCano;
    }

    public String getDescricao() {
        return this.descricao;
    }
}