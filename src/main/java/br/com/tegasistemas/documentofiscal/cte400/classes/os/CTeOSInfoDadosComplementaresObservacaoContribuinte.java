package br.com.tegasistemas.documentofiscal.cte400.classes.os;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "ObsCont")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeOSInfoDadosComplementaresObservacaoContribuinte extends DFBase {
    private static final long serialVersionUID = 3363633720721437281L;

    @Attribute(name = "xCampo")
    private String campo;

    @Element(name = "xTexto")
    private String texto;

    public String getCampo() {
        return this.campo;
    }

    /**
     * Identificação do campo
     */
    public void setCampo(final String campo) {
        DFStringValidador.tamanho20(campo, "Identificação do campo");
        this.campo = campo;
    }

    public String getTexto() {
        return this.texto;
    }

    /**
     * Identificação do texto
     */
    public void setTexto(final String texto) {
        DFStringValidador.tamanho160(texto, "Identificação do texto");
        this.texto = texto;
    }
}
