package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by Pedro Zampiroli 11-12-2020. Informações produto predominante.
 */

@Root(name = "infLotacao")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFInfoProdutoPredInfo extends DFBase {
    private static final long serialVersionUID = 4018991399177455416L;

    @Element(name = "infLocalCarrega")
    private MDFInfoProdutoPredInfoCarrega infoCarrega;

    @Element(name = "infLocalDescarrega")
    private MDFInfoProdutoPredInfoDescarrega infoDescarrega;

    public MDFInfoProdutoPredInfoCarrega getInfoCarrega() {
        return infoCarrega;
    }

    public void setInfoCarrega(MDFInfoProdutoPredInfoCarrega infoCarrega) {
        this.infoCarrega = infoCarrega;
    }

    public MDFInfoProdutoPredInfoDescarrega getInfoDescarrega() {
        return infoDescarrega;
    }

    public void setInfoDescarrega(MDFInfoProdutoPredInfoDescarrega infoDescarrega) {
        this.infoDescarrega = infoDescarrega;
    }
}
