package br.com.tegasistemas.documentofiscal.cte400.webservices;

import br.com.tegasistemas.documentofiscal.cte.CTeConfig;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeEvento;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.CTeEventoRetorno;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.cartacorrecao.CTeEnviaEventoCartaCorrecao;
import br.com.tegasistemas.documentofiscal.cte400.classes.evento.cartacorrecao.CTeInformacaoCartaCorrecao;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.validadores.DFXMLValidador;
import br.com.tegasistemas.documentofiscal.DFModelo;
import org.apache.axiom.om.OMElement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class WSCartaCorrecao extends WSRecepcaoEvento {
    private static final String DESCRICAO_EVENTO = "Carta de Correcao";
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("4.00");
    private static final String EVENTO_CARTA_DE_CORRECAO = "110110";
    private static final List<DFModelo> modelosPermitidos = Arrays.asList(DFModelo.CTE, DFModelo.CTeOS);

    private static String xmlCorrecao;

    WSCartaCorrecao(final CTeConfig config) {
        super(config, modelosPermitidos);
    }

    CTeEventoRetorno corrigeNotaAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        final OMElement omElementResult = super.efetuaEvento(eventoAssinadoXml, chaveAcesso, VERSAO_LEIAUTE);
        return this.config.getPersister().read(CTeEventoRetorno.class, omElementResult.toString());
    }

    CTeEventoRetorno corrigeNota(final String chaveAcesso, String grupoAlterado, String campoAlterado, String valorAlterado, String numeroItemAlterado, int sequencialEvento) throws Exception {
        final CTeInformacaoCartaCorrecao correcao = new CTeInformacaoCartaCorrecao();
        correcao.setGrupoAlterado(grupoAlterado);
        correcao.setCampoAlterado(campoAlterado);
        correcao.setValorAlterado(valorAlterado);
        if(Integer.parseInt(numeroItemAlterado) > 1) {
            correcao.setNumeroItemAlterado(numeroItemAlterado.replace(" ", ""));
        } else {
            correcao.setNumeroItemAlterado("0"+numeroItemAlterado.replace(" ", ""));
        }

        return corrigeNota(chaveAcesso, Collections.singletonList(correcao), sequencialEvento);
    }

    String corrigeNotaXML(final String chaveAcesso, String grupoAlterado, String campoAlterado, String valorAlterado, String numeroItemAlterado, int sequencialEvento) throws Exception {
        final CTeInformacaoCartaCorrecao correcao = new CTeInformacaoCartaCorrecao();
        correcao.setGrupoAlterado(grupoAlterado);
        correcao.setCampoAlterado(campoAlterado);
        correcao.setValorAlterado(valorAlterado);
        if(Integer.parseInt(numeroItemAlterado) > 1) {
            correcao.setNumeroItemAlterado(numeroItemAlterado.replace(" ", ""));
        } else {
            correcao.setNumeroItemAlterado("0"+numeroItemAlterado.replace(" ", ""));
        }

        CTeEventoRetorno c = corrigeNota(chaveAcesso, Collections.singletonList(correcao), sequencialEvento);

        return c.toString() + ";" + xmlCorrecao;
    }

    CTeEventoRetorno corrigeNota(final String chaveAcesso, List<CTeInformacaoCartaCorrecao> correcoes, int sequencialEvento) throws Exception {
        final String xmlAssinado = this.getXmlAssinado(chaveAcesso, correcoes, sequencialEvento);
        xmlCorrecao = xmlAssinado;

        return corrigeNotaAssinada(chaveAcesso, xmlAssinado);
    }

    String getXmlAssinado(final String chave, List<CTeInformacaoCartaCorrecao> correcoes, int sequencialEvento) throws Exception {
        final String xml = this.gerarDadosCartaCorrecao(chave, correcoes, sequencialEvento).toString();
        return new DFAssinaturaDigital(this.config).assinarDocumento(xml);
    }

    private CTeEvento gerarDadosCartaCorrecao(final String chaveAcesso, List<CTeInformacaoCartaCorrecao> correcoes, int sequencialEvento) throws Exception {
        final CTeEnviaEventoCartaCorrecao cartaCorrecao = new CTeEnviaEventoCartaCorrecao();
        cartaCorrecao.setDescricaoEvento(DESCRICAO_EVENTO);
        cartaCorrecao.setCorrecoes(correcoes);
        cartaCorrecao.setCondicaoUso("A Carta de Correcao e disciplinada pelo Art. 58-B do CONVENIO/SINIEF 06/89: Fica permitida a utilizacao de carta de correcao, para regularizacao de erro ocorrido na emissao de documentos fiscais relativos a prestacao de servico de transporte, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da prestacao;II - a correcao de dados cadastrais que implique mudanca do emitente, tomador, remetente ou do destinatario;III - a data de emissao ou de saida.");

        DFXMLValidador.validaEventoCartaCorrecaoCTe400(cartaCorrecao.toString());

        return super.gerarEvento(chaveAcesso, VERSAO_LEIAUTE, cartaCorrecao, EVENTO_CARTA_DE_CORRECAO, null, sequencialEvento);
    }

    private String xml;
}
