package MVC.Vistas;

import Excepciones.CampoBlancoException;
import Excepciones.ObjetoIgualException;
import MVC.MC.Modelo;
import base.Alumno;
import base.Asignatura;
import base.Examen;
import com.github.lgooddatepicker.components.DatePicker;
import org.jdatepicker.impl.JDatePanelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class crearExamen extends JDialog {
    private JPanel contentPane;
    private JComboBox comboBoxAsignatura;
    private JComboBox comboBoxAlumno;
    private JTextField notaTF;
    private JTextField profesorTF;
    private JButton resetearButton;
    private JButton salirButton;
    private JButton crearButton;
    private DatePicker datePicker;
    private Modelo modelo;

    public crearExamen(Modelo modelo) {
        this.modelo = modelo;
        initComboBox();
        AddActionListener();
        initUI();
    }

    private void initComboBox() {
        if(!modelo.getAlumnos().isEmpty()) {
            for (Alumno alumno : modelo.getAlumnos()) {
                comboBoxAlumno.addItem(alumno.toString());
            }
        }else{
            comboBoxAlumno.addItem("");
        }
        if(!modelo.getAsignaturas().isEmpty()) {
            for (Asignatura asignatura : modelo.getAsignaturas()) {
                comboBoxAsignatura.addItem(asignatura.toString());
            }
        }else{
            comboBoxAsignatura.addItem("");
        }
    }

    private void initUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(salirButton);
        setBounds(new Rectangle(650, 300));
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
        datePicker.setDate(LocalDate.now());
        setResizable(false);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void AddActionListener() {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comprobar())
                    resetear();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        resetearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetear();
            }
        });
    }

    private boolean comprobar() {
        try{
            if(profesorTF.getText().isEmpty())
                throw new CampoBlancoException("profesor");
            if(notaTF.getText().isEmpty())
                throw new CampoBlancoException("nota");
            try{
                Float.valueOf(notaTF.getText());
            }catch (Exception e){
                throw new Exception("La nota tiene que ser un número decimal");
            }
            if(comboBoxAsignatura.getSelectedItem().equals(""))
                throw new CampoBlancoException("asignatura");
            if(comboBoxAlumno.getSelectedItem().equals(""))
                throw new CampoBlancoException("alumno");
            Examen examen = new Examen(modelo.getAsignaturas().get(comboBoxAsignatura.getSelectedIndex()), modelo.getAlumnos().get(comboBoxAlumno.getSelectedIndex()), datePicker.getDate(), Float.valueOf(notaTF.getText()), profesorTF.getText());
            for(Examen exameen : modelo.getExamenes()){
                if(exameen.toString().equals(examen.toString()))
                    throw new ObjetoIgualException("examen");
            }
            modelo.getExamenes().add(examen);
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void resetear() {
        notaTF.setText("");
        profesorTF.setText("");
        datePicker.setDate(LocalDate.now());
    }
}
