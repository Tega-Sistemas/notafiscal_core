package br.com.tegasistemas.documentofiscal.nfe400.transformers;

import br.com.tegasistemas.documentofiscal.nfe400.classes.cadastro.NFIndicadorContribuinteNFe;
import org.simpleframework.xml.transform.Transform;

public class NFIndicadorContribuinteNFeTransformer implements Transform<NFIndicadorContribuinteNFe> {

    @Override
    public NFIndicadorContribuinteNFe read(final String codigoIndicador) {
        return NFIndicadorContribuinteNFe.valueOfCodigo(Integer.parseInt(codigoIndicador));
    }

    @Override
    public String write(final NFIndicadorContribuinteNFe indicador) {
        return String.valueOf(indicador.getCodigo());
    }
}
