package mx.uam.ayd.proyecto.negocio;

import java.util.LinkedList;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.OrdenRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;


@Service
public class ServicioOrden {
	
	@Autowired 
	private OrdenRepository ordenRepository;
	public List<Orden> ordenesListas = new LinkedList<Orden>();
	public List<Orden> ordenes = new LinkedList<Orden>();
	
	
	public LinkedList<Orden> creaOrdenes(List<String> pedidos,int numMesa, Timestamp hrApertura) {
		
		double costoUnitario=-1;
		String platillo;
		LinkedList<Orden> ordenes = new LinkedList<Orden>();
		for(int i=0;  i<pedidos.size(); i++) {
			platillo = pedidos.get(i);
			if(platillo.equals("Sopa"))
		        costoUnitario = 30;
			if(platillo.equals("Consome"))
		        costoUnitario = 30;
			if(platillo.equals("Arroz"))
		        costoUnitario = 20;
			if(platillo.equals("Pasta"))
		        costoUnitario = 20;
			if(platillo.equals("Taco Azteca"))
		        costoUnitario = 60;
			if(platillo.equals("Filete empanizado"))
		        costoUnitario = 65;
			if(platillo.equals("Chile relleno"))
		        costoUnitario = 50;
			if(platillo.equals("Carne de Res"))
		        costoUnitario = 50;
			if(platillo.equals("Enchiladas"))
		        costoUnitario = 50;
			if(platillo.equals("Gelatina"))
		        costoUnitario = 10.5;
			if(platillo.equals("Flan"))
		        costoUnitario = 10.5;
			Orden aux = new Orden(numMesa,pedidos.get(i),hrApertura,costoUnitario, 0);
			ordenRepository.save(aux);	
			ordenes.add(aux);
		}
		return ordenes;
	}
	
	public boolean actualizaOrdenes() {
		this.ordenes = ordenRepository.findAll();
	   	 return true;
	}

	
	/* Función que recibe un numero de mesa y regresa una lista con todas las ordenes 
	 * que correspondan a ese número de mesa y cuya hora de apertura sea la más reciente
	 */
	public LinkedList<Orden> ultimasOrdenes(int numMesa){
		LinkedList<Orden> ultimasOrden = new LinkedList<Orden>();
		LinkedList<Timestamp> ultimasFecha = getTodasLasFechasApertura(ordenRepository.findAll(), numMesa);
		Timestamp fechaMasReciente = fechaMasReciente(ultimasFecha);

		for(Orden o: ordenRepository.findAll()) {
			if(o.getNumeroMesa()==numMesa && o.getHoraApertura().equals(fechaMasReciente)) {
				ultimasOrden.add(o);
			}
		}	
		return ultimasOrden;
	}
	
	/*Funcion que recibe una lista de ordenes, extrae todos los pedidos y 
	 * los devuelve en una lista de String
	 */
	public LinkedList<String> getTodosLosPedidos(List<Orden> o){
		LinkedList<String> pedidos = new LinkedList<String>();
		for(Orden orden: o) {
			pedidos.add(orden.getPlatillo());
		}
		return pedidos;
	}
	
	/*Funcion que recibe una lista de ordenes, extrae todos los costos y 
	 * los devuelve en un array de double
	 */
	public double[] getTodosLosCostos(List<Orden> o){
		double[] costos = new double[o.size()];
		for(int i=0; i<o.size();i++) {
			costos[i] = o.get(i).getCostoUnitario();
		}
		return costos;
	}
	
	/*Recibe una lista de ordenes y un numero de mesa; regresa
	 * una lista con todas las fechas de apertura de esa mesa
	 */
	public LinkedList<Timestamp> getTodasLasFechasApertura(List<Orden> o, int numMesa) {
		LinkedList<Timestamp> fechas = new LinkedList<Timestamp>();
		for(Orden orden: o) {
			if(orden.getNumeroMesa()==numMesa) {
				fechas.add(orden.getHoraApertura());
			}
		}

		return fechas;
	}
	
	/*Recibe una lista de Timestamp (horas de apertura) y regresa la hora de apertura
	 * mas reciente de la lista
	 */
	@SuppressWarnings("deprecation")
	public Timestamp fechaMasReciente(List<Timestamp> t) {
		java.util.Date utilDate = new java.util.Date();
		long l = utilDate.getTime();
		Timestamp masReciente = new Timestamp(l);
		masReciente.setYear(1);
		for(Timestamp fecha: t) {
			if(fecha.after(masReciente)) {
				masReciente = fecha;
			}
		}
		return masReciente;
	}

	/*
	 * 
	 */
	
	public void cambiaEstado3Mesa(int numeroMesa, Timestamp hr, int estado) {
		LinkedList<Orden> lista = ordenRepository.findByHoraApertura(hr);
		java.util.Date utilDate = new java.util.Date();
		long l = utilDate.getTime();
		Timestamp hrCierre = new Timestamp(l);
		for(Orden o: lista) {
			if (o.getNumeroMesa()==numeroMesa) {
				o.setEstado(estado);	
				o.setHoraCierre(hrCierre);
				ordenRepository.save(o);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public LinkedList<Orden> datosTablaCorteCaja(){
		LinkedList<Orden> lista = new LinkedList<Orden>();
		java.util.Date utilDate = new java.util.Date();
		utilDate.setHours(0);
		utilDate.setMinutes(0);
		utilDate.setSeconds(0);
		for(Orden o: ordenRepository.findByEstado(3)) {
			if(o.getHoraCierre().after(utilDate)) {
				lista.add(o);
			}
		}
		return lista;
	}

	public LinkedList<Orden> recuperarOrdenesPorEstado(int estado) {
		return ordenRepository.findByEstado(estado);
	}

	public boolean actualizarEstadoOrden(Orden orden, int estado) {
		Orden aux = ordenRepository.findByidOrden(orden.getIdOrden());
		if(aux.getIdOrden()>=0) {
			aux.setEstado(estado);
			ordenRepository.save(aux);
			return true;
		}	
		return false;
	}
	
}
