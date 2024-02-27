package br.com.tegasistemas.documentofiscal.cte400.classes.nota;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFListValidador;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Informações do modal Rodoviário
 */

@Root(name = "rodo")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoCTeNormalInfoModalRodoviario extends DFBase {
    private static final long serialVersionUID = -1376805608072818029L;

    @Element(name = "RNTRC")
    private String rntrc;

    @ElementList(name = "occ", inline = true, required = false)
    private List<CTeNotaInfoCTeNormalInfoModalRodoviarioOrdemColetaAssociadas> ordemColetaAssociadas;

    public String getRntrc() {
        return this.rntrc;
    }

    /**
     * Registro Nacional de Transportadores Rodoviários de Carga<br>
     * Registro obrigatório do emitente do CT-e junto à ANTT para exercer a atividade de transportador rodoviário de cargas por conta de terceiros e mediante remuneração.
     */
    public void setRntrc(final String rntrc) {
        DFStringValidador.exatamente8(rntrc, "Registro Nacional de Transportadores Rodoviários de Carga");
        this.rntrc = rntrc;
    }

    public List<CTeNotaInfoCTeNormalInfoModalRodoviarioOrdemColetaAssociadas> getOrdemColetaAssociadas() {
        return this.ordemColetaAssociadas;
    }

    /**
     * Ordens de Coleta associados
     */
    public void setOrdemColetaAssociadas(final List<CTeNotaInfoCTeNormalInfoModalRodoviarioOrdemColetaAssociadas> ordemColetaAssociadas) {
        DFListValidador.tamanho10(ordemColetaAssociadas, "Ordens de Coleta associados");
        this.ordemColetaAssociadas = ordemColetaAssociadas;
    }
}
