package bomberman.managers;

import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.protagonistas.Muro;
import bomberman.protagonistas.Valcom;

public class PrepararEscenario {

	public static void ColocarMapa(Escenario esce, Character arrayChar[][], Jugador jug) {
		Character caracter;
		int cont = 0;

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				// Muro indestructible
				if (arrayChar[i][j] == 'X') {
					esce.añadirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, false, jug));
					// Muro destructible
				} else if (arrayChar[i][j] == 'I') {
					esce.añadirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, true, jug));
					// Valcom
				} else if (arrayChar[i][j] == 'V') {
					esce.añadirSprite(new Valcom(esce, 50, 50, jug));
					// Dahl
				} else if (arrayChar[i][j] == 'A') {

					// Minvo
				} else if (arrayChar[i][j] == 'M') {

					// Doria
				} else if (arrayChar[i][j] == 'D') {

				}
			}

		}

	}
}
