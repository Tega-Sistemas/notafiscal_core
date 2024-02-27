package br.com.tegasistemas.documentofiscal.cte200.transformers;

import br.com.tegasistemas.documentofiscal.cte200.classes.CTResponsavelSeguro;
import org.simpleframework.xml.transform.Transform;

public class CTResponsavelSeguroTransformer implements Transform<CTResponsavelSeguro> {

    @Override
    public CTResponsavelSeguro read(final String codigo) {
        return CTResponsavelSeguro.valueOfCodigo(codigo);
    }

    @Override
    public String write(final CTResponsavelSeguro tipo) {
        return tipo.getCodigo();
    }
}