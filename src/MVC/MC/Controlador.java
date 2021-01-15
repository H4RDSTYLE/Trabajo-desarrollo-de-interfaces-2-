package MVC.MC;

import MVC.Vistas.Informacion.VistaInformacionAlumno;
import MVC.Vistas.Informacion.VistaInformacionAsignatura;
import MVC.Vistas.Informacion.VistaInformacionExamen;
import MVC.Vistas.crearAlumno;
import MVC.Vistas.crearAsignatura;
import MVC.Vistas.crearExamen;
import base.Alumno;
import base.Asignatura;
import base.Examen;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Controlador implements ActionListener, ListSelectionListener, KeyListener, FocusListener, MouseListener {
    private Modelo modelo;
    private VistaPrincipal vista;
    private String foco;

    public Controlador(Modelo modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        setActionCommand();
        addActionListener(this);
        addListSelectionListener(this);
        addFocusListener(this);
        addKeyListener(this);
        addMouseListeners(this);
    }

    private void addMouseListeners(MouseListener listener) {
        vista.getListaAlumnos().addMouseListener(listener);
        vista.getListExamenes().addMouseListener(listener);
        vista.getListAsignaturas().addMouseListener(listener);
    }

    private void addKeyListener(KeyListener listener) {
        vista.getListAsignaturas().addKeyListener(listener);
        vista.getListExamenes().addKeyListener(listener);
        vista.getListaAlumnos().addKeyListener(listener);
    }

    public void refrescarListaAsignaturas(){
        vista.getModelAsignatura().clear();
        for(Asignatura asignatura : modelo.getAsignaturas()){
            vista.getModelAsignatura().addElement(asignatura);
        }
    }

    public void refrescarListaExamenes(){
        vista.getModelExamenes().clear();
        for(Examen examen : modelo.getExamenes()){
            vista.getModelExamenes().addElement(examen);
        }
    }

    public void refrescarListaAlumnos() {
        vista.getModelAlumnos().clear();
        for(Alumno alumno : modelo.getAlumnos()){
            vista.getModelAlumnos().addElement(alumno);
        }
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.getListaAlumnos().addListSelectionListener(listener);
        vista.getListAsignaturas().addListSelectionListener(listener);
        vista.getListExamenes().addListSelectionListener(listener);
    }

    private void addFocusListener(FocusListener listener){
        vista.getListaAlumnos().addFocusListener(listener);
        vista.getListExamenes().addFocusListener(listener);
        vista.getListAsignaturas().addFocusListener(listener);
    }

    private void addActionListener(ActionListener listener) {
        vista.getBtnCargar().addActionListener(listener);
        vista.getBtnGuardar().addActionListener(listener);
        vista.getBtnExamen().addActionListener(listener);
        vista.getBtnAlumno().addActionListener(listener);
        vista.getBtnAsignatura().addActionListener(listener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "btnAlumno":
                crearAlumno ca = new crearAlumno(modelo, this);
                break;
            case "btnAsignatura":
                crearAsignatura crea = new crearAsignatura(modelo, this);
                break;
            case "btnExamen":
                crearExamen ce = new crearExamen(modelo, this);
                break;
            case "btnCargar":
                JFileChooser selector = new JFileChooser();
                int opt = selector.showOpenDialog(vista);

                if (opt == JFileChooser.APPROVE_OPTION) {
                    File fichero = selector.getSelectedFile();

                    try {
                        modelo.cargarDatos(fichero);

                    } catch (Exception e1) {

                        e1.printStackTrace();

                    }
                }
                refrescarListaAsignaturas();
                refrescarListaAlumnos();
                refrescarListaExamenes();
                break;
            case  "btnGuardar":
                selector = new JFileChooser();
                opt = selector.showSaveDialog(vista);

                if (opt == JFileChooser.APPROVE_OPTION) {
                    File fichero = selector.getSelectedFile();

                    try {
                        modelo.guardarDatos(fichero);

                    } catch (Exception e1) {

                        e1.printStackTrace();

                    }
                }
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DELETE){
            if(foco.equals("alumno")){
                modelo.cambiarExamenesAlumno(modelo.getAlumnos().get(vista.getListaAlumnos().getSelectedIndex()), null);
                modelo.getAlumnos().remove(vista.getListaAlumnos().getSelectedIndex());
                refrescarListaAlumnos();
            }
            if(foco.equals("asignaturas")){
                modelo.cambiarExamenesAsignatura(modelo.getAsignaturas().get(vista.getListAsignaturas().getSelectedIndex()), null);
                modelo.getAsignaturas().remove(vista.getListAsignaturas().getSelectedIndex());
                refrescarListaAsignaturas();
            }
            if(foco.equals("examenes")){
                modelo.eliminarExamenesDeAlumnos(modelo.getExamenes().get(vista.getListExamenes().getSelectedIndex()));
                modelo.eliminarExamenesDeAsignaturas(modelo.getExamenes().get(vista.getListExamenes().getSelectedIndex()));
                modelo.getExamenes().remove(vista.getListExamenes().getSelectedIndex());
                refrescarListaAsignaturas();
                refrescarListaAlumnos();
            }
            refrescarListaExamenes();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.foco = e.getComponent().getName();
        System.out.println(foco);
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.foco = "";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            switch (e.getComponent().getName()){
                case "alumno":
                    VistaInformacionAlumno via = new VistaInformacionAlumno(modelo, vista.getListaAlumnos().getSelectedIndex());
                    refrescarListaAlumnos();
                    break;
                case "asignaturas":
                    VistaInformacionAsignatura viasign = new VistaInformacionAsignatura(modelo, vista.getListAsignaturas().getSelectedIndex());
                    refrescarListaAsignaturas();
                    break;
                case "examenes":
                    VistaInformacionExamen vie = new VistaInformacionExamen(modelo, vista.getListExamenes().getSelectedIndex());
                    refrescarListaExamenes();
                    break;
            }
        }
    }

    private void setActionCommand() {
        vista.getBtnCargar().setActionCommand("btnCargar");
        vista.getBtnGuardar().setActionCommand("btnGuardar");
        vista.getBtnExamen().setActionCommand("btnExamen");
        vista.getBtnAlumno().setActionCommand("btnAlumno");
        vista.getBtnAsignatura().setActionCommand("btnAsignatura");
        vista.getListaAlumnos().setName("alumno");
        vista.getListAsignaturas().setName("asignaturas");
        vista.getListExamenes().setName("examenes");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
