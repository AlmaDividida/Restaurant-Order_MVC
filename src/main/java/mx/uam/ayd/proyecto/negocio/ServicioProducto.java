package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Interfaz de las operaciones del ServicioProducto
 *
 * @author Anonymux Corporation
 */
@Service
public class ServicioProducto {

	/**
	 * Inyecci�n de dependencias {@link ProductoRepository} con sus Getters and Setters
	 * proporcionados por {@link Autowired}
	 */
	@Autowired
	private ProductoRepository productoRepository;

	/**
	 * Regresa una lista de  objetos de tipo Producto con todos los productos existentes en el DAO.
	 * En caso de no existir productos regresa  null.
	 * 
	 * @return una {@literal List} de Productos. Si no hay productos regresa {@literal null}
	 */
	public List<Producto> recuperaProductos() {

		List<Producto> productos = new ArrayList<>();
		for (Producto producto : productoRepository.findAll()) {
			productos.add(producto);
		}

		return productos;
	}

	/**
	 * Regresa una lista de  objetos de tipo Producto con los productos en el DAO cuya escasez sea true.
	 * En caso de no existir productos regresa una lista vac�a.
	 * 
	 * @see mx.uam.ayd.proyecto.negocio.modelo.Producto#isEscacez()
	 * @return una {@literal List} de Productos. Si no hay productos regresa {@literal List} vac�a
	 */
	public List<Producto> recuperaProductosEscacez() {

		List<Producto> productos = new ArrayList<>();
		for (Producto producto : productoRepository.findAll()) {
			if (producto.isEscacez()) {
				productos.add(producto);
			}
		}

		return productos;
	}

	/**
	 * Aumenta el atributo cantidad del producto especificado en la cantidad pasada por par�metro SI hay espacio
	 * en el inventario, v�ase m�todo {@link #validaInventario(Producto, Integer)}, y regresa true
	 * En caso de no existir el producto especificado regresa false.
	 * 
	 * @param nombreProducto un {@literal String} que determina el nombre del producto en el DAO
	 * @param cantidad un {@literal Integer} que se da por el usuario
	 * @return {@literal true} si se aument� la cantidad o {@literal false} si no existe el producto
	 */
	public Boolean aumentarCantidad(String nombreProducto, Integer cantidad) {
		
		Producto producto = productoRepository.findBynombreProducto(nombreProducto);
		try {
			int cantidadNueva = validaInventario(producto, cantidad);
			producto.setCantidad(cantidadNueva);
			producto.setFecha(java.time.LocalDate.now().toString());
			if ( productoRepository.save(producto) != null ) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
		
	}
	
	/**
	 * Valida que la cantidad pasada por par�metro no exceda el l�mite de la capacidad del inventario, 
	 * y no sea menor que el m�nimo del producto (RN-13), si se cumple la regresa la cantidadNueva.
	 * 
	 * @param producto un objeto de tipo {@link Producto} que determina el producto a validar
	 * @param cantidad una dato de tipo {@link Integer} que determina la cantidad a validar
	 * @return {@literal Integer} si cumple la validaci�n o {@literal null} si no es valida
	 */
	public Integer validaInventario(Producto producto, Integer cantidad) {
		// Limite: Capacidad del inventario (1000) especificado en la RN-13
		int maximoInventario = 1000;
		try {
			int cantidadNueva = producto.getCantidad() + cantidad;
			// Limite: Debe abastecer el m�nimo del inventario especificado en la RN-13
			if (cantidadNueva <= maximoInventario && cantidadNueva >= producto.getMinimo()) {
				return cantidadNueva;
			}
			else {
				return null;
			}
		} catch (NullPointerException e) {
			return null;
		}
		
	}
	
	/**
	 * Metodo que crea un producto para ser guardado en la BD
	 * @param fecha Fecha en la que se creo el producto
	 * @param nombreProducto Nombre que se la va a asignar al producto
	 * @param descripcion Descripcion acerca del producto a agregar
	 * @param cantidad Cantidad a agregar del producto
	 * @param minimo Minimo que establece cuando se tiene que hacer un pedido de este producto
	 * @return producto El producto que se creo
	 */
	public Producto crea(String fecha, String nombreProducto, String descripcion,int cantidad, int minimo){
		
		Producto producto = productoRepository.findBynombreProducto(nombreProducto);
		/*if(producto!=null) {
			throw new IllegalArgumentException("Este producto ya existe");
		}*/
		producto = new Producto(fecha, nombreProducto, descripcion, cantidad, minimo);
		productoRepository.save(producto);
		
		return producto;
	}
	
	/**
	 * Modifica los siguientes atributos de una entidad existente de productos
	 * @param nombre Nuevo nombre que se le piensa dar al producto
	 * @param idProducto ID para identificar que productos hay y poder escoger uno
	 * @param descripcion Descricion nueva del producto
	 * @param minimo Minimo que indicara que un producto debe de ser pedido si su cantidad es menor que el minimo
	 */
	public void modificarProducto(String nombre, int idProducto, String descripcion, int minimo) {
		Producto product = productoRepository.findByidProducto(idProducto);
		
		if(product == null) {
			throw new IllegalArgumentException("Este producto no existe");
		}
		
		product.setNombreProducto(nombre);
		product.setDescripcion(descripcion);
		product.setMinimo(minimo);
		
		productoRepository.save(product);
	}
	/**
	 * Realiza la suma de la cantidad de los productos existentes en la BD
	 * @return la cantidad total de productos que hay en el inventario
	 */
	public int cantidadTotalInventario() {
		List <Producto> productos = new ArrayList<>();
		int cantidad=0;
		for(Producto producto:productoRepository.findAll()) {
			productos.add(producto);
			cantidad = cantidad + producto.getCantidad();
		}
		return cantidad;
	}

}