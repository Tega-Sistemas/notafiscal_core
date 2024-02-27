package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Grupo de informações dos lacres dos cointainers da qtde da carga
 */

@Root(name = "lacre")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoCTeNormalInfoModalAquaviarioConteinerLacre extends DFBase {
    private static final long serialVersionUID = 626415727361958489L;

    @Element(name = "nLacre")
    private String numeroLacre;

    public String getNumeroLacre() {
        return this.numeroLacre;
    }

    /**
     * Lacre
     */
    public void setNumeroLacre(final String numeroLacre) {
        DFStringValidador.tamanho20(numeroLacre, "Lacre");
        this.numeroLacre = numeroLacre;
    }
}
