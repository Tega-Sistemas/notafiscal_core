package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.IntegerValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFNotaInfoItemImpostoISSQN extends DFBase {
    private static final long serialVersionUID = 5303195667632685433L;
    
    @Element(name = "vBC")
    private String valorBaseCalculo;
    
    @Element(name = "vAliq")
    private String valorAliquota;
    
    @Element(name = "vISSQN")
    private String valor;
    
    @Element(name = "cMunFG")
    private Integer codigoMunicipio;
    
    @Element(name = "cListServ")
    private String itemListaServicos;

    @Element(name = "vDeducao", required = false)
    private String valorDeducao;

    @Element(name = "vOutro", required = false)
    private String valorOutro;

    @Element(name = "vDescIncond", required = false)
    private String valorDescontoIncondicionado;

    @Element(name = "vDescCond", required = false)
    private String valorDescontoCondicionado;

    @Element(name = "vISSRet", required = false)
    private String valorRetencaoISS;
    
    @Element(name = "indISS")
    private NFNotaInfoItemIndicadorExigibilidadeISS indicadorExigibilidadeISS;

    @Element(name = "cServico", required = false)
    private String codigoServico;

    @Element(name = "cMun", required = false)
    private String codigoMunicipioIncidenciaImposto;

    @Element(name = "cPais", required = false)
    private String codigoPais;

    @Element(name = "nProcesso", required = false)
    private String numeroProcesso;
    
    @Element(name = "indIncentivo")
    private NFNotaInfoItemIndicadorIncentivoFiscal indicadorIncentivoFiscal;

    public void setValorBaseCalculo(final BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = BigDecimalValidador.tamanho15Com2CasasDecimais(valorBaseCalculo, "Valor BC ISSQN Item");
    }

    public void setValorAliquota(final BigDecimal valorAliquota) {
        this.valorAliquota = BigDecimalValidador.tamanho7ComAte4CasasDecimais(valorAliquota, "Valor Aliquota ISSQN Item");
    }

    public void setValor(final BigDecimal valor) {
        this.valor = BigDecimalValidador.tamanho15Com2CasasDecimais(valor, "Valor ISSQN Item");
    }

    public void setCodigoMunicipio(final Integer codigoMunicipio) {
        IntegerValidador.exatamente7(codigoMunicipio, "Codigo Municipio ISSQN Item");
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setItemListaServicos(final String itemListaServicos) {
        StringValidador.itemListaServico(itemListaServicos);
        this.itemListaServicos = itemListaServicos;
    }

    public void setCodigoMunicipioIncidenciaImposto(final String codigoMunicipioIncidenciaImposto) {
        StringValidador.exatamente7N(codigoMunicipioIncidenciaImposto, "Codigo Municipio Incidencia Imposto ISSQN Item");
        this.codigoMunicipioIncidenciaImposto = codigoMunicipioIncidenciaImposto;
    }

    public void setCodigoPais(final String codigoPais) {
        StringValidador.exatamente4N(codigoPais, "Codigo Pais ISSQN Item");
        this.codigoPais = codigoPais;
    }

    public void setCodigoServico(final String codigoServico) {
        StringValidador.tamanho20(codigoServico, "Codigo Servico ISSQN Item");
        this.codigoServico = codigoServico;
    }

    public void setIndicadorExigibilidadeISS(final NFNotaInfoItemIndicadorExigibilidadeISS indicadorExigibilidadeISS) {
        this.indicadorExigibilidadeISS = indicadorExigibilidadeISS;
    }

    public void setIndicadorIncentivoFiscal(final NFNotaInfoItemIndicadorIncentivoFiscal indicadorIncentivoFiscal) {
        this.indicadorIncentivoFiscal = indicadorIncentivoFiscal;
    }

    public void setNumeroProcesso(final String numeroProcesso) {
        StringValidador.tamanho30(numeroProcesso, "Numero Processo ISSQN Item");
        this.numeroProcesso = numeroProcesso;
    }

    public void setValorDeducao(final BigDecimal valorDeducao) {
        this.valorDeducao = BigDecimalValidador.tamanho15Com2CasasDecimais(valorDeducao, "Valor Deducao ISSQN Item");
    }

    public void setValorDescontoCondicionado(final BigDecimal valorDescontoCondicionado) {
        this.valorDescontoCondicionado = BigDecimalValidador.tamanho15Com2CasasDecimais(valorDescontoCondicionado, "Valor Desconto Condicionado ISSQN Item");
    }

    public void setValorDescontoIncondicionado(final BigDecimal valorDescontoIncondicionado) {
        this.valorDescontoIncondicionado = BigDecimalValidador.tamanho15Com2CasasDecimais(valorDescontoIncondicionado, "Valor Desconto Incondicionado ISSQN Item");
    }

    public void setValorOutro(final BigDecimal valorOutro) {
        this.valorOutro = BigDecimalValidador.tamanho15Com2CasasDecimais(valorOutro, "Valor Outro ISSQN Item");
    }

    public void setValorRetencaoISS(final BigDecimal valorRetencaoISS) {
        this.valorRetencaoISS = BigDecimalValidador.tamanho15Com2CasasDecimais(valorRetencaoISS, "Valor Retencao ISS ISSQN Item");
    }

    public String getValorBaseCalculo() {
        return this.valorBaseCalculo;
    }

    public String getValorAliquota() {
        return this.valorAliquota;
    }

    public String getValor() {
        return this.valor;
    }

    public Integer getCodigoMunicipio() {
        return this.codigoMunicipio;
    }

    public String getItemListaServicos() {
        return this.itemListaServicos;
    }

    public String getValorDeducao() {
        return this.valorDeducao;
    }

    public String getValorOutro() {
        return this.valorOutro;
    }

    public String getValorDescontoIncondicionado() {
        return this.valorDescontoIncondicionado;
    }

    public String getValorDescontoCondicionado() {
        return this.valorDescontoCondicionado;
    }

    public String getValorRetencaoISS() {
        return this.valorRetencaoISS;
    }

    public NFNotaInfoItemIndicadorExigibilidadeISS getIndicadorExigibilidadeISS() {
        return this.indicadorExigibilidadeISS;
    }

    public String getCodigoServico() {
        return this.codigoServico;
    }

    public String getCodigoMunicipioIncidenciaImposto() {
        return this.codigoMunicipioIncidenciaImposto;
    }

    public String getCodigoPais() {
        return this.codigoPais;
    }

    public String getNumeroProcesso() {
        return this.numeroProcesso;
    }

    public NFNotaInfoItemIndicadorIncentivoFiscal getIndicadorIncentivoFiscal() {
        return this.indicadorIncentivoFiscal;
    }
}