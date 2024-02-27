package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by Pedro Zampiroli 11-12-2020. Informações produto predominante.
 */

@Root(name = "infLocalDescarrega")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFInfoProdutoPredInfoDescarrega extends DFBase {
    private static final long serialVersionUID = 4018991399177455416L;

    @Element(name = "CEP")
    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
