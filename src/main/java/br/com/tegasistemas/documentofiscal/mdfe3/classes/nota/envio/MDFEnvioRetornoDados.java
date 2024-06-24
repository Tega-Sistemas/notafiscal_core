package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.envio;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.nota.MDFe;
public class MDFEnvioRetornoDados extends DFBase {
    private final MDFEnvioRetorno retorno;
    private final MDFe mdfeAssinado;

    public MDFEnvioRetornoDados(MDFEnvioRetorno retorno, MDFe mdfeAssinado) {
        this.retorno = retorno;
        this.mdfeAssinado = mdfeAssinado;
    }

    public MDFEnvioRetorno getRetorno() {
        return retorno;
    }

    public MDFe getMDFEAssinado() {
        return mdfeAssinado;
    }
}
