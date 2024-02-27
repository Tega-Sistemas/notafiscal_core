package br.com.tegasistemas.documentofiscal.cte300.classes.evento.inutilizacao;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.CTeEventoRetorno;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "inutCTe")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeProtocoloEventoInutilizacao extends DFBase {
    private static final long serialVersionUID = -5921322695285609605L;

    @Attribute(name = "versao")
    private String versao;

    @Element(name = "infInut")
    @Namespace(reference = "http://www.portalfiscal.inf.br/cte")
    private CTeEventoInutilizacao evento;


    @Element(name = "retInutCTe", required = false)
    private CTeEventoRetorno eventoRetorno;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho4Com2CasasDecimais(versao, "Versao");
    }

    public CTeEventoInutilizacao getEvento() {
        return this.evento;
    }

    public void setEvento(final CTeEventoInutilizacao evento) {
        this.evento = evento;
    }

    public void setEventoRetorno(final CTeEventoRetorno infoEventoRetorno) {
        this.eventoRetorno = infoEventoRetorno;
    }

    public CTeEventoRetorno getEventoRetorno() {
        return this.eventoRetorno;
    }
}
