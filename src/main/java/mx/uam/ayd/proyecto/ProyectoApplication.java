package mx.uam.ayd.proyecto;

import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.OrdenRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.registrarOrden.ControlRegistrarOrden;

/**
 * @author Anonimux Corporation
 *
 */
@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlPrincipal controlPrincipal;
	
	@Autowired
	ControlRegistrarOrden controlRegistrarPedido;
	
	@Autowired
	OrdenRepository ordenRepository;

	@Autowired 
	ProductoRepository productoRepository;
	
	/**
	 * Funcion principal
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	/**
	 * Carga la BD con datos de prueba
	 */
	@PostConstruct
	public void inicia() {
		
		inicializaBD();
		controlPrincipal.inicia();
	}
	
	
	/**
	 * 
	 * Inicializa la BD con datos 
	 * 
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void inicializaBD() {
		
		/*Registros de Orden*/
		int n1=1;
		String p1="Taco Azteca", p2="Sopa";
		java.util.Date utilDate = new java.util.Date();
		java.util.Date utilDate1 = new java.util.Date();
		utilDate.setMonth(1);
		utilDate.setYear(119);
		utilDate.setMinutes(1);
		utilDate1.setMonth(1);
		utilDate1.setYear(119);
		utilDate1.setMinutes(59);
		long lnMilisegundos = utilDate.getTime();
		long lnMilisegundos1 = utilDate1.getTime()+10;
		Timestamp fecha1= new Timestamp(lnMilisegundos);
		Timestamp fecha2 = new Timestamp(lnMilisegundos+9999999);
		Timestamp fecha3= new Timestamp(lnMilisegundos1);
		Timestamp fecha4 = new Timestamp(lnMilisegundos1+999999);
		Orden orden1 = new Orden(n1,p1, fecha1, fecha2, 60, 3);
		Orden orden2 = new Orden(n1,p2, fecha3, fecha4, 30, 3);
		orden1.setEstado(3);
		orden2.setEstado(3);
		ordenRepository.save(orden1);
		ordenRepository.save(orden2);
		
		/*Registros de producto*/
		Producto producto1 = new Producto();
		producto1.setFecha("2020-05-10");
		producto1.setNombreProducto("Chiles Verdes");
		producto1.setDescripcion("Bolsas de 1 kg de Chiles");
		producto1.setCantidad(15);
		producto1.setMinimo(20);
		productoRepository.save(producto1);
		
		Producto producto2 = new Producto();
		producto2.setFecha("2020-10-05");
		producto2.setNombreProducto("Papas");
		producto2.setDescripcion("Costal de 32 Kg de papas");
		producto2.setCantidad(58);
		producto2.setMinimo(10);
		productoRepository.save(producto2);
		
		Producto producto3 = new Producto();
		producto3.setFecha("2020-10-20");
		producto3.setNombreProducto("Frijoles");
		producto3.setDescripcion("Costales de 13 kg de Frijoles");
		producto3.setCantidad(2);
		producto3.setMinimo(20);
		productoRepository.save(producto3);
		
		Producto producto4 = new Producto();
		producto4.setFecha("2020-08-16");
		producto4.setNombreProducto("Sopa de fideo");
		producto4.setDescripcion("Bolsas de 500g de sopa de fideo");
		producto4.setCantidad(8);
		producto4.setMinimo(10);
		productoRepository.save(producto4);
		
	}
}
