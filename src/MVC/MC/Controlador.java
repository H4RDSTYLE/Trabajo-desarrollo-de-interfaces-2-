package MVC.MC;

import MVC.Vistas.VistaPrincipal;
import MVC.Vistas.crearAlumno;
import MVC.Vistas.crearAsignatura;
import MVC.Vistas.crearExamen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    Modelo modelo;
    VistaPrincipal vista;

    public Controlador(Modelo modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        addActionListener(this);
    }

    private void addActionListener(ActionListener listener) {
        vista.getBtnExamen().addActionListener(listener);
        vista.getBtnAlumno().addActionListener(listener);
        vista.getBtnAsignatura().addActionListener(listener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "btnAlumno":
                crearAlumno ca = new crearAlumno(modelo);
                break;
            case "btnAsignatura":
                crearAsignatura crea = new crearAsignatura(modelo);
                break;
            case "btnExamen":
                crearExamen ce = new crearExamen(modelo);
                break;
        }
    }
}
