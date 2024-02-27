package br.com.tegasistemas.documentofiscal.cte300.classes.evento;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class CTeEventoRetorno extends DFBase {
    private static final long serialVersionUID = -8952520263707135185L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "infEvento")
    private CTeInfoEventoRetorno infoEventoRetorno;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public CTeInfoEventoRetorno getInfoEventoRetorno() {
        return this.infoEventoRetorno;
    }

    public void setInfoEventoRetorno(final CTeInfoEventoRetorno infoEventoRetorno) {
        this.infoEventoRetorno = infoEventoRetorno;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho4Com2CasasDecimais(versao, "Versao");
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }
}