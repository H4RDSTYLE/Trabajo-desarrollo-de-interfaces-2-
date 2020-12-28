package MVC.Vistas;

import Excepciones.CampoBlancoException;
import MVC.MC.Modelo;
import base.Asignatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public crearAsignatura(Modelo modelo) {
        this.modelo = modelo;
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
                throw new CampoBlancoException("nombre");
            if(comboBoxEtapa.getSelectedItem().equals("")) {
                throw new CampoBlancoException(comboBoxEtapa.getName());
            }
            else {
                comprobarComboBox(comboBoxCurso);
                comprobarComboBox(comboBoxEtapa);
                modelo.getAsignaturas().add(new Asignatura(tfNombre.getText(), getSelectedRama(), Integer.valueOf(comboBoxCurso.getSelectedItem().toString()), comboBoxEtapa.getSelectedItem().toString()));
                return true;
            }
        } catch (CampoBlancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void comprobarComboBox(JComboBox cb) throws CampoBlancoException {
        if(cb.getSelectedItem().equals(null)||cb.getSelectedItem().equals("")){
            throw new CampoBlancoException(cb.getName().toString());
        }
    }

    private void configUI() {
        setVisible(true);
        setBounds(new Rectangle(515, 210));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnSalir);
        comboBoxCurso.setEnabled(false);
        comboBoxEtapa.setName("Etapa");
        comboBoxCurso.setName("Curso");
    }

}
