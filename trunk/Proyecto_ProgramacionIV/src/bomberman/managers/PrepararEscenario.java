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

	public static void ColocarMapa(Escenario esce, Character arrayChar[][],
			Jugador jug) {

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				switch (arrayChar[i][j]) {

				// Muro indestructible
				case 'X':
					esce.aņadirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, false, jug));
					break;

				// Muro destructible
				case 'I':
					esce.aņadirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, true, jug));
					break;

				// Valcom
				case 'V':
					esce.aņadirSprite(new Valcom(esce, (float) i * 33,
							(float) j * 33, jug));
					break;

				// Dahl
				case 'A':
					esce.aņadirSprite(new Dahl(esce, (float) i * 33,
							(float) j * 33, jug));
					break;
				// Minvo
				case 'M':
					esce.aņadirSprite(new Minvo(esce, (float) i * 33,
							(float) j * 33, jug));
					break;
				case 'O':
					esce.aņadirSprite(new Pontan(esce, (float) i * 33
							, (float) j * 33, jug));
					break;
				// Doria
				case 'D':

					break;
				// BombermanBlanco
				case 'B':
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setBomberman(new BombermanBlanco(esce,
									(float) i * 33, (float) j * 33, jug));
					break;
				// BombermanNegro
				case 'N':
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setBomberman2(new BombermanNegro(esce,
									(float) i * 33, (float) j * 33, jug));
					break;
				// Puerta
				case 'P':
					esce.aņadirSprite(new Puerta(esce, jug,
							(float) (i - 1) * 33, (float) j * 33));
					break;
				// Pildora bomba extra
				case 'E':
					esce.aņadirSprite(new Pildora(esce, (float) (i - 1) * 33,
							(float) j  * 33, 1, esce.getBomberman(), jug));
					break;
				default:
					break;
				}
			}
		}
	}
}
