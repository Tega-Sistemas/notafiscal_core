package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe400.classes.NFIndicadorFormaPagamento;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFNotaInfoFormaPagamento extends DFBase {
    private static final long serialVersionUID = 8908558834476720280L;

    @Element(name = "indPag", required = false)
    private NFIndicadorFormaPagamento indicadorFormaPagamento;

    @Element(name = "tPag")
    private NFMeioPagamento meioPagamento;

    @Element(name = "xPag", required = false)
    private String descPagamento;

    @Element(name = "vPag")
    private String valorPagamento;

    @Element(name = "card", required = false)
    private NFNotaInfoCartao cartao;

    public void setCartao(final NFNotaInfoCartao cartao) {
        this.cartao = cartao;
    }

    public NFNotaInfoCartao getCartao() {
        return this.cartao;
    }

    public void setIndicadorFormaPagamento(final NFIndicadorFormaPagamento indicadorFormaPagamento) {
        this.indicadorFormaPagamento = indicadorFormaPagamento;
    }

    public void setMeioPagamento(final NFMeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public void setValorPagamento(final BigDecimal valorPagamento) {
        this.valorPagamento = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorPagamento, "Valor Pagamento");
    }

    public NFIndicadorFormaPagamento getIndicadorFormaPagamento() {
        return this.indicadorFormaPagamento;
    }

    public NFMeioPagamento getMeioPagamento() {
        return this.meioPagamento;
    }

    public String getValorPagamento() {
        return this.valorPagamento;
    }

    public String getDescPagamento() {
        return descPagamento;
    }

    public void setDescPagamento(String descPagamento) {
        this.descPagamento = descPagamento;
    }
}