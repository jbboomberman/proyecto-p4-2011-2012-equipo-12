package bomberman.managers;

import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.protagonistas.Muro;
import bomberman.protagonistas.Valcom;

public class PrepararEscenario {

	public static void ColocarMapa(Escenario esce, Character arrayChar[][], Jugador jug) {

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				switch(arrayChar[i][j]){
				
				// Muro indestructible
				case 'X':
					esce.añadirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, false, jug));
					break;
				
				// Muro destructible
				case 'I':
					esce.añadirSprite(new Muro(esce, (float) i * 33,
							(float) j * 33, true, jug));
					break;
				
				//Valcom
				case 'V':
					esce.añadirSprite(new Valcom(esce, 50, 50, jug));
					break;
					
				//Dahl
				case 'A':
					
					break;
				//Minvo
				case 'M':
					
					break;
				//Doria
				case 'D':
					
					break;
				default:
					System.out.println("No existe un objeto del mapa");
					System.out.println(arrayChar[i][j]);
					break;
				}
			}
		}
	}
}
