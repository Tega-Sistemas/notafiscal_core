package br.com.tegasistemas.documentofiscal.nfe400.transformers;

import br.com.tegasistemas.documentofiscal.nfe400.classes.NFNotaInfoImpostoTributacaoICMS;
import org.simpleframework.xml.transform.Transform;

public class NFNotaInfoImpostoTributacaoICMSTransformer implements Transform<NFNotaInfoImpostoTributacaoICMS> {

    @Override
    public NFNotaInfoImpostoTributacaoICMS read(final String icms) {
        return NFNotaInfoImpostoTributacaoICMS.valueOfCodigo(icms);
    }

    @Override
    public String write(final NFNotaInfoImpostoTributacaoICMS icms) {
        return icms.getCodigo();
    }
}