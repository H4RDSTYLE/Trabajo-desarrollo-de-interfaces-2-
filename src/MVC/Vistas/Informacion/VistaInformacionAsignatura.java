package MVC.Vistas.Informacion;

import Excepciones.CampoBlancoException;
import Excepciones.ObjetoIgualException;
import Excepciones.WhiteCampException;
import MVC.MC.Modelo;
import base.Asignatura;
import base.Examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;

/**
 * JDialog which shows the information of the student
 * @author Hugo
 * @since JDK8
 * @version 1.0
 */
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
    private JButton editarExamenBtn;
    private JButton btnExamenAnadir;
    private JList listExamenesNull;
    private Asignatura asignatura;
    private Modelo modelo;
    private int posicion;
    private DefaultListModel<Examen> dlmExamenes;

    /**
     * Creates the object
     * @param modelo modelo
     * @param posicion posicion
     */
    public VistaInformacionAsignatura(Modelo modelo, int posicion){
        this.asignatura = modelo.getAsignaturas().get(posicion);
        this.modelo = modelo;
        this.posicion = posicion;
        actionListeners();
        anadirComboBox();
        anadirExamenesAAnadir();
        ponerDatos(asignatura);
        initUI();
    }

    /**
     * Puts all the data of the course into the dialog
     * @param asignatura Objeto Asignatura
     */
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
            dlmExamenes.addElement(examen);
        }
        listExamenes.setModel(dlmExamenes);
    }

    /**
     * Adds information into a ComboBox
     */
    private void anadirComboBox() {
        cbEtapa.addItem("");
        cbEtapa.addItem("Infantil");
        cbEtapa.addItem("Primaria");
        cbEtapa.addItem("Secundaria");
        cbEtapa.addItem("Bachillerato");
    }

    /**
     * Adds exams into the dlm
     */
    private void anadirExamenesAAnadir() {
        DefaultListModel<Examen> listModelNullExamen = new DefaultListModel<>();
        listModelNullExamen.clear();
        for(Examen examen : modelo.getAsignaturasNull())
            listModelNullExamen.addElement(examen);
        listExamenesNull.setModel(listModelNullExamen);
    }

    /**
     * Inits the graphic Interface
     */
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

    /**
     * Adds the Action Listeners
     */
    private void actionListeners() {

        btnExamenAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Examen> examenes = (ArrayList<Examen>) listExamenesNull.getSelectedValuesList();
                for(Examen examen : examenes) {
                    examen.setAsignatura(modelo.getAsignaturas().get(posicion));
                    modelo.getAsignaturas().get(posicion).getExamenes().add(examen);
                }
                recargarLista();
                anadirExamenesAAnadir();
            }
        });

        modificarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(modificarAsignatura())
                    dispose();
            }
        });

        editarExamenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaInformacionExamen vie = new VistaInformacionExamen(modelo, modelo.buscarExamen(listExamenes.getSelectedValue().toString()));
                recargarLista();
                anadirExamenesAAnadir();
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

    /**
     * Modifies a course
     * @return True si los datos son correctos, False si no
     */
    private Boolean modificarAsignatura() {
        try {
            if (tfNombre.getText().isEmpty())
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("nombre");
                else
                    throw new WhiteCampException("name");
            if(cbEtapa.getSelectedItem().equals("")) {
                if(Locale.getDefault().toString().equals("es_ES"))
                    throw new CampoBlancoException("etapa");
                else
                    throw new WhiteCampException("etapation");
            }
            else {
                if(cbCurso.getSelectedItem().equals(null)||cbCurso.getSelectedItem().equals("")){
                    if(Locale.getDefault().toString().equals("es_ES"))
                        throw new CampoBlancoException("curso");
                    else
                        throw new WhiteCampException("curse");
                }
                Asignatura asignaturaModificada = new Asignatura(tfNombre.getText(), getRama(), Integer.valueOf(cbCurso.getSelectedItem().toString()), cbEtapa.getSelectedItem().toString());
                if(asignatura.getExamenes().size()!=0) {
                    for (Examen examen : asignatura.getExamenes())
                        asignaturaModificada.getExamenes().add(examen);
                    modelo.cambiarExamenesAsignatura(asignatura, asignaturaModificada);
                }
                modelo.getAsignaturas().set(posicion, asignaturaModificada);

                JOptionPane.showMessageDialog(null, "Asignatura modificada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * gets a branch
     * @return Sociales si se ha seleccionado, en caso contrario Letras o Ciencias
     */
    private String getRama() {
        if(socialesRadioButton.isSelected())
            return "Sociales";
        else if(letrasRadioButton.isSelected())
            return "Letras";
        else
            return "Ciencias";
    }

    /**
     * Do something on keyReleased
     * @param e Evento de pulsación
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DELETE){
            ArrayList<Examen> examenes = (ArrayList<Examen>) listExamenes.getSelectedValuesList();
            for(Examen exam : examenes){
                asignatura.getExamenes().remove(listExamenes.getSelectedIndex());
                for(Examen examen : modelo.getExamenes()){
                    if(exam.equals(examen)){
                        examen.setAsignatura(null);
                    }
                }
            }
            recargarLista();
            anadirExamenesAAnadir();
        }
    }

    /**
     * Recharges a List
     */
    private void recargarLista() {
        dlmExamenes.clear();
        for(Examen examen : asignatura.getExamenes()){
            dlmExamenes.addElement(examen);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
