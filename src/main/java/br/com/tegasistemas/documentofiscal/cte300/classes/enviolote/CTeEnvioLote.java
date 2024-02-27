package br.com.tegasistemas.documentofiscal.cte300.classes.enviolote;

import br.com.tegasistemas.documentofiscal.cte300.classes.nota.CTeNota;
import br.com.tegasistemas.documentofiscal.validadores.ListValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.*;

import java.util.List;

/**
 * @author Caio
 * @info Tipo Pedido de Concessão de Autorização da CT-e
 */

@Root(name = "enviCTe")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeEnvioLote extends DFBase {
    private static final long serialVersionUID = -6451397314955370262L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "idLote")
    private String idLote;
    
    @ElementList(name = "CTe", inline = true)
    private List<CTeNota> nota;

    public CTeEnvioLote() {
        this.versao = null;
        this.idLote = null;
        this.nota = null;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public String getIdLote() {
        return this.idLote;
    }

    public void setIdLote(final String idLote) {
        StringValidador.tamanho15N(idLote, "ID do Lote");
        this.idLote = idLote;
    }

    public List<CTeNota> getNota() {
        return this.nota;
    }

    public void setNota(final List<CTeNota> nota) {
        ListValidador.tamanho50(nota, "Notas");
        this.nota = nota;
    }
}
