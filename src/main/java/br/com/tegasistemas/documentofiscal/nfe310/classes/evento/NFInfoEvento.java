package br.com.tegasistemas.documentofiscal.nfe310.classes.evento;

import br.com.tegasistemas.documentofiscal.validadores.BigDecimalValidador;
import br.com.tegasistemas.documentofiscal.validadores.IntegerValidador;
import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFAmbiente;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.DFUnidadeFederativa;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class NFInfoEvento extends DFBase {
    private static final long serialVersionUID = 8878652860997939767L;
    
    @Attribute(name = "Id")
    private String id;
    
    @Element(name = "cOrgao")
    private DFUnidadeFederativa orgao;
    
    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;
    
    @Element(name = "chNFe")
    private String chave;
    
    @Element(name = "dhEvento")
    private ZonedDateTime dataHoraEvento;
    
    @Element(name = "tpEvento")
    private String codigoEvento;
    
    @Element(name = "nSeqEvento")
    private Integer numeroSequencialEvento;
    
    @Element(name = "verEvento")
    private String versaoEvento;
    
    @Element(name = "detEvento")
    private NFTipoEvento dadosEvento;

    public void setOrgao(final DFUnidadeFederativa orgao) {
        this.orgao = orgao;
    }

    public void setVersaoEvento(final BigDecimal versaoEvento) {
        this.versaoEvento = BigDecimalValidador.tamanho5Com2CasasDecimais(versaoEvento, "Info Evento Versao");
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        StringValidador.exatamente54(id, "Info Evento ID");
        this.id = id;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(final String cnpj) {
        if (this.cpf != null) {
            throw new IllegalStateException("CPF ja foi setado");
        }
        StringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(final String cpf) {
        if (this.cnpj != null) {
            throw new IllegalStateException("CNPJ ja foi setado");
        }
        StringValidador.cpf(cpf);
        this.cpf = cpf;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(final String chave) {
        StringValidador.exatamente44N(chave, "Info Evento Chave");
        this.chave = chave;
    }

    public ZonedDateTime getDataHoraEvento() {
        return this.dataHoraEvento;
    }

    public void setDataHoraEvento(final ZonedDateTime dataHoraEvento) {
        this.dataHoraEvento = dataHoraEvento;
    }

    public String getCodigoEvento() {
        return this.codigoEvento;
    }

    public void setTipoEvento(final String tipoEvento) {
        StringValidador.exatamente6N(tipoEvento, "Tipo Evento");
        this.codigoEvento = tipoEvento;
    }

    public int getNumeroSequencialEvento() {
        return this.numeroSequencialEvento;
    }

    public void setNumeroSequencialEvento(final int numeroSequencialEvento) {
        IntegerValidador.tamanho1a2(numeroSequencialEvento, "Numero Sequencial Evento");
        this.numeroSequencialEvento = numeroSequencialEvento;
    }

    public String getVersaoEvento() {
        return this.versaoEvento;
    }

    public NFTipoEvento getDadosEvento() {
        return this.dadosEvento;
    }

    public void setDadosEvento(final NFTipoEvento cartaCorrecao) {
        this.dadosEvento = cartaCorrecao;
    }

    public DFUnidadeFederativa getOrgao() {
        return this.orgao;
    }

    public void setCodigoEvento(final String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }
}