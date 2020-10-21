/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingreso;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class SeleccioneMateria extends JFrame implements ActionListener, ItemListener {

    JFrame frame;
    JTextField textField;
    JTextField textField_1;
    JTextField textField_2;
    JScrollPane panel;
    JTable tablaper;
    JButton button, button_1;
    JComboBox comboBox;
    DefaultTableModel modelotabla;
    AgregarAlumno m = new AgregarAlumno();
        int valorc = 0;
    /**
     * Launch the application.
     */
    /**
     * Create the application.
     */
    public SeleccioneMateria() {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        comboBox = new JComboBox();
        comboBox.setBounds(32, 12, 440, 24);
        frame.getContentPane().add(comboBox);
        comboBox.addItemListener(this);

        JLabel lblNewLabel = new JLabel("Catedratico:");
        lblNewLabel.setBounds(42, 48, 96, 15);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(58, 92, 70, 15);
        frame.getContentPane().add(lblCarrera);

        JLabel lblGrupo = new JLabel("Grupo:");
        lblGrupo.setBounds(68, 127, 48, 15);
        frame.getContentPane().add(lblGrupo);

        textField = new JTextField();
        textField.setBounds(144, 48, 302, 24);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(146, 88, 302, 24);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(144, 123, 302, 24);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        button = new JButton("+");
        button.setBounds(428, 384, 44, 25);
        frame.getContentPane().add(button);
        button.addActionListener(this);

        button_1 = new JButton("-");
        button_1.setBounds(484, 384, 44, 25);
        frame.getContentPane().add(button_1);
        button_1.addActionListener(this);

        tablaper = new JTable();
        panel = new JScrollPane(tablaper);
        modelotabla = new DefaultTableModel(null, getcolumnas());
        setfilas();
        tablaper.setModel(modelotabla);
        panel.setBounds(10, 165, 522, 216);
        frame.getContentPane().add(panel);
        cargarlista();
        m.frame.setVisible(false);
    }

    private void cargarlista() {
        // TODO Auto-generated method stub
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root")) {
                Statement st = objConexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from Materia");

                while (rs.next()) {
                    String tmpStrObtenido = rs.getString("Asignatura");
                    comboBox.addItem(tmpStrObtenido);
                }
            }
        } catch (Exception e) {
            System.out.println("Error no carga la BD");
            e.printStackTrace();
            return;
        }

    }

    public String[] getcolumnas() {

        String columna[] = new String[]{"No", "NoControl", "Nombre", "Curso", "Semestre"};

        return columna;

    }
    
    public int valores(){
        try {
            Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            Statement st = objConexion.createStatement();
            ResultSet rs = st.executeQuery("Select * from materia where Asignatura='" + comboBox.getSelectedItem().toString() + "'");
            while (rs.next()) {
                valorc = rs.getInt("claveGrupo");
            }
            objConexion.close();
        } catch (Exception e) {

        }
        return valorc;
    }

    public void setfilas() {
        int a = 0;
        int b = 1;
        int valorb = 0;
        try {
            Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            Statement st = objConexion.createStatement();
            ResultSet rs = st.executeQuery("Select * from materia where Asignatura='" + comboBox.getSelectedItem().toString() + "'");
            while (rs.next()) {
                valorb = rs.getInt("claveGrupo");
            }
            objConexion.close();
        } catch (Exception e) {

        }
        try {
            Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            Statement st = objConexion.createStatement();
            Object datos[] = new Object[5];
            ResultSet rs = st.executeQuery("Select * from Alumnos where claveGrupo='" + valorb + "'");
            while (rs.next()) {
                datos[0] = b;
                datos[1] = rs.getString("noControl");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("curso");
                datos[4] = rs.getString("semestre");
                modelotabla.addRow(datos);
                b++;
            }
            objConexion.close();
        } catch (Exception e) {

        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboBox) {
            String seleccionado = (String) comboBox.getSelectedItem();

            try {
                Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
                Class.forName("com.mysql.jdbc.Driver");
                Statement st = objConexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from Materia where Asignatura='" + seleccionado + "'");

                while (rs.next()) {
                    textField.setText(rs.getString("catedratico"));
                    textField_1.setText(rs.getString("carrera"));
                    textField_2.setText(rs.getString("grupo"));
                }
            } catch (Exception ex) {
                System.out.println("Error no carga la BD");
                ex.printStackTrace();
                return;
            }
            modelotabla = new DefaultTableModel(null, getcolumnas());
            setfilas();
            tablaper.setModel(modelotabla);
        }
        valores();
    }
    
    public void cambiartabla(){
            modelotabla = new DefaultTableModel(null, getcolumnas());
            setfilas();
            tablaper.setModel(modelotabla);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
                    m.valorc=this.valorc;
                    m.frame.setVisible(true);
        }
        if (e.getSource() == button_1) {

            try {
                Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
                Class.forName("com.mysql.jdbc.Driver");
                Statement st = objConexion.createStatement();
                st.executeUpdate("Delete from Alumnos where noControl='"+modelotabla.getValueAt(tablaper.getSelectedRow(), 1).toString()+"'");
            } catch (Exception ex) {
                System.out.println("Error no carga la BD");
                ex.printStackTrace();
                return;
            }
        }
            modelotabla = new DefaultTableModel(null, getcolumnas());
            setfilas();
            tablaper.setModel(modelotabla);

    }

    public static void main(String[] args) {
    SeleccioneMateria ventana = new SeleccioneMateria(); 
    }

}