package ua.epam.main;

import org.apache.log4j.*;
import ua.epam.parsers.*;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import ua.epam.parsers.factories.DOMParserFactory;
import ua.epam.parsers.factories.ParserFactory;
import ua.epam.parsers.factories.SAXParserFactory;
import ua.epam.parsers.factories.StAXParserFactory;
import ua.epam.tariff.Tariff;
import ua.epam.GlobalConstants;
import ua.epam.GlobalConstants.ParserType;

import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * Main class
 */
public class Main {

    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        final String sourceXML = ClassLoader.getSystemResource(GlobalConstants.SOURCE_XML)
                .getPath()
                .replaceAll("%20"," ")
                .substring(1);
        final String sourceXSD = ClassLoader.getSystemResource(GlobalConstants.SOURCE_XSD)
                .getPath()
                .replaceAll("%20"," ")
                .substring(1);

        // Validator
        validate(sourceXML, sourceXSD);

        logger.info("Parse file using: "+ParserType.DOM);
        ParserFactory parserFactory = getParserFactory(ParserType.DOM);
        Parser parser = parserFactory.createParser();

        // Parsers
        runParser(parser, sourceXML);

        System.out.println("\n------\n");

        logger.info("Parse file using: "+ParserType.SAX);
        parserFactory = getParserFactory(ParserType.SAX);
        parser = parserFactory.createParser();
        runParser(parser, sourceXML);

        System.out.println("\n------\n");

        logger.info("Parse file using: "+ParserType.STAX);
        parserFactory = getParserFactory(ParserType.STAX);
        parser = parserFactory.createParser();
        runParser(parser, sourceXML);

        System.out.println("\n------\n");

        // JAXB
        logger.info("Parse file using: "+"JAXB");
        jaxb(sourceXML);
        //

    }

    private static void runParser(Parser parser, String fileName) {
        try {
            parser.parse(new FileInputStream(fileName));
        } catch (Exception ex) {
            logger.warn(ex);
        }
    }

    private static void validate(String xml, String xsd) {

        try {
            Source schemaFile = new StreamSource(new File(xsd));
            Source xmlFile = new StreamSource(new File(xml));
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();

            validator.validate(xmlFile);
            System.out.println(xml + " is valid");
        } catch (SAXException ex) {
            logger.warn(xml + "is NOT valid", ex);
        } catch (IOException ex) {
            logger.warn(ex);
        }
    }

    private static void jaxb(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Tariff.class);
            Unmarshaller unm = context.createUnmarshaller();
            Object tariff =  unm.unmarshal(new File(fileName));
            System.out.println(tariff);
        } catch (JAXBException ex) {
            logger.warn(ex);
        }
    }

    private static ParserFactory getParserFactory(ParserType parserType) {
        if (parserType == ParserType.DOM) {
            return new DOMParserFactory();
        } else if (parserType == ParserType.SAX) {
            return new SAXParserFactory();
        } else if (parserType == ParserType.STAX) {
            return new StAXParserFactory();
        } else {
            return null;
        }
    }
}