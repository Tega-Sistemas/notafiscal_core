package br.com.tegasistemas.documentofiscal.cte200.classes.cte;

import java.time.LocalDate;
import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte200.classes.CTTipoEntregaData;

public class CTInfoEntregaComData extends DFBase {
    private static final long serialVersionUID = 7236218662791375431L;

    @Element(name = "tpPer")
    private CTTipoEntregaData tipoPeriodo;

    @Element(name = "dProg")
    private LocalDate dataProgramada;

    public LocalDate getDataProgramada() {
        return this.dataProgramada;
    }

    public void setDataProgramada(final LocalDate dataProgramada) {
        this.dataProgramada = dataProgramada;
    }

    public CTTipoEntregaData getTipoPeriodo() {
        return this.tipoPeriodo;
    }

    public void setTipoPeriodo(final CTTipoEntregaData tipoPeriodo) {
        if (!CTTipoEntregaData.COM_DATA.contains(tipoPeriodo)) {
            throw new IllegalArgumentException("O tipo de per\u00edodo programado para entrega deve ser com data");
        }
        this.tipoPeriodo = tipoPeriodo;
    }
}
