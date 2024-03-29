package br.com.tegasistemas.documentofiscal.cte400.classes.evento.multimodal;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeTipoEvento;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "evRegMultimodal")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeEnviaEventoRegistroMultimodal extends CTeTipoEvento {
    private static final long serialVersionUID = -2748973737856358284L;

    @Element(name = "xRegistro")
    private String informacoesAdicionais;

    @Element(name = "nDoc")
    private String numeroDocumento;

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        DFStringValidador.tamanho15a1000(informacoesAdicionais, "Informações Adicionais");
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumero(String numeroDocumento) {
        DFStringValidador.validaTamanhoMaximo(numeroDocumento, 44, "Número do documento");
        this.numeroDocumento = numeroDocumento;
    }
}
