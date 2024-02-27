package br.com.tegasistemas.documentofiscal.nfe400.classes.evento.inutilizacao;

import br.com.tegasistemas.documentofiscal.utils.DFPersister;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;

@Root(name = "ProcInutNFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFProtocoloInutilizacao {
    private static final long serialVersionUID = -1075773716893722198L;

    @Attribute(name = "versao", required = false)
    private String versao;

    @Element(name = "inutNFe")
    private NFEnviaEventoInutilizacao evento;

    @Element(name = "retInutNFe")
    private NFRetornoEventoInutilizacao eventoRetorno;

    public NFEnviaEventoInutilizacao getEvento() {
        return evento;
    }

    public void setEvento(NFEnviaEventoInutilizacao evento) {
        this.evento = evento;
    }

    public NFRetornoEventoInutilizacao getEventoRetorno() {
        return eventoRetorno;
    }

    public NFRetornoEventoInutilizacao getInfoEventoRetorno() {
        return this.eventoRetorno;
    }

    public void setEventoRetorno(final NFRetornoEventoInutilizacao infoEventoRetorno) {
        this.eventoRetorno = infoEventoRetorno;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    @Override
    public String toString() {
        final Persister persister = new DFPersister();
        try (StringWriter writer = new StringWriter()) {
            persister.write(this, writer);
            return writer.toString();
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
