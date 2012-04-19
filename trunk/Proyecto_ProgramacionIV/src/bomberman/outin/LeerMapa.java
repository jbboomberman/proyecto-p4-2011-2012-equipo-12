package bomberman.outin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

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

	public static Character[][] LeerMapaJuego(String nom) {
		try {
			fichero = (LeerMapa.class.getClassLoader()
					.getResourceAsStream("bomberman/resources/" + nom));
			brF = new BufferedReader(new InputStreamReader(fichero));
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					inputLine2 = (char) brF.read();
					// No queremos leer los retornos de carro ni nuevas lineas.
					while ((int) inputLine2 == 13 || (int) inputLine2 == 10) {
						inputLine2 = (char) brF.read();
					}
					arrayChar[j][i] = (inputLine2);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				brF.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fichero.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		return arrayChar;
	}
}
