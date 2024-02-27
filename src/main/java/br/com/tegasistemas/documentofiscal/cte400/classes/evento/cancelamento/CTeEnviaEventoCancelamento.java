package br.com.tegasistemas.documentofiscal.cte400.classes.evento.cancelamento;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeTipoEvento;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "evCancCTe")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeEnviaEventoCancelamento extends CTeTipoEvento {
    private static final long serialVersionUID = -6990304145768185274L;

    @Element(name = "nProt")
    private String protocoloAutorizacao;

    @Element(name = "xJust")
    private String justificativa;

    public void setJustificativa(final String justificativa) {
        DFStringValidador.tamanho15a256(justificativa, "Justificativa");
        this.justificativa = justificativa;
    }

    public void setProtocoloAutorizacao(final String protocoloAutorizacao) {
        DFStringValidador.exatamente15N(protocoloAutorizacao, "Protocolo de Autorizacao");
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public String getJustificativa() {
        return this.justificativa;
    }

    public String getProtocoloAutorizacao() {
        return this.protocoloAutorizacao;
    }

}