package br.com.tegasistemas.documentofiscal.nfe310.classes.evento.inutilizacao;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.validadores.IntegerValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "infInut")
public class NFEventoInutilizacaoDados extends DFBase {
    private static final long serialVersionUID = -5481681546706524562L;
    
    @Attribute(name = "Id")
    private String identificador;
    
    @Element(name = "tpAmb")
    private DFAmbiente ambiente;
    
    @Element(name = "xServ")
    private String nomeServico;
    
    @Element(name = "cUF")
    private DFUnidadeFederativa uf;
    
    @Element(name = "ano")
    private Integer ano;
    
    @Element(name = "CNPJ")
    private String cnpj;
    
    @Element(name = "mod")
    private String modeloDocumentoFiscal;
    
    @Element(name = "serie")
    private String serie;
    
    @Element(name = "nNFIni")
    private String numeroNFInicial;
    
    @Element(name = "nNFFin")
    private String numeroNFFinal;
    
    @Element(name = "xJust")
    private String justificativa;

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(final String identificador) {
        StringValidador.identificador(identificador);
        this.identificador = identificador;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getNomeServico() {
        return this.nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        if (!nomeServico.equals("INUTILIZAR")) {
            throw new IllegalStateException("Nome de servico invalido");
        }
        this.nomeServico = nomeServico;
    }

    public DFUnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(final Integer ano) {
        IntegerValidador.exatamente2(ano, "Ano");
        this.ano = ano;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(final String cnpj) {
        StringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getModeloDocumentoFiscal() {
        return this.modeloDocumentoFiscal;
    }

    public void setModeloDocumentoFiscal(final String modeloDocumentoFiscal) {
        StringValidador.modeloDocumentoFiscal(modeloDocumentoFiscal);
        this.modeloDocumentoFiscal = modeloDocumentoFiscal;
    }

    public String getSerie() {
        return this.serie;
    }

    public void setSerie(final String serie) {
        StringValidador.tamanho3N(serie, "Serie");
        this.serie = serie;
    }

    public String getNumeroNFInicial() {
        return this.numeroNFInicial;
    }

    public void setNumeroNFInicial(final String numeroNFInicial) {
        StringValidador.tamanho9N(numeroNFInicial, "Numero NF Inicial");
        this.numeroNFInicial = numeroNFInicial;
    }

    public String getNumeroNFFinal() {
        return this.numeroNFFinal;
    }

    public void setNumeroNFFinal(final String numeroNFFinal) {
        StringValidador.tamanho9N(numeroNFFinal, "Numero NF Final");
        this.numeroNFFinal = numeroNFFinal;
    }

    public String getJustificativa() {
        return this.justificativa;
    }

    public void setJustificativa(final String justificativa) {
        StringValidador.tamanho15a255(justificativa, "Justificativa");
        this.justificativa = justificativa;
    }
}
