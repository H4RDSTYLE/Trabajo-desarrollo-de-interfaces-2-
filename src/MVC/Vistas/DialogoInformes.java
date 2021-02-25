package MVC.Vistas;

import MVC.MC.Modelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.event.*;

/**
 * Clase Dialogo Informes en la que se muestran informes
 * @author Hugo
 * @version 1.0
 * @since JDK 8
 */
public class DialogoInformes extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton informe1;
    private JRadioButton informe2;
    private JPanel panelInforme1;
    private Modelo modelo;

    /**
     * Constructor de la clase
     * @param modelo modelo
     */
    public DialogoInformes(Modelo modelo) {
        this.modelo=modelo;

        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Método que inicializa los componentes gráficos y los manejadores de evento
     *
     */
    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        informe1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JasperReport informe = (JasperReport) JRLoader.loadObject(getClass().getResource("/InformeAlumnos.jasper"));
                    JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getAlumnos());
                    JasperPrint printer = JasperFillManager.fillReport(informe, null, coleccion);
                    JRViewer visor = new JRViewer(printer);
                    panelInforme1.remove(visor);
                    panelInforme1.add(visor);

                } catch (JRException ex) {
                    ex.printStackTrace();
                }
            }
        });
        informe2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JasperReport informe = (JasperReport) JRLoader.loadObject(getClass().getResource("/InformeExamenes.jasper"));
                    JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getExamenes());
                    JasperPrint printer = JasperFillManager.fillReport(informe, null, coleccion);
                    JRViewer visor = new JRViewer(printer);
                    panelInforme1.remove(visor);
                    panelInforme1.add(visor);


                } catch (JRException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    /**
     * Método que reacciona ante el botón Ok
     */
    private void onOK() { dispose(); }

    /**
     * Método que reacciona ante el botón Cancelar
     */
    private void onCancel() { dispose(); }
}
