package bomberman.ventanas;

import java.util.*;
import java.awt.*;

import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;

/**
 * Clase que se encarga de gestionar las ventanas del programa. Muy útil y
 * además ayuda a que el código y el manejo del programa sea más claro y
 * eficiente.
 * 
 * @author David
 * @version 1.0
 */
public class GestorVentana {

	private static final long serialVersionUID = -7461719037402108362L;
	private static Vector<Window> vectorVentanas = iniciar();

	/**
	 * Inicializamos el Vector y le añadimos todas las ventanas, las cuales
	 * serán las que se mostrarán en el programa.
	 * 
	 * @param jug
	 *            - Jugador
	 */
	public static Vector<Window> iniciar() {
		// Inicializamos el vector
		Jugador jug = new Jugador("david", "h", "h", "h", 3, 0, 1, ModoJuego.Historia);
		Vector<Window> vector = new Vector<Window>();
		// Se incializan todas las ventanas.
		vector.add(new VentanaInicial());
		vector.add(new VentanaDatos());
		vector.add(new VentanaSeleccion(jug));
		vector.add(new VentanaJuego());
		vector.add(new VentanaInstrucciones());
		vector.add(new VentanaInformacion());
		vector.add(new VentanaVidaMenos(jug));
		vector.add(new VentanaDirecto(jug));
		vector.add(new VentanaCargar());
		vector.add(new VentanaPuntuaciones());
		vector.add(new VentanaControles());
		// Se devuelve el vector con las ventanas
		return vector;
	}

	/**
	 * Método que se encarga de hacer visible la ventana que se indique por los
	 * parámetros de entrada.
	 * 
	 * @param claseVentana
	 *            - Class
	 * @param unicaVisible
	 *            - boolean, indica si es la única ventana que se deja visible o
	 *            no. true si es la única, false si no.
	 */
	public static void hacerVisible(Class claseVentana, boolean unicaVisible) {
		for (Window vent : vectorVentanas) {
			// En caso de que la ventana sea de la clase que queremos.
			if (vent.getClass().isAssignableFrom(claseVentana)) {
				vent.setVisible(true);
			} else {
				/*
				 * Si queremos que la ventana sea la única visible debemos
				 * ocultar las demas.
				 */
				if (unicaVisible) {
					vent.setVisible(false);
				}
			}
		}
	}

	/**
	 * Método que se encarga de ocultar la ventana que sea de la misma clase que
	 * se indica en el parámetro de entrada.
	 * 
	 * @param claseVentana
	 *            - Class
	 */
	public static void ocultarVentana(Class claseVentana) {
		for (Window vent : vectorVentanas) {
			if (vent.getClass().isAssignableFrom(claseVentana)) {
				vent.setVisible(false);
				// Cortamos el for.
				break;
			}
		}
	}

	public static Object getVentana(Class claseVentana) {
		for (Window vent : vectorVentanas) {
			if (vent.getClass().isAssignableFrom(claseVentana)) {
				return vent;
			}
		}
		return null;
	}
}
