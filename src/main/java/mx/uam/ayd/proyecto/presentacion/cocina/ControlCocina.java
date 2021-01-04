package mx.uam.ayd.proyecto.presentacion.cocina;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioOrden;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.presentacion.mesas.ControlMesas;
//import mx.uam.ayd.proyecto.presentacion.mesas.ControlRegistrarPedido;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

/**
 * @author Anonimux Corporation
 */
@Component
public class ControlCocina {
	
	@Autowired
	private VentanaCocina ventanaCocina;
	
	@Autowired
	private ControlMesas controlMesas;
	
	@SuppressWarnings("unused")
	@Autowired
	private ControlPrincipal controlPrincipal;
	
	@Autowired
	private ServicioOrden servicioOrden;
	
	/**
	 * Arrancar el control cocina, iniciando la ventanaCocina.
	 */
	public void inicia() {
		ventanaCocina.muestra(this);
	}
	/**
	 * Método que regresa una lista con las órdenes que esten en un estado en específico
	 * @param estado El estado que se busca. (ver {@link mx.uam.ayd.proyecto.negocio.modelo.Orden})
	 * @return LinkedList con las órdenes que estén en el estado indicado
	 */
	public LinkedList<Orden> recuperarOrdenesPorEstado(int estado) {
		return servicioOrden.recuperarOrdenesPorEstado(estado);
	}
	/**
	 * Método que actualiza una órden con un nuevo estado
	 * @param orden la órden a actualizar.
	 * @param estado el nuevo estado que la órden va a poseer (ver {@link mx.uam.ayd.proyecto.negocio.modelo.Orden}).
	 * @return true en éxito.
	 */
	public boolean actualizarEstadoOrden(Orden orden, int estado){
		return servicioOrden.actualizarEstadoOrden(orden, estado);
	}
	/**
	 * Notifica al mesero de que una órden esta lista para recoger y entregar a la mesa.
	 * @param orden La órden a recoger.
	 */
	public void notificarOrdenLista(Orden orden) {
		controlMesas.notificarOrdenLista(orden);
	}
	/**
	 * Actualiza la lista de comandas en la ventanaCocina para visualizar las nuevas órdenes
	 * @return true en éxito.
	 */
	public boolean actualizaListaComandas(){
		return ventanaCocina.actualizaListaComandas();
	}
	public void abreVentana() {
		ventanaCocina.abre(this);
		
	}

	public boolean compruebaCambioColor(int mesa) {
		int aux=0;
		LinkedList<Orden> ultimas = servicioOrden.ultimasOrdenes(mesa);
		for(Orden o: ultimas) {
			if(o.getEstado()==1) {
				aux++;
			}
		}
		if(aux==ultimas.size()) {
			controlMesas.actualizaStatusMesaRojo(mesa);
			return true;
		}
		return false;
	}
	
	public void recibeOrdenesCreadas(LinkedList<Orden> creadas) {
		ventanaCocina.recibeOrdenesCreadas(creadas);
	}
	//public void actualizaVentanaCocina() {
		//ventanaCocina.actualizado(this);
		
//	}
}
