package br.com.tegasistemas.documentofiscal.nfe310.transformers;

import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.NFNotaInfoItemIndicadorIncentivoFiscal;
import org.simpleframework.xml.transform.Transform;

public class NFNotaInfoItemIndicadorIncentivoFiscalTransformer implements Transform<NFNotaInfoItemIndicadorIncentivoFiscal> {

    @Override
    public NFNotaInfoItemIndicadorIncentivoFiscal read(final String codigo) {
        return NFNotaInfoItemIndicadorIncentivoFiscal.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFNotaInfoItemIndicadorIncentivoFiscal incentivoFiscal) {
        return incentivoFiscal.getCodigo();
    }
}