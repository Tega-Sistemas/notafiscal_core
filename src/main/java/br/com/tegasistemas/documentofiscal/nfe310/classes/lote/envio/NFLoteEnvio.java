package br.com.tegasistemas.documentofiscal.nfe310.classes.lote.envio;

import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.NFNota;
import br.com.tegasistemas.documentofiscal.validadores.ListValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.*;

import java.util.List;

@Root(name = "enviNFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFLoteEnvio extends DFBase {
    private static final long serialVersionUID = 4208444639749964265L;
    
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "idLote")
    private String idLote;
    
    @Element(name = "indSinc")
    private NFLoteIndicadorProcessamento indicadorProcessamento;
    
    @ElementList(name = "NFe", inline = true)
    List<NFNota> notas;

    public String getIdLote() {
        return this.idLote;
    }

    public void setIdLote(final String idLote) {
        StringValidador.tamanho15N(idLote, "ID do Lote");
        this.idLote = idLote;
    }

    public void setNotas(final List<NFNota> notas) {
        ListValidador.tamanho50(notas, "Notas");
        this.notas = notas;
    }

    public List<NFNota> getNotas() {
        return this.notas;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public void setIndicadorProcessamento(final NFLoteIndicadorProcessamento indicadorProcessamento) {
        this.indicadorProcessamento = indicadorProcessamento;
    }

    public String getVersao() {
        return this.versao;
    }

    public NFLoteIndicadorProcessamento getIndicadorProcessamento() {
        return this.indicadorProcessamento;
    }
}