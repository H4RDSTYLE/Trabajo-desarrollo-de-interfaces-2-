package Excepciones;

public class IDRepeatedException extends Exception {
    public IDRepeatedException() {
        super("The id is repeated");
    }
}
