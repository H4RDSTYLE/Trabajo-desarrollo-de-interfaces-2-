package MVC.MC;

import Layouts.WrapLayout;
import base.Alumno;
import base.Asignatura;
import base.Examen;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends Component{
    private JPanel panel1;
    private JPanel panelCrear;
    private JButton btnAlumno;
    private JButton btnAsignatura;
    private JButton btnExamen;
    private JList listaAlumnos;
    private JList listAsignaturas;
    private JList listExamenes;
    private DefaultListModel<Alumno> modelAlumnos;
    private DefaultListModel<Examen> modelExamenes;
    private DefaultListModel<Asignatura> modelAsignatura;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JButton btnGuardar;
    private JButton btnCargar;
    private JButton button3;

    public VistaPrincipal() {
        initUI();
        initListas();

    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public DefaultListModel<Alumno> getModelAlumnos() {
        return modelAlumnos;
    }

    public DefaultListModel<Examen> getModelExamenes() {
        return modelExamenes;
    }

    public DefaultListModel<Asignatura> getModelAsignatura() {
        return modelAsignatura;
    }

    private void initListas() {
        modelAlumnos = new DefaultListModel();
        listaAlumnos.setModel(modelAlumnos);
        modelExamenes = new DefaultListModel<>();
        listExamenes.setModel(modelExamenes);
        modelAsignatura = new DefaultListModel<>();
        listAsignaturas.setModel(modelAsignatura);
    }


    public void initUI(){
        JFrame frame = new JFrame();
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setBounds(new Rectangle(640, 340));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.getRootPane().setDefaultButton(btnCargar);
        panelCrear.setLayout(new WrapLayout(FlowLayout.LEADING));
    }

    public JButton getBtnAlumno() {
        return btnAlumno;
    }

    public JButton getBtnAsignatura() {
        return btnAsignatura;
    }

    public JList getListaAlumnos() {
        return listaAlumnos;
    }

    public JList getListAsignaturas() {
        return listAsignaturas;
    }

    public JList getListExamenes() {
        return listExamenes;
    }

    public JButton getBtnExamen() {
        return btnExamen;
    }

}


