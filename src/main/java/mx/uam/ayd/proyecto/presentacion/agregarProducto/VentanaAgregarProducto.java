package mx.uam.ayd.proyecto.presentacion.agregarProducto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
/**
 * 
 * @author Anonymux Corporation
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaAgregarProducto extends JFrame {
	/**
	 * Declaramos los componentes a utilizar de la ventana
	 */
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textFecha;
	private JSpinner spinner; 
	private JSpinner cantidadMinima;
	private ControlAgregarProducto controlAgregarProducto;
	private int cantidadTotal;
	private List<Producto> productos;
	private JTextField textCantidadActual = new JTextField();
	/**
	 * Metodo encargado de inicializar sus componentes que tiene la ventana
	 */
	public VentanaAgregarProducto() {
		setTitle("Agregar Producto");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agregar Articulo");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(144, 11, 142, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblnombreProducto = new JLabel("Nombre");
		lblnombreProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnombreProducto.setBounds(53, 49, 54, 22);
		contentPane.add(lblnombreProducto);
		
		JLabel lbldescripcion = new JLabel("Descripcion");
		lbldescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbldescripcion.setBounds(53, 82, 75, 22);
		contentPane.add(lbldescripcion);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFecha.setBounds(53, 115, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad Actual");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(53, 175, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(141, 51, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setText("");
		textDescripcion.setBounds(144, 84, 86, 20);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(141, 113, 86, 20);
		contentPane.add(textFecha);
		textFecha.setColumns(10);
		
		textCantidadActual.setEditable(false);
		textCantidadActual.setBounds(152, 173, 75, 20);
		contentPane.add(textCantidadActual);
		textCantidadActual.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad a Agregar");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(240, 176, 111, 17);
		contentPane.add(lblNewLabel_2);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner.setBounds(361, 173, 39, 20);
		contentPane.add(spinner);
		
		cantidadMinima = new JSpinner();
		cantidadMinima.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		cantidadMinima.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cantidadMinima.setBounds(352, 113, 39, 20);
		contentPane.add(cantidadMinima);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFecha.getText().equals("") || textNombre.getText().equals("") || textDescripcion.getText().equals("") || spinner.getValue().equals(0) || cantidadMinima.getValue().equals(0)) {
					muestraDialogoConMensaje("No deben de haber campos vacios");
				}else {
					actualizaVentana();
					controlAgregarProducto.agregarProducto(textFecha.getText(), textNombre.getText(), textDescripcion.getText(), (Integer)spinner.getValue(), (Integer)cantidadMinima.getValue());
					textCantidadActual.setText(""+cantidadTotal);
				}
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregar.setBounds(53, 227, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlAgregarProducto.termina();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(274, 228, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad Minima");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(237, 116, 114, 14);
		contentPane.add(lblNewLabel_3);
	}
	/**
	 * Metodo que hace que en la ventana aparezca la cantidad de productos totales
	 * @param controlAgregarProducto Control que me permite usar sus metodos
	 * @param producto Productos que se encuentran en la BD para obtener su cantidad total
	 * @param cantidad Cantidad que guarda el total de productos para mostrar en la ventana
	 */
	public void muestra(ControlAgregarProducto controlAgregarProducto, List<Producto> producto, int cantidad) {
		this.controlAgregarProducto = controlAgregarProducto;
		this.productos = producto;
		textFecha.setText("");
		textNombre.setText("");
		textDescripcion.setText("");
		spinner.setValue(0);
		cantidadMinima.setValue(0);
		textCantidadActual.setText("" + cantidad);
		cantidadTotal();
		System.out.println("Cantidad Total: " + this.cantidadTotal);
		setVisible(true);
	}
	/**
	 * metodo que calcula la cantidad total de productos 
	 * para mostrar en la ventana
	 */
	public void cantidadTotal() {
		this.cantidadTotal = 0;
		for(Producto producto:productos) {
			this.cantidadTotal+= producto.getCantidad(); 
		}
	}
	/**
	 * actualiza la ventana despues de agregar un producto
	 * para que se agrege el producto y se muestre en la ventana inventario
	 */
	public void actualizaVentana() {
		controlAgregarProducto.actualizaVentana();
	}
	/**
	 * Metodo encargado de mostrar un mensaje de exito cuando se agrega un producto
	 * @param mensaje Mensaje de exito o error al agregar producto
	 */
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	/**
	 * se encarga de mostrar actualizar la cantidad de productos despues de
	 * que se agrega cada producto para que se  muestre en la ventana
	 * @param controlAgregarProducto2 control encargado de actualizar vista
	 */
	public void actualizado(ControlAgregarProducto controlAgregarProducto2) {
		this.controlAgregarProducto= controlAgregarProducto2;
	}
}
