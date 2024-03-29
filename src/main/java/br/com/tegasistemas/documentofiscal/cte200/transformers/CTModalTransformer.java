package br.com.tegasistemas.documentofiscal.cte200.transformers;

import br.com.tegasistemas.documentofiscal.cte200.classes.CTModal;
import org.simpleframework.xml.transform.Transform;

public class CTModalTransformer implements Transform<CTModal> {

    @Override
    public CTModal read(final String codigo) {
        return CTModal.valueOfCodigo(codigo);
    }

    @Override
    public String write(final CTModal tipo) {
        return tipo.getCodigo();
    }
}