package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "infNFeSupl")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFNotaInfoSuplementar extends DFBase {
    private static final long serialVersionUID = -7212144193264841151L;
    
    @Element(data = true, name = "qrCode")
    private String qrCode;

    public void setQrCode(final String qrCode) {
        StringValidador.tamanho100a600(qrCode, "QR Code");
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return this.qrCode;
    }
}