package br.com.tegasistemas.documentofiscal.cte400.classes.os;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "infModal")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeOSInfoCTeNormalInfoModal extends DFBase {
    private static final long serialVersionUID = 8987158163135069115L;

    @Element(name = "rodoOS", required = false)
    private CTeOSInfoCTeNormalInfoModalRodoviario rodoviario;

    @Attribute(name = "versaoModal")
    private String versao;

    public CTeOSInfoCTeNormalInfoModalRodoviario getRodoviario() {
        return this.rodoviario;
    }

    public void setRodoviario(final CTeOSInfoCTeNormalInfoModalRodoviario rodoviario) {
        this.rodoviario = rodoviario;
    }

    public String getVersao() {
        return this.versao;
    }

    /**
     * Versão do leiaute específico para o Modal
     */
    public void setVersao(final String versaoModal) {
        this.versao = versaoModal;
    }
}
