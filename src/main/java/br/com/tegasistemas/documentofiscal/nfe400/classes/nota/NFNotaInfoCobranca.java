package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import java.util.List;

import br.com.tegasistemas.documentofiscal.validadores.DFListValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import br.com.tegasistemas.documentofiscal.DFBase;

public class NFNotaInfoCobranca extends DFBase {
    private static final long serialVersionUID = -2471735975201108151L;

    @Element(name = "fat", required = false)
    private NFNotaInfoFatura fatura;

    @ElementList(entry = "dup", inline = true, required = false)
    private List<NFNotaInfoParcela> parcelas;

    public void setFatura(final NFNotaInfoFatura fatura) {
        this.fatura = fatura;
    }

    public void setParcelas(final List<NFNotaInfoParcela> parcelas) {
        DFListValidador.tamanho120(parcelas, "Parcelas");
        this.parcelas = parcelas;
    }

    public NFNotaInfoFatura getFatura() {
        return this.fatura;
    }

    public List<NFNotaInfoParcela> getParcelas() {
        return this.parcelas;
    }
}