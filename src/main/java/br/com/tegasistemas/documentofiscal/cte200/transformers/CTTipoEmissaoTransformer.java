package br.com.tegasistemas.documentofiscal.cte200.transformers;

import br.com.tegasistemas.documentofiscal.cte200.classes.CTTipoEmissao;
import org.simpleframework.xml.transform.Transform;

public class CTTipoEmissaoTransformer implements Transform<CTTipoEmissao> {

    @Override
    public CTTipoEmissao read(final String codigo) {
        return CTTipoEmissao.valueOfCodigo(codigo);
    }

    @Override
    public String write(final CTTipoEmissao tipo) {
        return tipo.getCodigo();
    }
}