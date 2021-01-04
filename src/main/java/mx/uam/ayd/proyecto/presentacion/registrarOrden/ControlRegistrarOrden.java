package mx.uam.ayd.proyecto.presentacion.registrarOrden;

import java.sql.Timestamp;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioOrden;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.presentacion.cocina.ControlCocina;
import mx.uam.ayd.proyecto.presentacion.mesas.ControlMesas;

/**
 * @author Anonymux Corporation
 */

@Component
public class ControlRegistrarOrden {
	
	@Autowired
	private ServicioOrden servicioOrden;
	
	@Autowired
	private VentanaRegistrarOrden ventanaRegistrarOrden;
	
	@Autowired
	private ControlMesas controlMesas;
	
	@Autowired
	private ControlCocina controlCocina;

	/**
	 * Muestra la ventana donde se registran las órdenes.
	 * @param numeroMesa Número de la mesa sobre la que se registran los pedidos.
	 */
	public void inicia(int numeroMesa) {
		ventanaRegistrarOrden.muestra(this, numeroMesa);
	}

	/**
	 * Cambia el color de fondo de la mesa dada a "ROJO".
	 * @param numeroMesa Número de la mesa que cambia de estado.
	 */
	public void actualizaStatusMesaNaranja(int numeroMesa) {
		controlMesas.actualizaStatusMesaNaranja(numeroMesa);
	}

	/**
	 * Manda una lista de pedidos al ServicioOrden; por cada pedido de la lista se crea
	 * una Orden y la guarda en persistencia. 
	 * @param pedidos Lista con todos los pedidos (campo de Orden) que se van a crear.
	 * @param numMesa Número de mesa que será asignado a todas las órdenes creadas.
	 * @return servicioOrden.creaOrdenes(pedidos, numMesa, hr) Llama a ServicioOrden para guardar las órdenes en persistencia.
	 */
	public boolean creaOrden(LinkedList<String> pedidos, int numMesa) {
		java.util.Date utilDate = new java.util.Date();
		long lnMilisegundos = utilDate.getTime();
		Timestamp hr = new Timestamp(lnMilisegundos);
		muestraVentanaCocina();
		LinkedList<Orden> ordenesCreadas = servicioOrden.creaOrdenes(pedidos, numMesa, hr);
		mandaCreadasACocina(ordenesCreadas);
		if(ordenesCreadas.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	private void muestraVentanaCocina() {
		controlCocina.abreVentana();
		
	}

	public boolean actualizaListaComandas() {
		return controlMesas.actualizaListaComandas();
	}
	
	public void mandaCreadasACocina(LinkedList<Orden> creadas) {
		controlCocina.recibeOrdenesCreadas(creadas);
	}


}