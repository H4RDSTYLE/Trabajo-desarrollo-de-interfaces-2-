import MVC.MC.Controlador;
import MVC.MC.Modelo;
import MVC.MC.VistaPrincipal;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Its the main class, from this class you will execute the program.
 * @author Hugo
 */
public class Ejecutar {
    public static void main(String[] args){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        Locale.setDefault(obtenerLocale());
        System.out.print(obtenerLocale());
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e){
            e.printStackTrace();
        }
        crearSiNoExisteDirectorioDatos();

        Controlador controlador = new Controlador(new Modelo(), new VistaPrincipal());
        
    }

    /**
     * Creates if doesnt exists the data directory
     */
    public static void crearSiNoExisteDirectorioDatos(){
        File directorio = new File("data");
        if(!directorio.exists()) {
            directorio.mkdir();
        }
    }

    /**
     * Método que obtiene la localización del usuario
     * @return localización
     */
    public static Locale obtenerLocale() {

        Locale locale = null;

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));
            String pais = properties.getProperty("pais");
            String idioma = properties.getProperty("idioma");

            if(pais.equals("UK")){
                locale = new Locale("en", "UK");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Si no se ha podido cargar el fichero, idioma spanish
        if(locale == null){
            locale = new Locale("es", "ES");
        }

        return locale;
    }
}
