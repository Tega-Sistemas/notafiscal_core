import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MDFeTest {


    public static void main(String[] args) {

        MDFeAPI api = new MDFeAPI();

        api.Configurar("654321",
                            "changeit",
                            "D:\\Certificado\\clientes\\renzes.pfx",
                            "D:\\Certificado\\clientes\\producao.cacerts",
                            "PR",
                            "1");

        //String xml = "<MDFe><infMDFe Id=\"MDFe41230407769039000117580020000083991130000229\" versao=\"3.00\"><ide><cUF>41</cUF><tpAmb>1</tpAmb><tpEmit>1</tpEmit><mod>58</mod><serie>2</serie><nMDF>8399</nMDF><cMDF>13000022</cMDF><cDV>9</cDV><modal>1</modal><dhEmi>2023-04-13T09:38:39-03:00</dhEmi><tpEmis>1</tpEmis><procEmi>0</procEmi><verProc>1.0</verProc><UFIni>MT</UFIni><UFFim>MT</UFFim><infMunCarrega><cMunCarrega>5105903</cMunCarrega><xMunCarrega>NOBRES</xMunCarrega></infMunCarrega></ide><emit><CNPJ>07769039000117</CNPJ><IE>9036046624</IE><xNome>LC QUADRI TRANSP RODOV LTDA</xNome><xFant>LC QUADRI TRANSP RODOV LTDA</xFant><enderEmit><xLgr>RUA MOURAO</xLgr><nro>201</nro><xBairro>CENTRO</xBairro><cMun>4104600</cMun><xMun>CAPITAO LEONIDAS MARQUES</xMun><CEP>85790000</CEP><UF>PR</UF><fone>4532867400</fone></enderEmit></emit><infModal versaoModal=\"3.00\"><rodo><infANTT><RNTRC>08018446</RNTRC><infContratante><CNPJ>07769039000117</CNPJ></infContratante></infANTT><veicTracao><cInt>1066</cInt><placa>AKV7381</placa><tara>17200</tara><capKG>48000</capKG><capM3>80</capM3><condutor><xNome>JORGE ADRIAN</xNome><CPF>03285000970</CPF></condutor><tpRod>03</tpRod><tpCar>00</tpCar><UF>PR</UF></veicTracao></rodo></infModal><infDoc><infMunDescarga><cMunDescarga>5108303</cMunDescarga><xMunDescarga>UNIAO DO SUL</xMunDescarga><infCTe><chCTe>41230407769039000117570000000554241040554244</chCTe></infCTe></infMunDescarga></infDoc><seg><infResp><respSeg>1</respSeg><CNPJ>07769039000117</CNPJ></infResp><infSeg><xSeg>A MESMA</xSeg><CNPJ>07769039000117</CNPJ></infSeg><nApol>0</nApol><nAver>0</nAver></seg><prodPred><tpCarga>05</tpCarga><xProd>CALCARIO</xProd><infLotacao><infLocalCarrega><CEP>78460000</CEP></infLocalCarrega><infLocalDescarrega><CEP>78543000</CEP></infLocalDescarrega></infLotacao></prodPred><tot><qCTe>1</qCTe><vCarga>3255.20</vCarga><cUnid>01</cUnid><qCarga>31.3000</qCarga></tot><infAdic><infCpl>Referente FRETES: 55424</infCpl></infAdic></infMDFe><infMDFeSupl><qrCodMDFe><![CDATA[https://dfe-portal.svrs.rs.gov.br/mdfe/qrCode?chMDFe=41230407769039000117580020000083991130000229&tpAmb=1]]></qrCodMDFe></infMDFeSupl></MDFe>";

        String retorno ;

        try {
            //retorno = api.transmitir ("8150","3.00",xml);

            //retorno = api.naoencerrada("33198097000148");

            //etorno = api.naoencerrada("18374429000104");

            //retorno = api.incluirCondutor("41200518374429000104580010000008541202005232","NOME CONDUTOR TESTE", "12900669065", "1");
            //retorno = api.consultaXMLProcMDFe("419001141377915","41220400781215000197580010000200161110000014","941220003673946",xml);

            retorno = api.consultarrecibo("419001575523351");

            //retorno = api.consultar("41230407769039000117580020000083991130000229");
            //retorno = api.transmitir("11334","3.00",xml);
        } catch (Exception e) {
            retorno = "Error: " + e.getMessage();

        }

        System.out.println(retorno);

    }
}

