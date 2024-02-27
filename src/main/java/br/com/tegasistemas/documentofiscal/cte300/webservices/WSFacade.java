package br.com.tegasistemas.documentofiscal.cte300.webservices;

import br.com.tegasistemas.documentofiscal.cte300.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte300.classes.consultastatusservico.CTeConsStatServRet;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLote;
import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.CTeEnvioLoteRetornoDados;
import br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.consulta.CTeConsultaRecLoteRet;
import br.com.tegasistemas.documentofiscal.cte300.classes.evento.cancelamento.CTeRetornoCancelamento;
import br.com.tegasistemas.documentofiscal.cte300.classes.nota.consulta.CTeNotaConsultaRetorno;
import br.com.tegasistemas.documentofiscal.utils.DFSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public class WSFacade {

    private final WSStatusConsulta wsStatusConsulta;
    private final WSRecepcaoLote wsRecepcaoLote;
    private final WSNotaConsulta wsNotaConsulta;
    private final WSCancelamento wsCancelamento;
    private final WSInutilizacao wsInutilizacao;
    private final WSCartaCorrecao wsCartaCorrecao;

    private final WSRecepcaoLoteRetorno wsRecepcaoLoteRetorno;

    public WSFacade(final CTeConfig config) throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        Protocol.registerProtocol("https", new Protocol("https", new DFSocketFactory(config), 443));
        this.wsStatusConsulta = new WSStatusConsulta(config);
        this.wsRecepcaoLote = new WSRecepcaoLote(config);
        this.wsRecepcaoLoteRetorno = new WSRecepcaoLoteRetorno(config);
        this.wsNotaConsulta = new WSNotaConsulta(config);
        this.wsCancelamento = new WSCancelamento(config);
        this.wsInutilizacao = new WSInutilizacao(config);
        this.wsCartaCorrecao = new WSCartaCorrecao(config);
    }

    /**
     * Faz a consulta de status responsavel pela UF
     *
     * @param uf uf UF que deseja consultar o status do sefaz responsavel
     * @return dados da consulta de status retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    public CTeConsStatServRet consultaStatus(final DFUnidadeFederativa uf) throws Exception {
        return this.wsStatusConsulta.consultaStatus(uf);
    }

    /**
     * Faz o envio do lote para a SEFAZ
     *
     * @param cteRecepcao a ser eviado para a SEFAZ
     * @return dados do retorno do envio do lote e o xml assinado
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    public CTeEnvioLoteRetornoDados envioRecepcaoLote(CTeEnvioLote cteRecepcao) throws Exception {
        return this.wsRecepcaoLote.envioRecepcao(cteRecepcao);
    }

    /**
     * Faz a consulta do processamento do lote na SEFAZ
     *
     * @param numRecibo do recebimento do lote
     * @return dados da consulta do processamento do lote
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    public CTeConsultaRecLoteRet consultaEnvioRecepcaoLote(String numRecibo) throws Exception {
        return this.wsRecepcaoLoteRetorno.consultaLote(numRecibo);
    }

    /**
     * Faz a consulta do CTe
     *
     * @param chaveDeAcesso chave de acesso do cte
     * @return dados da consulta da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    public CTeNotaConsultaRetorno consultaNota(final String chaveDeAcesso) throws Exception {
        return this.wsNotaConsulta.consultaNota(chaveDeAcesso);
    }

    public String consultaNotaString(final String chaveDeAcesso) throws Exception {
        return this.wsNotaConsulta.consultaNotaString(chaveDeAcesso);
    }

    public String consultaNotaChave(final String chaveDeAcesso) throws Exception {
        return this.wsNotaConsulta.consultaNotaChave(chaveDeAcesso);
    }

    /**
     * Faz o cancelamento do CTe
     *
     * @param chave           chave de acesso da nota
     * @param numeroProtocolo numero do protocolo da nota
     * @param motivo          motivo do cancelamento
     * @return dados do cancelamento da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    public CTeRetornoCancelamento cancelaNota(final String chave, final String numeroProtocolo, final String motivo) throws Exception {
        return this.wsCancelamento.cancelaNota(chave, numeroProtocolo, motivo);
    }

    public String cancelaNotaString(final String chave, final String numeroProtocolo, final String motivo) throws Exception {
        return this.wsCancelamento.cancelaNotaString(chave, numeroProtocolo, motivo);
    }

    /**
     * Faz o cancelamento da nota com evento ja assinado
     * ATENCAO: Esse metodo deve ser utilizado para assinaturas A3
     *
     * @param chave             chave de acesso da nota
     * @param eventoAssinadoXml evento ja assinado em formato XML
     * @return dados do cancelamento da nota retornado pelo webservice
     * @throws Exception caso nao consiga gerar o xml ou problema de conexao com o sefaz
     */
    public CTeRetornoCancelamento cancelaNotaAssinada(final String chave, final String eventoAssinadoXml) throws Exception {
        return this.wsCancelamento.cancelaNotaAssinada(chave, eventoAssinadoXml);
    }

    /**
     * Realiza a inutilização da CTe
     */
    public String inutilizaCte(String id, String chaveAcesso, String xServ, String cteInit, String cteFinal, String justificativa, String ano, String mod, String serie) throws Exception {
        return this.wsInutilizacao.inutilizaCte(id, chaveAcesso, xServ, cteInit, cteFinal, justificativa, ano, mod, serie);
    }

    /**
     * Realiza o envio da carta de correção
     *
     * @param chaveAcesso
     * @param numeroProtocolo
     * @param grupoAlterado
     * @param campoAlterado
     * @param valorAlterado
     * @param nroItemAlterado
     * @param condicaoUso
     * @return
     * @throws Exception
     */
    public String correcaoCteString(String chaveAcesso, String numeroProtocolo, String grupoAlterado, String campoAlterado, String valorAlterado, String nroItemAlterado, final String condicaoUso) throws Exception {
        return this.wsCartaCorrecao.corrigeCteString(chaveAcesso, numeroProtocolo, grupoAlterado, campoAlterado, valorAlterado, nroItemAlterado, condicaoUso);
    }
}