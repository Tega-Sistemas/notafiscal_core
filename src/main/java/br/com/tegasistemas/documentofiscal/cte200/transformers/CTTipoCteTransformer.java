package br.com.tegasistemas.documentofiscal.cte200.transformers;

import br.com.tegasistemas.documentofiscal.cte200.classes.CTTipoCte;
import org.simpleframework.xml.transform.Transform;

public class CTTipoCteTransformer implements Transform<CTTipoCte> {

    @Override
    public CTTipoCte read(final String codigo) {
        return CTTipoCte.valueOfCodigo(codigo);
    }

    @Override
    public String write(final CTTipoCte tipo) {
        return tipo.getCodigo();
    }
}