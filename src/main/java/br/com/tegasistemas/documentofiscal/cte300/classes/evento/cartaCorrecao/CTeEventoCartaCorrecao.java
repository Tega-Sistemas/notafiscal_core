package br.com.tegasistemas.documentofiscal.cte300.classes.evento.cartaCorrecao;

import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "eventoCTe")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeEventoCartaCorrecao extends DFBase {
    private static final long serialVersionUID = -8363617761063438288L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "infEvento")
    private CTeInfoEventoCartaCorrecao infoEvento;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho5Com2CasasDecimais(versao, "Versao");
    }

    public CTeInfoEventoCartaCorrecao getInfoEvento() {
        return this.infoEvento;
    }

    public void setInfoEvento(final CTeInfoEventoCartaCorrecao infoEvento) {
        this.infoEvento = infoEvento;
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }
}