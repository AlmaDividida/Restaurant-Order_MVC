package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.OrdenRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
@ExtendWith(MockitoExtension.class)
class ServicioOrdenTest {

	@Mock
	private OrdenRepository ordenRepository;
	
	@InjectMocks
	private ServicioOrden servicio = new ServicioOrden();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetTodosLosPedidos() {
		//Prueba 1: Regresa una lista vacía si no se pasa ninguna Orden:
		LinkedList<Orden> o = new LinkedList<Orden>();
		LinkedList<String> p = servicio.getTodosLosPedidos(o);
		
		assertTrue(p.isEmpty());
		
		//Prueba 2: Regresa una lista con n String si se pasan n Ordenes:
		o = new LinkedList<Orden>();
		o.add(new Orden());
		o.add(new Orden());
		p = servicio.getTodosLosPedidos(o);
		
		assertEquals(2, p.size());
	
		/*Prueba 3: El String en la posición i-ésima de la lista p 
		 * es igual al "platillo" (atributo de <Orden>) en la posición i de la lista o de <Orden>
		 * */
		o = new LinkedList<Orden>();
		p = new LinkedList<String>();
		Orden orden1 = new Orden();
		orden1.setPlatillo("platillo1");
		o.add(orden1);
		Orden orden2 = new Orden();
		orden2.setPlatillo("platillo2");
		o.add(orden2);
		p = servicio.getTodosLosPedidos(o);
		assertTrue(o.get(1).getPlatillo().equals(p.get(1)));
		
	}
	

	@Test
	void testGetTodosLosCostos() {
		//Prueba 1: Regresa un arreglo vacío si no se pasa ninguna Orden en la Lista
		LinkedList<Orden> o = new LinkedList<Orden>();
		double[] costos = servicio.getTodosLosCostos(o);
	
		assertEquals(0, costos.length);
		
		//Prueba 2: Regresa un arreglo con n elementos si se pasan n Ordenes:
		o = new LinkedList<Orden>();
		o.add(new Orden());
		o.add(new Orden());
		costos = servicio.getTodosLosCostos(o);
		
		assertEquals(2, costos.length);
		/*Prueba 3: El "costoUnitario" (atributo de <Orden>) en la posición i-ésima de la lista o de <Orden>
		 * es igual al elemento en la posición i del arreglo de double
		 * */
		o = new LinkedList<Orden>();
		Orden orden1 = new Orden();
		orden1.setCostoUnitario(10);
		o.add(orden1);
		Orden orden2 = new Orden();
		orden2.setCostoUnitario(20);
		o.add(orden2);
		costos = servicio.getTodosLosCostos(o);
		boolean flag = false;
		if(o.get(1).getCostoUnitario() == costos[1]){
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	void testFechaMasReciente() {
		//Prueba 1: Regresa null si no se pasa ningun Timestamp en la Lista
		LinkedList<Timestamp> listaFechas = new LinkedList<Timestamp>();
		assertEquals(null, servicio.fechaMasReciente(listaFechas));
		
		//Prueba 2: Regresa una fecha si se pasa una lista de Timestamp con al menos un elemento
		listaFechas = new LinkedList<Timestamp>();
		listaFechas.add(new Timestamp(0));

		assertEquals(Timestamp.class, servicio.fechaMasReciente(listaFechas).getClass());
		
		/*Prueba 3: La fecha de retorno es la más reciente 
		 * de la lista que se pasa como parámetro
		*/
		listaFechas = new LinkedList<Timestamp>();
		Timestamp fecha = new Timestamp(0);
		Timestamp fecha1 = new Timestamp(1);
		Timestamp fecha2 = new Timestamp(2);
		listaFechas.add(fecha);
		listaFechas.add(fecha1);
		listaFechas.add(fecha2);
		//System.out.println("fecha = "+fecha);
		//System.out.println("fecha1 = "+fecha1);
		//System.out.println("fecha2 = "+fecha2);
		assertTrue(servicio.fechaMasReciente(listaFechas).equals(fecha2));
		
	}

}
