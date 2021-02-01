package Excepciones;

public class WhiteCampException extends Exception {
    public WhiteCampException(String message) {
        super("The camp: " + message + " cant be in white.");
    }
}
