package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.DFStringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import org.simpleframework.xml.Element;

public class NFNotaInfoVeiculo extends DFBase {
    private static final long serialVersionUID = 6774439413962070444L;
    
    @Element(name = "placa")
    private String placaVeiculo;
    
    @Element(name = "UF")
    private String uf;

    @Element(name = "RNTC", required = false)
    private String registroNacionalTransportadorCarga;

    public void setPlacaVeiculo(final String placaVeiculo) {
        DFStringValidador.placaDeVeiculo(placaVeiculo);
        this.placaVeiculo = placaVeiculo;
    }

    public void setUf(final DFUnidadeFederativa uf) {
        this.uf = uf.getCodigo();
    }

    public String getPlacaVeiculo() {
        return this.placaVeiculo;
    }

    public String getRegistroNacionalTransportadorCarga() {
        return this.registroNacionalTransportadorCarga;
    }

    public String getUf() {
        return this.uf;
    }

    public void setRegistroNacionalTransportadorCarga(final String registroNacionalTransportadorCarga) {
        DFStringValidador.tamanho20(registroNacionalTransportadorCarga, "Registro Nacional Transportador Carga");
        this.registroNacionalTransportadorCarga = registroNacionalTransportadorCarga;
    }
}