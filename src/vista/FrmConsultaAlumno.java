package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.AlumnoModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.KeyEvent;

public class FrmConsultaAlumno extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtCorreo;
	private JTextField txtDesde;
	private JTextField txtHasta;
	private JTable table;
	private JButton btnFiltrar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					FrmConsultaAlumno frame = new FrmConsultaAlumno();
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
	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Alumno");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(145, 10, 162, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 58, 57, 12);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(68, 55, 96, 18);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(197, 58, 44, 12);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setBounds(224, 55, 96, 18);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(330, 58, 44, 12);
		contentPane.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(372, 55, 96, 18);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de nacimiento(desde):");
		lblNewLabel_1.setBounds(10, 83, 135, 12);
		contentPane.add(lblNewLabel_1);
		
		txtDesde = new JTextField();
		txtDesde.setBounds(155, 83, 96, 18);
		contentPane.add(txtDesde);
		txtDesde.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("(Hasta):");
		lblNewLabel_2.setBounds(263, 83, 44, 12);
		contentPane.add(lblNewLabel_2);
		
		txtHasta = new JTextField();
		txtHasta.setBounds(315, 83, 96, 18);
		contentPane.add(txtHasta);
		txtHasta.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 143, 431, 110);
		contentPane.add(scrollPane);
		
		table = new JTable();

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "DNI", "Correo", "Fecha Nacimiento"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.putIfAbsent("Table.alternateRowColor", new Color(176, 245, 215));
		
		scrollPane.setViewportView(table);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(82, 113, 84, 20);
		contentPane.add(btnFiltrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(207, 113, 84, 20);
		contentPane.add(btnCancelar);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(e);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent e) {
		//1 Recibimos todos los parametros del formulario
				String nombre = txtNombre.getText();
				String dni = txtDni.getText();
				String correo = txtCorreo.getText();
				String desde = txtDesde.getText();
				String hasta = txtHasta.getText();
				
				//imprimir los parametros recibidos
				System.out.println("Parametros recibidos: ");
				System.out.println("Nombre: " + nombre);
				System.out.println("DNI: " + dni);
				System.out.println("Correo: " + correo);
				System.out.println("Desde: " + desde);
				System.out.println("Hasta: " + hasta);
				
				
				//2 Validacion
				
				
				LocalDate fechaDesde = desde.isEmpty()? LocalDate.parse("9999-01-01"): LocalDate.parse(desde);
				LocalDate fechaHasta = hasta.isEmpty()? LocalDate.parse("9999-01-01"): LocalDate.parse(hasta);
				
				//3 Crear la clase model
				AlumnoModel objAlumnoModel = new AlumnoModel();
				List<Alumno> lista = objAlumnoModel.listaAlumno(nombre, dni, correo, fechaDesde, fechaHasta);
				
				//4 recorremos la lista
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
				
				for (Alumno a : lista) {
					Object[] rowData = { a.getIdAlumno(), 
										 a.getNombre(), 
										 a.getDni(), 
										 a.getCorreo(), 
										 a.getFechaNacimiento() 
										 };
					model.addRow(rowData);
				}
				
			
		
	}
	protected void do_btnCancelar_actionPerformed(ActionEvent e) {
		txtNombre.setText("");
		txtDni.setText("");
		txtCorreo.setText("");
		txtDesde.setText("");
		txtHasta.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpiar la tabla
	}
	public void keyPressed(KeyEvent e) {

	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtDni) {
			do_txtDni_keyReleased(e);
		}
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("Tecla presionada: keyPressed " + c);
		//Si es letra no ingresa
		if (Character.isLetter(c)) {
			e.consume(); // Ignorar la letra
		}
		//Si se ingresa más 8 dígitos digitos
		if (Character.isDigit(c) && txtDni.getText().length() >= 8) {
			e.consume(); // Ignorar el dígito si ya hay 8
		}
	}
	protected void do_txtDni_keyReleased(KeyEvent e) {
	
	}
}
