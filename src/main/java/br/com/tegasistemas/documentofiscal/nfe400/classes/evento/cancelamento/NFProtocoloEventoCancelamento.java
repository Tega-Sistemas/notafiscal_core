package br.com.tegasistemas.documentofiscal.nfe400.classes.evento.cancelamento;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe400.classes.evento.NFEventoRetorno;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "procEventoNFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFProtocoloEventoCancelamento extends DFBase {
    private static final long serialVersionUID = 8058424887014735846L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "evento")
    private NFEventoCancelamento evento;
    
    @Element(name = "retEvento")
    private NFEventoRetorno eventoRetorno;

    public NFEventoCancelamento getEvento() {
        return this.evento;
    }

    public void setEvento(final NFEventoCancelamento evento) {
        this.evento = evento;
    }

    public NFEventoRetorno getInfoEventoRetorno() {
        return this.eventoRetorno;
    }

    public void setEventoRetorno(final NFEventoRetorno infoEventoRetorno) {
        this.eventoRetorno = infoEventoRetorno;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }
}
