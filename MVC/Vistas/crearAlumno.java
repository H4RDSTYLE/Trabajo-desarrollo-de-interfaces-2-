package MVC.Vistas;

import Excepciones.CampoBlancoException;
import Excepciones.DniRepetidoException;
import MVC.MC.Modelo;
import base.Alumno;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

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
    private Modelo modelo;

    public crearAlumno(Modelo modelo) {
        this.modelo = modelo;
        addActionListener();
        initUI();
    }

    private void initUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnSalir);
        dp.setDate(LocalDate.now());
        setBounds(new Rectangle(600,300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setModal(true);
        setResizable(false);
    }

    private void addActionListener() {
        femaleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(femaleRB.isSelected()){
                    maleRB.setSelected(false);
                    helicopteroRB.setSelected(false);
                }
            }
        });

        maleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(maleRB.isSelected()){
                    femaleRB.setSelected(false);
                    helicopteroRB.setSelected(false);
                }
            }
        });

        helicopteroRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(helicopteroRB.isSelected()){
                    femaleRB.setSelected(false);
                    maleRB.setSelected(false);
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

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void crearAlumno(){
        if(comprobarYCrearAlumno())
            limpiarCampos();
    }

    private boolean comprobarYCrearAlumno() {
        try{
            String nombre = tfNombre.getText().toString();
            String dni = tfDNI.getText().toString();
            LocalDate date = dp.getDate();
            String genero = "";
            if(helicopteroRB.isSelected())
                genero = "Helicoptero Apache";
            if(maleRB.isSelected())
                genero = "Masculino";
            if(femaleRB.isSelected())
                genero = "Femenino";
            comprobarNombre(nombre);
            comprobarDNI(dni);
            modelo.getAlumnos().add(new Alumno(nombre, date, dni, genero));
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void comprobarNombre(String nombre) throws Exception {
        if (nombre.isEmpty())
            throw new CampoBlancoException("nombre");
    }

    private void comprobarDNI(String dni) throws Exception {
        if (dni.isEmpty())
            throw new CampoBlancoException("dni");
        for(Alumno alumno : modelo.getAlumnos()){
            if(alumno.getDni().equals(dni))
                throw new DniRepetidoException();

        }
    }

    private void limpiarCampos() {
        helicopteroRB.setSelected(true);
        femaleRB.setSelected(false);
        maleRB.setSelected(false);
        tfNombre.setText("");
        tfDNI.setText("");
        dp.setDate(LocalDate.now());
    }
}
