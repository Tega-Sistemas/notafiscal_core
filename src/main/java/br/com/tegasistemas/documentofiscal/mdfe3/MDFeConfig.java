package br.com.tegasistemas.documentofiscal.mdfe3;

import br.com.tegasistemas.documentofiscal.mdfe3.classes.def.MDFTipoEmissao;
import br.com.tegasistemas.documentofiscal.DFConfig;
import br.com.tegasistemas.documentofiscal.DFModelo;

/**
 * Created by Eldevan Nery Junior on 10/11/17.
 * Configuracao padrao do MDF-e.
 */
public abstract class MDFeConfig extends DFConfig {

    public static final String VERSAO = "3.00";
    public static final String NAMESPACE = "http://www.portalfiscal.inf.br/mdfe";

    public MDFTipoEmissao getTipoEmissao() {
        return MDFTipoEmissao.NORMAL;
    }

    @Override
    public DFModelo getModelo() {
        return DFModelo.MDFE;
    }
}
