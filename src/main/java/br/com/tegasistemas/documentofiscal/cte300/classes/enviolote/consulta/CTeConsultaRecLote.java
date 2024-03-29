package br.com.tegasistemas.documentofiscal.cte300.classes.enviolote.consulta;

import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Tipo Pedido de Consulta do Recibo do Lote de CT-e
 */

@Root(name = "consReciCTe")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeConsultaRecLote extends DFBase {
    private static final long serialVersionUID = -1071906898535302580L;
    
    @Element(name = "tpAmb")
    private DFAmbiente ambiente;
    
    @Element(name = "nRec")
    private String numeroRecebimento;
    
    @Attribute(name = "versao")
    private String versao;

    public CTeConsultaRecLote() {
        this.ambiente = null;
        this.numeroRecebimento = null;
        this.versao = null;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    /**
     * Identificação do Ambiente:<br>
     * 1 - Produção<br>
     * 2 - Homologação
     */
    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getNumeroRecebimento() {
        return this.numeroRecebimento;
    }

    /**
     * Número do Recibo do lote a ser consultado
     */
    public void setNumeroRecebimento(final String numeroRecebimento) {
        this.numeroRecebimento = numeroRecebimento;
    }

    public String getVersao() {
        return this.versao;
    }

    /**
     *
     * */
    public void setVersao(final String versao) {
        this.versao = versao;
    }
}
