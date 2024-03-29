package br.com.tegasistemas.documentofiscal.nfe310.classes.statusservico.consulta;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "consStatServ")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFStatusServicoConsulta extends DFBase {
    private static final long serialVersionUID = 1406428397598529093L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "tpAmb")
    private DFAmbiente ambiente;
    
    @Element(name = "cUF")
    private DFUnidadeFederativa uf;
    
    @Element(name = "xServ")
    private String servico;

    public NFStatusServicoConsulta() {
        this.versao = null;
        this.ambiente = null;
        this.uf = null;
        this.servico = null;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public DFUnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getServico() {
        return this.servico;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }
}