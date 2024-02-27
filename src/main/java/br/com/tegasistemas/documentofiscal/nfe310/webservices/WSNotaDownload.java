package br.com.tegasistemas.documentofiscal.nfe310.webservices;

import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.nfe.NFeConfig;
import br.com.tegasistemas.documentofiscal.nfe310.classes.NFAutorizador31;
import br.com.tegasistemas.documentofiscal.nfe310.classes.evento.downloadnf.NFDownloadNFe;
import br.com.tegasistemas.documentofiscal.nfe310.webservices.downloadnf.NfeDownloadNFStub;
import br.com.tegasistemas.documentofiscal.nfe310.classes.evento.downloadnf.NFDownloadNFeRetorno;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import java.math.BigDecimal;
import java.rmi.RemoteException;

class WSNotaDownload implements DFLog {
    
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("1.00");
    private static final String NOME_SERVICO = "DOWNLOAD NFE";
    private final NFeConfig config;
    
    WSNotaDownload(final NFeConfig config) {
        this.config = config;
    }
    
    NFDownloadNFeRetorno downloadNota(final String cnpj, final String chave) throws Exception {
        final OMElement omElementConsulta = AXIOMUtil.stringToOM(this.gerarDadosDownloadNF(cnpj, chave).toString());
        this.getLogger().debug(omElementConsulta.toString());
        
        final OMElement omElementRetorno = this.efetuaDownloadNF(omElementConsulta);
        this.getLogger().debug(omElementRetorno.toString());
        
        return this.config.getPersister().read(NFDownloadNFeRetorno.class, omElementRetorno.toString());
    }
    
    private OMElement efetuaDownloadNF(final OMElement omElementConsulta) throws RemoteException {
        final NfeDownloadNFStub.NfeCabecMsg cabec = new NfeDownloadNFStub.NfeCabecMsg();
        cabec.setCUF(this.config.getCUF().getCodigoIbge());
        cabec.setVersaoDados(WSNotaDownload.VERSAO_LEIAUTE.toPlainString());
        
        final NfeDownloadNFStub.NfeCabecMsgE cabecE = new NfeDownloadNFStub.NfeCabecMsgE();
        cabecE.setNfeCabecMsg(cabec);
        
        final NfeDownloadNFStub.NfeDadosMsg dados = new NfeDownloadNFStub.NfeDadosMsg();
        dados.setExtraElement(omElementConsulta);
        
        final NFAutorizador31 autorizador = NFAutorizador31.valueOfCodigoUF(this.config.getCUF());
        final String endpoint = autorizador.getNfeDownloadNF(this.config.getAmbiente());
        if (endpoint == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para DownloadNF, autorizador " + autorizador.name());
        }
        
        final NfeDownloadNFStub.NfeDownloadNFResult nfeDownloadNFResult = new NfeDownloadNFStub(endpoint).nfeDownloadNF(dados, cabecE);
        return nfeDownloadNFResult.getExtraElement();
    }
    
    private NFDownloadNFe gerarDadosDownloadNF(final String cnpj, final String chave) {
        final NFDownloadNFe nfDownloadNFe = new NFDownloadNFe();
        nfDownloadNFe.setVersao(WSNotaDownload.VERSAO_LEIAUTE.toPlainString());
        nfDownloadNFe.setAmbiente(this.config.getAmbiente());
        nfDownloadNFe.setServico(WSNotaDownload.NOME_SERVICO);
        nfDownloadNFe.setCnpj(cnpj);
        nfDownloadNFe.setChave(chave);
        return nfDownloadNFe;
    }
}
