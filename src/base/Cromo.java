package base;

import java.util.ArrayList;

/**
 * Clase Cromo
 * @author Hugo
 * @version 1.0
 * @since JDK8
 */
public class Cromo {
    String nombre;
    String coleccion;
    int numero;
    String color;
    ArrayList<Alumno> alumnos;

    /**
     * Constructor de la Clase
     * @param nombre nombre del cromo
     * @param coleccion coleccion del cromo
     * @param numero número del cromo
     * @param color color del cromo
     */
    public Cromo(String nombre, String coleccion, int numero, String color) {
        this.nombre = nombre;
        this.coleccion = coleccion;
        this.numero = numero;
        this.color = color;
        alumnos = new ArrayList<>();
    }

    /**
     * Getter del Nombre
     * @return nombre
     */
    public String getNombre() { return nombre; }

    /**
     * Setter del Nombre
     * @param nombre nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Método toString
     * @return cadena de caracteres
     */
    @Override
    public String toString() {
        return "Cromo" +
                "nombre='" + nombre  +
                ", coleccion='" + coleccion  +
                ", numero=" + numero +
                ", color='" + color;
    }

    /**
     * Getter Coleccion
     * @return coleccion
     */
    public String getColeccion() { return coleccion; }

    /**
     * Setter Coleccion
     * @param coleccion coleccion
     */
    public void setColeccion(String coleccion) { this.coleccion = coleccion; }

    /**
     * Getter Numero
     * @return numero
     */
    public  int getNumero() { return numero; }

    /**
     * Setter Numero
     * @param numero entero
     */
    public void setNumero(int numero) { this.numero = numero; }

    /**
     * Getter Color
     * @return color
     */
    public String getColor() { return color; }

    /**
     * Setter color
     * @param color string color
     */
    public void setColor(String color) { this.color = color; }

    /**
     * Lista de los alumnos de un cromo
     * @return alumnos
     */
    public ArrayList<Alumno> getAlumnos() { return alumnos; }

    /**
     * Establece una lista de alumnos a un cromo
     * @param alumnos lista de alumnos
     */
    public void setAlumnos(ArrayList<Alumno> alumnos) { this.alumnos = alumnos; }
}
