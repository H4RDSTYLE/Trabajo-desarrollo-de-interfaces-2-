package Excepciones;

public class DniRepetidoException extends Exception {
    public DniRepetidoException() {
        super("El dni esta repetido");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
