package ua.epam.parsers.factories;

import ua.epam.parsers.Parser;
import ua.epam.parsers.SAXParser;

/**
 * SAX parser factories class (factories method template)
 */
public class SAXParserFactory implements ParserFactory {

    @Override
    public Parser createParser() {
        return new SAXParser();
    }
}
