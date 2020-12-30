package MVC.Vistas;

import Layouts.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panelCrear;
    private JButton btnAlumno;
    private JButton btnAsignatura;
    private JButton btnExamen;

    public VistaPrincipal(){
        initUI();
        setActionCommand();
    }

    private void setActionCommand() {
        btnExamen.setActionCommand("btnExamen");
        btnAlumno.setActionCommand("btnAlumno");
        btnAsignatura.setActionCommand("btnAsignatura");
    }

    public void initUI(){
        JFrame frame = new JFrame();
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setBounds(new Rectangle(600, 260));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        panelCrear.setLayout(new WrapLayout(FlowLayout.LEADING));
    }

    public JButton getBtnAlumno() {
        return btnAlumno;
    }

    public void setBtnAlumno(JButton btnAlumno) {
        this.btnAlumno = btnAlumno;
    }

    public JButton getBtnAsignatura() {
        return btnAsignatura;
    }

    public void setBtnAsignatura(JButton btnAsignatura) {
        this.btnAsignatura = btnAsignatura;
    }

    public JButton getBtnExamen() {
        return btnExamen;
    }

    public void setBtnExamen(JButton btnExamen) {
        this.btnExamen = btnExamen;
    }
}


