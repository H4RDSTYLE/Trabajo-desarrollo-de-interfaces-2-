package Excepciones;
/**
 * Exception which is going to be showed when the id is on other object.
 */
public class DniRepetidoException extends Exception {
    /**
     * Método que establece un mensaje de error
     */
    public DniRepetidoException() {
        super("El dni esta repetido");
    }
}
