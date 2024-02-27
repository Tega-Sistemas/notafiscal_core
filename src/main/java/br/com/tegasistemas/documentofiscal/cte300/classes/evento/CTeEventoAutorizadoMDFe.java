package br.com.tegasistemas.documentofiscal.cte300.classes.evento;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "evCTeAutorizadoMDFe")
public class CTeEventoAutorizadoMDFe {

    private static final long serialVersionUID = -6990304145768185274L;

    @Element(name = "descEvento")
    private String descricaoEvento = "MDF-e Autorizado";

    @Element(name = "MDFe")
    private CTeEventoAutorizadoMDFeDados mdfe;

    @Element(name = "emit")
    private CTeEventoAutorizadoMDFeEmit emit;

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public CTeEventoAutorizadoMDFeDados getMdfe() {
        return mdfe;
    }

    public void setMdfe(CTeEventoAutorizadoMDFeDados mdfe) {
        this.mdfe = mdfe;
    }

    public CTeEventoAutorizadoMDFeEmit getEmit() {
        return emit;
    }

    public void setEmit(CTeEventoAutorizadoMDFeEmit emit) {
        this.emit = emit;
    }

}
