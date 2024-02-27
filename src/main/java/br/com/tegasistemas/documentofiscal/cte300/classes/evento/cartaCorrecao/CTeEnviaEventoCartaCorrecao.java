package br.com.tegasistemas.documentofiscal.cte300.classes.evento.cartaCorrecao;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "evCCeCTe")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeEnviaEventoCartaCorrecao extends DFBase {
    private static final long serialVersionUID = -6990304145768185274L;

    @Element(name = "descEvento")
    private String descricaoEvento = "Cancelamento";

    @Element(name = "infCorrecao")
    private CTeDetalhamentoInfoCartaCorrecao2 infoCorrecao;

    @Element(name = "xCondUso")
    private String condUso;

    public void setDescricaoEvento(final String descricaoEvento) {
        final String defaultValue = "Carta de Correcao";
        StringValidador.tamanho5a60(descricaoEvento, defaultValue);
        //StringValidador.equals(defaultValue, descricaoEvento);
        this.descricaoEvento = descricaoEvento;
    }

    public String getDescricaoEvento() {
        return this.descricaoEvento;
    }

    public CTeDetalhamentoInfoCartaCorrecao2 getInfoCorrecao() {
        return infoCorrecao;
    }

    public void setInfoCorrecao(CTeDetalhamentoInfoCartaCorrecao2 infoCorrecao) {
        this.infoCorrecao = infoCorrecao;
    }

    public String getCondUso() {
        return condUso;
    }

    public void setCondUso(String conficaoUso) {
        this.condUso = conficaoUso;
    }
}