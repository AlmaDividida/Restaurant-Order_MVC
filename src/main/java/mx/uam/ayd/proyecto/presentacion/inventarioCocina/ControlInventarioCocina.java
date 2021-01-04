package mx.uam.ayd.proyecto.presentacion.inventarioCocina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import mx.uam.ayd.proyecto.presentacion.inventario.VentanaAgregarProducto;
//import mx.uam.ayd.proyecto.presentacion.inventario.ControlAgregarProducto;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProducto;
import mx.uam.ayd.proyecto.presentacion.modificarProducto.ControlModificarProducto;

/**
 * 
 * @author Anonymux Corporation
 *
 */
@Component
public class ControlInventarioCocina {
	
	 @Autowired
	 private ServicioProducto servicioProducto;
	 @Autowired
	 private VentanaInventarioCocina ventanaInventario;
	 @Autowired
	 private ControlAgregarProducto controlAgregarProducto;
	 @Autowired
	 private ControlModificarProducto controlModificarProducto;
	 /**
	  * muestra la ventana mandandole los productos en la BD
	  */
	 public void inicia() {
		List <Producto> productos = servicioProducto.recuperaProductos();
		ventanaInventario.muestra(this, productos);
	}
	/**
	 * Hace que se muestre la ventana para agregar productos
	 */
	public void agregar() {
		controlAgregarProducto.inicia();
	}	
	/**
	 * Hace que se muestre la ventana para agregar productos
	 */
	public void agregarProducto() {
		controlAgregarProducto.inicia();
	}
	/**
	 * se manda a llamar al metodo muestra del controlModificarProducto
	 */
	public void modificarProducto() {
		controlModificarProducto.muestra();
	}
	/**
	 * cierra la ventana inventario
	 */
	public void termina() {
		ventanaInventario.setVisible(false);		
	}
	/**
	 * Actualiza la ventana inventario mandando los nuevos productos agregados
	 */
	public void actualizaVentanaInventario() {
		List <Producto> productos = servicioProducto.recuperaProductos();
		ventanaInventario.actualizaProductos(this, productos);
	}	
}
