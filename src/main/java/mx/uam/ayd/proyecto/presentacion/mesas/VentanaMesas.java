package mx.uam.ayd.proyecto.presentacion.mesas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Orden;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

/**
 * @author Anonymux Corporation
 *
 */
@Component
public class VentanaMesas {
	
	private JFrame frmMesasFondaSocorrito; //JFrame principal
	private ControlMesas controlMesas; //Control asociado a esta ventana
	private LinkedList<JButton> listaMesas = new LinkedList<JButton>(); //Lista que contiene los botones que representan gráficamente a las Mesas del local
	private JComboBox<String> comboBoxOpciones = new JComboBox<String>();
	private int flagCombo=0;
	private int i;
	/**
	 * Inicializa el contenido de la ventana.
	 */
	public VentanaMesas() {
		initialize();
	}

	/**
	 * Inicializa el contenido de la ventana.
	 */
	private void initialize() {
		frmMesasFondaSocorrito = new JFrame(); 
		frmMesasFondaSocorrito.setTitle("Mesas Fonda Socorrito Mixcoac");
		frmMesasFondaSocorrito.setBounds(100, 100, 450, 300);
		frmMesasFondaSocorrito.getContentPane().setLayout(new BorderLayout(0, 0));
		
		/**
		 * Se crea el panel que contiene a todas las Mesas.
		 */
		JPanel panel = new JPanel(); 
		frmMesasFondaSocorrito.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		/**
		 * Panel que contiene los JRadioButton.
		 */
		JPanel panelNorte = new JPanel();
		frmMesasFondaSocorrito.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 2, 0, 0));
		
		comboBoxOpciones.addItem("...");
		comboBoxOpciones.addItem("Registrar Pedido (1)");
		comboBoxOpciones.addItem("Ver ordenes (2)");
		comboBoxOpciones.addItem("Cerrar mesa (3)");
		panelNorte.add(comboBoxOpciones);
		panelNorte.add(new JLabel("Opciones"));
		
		/**
		 * A continuación se crean todos los botones que representan gráficamente
		 * a las Mesas del local, se agregan a un panel y se configura el 
		 * color de fondo en "VERDE" (mesa disponible).
		 * Cada vez que se presiona un botón mesa, se llama a la función 
		 * continuar(JRadioButton, JRadioButton, int, int).
		 */
		JButton mesa1 = new JButton("Mesa 1");
		listaMesas.add(0, mesa1);
		mesa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa1,1);
			}
		});
		mesa1.setBackground(Color.GREEN);
		panel.add(mesa1);
		
		JButton mesa2 = new JButton("Mesa 2");
		listaMesas.add(1, mesa2);
		mesa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa2,2);
				
			}
		});
		mesa2.setBackground(Color.GREEN);
		panel.add(mesa2);
		
		JButton mesa3 = new JButton("Mesa 3");
		listaMesas.add(2, mesa3);
		mesa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa3,3);
			}
		});
		mesa3.setBackground(Color.GREEN);
		panel.add(mesa3);
		
		JButton mesa4 = new JButton("Mesa 4");
		listaMesas.add(3, mesa4);
		mesa4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa4,4);
				
			}
		});
		mesa4.setBackground(Color.GREEN);
		panel.add(mesa4);
		
		JButton mesa5 = new JButton("Mesa 5");
		listaMesas.add(4, mesa5);
		mesa5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa5,5);
			}
		});
		mesa5.setBackground(Color.GREEN);
		panel.add(mesa5);
		
		JButton mesa6 = new JButton("Mesa 6");
		listaMesas.add(5, mesa6);
		mesa6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa6,6);
			}
		});
		mesa6.setBackground(Color.GREEN);
		panel.add(mesa6);
		
		JButton mesa7 = new JButton("Mesa 7");
		listaMesas.add(6, mesa7);
		mesa7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa7,7);
			}
		});
		mesa7.setBackground(Color.GREEN);
		panel.add(mesa7);
		
		JButton mesa8 = new JButton("Mesa 8");
		listaMesas.add(7, mesa8);
		mesa8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa8,8);
			}
		});
		mesa8.setBackground(Color.GREEN);
		panel.add(mesa8);
		
		JButton mesa9 = new JButton("Mesa 9");
		listaMesas.add(8, mesa9);
		mesa9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continuar(mesa9,9);
			}
		});
		mesa9.setBackground(Color.GREEN);
		panel.add(mesa9);
		
		
		/**
		 * Se crea un panel que contiene el botón "Regresar".
		 */
		JPanel panelCentro = new JPanel();
		frmMesasFondaSocorrito.getContentPane().add(panelCentro, BorderLayout.SOUTH);
		
		/**
		 * Se crea el botón "Regresar" cuya única función es 
		 * dejar de mostrar la ventana actual.
		 */
		JButton btnNewButton_10 = new JButton("Regresar");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMesasFondaSocorrito.setVisible(false);
				controlMesas.regresar();
			}
		});
		panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelCentro.add(btnNewButton_10);
	}
	
	/**
	 * Función que recibe un ControlMesas (flujo de control de la aplicación)
	 * y muestra la ventana actual.
	 * @param controlMesas Lleva el flujo de control de la aplicación.
	 */
	public void muestra(ControlMesas controlMesas) {
		this.controlMesas = controlMesas;
		frmMesasFondaSocorrito.setVisible(true);
		this.flagCombo = 0;
		comboBoxOpciones.setSelectedIndex(0);
		comboBoxOpciones.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
			      if(comboBoxOpciones.getSelectedItem().equals("Registrar Pedido (1)")) {
			    	  flagCombo = 1;
			    	  i=1;
			    	  for(JButton botonMesa: listaMesas) {
							if(botonMesa.getBackground()!=Color.GREEN) {
								botonMesa.setEnabled(false);
								botonMesa.setText("");
							}else {
								botonMesa.setEnabled(true);
								botonMesa.setText("Mesa "+i);
							}
							i++;
						}
			      }else if(comboBoxOpciones.getSelectedItem().equals("Ver ordenes (2)")) {
			    	  flagCombo = 2;
			    	  i=1;
			    	  for(JButton botonMesa: listaMesas) {
							if(botonMesa.getBackground()!=Color.ORANGE) {
								botonMesa.setEnabled(false);
								botonMesa.setText("");
							}else {
								botonMesa.setEnabled(true);
								botonMesa.setText("Mesa "+i);
							}
							i++;
						}
			      }else if(comboBoxOpciones.getSelectedItem().equals("Cerrar mesa (3)")) {
			    	  flagCombo = 3;
			    	  i=1;
			    	  for(JButton botonMesa: listaMesas) {
							if(botonMesa.getBackground()!=Color.RED) {
								botonMesa.setEnabled(false);
								botonMesa.setText("");
							}else {
								botonMesa.setEnabled(true);
								botonMesa.setText("Mesa "+i);
							}
							i++;
						}
			      }
			   }
			});
		
	}
	
	/**
	 * De acuerdo al JRadioButton seleccionado por el usuario realiza una acción:
	 * "Registrar nueva Orden" o "Cerrar mesa".
	 * @param mesa Mesa que se selecciono para registrar pedido
	 * @param numeroMesa Numero de mesa que se asignara
	 */
	public void continuar(JButton mesa, int numeroMesa) {
		/**
		 * En caso de que el usuario seleccione la opción "Registrar Pedido" del comboBox,
		 * se llama a la función registrarPedido()
		 */
		if(flagCombo==1) {
			controlMesas.registraPedido(numeroMesa);
		}else if(flagCombo==2) {
			controlMesas.cierraMesa(numeroMesa);
			cerrarDisabled();
		}else if(flagCombo==3) {
			controlMesas.cierraMesa(numeroMesa);
			cerrarEnabled();
		}else {
			JOptionPane.showMessageDialog(null, "Seleccione una opción y una mesa para continuar");
		}
	}
	
	/**
	 * 
	 * @param numeroMesa Número de la mesa cuyo color de fondo se cambia a "ROJO".
	 */
	public void actualizaStatusMesaRojo(int numeroMesa) {
		listaMesas.get(numeroMesa-1).setBackground(Color.RED);
	}
	
	/**
	 * Cambia el color de fondo de la mesa dada a "VERDE".
	 * @param numeroMesa Número de la mesa cuyo color de fondo se cambia a "VERDE".
	 */
	public void actualizaStatusMesaVerde(int numeroMesa) {
		listaMesas.get(numeroMesa-1).setBackground(Color.GREEN);
	}
	
	public void actualizaStatusMesaNaranja(int numeroMesa) {
		listaMesas.get(numeroMesa-1).setBackground(Color.ORANGE);
	}

	public void notificarOrdenLista(Orden orden) {
		JFrame platoListo = new JFrame();
		platoListo.setResizable(false);
		platoListo.setBounds(100, 100, 320, 200);
		platoListo.setTitle("Plato listo para servir");
		
		JPanel panel = new JPanel();
		platoListo.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		String mensaje = "el platillo " + orden.getPlatillo() + " de la mesa " + orden.getNumeroMesa() + " está listo";

		JLabel etiquetaMensaje = new JLabel(mensaje);
		etiquetaMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaMensaje.setVerticalAlignment(SwingConstants.CENTER);
		etiquetaMensaje.setBounds(12, 0, 296, 142);
		panel.add(etiquetaMensaje);
		
		JButton botonConfirmar = new JButton("ORDEN ENTREGADA");
		botonConfirmar.setBounds(71, 134, 179, 25);
		botonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlMesas.actualizarEstadoOrden(orden, Orden.ENTREGADA);
				platoListo.setVisible(false);
			}
		});
		panel.add(botonConfirmar);
		
		platoListo.setVisible(true);
	}
	
	public void cerrarEnabled() {
		controlMesas.cerrarEnabled();
	}
	
	public void cerrarDisabled() {
		controlMesas.cerrarDisabled();
	}
}
