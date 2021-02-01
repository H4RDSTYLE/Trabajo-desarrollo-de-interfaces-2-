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
    private DefaultComboBoxModel<String> modelLetra;
    private DefaultListModel<Asignatura> modelAsignatura;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JButton btnGuardar;
    private JButton btnCargar;
    private JPanel VerEditar;
    private JRadioButton ukRB;
    private JRadioButton esRB;
    private JComboBox comboBox1;
    private JPanel panelColor;
    private JPanel panelRellenar;
    private JPanel panelRellenar2;
    private JPanel panelRellenar3;
    private JLabel lblTipoLetra;
    private JButton aplicarButton;
    private JLabel lblColor;
    private JLabel lblIdioma;

    public VistaPrincipal() {
        initUI();
        initListas();
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
        frame.setTitle("AEA");
        frame.setContentPane(panel1);
        frame.setBounds(new Rectangle(660, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.getRootPane().setDefaultButton(btnCargar);
        panelCrear.setLayout(new WrapLayout(FlowLayout.LEADING));
        frame.setVisible(true);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public JTabbedPane getTabbedPane2() {
        return tabbedPane2;
    }

    public JPanel getVerEditar() {
        return VerEditar;
    }

    public JRadioButton getUkRB() {
        return ukRB;
    }

    public JPanel getPanelRellenar() {
        return panelRellenar;
    }

    public JPanel getPanelRellenar2() {
        return panelRellenar2;
    }

    public JPanel getPanelRellenar3() {
        return panelRellenar3;
    }

    public JPanel getPanelCrear() {
        return panelCrear;
    }

    public JRadioButton getRadioButton1() {
        return ukRB;
    }

    public JRadioButton getEsRB() {
        return esRB;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JPanel getPanelColor() {
        return panelColor;
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

    public JLabel getLblIdioma() {
        return lblIdioma;
    }

    public JLabel getLblColor() {
        return lblColor;
    }

    public JButton getAplicarButton() {
        return aplicarButton;
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

    public JLabel getLblTipoLetra() {
        return lblTipoLetra;
    }

    private void createUIComponents() {
        comboBox1 = new JComboBox(Toolkit.getDefaultToolkit().getFontList());
    }
}


