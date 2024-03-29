package br.com.tegasistemas.documentofiscal.cte200.classes.cte;

import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;

public class CTInfoCteComplementado extends DFBase {
    private static final long serialVersionUID = 594149684514913854L;

    @Element(name = "chave")
    private String chaveAcessoCteComplementado;

    public String getChaveAcessoCteComplementado() {
        return this.chaveAcessoCteComplementado;
    }

    public void setChaveAcessoCteComplementado(final String chaveAcessoCteComplementado) {
        this.chaveAcessoCteComplementado = chaveAcessoCteComplementado;
    }

}
