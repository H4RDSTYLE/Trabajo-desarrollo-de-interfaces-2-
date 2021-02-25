package Excepciones;
/**
 * Exception which is going to be showed when an object equals an object.
 */
public class ObjectEqualsException extends Exception{
    /**
     * Método que establece un mensaje de error
     * @param objeto mensaje
     */
    public ObjectEqualsException(String objeto) {
        super("The " + objeto + " is on the database.");
    }
}
