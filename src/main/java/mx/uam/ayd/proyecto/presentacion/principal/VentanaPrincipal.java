package mx.uam.ayd.proyecto.presentacion.principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Anonimux Corporation
 *
 */
@Component
public class VentanaPrincipal {

	private JFrame ventPrincipal;
	private ControlPrincipal control;
	
	/**
	 * Crea la aplicación.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Inicializa el contenido del JFrame.
	 */
	private void initialize() {
		/**
		 * Se agrega un nuevo JFrame a la ventana con el título "Vista Principal",
		 * se configuran los límites del margen, se elige la operación por defecto de cierre
		 * de la ventana y se configura su layout como BorderLayout.
		 */
		ventPrincipal = new JFrame();
		ventPrincipal.setTitle("Vista Principal");
		ventPrincipal.setBounds(100, 100, 516, 323);
		ventPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventPrincipal.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//ImageIcon iconMesa = ();
		//ImageIcon iconMesa = ();
		//ImageIcon iconMesa = ();
		//ImageIcon iconMesa = ();
		//ImageIcon iconMesa = ();
		/**
		 * Se agrega el JPanel que contiene todos los botones de esta ventana.
		 */
		JPanel vistasGridLay = new JPanel();
		ventPrincipal.getContentPane().add(vistasGridLay, BorderLayout.CENTER);
		vistasGridLay.setLayout(new GridLayout(0, 2, 0, 0));
		
		/**
		 * Se crea el botón "Mesas", que al ser presionado por el usuario,
		 * llama a la función mesas() del control"
		 */
		JButton btnMesas = new JButton("Mesas");
		btnMesas.setIcon(new ImageIcon("iconos/mesa.png"));
		btnMesas.setIconTextGap(50);
		btnMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.mesas();
			}
		});
		vistasGridLay.add(btnMesas);
		
		/**
		 * Se crea el botón "Cocina", que al ser presionado por el usuario,
		 * llama la función cocina() del control"
		 */
		JButton btnCocina = new JButton("Cocina");
		btnCocina.setIcon(new ImageIcon("iconos/cocina.png"));
		btnCocina.setIconTextGap(50);
		btnCocina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cocina();
			}
		});
		vistasGridLay.add(btnCocina);
		
		/**
		 * Se crea el botón "Caja", que al ser presionado por el usuario,
		 * llama la función caja() del control"
		 */
		JButton btnCaja = new JButton("Caja");
		btnCaja.setIcon(new ImageIcon("iconos/caja.png"));
		btnCaja.setIconTextGap(50);
		btnCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.caja();
			}
		});
		vistasGridLay.add(btnCaja);
		
		/**
		 * Se crea el botón "Inventario", que al ser presionado por el usuario,
		 * llama la función inventario() del control"
		 */
		JButton btnRealizarPedido = new JButton("Realizar un Pedido");
		btnRealizarPedido.setIcon(new ImageIcon("iconos/diablito.png"));
		btnRealizarPedido.setIconTextGap(50);
		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.realizarPedido();
			}
		});
		vistasGridLay.add(btnRealizarPedido);
		
		JButton btnInventarioCocina = new JButton("Inventario Cocina");
		btnInventarioCocina.setIcon(new ImageIcon("iconos/refri.png"));
		btnInventarioCocina.setIconTextGap(50);
		btnInventarioCocina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.inventarioCocina();
			}
		});
		vistasGridLay.add(btnRealizarPedido);
		vistasGridLay.add(btnInventarioCocina);
		
		JButton btnInformeInventario = new JButton("Informe Inventario");
		btnInformeInventario.setIcon(new ImageIcon("iconos/tabla.png"));
		btnInformeInventario.setIconTextGap(50);
		btnInformeInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.informeInventario();
			}
		});
		vistasGridLay.add(btnInformeInventario);
	}
	
	/**
	 * Actualiza el controlador de esta ventana y la muestra.
	 * @param control ControlPrincipal que lleva el flujo de control de esta ventana
	 */
	public void muestra(ControlPrincipal control) {
			
			this.control = control;	
			ventPrincipal.setVisible(true);
		}
}
