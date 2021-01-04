package mx.uam.ayd.proyecto.presentacion.modificarProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
/**
 * 
 * @author Anonymux Corporation
 *
 */
@Component
public class ControlModificarProducto {
	
	@Autowired
	private VentanaModificarProducto ventanaModificarProducto;
	
	@Autowired
	private ServicioProducto servicioProducto;
	/**
	 * inicia la ventana ModificarProductos
	 */
	public void muestra() {
		List<Producto> productos = servicioProducto.recuperaProductos();
		ventanaModificarProducto.muestra(this, productos);
		
	}
	/**
	 * Metodo que permite modificar un producto mostrando el id del producto
	 * @param nombre Nombre nuevo del producto
	 * @param idProducto ID que distingue al producto a modificar
	 * @param descripcion Descripcion nueva del producto
	 * @param minimo Minimo que debe tener el producto, es tipo int
	 */
	public void modificarProducto(String nombre, int idProducto, String descripcion, int minimo) {
		try {
			servicioProducto.modificarProducto(nombre, idProducto, descripcion, minimo);
			ventanaModificarProducto.muestraDialogoConMensaje("Producto modificado exitosamente");
		}catch(Exception ex){
			ventanaModificarProducto.muestraDialogoConMensaje("Error al modificar producto: " + ex.getMessage());
		}
		termina();
	}
	/**
	 * Metodo que cierra la ventana Modificar Producto
	 */
	public void termina() {
		ventanaModificarProducto.setVisible(false);
	}
	
}
