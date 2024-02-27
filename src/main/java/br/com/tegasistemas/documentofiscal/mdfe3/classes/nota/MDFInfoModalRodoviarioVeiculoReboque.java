package br.com.tegasistemas.documentofiscal.mdfe3.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import org.simpleframework.xml.Element;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import br.com.tegasistemas.documentofiscal.mdfe3.classes.def.MDFTipoCarroceria;

public class MDFInfoModalRodoviarioVeiculoReboque extends DFBase {
    private static final long serialVersionUID = -2787982058485353668L;

    @Element(name = "cInt", required = false)
    protected String codigoInterno;

    @Element(name = "placa")
    protected String placa;

    @Element(name = "RENAVAM", required = false)
    protected String renavam;

    @Element(name = "tara")
    protected String tara;

    @Element(name = "capKG")
    private String capacidadeKG;

    @Element(name = "capM3", required = false)
    protected String capacidadeM3;

    @Element(name = "prop", required = false)
    private MDFInfoModalRodoviarioVeiculoProp proprietario;
    /**
     * Tipo de carroceria
     */
    @Element(name = "tpCar")
    private MDFTipoCarroceria tipoCarroceria;

    @Element(name = "UF")
    private String unidadeFederativa;

    public String getCodigoInterno() {
        return this.codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return this.renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getTara() {
        return this.tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public void setCapacidadeKG(final String capacidadeKG) {
        this.capacidadeKG = StringValidador.capacidadeNDigitos(capacidadeKG, "Capacidade em KG reboque", 5);
    }

    public String getCapacidadeKG() {
        return this.capacidadeKG;
    }

    public String getCapacidadeM3() {
        return this.capacidadeM3;
    }

    public void setCapacidadeM3(String capacidadeM3) {
        this.capacidadeM3 = capacidadeM3;
    }

    public MDFInfoModalRodoviarioVeiculoProp getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(final MDFInfoModalRodoviarioVeiculoProp proprietario) {
        this.proprietario = proprietario;
    }

    public MDFTipoCarroceria getTipoCarroceria() {
        return this.tipoCarroceria;
    }

    public void setTipoCarroceria(final MDFTipoCarroceria tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public String getUnidadeFederativa() {
        return this.unidadeFederativa;
    }

    public void setUnidadeFederativa(final String unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public void setUnidadeFederativa(final DFUnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa.getCodigo();
    }

}

/*  @Override
    public void setCodigoInterno(final String codigoInterno) {
        super.codigoInterno = StringValidador.validador(codigoInterno, "Codigo interno Veiculo Reboque", 10, false, false);
    }

    @Override
    public void setPlaca(final String placa) {
        StringValidador.placaDeVeiculo(placa, "Placa do reboque");
        this.placa = placa;
    }

    @Override
    public void setRenavam(final String renavam) {
        this.renavam = StringValidador.validaIntervalo(renavam, 9, 11, "Renavam do reboque");
    }

    @Override
    public void setTara(final String tara) {
        this.tara = StringValidador.capacidadeNDigitos(tara, "Tara em reboque", 5);
    }

    public String getCapacidadeKG() {
        return this.capacidadeKG;
    }

    public void setCapacidadeKG(final String capacidadeKG) {
        this.capacidadeKG = StringValidador.capacidadeNDigitos(capacidadeKG, "Capacidade em KG reboque", 5);
    }

    @Override
    public String getCapacidadeM3() {
        return this.capacidadeM3;
    }

    @Override
    public void setCapacidadeM3(final String capacidadeM3) {
        this.capacidadeM3 = StringValidador.capacidadeNDigitos(capacidadeM3, "Capacidade em M3 reboque", 2);
    }*/
