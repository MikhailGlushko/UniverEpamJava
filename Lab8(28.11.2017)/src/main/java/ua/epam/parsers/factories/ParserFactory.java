package ua.epam.parsers.factories;

import ua.epam.parsers.Parser;

/**
 * Parser factories interface (factories method template)
 */
public interface ParserFactory {
    Parser createParser();
}