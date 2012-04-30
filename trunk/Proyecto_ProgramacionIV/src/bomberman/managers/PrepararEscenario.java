package bomberman.managers;

import bomberman.jugador.Jugador;
import bomberman.protagonistas.BombermanBlanco;
import bomberman.protagonistas.BombermanNegro;
import bomberman.protagonistas.Dahl;
import bomberman.protagonistas.Minvo;
import bomberman.protagonistas.Muro;
import bomberman.protagonistas.Pildora;
import bomberman.protagonistas.Pontan;
import bomberman.protagonistas.Puerta;
import bomberman.protagonistas.Valcom;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaJuego;

/**
 * Clase que nos permite crear el escenario del
 * juego. Crea los objetos del mapa en cada posici�n.
 * @author David
 * @version 1.0
 */
public class PrepararEscenario {
	
	/*
	 * Tama�o est�ndar de casi todos los personajes.
	 * Tambi�n es el tama�o de cada columna y fila.
	 */
	private static final int CASILLA = 32;

	/**
	 * M�todo que se encarga de a�adir los Sprites que
	 * va leyendo en cada posici�n del array de caracteres
	 * al ArrayList de Sprites de la ventana VentanaJuego.
	 * @param esce - Escenario
	 * @param arrayChar - Character[][]
	 * @param jug - Jugador
	 */
	public static void ColocarMapa(Escenario esce, Character arrayChar[][],
			Jugador jug) {

		/*
		 * Hacemos dos loops para leer todo el array
		 */
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				
				//Un switch para identificar la letra
				switch (arrayChar[i][j]) {
				// Muro indestructible
				case 'X':
					esce.a�adirSprite(new Muro(esce, (float) j * CASILLA,
							(float) i * CASILLA, false, jug));
					break;

				// Muro destructible
				case 'I':
					esce.a�adirSprite(new Muro(esce, (float) j * CASILLA,
							(float) i * CASILLA, true, jug));
					break;

				// Valcom
				case 'V':
					esce.a�adirSprite(new Valcom(esce, (float) j * CASILLA,
							(float) i * CASILLA, jug));
					break;

				// Dahl
				case 'A':
					esce.a�adirSprite(new Dahl(esce, (float) j * CASILLA,
							(float) i * CASILLA, jug));
					break;
				// Minvo
				case 'M':
					esce.a�adirSprite(new Minvo(esce, (float) j * CASILLA,
							(float) i * CASILLA, jug));
					break;
				case 'O':
					esce.a�adirSprite(new Pontan(esce, (float) j * CASILLA
							, (float) i * CASILLA, jug));
					break;
				// Doria
				case 'D':

					break;
				// BombermanBlanco
				case 'B':
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setBomberman(new BombermanBlanco(esce,
									(float) j * CASILLA, ((float) i * CASILLA) - 20, jug));
					break;
				// BombermanNegro
				case 'N':
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setBomberman2(new BombermanNegro(esce,
									(float) j * CASILLA, ((float) i * CASILLA) - 20, jug));
					break;
				// Puerta
				case 'P':
					esce.a�adirSprite(new Puerta(esce, jug,
							(float) (j - 1) * CASILLA, (float) i * CASILLA));
					break;
				// Pildora bomba extra
				case 'E':
					esce.a�adirSprite(new Pildora(esce, (float) (j - 1) * CASILLA,
							(float) i  * CASILLA, 1, esce.getBomberman(), jug));
					break;
				default:
					break;
				}
			}
		}
	}
}
