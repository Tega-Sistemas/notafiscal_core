package br.com.tegasistemas.documentofiscal.cte400.classes.os;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "infCTeSupl")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeOSInfoSuplementares extends DFBase {
    private static final long serialVersionUID = 5643192963806193737L;

    @Element(name = "qrCodCTe")
    private String qrCode;

    public String getQrCode() {
        return qrCode;
    }

    public CTeOSInfoSuplementares setQrCode(String qrCode) {
        this.qrCode = qrCode;
        return this;
    }
}
