package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Alumno;
import entidad.Concurso;
import model.AlumnoModel;
import model.ConcursoModel;
import util.ValidateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class FrmRegistraConcurso extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JButton btnRegistrarConcurso;
	private JCheckBox chckEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraConcurso frame = new FrmRegistraConcurso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRegistraConcurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar Concurso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(130, 10, 115, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 52, 44, 12);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Inicio");
		lblNewLabel_1_1.setBounds(10, 89, 76, 12);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha de Fin");
		lblNewLabel_1_1_1.setBounds(10, 118, 76, 12);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Estado:");
		lblNewLabel_1_2.setBounds(10, 154, 44, 12);
		contentPane.add(lblNewLabel_1_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(103, 49, 96, 18);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(103, 86, 96, 18);
		contentPane.add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setBounds(103, 115, 96, 18);
		contentPane.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		btnRegistrarConcurso = new JButton("RegistrarConcurso");
		btnRegistrarConcurso.addActionListener(this);
		btnRegistrarConcurso.setBounds(98, 186, 127, 20);
		contentPane.add(btnRegistrarConcurso);
		
		chckEstado = new JCheckBox("Activo");
		chckEstado.setBounds(71, 150, 92, 20);
		contentPane.add(chckEstado);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarConcurso) {
			do_btnRegistrarConcurso_actionPerformed(e);
		}
	}
	protected void do_btnRegistrarConcurso_actionPerformed(ActionEvent e) {
		//1 Recibir los datos del formulario en String
				String nombre = txtNombre.getText();
				String fechaInicio = txtFechaInicio.getText();
				String fechaFin = txtFechaFin.getText();
				int estado= chckEstado.isSelected()? 1 :0;
				//Validar datos 
				if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
					JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 30 caracteres");
					return;
				}
				
				//2 Crear el objeto Alumno
				Concurso obj = new Concurso();
				obj.setNombre(nombre);
				obj.setFechaInicio(java.time.LocalDate.parse(fechaInicio));
				obj.setFechaFin(java.time.LocalDate.parse(fechaFin));
				obj.setEstado(estado);
				
				
				//3 Crear el objeto AlumnoModel
				ConcursoModel model = new ConcursoModel();
				int salida = model.insertaConcurso(obj);
				
				//4 Mostrar el resultado
				if (salida > 0) {
					JOptionPane.showMessageDialog(this, "Alumno registrado correctamente");
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar el alumno");
				}
		
	}
}
