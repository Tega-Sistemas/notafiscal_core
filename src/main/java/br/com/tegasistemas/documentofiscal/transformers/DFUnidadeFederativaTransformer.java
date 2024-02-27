package br.com.tegasistemas.documentofiscal.transformers;

import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import org.simpleframework.xml.transform.Transform;

public class DFUnidadeFederativaTransformer implements Transform<DFUnidadeFederativa> {

    @Override
    public DFUnidadeFederativa read(final String codigo) {
        return DFUnidadeFederativa.valueOfCodigo(codigo);
    }

    @Override
    public String write(final DFUnidadeFederativa unidadeFederativa) {
        return unidadeFederativa.getCodigoIbge();
    }
}