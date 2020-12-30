package Excepciones;

public class ObjetoIgualException extends Exception {
    public ObjetoIgualException(String objeto) {
        super("El/La " + objeto + " ya figura en la base de datos.");
    }
}
