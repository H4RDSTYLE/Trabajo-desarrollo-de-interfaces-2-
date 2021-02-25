package MVC.Vistas.Informacion;

import MVC.MC.Modelo;
import base.Alumno;
import base.Cromo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Clase que muestra información sobre un Cromo
 * @author Hugo
 * @since JDK8
 * @version 1.0
 */
public class VistaInformacionCromo extends JDialog {
    private Modelo modelo;
    private JPanel contentPane;
    private JList listUsuariosConCromo;
    private JList listUsuariosSinCromo;
    private JButton btnQuitar;
    private JButton btnAgregar;
    private JTextField tfNombre;
    private JButton btnEditar;
    private JTextField tfColeccion;
    private JTextField tfColor;
    private JTextField tfNumero;
    private JButton btnSalir;
    private DefaultListModel<Alumno> alumnosSinCromo;
    private DefaultListModel<Alumno> alumnosConCromo;
    private Cromo cromo;

    /**
     * Constructor de la clase
     * @param modelo modelo
     * @param posicion posicion
     */
    public VistaInformacionCromo(Modelo modelo, int posicion) {
        this.modelo = modelo;
        this.cromo = modelo.getCromos().get(posicion);
        alumnosConCromo = new DefaultListModel<>();
        alumnosSinCromo = new DefaultListModel<>();
        ponerDatos(cromo);
        addActionListeners();
        initUI();
    }

    /**
     * Método que muestra los datos del Cromo en sus respectivas cajas
     * @param cromo Objeto Cromo
     */
    private void ponerDatos(Cromo cromo) {
        tfNombre.setText(cromo.getNombre());
        tfColor.setText(cromo.getColor());
        tfColeccion.setText(cromo.getColeccion());
        tfNumero.setText(String.valueOf(cromo.getNumero()));
        recargarListas();
    }

    /**
     * Método que recarga la lista de cromos
     */
    private void recargarListas() {
        alumnosSinCromo.removeAllElements();
        alumnosConCromo.removeAllElements();
        for(Alumno alumno : modelo.getAlumnos()){
            boolean loTiene = false;
            for(Alumno alumno1 : cromo.getAlumnos()){
                if(alumno1.equals(alumno))
                    loTiene = true;
            }
            if(loTiene)
                alumnosConCromo.addElement(alumno);
            else
                alumnosSinCromo.addElement(alumno);
        }
        listUsuariosConCromo.setModel(alumnosConCromo);
        listUsuariosSinCromo.setModel(alumnosSinCromo);
        System.out.println("Entra");
    }

    /**
     * Método que reacciona ante los Action Listeners de los botones
     */
    private void addActionListeners() {
        btnSalir.addActionListener(e -> dispose());
        btnEditar.addActionListener(e -> modificar());
        btnAgregar.addActionListener(e -> {
            ArrayList<Alumno> personasAAgregar = (ArrayList<Alumno>)listUsuariosSinCromo.getSelectedValuesList();
            for(Alumno alumno : personasAAgregar)
                cromo.getAlumnos().add(alumno);
            recargarListas();
        });
        btnQuitar.addActionListener(e -> {
            ArrayList<Alumno> personasAQuitar = (ArrayList<Alumno>)listUsuariosConCromo.getSelectedValuesList();
            for(Alumno alumno : personasAQuitar)
                cromo.getAlumnos().remove(alumno);
            recargarListas();
        });
    }

    /**
     *Método que modifica un Cromo
     */
    private void modificar() {
        ArrayList<Alumno> alumnos = this.cromo.getAlumnos();
        Cromo cromoNuevo = new Cromo(tfNombre.getText(), tfColeccion.getText(), Integer.valueOf(tfNumero.getText()), tfColor.getText());
        cromoNuevo.setAlumnos(alumnos);
        modelo.getCromos().set(modelo.getCromos().indexOf(this.cromo), cromoNuevo);
        dispose();
    }

    /**
     * Método que inicializa los componentes gráficos del diálogo
     */
    private void initUI() {
        setContentPane(contentPane);
        setBounds(new Rectangle(800,300));
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
