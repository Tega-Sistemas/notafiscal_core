package br.com.tegasistemas.documentofiscal.nfe400.transformers;

import br.com.tegasistemas.documentofiscal.nfe400.classes.cadastro.NFIndicadorContribuinteCTe;
import org.simpleframework.xml.transform.Transform;

public class NFIndicadorContribuinteCTTransformer implements Transform<NFIndicadorContribuinteCTe> {

    @Override
    public NFIndicadorContribuinteCTe read(final String codigo) {
        return NFIndicadorContribuinteCTe.valueOfCodigo(Integer.parseInt(codigo));
    }

    @Override
    public String write(final NFIndicadorContribuinteCTe indicador) {
        return String.valueOf(indicador.getCodigo());
    }
}