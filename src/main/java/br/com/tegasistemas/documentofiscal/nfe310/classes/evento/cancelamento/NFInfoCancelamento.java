package br.com.tegasistemas.documentofiscal.nfe310.classes.evento.cancelamento;

import br.com.tegasistemas.documentofiscal.nfe310.classes.evento.NFTipoEvento;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.Element;

public class NFInfoCancelamento extends NFTipoEvento {
    private static final long serialVersionUID = 7427073073940993756L;
    
    @Element(name = "nProt")
    private String protocoloAutorizacao;
    
    @Element(name = "xJust")
    private String justificativa;

    public void setJustificativa(final String justificativa) {
        StringValidador.tamanho15a256(justificativa, "Justificativa");
        this.justificativa = justificativa;
    }

    public void setProtocoloAutorizacao(final String protocoloAutorizacao) {
        StringValidador.exatamente15N(protocoloAutorizacao, "Protocolo de Autorizacao");
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public String getJustificativa() {
        return this.justificativa;
    }

    public String getProtocoloAutorizacao() {
        return this.protocoloAutorizacao;
    }

    @Override
    public void setCondicaoUso(final String condicaoUso) {
        throw new UnsupportedOperationException("Evento de cancelamento nao possui condicao de uso");
    }

    @Override
    public void setTextoCorrecao(final String textoCorrecao) {
        throw new UnsupportedOperationException("Evento de cancelamento nao possui texto de correcao");
    }
}