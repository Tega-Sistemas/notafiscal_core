package br.com.tegasistemas.documentofiscal.gnre200.loterecepcao;

import static java.util.Objects.nonNull;

public class LoteRecepcaoModel {

    /**
     * Identificador da Guia.
     */
    private String id;

    /**
     * Sigla da UF Favorecida. (Utilizar a Tabela do IBGE)
     */
    private String ufFavorecida;

    /**
     * Código da Receita.
     */
    private String codigoReceita;

    /**
     * Código do Detalhamento da Receita.
     */
    private String codigoDetalhamentoReceita;

    /**
     * Código do Produto.
     */
    private String codigoProduto;

    /**
     * Código do Tipo de Documento de Origem.
     */
    private String tipoDocumentoOrigem;

    /**
     * Número do Documento de Origem.
     */
    private String documentoOrigem;

    /**
     * Valor Original da Guia.
     */
    private String valorPrincipal;

    /**
     * Valor total da guia (valor original + encargos).
     */
    private String valorTotal;

    /**
     * Data de vencimento da guia.
     */
    private String dataVencimento;

    /**
     * Qdo for Envio:
     * - Data prevista de pagamento informada pelo contribuinte.
     * Qdo Retorno:
     * - Quando for uma validação retorna a Data prevista de pagamento informada pelo contribuinte.
     * - Quando for uma consulta da guia retorna a Data do pagamento da guia no banco.
     */
    private String dataPagamento;

    /**
     * Número do CNPJ do Contribuinte
     */
    private String emitenteCnpj;

    /**
     * Número do CPF do Contribuinte
     */
    private String emitenteCpf;

    /**
     * Inscrição Estadual do Contribuinte na UF favorecida.
     */
    private String emitenteIe;

    /**
     * Nome da firma ou a Razão Social do Contribuinte.
     */
    private String emitenteRazaoSocial;

    /**
     * Endereço do Contribuinte.
     */
    private String emitenteEndereco;

    /**
     * Código do Município de localização do Contribuinte.(Utilizar a tabela do IBGE)
     */
    private String emitenteCodigoMunicipio;

    /**
     * Código da UF do Contribuinte.
     */
    private String emitenteUf;

    /**
     * CEP do Contribuinte.
     */
    private String emitenteCep;
    /**
     * CEP do Contribuinte.
     */
    private String numParcela;

    /**
     * Telefone do contribuinte.
     */
    private String emitenteTelefone;

    /**
     * Inscrição Estadual
     */
    private String inscricaoEstadual;

    /**
     * Número do CNPJ do Contribuinte Destinatário
     */
    private String destinatarioCnpj;

    /**
     * Número do CPF do Contribuinte Destinatário
     */
    private String destinatarioCpf;

    /**
     * Inscrição Estadual do Contribuinte na UF favorecida.
     */
    private String destinatarioIe;

    /**
     * Nome da firma ou a Razão Social do Contribuinte.
     */
    private String destinatarioRazaoSocial;

    /**
     * Código do Município de Destino.(Utilizar a tabela do IBGE)
     */
    private String destinatarioCodigoMunicipio;

    /**
     * Número do convênio.
     */
    private String convenio;

    /**
     * Periodo (0 = mensal)
     */
    private String periodo;

    /**
     * Mês referente do item da guia
     */
    private String mes;

    /**
     * Ano referente do item da guia
     */
    private String ano;

    /**
     * inscrição estadual destinatário XML
     */
    private String inscricaoEstadualDestinatario;

    /**
     * cnpj destinatáio xml
     */
    private String cnpjDestinatario;

    /**
     * Caso a GNRE tenha vinculo com uma NF, o campo deve ser setado como true, dessa forma a tag <cpf></cpf> será
     * preenchida dentro de
     */
    private boolean vinculoNfCpf = false;

    /**
     * cpf destinatário do XML
     * Quando a GNRE possui vínculo com uma nota fiscal de saída, a informação do destinatário (tag CPF) será gerada no
     * .XML apenas se a nota fiscal do módulo Mercado ligada à Guia de Recolhimento for integrada para o módulo
     * Tributos antes da geração da GNRE. Caso a integração não seja feita, o CPF será gerado com valor 0.
     */
    private String cpfDestinatario;

    /**
     * Tipo do valor informado na tag <valor tipo"..."></valor> (Padrão gerado pela GNRE = 11
     */
    private String tipoValor;

    public LoteRecepcaoModel() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUfFavorecida(String ufFavorecida) {
        this.ufFavorecida = ufFavorecida;
    }

    public void setCodigoReceita(String codigoReceita) {
        this.codigoReceita = codigoReceita;
    }

    public void setCodigoDetalhamentoReceita(String codigoDetalhamentoReceita) {
        this.codigoDetalhamentoReceita = codigoDetalhamentoReceita;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setTipoDocumentoOrigem(String tipoDocumentoOrigem) {
        this.tipoDocumentoOrigem = tipoDocumentoOrigem;
    }

    public void setDocumentoOrigem(String documentoOrigem) {
        this.documentoOrigem = documentoOrigem;
    }

    public void setValorPrincipal(String valorPrincipal) {
        this.valorPrincipal = valorPrincipal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setEmitenteCnpj(String emitenteCnpj) {
        this.emitenteCnpj = emitenteCnpj;
    }

    public void setEmitenteCpf(String emitenteCpf) {
        this.emitenteCpf = emitenteCpf;
    }

    public void setEmitenteIe(String emitenteIe) {
        this.emitenteIe = emitenteIe;
    }

    public void setEmitenteRazaoSocial(String emitenteRazaoSocial) {
        this.emitenteRazaoSocial = emitenteRazaoSocial;
    }

    public void setEmitenteEndereco(String emitenteEndereco) {
        this.emitenteEndereco = emitenteEndereco;
    }

    public void setEmitenteCodigoMunicipio(String emitenteCodigoMunicipio) {
        this.emitenteCodigoMunicipio = emitenteCodigoMunicipio;
    }

    public void setEmitenteUf(String emitenteUf) {
        this.emitenteUf = emitenteUf;
    }

    public void setEmitenteCep(String emitenteCep) {
        this.emitenteCep = emitenteCep;
    }

    public void setNumParcela(String numParcela) {
        this.numParcela = numParcela;
    }

    public void setEmitenteTelefone(String emitenteTelefone) {
        this.emitenteTelefone = emitenteTelefone;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public void setDestinatarioCnpj(String destinatarioCnpj) {
        this.destinatarioCnpj = destinatarioCnpj;
    }

    public void setDestinatarioCpf(String destinatarioCpf) {
        this.destinatarioCpf = destinatarioCpf;
    }

    public void setDestinatarioIe(String destinatarioIe) {
        this.destinatarioIe = destinatarioIe;
    }

    public void setDestinatarioRazaoSocial(String destinatarioRazaoSocial) {
        this.destinatarioRazaoSocial = destinatarioRazaoSocial;
    }

    public void setDestinatarioCodigoMunicipio(String destinatarioCodigoMunicipio) {
        this.destinatarioCodigoMunicipio = destinatarioCodigoMunicipio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public void setPeriodo(String periodo) {
        this.periodo = "0";
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setInscricaoEstadualDestinatario(String inscricaoEstadualDestinatario) {
        this.inscricaoEstadualDestinatario = inscricaoEstadualDestinatario;
    }

    public void setCnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
    }

    public void setVinculoNfCpf(boolean vinculoNfCpf) {
        this.vinculoNfCpf = vinculoNfCpf;
    }

    public void setCpfDestinatario(String cpfDestinatario) {
        this.cpfDestinatario = cpfDestinatario;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        doIf(() -> builder.append("<ufFavorecida>").append(ufFavorecida).append("</ufFavorecida>"), nonNull(ufFavorecida));
        doIf(() -> builder.append("<tipoGnre>").append(0).append("</tipoGnre>"), nonNull(ufFavorecida));
        //.append("<tipoGnre>").append(0).append("</tipoGnre>")
        doIf(() -> builder
                .append("<contribuinteEmitente>")
                .append("<identificacao>"), true);
        doIf(() -> builder
                .append("<CPF>").append(emitenteCpf).append("</CPF>"), nonNull(emitenteCpf));
        doIf(() -> builder
                        .append("<CNPJ>").append(emitenteCnpj).append("</CNPJ>")
                        .append("<IE>").append(inscricaoEstadual).append("</IE>"),
                (nonNull(emitenteCnpj)) && (nonNull(inscricaoEstadual)));
        doIf(() -> builder
                .append("</identificacao>")
                .append("<razaoSocial>").append(emitenteRazaoSocial).append("</razaoSocial>")
                .append("<endereco>").append(emitenteEndereco).append("</endereco>")
                .append("<municipio>").append(emitenteCodigoMunicipio).append("</municipio>")
                .append("<uf>").append(ufFavorecida).append("</uf>")
                .append("<cep>").append(emitenteCep).append("</cep>")
                .append("<telefone>").append(emitenteTelefone).append("</telefone>")
                .append("</contribuinteEmitente>"), true);
        /*
         *      Caso vá enviar um lote com várias guias de GNRE, é possível alterar essa parte do código para ler
         *      um array de itens onde ele montará o xml de acordo...
         *      Porém, para tal alteração, e necessário a API receber esse array afim de gerar o código
         */
        doIf(() -> builder
                .append("<itensGNRE>")
                .append("<item>")
                .append("<receita>").append(codigoReceita).append("</receita>")
                //TODO: inserir detalhamento de receita
                .append("<detalhamentoReceita>").append(codigoDetalhamentoReceita).append("</detalhamentoReceita>")
                //Documento de origem deve ser tipo 22 ou 10 (e sempre deve conter 2 carácteres
                .append("<documentoOrigem tipo=\"").append(tipoDocumentoOrigem).append("\">").append(documentoOrigem).append("</documentoOrigem>")
                .append("<produto>").append(codigoProduto).append("</produto>")
                .append("<referencia>")
                //Valor fixo = "0"
                .append("<periodo>").append(periodo).append("</periodo>")
                .append("<mes>").append(mes).append("</mes>")
                .append("<ano>").append(ano).append("</ano>")
                .append("<parcela>").append(numParcela).append("</parcela>")
                .append("</referencia>")
                .append("<dataVencimento>").append(dataVencimento).append("</dataVencimento>")
                //Tipo valor válido 11, 12, 21, 22, 31, 32, 41, 42, 51 ou 52
                .append("<valor tipo=\"").append(tipoValor).append("\">").append(valorPrincipal).append("</valor>")
                .append("<convenio>").append(convenio).append("</convenio>")
                .append("<contribuinteDestinatario>")
                .append("<identificacao>"), true);

        //doIf(()-> builder
        // TODO: Válidar os casos com CPF ao invés de CNPJ
        //.append("<CPF>").append(vinculoNfCpf ? cpfDestinatario : "0").append("</CPF>"), (destinatarioCpf != "0") || (destinatarioCpf != null));
        doIf(() -> builder
                        //TODO: Inserir CNPJ contribuinte
                        .append("<CNPJ>").append(cnpjDestinatario).append("</CNPJ>")
                        //TODO: Inserir incricao estadual destinatário
                        .append("<IE>").append(inscricaoEstadualDestinatario).append("</IE>")
                        .append("</identificacao>")
                        .append("<razaoSocial>").append(destinatarioRazaoSocial).append("</razaoSocial>")
                        .append("<municipio>").append(destinatarioCodigoMunicipio).append("</municipio>")
                        .append("</contribuinteDestinatario>")
                        //Valores possíveis de código de campo extra (vide documentação)
                        //76 ou 74 ou 65 ou 95 ou 12 ou 47 ou 94 ou 79 ou 27 ou 88 ou 17 ou 30 ou 9 ou 91 ou 87 ou 97 ou 89 ou 83 ou 84 ou 78 ou 77 ou 80 ou 57 ou 72
                        /*
                        .append("<camposExtras>")
                        //Em caso de mais de 1 cmampo extra, essa tag deve ser repetida <campoExtra></campoExtra>
                        .append("<campoExtra>")
                        .append("<codigo>").append("").append("</codigo>")
                        .append("<valor>").append("").append("</valor>")
                        .append("</campoExtra>")
                        .append("</camposExtras>")
                        */
                        //TODO: Verificar o local correto da tag convênio na versão 2.00 (convênio dependa da receita escolhida)
                        //.append("<convenio>").append(convenio).append("</convenio>")
                        .append("</item>")
                        .append("</itensGNRE>")
                , nonNull(codigoReceita));
        doIf(() -> builder
                        .append("<valorGNRE>").append(valorTotal).append("</valorGNRE>")
                , true);
        return builder.toString();
    }

    private void doIf(Runnable runnable, boolean predicate) {
        if (predicate) {
            runnable.run();
        }
    }
}
