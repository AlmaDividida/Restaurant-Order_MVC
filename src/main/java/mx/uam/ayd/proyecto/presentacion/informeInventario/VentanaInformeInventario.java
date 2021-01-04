package mx.uam.ayd.proyecto.presentacion.informeInventario;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.realizarPedido.ControlRealizarPedido;

import javax.swing.JTable;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

/**
 * Ventana de la HU-44 Realizar un Pedido
 * 
 * @author Anonymux Corporation
 * 
 */
@SuppressWarnings("serial")
@Component
public class VentanaInformeInventario extends JFrame {
	
	/**
	 * Se declara el contenedor, sirven para agrupar los objetos de esta ventana (buttons, 
	 * checkboxs, jtexts, etc...)
	 */
	private JPanel contentPane;
	/**
	 * Se declara la comunicación con el controlador de Realizar Pedidos{@link ControlRealizarPedido}
	 */
	private ControlInformeInventario control;
	/**
	 * Se declara la tabla donde se despliegan los datos obtenido
	 */
	private JTable table;
	/**
	 * Se declara el {@link DefaultTableModel} que se usa en la ventana con el nombre model
	 */
	private DefaultTableModel model = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Fecha", "ID", "Nombre Producto", "Descripci\u00F3n", "Cantidad", "Cantidad M\u00EDnima"
		}
	) {
		boolean[] columnEditables = new boolean[] {
			false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgregarUsuario frame = new VentanaAgregarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaInformeInventario() {
		setTitle("Informes de Inventario");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Informes de Inventario");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 32));
		
		JLabel lblTabla = new JLabel("Productos que necesitan atención (hay escasez) ");
		lblTabla.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnRealizarPedido = new JButton("Realizar un pedido");
		btnRealizarPedido.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(117)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTabla, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnCerrar)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)))
					.addGap(42))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(227, Short.MAX_VALUE)
					.addComponent(btnRealizarPedido, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(202))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(lblTabla, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRealizarPedido, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(btnCerrar)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		table.getColumnModel().getColumn(3).setPreferredWidth(203);
		table.getColumnModel().getColumn(5).setPreferredWidth(99);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		// Listeners de los botones de la ventana
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cerrar();
			}
		});
		
		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaRealizarPedido();
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
	public void muestra(ControlInformeInventario control, List <Producto> productos) {
		this.control = control;
		model.setRowCount(0);
		for(Producto producto:productos) {
			model.addRow(new Object[] {producto.getFecha(), producto.getIdProducto(),producto.getNombreProducto() , producto.getDescripcion(), producto.getCantidad(), producto.getMinimo()});
		}
		setVisible(true);
	}
}
