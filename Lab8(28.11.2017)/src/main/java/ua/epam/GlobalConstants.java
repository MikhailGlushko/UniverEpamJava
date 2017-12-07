package ua.epam;

/**
 * Created by Maria on 06.07.2016.
 */
public interface GlobalConstants {

    /** Types of parsers */
    enum ParserType{DOM, SAX, STAX}

    /** Path to XML file */
    String SOURCE_XML = "tariff.xml";

    /** Path to XSD file */
    String SOURCE_XSD = "tariff.xsd";

    String LOGFILE = "log.txt";
}