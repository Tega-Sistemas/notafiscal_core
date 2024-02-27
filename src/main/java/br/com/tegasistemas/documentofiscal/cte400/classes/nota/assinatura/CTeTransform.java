package br.com.tegasistemas.documentofiscal.cte400.classes.nota.assinatura;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;

public class CTeTransform extends DFBase {
    private static final long serialVersionUID = 4404647911665495803L;

    @Attribute(name = "Algorithm", required = false)
    private String algorithm;

    public void setAlgorithm(final String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }
}