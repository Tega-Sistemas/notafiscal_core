package br.com.tegasistemas.documentofiscal.gnre200;

import br.com.tegasistemas.documentofiscal.DFConfig;
import br.com.tegasistemas.documentofiscal.nfe.NFTipoEmissao;

public abstract class GnreConfig extends DFConfig {

    public static final String NAMESPACE = "http://www.portalfiscal.inf.br/nfe";
    private String myCertificadoPath;
    private String myCacertsPath;
    private String myCertificadoSenha;
    private String myCacertSenha;

    /**
     * ID de contribuinte, somente para NFCe.
     *
     * @return ID do contribuinte.
     */
    public Integer getCodigoSegurancaContribuinteID() {
        return null;
    }

    /**
     * Codigo de seguranca do contribuinte, com 36 caracteres, somente para NFCe.
     *
     * @return Codigo de seguranca do contribuinte.
     */
    public String getCodigoSegurancaContribuinte() {
        return null;
    }

    /**
     * Tipo da emissao das notas (se normal ou em contingencia).
     *
     * @return Tipo da emissao das notas.
     */
    public NFTipoEmissao getTipoEmissao() {
        return NFTipoEmissao.EMISSAO_NORMAL;
    }

    /**
     * Retorna a versão do XML na receita.
     *
     * @return versão nota fiscal
     */
    public String getVersao() {
        return "4.00";
    }

    public String getMyCertificadoPath() {
        return myCertificadoPath;
    }

    public void setMyCertificadoPath(String myCertificadoPath) {
        this.myCertificadoPath = myCertificadoPath;
    }

    public String getMyCacertsPath() {
        return myCacertsPath;
    }

    public void setMyCacertsPath(String myCacertsPath) {
        this.myCacertsPath = myCacertsPath;
    }

    public String getMyCertificadoSenha() {
        return myCertificadoSenha;
    }

    public void setMyCertificadoSenha(String myCertificadoSenha) {
        this.myCertificadoSenha = myCertificadoSenha;
    }

    public String getMyCacertSenha() {
        return myCacertSenha;
    }

    public void setMyCacertSenha(String myCacertSenha) {
        this.myCacertSenha = myCacertSenha;
    }
}