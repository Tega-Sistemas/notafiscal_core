package br.com.tegasistemas.documentofiscal.cte300.classes.evento;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "MDFe")
public class CTeEventoAutorizadoMDFeDados {

    @Element(name = "chMDFe")
    private String chMDFe;

    @Element(name = "modal")
    private String modal;

    @Element(name = "dhEmi")
    private String dhEmi;

    @Element(name = "nProt")
    private String nProt;

    @Element(name = "dhRecbto")
    private String dhRecbto;

    public String getChMDFe() {
        return chMDFe;
    }

    public void setChMDFe(String chMDFe) {
        this.chMDFe = chMDFe;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(String dhEmi) {
        this.dhEmi = dhEmi;
    }

    public String getnProt() {
        return nProt;
    }

    public void setnProt(String nProt) {
        this.nProt = nProt;
    }

    public String getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(String dhRecbto) {
        this.dhRecbto = dhRecbto;
    }
}
