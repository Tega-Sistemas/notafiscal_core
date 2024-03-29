package br.com.tegasistemas.documentofiscal.cte400.classes.os;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte400.classes.CTCodigoSituacaoTributariaICMS;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "ICMS90")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeOSInfoInformacoesRelativasImpostosICMS90 extends DFBase {
    
    @Element(name = "CST")
    private CTCodigoSituacaoTributariaICMS codigoSituacaoTributaria;

    @Element(name = "pRedBC", required = false)
    private String aliquotaReducaoBaseCalculo;
    
    @Element(name = "vBC")
    private String baseCalculoICMS;
    
    @Element(name = "pICMS")
    private String aliquotaICMS;
    
    @Element(name = "vICMS")
    private String valorICMS;

    @Element(name = "vCred", required = false)
    private String valorCredito;

    public CTeOSInfoInformacoesRelativasImpostosICMS90() {
        this.codigoSituacaoTributaria = null;
        this.aliquotaReducaoBaseCalculo = null;
        this.baseCalculoICMS = null;
        this.aliquotaICMS = null;
        this.valorICMS = null;
        this.valorCredito = null;
    }

    public CTCodigoSituacaoTributariaICMS getCodigoSituacaoTributaria() {
        return this.codigoSituacaoTributaria;
    }

    /**
     * Classificação Tributária do Serviço<br>
     * 90 - ICMS outros
     */
    public void setCodigoSituacaoTributaria(final CTCodigoSituacaoTributariaICMS codigoSituacaoTributaria) {
        this.codigoSituacaoTributaria = codigoSituacaoTributaria;
    }

    public String getAliquotaReducaoBaseCalculo() {
        return this.aliquotaReducaoBaseCalculo;
    }

    /**
     * Percentual de redução da BC
     */
    public void setAliquotaReducaoBaseCalculo(final BigDecimal aliquotaReducaoBaseCalculo) {
        this.aliquotaReducaoBaseCalculo = DFBigDecimalValidador.tamanho5Com2CasasDecimais(aliquotaReducaoBaseCalculo, "Percentual de redução da BC");
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

    public String getValorCredito() {
        return this.valorCredito;
    }

    /**
     * Valor do Crédito Outorgado/Presumido
     */
    public void setValorCredito(final BigDecimal valorCredito) {
        this.valorCredito = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorCredito, "Valor do Crédito Outorgado/Presumido");
    }
}
