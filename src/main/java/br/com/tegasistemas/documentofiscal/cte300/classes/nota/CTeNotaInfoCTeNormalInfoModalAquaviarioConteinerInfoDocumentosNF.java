package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * @author Caio
 * @info Informações das NF
 */

@Root(name = "infNF")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoModalAquaviarioConteinerInfoDocumentosNF extends DFBase {
    private static final long serialVersionUID = 2044424620532788296L;
    
    @Element(name = "serie")
    private String serie;
    
    @Element(name = "nDoc")
    private String numero;

    @Element(name = "unidRat", required = false)
    private String unidadeRateada;

    public CTeNotaInfoCTeNormalInfoModalAquaviarioConteinerInfoDocumentosNF() {
        this.serie = null;
        this.numero = null;
        this.unidadeRateada = null;
    }

    public String getSerie() {
        return this.serie;
    }

    /**
     * Série
     */
    public void setSerie(final String serie) {
        StringValidador.tamanho3(serie, "Série");
        this.serie = serie;
    }

    public String getNumero() {
        return this.numero;
    }

    /**
     * Número
     */
    public void setNumero(final String numero) {
        StringValidador.tamanho20(numero, "Número");
        this.numero = numero;
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
