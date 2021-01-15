package MVC.Vistas.Informacion;

import Excepciones.CampoBlancoException;
import Excepciones.DniRepetidoException;
import MVC.MC.Modelo;
import base.Alumno;
import base.Examen;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

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
    private DefaultListModel<String> examenDefaultListModel;
    private Alumno alumno;

    public VistaInformacionAlumno(Modelo modelo, int posicion) {
        this.posicion = posicion;
        this.modelo = modelo;
        actionListeners();
        this.alumno = modelo.getAlumnos().get(posicion);
        setInformacion(alumno);
        initUI();
    }

    private void setInformacion(Alumno alumno) {
        tfNombre.setText(alumno.getNombre());
        dp.setDate(alumno.getFechaNacimiento());
        tfDNI.setText(alumno.getDni());
        switch (alumno.getSexo()){
            case "Helicoptero Apache":
                radioButtonHelicoptero.setSelected(true);
                break;
            case "Masculino":
                radioButtonMasculino.setSelected(true);
                break;
            case "Femenino":
                radioButtonFemenino.setSelected(true);
                break;
        }
        this.examenDefaultListModel = new DefaultListModel();
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
                    String nombre = tfNombre.getText().toString();
                    String dni = tfDNI.getText().toString();
                    LocalDate date = dp.getDate();
                    String genero = "";
                    if (radioButtonHelicoptero.isSelected())
                        genero = "Helicoptero Apache";
                    if (radioButtonMasculino.isSelected())
                        genero = "Masculino";
                    if (radioButtonFemenino.isSelected())
                        genero = "Femenino";
                    if (nombre.isEmpty())
                        throw new CampoBlancoException("nombre");
                    if (dni.isEmpty())
                        throw new CampoBlancoException("dni");
                    for (Alumno alumno : modelo.getAlumnos()) {
                        int i = 0;
                        if (alumno.getDni().equals(dni) && i!=posicion)
                            throw new DniRepetidoException();
                    }
                    Alumno alumnoEditado = new Alumno(nombre, date, dni, genero);
                    modelo.cambiarExamenesAlumno(alumno, alumnoEditado);
                    modelo.getAlumnos().set(posicion, alumnoEditado);
                    JOptionPane.showMessageDialog(null, "Alumno editado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                } catch(Exception exc){
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        radioButtonFemenino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonFemenino.isSelected()){
                    radioButtonMasculino.setSelected(false);
                    radioButtonHelicoptero.setSelected(false);
                }
            }
        });

        radioButtonMasculino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonMasculino.isSelected()){
                    radioButtonFemenino.setSelected(false);
                    radioButtonHelicoptero.setSelected(false);
                }
            }
        });

        radioButtonHelicoptero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonHelicoptero.isSelected()){
                    radioButtonFemenino.setSelected(false);
                    radioButtonMasculino.setSelected(false);
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
        }
    }

    private void recargarLista() {
        examenDefaultListModel.clear();
        for(Examen examen : alumno.getExamenes()){
            examenDefaultListModel.addElement(examen.toString());
        }
    }
}
