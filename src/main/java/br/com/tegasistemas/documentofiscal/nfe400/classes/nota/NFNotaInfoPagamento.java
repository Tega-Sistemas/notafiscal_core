package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.DFListValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.math.BigDecimal;
import java.util.List;

public class NFNotaInfoPagamento extends DFBase {
    private static final long serialVersionUID = -2613537583415054150L;

    @ElementList(entry = "detPag", inline = true, required = false)
    private List<NFNotaInfoFormaPagamento> detalhamentoFormasPagamento;

    @Element(name = "vTroco", required = false)
    private String valorTroco;

//    @Element(name = "xPag", required = false)
//    private String descPagamento;

    public NFNotaInfoPagamento setDetalhamentoFormasPagamento(final List<NFNotaInfoFormaPagamento> detalhamentoFormasPagamento) {
        DFListValidador.tamanho100(detalhamentoFormasPagamento, "Detalhamento de formas de pagamento");
        this.detalhamentoFormasPagamento = detalhamentoFormasPagamento;
        return this;
    }

    public List<NFNotaInfoFormaPagamento> getDetalhamentoFormasPagamento() {
        return this.detalhamentoFormasPagamento;
    }

    public String getValorTroco() {
        return this.valorTroco;
    }

    public void setValorTroco(final BigDecimal valorTroco) {
        this.valorTroco = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorTroco, "Valor troco");
    }

//    public String getDescPagamento() {
//        return this.descPagamento;
//    }
//
//    public void setDescPagamento(final String descPagamento) {
//        DFStringValidador.tamanho4a60(descPagamento, "Descricao do Pagamento");
//        this.descPagamento = descPagamento;
//    }

}