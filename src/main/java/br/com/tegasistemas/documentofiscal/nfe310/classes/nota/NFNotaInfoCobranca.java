package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import java.util.List;

import br.com.tegasistemas.documentofiscal.validadores.ListValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import br.com.tegasistemas.documentofiscal.DFBase;

public class NFNotaInfoCobranca extends DFBase {
    private static final long serialVersionUID = -2471735975201108151L;

    @Element(name = "fat", required = false)
    private NFNotaInfoFatura fatura;

    @ElementList(entry = "dup", inline = true, required = false)
    private List<NFNotaInfoDuplicata> duplicatas;

    public void setFatura(final NFNotaInfoFatura fatura) {
        this.fatura = fatura;
    }

    public void setDuplicatas(final List<NFNotaInfoDuplicata> duplicatas) {
        ListValidador.tamanho120(duplicatas, "Duplicatas");
        this.duplicatas = duplicatas;
    }

    public NFNotaInfoFatura getFatura() {
        return this.fatura;
    }

    public List<NFNotaInfoDuplicata> getDuplicatas() {
        return this.duplicatas;
    }
}