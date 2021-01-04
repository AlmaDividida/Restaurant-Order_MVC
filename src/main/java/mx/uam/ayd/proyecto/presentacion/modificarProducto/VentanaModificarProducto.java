package mx.uam.ayd.proyecto.presentacion.modificarProducto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
/**
 * 
 * @author Anonymux Corporation
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textDescripcionNueva;
	private ControlModificarProducto controlModificarProducto;
	private JTextField textNombreProducto;
	private JSpinner cantidadMinima;
	private JComboBox<Integer> comboBox;
	/**
	 * Metodo que inicializa los componentes
	 */
	public VentanaModificarProducto() {
		initialize();
	}
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("Inventario");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modificar Articulo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(137, 11, 136, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID producto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(110, 58, 86, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Descripcion nueva");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(12, 125, 113, 14);
		contentPane.add(lblNewLabel_3);
		
		textDescripcionNueva = new JTextField();
		textDescripcionNueva.setText("");
		textDescripcionNueva.setBounds(119, 123, 305, 20);
		contentPane.add(textDescripcionNueva);
		textDescripcionNueva.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad minima");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(21, 166, 119, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Nombre Producto");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(12, 93, 114, 14);
		contentPane.add(lblNewLabel_6);
		
		textNombreProducto = new JTextField();
		textNombreProducto.setBounds(118, 92, 306, 20);
		contentPane.add(textNombreProducto);
		textNombreProducto.setColumns(10);
		
		cantidadMinima = new JSpinner();
		cantidadMinima.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		cantidadMinima.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cantidadMinima.setBounds(129, 164, 38, 20);
		contentPane.add(cantidadMinima);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(189, 56, 70, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Realizar cambios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombreProducto.getText().equals("") || textDescripcionNueva.getText().equals("") || cantidadMinima.getValue().equals(0)) {
					muestraDialogoConMensaje("Hay campos vacios o Este producto no existe");
				}else {
					controlModificarProducto.modificarProducto(textNombreProducto.getText(), (Integer)comboBox.getSelectedItem(),textDescripcionNueva.getText(),(Integer)cantidadMinima.getValue());
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(57, 211, 148, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlModificarProducto.termina();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(281, 211, 89, 23);
		contentPane.add(btnNewButton_1);
			
	}
	/**
	 * Llamamos al controlModificarProducto para usar sus metodos
	 * y nos muestre en el comboBox el ID de los productos existentes para poder modificarlos
	 * @param controlModificarProducto Control encargado de modificar asociado a la vista
	 * @param productos existentes en la BD para poder ser modificados
	 */
	public void muestra(ControlModificarProducto controlModificarProducto, List<Producto> productos) {
		this.controlModificarProducto = controlModificarProducto; 
		DefaultComboBoxModel<Integer> comboBoxModel = new DefaultComboBoxModel<>();
		for(Producto producto:productos) {
			comboBoxModel.addElement(producto.getIdProducto());
		}
		comboBox.setModel(comboBoxModel);
		textNombreProducto.setText("");
		textDescripcionNueva.setText("");
		cantidadMinima.setValue(0);
		setVisible(true);
	}
	/**
	 * Muestra un mensaje de exito al modificarse un producto
	 * @param mensaje que dice que el productos se modifico exitosamente
	 */
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}
