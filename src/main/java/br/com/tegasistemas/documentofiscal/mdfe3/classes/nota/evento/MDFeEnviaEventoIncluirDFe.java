package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.evento;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import br.com.tegasistemas.documentofiscal.DFBase;

import java.util.List;
import org.simpleframework.xml.ElementList;

/**
 * Created by Edivaldo Merlo Stens on 19/02/20.
 */
@Root(name = " evIncDFeMDFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFeEnviaEventoIncluirDFe extends DFBase {

    private static final long serialVersionUID = -6894944230355205786L;

    @Element(name = "descEvento")
    private String descricaoEvento = "Inclusao Condutor";

    @Element(name = "nProt")
    private String nProt;

    /**
     * Código do Município de Carregamento
     */
    @Element(name = "cMunCarrega")
    private String codigoMunicipioCarregamento;

    /**
     * Nome do Município de Carregamento
     */
    @Element(name = "xMunCarrega")
    private String nomeMunicipioCarregamento;

    @ElementList(entry = "infDoc", inline = true, required = true)
    private List<MDFeEnviaEventoIncluirDFeInfDoc> infDoc;

    public void setDescricaoEvento(final String descricaoEvento) {
        final String defaultValue = "Inclusao DF-e";
        StringValidador.equals(defaultValue, descricaoEvento);
        this.descricaoEvento = descricaoEvento;
    }

    public String getDescricaoEvento() {
        return this.descricaoEvento;
    }

    public String getnProt() {
        return nProt;
    }

    public void setnProt(String nProt) {
        this.nProt = nProt;
    }

    public String getCodigoMunicipioCarregamento() {
        return this.codigoMunicipioCarregamento;
    }

    public void setCodigoMunicipioCarregamento(final String codigoMunicipioCarregamento) {
        this.codigoMunicipioCarregamento = StringValidador.validador(codigoMunicipioCarregamento, "Codigo municipio carregamento", 7, true, true);
    }

    public String getNomeMunicipioCarregamento() {
        return this.nomeMunicipioCarregamento;
    }

    public void setNomeMunicipioCarregamento(final String nomeMunicipioCarregamento) {
        StringValidador.tamanho2ate60(nomeMunicipioCarregamento, "Nome municipio carregamento");
        this.nomeMunicipioCarregamento = nomeMunicipioCarregamento;
    }

    public List<MDFeEnviaEventoIncluirDFeInfDoc> getInfDoc() {
        return infDoc;
    }

    public void setInfDoc(List<MDFeEnviaEventoIncluirDFeInfDoc> infDoc) {
        this.infDoc = infDoc;
    }

}
