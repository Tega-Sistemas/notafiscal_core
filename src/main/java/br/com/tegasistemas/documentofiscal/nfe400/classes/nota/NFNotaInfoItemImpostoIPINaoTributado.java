package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe400.classes.NFNotaInfoSituacaoTributariaIPI;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemImpostoIPINaoTributado extends DFBase {
    private static final long serialVersionUID = 6499358432906573487L;
    
    @Element(name = "CST")
    private NFNotaInfoSituacaoTributariaIPI situacaoTributaria;

    public NFNotaInfoItemImpostoIPINaoTributado() {
        this.situacaoTributaria = null;
    }

    public void setSituacaoTributaria(final NFNotaInfoSituacaoTributariaIPI situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public NFNotaInfoSituacaoTributariaIPI getSituacaoTributaria() {
        return this.situacaoTributaria;
    }
}