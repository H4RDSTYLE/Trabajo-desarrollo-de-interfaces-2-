package MVC.Vistas;

import Excepciones.CampoBlancoException;
import Excepciones.ObjectEqualsException;
import Excepciones.ObjetoIgualException;
import Excepciones.WhiteCampException;
import MVC.MC.Controlador;
import MVC.MC.Modelo;
import base.Alumno;
import base.Asignatura;
import base.Examen;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Locale;

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
    private Controlador controlador;

    public crearExamen(Modelo modelo, Controlador controlador) {
        this.modelo = modelo;
        this.controlador = controlador;
        initComboBox();
        AddActionListener();
        initUI();

    }

    private void initComboBox() {
        comboBoxAlumno.addItem("");
        if (!modelo.getAlumnos().isEmpty()) {
            for (Alumno alumno : modelo.getAlumnos()) {
                comboBoxAlumno.addItem(alumno.toString());
            }
            comboBoxAsignatura.addItem("");
            if (!modelo.getAsignaturas().isEmpty()) {
                for (Asignatura asignatura : modelo.getAsignaturas()) {
                    comboBoxAsignatura.addItem(asignatura.toString());
                }
            }
        }
    }

    private void initUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(salirButton);
        setBounds(new Rectangle(650, 300));
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        datePicker.setDate(LocalDate.now());
        setVisible(true);
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
        Float nota;
        try{
            if(profesorTF.getText().isEmpty())
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("profesor");
                else
                    throw new WhiteCampException("professor");
            if(notaTF.getText().isEmpty())
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("nota");
                else
                    throw new WhiteCampException("mark");
            try{
                nota = Float.valueOf(notaTF.getText().replace(",","."));
                if (nota<0 || nota>10) {
                    if(Locale.getDefault().toString().equals("es_ES"))
                        throw new Exception("La nota tiene que estar entre 0 y 10");
                    else
                        throw new Exception("The mark has to be between 0 and 10");
                }
            }catch (Exception e){
                if(e.getMessage()!="")
                    throw new Exception(e.getMessage());
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new Exception("La nota tiene que estar entre 0 y 10");
                else
                    throw new Exception("The mark has to be between 0 and 10");
            }
            Asignatura asignatura;
            if(comboBoxAsignatura.getSelectedItem()==null||comboBoxAsignatura.getSelectedItem().equals(""))
                asignatura = null;
            else
                asignatura = modelo.getAsignaturas().get(comboBoxAsignatura.getSelectedIndex()-1);
            Alumno alumno;
            if(comboBoxAsignatura.getSelectedItem()==null||comboBoxAlumno.getSelectedItem().equals(""))
                alumno = null;
            else
                alumno = modelo.getAlumnos().get(comboBoxAlumno.getSelectedIndex()-1);
            Examen examen = new Examen(asignatura, alumno, datePicker.getDate(), nota, profesorTF.getText());
            if(asignatura!=null)
                modelo.getAsignaturas().get(comboBoxAsignatura.getSelectedIndex()-1).getExamenes().add(examen);
            if(alumno!=null)
                modelo.getAlumnos().get(comboBoxAlumno.getSelectedIndex()-1).getExamenes().add(examen);
            for(Examen exameen : modelo.getExamenes()){
                if(exameen.toString().equals(examen.toString())) {
                    if(Locale.getDefault().toString().equals("es_ES"))
                        throw new ObjetoIgualException("Examen");
                    else
                        throw new ObjectEqualsException("Exam");
                }
            }
            modelo.getExamenes().add(examen);
            if(Locale.getDefault().toString().equals("es_ES"))
                JOptionPane.showMessageDialog(null, "Examen introducido con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Exam introduced with exitation", "Information", JOptionPane.INFORMATION_MESSAGE);
            controlador.refrescarListaExamenes();
            return true;
        }catch(Exception e){
            e.printStackTrace();
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
