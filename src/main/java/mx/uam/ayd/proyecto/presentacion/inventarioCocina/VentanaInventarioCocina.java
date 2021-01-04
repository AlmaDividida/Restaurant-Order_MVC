package mx.uam.ayd.proyecto.presentacion.inventarioCocina;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
/**
 * Ventana que tiene que ver con la interaccion de agregar productos y modificarlos
 * @author Anonymux Corporation
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaInventarioCocina extends JFrame {

	private JPanel contentPane;
	private ControlInventarioCocina controlInventario;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel(){ 
	    @Override 
	    public boolean isCellEditable(int row, int column) { 
	     return false; 
	    } 
	};
	/**
	 * Inicializa el contenido de la ventana
	 */
	public VentanaInventarioCocina() {
		initialize();
	}

	private void initialize() {
		setTitle("Inventario");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(48, 197, 89, 23);
		contentPane.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlInventario.agregarProducto();
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlInventario.modificarProducto();
			}
		});
		btnModificar.setBounds(289, 197, 89, 23);
		contentPane.add(btnModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 33, 379, 153);
		contentPane.add(scrollPane);
		
		table = new JTable(modelo);
		modelo.addColumn("ID_Producto");
		modelo.addColumn("Nombre");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Cantidad");
		
		scrollPane.setViewportView(table);
	}
	/**
	 * Muestra la ventana inventario con los productos previamente en la BD
	 * @param control Control del inventario asociado a la vista
	 * @param productos Productos que estan en la BD
	 */
	public void muestra(ControlInventarioCocina control, List <Producto> productos) {
		this.controlInventario = control;		
		modelo.setRowCount(0);
		for(Producto producto:productos) {
			modelo.addRow(new Object[] {producto.getIdProducto(), producto.getNombreProducto(), producto.getDescripcion(),producto.getCantidad()});
			table.setModel(modelo);
		}
		
		setVisible(true);

	}
	/**
	 * Llama al control inventario para que muestre la ventana de Agregar Producto 
	 */
	public void agregarProducto() {
		controlInventario.agregar();
	}
	/**
	 * Muestra un mensaje que se le pase en el control al intentar agregar un producto
	 * @param mensaje Mensaje que dice su un producto fue agregado o no
	 */
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	/**
	 * Actualiza la ventana inventario para que se muestren los nuevos productos agregados
	 * mostrando en el orden que se agregan
	 * @param control El control inventario asociado a la vista
	 * @param productos Los productos a mostrar que fueron agregados o ya existen en la BD
	 */
	public void actualizaProductos(ControlInventarioCocina control, List <Producto> productos) {
		this.controlInventario = control;		
		modelo.setRowCount(0);
		for(Producto producto:productos) {
			modelo.addRow(new Object[] {producto.getIdProducto(), producto.getNombreProducto(), producto.getDescripcion(),producto.getCantidad()});
			table.setModel(modelo);
		}	
		setVisible(true);
	}
}
