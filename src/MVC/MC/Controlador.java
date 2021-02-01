package MVC.MC;

import MVC.Vistas.Informacion.VistaInformacionAlumno;
import MVC.Vistas.Informacion.VistaInformacionAsignatura;
import MVC.Vistas.Informacion.VistaInformacionExamen;
import MVC.Vistas.crearAlumno;
import MVC.Vistas.crearAsignatura;
import MVC.Vistas.crearExamen;
import base.Alumno;
import base.Asignatura;
import base.Examen;
import sun.awt.windows.WFontConfiguration;

import javax.rmi.CORBA.Util;
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

public class Controlador implements ActionListener, ListSelectionListener, KeyListener, FocusListener, MouseListener, ChangeListener, ItemListener{
    private Modelo modelo;
    private VistaPrincipal vista;
    private String foco;
    private JColorChooser jcc;
    private AbstractColorChooserPanel acchp;

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

    private void addItemListeners(ItemListener listener) {
        vista.getComboBox1().addItemListener(listener);
    }

    private void setToolTips() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("idiomaResourceBundle");
        vista.getTabbedPane1().setToolTipTextAt(0, resourceBundle.getString("Crear"));
        vista.getTabbedPane1().setToolTipTextAt(1, resourceBundle.getString("Ver/Editar"));
        vista.getTabbedPane1().setToolTipTextAt(2, resourceBundle.getString("Opciones"));
        vista.getTabbedPane2().setToolTipTextAt(0, resourceBundle.getString("Ver Alumnos"));
        vista.getTabbedPane2().setToolTipTextAt(1, resourceBundle.getString("Ver Asignatura"));
        vista.getTabbedPane2().setToolTipTextAt(2, resourceBundle.getString("Ver Examenes"));
    }

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

    private void addMouseListeners(MouseListener listener) {
        vista.getListaAlumnos().addMouseListener(listener);
        vista.getListExamenes().addMouseListener(listener);
        vista.getListAsignaturas().addMouseListener(listener);
        vista.getPanelColor().addMouseListener(listener);
    }

    private void addKeyListener(KeyListener listener) {
        vista.getListAsignaturas().addKeyListener(listener);
        vista.getListExamenes().addKeyListener(listener);
        vista.getListaAlumnos().addKeyListener(listener);
    }

    public void refrescarListaAsignaturas(){
        vista.getModelAsignatura().clear();
        for(Asignatura asignatura : modelo.getAsignaturas()){
            vista.getModelAsignatura().addElement(asignatura);
        }
    }

    public void refrescarListaExamenes(){
        vista.getModelExamenes().clear();
        for(Examen examen : modelo.getExamenes()){
            vista.getModelExamenes().addElement(examen);
        }
    }

    public void refrescarListaAlumnos() {
        vista.getModelAlumnos().clear();
        for(Alumno alumno : modelo.getAlumnos()){
            vista.getModelAlumnos().addElement(alumno);
        }
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.getListaAlumnos().addListSelectionListener(listener);
        vista.getListAsignaturas().addListSelectionListener(listener);
        vista.getListExamenes().addListSelectionListener(listener);
    }

    private void addOnStateChangeListener(ChangeListener listener){
        jcc.getSelectionModel().addChangeListener(this);
    }

    private void addFocusListener(FocusListener listener){
        vista.getListaAlumnos().addFocusListener(listener);
        vista.getListExamenes().addFocusListener(listener);
        vista.getListAsignaturas().addFocusListener(listener);
    }

    private void addActionListener(ActionListener listener) {
        vista.getBtnCargar().addActionListener(listener);
        vista.getBtnGuardar().addActionListener(listener);
        vista.getBtnExamen().addActionListener(listener);
        vista.getBtnAlumno().addActionListener(listener);
        vista.getBtnAsignatura().addActionListener(listener);
        vista.getEsRB().addActionListener(listener);
        vista.getUkRB().addActionListener(listener);
        vista.getAplicarButton().addActionListener(listener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DELETE){
            if(foco.equals("alumno")){
                modelo.cambiarExamenesAlumno(modelo.getAlumnos().get(vista.getListaAlumnos().getSelectedIndex()), null);
                modelo.getAlumnos().remove(vista.getListaAlumnos().getSelectedIndex());
                refrescarListaAlumnos();
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
                refrescarListaAsignaturas();
                refrescarListaAlumnos();
            }
            refrescarListaExamenes();
        }
    }

    private void addPanelColor() {
        jcc = new JColorChooser();
        AbstractColorChooserPanel[] accp = jcc.getChooserPanels();
        acchp = accp[0];
        vista.getPanelColor().add(acchp);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.foco = e.getComponent().getName();
        System.out.println(foco);
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.foco = "";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            switch (e.getComponent().getName()){
                case "alumno":
                    VistaInformacionAlumno via = new VistaInformacionAlumno(modelo, vista.getListaAlumnos().getSelectedIndex());
                    refrescarListaAlumnos();
                    break;
                case "asignaturas":
                    VistaInformacionAsignatura viasign = new VistaInformacionAsignatura(modelo, vista.getListAsignaturas().getSelectedIndex());
                    refrescarListaAsignaturas();
                    break;
                case "examenes":
                    VistaInformacionExamen vie = new VistaInformacionExamen(modelo, vista.getListExamenes().getSelectedIndex());
                    refrescarListaExamenes();
                    break;
            }
        }
    }

    private void setActionCommand() {
        vista.getBtnCargar().setActionCommand("btnCargar");
        vista.getBtnGuardar().setActionCommand("btnGuardar");
        vista.getBtnExamen().setActionCommand("btnExamen");
        vista.getBtnAlumno().setActionCommand("btnAlumno");
        vista.getBtnAsignatura().setActionCommand("btnAsignatura");
        vista.getListaAlumnos().setName("alumno");
        vista.getListAsignaturas().setName("asignaturas");
        vista.getListExamenes().setName("examenes");
        vista.getUkRB().setActionCommand("UK");
        vista.getEsRB().setActionCommand("ES");
        vista.getAplicarButton().setActionCommand("aplicar");
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
    public void stateChanged(ChangeEvent e) {
        cambiarColor(jcc.getColor());
    }

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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED)
            cambiarFuente(new Font(e.getItem().toString(), Font.PLAIN, 14));
    }

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

    private void cambiarFuente(Font font) {
        vista.getLblTipoLetra().setFont(font);
        vista.getLblColor().setFont(font);
        vista.getTabbedPane1().setFont(font);
        vista.getTabbedPane2().setFont(font);
        vista.getLblIdioma().setFont(font);
        vista.getAplicarButton().setFont(font);
    }
}
