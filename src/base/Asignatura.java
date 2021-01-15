package base;

import java.io.Serializable;
import java.util.ArrayList;

public class Asignatura implements Serializable {

    private String nombre;
    private String rama;
    private int curso;
    private String etapa;
    private ArrayList<Examen> examenes;

    public Asignatura(String nombre, String rama, int curso, String etapa) {
        this.nombre = nombre;
        this.rama = rama;
        this.curso = curso;
        this.etapa = etapa;
        this.examenes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public int getCurso() {
        return curso;
    }

    public String getEtapa() {
        return etapa;
    }


    @Override
    public String toString() {
        return "nombre: " + nombre  + ", rama: " + rama  + ", curso: " + curso + ", etapa: " + etapa;
    }

    public ArrayList<Examen> getExamenes() {
        return examenes;
    }
}
