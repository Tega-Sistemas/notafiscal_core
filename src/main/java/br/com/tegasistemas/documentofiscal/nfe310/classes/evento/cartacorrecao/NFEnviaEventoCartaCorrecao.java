package br.com.tegasistemas.documentofiscal.nfe310.classes.evento.cartacorrecao;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe310.classes.evento.NFEvento;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.ListValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.*;

import java.math.BigDecimal;
import java.util.List;

@Root(name = "envEvento")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFEnviaEventoCartaCorrecao extends DFBase {
    private static final long serialVersionUID = -5454462961659029815L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "idLote")
    private String idLote;
    
    @ElementList(entry = "evento", inline = true)
    private List<NFEvento> evento;

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho5Com2CasasDecimais(versao, "Versao");
    }

    public String getVersao() {
        return this.versao;
    }

    public String getIdLote() {
        return this.idLote;
    }

    public void setIdLote(final String idLote) {
        StringValidador.tamanho15N(idLote, "ID do Lote");
        this.idLote = idLote;
    }

    public List<NFEvento> getEvento() {
        return this.evento;
    }

    public void setEvento(final List<NFEvento> evento) {
        ListValidador.tamanho20(evento, "Eventos");
        this.evento = evento;
    }
}