package Excepciones;

/**
 * Exception which is going to be showed when you left something empty.
 */
public class CampoBlancoException extends Exception {
    /**
     * Método que establece un mensaje de error
     * @param message mensaje
     */
    public CampoBlancoException(String message) {
        super("El campo: " + message + " no puede estar en blanco.");
    }
}
