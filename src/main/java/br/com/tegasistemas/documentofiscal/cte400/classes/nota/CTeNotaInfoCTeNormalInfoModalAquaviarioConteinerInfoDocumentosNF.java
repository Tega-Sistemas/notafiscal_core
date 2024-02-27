package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * Informações das NF
 */

@Root(name = "infNF")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoCTeNormalInfoModalAquaviarioConteinerInfoDocumentosNF extends DFBase {
    private static final long serialVersionUID = 5584286823820138838L;

    @Element(name = "serie")
    private String serie;
    
    @Element(name = "nDoc")
    private String numero;

    @Element(name = "unidRat", required = false)
    private String unidadeRateada;

    public String getSerie() {
        return this.serie;
    }

    /**
     * Série
     */
    public void setSerie(final String serie) {
        DFStringValidador.tamanho3(serie, "Série");
        this.serie = serie;
    }

    public String getNumero() {
        return this.numero;
    }

    /**
     * Número
     */
    public void setNumero(final String numero) {
        DFStringValidador.tamanho20(numero, "Número");
        this.numero = numero;
    }

    public String getUnidadeRateada() {
        return this.unidadeRateada;
    }

    /**
     * Unidade de medida rateada (Peso,Volume)
     */
    public void setUnidadeRateada(final BigDecimal unidadeRateada) {
        this.unidadeRateada = DFBigDecimalValidador.tamanho5Com2CasasDecimais(unidadeRateada, "Unidade de medida rateada (Peso,Volume)");
    }
}
