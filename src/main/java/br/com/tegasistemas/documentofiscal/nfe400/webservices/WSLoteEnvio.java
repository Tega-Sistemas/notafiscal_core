package br.com.tegasistemas.documentofiscal.nfe400.webservices;

import br.com.tegasistemas.documentofiscal.nfe400.classes.NFAutorizador400;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvio;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNotaInfoSuplementar;
import br.com.tegasistemas.documentofiscal.nfe400.webservices.gerado.NFeAutorizacao4Stub;
import br.com.tegasistemas.documentofiscal.utils.DFAssinaturaDigital;
import br.com.tegasistemas.documentofiscal.validadores.DFXMLValidador;
import br.com.tegasistemas.documentofiscal.DFLog;
import br.com.tegasistemas.documentofiscal.DFModelo;
import br.com.tegasistemas.documentofiscal.nfe.NFTipoEmissao;
import br.com.tegasistemas.documentofiscal.nfe.NFeConfig;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvioRetorno;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvioRetornoDados;
import br.com.tegasistemas.documentofiscal.nfe400.classes.lote.envio.NFLoteEnvioRetornoDadosNew;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNota;
import br.com.tegasistemas.documentofiscal.nfe400.utils.NFGeraChave;
import br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCode20;
import br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCodeContingenciaOffline20;
import br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCodeEmissaoNormal20;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.commons.lang3.StringUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.Iterator;

class WSLoteEnvio implements DFLog {

    private static final String NFE_ELEMENTO = "NFe";
    private final NFeConfig config;

    WSLoteEnvio(final NFeConfig config) {
        this.config = config;
    }

    NFLoteEnvioRetorno enviaLoteAssinado(final String loteAssinadoXml, final DFModelo modelo) throws Exception {
        return this.comunicaLote(loteAssinadoXml, modelo);
    }

    NFLoteEnvioRetornoDados enviaLote(final NFLoteEnvio lote, boolean validarXML) throws Exception {
        final NFLoteEnvio loteAssinado = this.getLoteAssinado(lote);
        final NFLoteEnvioRetorno loteEnvioRetorno = this.comunicaLote(loteAssinado.toString(),
                loteAssinado.getNotas().get(0).getInfo().getIdentificacao().getModelo(), validarXML);
        return new NFLoteEnvioRetornoDados(loteEnvioRetorno, loteAssinado);
    }

    NFLoteEnvioRetornoDadosNew enviaLoteXML(final String lote, final DFModelo modelo, boolean validarXML) throws Exception {
        final String loteAssinado = this.getLoteAssinadoString(lote);
        final NFLoteEnvioRetorno loteEnvioRetorno = this.comunicaLote(loteAssinado.toString(), modelo, validarXML);
        return new NFLoteEnvioRetornoDadosNew(loteEnvioRetorno, loteAssinado);
    }

    NFLoteEnvioRetornoDados enviaLote(final NFLoteEnvio lote) throws Exception {
        return this.enviaLote(lote, true);
    }

    String getLoteAssinadoString(final String lote) throws Exception {
        final String documentoAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(lote);
        final String loteAssinado = documentoAssinado;
        return loteAssinado;
    }

    NFLoteEnvio getLoteAssinado(final NFLoteEnvio lote) throws Exception {
        // adiciona a chave e o dv antes de assinar
        for (final NFNota nota : lote.getNotas()) {
            final NFGeraChave geraChave = new NFGeraChave(nota);
            nota.getInfo().getIdentificacao().setCodigoRandomico(StringUtils.defaultIfBlank(
                    nota.getInfo().getIdentificacao().getCodigoRandomico(), geraChave.geraCodigoRandomico()));
            nota.getInfo().getIdentificacao().setDigitoVerificador(geraChave.getDV());
            nota.getInfo().setIdentificador(geraChave.getChaveAcesso());
        }

        final String documentoAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(lote.toString());
        final NFLoteEnvio loteAssinado = this.config.getPersister().read(NFLoteEnvio.class, documentoAssinado);

        //Contagem para controle de qtde de NFCe e NFe
        int qtdNF = 0, qtdNFC = 0;
        for (final NFNota nota : loteAssinado.getNotas()) {
            switch (nota.getInfo().getIdentificacao().getModelo()) {
                case NFE:
                    qtdNF++;
                    break;
                case NFCE:
                    NFGeraQRCode20 geraQRCode = getNfGeraQRCode20(nota);
                    nota.setInfoSuplementar(new NFNotaInfoSuplementar());
                    nota.getInfoSuplementar().setUrlConsultaChaveAcesso(geraQRCode.urlConsultaChaveAcesso());
                    nota.getInfoSuplementar().setQrCode(geraQRCode.getQRCode());
                    qtdNFC++;
                    break;
                default:
                    throw new IllegalArgumentException(
                            String.format("Modelo de nota desconhecida: %s",
                                    nota.getInfo().getIdentificacao().getModelo())
                    );
            }
        }

        if ((qtdNF > 0) && (qtdNFC > 0)) {
            throw new IllegalArgumentException("Lote contendo notas de modelos diferentes!");
        }

        return loteAssinado;
    }

    private NFGeraQRCode20 getNfGeraQRCode20(NFNota nota) {
        if (NFTipoEmissao.EMISSAO_NORMAL.equals(nota.getInfo().getIdentificacao().getTipoEmissao())) {
            return new NFGeraQRCodeEmissaoNormal20(nota, this.config);
        } else if (NFTipoEmissao.CONTIGENCIA_OFFLINE.equals(nota.getInfo().getIdentificacao().getTipoEmissao())) {
            return new NFGeraQRCodeContingenciaOffline20(nota, this.config);
        } else {
            throw new IllegalArgumentException("QRCode 2.0 Tipo Emissao nao implementado: " + nota.getInfo().getIdentificacao().getTipoEmissao().getDescricao());
        }
    }

    private NFLoteEnvioRetorno comunicaLote(final String loteAssinadoXml, final DFModelo modelo, boolean validarXML) throws Exception {
        final NFeAutorizacao4Stub.NfeResultMsg autorizacaoLoteResult = comunicaLoteRaw(loteAssinadoXml, modelo, validarXML);
        final NFLoteEnvioRetorno loteEnvioRetorno = this.config.getPersister().read(NFLoteEnvioRetorno.class, autorizacaoLoteResult.getExtraElement().toString());
        this.getLogger().debug(loteEnvioRetorno.toString());
        return loteEnvioRetorno;
    }

    private NFLoteEnvioRetorno comunicaLote(final String loteAssinadoXml, final DFModelo modelo) throws Exception {
        return this.comunicaLote(loteAssinadoXml, modelo, true);
    }

    NFeAutorizacao4Stub.NfeResultMsg comunicaLoteRaw(final String loteAssinadoXml, final DFModelo modelo, boolean validarXML) throws Exception {

        if (validarXML) {
            // valida o lote assinado, para verificar se o xsd foi satisfeito, antes de
            // comunicar com a sefaz
            DFXMLValidador.validaLote400(loteAssinadoXml);
        }
        // envia o lote para a sefaz
        final OMElement omElement = this.nfeToOMElement(loteAssinadoXml);
        final NFeAutorizacao4Stub.NfeDadosMsg dados = new NFeAutorizacao4Stub.NfeDadosMsg();
        dados.setExtraElement(omElement);
        // define o tipo de emissao
        System.out.println("-----------------TIPO EMISSAO: " + this.config.getTipoEmissao());
        final NFAutorizador400 autorizador = NFAutorizador400.valueOfTipoEmissao(
                this.config.getTipoEmissao(),
                this.config.getCUF()
        );

        final String endpoint = DFModelo.NFE.equals(modelo) ?
                autorizador.getNfeAutorizacao(this.config.getAmbiente()) :
                autorizador.getNfceAutorizacao(this.config.getAmbiente());
        if (endpoint == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para Autorizacao " + modelo.name()
                    + ", autorizador " + autorizador.name());
        }
        return new NFeAutorizacao4Stub(endpoint).nfeAutorizacaoLote(dados);
    }

    private OMElement nfeToOMElement(final String documento) throws XMLStreamException {
        final XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_COALESCING, false);
        final XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(documento));
        final StAXOMBuilder builder = new StAXOMBuilder(reader);
        final OMElement ome = builder.getDocumentElement();
        final Iterator<?> children = ome.getChildrenWithLocalName(WSLoteEnvio.NFE_ELEMENTO);
        while (children.hasNext()) {
            final OMElement omElement = (OMElement) children.next();
            if ((omElement != null) && (WSLoteEnvio.NFE_ELEMENTO.equals(omElement.getLocalName()))) {
                omElement.addAttribute("xmlns", NFeConfig.NAMESPACE, null);
            }
        }
        return ome;
    }
}
