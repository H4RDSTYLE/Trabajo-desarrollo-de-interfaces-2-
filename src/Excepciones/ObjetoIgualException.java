package Excepciones;

/**
 * Exception which is going to be showed when an object equals an object.
 */
public class ObjetoIgualException extends Exception {
    /**
     * Método que establece un mensaje de error
     * @param objeto mensaje
     */
    public ObjetoIgualException(String objeto) {
        super("El/La " + objeto + " ya figura en la base de datos.");
    }
}
