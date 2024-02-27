package br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio;

import br.com.tegasistemas.documentofiscal.DFBase;

public class NFLoteEnvioRetornoDadosNew extends DFBase {

	private static final long serialVersionUID = 2276661581444740391L;

	private final NFLoteEnvioRetorno retorno;
    private final String loteAssinado;

	public NFLoteEnvioRetornoDadosNew(NFLoteEnvioRetorno retorno, String loteAssinado) {
		this.retorno = retorno;
		this.loteAssinado = loteAssinado;
	}


	public NFLoteEnvioRetorno getRetorno() {
		return retorno;
	}

	public String getLoteAssinado() {
		return loteAssinado;
	}
	
}
