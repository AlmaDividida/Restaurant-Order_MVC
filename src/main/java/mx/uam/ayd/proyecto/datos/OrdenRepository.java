package mx.uam.ayd.proyecto.datos;

import java.sql.Timestamp;
import java.util.LinkedList;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;

/**
 * Repositorio para Orden
 * 
 * @author Anonimux Corporation
 *
 */

public interface OrdenRepository extends CrudRepository <Orden, Long> {
	
	public Orden findByidOrden(long idOrden);
	public Orden findByhoraApertura(Timestamp horaApertura);
	@SuppressWarnings("unchecked")
	public Orden save(Orden o);
	public LinkedList<Orden> findAll();
	public LinkedList<Orden> findByHoraApertura(Timestamp horaApertura);
	public LinkedList<Orden> findByHoraCierre(Timestamp horaCierre);
	public LinkedList<Orden> findByEstado(int estado);
	public void delete(Orden orden);
}


