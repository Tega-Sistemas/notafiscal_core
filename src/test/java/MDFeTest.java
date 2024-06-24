import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class MDFeTest {


    public static void main(String[] args) {

        MDFeAPI api = new MDFeAPI();

        api.Configurar("123456",
                            "changeit",
                            "D:\\Certificado\\clientes\\Solar20.pfx",
                            "D:\\Certificado\\clientes\\producao.cacerts",
                            "SP",
                            "2");

        String xml = "<MDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\"><infMDFe Id=\"MDFe35240619278207000150580010000182301140000206\" versao=\"3.00\"><ide><cUF>35</cUF><tpAmb>2</tpAmb><tpEmit>2</tpEmit><mod>58</mod><serie>1</serie><nMDF>18230</nMDF><cMDF>14000020</cMDF><cDV>6</cDV><modal>1</modal><dhEmi>2024-06-14T10:01:44-03:00</dhEmi><tpEmis>1</tpEmis><procEmi>0</procEmi><verProc>1.0</verProc><UFIni>SP</UFIni><UFFim>SP</UFFim><infMunCarrega><cMunCarrega>3554003</cMunCarrega><xMunCarrega>TATUI</xMunCarrega></infMunCarrega></ide><emit><CNPJ>19278207000150</CNPJ><IE>687099668111</IE><xNome>SOLAR MOVEIS EIRELI</xNome><xFant>SOLAR MOVEIS EIRELI</xFant><enderEmit><xLgr>V MUNICIPAL MOISES MARTINS</xLgr><nro>1005</nro><xBairro>BENFICA</xBairro><cMun>3554003</cMun><xMun>TATUI</xMun><CEP>18270000</CEP><UF>SP</UF><fone>1532057300</fone><email>nfe@solarmoveis.ind.br</email></enderEmit></emit><infModal versaoModal=\"3.00\"><rodo><infANTT><infContratante><CNPJ>19278207000150</CNPJ></infContratante></infANTT><veicTracao><cInt>1686</cInt><placa>CKU8B65</placa><tara>0</tara><capKG>0</capKG><capM3>0</capM3><condutor><xNome>BRENO JOSE DE BARROS</xNome><CPF>26689406806</CPF></condutor><tpRod>01</tpRod><tpCar>00</tpCar><UF>SP</UF></veicTracao></rodo></infModal><infDoc><infMunDescarga><cMunDescarga>3550308</cMunDescarga><xMunDescarga>SAO PAULO</xMunDescarga><infNFe><chNFe>35240619278207000150550010001440561061440564</chNFe></infNFe><infNFe><chNFe>35240619278207000150550010001440571061440570</chNFe></infNFe><infNFe><chNFe>35240619278207000150550010001440581061440585</chNFe></infNFe><infNFe><chNFe>35240619278207000150550010001440591061440590</chNFe></infNFe><infNFe><chNFe>35240619278207000150550010001440601061440605</chNFe></infNFe></infMunDescarga></infDoc><seg><infResp><respSeg>1</respSeg><CNPJ>19278207000150</CNPJ></infResp><infSeg><xSeg>A MESMA</xSeg><CNPJ>19278207000150</CNPJ></infSeg><nApol>0</nApol><nAver>0</nAver></seg><tot><qNFe>5</qNFe><vCarga>60548.68</vCarga><cUnid>01</cUnid><qCarga>3178.0000</qCarga></tot><infAdic><infCpl>Referente NOTAS: 144056-144057-144058-144059-144060</infCpl></infAdic></infMDFe><infMDFeSupl><qrCodMDFe><![CDATA[https://dfe-portal.svrs.rs.gov.br/mdfe/qrCode?chMDFe=35240619278207000150580010000182301140000206&tpAmb=1]]></qrCodMDFe></infMDFeSupl></MDFe>";

        String retorno ;

        try {

            retorno = api.teste();

            //retorno = api.transmitir(xml);

            //retorno = api.consultar("35240619278207000150580010000178871110000209");

            //retorno = api.naoencerrada("33198097000148");

            //etorno = api.naoencerrada("18374429000104");

            //retorno = api.incluirCondutor("41200518374429000104580010000008541202005232","NOME CONDUTOR TESTE", "12900669065", "1");

            //retorno = api.consultaXMLProcMDFe("419001141377915","41220400781215000197580010000200161110000014","941220003673946",xml);

            //retorno = api.consultarrecibo("419001709775093");

            //retorno = api.consultar(chave);

            //retorno = api.gerarQRCode("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\"><infMDFe versao=\"3.00\" Id=\"MDFe41240407769039000117580020000101531090000222\"><ide><cUF>41</cUF><tpAmb>2</tpAmb><tpEmit>1</tpEmit><mod>58</mod><serie>2</serie><nMDF>10153</nMDF><cMDF>09000022</cMDF><cDV>2</cDV><modal>1</modal><dhEmi>2024-04-09T13:16:41-03:00</dhEmi><tpEmis>1</tpEmis><procEmi>0</procEmi><verProc>1.0</verProc><UFIni>PR</UFIni><UFFim>MT</UFFim><infMunCarrega><cMunCarrega>4104600</cMunCarrega><xMunCarrega>CAPITAO LEONIDAS MARQUES</xMunCarrega></infMunCarrega><infPercurso><UFPer>MS</UFPer></infPercurso></ide><emit><CNPJ>07769039000117</CNPJ><IE>9036046624</IE><xNome>LC QUADRI TRANSP RODOV LTDA</xNome><xFant>LC QUADRI TRANSP RODOV LTDA</xFant><enderEmit><xLgr>RUA MOURAO</xLgr><nro>201</nro><xBairro>CENTRO</xBairro><cMun>4104600</cMun><xMun>CAPITAO LEONIDAS MARQUES</xMun><CEP>85790000</CEP><UF>PR</UF><fone>4532867400</fone><email>transportadora@transquadri.com.br</email></enderEmit></emit><infModal versaoModal=\"3.00\"><rodo><infANTT><RNTRC>08018446</RNTRC><infContratante><CNPJ>07769039000117</CNPJ></infContratante></infANTT><veicTracao><cInt>1350</cInt><placa>RNO4E73</placa><tara>0</tara><capKG>0</capKG><capM3>0</capM3><condutor><xNome>ADELAR HEBERLE - MGZ0149</xNome><CPF>02559941929</CPF></condutor><tpRod>03</tpRod><tpCar>00</tpCar><UF>PR</UF></veicTracao><veicReboque><cInt>1229</cInt><placa>RHI2E55</placa><tara>0</tara><capKG>0</capKG><capM3>0</capM3><tpCar>00</tpCar><UF>PR</UF></veicReboque><veicReboque><cInt>1230</cInt><placa>RHI2E58</placa><tara>0</tara><capKG>0</capKG><capM3>0</capM3><tpCar>00</tpCar><UF>PR</UF></veicReboque><veicReboque><cInt>1224</cInt><placa>RHI2E59</placa><tara>0</tara><capKG>0</capKG><capM3>0</capM3><tpCar>00</tpCar><UF>PR</UF></veicReboque></rodo></infModal><infDoc><infMunDescarga><cMunDescarga>5105606</cMunDescarga><xMunDescarga>MATUPA</xMunDescarga><infCTe><chCTe>41240407769039000117570010000643901040643902</chCTe></infCTe></infMunDescarga></infDoc><seg><infResp><respSeg>1</respSeg><CNPJ>07769039000117</CNPJ></infResp><infSeg><xSeg>A MESMA</xSeg><CNPJ>07769039000117</CNPJ></infSeg><nApol>0</nApol><nAver>0</nAver></seg><prodPred><tpCarga>05</tpCarga><xProd>TESTE</xProd><infLotacao><infLocalCarrega><CEP>85790000</CEP></infLocalCarrega><infLocalDescarrega><CEP>78525000</CEP></infLocalDescarrega></infLotacao></prodPred><tot><qCTe>1</qCTe><vCarga>100.00</vCarga><cUnid>01</cUnid><qCarga>1.0000</qCarga></tot><infAdic><infCpl>Referente FRETES: 64390</infCpl></infAdic></infMDFe><infMDFeSupl><qrCodMDFe>ATUALIZARQRCODE</qrCodMDFe></infMDFeSupl></MDFe>");

        } catch (Exception e) {
            retorno = "Error: " + e.getMessage();

        }

        System.out.println(retorno);

    }
}

