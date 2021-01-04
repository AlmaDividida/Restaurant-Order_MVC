package mx.uam.ayd.proyecto.presentacion.agregarProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import mx.uam.ayd.proyecto.presentacion.inventario.ControlInventario;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.presentacion.inventarioCocina.ControlInventarioCocina;
/**
 *  
 * @author Anonymux Corporation
 *
 */
@Component
public class ControlAgregarProducto {
	
	@Autowired
	private ControlInventarioCocina controlInventario;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private VentanaAgregarProducto ventanAgregarProducto;
	/**
	 * Metodo que muestra la venta agregar producto
	 * ademas de que se encarga de mostrar la cantidad total de productos
	 */
	public void inicia() {
		int cantidad = servicioProducto.cantidadTotalInventario();
		ventanAgregarProducto.muestra(this, servicioProducto.recuperaProductos(), cantidad);
	}
	/**
	 * Metodo que permite agregar un nuevo producto
	 * pasandole como parametros los siguientes datos 
	 * ademas de que corrobora que no se pasen campos vacios
	 * @param fecha Fecha en la que se creo el producto
	 * @param nombreProducto Nombre del producto a agregar
	 * @param descripcion Descripcion que tenga el producto a agregar
	 * @param cantidad Cantidad de productos a agregar, debe de ser tipo int
	 * @param minimo Minimo para saber cuando hay que hacer pedidos sobre este producto, es tipo int
	 */
	public void agregarProducto(String fecha, String nombreProducto, String descripcion,int cantidad, int minimo) {
		try {
			servicioProducto.crea(fecha, nombreProducto, descripcion, cantidad, minimo);
			ventanAgregarProducto.muestraDialogoConMensaje("Agregado Exitosamente");
			actualizaVentanaInventario();
		}catch(Exception ex) {
			ventanAgregarProducto.muestraDialogoConMensaje("Error al agregar producto: " + ex.getMessage());
		}	
		termina();
	}
	/**
	 * metodo que actualiza la ventana inventario
	 * para que muestre que producto se acaba de agregar
	 */
	public void actualizaVentanaInventario() {
		controlInventario.actualizaVentanaInventario();
	}
	/**
	 * metodo que permite que se cierre la ventana actual
	 * sin cerrar todo el programa
	 */
	public void termina() {
		ventanAgregarProducto.setVisible(false);
	}
	/**
	 * muestra la ventana de agregar producto con la cantidad actualizada
	 */
	public void actualizaVentana() {
		ventanAgregarProducto.actualizado(this);
	}
}
