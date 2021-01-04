package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Pruebas Unitarias del servicio Producto
 * 
 * @author Anonymux Corporation
 *
 */
@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {

	/**
	 * Se crea un Mock instancia del {@link ProductoRepository} para realizar las pruebas unitarias
	 * con los métodos findAll(), save(), findByNombreProducto
	 * 
	 */
	@Mock
	private ProductoRepository productoRepository;
	
	/**
	 * Inyeccion de dependencias de los Mocks hacía el servicio {@link ServicioProducto}
	 * 
	 */
	@InjectMocks
	private ServicioProducto servicio;
	
	/**
	 * Objeto de tipo Producto con escasez que se utilizará como modelo para 
	 * realizar las pruebas unitarias
	 */
	private Producto producto1 = new Producto();
	
	/**
	 * Objeto de tipo Producto sin escasez que se utilizará como modelo para
	 * realizar las pruebas unitarias
	 */
	private Producto producto2 = new Producto();
	
	/**
	 * Precondiciones: Existen dos productos en la BD
	 * El producto1 esta inicialzado con Escasez
	 * El producto2 esta inicialzado sin Escasez
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		// Inicializacion del Producto CON Escasez
		producto1.setFecha("2020-05-10");
		producto1.setNombreProducto("Chiles Verdes");
		producto1.setDescripcion("Bolsas de 1 kg de Chiles");
		producto1.setCantidad(15);
		producto1.setMinimo(20);
		
		// Inicializacion del Producto SIN Escasez
		producto2.setFecha("2020-10-05");
		producto2.setNombreProducto("Papas");
		producto2.setDescripcion("Costal de 32 Kg de papas");
		producto2.setCantidad(58);
		producto2.setMinimo(10);
		
	}

	/**
	 * Reseteo de las Precondiciones antes de la prueba
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Prueba Unitaria para el método {@link mx.uam.ayd.proyecto.negocio.ServicioProducto#recuperaProductos()}
	 * Para el caso de que exista uno o más productos regresa una {@link List} de Productos
	 * 
	 */
	@Test
	void testRecuperaProductos() {
		// Prueba 1: Corroborar que regresa una lista vacía si no hay usuarios en la BD
		List <Producto> productos = servicio.recuperaProductos();
		assertTrue(productos.isEmpty());

		// Prueba 2: Corroborar que regresa una lista con usuarios cuando hay usuarios en la BD
		LinkedList <Producto> lista = new LinkedList <> ();
		lista.add(producto1);
		lista.add(producto2);
		when(productoRepository.findAll()).thenReturn(lista);
		
		productoRepository.save(producto1);
		productoRepository.save(producto2);
		
		productos = servicio.recuperaProductos();
		
		assertEquals(2, productos.size());
	}
	
	/**
	 * Prueba Unitaria para el método {@link mx.uam.ayd.proyecto.negocio.ServicioProducto#recuperaProductosEscacez()}
	 * Para el caso de que exista uno o más productos con ESCASEZ regresa una {@link List} de Productos
	 * 
	 */
	@Test
	void testRecuperaProductosEscasez() {
		// Prueba 1: Corroborar que regresa una lista vacía si no hay usuarios en la BD
		List <Producto> productos = servicio.recuperaProductos();
		assertTrue(productos.isEmpty());

		// Prueba 2: Corroborar que regresa una lista con usuarios cuando hay usuarios en la BD
		LinkedList <Producto> lista = new LinkedList <> ();
		lista.add(producto1);
		lista.add(producto2);
		when(productoRepository.findAll()).thenReturn(lista);
		
		productoRepository.save(producto1);
		productoRepository.save(producto2);
		
		productos = servicio.recuperaProductosEscacez();
		
		assertEquals(1, productos.size());
	}

	/**
	 * Prueba Unitaria para el método {@link mx.uam.ayd.proyecto.negocio.ServicioProducto#aumentarCantidad(String, Integer)}
	 * Para el caso de que exista escasez regresa {@literal true}
	 * Para el caso contrario regresa {@literal false}
	 * 
	 */
	@Test
	void testAumentarCantidad() {
		// Prueba 1: Corroborar que regresa false si no fue posible aumentar la Cantidad (cantidad Minima)
		when(productoRepository.save(producto1)).thenReturn(producto1);
		when(productoRepository.findBynombreProducto(producto1.getNombreProducto())).thenReturn(producto1);
		assertFalse(servicio.aumentarCantidad(producto1.getNombreProducto(), 2));

		// Prueba 2: Corroborar que regresa true si la cantidad es Valida
		assertTrue(servicio.aumentarCantidad(producto1.getNombreProducto(), 200));
		
		// Prueba 3: Corroborar que regresa false si el usuario es Nulo
		assertFalse(servicio.aumentarCantidad(null, 200));
		
		// Prueba 4: Corroborar que regresa false si la cantidad es Nula
		assertFalse(servicio.aumentarCantidad(producto1.getNombreProducto(), null));
	}
	
	/**
	 * Prueba Unitaria para el método {@link mx.uam.ayd.proyecto.negocio.ServicioProducto#validaInventario(Producto, Integer)}
	 * Para el caso de que la cantidad sea Valida (cumple con el mínimo y máximo) regresa {@literal Integer}
	 * Para el caso contrario regresa {@literal null}
	 * 
	 */
	@Test
	void testValidaInventario() {
		// Prueba 1: Corroborar que regresa la cantidadNueva si la cantidad dada es Valida (cumple mínimo y máximo)
		Integer actual = servicio.validaInventario(producto1, 100);
		Integer esperado = 115;
		
		assertEquals(esperado, actual);
		
		// Prueba 2: Corroborar que regresa null si la cantidad excede el límite del inventario
		actual = servicio.validaInventario(producto1, 1000);
		
		assertNull(actual);
		
		// Prueba 3: Corroborar que regresa null si la cantidad no cumple con el mínimo establecido
		actual = servicio.validaInventario(producto1, 1);
		
		assertNull(actual);
		
		// Prueba 4: Corroborar que regresa null si la cantidad es null
		actual = servicio.validaInventario(producto1, null);
		
		assertNull(actual);

		// Prueba 5: Corroborar que regresa null si el producto es null
		actual = servicio.validaInventario(null, 100);
		
		assertNull(actual);
	}
	
	/**
	 * Prueba del metodo crea
	 * crea un producto y lo regresa si se almaceno correctamen
	 */
	@Test
	void testCrea() {
		//Prueba 1: corroborar que se crea un nuevo producto para ser guardado en la BD
		//servicioProducto.crea(producto.getFecha(), producto.getNombreProducto(), producto.getDescripcion(), producto.getCantidad(), producto.getMinimo())
		List<Producto> productos = servicio.recuperaProductos();
		Producto p1;
		p1 = servicio.crea("11/11/2020", "Papas", "Costal 10 kg", 10, 3);
		productos.add(p1);
		
		when(productoRepository.findAll()).thenReturn(productos);
		productos = servicio.recuperaProductos();
		assertEquals(1, productos.size());
	}
	
	/**
	 * Prueba del metodo cantidadTotalInventario
	 * Regresa la cantidad total de los productos que se agregaron en la BD
	 */
	@Test
	void testCantidadTotalInventario() {
		//Prueba 1: Regresa 0 si no hay productos en la BD
		List <Producto> productos = servicio.recuperaProductos();
		assertTrue(servicio.cantidadTotalInventario()==0);
		
		//Prueba 2: Regresa la cantidad total si hay productos en la BD
		LinkedList <Producto> lista = new LinkedList<>();

		lista.add(producto1);
		lista.add(producto2);
		
		when(productoRepository.findAll()).thenReturn(lista);
		productos = servicio.recuperaProductos();	
		assertEquals(73 ,servicio.cantidadTotalInventario());
	}
	
}
