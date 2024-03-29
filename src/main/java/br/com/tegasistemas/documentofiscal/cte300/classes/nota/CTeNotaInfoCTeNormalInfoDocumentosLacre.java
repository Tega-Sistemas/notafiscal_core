package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Lacres das Unidades de Carga
 */

@Root(name = "lacUnidCarga")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoDocumentosLacre extends DFBase {
    private static final long serialVersionUID = 4668372748009297086L;
    
    @Element(name = "nLacre")
    private String numeroLacre;

    public CTeNotaInfoCTeNormalInfoDocumentosLacre() {
        this.numeroLacre = null;
    }

    public String getNumeroLacre() {
        return this.numeroLacre;
    }

    /**
     * Número do lacre
     */
    public void setNumeroLacre(final String numeroLacre) {
        StringValidador.tamanho20(numeroLacre, "Número do lacre");
        this.numeroLacre = numeroLacre;
    }
}
