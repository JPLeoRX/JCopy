package io.github.jpleorx.jcopy.core.copy;

/**
 * Runtime exception to be used in {@link CopyOperation}
 *
 * @author Leo Ertuna
 * @since 18.05.2018 23:34
 */
public class CopyOperationException extends RuntimeException {
    public CopyOperationException() {

    }

    public CopyOperationException(String message) {
        super(message);
    }

    public CopyOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CopyOperationException(Throwable cause) {
        super(cause);
    }

    public CopyOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}