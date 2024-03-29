package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * @author Caio
 * @info Informações das NFe
 */

@Root(name = "infNFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoModalAquaviarioConteinerInfoDocumentosNFe extends DFBase {
    private static final long serialVersionUID = -2556091825989274041L;
    
    @Element(name = "chave")
    private String chave;

    @Element(name = "unidRat", required = false)
    private String unidadeRateada;

    public CTeNotaInfoCTeNormalInfoModalAquaviarioConteinerInfoDocumentosNFe() {
        this.chave = null;
        this.unidadeRateada = null;
    }

    public String getChave() {
        return this.chave;
    }

    /**
     * Chave de acesso da NF-e
     */
    public void setChave(final String chave) {
        StringValidador.exatamente44N(chave, "Chave de acesso da NF-e");
        this.chave = chave;
    }

    public String getUnidadeRateada() {
        return this.unidadeRateada;
    }

    /**
     * Unidade de medida rateada (Peso,Volume)
     */
    public void setUnidadeRateada(final BigDecimal unidadeRateada) {
        this.unidadeRateada = BigDecimalValidador.tamanho5Com2CasasDecimais(unidadeRateada, "Unidade de medida rateada (Peso,Volume)");
    }
}
