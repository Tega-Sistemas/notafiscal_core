package br.com.tegasistemas.documentofiscal.validadores;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;

public final class XMLValidador {

    public static boolean validaLote(final String arquivoXML) throws Exception {
        return XMLValidador.valida(arquivoXML, "enviNFe_v3.10.xsd");
    }

    public static boolean validaNota(final String arquivoXML) throws Exception {
        return XMLValidador.valida(arquivoXML, "nfe_v3.10.xsd");
    }

    public static boolean validaLote400(final String arquivoXML) throws Exception {
        return XMLValidador.valida400(arquivoXML, "enviNFe_v4.00.xsd");
    }

    public static boolean validaNota400(final String arquivoXML) throws Exception {
        return XMLValidador.valida400(arquivoXML, "nfe_v4.00.xsd");
    }

    public static boolean validaLoteMDFe(final String arquivoXML) throws Exception {
        return XMLValidador.validaMDF(arquivoXML, "enviMDFe_v3.00.xsd");
    }

    public static boolean validaMDFe(final String arquivoXML) throws Exception {
        return XMLValidador.validaMDF(arquivoXML, "mdfe_v3.00.xsd");
    }

    public static boolean validaLoteCTe(final String arquivoXML) throws Exception {
        return XMLValidador.validaCTe(arquivoXML, "enviCTe_v3.00.xsd");
    }

    public static boolean validaNotaCte(final String arquivoXML) throws Exception {
        return XMLValidador.validaCTe(arquivoXML, "cte_v3.00.xsd");
    }

    public static boolean validaEpec(final String arquivoXML) throws Exception {
        return XMLValidador.validaEpec(arquivoXML, "envEPEC_v1.00.xsd");
    }

    public static boolean validaEventoEpec(final String arquivoXML) throws Exception {
        return XMLValidador.validaEpec(arquivoXML, "EPEC_v1.00.xsd");
    }

    public static boolean validaLoteGnre200(final String arquivoXML) throws Exception {
        return XMLValidador.validaGnre200(arquivoXML, "lote_gnre_v2.00.xsd");
    }

    private static boolean valida(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/PL_008i2/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    public static boolean valida400(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/PL009c_v1.20/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    private static boolean validaMDF(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/PL_MDFe_300-2020/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    private static boolean validaCTe(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/PL_CTe_300a_NT2022.001/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    private static boolean validaDfe(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/PL_NFeDistDFe_102/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    public static boolean validaConsultaDfe(final String arquivoXML) throws Exception {
        return XMLValidador.validaDfe(arquivoXML, "distDFeInt_v1.01.xsd");
    }

    private static boolean validaEpec(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/Evento_EPEC_PL_v1.01/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    public static boolean validaGnre200(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format("schemas/GNREv2/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }
}
