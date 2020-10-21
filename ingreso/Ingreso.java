/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingreso;

/**
 *
 * @author valti
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Ingreso extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    JButton btnAcceder;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ingreso window = new Ingreso();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Ingreso() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 192);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(46, 22, 70, 15);
        frame.getContentPane().add(lblUsuario);

        JLabel lblContrasea = new JLabel("Contraseña:");
        lblContrasea.setBounds(25, 63, 96, 15);
        frame.getContentPane().add(lblContrasea);

        textField = new JTextField();
        textField.setBounds(122, 20, 114, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        btnAcceder = new JButton("Acceder");
        btnAcceder.setBounds(119, 102, 117, 25);
        frame.getContentPane().add(btnAcceder);
        btnAcceder.addActionListener(this);

        passwordField = new JPasswordField();
        passwordField.setBounds(122, 61, 113, 19);
        frame.getContentPane().add(passwordField);
    }

    public void actionPerformed(ActionEvent e) {
        String usuario = null,password = null;
        if (e.getSource() == btnAcceder) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
                Statement st = objConexion.createStatement();
                ResultSet rs = st.executeQuery("Select usuario,password from usuarios Where usuario='"+textField.getText()+"'");
                while (rs.next()) {
                    
                   usuario=rs.getString(1);
                   password=rs.getString(2);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(password);
                if (textField.getText().equals(usuario) && passwordField.getText().equals(password)) {
                    SeleccioneMateria m = new SeleccioneMateria();
                    m.frame.setVisible(true);
                    frame.setVisible(false);
                }
                
        }
    }
}