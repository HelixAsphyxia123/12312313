/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingreso;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class AgregarAlumno extends JFrame implements ActionListener{
        JFrame frame;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	JTextField textField_3;
        JButton btnNewButton;
        JButton btnCancelar;
        int valorc;

        public AgregarAlumno() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 240);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
                frame.setVisible(true);
                frame.setResizable(false);
		
		JLabel lblNoControl = new JLabel("No. Control:");
		lblNoControl.setBounds(12, 30, 85, 15);
                lblNoControl.setVisible(true);
		frame.getContentPane().add(lblNoControl);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 57, 70, 15);
                lblNombre.setVisible(true);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(12, 84, 70, 15);
                lblCurso.setVisible(true);
		frame.getContentPane().add(lblCurso);
		
		JLabel lblSemestre = new JLabel("Semestre:");
		lblSemestre.setBounds(12, 111, 85, 15);
                lblSemestre.setVisible(true);
		frame.getContentPane().add(lblSemestre);
		
		textField = new JTextField();
		textField.setBounds(106, 28, 350, 19);
                textField.setColumns(10);
                textField.setVisible(true);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 55, 350, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 82, 350, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(106, 109, 350, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("Añadir");
		btnNewButton.setBounds(156, 154, 93, 25);
		frame.getContentPane().add(btnNewButton);
                btnNewButton.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(279, 154, 96, 25);
		frame.getContentPane().add(btnCancelar);;
                btnCancelar.addActionListener(this);
	}
        
           public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewButton) {
            int NoControl = Integer.parseInt(textField.getText());
            String Nombre = textField_1.getText();
            String Curso = textField_2.getText();
            int Semestre = Integer.parseInt(textField_3.getText());
            int clave= valorc;
            try {
                Connection objConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Listas", "root", "root");
                Class.forName("com.mysql.jdbc.Driver");
                Statement st = objConexion.createStatement();
                st.executeUpdate("insert into Alumnos values ("+NoControl+",'"+Nombre+"','"+Curso+"',"+Semestre+","+clave+")");
            } catch (Exception ex) {
                System.out.println("Error no carga la BD");
                ex.printStackTrace();
                return;
            }
        }
        if (e.getSource() == btnCancelar) {
            this.frame.setVisible(false);
        }

    }
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
     * @param args
	 */
        public static void main(String []args){
        AgregarAlumno ventana = new AgregarAlumno(); 
    }  
}