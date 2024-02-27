package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by Pedro Zampiroli 11-12-2020. Informações produto predominante.
 */
@Root(name = "prodPred")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFInfoProdutoPred extends DFBase {

    private static final long serialVersionUID = 665561268515018765L;

    @Element(name = "tpCarga")
    private String tpCarga;

    @Element(name = "xProd", required = false)
    private String xprod;

    @Element(name = "infLotacao", required = false)
    private MDFInfoProdutoPredInfo apolice;

    public String getTpCarga() {
        return tpCarga;
    }

    public void setTpCarga(String tpCarga) {
        this.tpCarga = tpCarga;
    }

    public String getXprod() {
        return xprod;
    }

    public void setXprod(String xprod) {
        this.xprod = xprod;
    }

    public MDFInfoProdutoPredInfo getApolice() {
        return apolice;
    }

    public void setApolice(MDFInfoProdutoPredInfo apolice) {
        this.apolice = apolice;
    }
}
