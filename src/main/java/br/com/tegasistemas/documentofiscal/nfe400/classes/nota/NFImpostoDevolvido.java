package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFImpostoDevolvido extends DFBase {
    private static final long serialVersionUID = 3300133386344205418L;
    
    @Element(name = "pDevol")
    private String percentualDevolucao;
    
    @Element(name = "IPI")
    private NFInformacaoImpostoDevolvido informacaoIPIDevolvido;

    public String getPercentualDevolucao() {
        return this.percentualDevolucao;
    }

    public void setPercentualDevolucao(final BigDecimal percentualDevolucao) {
        this.percentualDevolucao = DFBigDecimalValidador.tamanho5Com2CasasDecimais(percentualDevolucao, "Percentual Devolucao");
    }

    public NFInformacaoImpostoDevolvido getInformacaoIPIDevolvido() {
        return this.informacaoIPIDevolvido;
    }

    public void setInformacaoIPIDevolvido(final NFInformacaoImpostoDevolvido informacaoIPIDevolvido) {
        this.informacaoIPIDevolvido = informacaoIPIDevolvido;
    }
}