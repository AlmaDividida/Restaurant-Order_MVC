package mx.uam.ayd.proyecto.presentacion.cerrarMesa;

import java.sql.Timestamp;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioOrden;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.presentacion.mesas.ControlMesas;

/**
 * @author Anonymux Corporation
 */

@Component
public class ControlCerrarMesa {

	@Autowired
	private VentanaCerrarMesa ventanaCerrarMesa;
	
	@Autowired
	private ServicioOrden servicioOrden;
	
	@Autowired
	private ControlMesas controlMesas;
	

	/**
	 * Muestra la ventanaCerrarMesa e inicia sus atributos de clase con los parámetros dados.
	 * @param numeroMesa Número de la mese que se quiere cerrar.
	 */
	public void inicia(int numeroMesa) {
		ventanaCerrarMesa.muestra(this, numeroMesa, getUltimaHoraApertura(numeroMesa));	
	}

	/**
	 * Devuelve una lista con los pedidos cuya fecha de apertura sea la más reciente en la persistencia.
	 * @param numMesa Número de la mesa sobre la cual se quiere extraer la lista de pedidos.
	 * @return pedidos Contiene todos los pedidos asociados a la fecha de apertura más reciente de la mesa dada.
	 */
	public LinkedList<String> ultimosPedidos(int numMesa) {
		LinkedList<Orden> ordenes = servicioOrden.ultimasOrdenes(numMesa);
		LinkedList<String> pedidos = servicioOrden.getTodosLosPedidos(ordenes);
		return pedidos;
	}

	/**
	 * Devuelve un arreglo con todos los costos que corresponden a las órdenes de la mesa dada, con la fecha de apertura más reciente.
	 * @param numMesa El número de la mesa sobre la cual se quiere extraer el arreglo de costos unitarios.
	 * @return ultimos Arreglo que contiene todos los costos unitarios de las órdenes cuya fecha de apertura sea la más reciente y el número de mesa sea el establecido.
	 */
	public double[] ultimosCostos(int numMesa) {
		double[] ultimos = servicioOrden.getTodosLosCostos(servicioOrden.ultimasOrdenes(numMesa));
		return ultimos;
	}

	/**
	 * Obtiene la última fecha de apertura que encuentra en el repositorio de órdenes y la devuelve.
	 * @param numMesa El número de mesa sobre la que se realiza la operación.
	 * @return ultima La última fecha de apertura encontrada que se asocia al número de mesa dado.
	 */
	public Timestamp getUltimaHoraApertura(int numMesa) {
		LinkedList<Orden> aux = servicioOrden.ultimasOrdenes(numMesa);
		Timestamp ultima = aux.get(0).getHoraApertura();
		return ultima;
	}

	/**
	 * Cambia el estado de las órdenes asociadas a la mesa con la hora de apertura dada al 3 (PAGADO). 
	 * @param numeroMesa El número de la mesa sobre la cual se aplica la operación.
	 * @param hrApertura La hora de apertura de la mesa seleccionada.
	 * @param estado El estado al que se quiere fijar la mesa.
	 */
	public void cambiaEstado3Mesa(int numeroMesa, Timestamp hrApertura, int estado) {
		servicioOrden.cambiaEstado3Mesa(numeroMesa, hrApertura, estado);
		
	}
	
	/**
	 * Fija el color de fondo de la mesa dada a "VERDE".
	 * @param numeroMesa El número de la mesa sobre la cual se apica la operación.
	 */
	public void actualizaStatusMesaVerde(int numeroMesa) {
		controlMesas.actualizaStatusMesaVerde(numeroMesa);
		
	}

	public void cerrarEnabled() {
		ventanaCerrarMesa.cerrarEnabled();
	}

	public void cerrarDisabled() {
		ventanaCerrarMesa.cerrarDisabled();
	}

}
