package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFNotaInfoCanaDeducao extends DFBase {
    private static final long serialVersionUID = 5519359886554978924L;
    
    @Element(name = "xDed")
    private String descricaoDeducao;
    
    @Element(name = "vDed")
    private String valorDeducao;

    public void setDescricaoDeducao(final String descricaoDeducao) {
        DFStringValidador.tamanho60(descricaoDeducao, "Descricao Deducao");
        this.descricaoDeducao = descricaoDeducao;
    }

    public void setValorDeducao(final BigDecimal valorDeducao) {
        this.valorDeducao = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorDeducao, "Valor Deducao");
    }

    public String getDescricaoDeducao() {
        return this.descricaoDeducao;
    }

    public String getValorDeducao() {
        return this.valorDeducao;
    }
}