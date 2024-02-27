package br.com.tegasistemas.documentofiscal.nfe400.classes.util;

import br.com.tegasistemas.documentofiscal.utils.DFPersister;
import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNota;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNotaProcessada;
import org.apache.commons.lang3.StringUtils;

import static br.com.tegasistemas.documentofiscal.nfe400.utils.qrcode20.NFGeraQRCode20.createHash;

public class GetURLQrCode {
    public String getQRCode(String xml, String Ambiente, String codContribuinte,int codContribuinteId) {
        String qrcode = null;

        try {
            NFNotaProcessada n = new DFPersister().read(NFNotaProcessada.class, xml);
            NFNota nota = n.getNota();
            String url = getUrlQRCode(Ambiente,codContribuinte,nota);
            final StringBuilder parametros = new StringBuilder();
            parametros.append(nota.getInfo().getChaveAcesso()).append("|");     // Chave de Acesso da NFC-e
            parametros.append("2").append("|");                                 // Versao do QRCode
            parametros.append(Ambiente).append("|");                            // Tipo de Ambiente Homolog / Producao
            parametros.append(codContribuinteId);                               // Identificador do CSC – Codigo de Seguranca do Contribuinte no Banco de Dados da SEFAZ
            return url + "?p=" + parametros.toString() + "|" + createHash(parametros.toString(), codContribuinte);
        } catch (Exception e) {
            qrcode = e.getMessage();
        }
        return qrcode;
    }

    public String getUrlQRCode(String ambiente, String codContribuinte, NFNota nota) {
        DFAmbiente amb;
        if (ambiente == "1"){
            amb = DFAmbiente.PRODUCAO;
        } else {
            amb = DFAmbiente.HOMOLOGACAO;
        }
        String url = amb.equals(DFAmbiente.PRODUCAO) ? nota.getInfo().getIdentificacao().getUf().getQrCodeProducao() : nota.getInfo().getIdentificacao().getUf().getQrCodeHomologacao();

        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("URL para consulta do QRCode nao informada para uf " + nota.getInfo().getIdentificacao().getUf() + "!");
        }

        if (StringUtils.isBlank(codContribuinte)) {
            throw new IllegalArgumentException("CSC nao informado nas configuracoes!");
        }

        return url;
    }
}
