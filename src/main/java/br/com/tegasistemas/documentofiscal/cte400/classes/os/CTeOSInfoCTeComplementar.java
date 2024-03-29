package br.com.tegasistemas.documentofiscal.cte400.classes.os;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "infCteComp")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeOSInfoCTeComplementar extends DFBase {
    private static final long serialVersionUID = 8633547499681692469L;

    @Element(name = "chCTe")
    private String chave;

    public CTeOSInfoCTeComplementar() {
        this.chave = null;
    }

    public String getChave() {
        return this.chave;
    }

    /**
     * Chave do CT-e complementado
     */
    public void setChave(final String chave) {
        DFStringValidador.exatamente44N(chave, "Chave do CT-e complementado");
        this.chave = chave;
    }
}
