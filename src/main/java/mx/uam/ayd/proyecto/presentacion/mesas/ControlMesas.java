package mx.uam.ayd.proyecto.presentacion.mesas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioOrden;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.presentacion.cerrarMesa.ControlCerrarMesa;
import mx.uam.ayd.proyecto.presentacion.cocina.ControlCocina;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.registrarOrden.ControlRegistrarOrden;

/**
 * @author Anonymux Corporation
 *
 */
@Component
public class ControlMesas {
	
	@Autowired
	private VentanaMesas ventanaMesas;
	
	@Autowired
	private ControlRegistrarOrden controlRegistrarOrden;
	
	@Autowired
	private ControlPrincipal controlPrincipal;
	
	@Autowired
	private ControlCerrarMesa controlCerrarMesa;
	
	@Autowired
	private ServicioOrden servicioOrden;
	
	@Autowired
	private ControlCocina controlCocina;
	
	/**
	 * Lleva el flujo de control hacia la ventanaMesas.
	 * 
	 */
	public void inicia() {
		ventanaMesas.muestra(this);
	}

	/**
	 * Inicia el flujo de controlRegistrarOrden.
	 * @param numeroMesa Número de la mesa sobre la cual se realiza la operación.
	 */
	public void registraPedido(int numeroMesa) {
		controlRegistrarOrden.inicia(numeroMesa);
	}

	/**
	 * Inicia el flujo de controlCerrarMesa.
	 * @param numeroMesa Número de la mesa sobre la cual se realiza la operación.
	 */
	public void cierraMesa(int numeroMesa) {
		
		controlCerrarMesa.inicia(numeroMesa);
		
	}

	/**
	 * Inicia el flujo de controlPrincipal.
	 */
	public void regresar() {
		controlPrincipal.inicia();
		
	}
	
	/**
	 * Actualiza el color de fondo de la mesa seleccionada a "ROJO".
	 * @param numeroMesa Número de la mesa sobre la cual se realiza la operación.
	 */
	public void actualizaStatusMesaRojo(int numeroMesa) {
		ventanaMesas.actualizaStatusMesaRojo(numeroMesa);
		ventanaMesas.muestra(this);
		
	}

	/**
	 * Actualiza el color de fondo de la mesa seleccionada a "VERDE".
	 * @param numeroMesa Número de la mesa sobre la cual se realiza la operación.
	 */
	public void actualizaStatusMesaVerde(int numeroMesa) {
		ventanaMesas.actualizaStatusMesaVerde(numeroMesa);	
		ventanaMesas.muestra(this);
	}
	
	/**
	 * Actualiza el color de fondo de la mesa seleccionada a "NARANJA".
	 * @param numeroMesa Número de la mesa sobre la cual se realiza la operación.
	 */
	public void actualizaStatusMesaNaranja(int numeroMesa) {
		ventanaMesas.actualizaStatusMesaNaranja(numeroMesa);	
		ventanaMesas.muestra(this);
	}

	public void notificarOrdenLista(Orden orden) {
		ventanaMesas.notificarOrdenLista(orden);
	}

	public boolean actualizarEstadoOrden(Orden orden, int estado) {
		return(servicioOrden.actualizarEstadoOrden(orden, estado));
		
		
	}

	public boolean actualizaListaComandas() {
		return(controlCocina.actualizaListaComandas());
		
	}

	public void cerrarEnabled() {
		controlCerrarMesa.cerrarEnabled();
		
	}

	public void cerrarDisabled() {
		controlCerrarMesa.cerrarDisabled();
	}



}
