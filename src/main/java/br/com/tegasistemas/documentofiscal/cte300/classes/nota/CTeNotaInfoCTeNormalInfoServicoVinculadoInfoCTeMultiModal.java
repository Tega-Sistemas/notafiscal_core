package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info informações do CT-e multimodal vinculado
 */

@Root(name = "infCTeMultimodal")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoServicoVinculadoInfoCTeMultiModal extends DFBase {
    private static final long serialVersionUID = 4687995844687441962L;
    
    @Element(name = "chCTeMultimodal")
    private String chaveCTeMultiModal;

    public CTeNotaInfoCTeNormalInfoServicoVinculadoInfoCTeMultiModal() {
        this.chaveCTeMultiModal = null;
    }

    public String getChaveCTeMultiModal() {
        return this.chaveCTeMultiModal;
    }

    /**
     * Chave de acesso do CT-e Multimodal
     */
    public void setChaveCTeMultiModal(final String chaveCTeMultiModal) {
        StringValidador.exatamente44N(chaveCTeMultiModal, "Chave de acesso do CT-e Multimodal");
        this.chaveCTeMultiModal = chaveCTeMultiModal;
    }
}
