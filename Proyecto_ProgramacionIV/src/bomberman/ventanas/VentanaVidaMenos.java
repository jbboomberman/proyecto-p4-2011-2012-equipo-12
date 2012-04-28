package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bomberman.database.AccesoMapa;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;

public class VentanaVidaMenos extends JDialog implements ActionListener{

	private JButton jbContinuar;
	private JButton jbCancelar;
	private JLabel jlTexto;
	private JPanel jpInferior;
	private Jugador jugador;
	
	public VentanaVidaMenos(Jugador jug){
		jbContinuar = new JButton("Continuar");
		jbCancelar = new JButton("Cancelar");
		jlTexto = new JLabel("Has perdido una vida, ¿quieres continuar?");
		jlTexto.setHorizontalAlignment(SwingConstants.CENTER);
		jpInferior = new JPanel();
		this.jugador = jug;
		
		//Layout
		jpInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setLayout(new BorderLayout());
		
		//Añadir componentes
		jpInferior.add(jbContinuar);
		jpInferior.add(jbCancelar);
		getContentPane().add(jlTexto, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);
		
		//Listeners
		jbContinuar.addActionListener(this);
		jbCancelar.addActionListener(this);
		
		//Parámetros de la ventana
		this.setSize(270, 100);
		this.setResizable(false);
		this.setTitle("¿Seguir jugando?");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e){
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();
		
		if(botonPulsado == jbContinuar){
			new Thread(
            		new Runnable() {
            			public void run() {
            				GestorVentana.ocultarVentana(VentanaJuego.class);
            				VentanaJuego tempVent = (VentanaJuego)GestorVentana.getVentana(VentanaJuego.class);
            				tempVent.borrarSprites();
            				GestorVentana.ocultarVentana(VentanaVidaMenos.class);
            				ControlPrincipal.crearEscenario(AccesoMapa.getCodMapa(jugador.getNivel()));
            				tempVent.setAcabarPartida(false);
        					GestorVentana.hacerVisible(VentanaJuego.class, true);
            			}
            		}
            ).start();
		}else if(botonPulsado == jbCancelar){
			GestorVentana.ocultarVentana(VentanaVidaMenos.class);
			GestorVentana.ocultarVentana(VentanaJuego.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}
	
	public static void main (String []args){
		VentanaVidaMenos prueba = new VentanaVidaMenos(new Jugador("David", "h", "h", "h", 3, 2, 1, ModoJuego.Historia, 1, 1));
		prueba.setVisible(true);
			
	}
}
