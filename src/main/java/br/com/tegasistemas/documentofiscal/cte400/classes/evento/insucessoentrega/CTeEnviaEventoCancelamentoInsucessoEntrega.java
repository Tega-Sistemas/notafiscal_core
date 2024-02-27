package br.com.tegasistemas.documentofiscal.cte400.classes.evento.insucessoentrega;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeTipoEvento;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "evCancIECTe")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeEnviaEventoCancelamentoInsucessoEntrega extends CTeTipoEvento {
    private static final long serialVersionUID = -3393318307075884689L;

    @Element(name = "nProt")
    private String protocoloAutorizacao;

    @Element(name = "nProtIE")
    private String protocoloInsucessoEntrega;

    public String getProtocoloAutorizacao() {
        return protocoloAutorizacao;
    }

    public void setProtocoloAutorizacao(String protocoloAutorizacao) {
        DFStringValidador.exatamente15N(protocoloAutorizacao, "Protocolo de Autorizacao");
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public String getProtocoloInsucessoEntrega() {
        return protocoloInsucessoEntrega;
    }

    public void setProtocoloInsucessoEntrega(String protocoloInsucessoEntrega) {
        DFStringValidador.exatamente15N(protocoloInsucessoEntrega, "Protocolo do Insucesso na Entrega");
        this.protocoloInsucessoEntrega = protocoloInsucessoEntrega;
    }
}