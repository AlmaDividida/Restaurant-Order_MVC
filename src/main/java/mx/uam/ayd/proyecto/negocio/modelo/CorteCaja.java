package mx.uam.ayd.proyecto.negocio.modelo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Usuario
 * 
 * @author Anonymux Corporation
 *
 */
@Entity
@Data
public class CorteCaja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCorteCaja;
	private double corteSistema;
	private double corteReal;
	private Timestamp fechaCorte;
	private String Observaciones;
	
	public CorteCaja(double corteSistema, double corteReal, Timestamp fechaCorte, String observaciones) {
		super();
		this.corteSistema = corteSistema;
		this.corteReal = corteReal;
		this.fechaCorte = fechaCorte;
		Observaciones = observaciones;
	}
	
	
}
