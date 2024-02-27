package br.com.tegasistemas.documentofiscal.cte400.classes.evento;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "procEventoCTe")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeEventoProc extends DFBase {

    private static final long serialVersionUID = -6887612399839814677L;
    @Element(name = "eventoCTe")
    private String evento;

    @Element(name = "retEventoCTe")
    private CTeEventoRetorno retorno;

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public CTeEventoRetorno getRetorno() {
        return retorno;
    }

    public void setRetorno(CTeEventoRetorno retorno) {
        this.retorno = retorno;
    }
}
