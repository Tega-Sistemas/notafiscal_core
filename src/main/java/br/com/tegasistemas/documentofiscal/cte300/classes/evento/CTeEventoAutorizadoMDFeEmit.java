package br.com.tegasistemas.documentofiscal.cte300.classes.evento;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "emit")
public class CTeEventoAutorizadoMDFeEmit {

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String CPF;

    @Element(name = "IE", required = false)
    private String IE;

    @Element(name = "xNome")
    private String xNome;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

}
