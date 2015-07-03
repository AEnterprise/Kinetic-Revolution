package kineticrevolution.multiblocks.patterns;

/**
 * Created by MrKickkiller on 3/07/2015.
 */
public class PatternException extends Exception {

    public PatternException() {
    }

    public PatternException(String message) {
        super(message);
    }

    public PatternException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatternException(Throwable cause) {
        super(cause);
    }

    public PatternException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
