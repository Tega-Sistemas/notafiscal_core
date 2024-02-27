package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Informações de Seguro do Multimodal
 */

@Root(name = "seg")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoCTeNormalInfoModalMultiModalSeguro extends DFBase {
    private static final long serialVersionUID = 494073683560762024L;

    @Element(name = "infSeg")
    private CTeNotaInfoCTeNormalInfoModalMultiModalSeguroInfo info;
    
    @Element(name = "nApol")
    private String apolice;
    
    @Element(name = "nAver")
    private String averbacao;

    public CTeNotaInfoCTeNormalInfoModalMultiModalSeguroInfo getInfo() {
        return this.info;
    }

    /**
     * Informações da seguradora
     */
    public void setInfo(final CTeNotaInfoCTeNormalInfoModalMultiModalSeguroInfo info) {
        this.info = info;
    }

    public String getApolice() {
        return this.apolice;
    }

    /**
     * Número da Apólice<br>
     * Obrigatório pela lei 11.442/07 (RCTRC)
     */
    public void setApolice(final String apolice) {
        DFStringValidador.tamanho20(apolice, "Número da Apólice");
        this.apolice = apolice;
    }

    public String getAverbacao() {
        return this.averbacao;
    }

    /**
     * Número da Averbação<br>
     * Não é obrigatório, pois muitas averbações ocorrem aapós a emissão do CT, mensalmente, por exemplo.
     */
    public void setAverbacao(final String averbacao) {
        DFStringValidador.tamanho20(averbacao, "Número da Averbação");
        this.averbacao = averbacao;
    }
}
