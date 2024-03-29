package br.com.tegasistemas.documentofiscal.cte200.classes.cte;

import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoImpostoTributacaoICMS;
import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;

public class CTInfoImpostoICMS45 extends DFBase {
    private static final long serialVersionUID = 5790409052194264441L;

    @Element(name = "CST")
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;

    public NFNotaInfoImpostoTributacaoICMS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public void setSituacaoTributaria(final NFNotaInfoImpostoTributacaoICMS situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

}
