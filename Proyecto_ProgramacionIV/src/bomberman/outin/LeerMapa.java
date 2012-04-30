package bomberman.outin;

import bomberman.database.AccesoMapa;
import bomberman.database.Mapa;

/**
 * Esta clase encargar� de leer los mapas del juego de la BD y guardarlos en un
 * array bidimensional.
 * 
 * @author David
 * @version 1.0
 */
public class LeerMapa {

	/**
	 * A este m�todo le enviamos por par�metro el c�digo del mapa y nos devuelve
	 * un array con caracteres que representa al mapa.
	 * 
	 * @param codMapa
	 *            - int, el c�digo del mapa
	 * @return Character[][]
	 */
	public static Character[][] LeerMapaJuego(int codMapa) {
		/*
		 * Llamamos al m�todo est�tico getMapa para que nos devuelva un objeto
		 * de la clase Mapa que contendr� el mapa en formato String.
		 */
		Mapa mapa = AccesoMapa.getMapa(codMapa);
		String stringMap = new String(mapa.getCharArray());
		Character[][] tempCharArray = new Character[20][20];

		/*
		 * Vamos recorriendo todo el String y metemos los datos en un array
		 * bidimensional.
		 */
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				tempCharArray[i][j] = stringMap.charAt((20 * i) + j);
			}
		}
		return tempCharArray;
	}
}
