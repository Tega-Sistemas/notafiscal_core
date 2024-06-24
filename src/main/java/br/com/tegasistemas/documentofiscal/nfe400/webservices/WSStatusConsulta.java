package br.com.tegasistemas.documentofiscal.nfe400.webservices;

import br.com.tegasistemas.documentofiscal.nfe400.classes.NFAutorizador400;
import br.com.tegasistemas.documentofiscal.nfe400.classes.statusservico.consulta.NFStatusServicoConsulta;
import br.com.tegasistemas.documentofiscal.nfe400.classes.statusservico.consulta.NFStatusServicoConsultaRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.webservices.statusservico.consulta.NfeStatusServico4Stub;
import br.com.tegasistemas.documentofiscal.nfe.NFTipoEmissao;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.nfe.NFeConfig;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.rmi.RemoteException;

class WSStatusConsulta implements DFLog {
    
    private static final String NOME_SERVICO = "STATUS";
    private final NFeConfig config;
    
    WSStatusConsulta(final NFeConfig config) {
        this.config = config;
    }
    
    NFStatusServicoConsultaRetorno consultaStatus(final DFUnidadeFederativa uf, final DFModelo modelo, final NFTipoEmissao tpEmis) throws Exception {
        final OMElement omElementConsulta = AXIOMUtil.stringToOM(this.gerarDadosConsulta(uf).toString());
        this.getLogger().debug(omElementConsulta.toString());

        final OMElement omElementResult = this.efetuaConsultaStatus(omElementConsulta, uf, modelo, tpEmis);
        this.getLogger().debug(omElementResult.toString());

        return this.config.getPersister().read(NFStatusServicoConsultaRetorno.class, omElementResult.toString());
    }
    
    private NFStatusServicoConsulta gerarDadosConsulta(final DFUnidadeFederativa unidadeFederativa) {
        final NFStatusServicoConsulta consStatServ = new NFStatusServicoConsulta();
        consStatServ.setUf(unidadeFederativa);
        consStatServ.setAmbiente(this.config.getAmbiente());
        consStatServ.setVersao(this.config.getVersao());
        consStatServ.setServico(WSStatusConsulta.NOME_SERVICO);
        return consStatServ;
    }
    
    private OMElement efetuaConsultaStatus(final OMElement omElement, final DFUnidadeFederativa unidadeFederativa, final DFModelo modelo, final NFTipoEmissao tpEmis) throws RemoteException {
        final NfeStatusServico4Stub.NfeDadosMsg dados = new NfeStatusServico4Stub.NfeDadosMsg();
        dados.setExtraElement(omElement);

        final NFAutorizador400 autorizador = DFModelo.NFCE.equals(modelo) ?
                NFAutorizador400.valueOfCodigoUF (unidadeFederativa) :
                NFAutorizador400.valueOfTipoEmissao (tpEmis, unidadeFederativa);

        final String endpoint = DFModelo.NFCE.equals(modelo) ?
                autorizador.getNfceStatusServico(this.config.getAmbiente()) :
                autorizador.getNfeStatusServico(this.config.getAmbiente());

        if (endpoint == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para StatusServico " + modelo.name() + ", autorizador " + autorizador.name() + ", UF " + unidadeFederativa.name());
        }
        return new NfeStatusServico4Stub(endpoint, config).nfeStatusServicoNF(dados).getExtraElement();
    }

}
