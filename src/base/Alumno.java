package base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Alumno implements Serializable {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;
    private String sexo;
    private ArrayList<Examen> examenes;

    public Alumno(String nombre, LocalDate fechaNacimiento, String dni, String sexo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        examenes = new ArrayList<>();
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", fechaNacimiento: " + fechaNacimiento + ", dni: " + dni + ", sexo: " + sexo;
    }
}
