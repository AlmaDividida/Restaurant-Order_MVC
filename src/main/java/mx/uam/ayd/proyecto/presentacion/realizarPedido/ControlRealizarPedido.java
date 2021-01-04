package mx.uam.ayd.proyecto.presentacion.realizarPedido;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.informeInventario.VentanaInformeInventario;

/**
 * Controlador de la HU-44 Realizar un Pedido
 * 
 * @author Anonymux Corporation
 * 
 */
@Component
public class ControlRealizarPedido {
	
	/**
	 * Inyección de dependencias de la clase {@link ServicioProducto} con sus Getters and Setters
	 * proporcionados por {@link Autowired}
	 */
	@Autowired
	private ServicioProducto servicioProducto;
	
	/**
	 * Inyección de dependencias de la clase {@link VentanaRealizarPedido} con sus Getters and Setters
	 * proporcionados por {@link Autowired}
	 */
	@Autowired
	private VentanaRealizarPedido ventana;
	
	/**
	 * Comunica con {@link mx.uam.ayd.proyecto.negocio.ServicioProducto} obtener todos los Productos
	 * Inicia el funcionamiento de la {@link VentanaRealizarPedido}
	 */
	public void inicia() {
		// Llama a la capa de negocios para recuperar todos los productos
		List <Producto> productos = servicioProducto.recuperaProductos();
		ventana.muestra(this, productos);		
	}
	
	/**
	 * Comunica con {@link mx.uam.ayd.proyecto.negocio.ServicioProducto} para obtener aumentar la cantidad de los Productos
	 * 
	 * @param producto
	 * @param cantidad
	 * @return un {@literal true} si se pudo aumentar la cantidad, o un {@literal false} en caso contrario
	 */
	public Boolean aumentarCantidad(String producto, Integer cantidad) {
		try {
			// Llama a la capa de negocios para aumentar la cantidad del producto
			return servicioProducto.aumentarCantidad(producto, cantidad);
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Cambia la propiedad de Visibilidad {@link VentanaInformeInventario#setVisible(boolean)} de la Ventana para cerrarla
	 */
	public void cerrar() {
		ventana.setVisible(false);
	}
}
