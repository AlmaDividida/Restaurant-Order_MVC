package mx.uam.ayd.proyecto.presentacion.caja;

import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Anonimux Corporation
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaCorteCaja extends JFrame {
	
	private ControlCorteCaja controlCorteCaja; //Control asociado a esta ventana
	private double corteSistema; //Monto total del corte de caja generado por el sistema
	private DefaultTableModel modelo = new DefaultTableModel(
			new Object[][] {
		},
		new String[] {
			"Fecha", "Numero de Mesa", "Descripción", "Importe"
		}); //Modelo utilizado para llenar la JTable
	private JTable tablaPedidos = new JTable(); //JTable que muestra todas las órdenes sobre las que se realiza el Corte de Caja
	

	/**
	 * Crea la aplicación.
	 */
	public VentanaCorteCaja() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JScrollPane scrollPane = new JScrollPane(tablaPedidos);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		/**
		 * Botón que, al ser presionado, deja de mostrar esta ventana.
		 */
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnRegresar);
		
		/**
		 * Botón que al ser presionado llama a la función continuar del control con la
		 * fecha de hoy y el monto total del corte del sistema como parámetros.
		 */
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date utilDate = new java.util.Date();
				long lnMilisegundos = utilDate.getTime();
				Timestamp hoy = new Timestamp(lnMilisegundos);
				
				controlCorteCaja.continuar(fechaHoy(hoy),corteSistema);
			}
		});
		panel.add(btnContinuar);
	}

	/**
	 * Llena la JTable con la información de todas las órdenes que tienen la fecha de hoy.
	 * @param datos Contiene todos los registros de Ordenes a las que se les 
	 * extraen la información de los campos relevantes para mostrar en la JTable.
	 */
	private void llenaJModel(LinkedList<Orden> datos) {
		double montoTotal = 0;
		this.modelo.setColumnCount(4);
		this.modelo.setRowCount(datos.size()+2);
		int i;
		for(i=0; i<datos.size();i++){
			montoTotal += datos.get(i).getCostoUnitario();
			this.modelo.setValueAt(datos.get(i).getHoraCierre(), i, 0);
			this.modelo.setValueAt(datos.get(i).getPlatillo(), i, 2);
			this.modelo.setValueAt(datos.get(i).getNumeroMesa(), i, 1);
			this.modelo.setValueAt(datos.get(i).getCostoUnitario(), i, 3);
		}
		
		this.modelo.setValueAt("-", datos.size(), 0);
		this.modelo.setValueAt("-", datos.size(), 1);
		this.modelo.setValueAt("--------------------", datos.size(), 2);
		this.modelo.setValueAt("--------------------", datos.size(), 3);
		this.modelo.setValueAt("-", datos.size()+1, 1);
		this.modelo.setValueAt("-", datos.size()+1, 0);
		this.modelo.setValueAt("$ "+montoTotal, datos.size()+1, 3);
		this.modelo.setValueAt("TOTAL", datos.size()+1, 2);
		corteSistema = montoTotal;
		montoTotal = 0;
	}
	
	/**
	 * Actualiza los atributos de clase de esta ventana de acuerdo a los parámetros dados y muestra
	 * la ventana en pantalla con la JTable llena con la información de las órdenes dadas.
	 * @param controlCorteCaja1 el control invocó a esta ventana.
	 * @param datos Todas las órdenes "vendidas" (estado=3) que contienen la fecha de hoy.
	 */
	public void muestra(ControlCorteCaja controlCorteCaja1, LinkedList<Orden> datos) {
		this.controlCorteCaja = controlCorteCaja1;
		/**
		 * Si la lista de datos no está vacía, entonces muestra la ventana.
		 */
		if(!datos.isEmpty()) {
			setTitle("Corte de Caja a la fecha "+fechaHoy(datos.get(0).getHoraApertura()));
			llenaJModel(datos);
			this.tablaPedidos.setModel(this.modelo);
			setVisible(true);
		}
		/**
		 * Si la lista de datos está vacía, entonces muestra un aviso.
		 */
		else {
			java.util.Date utilDate = new java.util.Date();
			long lnMilisegundos = utilDate.getTime();
			Timestamp hoy = new Timestamp(lnMilisegundos);
			JOptionPane.showMessageDialog(null, "No se tiene registro de ventas para el día "+fechaHoy(hoy));
		}
	}
	
	/**
	 * Devuelve la fecha de hoy como cadena para mostrarla en pantalla.
	 * @param fecha La fecha que se quiere convertir a String con formato dd/mm/aaaa.
	 * @return retorno String con la fecha dada en el formato requerido.
	 */
	public String fechaHoy(Timestamp fecha) {
		LocalDateTime hoy = fecha.toLocalDateTime();
		int dia = hoy.getDayOfMonth();
		int mes = hoy.getMonthValue();
		int anio = hoy.getYear();
		String retorno = ""+dia +"/"+ mes +"/"+ anio;
		return retorno;
	}

	/**
	 * Deja de mostrar la ventana actual.
	 */
	public void apagar() {
		setVisible(false);
		
	}

}
