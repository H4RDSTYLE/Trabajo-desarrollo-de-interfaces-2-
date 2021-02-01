package MVC.Vistas.Informacion;

import Excepciones.CampoBlancoException;
import Excepciones.WhiteCampException;
import MVC.MC.Modelo;
import base.Alumno;
import base.Examen;
import com.github.lgooddatepicker.components.DatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class VistaInformacionAlumno extends JDialog implements KeyListener{
    private int posicion;
    private Modelo modelo;
    private JPanel contentPane;
    private JTextField tfNombre;
    private JTextField tfDNI;
    private JRadioButton radioButtonHelicoptero;
    private JRadioButton radioButtonFemenino;
    private JRadioButton radioButtonMasculino;
    private JButton btnEditar;
    private JButton btnCancelar;
    private DatePicker dp;
    private JList listExamenes;
    private JTabbedPane tabbedPane1;
    private JPanel panelExamenes;
    private JRadioButton otherRB;
    private JButton btnNuevoGenero;
    private JLabel lblNuevoGenero;
    private JLabel lblHelicoptero;
    private JLabel lblFemenino;
    private JLabel lblMasculino;
    private JRadioButton barrasRB;
    private JRadioButton tartaRadioButton;
    private JPanel tartaRB;
    private JScrollPane paanel;
    private JPanel panelGraficos;
    private JButton btnModificarExamen;
    private JList listExamenesNull;
    private JButton btnExamenAnadir;
    private JPanel btnAnadirExamen;
    private DefaultListModel<String> examenDefaultListModel;
    private Alumno alumno;

    public VistaInformacionAlumno(Modelo modelo, int posicion) {
        this.posicion = posicion;
        this.modelo = modelo;
        actionListeners();
        this.alumno = modelo.getAlumnos().get(posicion);
        setInformacion(alumno);
        crearGrafico("barras");
        anadirExamenesAAnadir();
        initUI();
        internacionalizarTabbedPane();
    }

    private void internacionalizarTabbedPane() {
        ResourceBundle bundle = ResourceBundle.getBundle("idiomaResourceBundle");
        tabbedPane1.getTabComponentAt(0).setName(bundle.getString("Examenes"));
        tabbedPane1.getTabComponentAt(1).setName(bundle.getString("Gráfico Examenes"));
        tabbedPane1.getTabComponentAt(2).setName(bundle.getString("AgregarExamen"));
    }

    private void anadirExamenesAAnadir() {
        DefaultListModel<Examen> listModelNullExamen = new DefaultListModel<>();
        listModelNullExamen.clear();
        for(Examen examen : modelo.getAlumnosNull())
            listModelNullExamen.addElement(examen);
        listExamenesNull.setModel(listModelNullExamen);
    }

    private void crearGrafico(String tipo) {
        panelGraficos.removeAll();
        DefaultCategoryDataset dpd = new DefaultCategoryDataset();
        DefaultPieDataset dpid = new DefaultPieDataset();
        if(alumno.getExamenes().size()!=0) {
            for (int i = 0; i < alumno.getExamenes().size(); i++) {
                Examen examen = alumno.getExamenes().get(i);
                dpd.setValue(examen.getNota(), i + ". " + examen.getAsignaturaToString(), examen.getAsignaturaToString());
                dpid.setValue(i + ". " + examen.getAsignaturaToString(), examen.getNota());
            }
            JFreeChart jfc;
            if (tipo.equals("barras")) {
                jfc = ChartFactory.createBarChart("Grafica de Barras", "Asignatura", "Nota", dpd, PlotOrientation.VERTICAL, true, true, false);
            }
            else
                jfc = ChartFactory.createPieChart("Grafica de Tarta", dpid, true, true, false);
            ChartPanel cp = new ChartPanel(jfc, true);
            panelGraficos.add(cp);
            panelGraficos.revalidate();
            panelGraficos.repaint();
        }
    }


    private void setInformacion(Alumno alumno) {
        tfNombre.setText(alumno.getNombre());
        dp.setDate(alumno.getFechaNacimiento());
        tfDNI.setText(alumno.getDni());
        switch (alumno.getSexoString()){
            case "Helicoptero Apache":
                radioButtonHelicoptero.setSelected(true);
                break;
            case "Masculino":
                radioButtonMasculino.setSelected(true);
                break;
            case "Femenino":
                radioButtonFemenino.setSelected(true);
                break;
            case "No binario":
                otherRB.setSelected(true);
                lblNuevoGenero.setIcon(escalarImagen(alumno.getSexo(),55,55));
                break;
        }
        this.examenDefaultListModel = new DefaultListModel();
        if(alumno.getExamenes().size()!=0)
            System.out.println("Entar");
            for(Examen examen : alumno.getExamenes())
                examenDefaultListModel.addElement(examen.toString());
        listExamenes.setModel(examenDefaultListModel);
    }

    private void initUI() {
        setContentPane(contentPane);
        setBounds(new Rectangle(540,300));
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnCancelar);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void actionListeners() {
        listExamenes.addKeyListener(this);
        btnExamenAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Examen examen = (Examen) listExamenesNull.getSelectedValue();
                examen.setAlumno(modelo.getAlumnos().get(posicion));
                modelo.getAlumnos().get(posicion).getExamenes().add(examen);
                recargarLista();
                anadirExamenesAAnadir();
            }
        });
        btnModificarExamen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaInformacionExamen vie = new VistaInformacionExamen(modelo, modelo.buscarExamen(listExamenes.getSelectedValue().toString()));
                recargarLista();
                anadirExamenesAAnadir();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = tfNombre.getText();
                    String dni = tfDNI.getText();
                    LocalDate date = dp.getDate();
                    ImageIcon genero = new ImageIcon();
                    String generoo = "";
                    if (radioButtonHelicoptero.isSelected()) {
                        genero = (ImageIcon) lblHelicoptero.getIcon();
                        generoo = "Helicoptero Apache";
                    }
                    if (radioButtonMasculino.isSelected()) {
                        genero = (ImageIcon) lblMasculino.getIcon();
                        generoo = "Masculino";
                    }
                    if (radioButtonFemenino.isSelected()) {
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
                            throw new CampoBlancoException("genero no binario");
                        else
                            throw new WhiteCampException("Non-binary gender");
                    }
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
                    if(radioButtonMasculino.isSelected()==false && radioButtonFemenino.isSelected()==false && radioButtonHelicoptero.isSelected()==false && otherRB.isSelected()==false)
                        if(Locale.getDefault().toString().equals("es_ES"))
                            throw new CampoBlancoException("genero");
                        else
                            throw new WhiteCampException("gender");
                    Alumno alumnoEditado = new Alumno(nombre, date, dni, genero, generoo);
                    if(alumno.getExamenes().size()!=0) {
                        for (Examen examen : alumno.getExamenes()) {
                            alumnoEditado.getExamenes().add(examen);
                        }
                        modelo.cambiarExamenesAlumno(alumno, alumnoEditado);
                    }
                    modelo.getAlumnos().set(posicion, alumnoEditado);
                    JOptionPane.showMessageDialog(null, "Alumno editado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(Exception exc){
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNuevoGenero.addActionListener(new ActionListener() {
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
        radioButtonFemenino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonFemenino.isSelected()){
                    radioButtonMasculino.setSelected(false);
                    radioButtonHelicoptero.setSelected(false);
                    otherRB.setSelected(false);
                }
            }
        });

        radioButtonMasculino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonMasculino.isSelected()){
                    radioButtonFemenino.setSelected(false);
                    radioButtonHelicoptero.setSelected(false);
                    otherRB.setSelected(false);
                }
            }
        });

        otherRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(otherRB.isSelected()){
                    radioButtonMasculino.setSelected(false);
                    radioButtonFemenino.setSelected(false);
                    radioButtonHelicoptero.setSelected(false);
                }
            }
        });
        barrasRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barrasRB.isSelected()){
                    tartaRadioButton.setSelected(false);
                    crearGrafico("barras");
                }
            }
        });
        tartaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tartaRadioButton.isSelected()){
                    crearGrafico("tarta");
                    barrasRB.setSelected(false);
                }

            }
        });

        radioButtonHelicoptero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonHelicoptero.isSelected()){
                    radioButtonFemenino.setSelected(false);
                    radioButtonMasculino.setSelected(false);
                    otherRB.setSelected(false);
                }
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DELETE){
            Examen examenAModificar = alumno.getExamenes().get(listExamenes.getSelectedIndex());
            alumno.getExamenes().remove(listExamenes.getSelectedIndex());
            for(Examen examen : modelo.getExamenes()){
                if(examenAModificar.equals(examen)){
                    examen.setAlumno(null);
                }
            }
            recargarLista();
            anadirExamenesAAnadir();
            crearGrafico("barras");
        }
    }

    public ImageIcon escalarImagen(ImageIcon icon, int height, int width){
        Image image = icon.getImage();
        Image imagenEscalada = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    private void recargarLista() {
        examenDefaultListModel.clear();
        for(Examen examen : alumno.getExamenes()){
            examenDefaultListModel.addElement(examen.toString());
        }
    }
}
