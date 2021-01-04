package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Producto
 * Representa cada uno de los productos del Inventario a través de un objeto de tipo Producto
 *
 * @author Anonymux
 */
@Entity
@Data
public class Producto {
	/**
	 * Atributo idProducto de la clase Producto, identifica a cada uno de los Produtos a traves de un entero,
	 * Se genera automaticamente a través de {@link GeneratedValue}
	 * Sus valores son únicos y no nulos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;

	/**
	 * Atributo fecha, representa el valor de la fecha en que se hizo el último pedido,
	 * se representa a través de un dato tipo cadena (String) y es dado por el usuario
	 */
	private String fecha;
	
	/**
	 * Atributo nombreProducto, describe el nombre especifico de cada Producto en el inventario
	 * se representa a través de un dato tipo cadena (String) y es dado por el usuario
	 */
	private String nombreProducto;
	
	/**
	 * Atributo Descripcion, proporciona una breve descripcion del producto 
	 * se representa a través de un dato tipo cadena (String) y es dado por el usuario
	 */
	private String descripcion;
	
	/**
	 * Atributo cantidad, numero de productos existentes en el inventario
	 * se representa a través de un dato tipo entero (Integer) y es dado por el usuario
	 */
	private Integer cantidad;
	
	/**
	 * Atributo minimo, numero de minimo de productos que deben existir para este Producto
	 * se representa a través de un dato tipo entero (Integer) y es dado por el usuario
	 */
	private Integer minimo;
	
	/**
	 * Valida si es que existe una escasez en el producto de acuerdo a la regla de negocio (RN-16)
	 * regresa true existe una escasez o false en el caso contrario
	 * 
	 * @return un {@literal true} si la {@link #cantidad} es menor que {@link #minimo} 
	 */
	public Boolean isEscacez() {
		if (this.getCantidad() < this.getMinimo()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Constructo que usa todos los atributos
	 * de la clase producto
	 * @param idProducto ID del producto que se genera automaticamente
	 * @param fecha Fecha en la que se agrego el producto
	 * @param nombreProducto Nombre que se le asignara al producto
	 * @param descripcion Descripcion acerca del producto
	 * @param cantidad Cantidad a agregar del producto
	 * @param minimo Minimo que establece cuando se tiene que hacer un pedido de este producto
	 */
	public Producto(int idProducto, String fecha, String nombreProducto, String descripcion, int cantidad, int minimo) {
		super();
		this.idProducto = idProducto;
		this.fecha = fecha;
		this.nombreProducto = nombreProducto;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.minimo = minimo;
	}
	
	/**
	 * Constructor que no usa el ID del producto
	 * @param fecha Fecha en la que se agrego el producto
	 * @param nombreProducto Nombre que se le asignara al producto
	 * @param descripcion Descripcion acerca del producto
	 * @param cantidad Cantidad a agregar del producto
	 * @param minimo Minimo que establece cuando se tiene que hacer un pedido de este producto
	 */
	public Producto(String fecha, String nombreProducto, String descripcion, int cantidad, int minimo) {
		super();
		this.fecha = fecha;
		this.nombreProducto = nombreProducto;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.minimo = minimo;
	}
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}
}
