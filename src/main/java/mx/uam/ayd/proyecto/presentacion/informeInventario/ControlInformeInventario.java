package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.realizarPedido.ControlRealizarPedido;

/**
 * Controlador de la HU-45 Informe Inventario
 * 
 * @author Anonymux Corporation
 *
 */
@Component
public class ControlInformeInventario {
	
	/**
	 * Inyección de dependencias de la clase {@link ServicioProducto} con sus Getters and Setters
	 * proporcionados por {@link Autowired}
	 */
	@Autowired
	private ServicioProducto servicioProducto;
	
	/**
	 * Inyección de dependencias de la clase {@link ControlInformeInventario} con sus Getters and Setters
	 * proporcionados por {@link Autowired}
	 */
	@Autowired
	private ControlRealizarPedido controlRealizarPedido;
	
	/**
	 * Inyección de dependencias de la clase {@link VentanaInformeInventario} con sus Getters and Setters
	 * proporcionados por {@link Autowired}
	 */
	@Autowired
	private VentanaInformeInventario ventana;
	
	/**
	 * Recupera los productos de la capa de negocios en una Lista
	 * Inicia el funcionamiento de la {@link VentanaInformeInventario}
	 */
	public void inicia() {
		// Llama a la capa de negocios para recuperar los productos con Escasez
		List <Producto> productos = servicioProducto.recuperaProductosEscacez();
		ventana.muestra(this, productos);
	}

	/**
	 * Cambia la propiedad de Visibilidad {@link VentanaInformeInventario#setVisible(boolean)} de la Ventana para cerrarla
	 */
	public void cerrar() {
		ventana.setVisible(false);		
	}
	
	/**
	 * Inicia el funcionamiento de la {@link ControlRealizarPedido}
	 */
	public void iniciaRealizarPedido() {
		controlRealizarPedido.inicia();
	}

}
