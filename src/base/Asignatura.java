package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A class which represents a course
 * @author Hugo Melon
 */
public class Asignatura implements Serializable {

    private String nombre;
    private String rama;
    private int curso;
    private String etapa;
    private ArrayList<Examen> examenes;

    /**
     * Constructs a  giving:
     * @param nombre The name of the course
     * @param rama The branch of the course
     * @param curso The curse of the course
     * @param etapa The stage of the course
     */
    public Asignatura(String nombre, String rama, int curso, String etapa) {
        this.nombre = nombre;
        this.rama = rama;
        this.curso = curso;
        this.etapa = etapa;
        this.examenes = new ArrayList<>();
    }

    /**
     *
     * @return the name
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the object
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     *
     * @return the branch
     */
    public String getRama() {
        return rama;
    }
    /**
     * Sets the branch of the object
     * @param rama
     */
    public void setRama(String rama) {
        this.rama = rama;
    }
    /**
     *
     * @return the course
     */
    public int getCurso() {
        return curso;
    }
    /**
     *
     * @return the stage
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     *
     * @return the object into a String
     */
    @Override
    public String toString() {
        String idioma = Locale.getDefault().toString();
        if(idioma.equals("es_ES"))
            return "nombre: " + nombre  + ", rama: " + rama  + ", curso: " + curso + ", etapa: " + etapa;
        else
            return "name: " + nombre + ", branch: " + rama + ", curse: " + curso + ", stage: " + etapa;
    }

    /**
     *
     * @return an ArrayList with the exams of the course
     */
    public ArrayList<Examen> getExamenes() {
        return examenes;
    }
}
