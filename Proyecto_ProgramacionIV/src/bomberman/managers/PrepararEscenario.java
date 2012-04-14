package bomberman.managers;

import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.protagonistas.Dahl;
import bomberman.protagonistas.Minvo;
import bomberman.protagonistas.Muro;
import bomberman.protagonistas.Valcom;

public class PrepararEscenario {

	public static void ColocarMapa(Escenario esce, Character arrayChar[][], Jugador jug) {

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				switch(arrayChar[i][j]){
				
				// Muro indestructible
				case 'X':
					esce.a�adirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, false, jug));
					break;
				
				// Muro destructible
				case 'I':
					esce.a�adirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, true, jug));
					break;
				
				//Valcom
				case 'V':
					esce.a�adirSprite(new Valcom(esce, (float) i * 33, (float) j * 33, jug));
					break;
					
				//Dahl
				case 'A':
					esce.a�adirSprite(new Dahl(esce, (float) i * 33, (float) j * 33, jug));
					break;
				//Minvo
				case 'M':
					esce.a�adirSprite(new Minvo(esce, (float) i * 33, (float) j * 33, jug));
					break;
				//Doria
				case 'D':
					
					break;
				default:
					break;
				}
			}
		}
	}
}
