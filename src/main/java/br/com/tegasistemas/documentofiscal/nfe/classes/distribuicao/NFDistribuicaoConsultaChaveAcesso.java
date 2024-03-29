package br.com.tegasistemas.documentofiscal.nfe.classes.distribuicao;

import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;

public class NFDistribuicaoConsultaChaveAcesso extends DFBase {
    private static final long serialVersionUID = 5822101975600089554L;

    @Element(name = "chNFe")
    private String chaveAcesso;

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }

    public NFDistribuicaoConsultaChaveAcesso setChaveAcesso(final String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
        return this;
    }

}
