package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Informações da seguradora
 */

@Root(name = "infSeg")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoModalMultiModalSeguroInfo extends DFBase {
    private static final long serialVersionUID = 1853696818718851264L;
    
    @Element(name = "xSeg")
    private String seguradora;
    
    @Element(name = "CNPJ")
    private String cnpj;

    public CTeNotaInfoCTeNormalInfoModalMultiModalSeguroInfo() {
        this.seguradora = null;
        this.cnpj = null;
    }

    public String getSeguradora() {
        return this.seguradora;
    }

    /**
     * Nome da Seguradora
     */
    public void setSeguradora(final String seguradora) {
        StringValidador.tamanho30(seguradora, "Nome da Seguradora");
        this.seguradora = seguradora;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    /**
     * Número do CNPJ da seguradora<br>
     * Obrigatório apenas se responsável pelo seguro for (2) responsável pela contratação do transporte - pessoa jurídica
     */
    public void setCnpj(final String cnpj) {
        StringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }
}
