package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.DFBigIntegerValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NFNotaInfoItemExportacaoIndireta extends DFBase {
    private static final long serialVersionUID = 2911935204123851926L;
    
    @Element(name = "nRE")
    private BigInteger numeroRegistroExportacao;
    
    @Element(name = "chNFe")
    private String chaveAcessoNFe;
    
    @Element(name = "qExport")
    private String quantidadeItemEfetivamenteExportado;

    public void setChaveAcessoNFe(final String chaveAcessoNFe) {
        StringValidador.exatamente44N(chaveAcessoNFe, "Chave de Acesso NFe");
        this.chaveAcessoNFe = chaveAcessoNFe;
    }

    public void setNumeroRegistroExportacao(final BigInteger numeroRegistroExportacao) {
        DFBigIntegerValidador.tamanho12(numeroRegistroExportacao, "Numero Registro Exportacao");
        this.numeroRegistroExportacao = numeroRegistroExportacao;
    }

    public void setQuantidadeItemEfetivamenteExportado(final BigDecimal quantidadeItemEfetivamenteExportado) {
        this.quantidadeItemEfetivamenteExportado = BigDecimalValidador.tamanho15comAte4CasasDecimais(quantidadeItemEfetivamenteExportado, "Quantidade Item Evetivamente Exportado");
    }

    public String getChaveAcessoNFe() {
        return this.chaveAcessoNFe;
    }

    public BigInteger getNumeroRegistroExportacao() {
        return this.numeroRegistroExportacao;
    }

    public String getQuantidadeItemEfetivamenteExportado() {
        return this.quantidadeItemEfetivamenteExportado;
    }
}