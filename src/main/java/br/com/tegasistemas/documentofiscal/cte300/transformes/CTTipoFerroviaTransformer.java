package br.com.tegasistemas.documentofiscal.cte300.transformes;

import br.com.tegasistemas.documentofiscal.cte300.classes.CTTipoFerrovia;
import org.simpleframework.xml.transform.Transform;

public class CTTipoFerroviaTransformer implements Transform<CTTipoFerrovia> {

	@Override
    public CTTipoFerrovia read(String arg0) {
		return CTTipoFerrovia.valueOfCodigo(arg0);
	}

	@Override
    public String write(CTTipoFerrovia arg0) {
		return arg0.getCodigo();
	}
}