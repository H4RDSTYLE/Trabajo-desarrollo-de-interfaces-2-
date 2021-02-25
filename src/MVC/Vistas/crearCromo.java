package MVC.Vistas;

import MVC.MC.Modelo;
import base.Cromo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

/**
 * Dialogo que crea unn objeto de tipo Cromo
 * @author Hugo
 * @version 1.0
 * @since JDK8
 */
public class crearCromo extends JDialog {
    private JPanel contentPane;
    private JTextField tfNombre;
    private JTextField tfColeccion;
    private JTextField tfColor;
    private JTextField tfNumero;
    private JButton btnCrear;
    private Modelo modelo;

    /**
     * Constructor de la clase
     * @param modelo modelo
     */
    public crearCromo(Modelo modelo) {
        this.modelo = modelo;
        addActionListeners();
        initUI();
    }

    /**
     * Método que inicializa los componentes gráficos
     */
    private void initUI() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnCrear);
        setBounds(new Rectangle(650, 300));
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(true);
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
     * Método que inicializa los manejadores de eventos
     */
    private void addActionListeners() {
        btnCrear.addActionListener(e -> {
            Cromo cromo = new Cromo(tfNombre.getText(), tfColeccion.getText(), Integer.valueOf(tfNumero.getText()), tfColor.getText());
            modelo.getCromos().add(cromo);
            dispose();
        });
    }
}
