package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.envio;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.MDFProtocolo;

/**
 * Created by Edivaldo Merlo Stens on 29/05/24. Retorno do envio do MDF-e.
 */
@Root(name = "retMDFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFEnvioRetorno extends DFBase {
    private static final long serialVersionUID = -1891312937948557486L;

    @Element(name = "tpAmb", required = false)
    private DFAmbiente ambiente;

    @Element(name = "cUF", required = false)
    private DFUnidadeFederativa uf;

    @Element(name = "verAplic", required = false)
    private String versaoAplicacao;

    @Element(name = "cStat", required = false)
    private String status;

    @Element(name = "xMotivo", required = false)
    private String motivo;

    @Element(name = "protMDFe", required = false)
    private MDFProtocolo mdfProtocolo;

    @Attribute(name = "versao", required = false)
    private String versao;

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    /**
     * Identificação do Ambiente:1 - Produção; 2 - Homologação
     */
    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public DFUnidadeFederativa getUf() {
        return this.uf;
    }

    /**
     * Identificação da UF
     */
    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    /**
     * Versão do Aplicativo que recebeu o Lote.
     */
    public void setVersaoAplicacao(final String versaoAplicacao) {
        this.versaoAplicacao = versaoAplicacao;
    }

    public String getStatus() {
        return this.status;
    }

    /**
     * Código do status da mensagem enviada.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    public String getMotivo() {
        return this.motivo;
    }

    /**
     * Descrição literal do status do serviço solicitado.
     */
    public void setMotivo(final String motivo) {
        this.motivo = motivo;
    }

    public MDFProtocolo getMdfProtocolo() {
        return this.mdfProtocolo;
    }

    /**
     * Dados do Recibo do Lote
     */
    public void setMdfProtocolo(final MDFProtocolo mdfProtocolo) {
        this.mdfProtocolo = mdfProtocolo;
    }

    public String getVersao() {
        return this.versao;
    }

    /**
     * versão da aplicação
     */
    public void setVersao(final String versao) {
        this.versao = versao;
    }
}