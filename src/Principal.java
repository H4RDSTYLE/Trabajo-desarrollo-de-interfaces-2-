import MVC.MC.Controlador;
import MVC.MC.Modelo;
import MVC.Vistas.VistaPrincipal;

import javax.swing.*;

public class Principal {
    public static void main(String[] args){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e){
            e.printStackTrace();
        }

        Controlador controlador = new Controlador(new Modelo(), new VistaPrincipal());
    }
}
