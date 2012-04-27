package bomberman.outin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import bomberman.database.AccesoMapa;
import bomberman.database.Mapa;
import bomberman.managers.ManagerImagen;

public class LeerMapa {

	private static InputStream fichero;
	private static BufferedReader brF;
	private static Character inputLine2;
	private static Character arrayChar[][] = iniciar();

	public static Character[][] iniciar() {
		Character tempArray[][] = new Character[20][20];
		return tempArray;
	}

	public static Character[][] LeerMapaJuego(int codMapa) {
		Mapa mapa = AccesoMapa.getMapa(codMapa);
		String stringMap = new String(mapa.getCharArray());
		Character[][]tempCharArray = new Character[20][20];
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				tempCharArray[j][i] = stringMap.charAt((20*i) + j);
			}
		}
		return tempCharArray;
	}
}
