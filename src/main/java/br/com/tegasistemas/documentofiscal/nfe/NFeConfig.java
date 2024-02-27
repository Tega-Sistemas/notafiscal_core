package br.com.tegasistemas.documentofiscal.nfe;

import br.com.tegasistemas.documentofiscal.DFConfig;

/**
 * Configuração basica do sistema de notas fiscais.
 */
public abstract class NFeConfig extends DFConfig {

    public NFTipoEmissao tpEmis;
    public static final String NAMESPACE = "http://www.portalfiscal.inf.br/nfe";

    /**
     * ID de contribuinte, somente para NFCe.
     * @return ID do contribuinte.
     */
    public Integer getCodigoSegurancaContribuinteID() {
        return null;
    }

    /**
     * Codigo de seguranca do contribuinte, com 36 caracteres, somente para NFCe.
     * @return Codigo de seguranca do contribuinte.
     */
    public String getCodigoSegurancaContribuinte() {
        return null;
    }

    /**
     * Tipo da emissao das notas (se normal ou em contingencia).
     * @return Tipo da emissao das notas (configurada e caso vazia retorna a emissão normal por padrão)
     */
    public NFTipoEmissao getTipoEmissao() {
        return (tpEmis == null) ? NFTipoEmissao.EMISSAO_NORMAL : tpEmis;
    }

    /**
     * Tipo da emissao das notas (se normal ou em contingencia).
     * Seta o tipo de emissão da NF em questão
     */
    public void setTipoEmissao(NFTipoEmissao tpEmis) {
        this.tpEmis = tpEmis;
    }

    /**
     * Retorna a versão do XML na receita.
     * @return versão nota fiscal
     */
    public String getVersao() {
        return "4.00";
    }
}
