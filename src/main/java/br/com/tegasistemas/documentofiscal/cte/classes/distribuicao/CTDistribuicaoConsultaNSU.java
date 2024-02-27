package br.com.tegasistemas.documentofiscal.cte.classes.distribuicao;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

public class CTDistribuicaoConsultaNSU extends DFBase {

    private static final long serialVersionUID = -582191692175285331L;

    @Element(name = "NSU")
    private String nsu;

    public String getNsu() {
        return this.nsu;
    }

    public CTDistribuicaoConsultaNSU setNsu(final String nsu) {
        this.nsu = nsu;
        return this;
    }

}
