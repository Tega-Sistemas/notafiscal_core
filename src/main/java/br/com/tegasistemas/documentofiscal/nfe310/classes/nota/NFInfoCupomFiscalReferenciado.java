package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

public class NFInfoCupomFiscalReferenciado extends DFBase {
    private static final long serialVersionUID = -8337973110998173462L;
    
    @Element(name = "mod")
    private String modeloDocumentoFiscal;
    
    @Element(name = "nECF")
    private String numeroOrdemSequencialECF;
    
    @Element(name = "nCOO")
    private String numeroContadorOrdemOperacao;

    public void setModeloDocumentoFiscal(final String modeloDocumentoFiscal) {
        StringValidador.exatamente2(modeloDocumentoFiscal, "Modelo Documento Fiscal");
        this.modeloDocumentoFiscal = modeloDocumentoFiscal;
    }

    public void setNumeroOrdemSequencialECF(final String numeroOrdemSequencialECF) {
        StringValidador.exatamente3(numeroOrdemSequencialECF, "Numero Ordem Sequencial ECF");
        this.numeroOrdemSequencialECF = numeroOrdemSequencialECF;
    }

    public void setNumeroContadorOrdemOperacao(final String numeroContadorOrdemOperacao) {
        StringValidador.exatamente6(numeroContadorOrdemOperacao, "Numero Contador Ordem Operacao");
        this.numeroContadorOrdemOperacao = numeroContadorOrdemOperacao;
    }

    public String getModeloDocumentoFiscal() {
        return this.modeloDocumentoFiscal;
    }

    public String getNumeroContadorOrdemOperacao() {
        return this.numeroContadorOrdemOperacao;
    }

    public String getNumeroOrdemSequencialECF() {
        return this.numeroOrdemSequencialECF;
    }
}