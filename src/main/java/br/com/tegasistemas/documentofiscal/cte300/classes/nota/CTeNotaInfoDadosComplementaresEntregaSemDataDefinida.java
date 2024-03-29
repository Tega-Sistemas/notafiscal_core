package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte300.classes.CTTipoPrazoDataEntrega;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Entrega sem data definida<br>
 *       Esta opção é proibida para o modal aéreo.
 */

@Root(name = "semData")
public class CTeNotaInfoDadosComplementaresEntregaSemDataDefinida extends DFBase {
    private static final long serialVersionUID = -6311993509859814067L;
    
    @Element(name = "tpPer")
    private CTTipoPrazoDataEntrega tipoPrazoDataEntrega;

    public CTeNotaInfoDadosComplementaresEntregaSemDataDefinida() {
        this.tipoPrazoDataEntrega = null;
    }

    public CTTipoPrazoDataEntrega getTipoPrazoDataEntrega() {
        return this.tipoPrazoDataEntrega;
    }

    /**
     * Tipo de data/período programado para entrega<br>
     * 0 - Sem data definida
     */
    public void setTipoPrazoDataEntrega(final CTTipoPrazoDataEntrega tipoPrazoDataEntrega) {
        this.tipoPrazoDataEntrega = tipoPrazoDataEntrega;
    }
}
