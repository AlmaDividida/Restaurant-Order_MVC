package mx.uam.ayd.proyecto.presentacion.registrarOrden;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import org.springframework.stereotype.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

/**
 * @author Anonymux Corporation
 */

@Component
public class VentanaRegistrarOrden {
	
	private ControlRegistrarOrden controlRegistrarOrden; //Control asociado a esta ventana
	private JFrame frmMenu; //JFrame principal de la ventana
	private JPanel panelBotones = new JPanel();	//JPanel destinado a albergar los botones "Ordenar" y "Cancelar"
	private int indexMesa; //Almacena el número de mesa sobre la cual se registra una Orden
	private LinkedList<JSpinner> listaSpinner = new LinkedList<JSpinner>(); //Contiene todos los JSpinner utilizados en la ventana
	private LinkedList<String> menuCompleto = new LinkedList<String>(); //Contiene todos los platillos mostrados a través de los JLabel en la ventana 

	/**
	 * Crea la aplicación.
	 */
	public VentanaRegistrarOrden() {
		initialize();
	}

	/**
	 * Inicializa el contenido del JFrame.
	 */
	private void initialize() {
		/*
		 * Se crea un nuevo JFrame con sus límites de margen.
		 */
		frmMenu = new JFrame(); 
		frmMenu.setBounds(100, 100, 295, 308);
		
		/*
		 * Se agrega un BorderLayout al JFrame.
		 */
		frmMenu.getContentPane().setLayout(new BorderLayout(0, 0));
		
		/*
		 * Se crea un nuevo panel destinado a albergar los JLabel con los nombres de los platillos
		 * y los JSpinner que le permiten al usuario seleccionar la cantidad de platillos requeridos.
		 * El panel se agrega en el CENTRO del BorderLayout definido para el JFrame principal.
		 * Se configura el layot del panel como un GridLayout.
		 */
		JPanel panelCentro = new JPanel();
		frmMenu.getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		/*
		 * Se agregan todos los JLabel y JSpinner que se utilizan en la ventana para la interacción con el usuario.
		 */
		JLabel lblNewLabel = new JLabel("Sopa");
		panelCentro.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner);
		listaSpinner.add(0, spinner);
		menuCompleto.add(0,"Sopa");
		
		JLabel lblNewLabel_1 = new JLabel("Consome");
		panelCentro.add(lblNewLabel_1);
		
		JSpinner spinner_1 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_1);
		listaSpinner.add(1, spinner_1);
		menuCompleto.add(1,"Consome");
		
		JLabel lblNewLabel_2 = new JLabel("Arroz");
		panelCentro.add(lblNewLabel_2);
		
		JSpinner spinner_2 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_2);
		listaSpinner.add(2, spinner_2);
		menuCompleto.add(2,"Arroz");
		
		JLabel lblNewLabel_3 = new JLabel("Pasta");
		panelCentro.add(lblNewLabel_3);
		
		JSpinner spinner_3 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_3);
		listaSpinner.add(3, spinner_3);
		menuCompleto.add(3,"Pasta");
		
		JLabel lblNewLabel_4 = new JLabel("Taco Azteca");
		panelCentro.add(lblNewLabel_4);
		
		JSpinner spinner_4 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_4);
		listaSpinner.add(4, spinner_4);
		menuCompleto.add(4,"Taco Azteca");
		
		JLabel lblNewLabel_5 = new JLabel("Filete empanizado");
		panelCentro.add(lblNewLabel_5);
		
		JSpinner spinner_5 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_5);
		listaSpinner.add(5, spinner_5);
		menuCompleto.add(5,"Filete empanizado");
		
		JLabel lblNewLabel_6 = new JLabel("Chile relleno");
		panelCentro.add(lblNewLabel_6);
		
		JSpinner spinner_6 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_6);
		listaSpinner.add(6, spinner_6);
		menuCompleto.add(6,"Chile relleno");
		
		JLabel lblNewLabel_7 = new JLabel("Carne de Res");
		panelCentro.add(lblNewLabel_7);
		
		JSpinner spinner_7 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_7);
		listaSpinner.add(7, spinner_7);
		menuCompleto.add(7,"Carne de Res");
		
		JLabel lblNewLabel_8 = new JLabel("Enchiladas");
		panelCentro.add(lblNewLabel_8);
		
		JSpinner spinner_8 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_8);
		listaSpinner.add(8, spinner_8);
		menuCompleto.add(8,"Enchiladas");
		
		JLabel lblNewLabel_9 = new JLabel("Gelatina");
		panelCentro.add(lblNewLabel_9);
		
		JSpinner spinner_9 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_9);
		listaSpinner.add(9, spinner_9);
		menuCompleto.add(9,"Gelatina");
		
		JLabel lblNewLabel_10 = new JLabel("Flan");
		panelCentro.add(lblNewLabel_10);
		
		JSpinner spinner_10 = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		panelCentro.add(spinner_10);
		listaSpinner.add(10, spinner_10);
		menuCompleto.add(10,"Flan");
		
		/*
		 * Se agrega al JFrame principal el panel que alberga los botones "Ordenar" y "Cancelar"
		 * en la posición SOUTH del BorderLayout.
		 */
		frmMenu.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		/*
		 * Se agrega el botón "Ordenar".
		 */
		JButton ordenar = new JButton("Ordenar");
		ordenar.addActionListener(new ActionListener() {
			/*
			 * Se define la acción a realizar en caso de que se presione el botón "Ordenar".
			 */
			public void actionPerformed(ActionEvent e) {
				LinkedList<String> pedidos = new LinkedList<String>();
				pedidos = spinnerListToString(listaSpinner);
				/*
				 * Si la lista de pedidos (posteriormente Ordenes) se ha creado satisfactoriamente
				 * en persistencia, muestra un mensaje de éxito y actualiza el color de la mesa
				 * sobre la cuál se levantó la orden a "ROJO" (mesa ocupada).
				 */
				if(creaOrden(pedidos, indexMesa)) {
					JOptionPane.showMessageDialog(null, "Se ha enviado la orden a cocina");
					actualizaStatusMesaNaranja(indexMesa);
					
				}else{
					JOptionPane.showMessageDialog(null, "No se ha enviado la orden (orden nula)");
				}
				actualizaSpinner();
				controlRegistrarOrden.actualizaListaComandas();
				frmMenu.setVisible(false);
			}
		});
		panelBotones.add(ordenar);
		
		/**
		 * Se crea el botón "Cancelar", cuya única función es cerrar esta ventana.
		 */
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);
			}
		});
		panelBotones.add(cancelar);
	}
	
	/**
	 * Recibe el controlRegistrarOrden del que proviene esta ventana 
	 * y el número de mesa seleccionado por el usuario. Muestra la ventana en pantalla.
	 * @param controlRegistrarOrden Control asociado a esta ventana.
	 * @param numeroMesa Número de la mesa que fue seleccionada por el usuario para registrar una orden.
	 */
	public void muestra(ControlRegistrarOrden controlRegistrarOrden, int numeroMesa) {
		this.controlRegistrarOrden = controlRegistrarOrden;
		this.indexMesa = numeroMesa;
		frmMenu.setTitle("Registrar Pedido (Mesa "+numeroMesa+")");
		frmMenu.setVisible(true);
	}
	
	/**
	 * Recibe un número de mesa y cambia el color de fondo a "ROJO" 
	 * del botón que representa a esa mesa en la VentanaMesas.
	 * @param numeroMesa El número de la mesa cuyo color de fondo se desea actualizar a "ROJO".
	 */
	public void actualizaStatusMesaNaranja(int numeroMesa) {
		controlRegistrarOrden.actualizaStatusMesaNaranja(numeroMesa);
	}
	
	/**
	 * Regresa a 0 el valor mostrado por todos los JSpinner de la ventana.
	 */
	public void actualizaSpinner() {
		this.listaSpinner.forEach(spinner -> spinner.setValue(0));
	}
	
	/**
	 * Se encarga de mapear la cantidad de pedidos que el usuario selecciona a través de los 
	 * JSpinner. Regresa una lista de String, donde cada elemento de la lista corresponde a un pedido
	 * @param listaSpinner Lista que contiene todos los JSpinner de la ventana.
	 * @return listaPedidos Lista de String que contiene todos los pedidos.
	 */
	public LinkedList<String> spinnerListToString (LinkedList<JSpinner> listaSpinner) {
		LinkedList<String> listaPedidos = new LinkedList<String>();
		Integer cantidad;
		/*
		 * Para cada elemento de la lista de JSpinner, extrae la "cantidad" seleccionada por el usuario,
		 * es decir, guarda las veces que se pide un platillo en la variable "cantidad" .
		 */
		for(int i=0; i<listaSpinner.size();i++) {
			cantidad = (Integer) listaSpinner.get(i).getValue()-1;
			/*
			 * Agrega en la lista de platillos tantos platillos como "cantidad" de ese platillo
			 * haya seleccionado el usuario.
			 */
			for(int j=0; j<=cantidad; j++) {
				listaPedidos.add(menuCompleto.get(i));
			}
		}
		return listaPedidos;
	}
	
	/**
	 * Guarda en persistencia los los pedidos enviados
	 * (dichos pedidos son convertidos a Ordenes y se guardan en OrdenRepository).
	 * @param pedidos Lista con todos los pedidos .
	 * @param numMesa Número de mesa de la Orden.
	 * @return controlRegistrarPedido.creaOrden(pedidos, numMesa) Llama al control de esta ventana para crear las Ordenes enviadas con el numero de mesa en la Lista de pedidos.
	 */
	public boolean creaOrden(LinkedList<String> pedidos, int numMesa) {
		return controlRegistrarOrden.creaOrden(pedidos, numMesa);
	}
}
