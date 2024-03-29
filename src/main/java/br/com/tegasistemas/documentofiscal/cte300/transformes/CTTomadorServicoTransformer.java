package br.com.tegasistemas.documentofiscal.cte300.transformes;

import br.com.tegasistemas.documentofiscal.cte300.classes.CTTomadorServico;
import org.simpleframework.xml.transform.Transform;

public class CTTomadorServicoTransformer implements Transform<CTTomadorServico> {

	@Override
    public CTTomadorServico read(String arg0) {
		return CTTomadorServico.valueOfCodigo(arg0);
	}

	@Override
    public String write(CTTomadorServico arg0) {
		return arg0.getCodigo();
	}
}