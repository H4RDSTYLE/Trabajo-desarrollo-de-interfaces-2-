package Excepciones;

/**
 * Exception which is going to be showed when you left something empty.
 */
public class WhiteCampException extends Exception {
    /**
     * Método que establece un mensjae
     * @param message mensaje
     */
    public WhiteCampException(String message) {
        super("The camp: " + message + " cant be in white.");
    }
}
