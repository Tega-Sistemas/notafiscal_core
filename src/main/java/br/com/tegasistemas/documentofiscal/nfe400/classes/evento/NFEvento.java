package br.com.tegasistemas.documentofiscal.nfe400.classes.evento;

import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.assinatura.NFSignature;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFEvento extends DFBase {
    private static final long serialVersionUID = 8494204773888020693L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "infEvento")
    private NFInfoEvento infoEvento;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public void setVersao(final BigDecimal versao) {
        this.versao = DFBigDecimalValidador.tamanho5Com2CasasDecimais(versao, "Versao");
    }

    public NFInfoEvento getInfoEvento() {
        return this.infoEvento;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setInfoEvento(final NFInfoEvento infoEvento) {
        this.infoEvento = infoEvento;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }
}