package br.com.tegasistemas.documentofiscal.cte300.classes.evento.inutilizacao;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.CTeInfoEventoRetorno;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "retInutCTe")
public class CTeRetornoInutilizacao extends DFBase {
    private static final long serialVersionUID = -578023299108955542L;

    @Attribute(name = "versao", required = false)
    private String versao;

    @Element(name = "infInut")
    private CTeInfoEventoRetorno infoInutilizacao;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public CTeInfoEventoRetorno getInfoInutilizacao() {
        return this.infoInutilizacao;
    }

    public void setInfoInutilizacao(final CTeInfoEventoRetorno infoInutilizacao) {
        this.infoInutilizacao = infoInutilizacao;
    }
}