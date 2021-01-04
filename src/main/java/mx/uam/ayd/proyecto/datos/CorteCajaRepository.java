package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.CorteCaja;

/**
 * Repositorio para Orden
 * 
 * @author Anonimux Corporation
 *
 */

public interface CorteCajaRepository extends CrudRepository <CorteCaja, Long> {
	
	public long findById(long idCorteCaja);
	@SuppressWarnings("unchecked")
	public CorteCaja save(CorteCaja corteCaja);
}


