package br.com.tegasistemas.documentofiscal.nfe310.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import org.simpleframework.xml.Element;

public class NFNotaInfoExportacao extends DFBase {
    private static final long serialVersionUID = 3726297203738653822L;
    
    @Element(name = "UFSaidaPais")
    private String ufEmbarqueProduto;
    
    @Element(name = "xLocExporta")
    private String localEmbarqueProdutos;

    @Element(name = "xLocDespacho", required = false)
    private String localDespachoProdutos;

    public void setUfEmbarqueProduto(final DFUnidadeFederativa ufEmbarqueProduto) {
        this.ufEmbarqueProduto = ufEmbarqueProduto.getCodigo();
    }

    public void setLocalEmbarqueProdutos(final String localEmbarqueProdutos) {
        StringValidador.tamanho60(localEmbarqueProdutos, "Local Embarque Produtos");
        this.localEmbarqueProdutos = localEmbarqueProdutos;
    }

    public void setLocalDespachoProdutos(final String localDespachoProdutos) {
        StringValidador.tamanho60(localDespachoProdutos, "Local Despacho Produtos");
        this.localDespachoProdutos = localDespachoProdutos;
    }

    public String getLocalDespachoProdutos() {
        return this.localDespachoProdutos;
    }

    public String getLocalEmbarqueProdutos() {
        return this.localEmbarqueProdutos;
    }

    public String getUfEmbarqueProduto() {
        return this.ufEmbarqueProduto;
    }
}