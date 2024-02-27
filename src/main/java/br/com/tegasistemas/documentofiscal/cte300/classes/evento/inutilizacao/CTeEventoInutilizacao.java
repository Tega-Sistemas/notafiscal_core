package br.com.tegasistemas.documentofiscal.cte300.classes.evento.inutilizacao;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "inutCTe")
public class CTeEventoInutilizacao extends DFBase {
    private static final long serialVersionUID = -8363617761063438288L;

    @Attribute(name = "Id")
    private String id;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "xServ", required = false)
    private String xServ;

    @Element(name = "cUF", required = false)
    private DFUnidadeFederativa orgao;

    @Element(name = "ano", required = false)
    private String ano;

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;

    //modelo cte
    @Element(name = "mod", required = false)
    private String mod;

    @Element(name = "serie", required = false)
    private String serie;

    @Element(name = "nCTIni")
    private String cteInit;

    @Element(name = "nCTFin")
    private String cteFinal;

    @Element(name = "xJust")
    private String xJust;

    @Element(name = "verEvento", required = false)
    private String versaoEvento;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public CTeInfoEventoInutilizacao getInfoEvento() {
//        return this.infoEvento;
//    }
//
//    public void setInfoEvento(final CTeInfoEventoInutilizacao infoEvento) {
//        this.infoEvento = infoEvento;
//    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public DFAmbiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getxServ() {
        return xServ;
    }

    public void setxServ(String xServ) {
        this.xServ = xServ;
    }

    public DFUnidadeFederativa getOrgao() {
        return orgao;
    }

    public void setOrgao(DFUnidadeFederativa orgao) {
        this.orgao = orgao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCteInit() {
        return cteInit;
    }

    public void setCteInit(String cteInit) {
        this.cteInit = cteInit;
    }

    public String getCteFinal() {
        return cteFinal;
    }

    public void setCteFinal(String cteFinal) {
        this.cteFinal = cteFinal;
    }

    public String getxJust() {
        return xJust;
    }

    public void setxJust(String xJust) {
        this.xJust = xJust;
    }

    public String getVersaoEvento() {
        return versaoEvento;
    }

    public void setVersaoEvento(String versaoEvento) {
        this.versaoEvento = versaoEvento;
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }
}