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
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOrden; 
	private int numeroMesa;
	private String platillo;
	private int estado;
	private Timestamp horaApertura;
	private Timestamp horaCierre;
	private double costoUnitario;
	
	public static final int PEDIDA = 0;
	public static final int LISTA = 1;
	public static final int ENTREGADA = 2;
	public static final int PAGADA = 3;

	public Orden(int numeroMesa, String platillo, Timestamp  hrApertura, double costoUnitario) {
		super();
		this.numeroMesa = numeroMesa;
		this.platillo = platillo;
		this.horaApertura = hrApertura;
		this.costoUnitario = costoUnitario;
	}
	

	public Orden() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Orden(int numeroMesa, String platillo, Timestamp horaApertura, Timestamp horaCierre, double costoUnitario, int estado) {
		super();
		this.numeroMesa = numeroMesa;
		this.platillo = platillo;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.estado = estado;
		this.costoUnitario = costoUnitario;
	}


	public Orden(int numeroMesa, String platillo, Timestamp horaApertura, double costoUnitario, int estado) {
		this.numeroMesa = numeroMesa;
		this.platillo = platillo;
		this.horaApertura = horaApertura;
		this.costoUnitario = costoUnitario;
		this.estado = estado;
	}
	
	
}
