package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

public class NFNotaInfoInfIntermed extends DFBase {
    private static final long serialVersionUID = 8579628067567740408L;

    @Element(name = "CNPJ", required = false)
    private String CNPJ;

    @Element(name = "idCadIntTran", required = false)
    private String idCadIntTran;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getIdCadIntTran() {
        return idCadIntTran;
    }

    public void setIdCadIntTran(String idCadIntTran) {
        this.idCadIntTran = idCadIntTran;
    }
}