package br.com.tegasistemas.documentofiscal.cte300.classes.nota;

import br.com.tegasistemas.documentofiscal.validadores.StringValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Informações do Destinatário do CT-e<br>
 *       Só pode ser omitido em caso de redespacho intermediário
 */

@Root(name = "dest")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoDestinatario extends DFBase {
    private static final long serialVersionUID = 2313824611520451190L;

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;

    @Element(name = "IE", required = false)
    private String inscricaoEstadual;
    
    @Element(name = "xNome")
    private String razaoSocial;

    @Element(name = "fone", required = false)
    private String telefone;

    @Element(name = "ISUF", required = false)
    private String inscricaoSuframa;
    
    @Element(name = "enderDest")
    private CTeNotaEndereco endereco;

    @Element(name = "email", required = false)
    private String email;

    public CTeNotaInfoDestinatario() {
        this.cnpj = null;
        this.cpf = null;
        this.inscricaoEstadual = null;
        this.razaoSocial = null;
        this.telefone = null;
        this.inscricaoSuframa = null;
        this.endereco = null;
        this.email = null;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    /**
     * Número do CNPJ<br>
     * Em caso de empresa não estabelecida no Brasil, será informado o CNPJ com zeros. Informar os zeros não significativos.
     */
    public void setCnpj(final String cnpj) {
        StringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    /**
     * Número do CPF<br>
     * Informar os zeros não significativos.
     */
    public void setCpf(final String cpf) {
        StringValidador.cpf(cpf);
        this.cpf = cpf;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    /**
     * Inscrição Estadual<br>
     * Informar a IE do destinatário ou ISENTO se destinatário é contribuinte do ICMS isento de inscrição no cadastro de contribuintes do ICMS. Caso o destinatário não seja contribuinte do ICMS não informar o conteúdo.
     */
    public void setInscricaoEstadual(final String inscricaoEstadual) {
        StringValidador.inscricaoEstadual(inscricaoEstadual);
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    /**
     * Razão Social ou Nome do destinatário
     */
    public void setRazaoSocial(final String razaoSocial) {
        StringValidador.tamanho2ate60(razaoSocial, "Razão Social ou Nome do destinatário");
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return this.telefone;
    }

    /**
     * Telefone
     */
    public void setTelefone(final String telefone) {
        StringValidador.telefone(telefone);
        this.telefone = telefone;
    }

    public String getInscricaoSuframa() {
        return this.inscricaoSuframa;
    }

    /**
     * Inscrição na SUFRAMA<br>
     * (Obrigatório nas operações com as áreas com benefícios de incentivos fiscais sob controle da SUFRAMA)
     */
    public void setInscricaoSuframa(final String inscricaoSuframa) {
        StringValidador.tamanho8a9N(inscricaoSuframa, "Inscrição na SUFRAMA");
        this.inscricaoSuframa = inscricaoSuframa;
    }

    public CTeNotaEndereco getEndereco() {
        return this.endereco;
    }

    /**
     * Dados do endereço
     */
    public void setEndereco(final CTeNotaEndereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    /**
     * Endereço de email
     */
    public void setEmail(final String email) {
        StringValidador.tamanho60(email, "Endereço de email");
        StringValidador.email(email);
        this.email = email;
    }
}
