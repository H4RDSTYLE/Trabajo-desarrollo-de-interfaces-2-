package Excepciones;

public class CampoBlancoException extends Exception {

    public CampoBlancoException(String message) {
        super("El campo: " + message + " no puede estar en blanco.");
    }
}
