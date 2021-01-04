package mx.uam.ayd.proyecto.presentacion.realizarPedido;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Ventana de la HU-44 Realizar un Pedido
 * 
 * @author Anonymux Corporation
 * 
 */
@SuppressWarnings("serial")
@Component
public class VentanaRealizarPedido extends JFrame {

	/**
	 * Se declara el contenedor, sirven para agrupar los objetos de esta ventana (buttons, 
	 * checkboxs, jtexts, etc...)
	 */
	private JPanel contentPane;
	/**
	 * Se declara la comunicación con el controlador de Realizar Pedidos{@link ControlRealizarPedido}
	 */
	private ControlRealizarPedido control;
	/**
	 * Se declara el {@link JComboBox} que se usa en la ventana con el nombre comboBox
	 */
	private JComboBox<String> comboBox = new JComboBox<String>();
	/**
	 * Se declara el {@link JTextField} que se usa en la ventana con el nombre txtFDescripcion
	 */
	private JTextField txtFDescripcion;
	/**
	 * Se declara el {@link JTextField} que se usa en la ventana con el nombre txtFCantidadInventario
	 */
	private JTextField txtFCantidadInventario;
	/**
	 * Se declara el {@link JTextField} que se usa en la ventana con el nombre txtFCantidadMinima
	 */
	private JTextField txtFCantidadMinima;
	/**
	 * Se declara el {@link JTextField} que se usa en la ventana con el nombre txtFCantidad
	 */
	private JTextField txtFCantidad;
	/**
	 * Se declara el {@link DefaultTableModel} que se usa en la ventana con el nombre model
	 */
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "Fecha", "ID_Producto", "Descripci\u00F3n", "Cantidad", "Cantidad M\u00EDnima" }) {
		boolean[] columnEditables = new boolean[] { false, false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	/**
	 * Inicia la aplicación
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VentanaAgregarUsuario frame = new
	 * VentanaAgregarUsuario(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Se crea el cuadro de la Ventana.
	 */
	public VentanaRealizarPedido() {
		setTitle("Realizar un Pedido");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel title = new JLabel("Realizar un pedido");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 32));

		JButton btnRealizarPedido = new JButton("Realizar un pedido");
		btnRealizarPedido.setFont(new Font("Arial", Font.PLAIN, 14));

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblProducto = new JLabel("Producto: ");
		lblProducto.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblId = new JLabel("Descripción:");
		lblId.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblEnInventario = new JLabel("Cantidad en inventario:");
		lblEnInventario.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblCantidadMinima = new JLabel("Cantidad Minima:");
		lblCantidadMinima.setFont(new Font("Arial", Font.PLAIN, 14));

		txtFDescripcion = new JTextField();
		txtFDescripcion.setEditable(false);
		txtFDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFDescripcion.setColumns(10);

		txtFCantidadInventario = new JTextField();
		txtFCantidadInventario.setEditable(false);
		txtFCantidadInventario.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFCantidadInventario.setColumns(10);
		
		txtFCantidadMinima = new JTextField();
		txtFCantidadMinima.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFCantidadMinima.setEditable(false);
		txtFCantidadMinima.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 14));
		
		txtFCantidad = new JTextField();
		txtFCantidad.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFCantidad.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(501, Short.MAX_VALUE)
					.addComponent(btnCerrar)
					.addGap(34))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(223, Short.MAX_VALUE)
					.addComponent(btnRealizarPedido, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(206))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(136)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProducto, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(lblId, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(lblEnInventario, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(lblCantidadMinima, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(lblCantidad))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtFCantidad)
										.addComponent(txtFCantidadMinima)
										.addComponent(txtFCantidadInventario)
										.addComponent(txtFDescripcion, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(comboBox, 0, 196, Short.MAX_VALUE)))
						.addComponent(title, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
					.addGap(117))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProducto)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFCantidadInventario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnInventario, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFCantidadMinima, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCantidadMinima, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFCantidad, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addComponent(btnRealizarPedido, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(btnCerrar)
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);

		// Listeners de los botones de la ventana

		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se comunica con el control para llamar al metodo ValidarCantidad
				if ( validaCantidad( txtFCantidad.getText() ) ) {
					// Se comunica con el control para llamar al metodo AumentarCantidad 
					if ( aumentaCantidad(comboBox.getSelectedItem().toString(), Integer.parseInt(txtFCantidad.getText())) ) {
						muestraDialogoConMensaje("Se ha completado el pedido con éxito");
						// Se comunica con el control para llamar al metodo correspondiente y cerrar la ventana
						control.cerrar();
					}
					else {
						muestraDialogoConMensaje("Error: Se debe cubrir la cantidad mínima y No debe de exceder el límite establecido (1000 pzs)");
					}
				}
				else {
					muestraDialogoConMensaje("Error: Ingrese un número Valido");
				}
			}
		});

		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se comunica con el control para llamar al metodo correspondiente y cerrar la ventana
				control.cerrar();
			}
		});

	}

	/**
	 * Recibe al controlador y una lista de Productos para mostrarlo en la ventana
	 * Se comunica con el controlador 
	 * 
	 * @param control
	 * @param productos
	 */
	public void muestra(ControlRealizarPedido control, List<Producto> productos) {
		this.control = control;

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		for (Producto producto : productos) {
			comboBoxModel.addElement(producto.getNombreProducto());
		}
		comboBox.setModel(comboBoxModel);
		txtFCantidad.setText("");
		txtFDescripcion.setText( productos.get(comboBox.getSelectedIndex()).getDescripcion());
		txtFCantidadInventario.setText(Integer.toString(productos.get(comboBox.getSelectedIndex()).getCantidad()));
		txtFCantidadMinima.setText(Integer.toString(productos.get(comboBox.getSelectedIndex()).getMinimo()));
		actualizaCampos(productos);
		setVisible(true);
	}

	/**
	 * Recibe al controlador y una lista de Productos para actualizar los datos desplegados en la ventana
	 * Se comunica con el controlador 
	 * 
	 * @param productos
	 */
	public void actualizaCampos(List<Producto> productos) {
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFCantidad.setText("");
				txtFDescripcion.setText( productos.get(comboBox.getSelectedIndex()).getDescripcion());
				txtFCantidadInventario.setText(Integer.toString(productos.get(comboBox.getSelectedIndex()).getCantidad()));
				txtFCantidadMinima.setText(Integer.toString(productos.get(comboBox.getSelectedIndex()).getMinimo()));
			}
		});
	}

	/**
	 * Valida que la cantidad ingresada por el usuario en la ventana sea un entero mayor a cero
	 * 
	 * @param cantidad
	 * @return un {@literal true} si valida o {@literal flase} en caso contrario
	 */
	public Boolean validaCantidad(Object cantidad) {
		try {
			if ( Integer.parseInt( (String) cantidad ) > 0 ) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * Se comunica con el controlador para ejecutar el metodo el metodo 
	 * {@link ControlRealizarPedido#aumentarCantidad(String, Integer)}
	 * 
	 * @param nombreProducto
	 * @param cantidad
	 * @return un {@literal true} si valida o {@literal flase} en caso contrario 
	 */
	public Boolean aumentaCantidad(String nombreProducto, Integer cantidad){
		try {
			 return control.aumentarCantidad(nombreProducto, cantidad);
		 }
		 catch (Exception e) {
			 return false;
		 }
	}

	/**
	 * Muestra un mesaje de dialogo en la ventana actual con el mensaje especificado
	 * 
	 * @param mensaje
	 */
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	/**
	 * Se comunica con el controlador para llamar el metodo cerrar
	 */
	public void cerrar() {
		control.cerrar();
	}

	/**
	 * Getter de DefaultTableModel
	 * 
	 * @return un {@link DefaultTableModel}
	 */
	public DefaultTableModel getModel() {
		return model;
	}

	/**
	 * Setter de DefaultTableModel
	 */
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
}
