package br.com.tegasistemas.documentofiscal.cte400.classes.os;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "infQ")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeOSInfoCTeNormalInfoCargaInformacoesQuantidadeCarga extends DFBase {
    private static final long serialVersionUID = 9113316063559072994L;

    @Element(name = "qCarga")
    private String quantidade;

    public String getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(final BigDecimal quantidade) {
        this.quantidade = DFBigDecimalValidador.tamanho15Com4CasasDecimais(quantidade, "Quantidade");
    }
}
