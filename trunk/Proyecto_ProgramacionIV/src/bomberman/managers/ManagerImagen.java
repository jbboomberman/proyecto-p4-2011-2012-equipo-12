package bomberman.managers;

import bomberman.protagonistas.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;

public class ManagerImagen {

	private static URL miUrl;
	private static BufferedImage biImagen;
	private static BufferedImage finalImagen;
	private static HashMap<String, BufferedImage> sprites = cargarImagenes();

	private static HashMap<String, BufferedImage> cargarImagenes() {
		HashMap<String, BufferedImage> tempHash = new HashMap<String, BufferedImage>();
		String[] array = { "bombs.gif", "bomber.gif", "muro.jpg",
				"indestructible.jpg" };
		int cont;
		int[] array2 = { 3, 4, 8, 1 };
		int[] array3 = { 5, 6, 1, 1 };
		for (int i = 0; i < array.length; i++) {
			cont = 0;
			miUrl = ManagerImagen.class.getClassLoader().getResource(
					"bomberman/resources/" + array[i]);
			try {
				biImagen = ImageIO.read(miUrl);
				int parseWidth = biImagen.getWidth() / array2[i];
				int parseHeight = biImagen.getHeight() / array3[i];
				for (int j = 0; j < array3[i]; j++) {
					for (int x = 0; x < array2[i]; x++) {
						BufferedImage tempBuff = biImagen.getSubimage(x
								* parseWidth, j * parseHeight, parseWidth,
								parseHeight);
						tempHash.put(array[i] + '_' + (cont + 1), tempBuff);
						cont++;
					}
				}
				// finalImagen = biImagen.getSubimage(x, y, sizex, sizey);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tempHash;
	}

	/**
	 * 
	 * @param nom
	 * @param maxAnc
	 *            - Número de personajes de ancho
	 * @param maxAlt
	 *            - Número de personajes de largo
	 * @param anc
	 * @param alt
	 */
	public static void cargarImagen(String nom, String futNom, int maxAnc, int maxAlt,
			int anc, int alt) {
		miUrl = ManagerImagen.class.getClassLoader().getResource(
				"bomberman/resources/" + nom);
		try {
			biImagen = ImageIO.read(miUrl);
			int parseWidth = biImagen.getWidth() / maxAnc;
			int parseHeight = biImagen.getHeight() / maxAlt;
			BufferedImage tempBuff = biImagen.getSubimage(anc * parseWidth, alt * parseHeight, parseWidth,
							parseHeight);
			sprites.put(futNom, tempBuff);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getImagen(String path) {
		BufferedImage tempImag = (BufferedImage) sprites.get(path);
		if (tempImag == null) {
		}
		return tempImag;
	}
}
