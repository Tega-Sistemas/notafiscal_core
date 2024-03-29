package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.consulta;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.MDFProtocolo;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.evento.MDFeProtocoloEvento;
import org.simpleframework.xml.*;

import java.util.List;

@Root(name = "retConsSitMDFe", strict = false)
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFeNotaConsultaRetorno extends DFBase {
    private static final long serialVersionUID = -3063292755609244217L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "tpAmb")
    private DFAmbiente ambiente;
    
    @Element(name = "verAplic")
    private String versaoAplicacao;
    
    @Element(name = "cStat")
    private String status;
    
    @Element(name = "xMotivo")
    private String motivo;
    
    @Element(name = "cUF")
    private DFUnidadeFederativa uf;

    @Element(name = "protMDFe", required = false)
    protected MDFProtocolo protocolo;

    @ElementList(entry = "procEventoMDFe", inline = true, required = false)
    private List<MDFeProtocoloEvento> protocoloEvento;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
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

    public MDFProtocolo getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(final MDFProtocolo protocolo) {
        this.protocolo = protocolo;
    }

    public List<MDFeProtocoloEvento> getProtocoloEvento() {
        return this.protocoloEvento;
    }

    public void setProtocoloEvento(final List<MDFeProtocoloEvento> protocoloEvento) {
        this.protocoloEvento = protocoloEvento;
    }
}