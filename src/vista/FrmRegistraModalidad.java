package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.List;

import entidad.Deporte;
import entidad.Modalidad;

import model.DeporteModel;
import model.ModalidadModel;
import util.ValidateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmRegistraModalidad extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNumHombres;
	private JTextField txtNumMujeres;
	private JTextField txtEdadMinima;
	private JTextField txtEdadMaxima;
	private JTextField txtSede;
	private JComboBox<String> cboDeporte;
	private JButton btnRegistrarModalidad;
	private JCheckBox chckEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					FrmRegistraModalidad frame = new FrmRegistraModalidad();
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
	public FrmRegistraModalidad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar Modalidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(130, 10, 150, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 52, 76, 12);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("N° Hombres");
		lblNewLabel_1_1.setBounds(10, 81, 76, 12);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("N° Mujeres");
		lblNewLabel_1_2.setBounds(10, 110, 76, 12);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edad Mínima");
		lblNewLabel_1_3.setBounds(10, 139, 76, 12);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Edad Máxima");
		lblNewLabel_1_4.setBounds(10, 168, 76, 12);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Sede");
		lblNewLabel_1_5.setBounds(10, 197, 76, 12);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Deporte");
		lblNewLabel_1_6.setBounds(10, 226, 76, 12);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Estado:");
		lblNewLabel_1_7.setBounds(10, 262, 44, 12);
		contentPane.add(lblNewLabel_1_7);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(103, 49, 150, 18);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNumHombres = new JTextField();
		txtNumHombres.setBounds(103, 78, 50, 18);
		contentPane.add(txtNumHombres);
		txtNumHombres.setColumns(10);
		
		txtNumMujeres = new JTextField();
		txtNumMujeres.setBounds(103, 107, 50, 18);
		contentPane.add(txtNumMujeres);
		txtNumMujeres.setColumns(10);
		
		txtEdadMinima = new JTextField();
		txtEdadMinima.setBounds(103, 136, 50, 18);
		contentPane.add(txtEdadMinima);
		txtEdadMinima.setColumns(10);
		
		txtEdadMaxima = new JTextField();
		txtEdadMaxima.setBounds(103, 165, 50, 18);
		contentPane.add(txtEdadMaxima);
		txtEdadMaxima.setColumns(10);
		
		txtSede = new JTextField();
		txtSede.setBounds(103, 194, 150, 18);
		contentPane.add(txtSede);
		txtSede.setColumns(10);
		
		cboDeporte = new JComboBox<String>();
		cboDeporte.setBounds(103, 223, 150, 22);
		contentPane.add(cboDeporte);
		
		chckEstado = new JCheckBox("Activo");
		chckEstado.setBounds(71, 258, 92, 20);
		contentPane.add(chckEstado);
		
		btnRegistrarModalidad = new JButton("RegistrarModalidad");
		btnRegistrarModalidad.addActionListener(this);
		btnRegistrarModalidad.setBounds(98, 300, 150, 20);
		contentPane.add(btnRegistrarModalidad);
		
		cargaDeporte();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarModalidad) {
			do_btnRegistrarModalidad_actionPerformed(e);
		}
	}
	protected void do_btnRegistrarModalidad_actionPerformed(ActionEvent e) {
		//1 Recibir los datos del formulario en String
				String nombre = txtNombre.getText();
				String numHombres = txtNumHombres.getText();
				String numMujeres = txtNumMujeres.getText();
				String edadMinima = txtEdadMinima.getText();
				String edadMaxima = txtEdadMaxima.getText();
				String sede = txtSede.getText();
				int indexDeporte = cboDeporte.getSelectedIndex();
				int estado= chckEstado.isSelected()? 1 :0;
				
				//Validar datos 
				if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
					JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 30 caracteres");
					return;
				}
				if (indexDeporte == 0) {
					JOptionPane.showMessageDialog(this, "Debe seleccionar un deporte");
					return;
				}
				
				//2 Crear el objeto Deporte
				Deporte objDeporte = new Deporte();
				objDeporte.setIdDeporte(Integer.parseInt(cboDeporte.getSelectedItem().toString().split(" - ")[0]));
				
				//3 Crear el objeto Modalidad
				Modalidad obj = new Modalidad();
				obj.setNombre(nombre);
				obj.setNumHombres(numHombres);
				obj.setNumMujeres(numMujeres);
				obj.setEdadMinima(edadMinima);
				obj.setEdadMaxima(edadMaxima);
				obj.setSede(sede);
				obj.setDeporte(objDeporte);
				obj.setEstado(estado);
				obj.setFechaRegistro(java.time.LocalDateTime.now());
				obj.setFechaActualizacion(java.time.LocalDateTime.now());
				
				//4 Crear el objeto ModalidadModel
				ModalidadModel model = new ModalidadModel();
				int salida = model.insertaModalidad(obj);
				
				//5 Mostrar el resultado
				if (salida > 0) {
					JOptionPane.showMessageDialog(this, "Modalidad registrada correctamente");
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar la modalidad");
				}
		
	}
	
	void cargaDeporte() {
		//1 llamar a DeporteModel para obtener la lista de deportes
		DeporteModel model = new DeporteModel();
		List<Deporte> lista = model.ListaTodos();
		
		//2 cargar la lista de deportes en el combo
		DefaultComboBoxModel<String> cboModel = new DefaultComboBoxModel<String>();
		cboModel.addElement(" [ Seleccione ]");
		
		for (Deporte d : lista) {
			cboModel.addElement(d.getIdDeporte() + " - " + d.getNombre());
		}
		
		cboDeporte.setModel(cboModel);
	}
}
