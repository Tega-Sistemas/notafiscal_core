package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Grupo de informações das balsas
 */

@Root(name = "balsa")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoModalAquaviarioBalsa extends DFBase {
    private static final long serialVersionUID = -1451896642980692340L;
    
    @Element(name = "xBalsa")
    private String descricao;

    public CTeNotaInfoCTeNormalInfoModalAquaviarioBalsa() {
        this.descricao = null;
    }

    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Identificador da Balsa
     */
    public void setDescricao(final String descricao) {
        StringValidador.tamanho60(descricao, "Identificador da Balsa");
        this.descricao = descricao;
    }
}
