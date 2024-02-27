public class GnreTest {

    public static void main(String[] args) throws Exception {
        String logs;
        GnreAPI api = new GnreAPI();
        //api.configurar("654321",
        //        "changeit",
        //        "D:\\Certificado\\special.pfx",
        //        "D:\\Certificado\\homologacao.cacerts",
        //        "PR", "100013", "2", "2.00");

        //String xml = "<TLote_GNRE xmlns=\"http://www.gnre.pe.gov.br\" versao=\"2.00\"><guias><TDadosGNRE versao=\"2.00\"><ufFavorecida>MS</ufFavorecida><tipoGnre>0</tipoGnre><contribuinteEmitente><identificacao><CNPJ>19278207000150</CNPJ></identificacao><razaoSocial>teste</razaoSocial><endereco>teste</endereco><municipio>01507</municipio><uf>PR</uf><cep>86702040</cep><telefone>43333333333</telefone></contribuinteEmitente><itensGNRE><item><receita>100102</receita><dataVencimento>2021-08-12</dataVencimento><valor tipo=\"11\">1.00</valor><contribuinteDestinatario><identificacao><IE>9650156</IE></identificacao></contribuinteDestinatario><camposExtras><campoExtra><codigo>105</codigo><valor>41210719442650000115550010000020811070020810</valor></campoExtra></camposExtras></item></itensGNRE><valorGNRE>1.00</valorGNRE><dataPagamento>2021-08-12</dataPagamento></TDadosGNRE></guias></TLote_GNRE>";
        String xml = "<TLote_GNRE xmlns=\"http://www.gnre.pe.gov.br\" versao=\"2.00\"><guias><TDadosGNRE versao=\"2.00\"><ufFavorecida>MS</ufFavorecida><tipoGnre>0</tipoGnre><contribuinteEmitente><identificacao><IE>9065147294</IE></identificacao></contribuinteEmitente><itensGNRE><item><receita>100099</receita><documentoOrigem tipo=\"10\">116977</documentoOrigem><produto>24</produto><dataVencimento>2021-12-18</dataVencimento><valor xmlns=\"http://www.gnre.pe.gov.br\" tipo=\"11\">61.75</valor><contribuinteDestinatario><identificacao><CNPJ>28479193000124</CNPJ></identificacao><razaoSocial>LAT FORTE PET SHOP EIRELI ME</razaoSocial><municipio>02704</municipio></contribuinteDestinatario><camposExtras><campoExtra><codigo>116977</codigo><valor>41211119442650000115550010001169771111169777</valor></campoExtra></camposExtras></item></itensGNRE><valorGNRE>61.75</valorGNRE><dataPagamento>2021-12-18</dataPagamento></TDadosGNRE></guias></TLote_GNRE>";

        //logs = api.transmitirXml(xml,"D:\\Certificado\\special.pfx","D:\\Certificado\\homologacao.cacerts","654321","changeit",2);

        //logs = api.consultar("2100267135","D:\\Certificado\\cafeeira.pfx","D:\\Certificado\\homologacao.cacerts","1234","changeit",2);

        //logs = api.consultar("2100267135","C:\\Users\\Pedro Zampiroli\\Desktop\\testegnre.pfx","D:\\Certificado\\homologacao.cacerts","654321","changeit",2);

        //logs = api.BuscaConfigUF("PR","100145","D:\\Certificado\\cafeeira.pfx","D:\\Certificado\\homologacao.cacerts","1234","changeit",2);
        //logs = api.consultar("2100263344","D:\\Certificado\\cafeeira.pfx","D:\\Certificado\\homologacao.cacerts","1234","changeit",2);

        //logs = api.BuscaConfigUF("PR","100099","D:\\Certificado\\cafeeira.pfx","D:\\Certificado\\homologacao.cacerts","1234","changeit",2);
        logs = api.BuscaConfigUF("PR","100099","C:\\Users\\Pedro Zampiroli\\Desktop\\testegnre.pfx","D:\\Certificado\\homologacao.cacerts","654321","changeit",1);

        //String xml = "<TLote_GNRE xmlns=\"http://www.gnre.pe.gov.br\" versao=\"2.00\"><guias><TDadosGNRE versao=\"2.00\"><ufFavorecida>PR</ufFavorecida><tipoGnre>0</tipoGnre><contribuinteEmitente><identificacao><CNPJ>19278207000150</CNPJ></identificacao><razaoSocial>SOLAR MOVEIS EIRELI</razaoSocial><endereco>aaaa</endereco><municipio>01507</municipio><uf>PR</uf><cep>86703070</cep><telefone>4333033300</telefone></contribuinteEmitente><itensGNRE><item><receita>100013</receita><referencia><periodo>0</periodo><mes>01</mes><ano>2021</ano></referencia><dataVencimento>2021-06-29</dataVencimento><valor tipo=\"11\">100.00</valor></item></itensGNRE><valorGNRE>100.00</valorGNRE><dataPagamento>2021-06-30</dataPagamento></TDadosGNRE></guias></TLote_GNRE>";
        System.out.println(logs);
    }
}
