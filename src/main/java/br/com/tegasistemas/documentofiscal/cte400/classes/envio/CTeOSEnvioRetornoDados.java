package br.com.tegasistemas.documentofiscal.cte400.classes.envio;

import br.com.tegasistemas.documentofiscal.cte400.classes.os.CTeOS;

public class CTeOSEnvioRetornoDados {

    private final CTeOSEnvioRetorno retorno;
    private final CTeOS loteAssinado;

	public CTeOSEnvioRetornoDados(CTeOSEnvioRetorno retorno, CTeOS loteAssinado) {
		this.retorno = retorno;
		this.loteAssinado = loteAssinado;
	}

	public CTeOSEnvioRetorno getRetorno() {
		return retorno;
	}

	public CTeOS getLoteAssinado() {
		return loteAssinado;
	}
}
