package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Informações do modal Dutoviário
 */

@Root(name = "duto")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoCTeNormalInfoModalDutoviario extends DFBase {
    private static final long serialVersionUID = 5249378125159119749L;

    @Element(name = "vTar", required = false)
    private String valorTarifa;
    
    @Element(name = "dIni")
    private LocalDate dataInicioServico;
    
    @Element(name = "dFim")
    private LocalDate dataFimServico;

    public String getValorTarifa() {
        return this.valorTarifa;
    }

    /**
     * Valor da tarifa
     */
    public void setValorTarifa(final BigDecimal valorTarifa) {
        this.valorTarifa = DFBigDecimalValidador.tamanho15comAte6CasasDecimais(valorTarifa, "Valor da tarifa");
    }

    public LocalDate getDataInicioServico() {
        return this.dataInicioServico;
    }

    /**
     * Data de Início da prestação do serviço
     */
    public void setDataInicioServico(final LocalDate dataInicioServico) {
        this.dataInicioServico = dataInicioServico;
    }

    public LocalDate getDataFimServico() {
        return this.dataFimServico;
    }

    /**
     * Data de Fim da prestação do serviço
     */
    public void setDataFimServico(final LocalDate dataFimServico) {
        this.dataFimServico = dataFimServico;
    }
}
