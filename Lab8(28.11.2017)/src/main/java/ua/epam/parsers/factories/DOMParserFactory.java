package ua.epam.parsers.factories;

import ua.epam.parsers.Parser;
import ua.epam.parsers.DOMParser;

/**
 * DOM parser factories class (factories method template)
 */
public class DOMParserFactory implements ParserFactory {

    @Override
    public Parser createParser() {
        return new DOMParser();
    }
}
