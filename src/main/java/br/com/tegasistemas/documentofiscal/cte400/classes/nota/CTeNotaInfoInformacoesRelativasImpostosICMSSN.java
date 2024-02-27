package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte400.classes.CTCodigoSituacaoTributariaICMS;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Simples Nacional
 */

@Root(name = "ICMSSN")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoInformacoesRelativasImpostosICMSSN extends DFBase {
    private static final long serialVersionUID = -4050421099195471679L;

    @Element(name = "CST")
    private CTCodigoSituacaoTributariaICMS codigoSituacaoTributaria;
    
    @Element(name = "indSN")
    private String indicadorSN;

    public CTCodigoSituacaoTributariaICMS getCodigoSituacaoTributaria() {
        return this.codigoSituacaoTributaria;
    }

    /**
     * Classificação Tributária do Serviço<br>
     * 90 - ICMS Simples Nacional
     */
    public void setCodigoSituacaoTributaria(final CTCodigoSituacaoTributariaICMS codigoSituacaoTributaria) {
        this.codigoSituacaoTributaria = codigoSituacaoTributaria;
    }

    public String getIndicadorSN() {
        return this.indicadorSN;
    }

    /**
     * Indica se o contribuinte é Simples Nacional 1=Sim
     */
    public void setIndicadorSN(final String indicadorSN) {
        this.indicadorSN = indicadorSN;
    }
}
