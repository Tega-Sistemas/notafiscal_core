package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.cte300.classes.CTClasseTarifa;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * @author Caio
 * @info Informações de tarifa
 */

@Root(name = "tarifa")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoModalAereoTarifa extends DFBase {
    private static final long serialVersionUID = 4117608894098820892L;
    
    @Element(name = "CL")
    private CTClasseTarifa classe;

    @Element(name = "cTar", required = false)
    private String codigo;
    
    @Element(name = "vTar")
    private String valor;

    public CTeNotaInfoCTeNormalInfoModalAereoTarifa() {
        this.classe = null;
        this.codigo = null;
        this.valor = null;
    }

    public CTClasseTarifa getClasse() {
        return this.classe;
    }

    /**
     * Classe<br>
     * M - Tarifa Mínima;<br>
     * G - Tarifa Geral;<br>
     * E - Tarifa Específica
     */
    public void setClasse(final CTClasseTarifa classe) {
        this.classe = classe;
    }

    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Código da Tarifa<br>
     * Deverão ser incluídos os códigos de três dígitos, correspondentes à tarifa.
     */
    public void setCodigo(final String codigo) {
        StringValidador.tamanho4(codigo, "Código da Tarifa");
        this.codigo = codigo;
    }

    public String getValor() {
        return this.valor;
    }

    /**
     * Valor da Tarifa<br>
     * Valor da tarifa por kg quando for o caso.
     */
    public void setValor(final BigDecimal valor) {
        this.valor = BigDecimalValidador.tamanho15Com2CasasDecimais(valor, "Valor da Tarifa");
    }
}
