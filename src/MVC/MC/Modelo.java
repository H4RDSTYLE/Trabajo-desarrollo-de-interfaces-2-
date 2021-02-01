package MVC.MC;

import base.Alumno;
import base.Asignatura;
import base.Examen;

import java.io.*;
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

    public ArrayList<Examen> getAlumnosNull() {
        ArrayList<Examen> examenesADevolver = new ArrayList<>();
        for (Examen examen : examenes){
            if(examen.getAlumno()==null)
                examenesADevolver.add(examen);

        }
        return examenesADevolver;
    }

    public ArrayList<Examen> getAsignaturasNull() {
        ArrayList<Examen> examenesADevolver = new ArrayList<>();
        for (Examen examen : examenes){
            if(examen.getAsignatura()==null)
                examenesADevolver.add(examen);
        }
        return examenesADevolver;
    }

    public void cambiarExamenesAlumno(Alumno alumnoABuscar, Alumno alumnoPorElQueCambiar){
        for(Examen examen : getExamenes()){
            if(examen.getAlumno().equals(alumnoABuscar))
                examen.setAlumno(alumnoPorElQueCambiar);
        }
    }

    public void cambiarExamenesAsignatura(Asignatura asignaturaABuscar, Asignatura asignaturaPorLaQueCambiar){
        if(asignaturaPorLaQueCambiar==null)
            System.out.print("Ha entrado");
        for(Examen examen : getExamenes()){
            if(examen.getAsignatura().equals(asignaturaABuscar))
                examen.setAsignatura(asignaturaPorLaQueCambiar);
        }
    }

    public void guardarDatos(File fichero) throws IOException {

        FileOutputStream fos = new FileOutputStream(fichero);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(examenes);
        oos.writeObject(alumnos);
        oos.writeObject(asignaturas);
        oos.close();
    }

    public void cargarDatos(File fichero) throws ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);

        examenes = (ArrayList<Examen>) ois.readObject();
        alumnos = (ArrayList<Alumno>) ois.readObject();
        asignaturas = (ArrayList<Asignatura>) ois.readObject();

        ois.close();
    }

    public void eliminarExamenesDeAlumnos(Examen examen) {
        for (Alumno alumno : getAlumnos()) {
            alumno.getExamenes().remove(examen);
        }
    }

    public int buscarExamen(String toString){
        for(Examen examen : getExamenes()){
            if(examen.toString().equals(toString)) {
                return getExamenes().indexOf(examen);
            }
        }
        return -1;
    }

    public void eliminarExamenesDeAsignaturas(Examen examen) {
        for (Asignatura asignatura : getAsignaturas()) {
            asignatura.getExamenes().remove(examen);
        }
    }


    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
