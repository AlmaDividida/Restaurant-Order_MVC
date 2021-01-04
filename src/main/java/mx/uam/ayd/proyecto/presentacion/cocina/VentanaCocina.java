package mx.uam.ayd.proyecto.presentacion.cocina;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Orden;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Anonymux Corporation
 */
@Component
public class VentanaCocina {

	private ControlCocina controlCocina;		//enláce al control correspondiente
	private JFrame marcoMesa;					//marco que contiene los elementos de la ventana
	private JPanel contenedor;					//contenedor que almacenará las comandas
	private JScrollPane panelOrdenes;			//panel de desplazamiento para compactar el contenedor
	private int numMesa;
	private int j;
	private int indexComandas=-1;
	/**
	 * Lista con las comandas y sus botones asociados
	 */
	public LinkedList<Comanda> comandas;
	/**
	 * Lista de las ordenes ya procesadas
	 */
	public LinkedList<Orden> ordenesPendientesTotal = new LinkedList<Orden>();
	public LinkedList<Orden> ordenesPendientesNuevas = new LinkedList<Orden>();

	/**
	 * Crear la aplicación
	 */
	public VentanaCocina() {
		this.comandas = new LinkedList<Comanda>();
		initialize();
	}

	/**
	 * Inicializar los contenidos del marco
	 */
	private void initialize() {
		
		/*
		 * creamos un marco principal
		 * llamado "ordenes a preparar"
		 * de tamaño 640 x 480 px
		 * con disposición de retícula
		*/
		marcoMesa = new JFrame();
		marcoMesa.setTitle("Ordenes a preparar");
		marcoMesa.setBounds(100, 100, 640, 480);
		marcoMesa.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		/*
		 * creamos un panel que va a contener las órdenes
		 * con disposición MigLayout, que cada 2 columnas haga una nueva fila
		 * con la primera columna estirandose hasta ocupar todo el espacio posible
		 * alineando sus elementos a la izquierda,
		 * y la segúnda columna alineando sus elementos al centro
		 * con (inicialmente al menos) una fila.
		 */
		contenedor = new JPanel();
		contenedor.setLayout(new MigLayout("wrap 2", "[grow,left][center]", "[]"));
		
		/*
		 * creamos un panel de scroll que alojará el panel "contenedor"
		 * que siempre tenga visible la barra de desplazamiento vertical,
		 * pero que siempre oculte la barra de despl. horizontal,
		 * y la agregamos al marco principal.
		 */
		panelOrdenes = new JScrollPane(contenedor);
		panelOrdenes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelOrdenes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		marcoMesa.getContentPane().add(panelOrdenes);
	}
	
	
	/**
	 * Muestra la ventana de cocina desde {@link mx.uam.ayd.proyecto.presentacion.cocina.ControlCocina}
	 * @param controlCocina en contol de cocina asociado a la vista
	 */

	public void muestra(ControlCocina controlCocina) {
		// inicializamos el control de cocina para llamar sus métodos
		// y hacemos la ventana visible.
		// por túltimo actualizamos la lista de comandas
		this.controlCocina = controlCocina;
		marcoMesa.setVisible(true);
		actualizaListaComandas();

	}
	
	/**
	 * Actualizar la lista de comandas que se muestran en la ventana de cocina
	 * @return true en caso de éxito
	 */
	
	public boolean actualizaListaComandas(){
		/*
		 * iteramos sobre la lista de ordenes pendientes
		 * y creamos una instáncia de la clase Comanda dentro de la lista comandas.
		 * Luego omamos los elementos de la interfaz de Comanda y los agregamos a la interfaz
		 */
		Orden orden = new Orden();
		j = ordenesPendientesTotal.size()-ordenesPendientesNuevas.size();
		for (int i=j; i<ordenesPendientesTotal.size(); i++) {
			orden = ordenesPendientesTotal.get(i);
			numMesa = orden.getNumeroMesa();
			this.comandas.add(new Comanda(orden, contenedor));
			indexComandas++;
			contenedor.add(comandas.get(indexComandas).etiqueta);
			contenedor.add(comandas.get(indexComandas).boton);
		}
	
		/*
		 * actuaizamos la interfaz para mostrar los nuevos
		 * elementos
		 */
		contenedor.revalidate();
		contenedor.repaint();
		return true;
	}
	
	/**
	 * subclase para manejar las ordenes de forma más fácil
	 * @author Anonimux Corporation
	 */
	class Comanda {
		Orden orden;		//la orden que dio origen a la comanda
		String platillo;	//el platilo a mostrar en la lista de comandas
		JLabel etiqueta;	//elemento de la interfaz que mostrará el platillo
		JButton boton;		//botón para marcar el platillo como listo
		JPanel contenedor;	//contenedor de elementos de la interfaz donde van las ordenes
		Comanda comanda;	//referencia de la clase a si misma
		
		/**
		 * Constructor de la clase
		 * @param orden la órden que genera esta comanda
		 * @param contenedor el JPanel que va almacenar las órdenes que llegan
		 */
		public Comanda(Orden orden, JPanel contenedor) {
			/*
			 * asignamos las variables orden y contenedor a los parámetros pasados,
			 * así como la referencia a la clase en si misma
			 * el nombre de platillo y la etiqueta lo sacamos de la orden,
			 * creamos el botón para indicar que la orden está lista,
			 * y ponemos que al presionarse se ejecute el método remover()
			 */
			this.orden = orden;
			this.contenedor = contenedor;
			this.comanda = this;
			this.platillo=orden.getPlatillo();
			etiqueta = new JLabel(platillo+"     Mesa: "+numMesa);
			boton = new JButton("ORDEN LISTA");
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remover(etiqueta, boton, comanda, orden);	
					compruebaCambioColor(orden);
				}
			});
	 	}
		
		/**
		 * Método que remueve la comanda tanto de la interfaz como de las ordenes listas
		 * @param etiqueta la etiqueta de texto que se asocia a la comanda
		 * @param boton el botón de "ORDEN LISTA" que se aocia a la comanda
		 * @param comanda la comanda a remover de la vista
		 * @param orden la órden asociada a la comanda
		 */
		void remover(JLabel etiqueta, JButton boton, Comanda comanda, Orden orden) {
			/*
			 * quita de la interfaz la etiqueta y el botón,
			 * actualiza la interfaz,
			 * agrega la orden a la lista de ordenes listas,
			 * notifica que la órdnen esta lista,
			 * y borrar la comanda de la lista de comandas
			 */
			contenedor.remove(etiqueta);
			contenedor.remove(boton);
			contenedor.revalidate();
			contenedor.repaint();
			controlCocina.actualizarEstadoOrden(orden, Orden.LISTA);
			controlCocina.notificarOrdenLista(orden);
			comandas.remove(comanda);
			indexComandas--;
		}
		
		public void compruebaCambioColor(Orden orden) {
			int mesa = orden.getNumeroMesa();
			controlCocina.compruebaCambioColor(mesa);
		}
	}

	public void recibeOrdenesCreadas(LinkedList<Orden> creadas) {
		ordenesPendientesNuevas.clear();
		ordenesPendientesNuevas.addAll(creadas);
		ordenesPendientesTotal.addAll(ordenesPendientesNuevas);
	}
	
	public void abre(ControlCocina control) {
		this.controlCocina = control;
		marcoMesa.setVisible(true);
	}
}