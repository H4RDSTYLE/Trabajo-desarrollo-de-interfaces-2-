package MVC.MC;

import base.Alumno;
import base.Asignatura;
import base.Examen;

import java.util.ArrayList;

public class Modelo {

    private ArrayList<Examen> examenes;
    private ArrayList<Alumno> alumnos;
    private ArrayList<Asignatura> asignaturas;

    public Modelo(){
        examenes = new ArrayList<>();
        alumnos = new ArrayList<>();
        asignaturas = new ArrayList<>();
    }

    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(ArrayList<Examen> examenes) {
        this.examenes = examenes;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
