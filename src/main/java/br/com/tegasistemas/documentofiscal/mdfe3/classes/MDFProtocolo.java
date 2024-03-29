package br.com.tegasistemas.documentofiscal.mdfe3.classes;

import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @Author Eldevan Nery Junior on 26/05/17.
 */
@Root(name = "protMDFe")
public class MDFProtocolo extends DFBase {
    private static final long serialVersionUID = 7056704602846442462L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "infProt")
    private MDFProtocoloInfo protocoloInfo;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public void setProtocoloInfo(final MDFProtocoloInfo protocoloInfo) {
        this.protocoloInfo = protocoloInfo;
    }

    public MDFProtocoloInfo getProtocoloInfo() {
        return this.protocoloInfo;
    }

    public String getVersao() {
        return this.versao;
    }
}