package MVC.MC;

import base.Alumno;
import base.Asignatura;
import base.Cromo;
import base.Examen;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents the model on a MVC code pattern.
 * @author Hugo
 * @version 1.0
 * @since JDK8
 */
public class Modelo {

    private ArrayList<Examen> examenes;
    private ArrayList<Alumno> alumnos;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Cromo> cromos;

    /**
     * Constructs the object modelo.
     */
    public Modelo(){
        examenes = new ArrayList<>();
        alumnos = new ArrayList<>();
        asignaturas = new ArrayList<>();
        cromos = new ArrayList<>();
    }

    /**
     * Método que obtiene los cromos de la aplicación
     * @return lista de cromos
     */
    public ArrayList<Cromo> getCromos() {
        return cromos;
    }

    /**
     * Método que establece una lista de cromos
     * @param cromos lista de cromos
     */
    public void setCromos(ArrayList<Cromo> cromos) {
        this.cromos = cromos;
    }

    /**
     * Método que devuelve una lista de los examenes
     * @return an ArrayList with the exams
     */
    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    /**
     * Método que establece una lista de examenes
     * @param examenes lista de examenes
     */
    public void setExamenes(ArrayList<Examen> examenes) {
        this.examenes = examenes;
    }

    /**
     *Método que devuelve una lista de alumnos
     * @return an ArrayList with the students
     */
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * Método que establece una lista de examenes
     * @param alumnos lista de examenes
     */
    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    /**
     * Método que obtiene una lista de las asignaturas
     * @return an ArrayList with the courses
     */
    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * Método que obtiene una lista de alumnos Nula
     * @return an ArrayList with the exams which have a null student
     */
    public ArrayList<Examen> getAlumnosNull() {
        ArrayList<Examen> examenesADevolver = new ArrayList<>();
        for (Examen examen : examenes){
            if(examen.getAlumno()==null)
                examenesADevolver.add(examen);

        }
        return examenesADevolver;
    }

    /**
     * Método que obtiene una lista de asginaturas Nula
     * @return an ArrayList with the exams which have a null course
     */
    public ArrayList<Examen> getAsignaturasNull() {
        ArrayList<Examen> examenesADevolver = new ArrayList<>();
        for (Examen examen : examenes){
            if(examen.getAsignatura()==null)
                examenesADevolver.add(examen);
        }
        return examenesADevolver;
    }

    /**
     * Changes the exams which a student has make
     * @param alumnoABuscar the student which you want to change the exams
     * @param alumnoPorElQueCambiar the student which you want to set the exam
     */
    public void cambiarExamenesAlumno(Alumno alumnoABuscar, Alumno alumnoPorElQueCambiar){
        if(!(alumnoABuscar.getExamenes().size()<=0)) {
            for (Examen examen : getExamenes()) {
                if ((examen.getAlumno()!=null)&&(examen.getAlumno().equals(alumnoABuscar)))
                    examen.setAlumno(alumnoPorElQueCambiar);
            }
        }
    }
    /**
     * Changes the exams which a course has make
     * @param asignaturaABuscar the course which you want to change the exams
     * @param asignaturaPorLaQueCambiar the course which you want to set the exam
     */
    public void cambiarExamenesAsignatura(Asignatura asignaturaABuscar, Asignatura asignaturaPorLaQueCambiar){
        if(asignaturaPorLaQueCambiar==null)
            System.out.print("Ha entrado");
        for(Examen examen : getExamenes()){
            if((examen.getAsignatura()!=null)&&examen.getAsignatura().equals(asignaturaABuscar))
                examen.setAsignatura(asignaturaPorLaQueCambiar);
        }
    }

    /**
     * Saves the data on a file
     * @param fichero Fichero en el que se guardarán los datos
     * @throws IOException Excepcion de E/S
     */
    public void guardarDatos(File fichero) throws IOException {

        FileOutputStream fos = new FileOutputStream(fichero);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(examenes);
        oos.writeObject(alumnos);
        oos.writeObject(asignaturas);
        oos.close();
    }

    /**
     * Charges the data of a file into the program
     * @param fichero Fichero en el cargarán los datos
     * @throws ClassNotFoundException Exception de clase no encontrada
     * @throws IOException Exception de E/S
     */
    public void cargarDatos(File fichero) throws ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);

        examenes = (ArrayList<Examen>) ois.readObject();
        alumnos = (ArrayList<Alumno>) ois.readObject();
        asignaturas = (ArrayList<Asignatura>) ois.readObject();

        ois.close();
    }

    /**
     * Deletes the exam of all the students which has it
     * @param examen Object Exam
     */
    public void eliminarExamenesDeAlumnos(Examen examen) {
        for (Alumno alumno : getAlumnos()) {
            alumno.getExamenes().remove(examen);
        }
    }

    /**
     * Gets a string of an exam and returns an exam
     * @param toString cadena de caracteres a buscar
     * @return Examen si se ha encontrados, -1 si no
     */
    public int buscarExamen(String toString){
        for(Examen examen : getExamenes()){
            if(examen.toString().equals(toString)) {
                return getExamenes().indexOf(examen);
            }
        }
        return -1;
    }

    /**
     * Deletes the course of all the students which has it
     * @param examen Object Exam
     */
    public void eliminarExamenesDeAsignaturas(Examen examen) {
        for (Asignatura asignatura : getAsignaturas()) {
            asignatura.getExamenes().remove(examen);
        }
    }

    /**
     * Sets asignaturas
     * @param asignaturas Lista de asignaturas
     */
    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * Método que actualiza los cromos
     * @param alumno Objeto ALumno
     * @param alumnoEditado Objeto Alumno
     */
    public void updateCromos(Alumno alumno, Alumno alumnoEditado) {
        for(Cromo cromo : getCromos()) {
            for (Alumno all : cromo.getAlumnos()) {
                if (all.equals(alumno)) {
                    cromo.getAlumnos().remove(alumno);
                    cromo.getAlumnos().add(alumnoEditado);
                }
            }
        }
    }
}
