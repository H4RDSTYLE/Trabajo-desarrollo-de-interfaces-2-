package Excepciones;

public class ObjectEqualsException extends Exception{
    public ObjectEqualsException(String objeto) {
        super("The " + objeto + " is on the database.");
    }
}
