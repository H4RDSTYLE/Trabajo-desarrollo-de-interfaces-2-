package MVC.MC;

import MVC.Vistas.*;
import MVC.Vistas.Informacion.VistaInformacionAlumno;
import MVC.Vistas.Informacion.VistaInformacionAsignatura;
import MVC.Vistas.Informacion.VistaInformacionCromo;
import MVC.Vistas.Informacion.VistaInformacionExamen;
import base.Alumno;
import base.Asignatura;
import base.Cromo;
import base.Examen;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Class which represents a Controller using the code pattern of model-view-controller
 * @author Hugo
 * @since JDK8
 * @version 1.0
 */
public class Controlador implements ActionListener, ListSelectionListener, KeyListener, FocusListener, MouseListener, ChangeListener, ItemListener{
    private Modelo modelo;
    private VistaPrincipal vista;
    private String foco;
    private JColorChooser jcc;
    private AbstractColorChooserPanel acchp;

    /**
     * Constructs a Controller using:
     * @param modelo A model
     * @param vista A view
     */
    public Controlador(Modelo modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        setActionCommand();
        addPanelColor();
        acchp.setName("color");
        addActionListener(this);
        addListSelectionListener(this);
        addFocusListener(this);
        addKeyListener(this);
        addMouseListeners(this);
        addOnStateChangeListener(this);
        addItemListeners(this);
        setMnemonics();
        setToolTips();
        cambiarFuente(new Font(obtenerTipoLetra(), Font.BOLD, 14));
        setConfiguracion();
    }

    /**
     * Reads the configuration file and changes the view of the program using the information readed.
     */
    private void setConfiguracion() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));

            String pais = properties.getProperty("pais");

            if(pais.equals("ES")){
                vista.getEsRB().setSelected(true);
            }else {
                vista.getUkRB().setSelected(true);
            }
            Color color = new Color(Integer.parseInt(properties.getProperty("color")));
            cambiarColor(color);
            Font font = new Font(properties.getProperty("tipoLetra"), Font.BOLD, 14);
            cambiarFuente(font);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds an ItemListener to the objects which needs it
     * @param listener ItemListener
     */
    private void addItemListeners(ItemListener listener) {
        vista.getComboBox1().addItemListener(listener);
    }

    /**
     * Sets the tooltips of the view
     */
    private void setToolTips() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idiomaResourceBundle");
        vista.getTabbedPane1().setToolTipTextAt(0, resourceBundle.getString("Crear"));
        vista.getTabbedPane1().setToolTipTextAt(1, resourceBundle.getString("Ver/Editar"));
        vista.getTabbedPane1().setToolTipTextAt(2, resourceBundle.getString("Opciones"));
        vista.getTabbedPane2().setToolTipTextAt(0, resourceBundle.getString("Ver Alumnos"));
        vista.getTabbedPane2().setToolTipTextAt(1, resourceBundle.getString("Ver Asignatura"));
        vista.getTabbedPane2().setToolTipTextAt(2, resourceBundle.getString("Ver Examenes"));
    }

    /**
     * Sets the Mnemonics of the view
     */
    private void setMnemonics() {
        vista.getBtnGuardar().setMnemonic(KeyEvent.VK_G);
        vista.getBtnGuardar().setMnemonic(KeyEvent.VK_H);
        vista.getTabbedPane1().setMnemonicAt(0, KeyEvent.VK_C);
        vista.getTabbedPane1().setMnemonicAt(1, KeyEvent.VK_V);
        vista.getTabbedPane1().setMnemonicAt(2, KeyEvent.VK_O);
        vista.getTabbedPane2().setMnemonicAt(0, KeyEvent.VK_A);
        vista.getTabbedPane2().setMnemonicAt(1, KeyEvent.VK_S);
        vista.getTabbedPane2().setMnemonicAt(2, KeyEvent.VK_E);
    }

    /**
     * Adds a MouseListener to the objects which needs it
     * @param listener MouseListener
     */
    private void addMouseListeners(MouseListener listener) {
        vista.getListaAlumnos().addMouseListener(listener);
        vista.getListExamenes().addMouseListener(listener);
        vista.getListAsignaturas().addMouseListener(listener);
        vista.getPanelColor().addMouseListener(listener);
        vista.getCromosJList().addMouseListener(listener);
    }

    /**
     * Adds a KeyListener to the objects which needs it
     * @param listener KeyListener
     */
    private void addKeyListener(KeyListener listener) {
        vista.getListAsignaturas().addKeyListener(listener);
        vista.getListExamenes().addKeyListener(listener);
        vista.getListaAlumnos().addKeyListener(listener);
        vista.getCromosJList().addKeyListener(listener);
    }

    /**
     * Refresh the list of the courses
     */
    public void refrescarListaAsignaturas(){
        vista.getModelAsignatura().clear();
        for(Asignatura asignatura : modelo.getAsignaturas()){
            vista.getModelAsignatura().addElement(asignatura);
        }
    }

    /**
     * Refresh the list of exams
     */
    public void refrescarListaExamenes(){
        vista.getModelExamenes().clear();
        for(Examen examen : modelo.getExamenes()){
            vista.getModelExamenes().addElement(examen);
        }
    }

    /**
     * Refresh the list of students
     */
    public void refrescarListaAlumnos() {
        vista.getModelAlumnos().clear();
        for(Alumno alumno : modelo.getAlumnos()){
            vista.getModelAlumnos().addElement(alumno);
        }
    }

    /**
     * Adds an ListSelectionListener to the objects which needs it
     * @param listener ListSelectionListener
     */
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.getListaAlumnos().addListSelectionListener(listener);
        vista.getListAsignaturas().addListSelectionListener(listener);
        vista.getListExamenes().addListSelectionListener(listener);
    }
    /**
     * Adds an ChangeListener to the objects which needs it
     * @param listener ChangeListener
     */
    private void addOnStateChangeListener(ChangeListener listener){
        jcc.getSelectionModel().addChangeListener(this);
    }
    /**
     * Adds a FocusListener to the objects which needs it
     * @param listener FocusListener
     */
    private void addFocusListener(FocusListener listener){
        vista.getListaAlumnos().addFocusListener(listener);
        vista.getListExamenes().addFocusListener(listener);
        vista.getListAsignaturas().addFocusListener(listener);
        vista.getCromosJList().addFocusListener(listener);
    }

    /**
     * Adds an ActionListener to the objects which needs it
     * @param listener ActionListener
     */
    private void addActionListener(ActionListener listener) {
        vista.getBtnCargar().addActionListener(listener);
        vista.getBtnGuardar().addActionListener(listener);
        vista.getBtnExamen().addActionListener(listener);
        vista.getBtnAlumno().addActionListener(listener);
        vista.getBtnAsignatura().addActionListener(listener);
        vista.getEsRB().addActionListener(listener);
        vista.getUkRB().addActionListener(listener);
        vista.getAplicarButton().addActionListener(listener);
        vista.getBtnCromo().addActionListener(listener);
        vista.itemInformes.addActionListener(listener);
        vista.itemManualUser.addActionListener(listener);
    }

    /**
     *Método que reacciona ante el ActionListener
     * @param e This method controls which object has received an actionEvent and does something.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Informes":
                DialogoInformes dialogoInformes = new DialogoInformes(modelo);
                break;
            case "ManualUser":
                SwingController controller = new SwingController();
                SwingViewBuilder factory = new SwingViewBuilder(controller);


                JPanel viewerPanel = factory.buildViewerPanel();


                JFrame frame = new JFrame("Visor Pdf");
                frame.setContentPane(viewerPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                controller.openDocument(getClass().getResource("/MANUAL_DE_USUARIO.pdf"));
                break;
            case "UK":
                vista.getEsRB().setSelected(false);
                Locale.setDefault(Locale.ENGLISH);
                break;
            case "ES":
                vista.getUkRB().setSelected(false);
                Locale.setDefault(new Locale("es", "ES"));
                break;
            case "btnAlumno":
                crearAlumno ca = new crearAlumno(modelo, this);
                break;
            case "btnAsignatura":
                crearAsignatura crea = new crearAsignatura(modelo, this);
                break;
            case "btnExamen":
                crearExamen ce = new crearExamen(modelo, this);
                break;
            case "btnCargar":
                JFileChooser selector = new JFileChooser();
                int opt = selector.showOpenDialog(vista);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    File fichero = selector.getSelectedFile();
                    try {
                        modelo.cargarDatos(fichero);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                refrescarListaAsignaturas();
                refrescarListaAlumnos();
                refrescarListaExamenes();
                refrescarListaCromos();
                break;
            case  "btnGuardar":
                selector = new JFileChooser();
                opt = selector.showSaveDialog(vista);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    File fichero = selector.getSelectedFile();
                    try {
                        modelo.guardarDatos(fichero);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case "cromo":
                crearCromo cc = new crearCromo(modelo);
                refrescarListaCromos();
                break;
            case "aplicar":
                Properties propiedades = new Properties();
                String idioma;
                String pais;
                if(vista.getEsRB().isSelected()){
                    idioma = "es";
                    pais = "ES";
                } else {
                    idioma = "en";
                    pais = "UK";
                }
                propiedades.setProperty("idioma", idioma);
                propiedades.setProperty("pais", pais);
                propiedades.setProperty("color", String.valueOf(jcc.getColor().getRGB()));
                propiedades.setProperty("tipoLetra", vista.getComboBox1().getSelectedItem().toString());

                try {
                    propiedades.store(new FileWriter("data/preferencias.conf"), "Fichero de preferencias");
                } catch (IOException we) {
                    we.printStackTrace();
                }
                System.exit(0);
        }
    }

    /**
     * Método que refresca la lista de los cromos
     */
    private void refrescarListaCromos() {
        vista.getModelCromo().clear();
        for(Cromo cromo : modelo.getCromos()){
            vista.getModelCromo().addElement(cromo);
        }
    }

    /**
     * Método que reacciona ante la liberación de una tecla que ha sido pulsada
     * @param e gets which object has received an actionEvent and does something
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DELETE){
            if(foco.equals("alumno")){
                modelo.cambiarExamenesAlumno(modelo.getAlumnos().get(vista.getListaAlumnos().getSelectedIndex()), null);
                modelo.getAlumnos().remove(vista.getListaAlumnos().getSelectedIndex());
            }
            if(foco.equals("asignaturas")){
                modelo.cambiarExamenesAsignatura(modelo.getAsignaturas().get(vista.getListAsignaturas().getSelectedIndex()), null);
                modelo.getAsignaturas().remove(vista.getListAsignaturas().getSelectedIndex());
                refrescarListaAsignaturas();
            }
            if(foco.equals("examenes")){
                modelo.eliminarExamenesDeAlumnos(modelo.getExamenes().get(vista.getListExamenes().getSelectedIndex()));
                modelo.eliminarExamenesDeAsignaturas(modelo.getExamenes().get(vista.getListExamenes().getSelectedIndex()));
                modelo.getExamenes().remove(vista.getListExamenes().getSelectedIndex());
            }
            if(foco.equals("cromos")){
                modelo.getCromos().remove(vista.getCromosJList().getSelectedIndex());
            }
            refrescarListaAsignaturas();
            refrescarListaAlumnos();
            refrescarListaExamenes();
            refrescarListaCromos();
        }
    }

    /**
     * Adds a ColorChooser panel
     */
    private void addPanelColor() {
        jcc = new JColorChooser();
        AbstractColorChooserPanel[] accp = jcc.getChooserPanels();
        acchp = accp[0];
        vista.getPanelColor().add(acchp);
    }

    /**
     * Changes the variable "foco" when the focus changes.
     * @param e FocusEvent
     */
    @Override
    public void focusGained(FocusEvent e) {
        this.foco = e.getComponent().getName();
    }

    /**
     * Changes the variable foco to "" when the focus is lost
     * @param e FocusEvent
     */
    @Override
    public void focusLost(FocusEvent e) {
        this.foco = "";
    }

    /**
     * Gets a Mouse Event, checks the object which has received it and does something.
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            switch (e.getComponent().getName()){
                case "alumno":
                    VistaInformacionAlumno via = new VistaInformacionAlumno(modelo, vista.getListaAlumnos().getSelectedIndex());
                    break;
                case "asignaturas":
                    VistaInformacionAsignatura viasign = new VistaInformacionAsignatura(modelo, vista.getListAsignaturas().getSelectedIndex());
                    break;
                case "examenes":
                    VistaInformacionExamen vie = new VistaInformacionExamen(modelo, vista.getListExamenes().getSelectedIndex());
                    break;
                case "cromos":
                    VistaInformacionCromo vic = new VistaInformacionCromo(modelo, vista.getCromosJList().getSelectedIndex());
                    break;
            }
            refrescarListaAlumnos();
            refrescarListaCromos();
            refrescarListaAsignaturas();
            refrescarListaExamenes();
        }
    }

    /**
     * Sets action commands on all needed objects
     */
    private void setActionCommand() {
        vista.getBtnCargar().setActionCommand("btnCargar");
        vista.getBtnGuardar().setActionCommand("btnGuardar");
        vista.getBtnExamen().setActionCommand("btnExamen");
        vista.getBtnAlumno().setActionCommand("btnAlumno");
        vista.getBtnAsignatura().setActionCommand("btnAsignatura");
        vista.getListaAlumnos().setName("alumno");
        vista.getListAsignaturas().setName("asignaturas");
        vista.getListExamenes().setName("examenes");
        vista.getCromosJList().setName("cromos");
        vista.getUkRB().setActionCommand("UK");
        vista.getEsRB().setActionCommand("ES");
        vista.getAplicarButton().setActionCommand("aplicar");
        vista.getBtnCromo().setActionCommand("cromo");
    }

    /**
     * On color changed changes the color of the view
     * @param e ChangeEvent
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        cambiarColor(jcc.getColor());
    }

    /**
     * Changes the color of the view
     * @param color Color
     */
    private void cambiarColor(Color color) {
        vista.getPanelColor().setBackground(color);
        vista.getPanelCrear().setBackground(color);
        vista.getPanel1().setBackground(color);
        vista.getPanelRellenar().setBackground(color);
        vista.getPanelRellenar2().setBackground(color);
        vista.getPanelRellenar3().setBackground(color);
        acchp.setBackground(color);
        vista.getTabbedPane2().setBackground(color);
        vista.getTabbedPane1().setBackground(color);
    }

    /**
     * Changes the font
     * @param e itemEvent
     */

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED)
            cambiarFuente(new Font(e.getItem().toString(), Font.PLAIN, 14));
    }

    /**
     * Método que obtiene el tipo de Letra
     * @return the font of the preferences file.
     */
    public String obtenerTipoLetra() {

        Locale locale = null;

        Properties properties = new Properties();
        String tipoLetra = "";
        try {
            properties.load(new FileReader("data/preferencias.conf"));
            tipoLetra = properties.getProperty("tipoLetra");
        }catch (Exception e){
            e.printStackTrace();
        }
        return tipoLetra;
    }

    /**
     * Changes the font of the view
     * @param font Font
     */
    private void cambiarFuente(Font font) {
        vista.getLblTipoLetra().setFont(font);
        vista.getLblColor().setFont(font);
        vista.getTabbedPane1().setFont(font);
        vista.getTabbedPane2().setFont(font);
        vista.getLblIdioma().setFont(font);
        vista.getAplicarButton().setFont(font);
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
