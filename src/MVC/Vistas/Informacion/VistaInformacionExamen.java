package MVC.Vistas.Informacion;

import Excepciones.CampoBlancoException;
import Excepciones.ObjetoIgualException;
import MVC.MC.Modelo;
import base.Alumno;
import base.Asignatura;
import base.Examen;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaInformacionExamen extends JDialog {
    private int posicion;
    private JPanel contentPane;
    private JComboBox comboBoxAsignatura;
    private JComboBox comboBoxAlumno;
    private JTextField notaTF;
    private JTextField profesorTF;
    private JButton btnSalir;
    private JButton btnModificar;
    private DatePicker dp;
    private Modelo modelo;
    private Alumno alumno;
    private Asignatura asignatura;
    private Examen examen;

    public VistaInformacionExamen(Modelo modelo, int posicion) {
        this.modelo = modelo;
        this.posicion = posicion;
        this.examen = modelo.getExamenes().get(posicion);
        this.alumno = examen.getAlumno();
        this.asignatura = examen.getAsignatura();
        ponerDatosExamen(examen);
        ActionListeners();
        initUI();
    }

    private void ponerDatosExamen(Examen examen) {
        DefaultComboBoxModel cbAsignatura = new DefaultComboBoxModel();
        cbAsignatura.addElement("");
        DefaultComboBoxModel cbAlumno = new DefaultComboBoxModel();
        cbAlumno.addElement("");
        for(Alumno alumno : modelo.getAlumnos())
            cbAlumno.addElement(alumno.toString());
        for(Asignatura asignatura : modelo.getAsignaturas())
            cbAsignatura.addElement(asignatura.toString());
        comboBoxAsignatura.setModel(cbAsignatura);
        if(examen.getAsignatura()!=null)
            comboBoxAsignatura.setSelectedItem(examen.getAsignatura().toString());
        else{
            comboBoxAsignatura.setSelectedItem("");
        }
        comboBoxAlumno.setModel(cbAlumno);
        if(examen.getAlumno()!=null)
            comboBoxAlumno.setSelectedItem(examen.getAlumno().toString());
        else{
            comboBoxAlumno.setSelectedItem("");
        }
        dp.setDate(examen.getFechaRealización());
        notaTF.setText(String.valueOf(examen.getNota()));
        profesorTF.setText(examen.getProfesor());
    }

    private void ActionListeners() {
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comprobar())
                    dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private boolean comprobar() {
        Float nota;
        try{
            if(profesorTF.getText().isEmpty())
                throw new CampoBlancoException("profesor");
            if(notaTF.getText().isEmpty())
                throw new CampoBlancoException("nota");
            try{
                nota = Float.valueOf(notaTF.getText().replace(",","."));
                if (nota<0 || nota>10)
                    throw new Exception("La nota tiene que estar entre 0 y 10");
            }catch (Exception e){
                if(e.getMessage()!="")
                    throw new Exception(e.getMessage());
                throw new Exception("La nota tiene que estar entre 0 y 10");
            }
            Asignatura asignatura;
            if(comboBoxAsignatura.getSelectedItem().equals(""))
                asignatura = null;
            else
                asignatura = modelo.getAsignaturas().get(comboBoxAsignatura.getSelectedIndex()-1);
            Alumno alumno;
            if(comboBoxAlumno.getSelectedItem().equals(""))
                alumno = null;
            else
                alumno = modelo.getAlumnos().get(comboBoxAlumno.getSelectedIndex()-1);
            Examen examen = new Examen(asignatura, alumno, dp.getDate(), nota, profesorTF.getText());
            for(Examen exameen : modelo.getExamenes()) {
                if (exameen.toString().equals(examen.toString()))
                    throw new ObjetoIgualException("examen");
            }
            eliminarEnUsuarioYAsignatura(examen);
            if(alumno!=null)
                modelo.getAlumnos().get(comboBoxAlumno.getSelectedIndex()-1).getExamenes().add(examen);
            if(asignatura!=null)
                modelo.getAsignaturas().get(comboBoxAsignatura.getSelectedIndex()-1).getExamenes().add(examen);
            modelo.getExamenes().set(posicion, examen);
            JOptionPane.showMessageDialog(null, "Examen modificado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void eliminarEnUsuarioYAsignatura(Examen examenAIntroducir){
        if(alumno!=null)
            alumno.getExamenes().remove(examen);
        if(asignatura!=null)
            asignatura.getExamenes().remove(examen);
    }

    private void initUI() {
        setContentPane(contentPane);
        setBounds(new Rectangle(800,300));
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnSalir);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
