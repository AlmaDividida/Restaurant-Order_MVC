package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Repositorio Productos
 * Extiende metodos del Repository para recuperar entidades del Repositorio
 * 
 * @author Anonymux
 *
 */
public interface ProductoRepository extends CrudRepository <Producto, Long> {
	
	/**
	 * Regresa una Lista de Productos con todos los productos existentes en la BD
	 * <p>
	 * Si alguna entidad no existe no regresa ese Producto
	 * </p>
	 * @param No debe de ser {@literal null} ni contener valores {@literal null}.
	 * @return Garantiza que no sea nulo {@literal null}. El tamaño debe ser igual al tamaño de {@link List Productos}.
	 * @throws IllegalArgumentException en caso de que {@link List Productos}
	 */
	public List <Producto> findAll();
	
	/**
	 * Regresa un objeto del tipo Producto especificado por su nombre
	 * 
	 * @param name
	 * @return una instancia de tipo {@link Producto}
	 */
	public Producto findBynombreProducto(String name);
	
	/**
	 * Regresa un objeto del tipo Producto especificado por su idProducto
	 * @param idProducto ID del producto a buscar
	 * @return regresa los productos en existencia que tienen un id 
	 */
	public Producto findByidProducto(int idProducto);
}
