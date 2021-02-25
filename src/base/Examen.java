package base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;
/**
 * A class which represents an exam
 * @author Hugo Melon
 * @since JDK8
 * @version 1.0
 */
public class Examen implements Serializable {
    private Asignatura asignatura;
    private Alumno alumno;
    private LocalDate fechaRealizacion;
    private float nota;
    private String profesor;

    /**
     * Construct an exam using:
     * @param asignatura An object course
     * @param alumno An object alumn
     * @param fechaRealizacion The date of realization
     * @param nota The mark
     * @param profesor The professor which was present during the exam
     */

    public Examen(Asignatura asignatura, Alumno alumno, LocalDate fechaRealizacion, float nota, String profesor) {
        this.asignatura = asignatura;
        this.alumno = alumno;
        this.fechaRealizacion = fechaRealizacion;
        this.nota = nota;
        this.profesor = profesor;
    }

    /**
     *
     * @return An object Course
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     *
     * @param asignatura sets the course
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
    /**
     *
     * @return An object Student
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     *
     * @param alumno Sets the student
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    /**
     *
     * @return A localdate object with the date of realization
     */
    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }
    /**
     * Sets the date of realization
     * @param fechaRealizacion fecha
     */
    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    /**
     *
     * @return the mark
     */
    public float getNota() {
        return nota;
    }

    /**
     * Sets the mark
     * @param nota nota
     */
    public void setNota(float nota) {
        this.nota = nota;
    }
    /**
     *
     * @return The professor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Sets the professor
     * @param profesor profesor
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
    /**
     *
     * @return An string with all the object information
     */
    @Override
    public String toString() {
        String idioma = Locale.getDefault().toString();
        if(idioma.equals("es_ES"))
            return "asignatura: " + getAsignaturaToString() + ", alumno: " + getAlumnoToString() + ", fecha de realización: " + fechaRealizacion + ", nota: " + nota + ", profesor: " + profesor;
        else
            return "subject: " + getAsignaturaToString() + ", alumn: " + getAlumnoToString() + ", date of realization: " + fechaRealizacion + ", mark: " + nota + ", professor: " + profesor;
    }

    /**
     *
     * @return The toString of the student
     */
    private String getAlumnoToString() {
        String aDevolver = "";
        if(alumno==null){
            aDevolver+= " ";
        }else{
            aDevolver+= alumno.getNombre();
        }
        return aDevolver;
    }
    /**
     *
     * @return The toString of the course
     */
    public String getAsignaturaToString(){
        String aDevolver = "";
        if(asignatura==null){
            aDevolver+= " ";
        }else{
            aDevolver += asignatura.getNombre();
        }
        return aDevolver;
    }
}
