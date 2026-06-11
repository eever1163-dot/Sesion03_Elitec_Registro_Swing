package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entidad.Tipo;
import entidad.Pais;
import entidad.Proveedor;
import model.TipoModel;
import model.PaisModel;
import model.ProveedorModel;
import util.ValidateUtil;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmRegistraProveedor extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JComboBox<String> cboTipo;
	private JComboBox<String> cboPais;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraProveedor frame = new FrmRegistraProveedor();
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
	public FrmRegistraProveedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro Proveedor");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 40, 570, 35);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(104, 125, 93, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(294, 122, 220, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(104, 169, 93, 14);
		contentPane.add(lblDNI);
		
		txtDni = new JTextField();
		txtDni.addKeyListener((KeyListener) this);
		txtDni.setColumns(10);
		txtDni.setBounds(294, 166, 220, 20);
		contentPane.add(txtDni);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(104, 219, 93, 14);
		contentPane.add(lblTipo);
		
		cboTipo = new JComboBox<String>();
		cboTipo.setBounds(294, 215, 220, 22);
		contentPane.add(cboTipo);
		
		JLabel lblPais = new JLabel("País");
		lblPais.setBounds(104, 269, 93, 14);
		contentPane.add(lblPais);
		
		cboPais = new JComboBox<String>();
		cboPais.setBounds(294, 265, 220, 22);
		contentPane.add(cboPais);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener((ActionListener) this);
		btnRegistrar.setBounds(199, 354, 226, 23);
		contentPane.add(btnRegistrar);
		
		cargaTipo();
		cargaPais();

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		//1 Capturar datos
		String nombre = txtNombre.getText();
		String dni = txtDni.getText();
		int indexTipo = cboTipo.getSelectedIndex();
		int indexPais = cboPais.getSelectedIndex();
		
		//2 validar datos
		if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
			 JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 40 caracteres");
 			return;
		}
		if (dni.matches(ValidateUtil.DNI) == false) {
			JOptionPane.showMessageDialog(this, "El DNI no es válido. Tiene que tener 8 dígitos");
			return;
		}
		if (indexTipo == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo");
			return;
		}
		if (indexPais == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un país");
			return;
		}
		
		//2.1 Validar que el DNI no exista en la base de datos
		ProveedorModel model = new ProveedorModel();
		boolean existeProveedor = model.existeProveedorPorDNI(dni);
		if (existeProveedor) {
			JOptionPane.showMessageDialog(this, "El DNI : " + dni + " ya existe. No se puede registrar el proveedor");
			return;
		}
		
		//3 Crear objeto Proveedor
		Tipo objTipo = new Tipo();
		objTipo.setIdTipo(Integer.parseInt(cboTipo.getSelectedItem().toString().split(" - ")[0]));
		
		Pais objPais = new Pais();
		objPais.setIdPais(Integer.parseInt(cboPais.getSelectedItem().toString().split(" - ")[0]));
		
		Proveedor proveedor = new Proveedor();
		proveedor.setNombre(nombre);
		proveedor.setDni(dni);
		proveedor.setFechaRegistro(LocalDateTime.now());
		proveedor.setFechaActualizacion(LocalDateTime.now());
		proveedor.setEstado(1);
		proveedor.setTipo(objTipo);
		proveedor.setPais(objPais);
		
		//4 Llamar a ProveedorModel para registrar el proveedor
		int resultado = model.insertaProveedor(proveedor);

        // 4. Mensaje
        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Proveedor registrado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar Proveedor");
        }

	}
	
	
	void cargaTipo() {
		//1 llamar a TipoModel para obtener la lista de tipos
		TipoModel model = new TipoModel();
		List<Tipo> lista = model.ListaTodos();
		
		//2 cargar la lista de tipos en el combo
		DefaultComboBoxModel<String> cboModel = new DefaultComboBoxModel<String>();
		cboModel.addElement(" [ Seleccione ]");
		
		for (Tipo t : lista) {
			cboModel.addElement(t.getIdTipo() + " - " + t.getDescripcion());
		}
		
		cboTipo.setModel(cboModel);
		
	}
	
	void cargaPais() {
		//1 llamar a PaisModel para obtener la lista de paises
		PaisModel model = new PaisModel();
		List<Pais> lista = model.ListaTodos();
		
		//2 cargar la lista de paises en el combo
		DefaultComboBoxModel<String> cboModel = new DefaultComboBoxModel<String>();
		cboModel.addElement(" [ Seleccione ]");
		
		for (Pais p : lista) {
			cboModel.addElement(p.getIdPais() + " - " + p.getNombre());
		}
		
		cboPais.setModel(cboModel);
		
	}
	
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtDni) {
			do_textField_keyTyped(e);
		}
	}
	protected void do_textField_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		
		if (Character.isLetter(c)) {
			e.consume(); 
		}
		
		if (Character.isDigit(c) && txtDni.getText().length() >= 8) {
			e.consume(); 
		}
	}

}
