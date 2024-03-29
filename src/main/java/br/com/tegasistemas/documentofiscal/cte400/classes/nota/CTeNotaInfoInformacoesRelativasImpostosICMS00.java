package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte400.classes.CTCodigoSituacaoTributariaICMS;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * Prestação sujeito à tributação normal do ICMS
 */

@Root(name = "ICMS00")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoInformacoesRelativasImpostosICMS00 extends DFBase {
    private static final long serialVersionUID = 697998987953556318L;

    @Element(name = "CST")
    private CTCodigoSituacaoTributariaICMS codigoSituacaoTributaria;
    
    @Element(name = "vBC")
    private String baseCalculoICMS;
    
    @Element(name = "pICMS")
    private String aliquotaICMS;
    
    @Element(name = "vICMS")
    private String valorICMS;

    public CTCodigoSituacaoTributariaICMS getCodigoSituacaoTributaria() {
        return this.codigoSituacaoTributaria;
    }

    /**
     * classificação Tributária do Serviço<br>
     * 00 - tributação normal ICMS
     */
    public void setCodigoSituacaoTributaria(final CTCodigoSituacaoTributariaICMS codigoSituacaoTributaria) {
        this.codigoSituacaoTributaria = codigoSituacaoTributaria;
    }

    public String getBaseCalculoICMS() {
        return this.baseCalculoICMS;
    }

    /**
     * Valor da BC do ICMS
     */
    public void setBaseCalculoICMS(final BigDecimal baseCalculoICMS) {
        this.baseCalculoICMS = DFBigDecimalValidador.tamanho15Com2CasasDecimais(baseCalculoICMS, "Valor da BC do ICMS");
    }

    public String getAliquotaICMS() {
        return this.aliquotaICMS;
    }

    /**
     * Alíquota do ICMS
     */
    public void setAliquotaICMS(final BigDecimal aliquotaICMS) {
        this.aliquotaICMS = DFBigDecimalValidador.tamanho5Com2CasasDecimais(aliquotaICMS, "Alíquota do ICMS");
    }

    public String getValorICMS() {
        return this.valorICMS;
    }

    /**
     * Valor do ICMS
     */
    public void setValorICMS(final BigDecimal valorICMS) {
        this.valorICMS = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorICMS, "Valor do ICMS");
    }
}
