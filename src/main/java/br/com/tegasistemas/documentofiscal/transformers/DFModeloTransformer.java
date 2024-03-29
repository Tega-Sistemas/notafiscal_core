package br.com.tegasistemas.documentofiscal.transformers;

import br.com.tegasistemas.documentofiscal.DFModelo;
import org.simpleframework.xml.transform.Transform;

public class DFModeloTransformer implements Transform<DFModelo> {

    @Override
    public DFModelo read(final String codigo) {
        return DFModelo.valueOfCodigo(codigo);
    }

    @Override
    public String write(final DFModelo tipo) {
        return tipo.getCodigo();
    }
}