package br.com.tegasistemas.documentofiscal.nfe400.transformers;

import br.com.tegasistemas.documentofiscal.nfe400.classes.NFNotaInfoItemModalidadeBCICMS;
import org.simpleframework.xml.transform.Transform;

public class NFNotaInfoItemModalidadeBCICMSTransformer implements Transform<NFNotaInfoItemModalidadeBCICMS> {

    @Override
    public NFNotaInfoItemModalidadeBCICMS read(final String codigo) {
        return NFNotaInfoItemModalidadeBCICMS.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFNotaInfoItemModalidadeBCICMS modalidade) {
        return modalidade.getCodigo();
    }
}