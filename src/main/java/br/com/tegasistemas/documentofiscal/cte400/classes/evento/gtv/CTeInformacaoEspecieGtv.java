package br.com.tegasistemas.documentofiscal.cte400.classes.evento.gtv;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte400.classes.CTTipoEspecieGtv;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class CTeInformacaoEspecieGtv extends DFBase {
    private static final long serialVersionUID = 229144141854691208L;
    
    @Element(name = "tpEspecie")
    private CTTipoEspecieGtv tipoEspecie;

    @Element(name = "vEspecie", required = false)
    private String valorEspecie;

    public CTTipoEspecieGtv getTipoEspecie() {
        return tipoEspecie;
    }

    public void setTipoEspecie(CTTipoEspecieGtv tipoEspecie) {
        this.tipoEspecie = tipoEspecie;
    }

    public String getValorEspecie() {
        return valorEspecie;
    }

    public void setValorEspecie(BigDecimal valorEspecie) {
        this.valorEspecie = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorEspecie, "Valor Especie");
    }
}
