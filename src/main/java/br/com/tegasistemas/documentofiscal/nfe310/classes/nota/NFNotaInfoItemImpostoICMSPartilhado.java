package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoImpostoTributacaoICMS;
import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoItemModalidadeBCICMS;
import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoItemModalidadeBCICMSST;
import br.com.tegasistemas.documentofiscal.nfe310.classes.NFOrigem;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFNotaInfoItemImpostoICMSPartilhado extends DFBase {
    private static final long serialVersionUID = 3053815337863231705L;
    
    @Element(name = "orig")
    private NFOrigem origem;
    
    @Element(name = "CST")
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;
    
    @Element(name = "modBC")
    private NFNotaInfoItemModalidadeBCICMS modalidadeBCICMS;
    
    @Element(name = "vBC")
    private String valorBCICMS;

    @Element(name = "pRedBC", required = false)
    private String percentualReducaoBC;
    
    @Element(name = "pICMS")
    private String percentualAliquotaImposto;
    
    @Element(name = "vICMS")
    private String valorICMS;
    
    @Element(name = "modBCST")
    private NFNotaInfoItemModalidadeBCICMSST modalidadeBCICMSST;

    @Element(name = "pMVAST", required = false)
    private String percentualMargemValorAdicionadoICMSST;

    @Element(name = "pRedBCST", required = false)
    private String percentualReducaoBCICMSST;
    
    @Element(name = "vBCST")
    private String valorBCICMSST;
    
    @Element(name = "pICMSST")
    private String percentualAliquotaImpostoICMSST;
    
    @Element(name = "vICMSST")
    private String valorICMSST;
    
    @Element(name = "pBCOp")
    private String percentualBCOperacaoPropria;
    
    @Element(name = "UFST")
    private String ufICMSST;

    public void setOrigem(final NFOrigem origem) {
        this.origem = origem;
    }

    public void setSituacaoTributaria(final NFNotaInfoImpostoTributacaoICMS situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public void setModalidadeBCICMS(final NFNotaInfoItemModalidadeBCICMS modalidadeBCICMS) {
        this.modalidadeBCICMS = modalidadeBCICMS;
    }

    public void setValorBCICMS(final BigDecimal valorBCICMS) {
        this.valorBCICMS = BigDecimalValidador.tamanho15Com2CasasDecimais(valorBCICMS, "Valor BC ICMS Partilhado");
    }

    public void setPercentualReducaoBC(final BigDecimal percentualReducaoBC) {
        this.percentualReducaoBC = BigDecimalValidador.tamanho5Com2CasasDecimais(percentualReducaoBC, "Percentual Reducao BC ICMS Partilhado");
    }

    public void setPercentualAliquotaImposto(final BigDecimal percentualAliquotaImposto) {
        this.percentualAliquotaImposto = BigDecimalValidador.tamanho7ComAte4CasasDecimais(percentualAliquotaImposto, "Percentual Aliquota Imposto ICMS Partilhado");
    }

    public void setValorICMS(final BigDecimal valorICMS) {
        this.valorICMS = BigDecimalValidador.tamanho15Com2CasasDecimais(valorICMS, "Valor ICMS Partilhado");
    }

    public void setModalidadeBCICMSST(final NFNotaInfoItemModalidadeBCICMSST modalidadeBCICMSST) {
        this.modalidadeBCICMSST = modalidadeBCICMSST;
    }

    public void setPercentualMargemValorAdicionadoICMSST(final BigDecimal percentualMargemValorAdicionadoICMSST) {
        this.percentualMargemValorAdicionadoICMSST = BigDecimalValidador.tamanho7ComAte4CasasDecimais(percentualMargemValorAdicionadoICMSST, "Percentual Margem Valor Adicionado ICMS ST Partilhado");
    }

    public void setPercentualReducaoBCICMSST(final BigDecimal percentualReducaoBCICMSST) {
        this.percentualReducaoBCICMSST = BigDecimalValidador.tamanho7ComAte4CasasDecimais(percentualReducaoBCICMSST, "Percentual Reducao BC ICMS ST Partilhado");
    }

    public void setValorBCICMSST(final BigDecimal valorBCICMSST) {
        this.valorBCICMSST = BigDecimalValidador.tamanho15Com2CasasDecimais(valorBCICMSST, "Valor BC ICMS ST Partilhado");
    }

    public void setPercentualAliquotaImpostoICMSST(final BigDecimal percentualAliquotaImpostoICMSST) {
        this.percentualAliquotaImpostoICMSST = BigDecimalValidador.tamanho7ComAte4CasasDecimais(percentualAliquotaImpostoICMSST, "Percentual Aliquota Imposto ICMS ST Partilhado");
    }

    public void setValorICMSST(final BigDecimal valorICMSST) {
        this.valorICMSST = BigDecimalValidador.tamanho15Com2CasasDecimais(valorICMSST, "Valor ICMS ST Partilhado");
    }

    public void setPercentualBCOperacaoPropria(final BigDecimal percentual) {
        this.percentualBCOperacaoPropria = BigDecimalValidador.tamanho7ComAte4CasasDecimais(percentual, "Percentual Partilhado");
    }

    public void setUfICMSST(final DFUnidadeFederativa ufICMSST) {
        this.ufICMSST = ufICMSST.getCodigo();
    }

    public NFOrigem getOrigem() {
        return this.origem;
    }

    public NFNotaInfoImpostoTributacaoICMS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public NFNotaInfoItemModalidadeBCICMS getModalidadeBCICMS() {
        return this.modalidadeBCICMS;
    }

    public String getValorBCICMS() {
        return this.valorBCICMS;
    }

    public String getPercentualReducaoBC() {
        return this.percentualReducaoBC;
    }

    public String getPercentualAliquotaImposto() {
        return this.percentualAliquotaImposto;
    }

    public String getValorICMS() {
        return this.valorICMS;
    }

    public NFNotaInfoItemModalidadeBCICMSST getModalidadeBCICMSST() {
        return this.modalidadeBCICMSST;
    }

    public String getPercentualMargemValorAdicionadoICMSST() {
        return this.percentualMargemValorAdicionadoICMSST;
    }

    public String getPercentualReducaoBCICMSST() {
        return this.percentualReducaoBCICMSST;
    }

    public String getValorBCICMSST() {
        return this.valorBCICMSST;
    }

    public String getPercentualAliquotaImpostoICMSST() {
        return this.percentualAliquotaImpostoICMSST;
    }

    public String getValorICMSST() {
        return this.valorICMSST;
    }

    public String getPercentualBCOperacaoPropria() {
        return this.percentualBCOperacaoPropria;
    }

    public String getUfICMSST() {
        return this.ufICMSST;
    }
}