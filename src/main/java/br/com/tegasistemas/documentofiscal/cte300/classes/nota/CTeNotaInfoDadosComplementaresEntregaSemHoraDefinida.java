package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte300.classes.CTTipoPrazoHoraEntrega;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Entrega sem hora definida
 */

@Root(name = "semHora")
public class CTeNotaInfoDadosComplementaresEntregaSemHoraDefinida extends DFBase {
    private static final long serialVersionUID = 8715534516011562469L;
    
    @Element(name = "tpHor")
    private CTTipoPrazoHoraEntrega tipoPrazoHoraEntrega;

    public CTeNotaInfoDadosComplementaresEntregaSemHoraDefinida() {
        this.tipoPrazoHoraEntrega = null;
    }

    public CTTipoPrazoHoraEntrega getTipoPrazoHoraEntrega() {
        return this.tipoPrazoHoraEntrega;
    }

    /**
     * Tipo de hora<br>
     * 0 - Sem hora definida
     */
    public void setTipoPrazoHoraEntrega(final CTTipoPrazoHoraEntrega tipoPrazoHoraEntrega) {
        this.tipoPrazoHoraEntrega = tipoPrazoHoraEntrega;
    }
}
