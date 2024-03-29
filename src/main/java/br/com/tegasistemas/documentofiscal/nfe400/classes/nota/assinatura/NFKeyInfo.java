package br.com.tegasistemas.documentofiscal.nfe400.classes.nota.assinatura;

import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;

public class NFKeyInfo extends DFBase {
    private static final long serialVersionUID = 7949034334524924221L;

    @Element(name = "X509Data", required = false)
    private NFX509Data data;

    public NFX509Data getData() {
        return this.data;
    }

    public void setData(final NFX509Data data) {
        this.data = data;
    }
}