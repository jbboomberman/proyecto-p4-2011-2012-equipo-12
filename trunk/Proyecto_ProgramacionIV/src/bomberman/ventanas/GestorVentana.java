package bomberman.ventanas;

import java.util.*;
import java.awt.*;

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
	private static ArrayList<Window> vectorVentanas = iniciar();

	/**
	 * Inicializamos el Vector y le añadimos todas las ventanas, las cuales
	 * serán las que se mostrarán en el programa.
	 */
	public static ArrayList<Window> iniciar() {
		// Inicializamos la lista
		ArrayList<Window> lista = new ArrayList<Window>();
		// Se incializan todas las ventanas.
		lista.add(new VentanaInicial());
		lista.add(new VentanaDatos());
		lista.add(new VentanaSeleccion());
		lista.add(new VentanaJuego());
		lista.add(new VentanaInstrucciones());
		lista.add(new VentanaInformacion());
		lista.add(new VentanaVidaMenos());
		lista.add(new VentanaDirecto());
		lista.add(new VentanaCargar());
		lista.add(new VentanaPuntuaciones());
		lista.add(new VentanaControles());
		lista.add(new VentanaSuperado());
		lista.add(new VentanaSeguir());
		lista.add(new VentanaNoSuperado());
		lista.add(new VentanaGuardado());
		// Se devuelve la lista con las ventanas
		return lista;
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
