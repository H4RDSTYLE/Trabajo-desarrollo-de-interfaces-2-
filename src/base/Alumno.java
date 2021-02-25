package base;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 *  A class which represents an Alumn.
 *  @author Hugo
 */
public class Alumno implements Serializable {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;
    private ImageIcon sexo;
    private ArrayList<Examen> examenes;
    private String sexoString;

    /**
     * Creates a student giving:
     * @param nombre The name
     * @param fechaNacimiento The date of birth
     * @param dni The id
     * @param sexo An image wich represents its gender
     * @param sexoString The gender which the user is going to see
     */
    public Alumno(String nombre, LocalDate fechaNacimiento, String dni, ImageIcon sexo, String sexoString) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        examenes = new ArrayList<>();
        this.sexoString = sexoString;
    }

    /**
     * @return returns an array with all the exams which the student has
     */
    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    /**
     *
     * @return returns the name of the student
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre sets the name of the student
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return returns the date of birth
     */

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @param fechaNacimiento sets the date of birth
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     *
     * @return returns the id
     */

    public String getDni() {
        return dni;
    }

    /**
     *
     * @param dni sets the id
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     *
     * @return an image of the gender.
     */

    public ImageIcon getSexo() {
        return sexo;
    }

    /**
     *
     * @param sexo sets the gender
     */

    public void setSexo(ImageIcon sexo) {
        this.sexo = sexo;
    }

    /**
     *
     * @return the object information into a String
     */
    @Override
    public String toString() {
        String idioma = Locale.getDefault().toString();
        if(idioma.equals("es_ES"))
            return "nombre: " + nombre + ", fecha de nacimiento: " + fechaNacimiento + ", dni: " + dni + ", género: " + sexoString;
        else
            return "name: " + nombre + ", date of birth: " + fechaNacimiento + ", id: " + dni + ", gender: " + sexoString;
    }

    /**
     *
     * @return a String  which represents the gender.
     */

    public String getSexoString() {
        return this.sexoString;
    }
}
