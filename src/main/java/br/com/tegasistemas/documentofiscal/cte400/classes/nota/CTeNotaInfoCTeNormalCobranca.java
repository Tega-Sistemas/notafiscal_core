package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Dados da cobrança do CT-e
 */

@Root(name = "cobr")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoCTeNormalCobranca extends DFBase {
    private static final long serialVersionUID = 1304916172129824460L;

    @Element(name = "fat", required = false)
    private CTeNotaInfoCTeNormalCobrancaFatura fatura;

    @ElementList(entry = "dup", inline = true, required = false)
    private List<CTeNotaInfoCTeNormalCobrancaDuplicata> duplicata;

    public CTeNotaInfoCTeNormalCobrancaFatura getFatura() {
        return this.fatura;
    }

    /**
     * Dados da fatura
     */
    public void setFatura(final CTeNotaInfoCTeNormalCobrancaFatura fatura) {
        this.fatura = fatura;
    }

    public List<CTeNotaInfoCTeNormalCobrancaDuplicata> getDuplicata() {
        return this.duplicata;
    }

    /**
     * Dados das duplicatas
     */
    public void setDuplicata(final List<CTeNotaInfoCTeNormalCobrancaDuplicata> duplicata) {
        this.duplicata = duplicata;
    }
}
