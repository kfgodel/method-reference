package ar.com.kfgodel.methodreference.api;

/**
 * This type represents an error on the method references
 *
 * Created by kfgodel on 06/01/15.
 */
public class MethodReferenceException extends RuntimeException {

    public MethodReferenceException(String message) {
        super(message);
    }

    public MethodReferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodReferenceException(Throwable cause) {
        super(cause);
    }
}
