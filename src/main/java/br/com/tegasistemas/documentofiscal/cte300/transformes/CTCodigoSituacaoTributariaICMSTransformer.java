package br.com.tegasistemas.documentofiscal.cte300.transformes;

import br.com.tegasistemas.documentofiscal.cte300.classes.CTCodigoSituacaoTributariaICMS;
import org.simpleframework.xml.transform.Transform;

public class CTCodigoSituacaoTributariaICMSTransformer implements Transform<CTCodigoSituacaoTributariaICMS> {
    
    @Override
    public CTCodigoSituacaoTributariaICMS read(String arg0) {
        return CTCodigoSituacaoTributariaICMS.valueOfCodigo(arg0);
    }
    
    @Override
    public String write(CTCodigoSituacaoTributariaICMS arg0) {
        return arg0.getCodigo();
    }
}
