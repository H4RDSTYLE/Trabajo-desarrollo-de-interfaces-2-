package MVC.Vistas.Informacion;

import Excepciones.CampoBlancoException;
import Excepciones.ObjetoIgualException;
import MVC.MC.Modelo;
import base.Asignatura;
import base.Examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;

public class VistaInformacionAsignatura extends JDialog implements KeyListener{
    private JPanel contentPane;
    private JTextField tfNombre;
    private JRadioButton cienciasRadioButton;
    private JRadioButton socialesRadioButton;
    private JRadioButton letrasRadioButton;
    private JComboBox cbEtapa;
    private JComboBox cbCurso;
    private JList listExamenes;
    private JButton salirBtn;
    private JButton modificarBtn;
    private Asignatura asignatura;
    private Modelo modelo;
    private int posicion;
    private DefaultListModel<String> dlmExamenes;

    public VistaInformacionAsignatura(Modelo modelo, int posicion){
        this.asignatura = modelo.getAsignaturas().get(posicion);
        this.modelo = modelo;
        this.posicion = posicion;
        actionListeners();
        anadirComboBox();
        ponerDatos(asignatura);
        initUI();
    }

    private void ponerDatos(Asignatura asignatura) {
        tfNombre.setText(asignatura.getNombre());
        cbEtapa.setSelectedItem(asignatura.getEtapa());
        cbCurso.setSelectedItem(String.valueOf(asignatura.getCurso()));
        switch (asignatura.getRama()){
            case "Sociales":
                socialesRadioButton.setSelected(true);
                break;
            case "Letras":
                letrasRadioButton.setSelected(true);
                break;
            case "Ciencias":
                cienciasRadioButton.setSelected(true);
                break;
        }
        dlmExamenes = new DefaultListModel();
        for(Examen examen : asignatura.getExamenes()){
            dlmExamenes.addElement(examen.toString());
        }
        listExamenes.setModel(dlmExamenes);
    }

    private void anadirComboBox() {
        cbEtapa.addItem("");
        cbEtapa.addItem("Infantil");
        cbEtapa.addItem("Primaria");
        cbEtapa.addItem("Secundaria");
        cbEtapa.addItem("Bachillerato");
    }

    private void initUI() {
        setContentPane(contentPane);
        setBounds(new Rectangle(490, 300));
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(salirBtn);
        setVisible(true);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void actionListeners() {

        modificarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(modificarAsignatura())
                    dispose();
            }
        });

        listExamenes.addKeyListener(this);
        salirBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cienciasRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cienciasRadioButton.isSelected()){
                    socialesRadioButton.setSelected(false);
                    letrasRadioButton.setSelected(false);
                }

            }
        });

        cbEtapa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String elementoSeleccionado = cbEtapa.getSelectedItem().toString();
                cbCurso.removeAllItems();
                switch (elementoSeleccionado){
                    case "Infantil":
                        cbCurso.addItem("1");
                        cbCurso.addItem("2");
                        cbCurso.addItem("3");
                        break;
                    case "Primaria":
                        cbCurso.addItem("1");
                        cbCurso.addItem("2");
                        cbCurso.addItem("3");
                        cbCurso.addItem("4");
                        cbCurso.addItem("5");
                        cbCurso.addItem("6");
                        break;
                    case "Secundaria":
                        cbCurso.addItem("1");
                        cbCurso.addItem("2");
                        cbCurso.addItem("3");
                        cbCurso.addItem("4");
                        break;
                    case "Bachillerato":
                        cbCurso.addItem("1");
                        cbCurso.addItem("2");
                        break;
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
    }

    private Boolean modificarAsignatura() {
        try {
            if (tfNombre.getText().isEmpty())
                throw new CampoBlancoException("nombre");
            if(cbEtapa.getSelectedItem().equals("")) {
                throw new CampoBlancoException("etapa");
            }
            else {
                if(cbCurso.getSelectedItem().equals(null)||cbCurso.getSelectedItem().equals("")){
                    throw new CampoBlancoException("curso");
                }
                Asignatura asignaturaModificada = new Asignatura(tfNombre.getText(), getRama(), Integer.valueOf(cbCurso.getSelectedItem().toString()), cbEtapa.getSelectedItem().toString());
                for(Asignatura asignatura : modelo.getAsignaturas()) {
                    if (asignaturaModificada.toString().equals(asignatura.toString()))
                        throw new ObjetoIgualException("asignatura");
                }
                modelo.getAsignaturas().set(posicion, asignaturaModificada);
                modelo.cambiarExamenesAsignatura(asignatura, asignaturaModificada);
                JOptionPane.showMessageDialog(null, "Asignatura modificada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private String getRama() {
        if(socialesRadioButton.isSelected())
            return "Sociales";
        else if(letrasRadioButton.isSelected())
            return "Letras";
        else
            return "Ciencias";
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
            Examen examenAModificar = asignatura.getExamenes().get(listExamenes.getSelectedIndex());
            asignatura.getExamenes().remove(listExamenes.getSelectedIndex());
            for(Examen examen : modelo.getExamenes()){
                if(examenAModificar.equals(examen)){
                    examen.setAsignatura(null);
                }
            }
            recargarLista();
        }
    }

    private void recargarLista() {
        dlmExamenes.clear();
        for(Examen examen : asignatura.getExamenes()){
            dlmExamenes.addElement(examen.toString());
        }
    }
}
