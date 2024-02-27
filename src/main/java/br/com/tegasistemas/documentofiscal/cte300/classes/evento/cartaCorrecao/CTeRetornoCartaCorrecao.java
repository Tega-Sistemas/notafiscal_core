package br.com.tegasistemas.documentofiscal.cte300.classes.evento.cartaCorrecao;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.CTeInfoEventoRetorno;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "retCancCTe")
public class CTeRetornoCartaCorrecao extends DFBase {
    private static final long serialVersionUID = -578023299108955542L;

    @Attribute(name = "versao", required = false)
    private String versao;

    @Element(name = "infEvento")
    private CTeInfoEventoRetorno infoCancelamento;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public CTeInfoEventoRetorno getInfoCancelamento() {
        return this.infoCancelamento;
    }

    public void setInfoCancelamento(final CTeInfoEventoRetorno infoCancelamento) {
        this.infoCancelamento = infoCancelamento;
    }
}