package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import java.math.BigInteger;

import br.com.tegasistemas.documentofiscal.validadores.DFBigIntegerValidador;
import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;

public class NFNotaInfoItemDetalheExportacao extends DFBase {
    private static final long serialVersionUID = 8265188954413940773L;

    @Element(name = "nDraw", required = false)
    private BigInteger atoConcessorioDrawback;

    @Element(name = "exportInd", required = false)
    private NFNotaInfoItemExportacaoIndireta exportacaoIndireta;

    public void setNumeroAtoConcessorioDrawback(final BigInteger numeroAtoConcessorioDrawback) {
        DFBigIntegerValidador.tamanho11(numeroAtoConcessorioDrawback, "Numero Ato Concessorio");
        this.atoConcessorioDrawback = numeroAtoConcessorioDrawback;
    }

    public void setExportacaoIndireta(final NFNotaInfoItemExportacaoIndireta exportacaoIndireta) {
        this.exportacaoIndireta = exportacaoIndireta;
    }

    public BigInteger getAtoConcessorioDrawback() {
        return this.atoConcessorioDrawback;
    }

    public NFNotaInfoItemExportacaoIndireta getExportacaoIndireta() {
        return this.exportacaoIndireta;
    }
}