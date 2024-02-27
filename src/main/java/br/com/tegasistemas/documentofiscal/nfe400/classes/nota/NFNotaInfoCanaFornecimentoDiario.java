package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFNotaInfoCanaFornecimentoDiario extends DFBase {
    private static final long serialVersionUID = 4997480383513451707L;
    
    @Attribute(name = "dia")
    private int dia;
    
    @Element(name = "qtde")
    private String quantidade;

    public void setDia(final int dia) {
        if (dia > 31 || dia < 1) {
            throw new IllegalStateException("valor do dia invalido.");
        }
        this.dia = dia;
    }

    public void setQuantidade(final BigDecimal quantidade) {
        this.quantidade = DFBigDecimalValidador.tamanho21ComAte10CasasDecimais(quantidade, "Quantidade");
    }

    public int getDia() {
        return this.dia;
    }

    public String getQuantidade() {
        return this.quantidade;
    }
}