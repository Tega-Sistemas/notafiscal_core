package br.com.tegasistemas.documentofiscal.nfe310.classes.evento.manifestacaodestinatario;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFEventoManifestacaoDestinatario extends DFBase {
    private static final long serialVersionUID = 4608659349977367804L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "infEvento")
    private NFInfoEventoManifestacaoDestinatario infoEvento;

    @Element(name = "Signature", required = false)
    private String assinatura;

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho5Com2CasasDecimais(versao, "Versao");
    }

    public NFInfoEventoManifestacaoDestinatario getInfoEvento() {
        return this.infoEvento;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setInfoEvento(final NFInfoEventoManifestacaoDestinatario infoEvento) {
        this.infoEvento = infoEvento;
    }

    public void setAssinatura(final String assinatura) {
        this.assinatura = assinatura;
    }

    public String getAssinatura() {
        return this.assinatura;
    }
}