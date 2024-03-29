package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.evento;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "procEventoMDFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFeProtocoloEvento extends DFBase {
    private static final long serialVersionUID = 8849959763700133248L;

    @Attribute(name = "versao")
    private String versao;

    @Element(name = "eventoMDFe")
    private MDFeEvento evento;

    @Element(name = "retEventoMDFe", required = false)
    private MDFeEventoRetorno eventoRetorno;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho4Com2CasasDecimais(versao, "Versao");
    }

    public MDFeEvento getEvento() {
        return this.evento;
    }

    public void setEvento(final MDFeEvento evento) {
        this.evento = evento;
    }

    public void setEventoRetorno(final MDFeEventoRetorno infoEventoRetorno) {
        this.eventoRetorno = infoEventoRetorno;
    }

    public MDFeEventoRetorno getEventoRetorno() {
        return this.eventoRetorno;
    }
}
