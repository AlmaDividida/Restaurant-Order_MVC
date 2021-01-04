package mx.uam.ayd.proyecto.presentacion.caja;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Anonimux Corporation
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaCorteCaja2 extends JFrame {

	private ControlCorteCaja controlCorteCaja; //Control asociado a esta ventana
	private JPanel contentPane; //Panel principal de la ventana
	private JTextField textFieldCorteReal = new JTextField(); //JTextField en el que el usuario introduce el Corte Real
	private JTextField textFieldObservaciones = new JTextField(); //JTextField en el que el usuario introduce observaciones
	private double corteReal; //Almacena el Corte Real que da el usuario
	private double corteSistema; //Almacena el Corte del sistema
	private JPanel panelDiferencia = new JPanel(); //JPanel que se muestra la diferencia (resta) entre el corte real y el corte del sistema.
	private JLabel lblFechaCorte = new JLabel(); //JLablel que muestra la fecha de corte
	private JLabel lblCorteSistema = new JLabel(); //JLabel que muestra el valor del corte del sistema
	private JButton btnContinuar = new JButton("Continuar"); //Botón utilizado en la ventana
	private int flag = 0; //Bandera que si está puesta en 2, y el usuario presiona el botón continuar, finaliza el corte de caja
	String fechaHoy; //Fecha de hoy en formato dd/mm/aaaa
	String observaciones; //Observaciones hechas por el usuario. Se guardan en persistencia

	/**
	 * Crea la aplicación.
	 */
	public VentanaCorteCaja2() {
		setTitle("Corte de Caja");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitulo = new JPanel();
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		
		JLabel lblInformacin = new JLabel("Información de Corte de Caja");
		lblInformacin.setFont(new Font("Dialog", Font.BOLD, 18));
		panelTitulo.add(lblInformacin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(50,50, 250, 150);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_1.add(lblFechaCorte);
		panel_1.add(lblCorteSistema);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Corte de Caja Real: ");
		panel_3.add(lblNewLabel_2);
		
		panel_3.add(textFieldCorteReal);
		
		panel_3.add(textFieldCorteReal);
		textFieldCorteReal.setColumns(10);
		
		panel_1.add(panelDiferencia);
		panelDiferencia.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDiferencia = new JLabel("");
		panelDiferencia.add(lblDiferencia);
		
		JPanel panelObservaciones = new JPanel();
		panel_1.add(panelObservaciones);
		
		panelObservaciones.setLayout(new GridLayout(0, 2, 0, 0));
		
		/**
		 * Etiqueta que se muestra visible hasta que el usuario ha ingresado el corte de caja real.
		 */
		JLabel lblObservaciones = new JLabel("Observaciones");
		panelObservaciones.add(lblObservaciones);
		lblObservaciones.setVisible(false);
		
		panelObservaciones.add(textFieldObservaciones);
		textFieldObservaciones.setColumns(10);
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		textFieldObservaciones.setVisible(false);
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		/**
		 * Botón que, al ser presionado por el usuario, cierra la ventana actual.
		 */
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_2.add(btnRegresar);

		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String corteRealS = textFieldCorteReal.getText();
				observaciones = textFieldObservaciones.getText();
				/**
				 * Si el valor que se introdujo en el textFieldCorteReal es de tipo double
				 * realiza el corte de caja, de lo contrario muestra un mensaje pidiendo que ingrese un valor válido.
				 */
				if(isDouble(corteRealS)) {
					corteReal = Double.parseDouble(corteRealS);
					lblDiferencia.setText("Diferencia                        	             "+(corteSistema-corteReal));
					textFieldObservaciones.setVisible(true);
					lblObservaciones.setVisible(true);
					flag++;
					/**
					 * Cuando el usuario presiona el botón por segunda vez, el corte de caja finaliza.
					 */
					if (flag==2) {
						creaCorteCaja(corteSistema, corteReal, observaciones);
						JOptionPane.showMessageDialog(null, "Corte de caja realizado\nFecha de corte: "+fechaHoy+"\nObservaciones: "+observaciones);
						setVisible(false);
						btnContinuar.setVisible(false);
						textFieldCorteReal.setEditable(false);
						textFieldObservaciones.setEditable(false);
						apagaCorte();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Inserte un valor numérico para el Corte Real");
				}
			}
		});
		panel_2.add(btnContinuar);
	}

	/**
	 * Actualiza los atributos de clase de esta ventana de acuerdo a los parámetros dados y muestra la ventana en pantalla.
	 * @param controlCorteCaja El control que invocó esta ventana.
	 * @param fechaHoy La fecha de hoy que se muestra en pantalla.
	 * @param montoTotal El monto total del corte de caja del sistema.
	 */
	public void muestra(ControlCorteCaja controlCorteCaja, String fechaHoy, double montoTotal) {
		this.controlCorteCaja = controlCorteCaja;
		this.lblFechaCorte.setText("Fecha de corte:                            "+fechaHoy);
		this.lblCorteSistema.setText("Corte de Caja del Sistema:        "+montoTotal);
		this.corteSistema = montoTotal;
		this.fechaHoy = fechaHoy;
		setVisible(true);
		
	}
	
	/**
	 * Verifica si un String dado contiene un valor que puede ser convertido a tipo double.
	 * @param cadena El String a verificar si es double o no.
	 * @return resultado true si la cadena se pudo convertir a double, false si no.
	 */
	public static boolean isDouble(String cadena) {

        boolean resultado;

        try {
            Double.parseDouble(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
	
	/**
	 * Deja de mostrar la ventanaCorteCaja
	 */
	public void apagaCorte() {
		controlCorteCaja.apagaCorte();
	}
	
	public void creaCorteCaja(double corteSistema, double corteReal, String observaciones) {
		controlCorteCaja.creaCorteCaja(corteSistema, corteReal, observaciones);
	}
}
