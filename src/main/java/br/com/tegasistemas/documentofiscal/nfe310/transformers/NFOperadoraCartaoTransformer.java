package br.com.tegasistemas.documentofiscal.nfe310.transformers;

import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.NFOperadoraCartao;
import org.simpleframework.xml.transform.Transform;

public class NFOperadoraCartaoTransformer implements Transform<NFOperadoraCartao> {

    @Override
    public NFOperadoraCartao read(final String codigo) {
        return NFOperadoraCartao.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFOperadoraCartao operadoraCartao) {
        return operadoraCartao.getCodigo();
    }
}