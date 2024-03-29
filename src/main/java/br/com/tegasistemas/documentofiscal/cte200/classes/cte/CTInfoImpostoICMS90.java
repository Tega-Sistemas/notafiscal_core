package br.com.tegasistemas.documentofiscal.cte200.classes.cte;

import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoImpostoTributacaoICMS;
import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;

public class CTInfoImpostoICMS90 extends DFBase {
    private static final long serialVersionUID = 2424510254872941982L;

    @Element(name = "CST")
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;

    @Element(name = "pRedBC", required = false)
    private String percentualReducaoBaseCalculo;

    @Element(name = "vBC")
    private String valorBaseCalculo;

    @Element(name = "pICMS")
    private String percentualAliquota;

    @Element(name = "vICMS")
    private String valorTributo;

    @Element(name = "vCred", required = false)
    private String valorCredito;

    public NFNotaInfoImpostoTributacaoICMS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public void setSituacaoTributaria(final NFNotaInfoImpostoTributacaoICMS situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public String getPercentualReducaoBaseCalculo() {
        return this.percentualReducaoBaseCalculo;
    }

    public void setPercentualReducaoBaseCalculo(final String percentualReducaoBaseCalculo) {
        this.percentualReducaoBaseCalculo = percentualReducaoBaseCalculo;
    }

    public String getValorBaseCalculo() {
        return this.valorBaseCalculo;
    }

    public void setValorBaseCalculo(final String valorBaseCalculo) {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    public String getPercentualAliquota() {
        return this.percentualAliquota;
    }

    public void setPercentualAliquota(final String percentualAliquota) {
        this.percentualAliquota = percentualAliquota;
    }

    public String getValorTributo() {
        return this.valorTributo;
    }

    public void setValorTributo(final String valorTributo) {
        this.valorTributo = valorTributo;
    }

    public String getValorCredito() {
        return this.valorCredito;
    }

    public void setValorCredito(final String valorCredito) {
        this.valorCredito = valorCredito;
    }

}
