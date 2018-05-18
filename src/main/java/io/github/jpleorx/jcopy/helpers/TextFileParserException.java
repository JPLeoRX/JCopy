package io.github.jpleorx.jcopy.helpers;

/**
 * A runtime exception to use in {@link TextFileParser}
 *
 * @author Leo Ertuna
 * @since 18.05.2018 23:58
 */
public class TextFileParserException extends RuntimeException {
    public TextFileParserException() {

    }

    public TextFileParserException(String message) {
        super(message);
    }

    public TextFileParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextFileParserException(Throwable cause) {
        super(cause);
    }

    public TextFileParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}