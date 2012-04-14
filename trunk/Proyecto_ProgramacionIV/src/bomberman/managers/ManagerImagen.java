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
	private static HashMap<String, BufferedImage> sprites = cargarImagenes();

	private static HashMap<String, BufferedImage> cargarImagenes() {
		HashMap<String, BufferedImage> tempHash = new HashMap<String, BufferedImage>();
		String[] nomImagenes = { "bombs.gif", "muro.jpg",
				"indestructible.jpg",  "valcom.png", "valcom_dest.gif", "destruccion.png", "minvo.gif", 
				"minvo_dest.gif", "dahl.gif", "pildora_bomba.png", "nada.png", "llama.gif", "bomber_dest.gif",
				"bomber_up_down.gif", "bomber_der.gif", "bomber_izq.gif"};
		int cont;
		int[] columnas = { 3, 8, 1, 6, 1, 5, 3, 1, 5, 1, 1, 4, 4, 3, 3, 3};
		int[] filas = { 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 1, 2, 1, 1};
		
		for (int i = 0; i < nomImagenes.length; i++) {
			cont = 0;
			miUrl = ManagerImagen.class.getClassLoader().getResource(
					"bomberman/resources/" + nomImagenes[i]);
			try {
				biImagen = ImageIO.read(miUrl);
				int anchuraIndi = biImagen.getWidth() / columnas[i];
				int alturaIndi = biImagen.getHeight() / filas[i];
				
				for (int j = 0; j < filas[i]; j++) {
					for (int x = 0; x < columnas[i]; x++) {
						BufferedImage tempBuff = biImagen.getSubimage(x
								* anchuraIndi, j * alturaIndi, anchuraIndi,
								alturaIndi);
						tempHash.put(nomImagenes[i] + '_' + (cont + 1), tempBuff);
						cont++;
					}
				}
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
//	public static void cargarImagen(String nom, String futNom, int maxAnc,
//			int maxAlt, int anc, int alt) {
//		miUrl = ManagerImagen.class.getClassLoader().getResource(
//				"bomberman/resources/" + nom);
//		try {
//			biImagen = ImageIO.read(miUrl);
//			int parseWidth = biImagen.getWidth() / maxAnc;
//			int parseHeight = biImagen.getHeight() / maxAlt;
//			BufferedImage tempBuff = biImagen.getSubimage(anc * parseWidth, alt
//					* parseHeight, parseWidth, parseHeight);
//			sprites.put(futNom, tempBuff);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static BufferedImage getImagen(String path) {
		BufferedImage tempImag = (BufferedImage) sprites.get(path);
		return tempImag;
	}
}
