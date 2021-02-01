package base;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Alumno implements Serializable {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;
    private ImageIcon sexo;
    private ArrayList<Examen> examenes;
    private String sexoString;

    public Alumno(String nombre, LocalDate fechaNacimiento, String dni, ImageIcon sexo, String sexoString) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        examenes = new ArrayList<>();
        this.sexoString = sexoString;
    }

    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ImageIcon getSexo() {
        return sexo;
    }

    public void setSexo(ImageIcon sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        String idioma = Locale.getDefault().toString();
        if(idioma.equals("es_ES"))
            return "nombre: " + nombre + ", fecha de nacimiento: " + fechaNacimiento + ", dni: " + dni + ", género: " + sexoString;
        else
            return "name: " + nombre + ", date of birth: " + fechaNacimiento + ", id: " + dni + ", gender: " + sexoString;
    }

    public String getSexoString() {
        return this.sexoString;
    }
}
