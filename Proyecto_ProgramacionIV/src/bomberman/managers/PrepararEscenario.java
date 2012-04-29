package bomberman.managers;

import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.protagonistas.Bomberman;
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

public class PrepararEscenario {
	
	private static final int CASILLA = 32;

	public static void ColocarMapa(Escenario esce, Character arrayChar[][],
			Jugador jug) {

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				switch (arrayChar[i][j]) {

				// Muro indestructible
				case 'X':
					esce.añadirSprite(new Muro(esce, (float) i * CASILLA,
							(float) j * CASILLA, false, jug));
					break;

				// Muro destructible
				case 'I':
					esce.añadirSprite(new Muro(esce, (float) i * CASILLA,
							(float) j * CASILLA, true, jug));
					break;

				// Valcom
				case 'V':
					esce.añadirSprite(new Valcom(esce, (float) i * CASILLA,
							(float) j * CASILLA, jug));
					break;

				// Dahl
				case 'A':
					esce.añadirSprite(new Dahl(esce, (float) i * CASILLA,
							(float) j * CASILLA, jug));
					break;
				// Minvo
				case 'M':
					esce.añadirSprite(new Minvo(esce, (float) i * CASILLA,
							(float) j * CASILLA, jug));
					break;
				case 'O':
					esce.añadirSprite(new Pontan(esce, (float) i * CASILLA
							, (float) j * CASILLA, jug));
					break;
				// Doria
				case 'D':

					break;
				// BombermanBlanco
				case 'B':
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setBomberman(new BombermanBlanco(esce,
									(float) i * CASILLA, ((float) j * CASILLA) - 20, jug));
					break;
				// BombermanNegro
				case 'N':
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setBomberman2(new BombermanNegro(esce,
									(float) i * CASILLA, ((float) j * CASILLA) - 20, jug));
					break;
				// Puerta
				case 'P':
					esce.añadirSprite(new Puerta(esce, jug,
							(float) (i - 1) * CASILLA, (float) j * CASILLA));
					break;
				// Pildora bomba extra
				case 'E':
					esce.añadirSprite(new Pildora(esce, (float) (i - 1) * CASILLA,
							(float) j  * CASILLA, 1, esce.getBomberman(), jug));
					break;
				default:
					break;
				}
			}
		}
	}
}
