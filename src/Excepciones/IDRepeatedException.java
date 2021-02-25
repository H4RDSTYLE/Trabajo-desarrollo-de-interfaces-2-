package Excepciones;

/**
 * Exception which is going to be showed when you left something empty.
 */
public class IDRepeatedException extends Exception {
    /**
     * Método que establece un mensaje de error
     */
    public IDRepeatedException() {
        super("The id is repeated");
    }
}
