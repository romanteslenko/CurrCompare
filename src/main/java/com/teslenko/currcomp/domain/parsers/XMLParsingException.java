package com.teslenko.currcomp.domain.parsers;

/**
 * A custom exception class which aggregates all kind of exceptions
 * that can be thrown while different implementations of XMLParser is used
 */
public class XMLParsingException extends Exception {
    public XMLParsingException() {
        super();
    }

    public XMLParsingException(String message) {
        super(message);
    }

    public XMLParsingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
