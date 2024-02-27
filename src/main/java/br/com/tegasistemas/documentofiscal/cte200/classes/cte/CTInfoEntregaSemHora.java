package br.com.tegasistemas.documentofiscal.cte200.classes.cte;

import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte200.classes.CTTipoEntregaHorario;

public class CTInfoEntregaSemHora extends DFBase {
    private static final long serialVersionUID = -8339490222357216168L;

    @Element(name = "tpHor")
    private CTTipoEntregaHorario tipoHorario;

    public void setTipoHorario(final CTTipoEntregaHorario tipoHorario) {
        if (!CTTipoEntregaHorario.SEM_HORARIO.equals(tipoHorario)) {
            throw new IllegalArgumentException("O tipo de per\u00edodo programado para entrega deve ser sem hor\u00e1rio");
        }
        this.tipoHorario = tipoHorario;
    }

    public CTTipoEntregaHorario getTipoHorario() {
        return this.tipoHorario;
    }

}
