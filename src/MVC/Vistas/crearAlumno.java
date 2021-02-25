package MVC.Vistas;

import Excepciones.CampoBlancoException;
import Excepciones.DniRepetidoException;
import Excepciones.IDRepeatedException;
import Excepciones.WhiteCampException;
import MVC.MC.Controlador;
import MVC.MC.Modelo;
import base.Alumno;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Locale;

/**
 *
 * JDialog which is used to create a student
 * @author Hugo
 * @version 1.0
 * @since JDK8
 */
public class crearAlumno extends JDialog {
    private JPanel contentPane;
    private JButton btnCrear;
    private JButton btnSalir;
    private JTextField tfNombre;
    private JRadioButton helicopteroRB;
    private JRadioButton femaleRB;
    private JRadioButton maleRB;
    private JTextField tfDNI;
    private DatePicker dp;
    private JRadioButton otherRB;
    private JButton nuevoGeneroBtn;
    private JLabel lblNuevoGenero;
    private JLabel lblHelicoptero;
    private JLabel lblFemenino;
    private JLabel lblMasculino;
    private Modelo modelo;
    private Controlador controlador;

    /**
     * Creates the object
     * @param modelo modelo
     * @param controlador controlador
     */
    public crearAlumno(Modelo modelo, Controlador controlador) {
        dp.setDate(LocalDate.now());
        this.modelo = modelo;
        this.controlador = controlador;
        addActionListener();
        initUI();
    }

    /**
     * Inits the Graphic Interface
     */
    private void initUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnSalir);
        setBounds(new Rectangle(600,300));
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
        dp.setDate(LocalDate.now());
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    /**
     * Adds Listeners
     */
    private void addActionListener() {
        femaleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(femaleRB.isSelected()){
                    maleRB.setSelected(false);
                    helicopteroRB.setSelected(false);
                    otherRB.setSelected(false);
                }
            }
        });

        maleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(maleRB.isSelected()){
                    femaleRB.setSelected(false);
                    helicopteroRB.setSelected(false);
                    otherRB.setSelected(false);
                }
            }
        });

        helicopteroRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(helicopteroRB.isSelected()){
                    femaleRB.setSelected(false);
                    maleRB.setSelected(false);
                    otherRB.setSelected(false);
                }
            }
        });
        otherRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(otherRB.isSelected()){
                    femaleRB.setSelected(false);
                    maleRB.setSelected(false);
                    helicopteroRB.setSelected(false);
                }
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearAlumno();
            }
        });
        nuevoGeneroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selector = new JFileChooser();
                int opt = selector.showOpenDialog(getParent());

                if (opt == JFileChooser.APPROVE_OPTION) {
                    File fichero = selector.getSelectedFile();
                    lblNuevoGenero.setName(fichero.getPath());
                    lblNuevoGenero.setIcon(escalarImagen(new ImageIcon(fichero.getPath()), 55, 55));
                }
            }
        });
    }

    /**
     * Creates the student
     */
    public void crearAlumno(){
        if(comprobarYCrearAlumno())
            limpiarCampos();
        controlador.refrescarListaAlumnos();
    }

    /**
     * Checks if the alumn is OK and creates it
     * @return
     */
    private boolean comprobarYCrearAlumno() {
        try{
            String nombre = tfNombre.getText();
            String dni = tfDNI.getText();
            LocalDate date = dp.getDate();
            ImageIcon genero = new ImageIcon();
            String generoo = "";
            if(helicopteroRB.isSelected()) {
                genero = (ImageIcon) lblHelicoptero.getIcon();
                generoo = "Helicoptero Apache";
            }
            if(maleRB.isSelected()) {
                genero = (ImageIcon) lblMasculino.getIcon();
                generoo = "Masculino";
            }
            if(femaleRB.isSelected()) {
                genero = (ImageIcon) lblFemenino.getIcon();
                generoo = "Femenino";
            }
            if(otherRB.isSelected()) {
                genero = (ImageIcon) lblNuevoGenero.getIcon();
                generoo = "No binario";
            }
            try {
                Image imagen = genero.getImage();
            }catch (Exception exc){
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("Genero no Binario");
                else
                    throw new WhiteCampException("Non-binary gender");
            }
            if(maleRB.isSelected()==false && otherRB.isSelected()==false && femaleRB.isSelected()==false && helicopteroRB.isSelected()==false)
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("genero");
                else
                    throw new WhiteCampException("gender");
            if (nombre.isEmpty())
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("nombre");
                else
                    throw new WhiteCampException("name");
            if (dni.isEmpty())
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("dni");
                else
                    throw new WhiteCampException("id");
            for(Alumno alumno : modelo.getAlumnos()){
                if(alumno.getDni().equals(dni))
                    if(Locale.getDefault().toString().equals("es_ES"))
                        throw new DniRepetidoException();
                    else
                        throw new IDRepeatedException();
            }
            Alumno alumno = new Alumno(nombre, date, dni, genero, generoo);
            modelo.getAlumnos().add(alumno);
            if(Locale.getDefault().toString().equals("es_ES"))
                JOptionPane.showMessageDialog(null, "Alumno introducida con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Alumn introduced with exitation", "Information", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Cleans the form
     */
    private void limpiarCampos() {
        helicopteroRB.setSelected(true);
        femaleRB.setSelected(false);
        maleRB.setSelected(false);
        tfNombre.setText("");
        tfDNI.setText("");
        dp.setDate(LocalDate.now());
    }

    /**
     * Escales the image
     * @param icon imagen
     * @param height alto
     * @param width ancho
     * @return imagen
     */
    public ImageIcon escalarImagen(ImageIcon icon, int height, int width){
        Image image = icon.getImage();
        Image imagenEscalada = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
}
