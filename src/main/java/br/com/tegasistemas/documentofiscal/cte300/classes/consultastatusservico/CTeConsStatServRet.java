package br.com.tegasistemas.documentofiscal.cte300.classes.consultastatusservico;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;

@Root(name = "retConsStatServCte")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeConsStatServRet extends DFBase {
    private static final long serialVersionUID = -8803841882986967420L;

    @Element(name = "tpAmb", required = false)
    private DFAmbiente ambiente;

    @Element(name = "verAplic", required = false)
    private String versaoAplicacao;

    @Element(name = "cStat", required = false)
    private String codigoStatus;

    @Element(name = "xMotivo", required = false)
    private String motivo;

    @Element(name = "cUF", required = false)
    private DFUnidadeFederativa uf;

    @Element(name = "dhRecbto", required = false)
    private String dataRecebimento;

    @Element(name = "tMed", required = false)
    private String tempoMedio;

    @Element(name = "dhRetorno", required = false)
    private String dataRetorno;

    @Element(name = "xObs", required = false)
    private String Observacao;

    @Attribute(name = "versao", required = false)
    private String versao;

    public CTeConsStatServRet() {
        this.ambiente = null;
        this.versaoAplicacao = null;
        this.codigoStatus = null;
        this.motivo = null;
        this.uf = null;
        this.dataRecebimento = null;
        this.tempoMedio = null;
        this.dataRetorno = null;
        this.Observacao = null;
        this.versao = null;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public void setVersaoAplicacao(final String versaoAplicacao) {
        this.versaoAplicacao = versaoAplicacao;
    }

    public String getCodigoStatus() {
        return this.codigoStatus;
    }

    public void setCodigoStatus(final String codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(final String motivo) {
        this.motivo = motivo;
    }

    public DFUnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getDataRecebimento() {
        return this.dataRecebimento;
    }

    public void setDataRecebimento(final String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getTempoMedio() {
        return this.tempoMedio;
    }

    public void setTempoMedio(final String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public String getDataRetorno() {
        return this.dataRetorno;
    }

    public void setDataRetorno(final String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getObservacao() {
        return this.Observacao;
    }

    public void setObservacao(final String observacao) {
        this.Observacao = observacao;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

}
