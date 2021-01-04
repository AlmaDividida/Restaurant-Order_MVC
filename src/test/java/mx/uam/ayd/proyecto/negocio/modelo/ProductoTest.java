/**
 * 
 */
package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Pruebas Unitarias del modelo Producto
 * 
 * @author Anonymux Corporation
 *
 */

public class ProductoTest {
	
	/**
	 * Objeto de tipo Producto con escasez que se utilizará como modelo para 
	 * realizar las pruebas unitarias
	 */
	private static Producto producto1 = new Producto();
	/**
	 * Objeto de tipo Producto sin escasez que se utilizará como modelo para
	 * realizar las pruebas unitarias
	 */
	private static Producto producto2 = new Producto();
	

	/**
	 * Precondiciones: Existen dos productos en la BD
	 * El producto1 esta inicialzado con Escasez
	 * El producto2 esta inicialzado sin Escasez
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Producto CON Escasez
		producto1.setFecha("2020-05-10");
		producto1.setNombreProducto("Chiles Verdes");
		producto1.setDescripcion("Bolsas de 1 kg de Chiles");
		producto1.setCantidad(15);
		producto1.setMinimo(20);
		
		// Producto SIN Escasez
		producto2.setFecha("2020-10-05");
		producto2.setNombreProducto("Papas");
		producto2.setDescripcion("Costal de 32 Kg de papas");
		producto2.setCantidad(58);
		producto2.setMinimo(10);
	}

	/**
	 * Reseteo de las Precondiciones antes de la prueba
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Prueba Unitaria para el método {@link mx.uam.ayd.proyecto.negocio.modelo.Producto#isEscacez()}
	 * Para el caso de que exista escasez regresa {@literal true}
	 * Para el caso contrario regresa {@literal false}
	 * 
	 */
	@Test
	public final void testIsEscacez() {
		// Caso 1: Corroborar que regresa true cuando existe escasez en un producto
		assertTrue(producto1.isEscacez());
		
		// Caso 2: Corroborar que regresa false cuando No existe escasez en un producto
		assertFalse(producto2.isEscacez());
	}

}
