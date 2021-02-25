package MVC.MC;

import Layouts.WrapLayout;
import base.Alumno;
import base.Asignatura;
import base.Cromo;
import base.Examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Class which represents a View on a MVC pattern
 * @author Hugo
 * @since JDK8
 * @version 1.0
 */
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
    private DefaultListModel<Cromo> modelCromo;
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
    private JButton btnCromo;
    private JList cromosJList;
    JMenuItem itemInformes;
    JMenuItem itemManualUser;
    JFrame frame;

    /**
     * Constructor of the view object
     */
    public VistaPrincipal() {
        initUI();
        initListas();
    }

    /**
     * init the lists
     */
    private void initListas() {
        modelAlumnos = new DefaultListModel();
        listaAlumnos.setModel(modelAlumnos);
        modelExamenes = new DefaultListModel<>();
        listExamenes.setModel(modelExamenes);
        modelAsignatura = new DefaultListModel<>();
        listAsignaturas.setModel(modelAsignatura);
        modelCromo = new DefaultListModel<>();
        cromosJList.setModel(modelCromo);
    }

    /**
     * inits the graphic aspect of the UI
     */
    public void initUI(){
        frame = new JFrame();
        frame.setTitle("AEA");
        frame.setContentPane(panel1);
        frame.setBounds(new Rectangle(660, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.getRootPane().setDefaultButton(btnCargar);
        panelCrear.setLayout(new WrapLayout(FlowLayout.LEADING));
        crearMenu();
        frame.setVisible(true);
    }

    /**
     * Método que crea el menú
     */
    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        frame.setJMenuBar(barra);

        JMenu menuVista = new JMenu("Vista");
        barra.add(menuVista);

        itemInformes = new JMenuItem("Informes");
        itemInformes.setActionCommand("Informes");
        itemInformes.setIcon(new ImageIcon(getClass().getResource("/reporte.png")));

        menuVista.add(itemInformes);

        //Menu Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        barra.add(menuAyuda);
        itemManualUser = new JMenuItem("Manual de Usuario");
        itemManualUser.setActionCommand("ManualUser");
        itemManualUser.setIcon(new ImageIcon(getClass().getResource("/informacion.png")));

        menuAyuda.add(itemManualUser);

        itemInformes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        itemManualUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
    }

    /**
     * Método que accede al panel1
     * @return panel1
     */
    public JPanel getPanel1() {
        return panel1;
    }

    /**
     * Método que accede al TabbedPane1
     * @return tabbedPane1
     */
    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    /**
     * Método que accede al TabbedPane2
     * @return tabbedPane2
     */
    public JTabbedPane getTabbedPane2() {
        return tabbedPane2;
    }

    /**
     * Método que accede al JList de Cromos
     * @return cromosJList
     */
    public JList getCromosJList() {
        return cromosJList;
    }

    /**
     * Método que accede al DefaultListModel de los cromos
     * @return modelCromo
     */
    public DefaultListModel<Cromo> getModelCromo() {
        return modelCromo;
    }

    /**
     * Método que establece el DefaultListModel de los cromos
     * @param modelCromo DefaultListModel de los cromos
     */
    public void setModelCromo(DefaultListModel<Cromo> modelCromo) {
        this.modelCromo = modelCromo;
    }

    /**
     * Método que establece el JList de los cromos
     * @param cromosJList JList de los cromos
     */
    public void setCromosJList(JList cromosJList) {
        this.cromosJList = cromosJList;
    }

    /**
     * Getter del btnCromo
     * @return btnCromo
     */
    public JButton getBtnCromo() {
        return btnCromo;
    }

    /**
     * Setter del btnCromo
     * @param btnCromo boton
     */
    public void setBtnCromo(JButton btnCromo) {
        this.btnCromo = btnCromo;
    }

    /**
     * Getter del verEditar
     * @return VerEditar
     */
    public JPanel getVerEditar() {
        return VerEditar;
    }

    /**
     * Getter del UkRB
     * @return ukRB
     */
    public JRadioButton getUkRB() {
        return ukRB;
    }

    /**
     * Getter del PanelRellenar
     * @return panelRellenar
     */
    public JPanel getPanelRellenar() {
        return panelRellenar;
    }

    /**
     * Getter del PanelRellenar2
     * @return panelRellenar2
     */
    public JPanel getPanelRellenar2() {
        return panelRellenar2;
    }

    /**
     * Getter PanelRellenar3
     * @return panelRellenar3
     */
    public JPanel getPanelRellenar3() {
        return panelRellenar3;
    }

    /**
     * Getter PanelCrear
     * @return panelCrear
     */
    public JPanel getPanelCrear() {
        return panelCrear;
    }

    /**
     * Getter RadioButton1
     * @return ukRB
     */
    public JRadioButton getRadioButton1() {
        return ukRB;
    }

    /**
     * Getter EsRb
     * @return esRB
     */
    public JRadioButton getEsRB() {
        return esRB;
    }

    /**
     * Getter ComboBox1
     * @return comboBox1
     */
    public JComboBox getComboBox1() {
        return comboBox1;
    }

    /**
     * Getter PanelColor
     * @return panelColor
     */
    public JPanel getPanelColor() {
        return panelColor;
    }

    /**
     * Getter BtnGuardar
     * @return btnGuardar
     *
     */
    public JButton getBtnGuardar() { return btnGuardar; }

    /**
     * Getter BtnCgargar
     * @return btnCargar
     */
    public JButton getBtnCargar() { return btnCargar; }

    /**
     * Getter DefaultListModel ALumnos
     * @return modelAlumnos
     */
    public DefaultListModel<Alumno> getModelAlumnos() { return modelAlumnos; }

    /**
     * Getter DefaultListModel Examen
     * @return modelExamenes
     */
    public DefaultListModel<Examen> getModelExamenes() { return modelExamenes; }

    /**
     * Getter DefaultListModel Asignaturas
     * @return modelAsignatura
     */
    public DefaultListModel<Asignatura> getModelAsignatura() { return modelAsignatura; }

    /**
     * Getter LblIdioma
     * @return lblIdioma
     */
    public JLabel getLblIdioma() { return lblIdioma; }

    /**
     * Getter LblColor
     * @return lblColor
     */
    public JLabel getLblColor() { return lblColor; }

    /**
     * Getter AplicarButton
     * @return aplicarButton
     */
    public JButton getAplicarButton() { return aplicarButton; }

    /**
     * Getter BtnAlumno
     * @return btnAlumno
     */
    public JButton getBtnAlumno() { return btnAlumno; }

    /**
     * Getter BtnAsigntura
     * @return btnAsignatura
     */
    public JButton getBtnAsignatura() { return btnAsignatura; }

    /**
     * Getter ListaAlumnos
     * @return listaAlumnos
     */
    public JList getListaAlumnos() { return listaAlumnos; }

    /**
     * Getter Lista Asignaturas
     * @return listAsignaturas
     */
    public JList getListAsignaturas() { return listAsignaturas; }

    /**
     * Getter ListExamenes
     * @return listExamenes
     */
    public JList getListExamenes() { return listExamenes; }

    /**
     * Getter BtnExamen
     * @return btnExamen
     */
    public JButton getBtnExamen() { return btnExamen; }

    /**
     * Getter LblTipoLetra
     * @return lblTipoLetra
     */
    public JLabel getLblTipoLetra() { return lblTipoLetra; }

    /**
     * Sets a fontList onto a comboBox.
     */
    private void createUIComponents() {
        comboBox1 = new JComboBox(Toolkit.getDefaultToolkit().getFontList());
    }
}


