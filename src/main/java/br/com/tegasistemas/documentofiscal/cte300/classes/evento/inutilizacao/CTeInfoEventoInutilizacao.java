package br.com.tegasistemas.documentofiscal.cte300.classes.evento.inutilizacao;

import br.com.tegasistemas.documentofiscal.DFBase;

public class CTeInfoEventoInutilizacao extends DFBase {
//    private static final long serialVersionUID = -9071000192790378973L;
//
//    @Attribute(name = "Id", required = false)
//    private String id;
//
//    @Element(name = "tpAmb")
//    private DFAmbiente ambiente;
//
//    @Element(name = "xServ", required = false)
//    private String xServ;
//
//    @Element(name = "cUF", required = false)
//    private DFUnidadeFederativa orgao;
//
//    @Element(name = "ano", required = false)
//    private String ano;
//
//    @Element(name = "CNPJ", required = false)
//    private String cnpj;
//
//    @Element(name = "CPF", required = false)
//    private String cpf;
//
//    //modelo cte
//    @Element(name = "mod", required = false)
//    private String mod;
//
//    @Element(name = "serie", required = false)
//    private String serie;
//
//    @Element(name = "nCTIni")
//    private Integer cteInit;
//
//    @Element(name = "nCTFin")
//    private Integer cteFinal;
//
//    @Element(name = "xJust")
//    private String xJust;
//
//    @Element(name = "verEvento", required = false)
//    private String versaoEvento;
//
//    public String getxServ() {
//        return xServ;
//    }
//
//    public void setxServ(String xServ) {
//        this.xServ = xServ;
//    }
//
//    public String getAno() {
//        return ano;
//    }
//
//    public void setAno(String ano) {
//        this.ano = ano;
//    }
//
//    public Integer getCteInit() {
//        return cteInit;
//    }
//
//    public void setCteInit(Integer cteInit) {
//        this.cteInit = cteInit;
//    }
//
//    public Integer getCteFinal() {
//        return cteFinal;
//    }
//
//    public void setCteFinal(Integer cteFinal) {
//        this.cteFinal = cteFinal;
//    }
//
//    public String getxJust() {
//        return xJust;
//    }
//
//    public void setxJust(String xJust) {
//        this.xJust = xJust;
//    }
//
//    public void setOrgao(final DFUnidadeFederativa orgao) {
//        this.orgao = orgao;
//    }
//
//    public void setVersaoEvento(final BigDecimal versaoEvento) {
//        this.versaoEvento = BigDecimalValidador.tamanho5Com2CasasDecimais(versaoEvento, "Versao do Evento");
//    }
//
//    public String getId() {
//        return this.id;
//    }
//
//    public void setId(final String id) {
//        StringValidador.exatamente54(id, "Info Evento Cancelamento ID");
//        this.id = id;
//    }
//
//    public DFAmbiente getAmbiente() {
//        return this.ambiente;
//    }
//
//    public void setAmbiente(final DFAmbiente ambiente) {
//        this.ambiente = ambiente;
//    }
//
//    public String getCnpj() {
//        return this.cnpj;
//    }
//
//    public void setCnpj(final String cnpj) {
//        if (this.cpf != null) {
//            throw new IllegalStateException("CPF ja foi setado");
//        }
//        StringValidador.cnpj(cnpj);
//        this.cnpj = cnpj;
//    }
//
//    public String getCpf() {
//        return this.cpf;
//    }
//
//    public void setCpf(final String cpf) {
//        if (this.cnpj != null) {
//            throw new IllegalStateException("CNPJ ja foi setado");
//        }
//        StringValidador.cpf(cpf);
//        this.cpf = cpf;
//    }
//
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public String getMod() {
//        return mod;
//    }
//
//    public void setMod(String mod) {
//        this.mod = mod;
//    }
//
//    public String getSerie() {
//        return serie;
//    }
//
//    public void setSerie(String serie) {
//        this.serie = serie;
//    }
//
//    public String getVersaoEvento() {
//        return this.versaoEvento;
//    }
//
//    public DFUnidadeFederativa getOrgao() {
//        return this.orgao;
//    }
//
//    public void setVersaoEvento(final String versaoEvento) {
//        this.versaoEvento = versaoEvento;
//    }
}