package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.consulta;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "consSitMDFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFeNotaConsulta extends DFBase {
    private static final long serialVersionUID = -6782722764188583523L;

    @Attribute(name = "versao")
    private String versao;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "xServ")
    private String servico;

    @Element(name = "chMDFe")
    private String chave;

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalValidador.tamanho4Com2CasasDecimais(versao, "Versao Nota Consulta");
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public void setChave(final String chave) {
        StringValidador.exatamente44N(chave, "Chave de Acesso Nota Consulta");
        this.chave = chave;
    }

    public String getVersao() {
        return this.versao;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getServico() {
        return this.servico;
    }

    public String getChave() {
        return this.chave;
    }
}