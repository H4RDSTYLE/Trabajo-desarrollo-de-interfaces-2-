package base;

import java.time.LocalDate;

public class Examen {
    private Asignatura asignatura;
    private Alumno alumno;
    private LocalDate fechaRealización;
    private float nota;
    private String profesor;

    public Examen(Asignatura asignatura, Alumno alumno, LocalDate fechaRealización, float nota, String profesor) {
        this.asignatura = asignatura;
        this.alumno = alumno;
        this.fechaRealización = fechaRealización;
        this.nota = nota;
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LocalDate getFechaRealización() {
        return fechaRealización;
    }

    public void setFechaRealización(LocalDate fechaRealización) {
        this.fechaRealización = fechaRealización;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
