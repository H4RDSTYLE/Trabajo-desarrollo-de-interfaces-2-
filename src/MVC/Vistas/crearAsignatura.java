package MVC.Vistas;

import Excepciones.CampoBlancoException;
import Excepciones.ObjectEqualsException;
import Excepciones.ObjetoIgualException;
import Excepciones.WhiteCampException;
import MVC.MC.Controlador;
import MVC.MC.Modelo;
import base.Asignatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class crearAsignatura extends JDialog {
    private JPanel contentPane;
    private JTextField tfNombre;
    private JComboBox comboBoxCurso;
    private JComboBox comboBoxEtapa;
    private JRadioButton cienciasRadioButton;
    private JRadioButton socialesRadioButton;
    private JRadioButton letrasRadioButton;
    private JButton btnSalir;
    private JButton btnAgregar;
    private JButton btnResetear;
    private Modelo modelo;
    private Controlador controlador;

    public crearAsignatura(Modelo modelo, Controlador controlador) {
        this.modelo = modelo;
        this.controlador = controlador;
        anadirComboBox();
        actionListeners();
        configUI();
    }

    private void anadirComboBox() {
        comboBoxEtapa.addItem("");
        comboBoxEtapa.addItem("Infantil");
        comboBoxEtapa.addItem("Primaria");
        comboBoxEtapa.addItem("Secundaria");
        comboBoxEtapa.addItem("Bachillerato");
        comboBoxCurso.setEnabled(false);
    }

    private void actionListeners() {

        cienciasRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cienciasRadioButton.isSelected()){
                    socialesRadioButton.setSelected(false);
                    letrasRadioButton.setSelected(false);
                }

            }
        });

        socialesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(socialesRadioButton.isSelected()){
                    cienciasRadioButton.setSelected(false);
                    letrasRadioButton.setSelected(false);
                }

            }
        });

        letrasRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(letrasRadioButton.isSelected()){
                    socialesRadioButton.setSelected(false);
                    cienciasRadioButton.setSelected(false);
                }
            }
        });

        comboBoxEtapa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               String elementoSeleccionado = comboBoxEtapa.getSelectedItem().toString();
               comboBoxCurso.removeAllItems();
               switch (elementoSeleccionado){
                   case "Infantil":
                       comboBoxCurso.addItem("1");
                       comboBoxCurso.addItem("2");
                       comboBoxCurso.addItem("3");
                       comboBoxCurso.setEnabled(true);
                       break;
                   case "Primaria":
                       comboBoxCurso.addItem("1");
                       comboBoxCurso.addItem("2");
                       comboBoxCurso.addItem("3");
                       comboBoxCurso.addItem("4");
                       comboBoxCurso.addItem("5");
                       comboBoxCurso.addItem("6");
                       comboBoxCurso.setEnabled(true);
                       break;
                   case "Secundaria":
                       comboBoxCurso.addItem("1");
                       comboBoxCurso.addItem("2");
                       comboBoxCurso.addItem("3");
                       comboBoxCurso.addItem("4");
                       comboBoxCurso.setEnabled(true);
                       break;
                   case "Bachillerato":
                       comboBoxCurso.addItem("1");
                       comboBoxCurso.addItem("2");
                       comboBoxCurso.setEnabled(true);
                       break;
                   default:
                       comboBoxCurso.setEnabled(false);
                       break;
               }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarAsignatura();
            }
        });

        btnResetear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void agregarAsignatura() {
        if(comprobarAsignatura())
            limpiarCampos();
        controlador.refrescarListaAsignaturas();
    }

    private void limpiarCampos() {
        tfNombre.setText("");
        comboBoxEtapa.setSelectedItem("");
        cienciasRadioButton.setSelected(true);
        letrasRadioButton.setSelected(false);
        socialesRadioButton.setSelected(false);
    }

    private String getSelectedRama(){
        if(socialesRadioButton.isSelected())
            return "Sociales";
        else if(letrasRadioButton.isSelected())
            return "Letras";
        else
            return "Ciencias";
    }

    private Boolean comprobarAsignatura() {
        try {
            if (tfNombre.getText().isEmpty())
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("nombre");
                else
                    throw new WhiteCampException("name");
            if(comboBoxEtapa.getSelectedItem().equals("")) {
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("etapa");
                else
                    throw new WhiteCampException("etapation");
            }
            else {
                if(comboBoxCurso.getSelectedItem().equals(null)||comboBoxCurso.getSelectedItem().equals("")){
                    if(Locale.getDefault().toString().equals("es_ES"))
                        throw new CampoBlancoException("curso");
                    else
                        throw new WhiteCampException("curse");
                }
                Asignatura asignatura = new Asignatura(tfNombre.getText(), getSelectedRama(), Integer.valueOf(comboBoxCurso.getSelectedItem().toString()), comboBoxEtapa.getSelectedItem().toString());
                for(Asignatura asignaturaa : modelo.getAsignaturas()) {
                    if (asignatura.toString().equals(asignaturaa.toString()))
                        if(Locale.getDefault().toString().equals("es_ES"))
                            throw new ObjetoIgualException("asignatura");
                        else
                            throw new ObjectEqualsException("asignaturation");
                }
                modelo.getAsignaturas().add(asignatura);
                if(Locale.getDefault().toString().equals("es_ES"))
                    JOptionPane.showMessageDialog(null, "Asignatura introducida con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Asignaturation introduced with exitation", "Information", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void configUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnSalir);
        setBounds(new Rectangle(515, 210));
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
        comboBoxEtapa.setName("Etapa");
        comboBoxCurso.setName("Curso");
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

}
