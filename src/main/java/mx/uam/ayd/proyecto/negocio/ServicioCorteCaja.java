package mx.uam.ayd.proyecto.negocio;


import java.sql.Timestamp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.CorteCajaRepository;

import mx.uam.ayd.proyecto.negocio.modelo.CorteCaja;

@Service
public class ServicioCorteCaja {
	
	@Autowired 
	private CorteCajaRepository corteCajaRepository;
	
	public void creaCorteCaja(double corteSistema, double corteReal, String observaciones){
		CorteCaja corteCaja = new CorteCaja(corteSistema, corteReal, fechaHoy(), observaciones);
		corteCajaRepository.save(corteCaja);
	}
	
	public Timestamp fechaHoy() {
		java.util.Date utilDate = new java.util.Date();
		long lnMilisegundos = utilDate.getTime();
		Timestamp fechaHoy = new Timestamp(lnMilisegundos);
		return fechaHoy;
	}
	
}
