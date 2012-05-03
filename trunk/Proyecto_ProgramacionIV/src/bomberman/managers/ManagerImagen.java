package bomberman.managers;

import bomberman.database.AccesoImagen;
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

//	private static HashMap<String, BufferedImage> cargarImagenes() {
//		HashMap<String, BufferedImage> tempHash = new HashMap<String, BufferedImage>();
//		String[] nomImagenes = { "pildora_vida.gif" };
//		int cont;
//		int[] columnas = { 1 };
//		int[] filas = { 1 };
//
//		for (int i = 0; i < nomImagenes.length; i++) {
//			cont = 0;
//			miUrl = ManagerImagen.class.getClassLoader().getResource(
//					"bomberman/resources/" + nomImagenes[i]);
//			try {
//				biImagen = ImageIO.read(miUrl);
//				int anchuraIndi = biImagen.getWidth() / columnas[i];
//				int alturaIndi = biImagen.getHeight() / filas[i];
//
//				for (int j = 0; j < filas[i]; j++) {
//					for (int x = 0; x < columnas[i]; x++) {
//						BufferedImage tempBuff = biImagen.getSubimage(x
//								* anchuraIndi, j * alturaIndi, anchuraIndi,
//								alturaIndi);
////						tempHash.put(nomImagenes[i] + '_' + (cont + 1),
////								tempBuff);
//						AccesoImagen.insertarImagen(tempBuff, "pildora_vida.gif_1", "gif");
//						cont++;
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
////		return tempHash;
//	}

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
	public static void cargarImagen(String nom, String futNom) {
		miUrl = ManagerImagen.class.getClassLoader().getResource(
				"bomberman/resources/" + nom);
		try {
			biImagen = ImageIO.read(miUrl);
			// sprites.put(futNom, tempBuff);
			AccesoImagen.insertarImagen(biImagen, futNom, "gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 private static HashMap<String, BufferedImage> cargarImagenes() {
	 HashMap<String, BufferedImage> tempHash = new HashMap<String,
	 BufferedImage>();
	 String[] nomImagenes = { "bombs.gif_1", "bombs.gif_2", "bombs.gif_3",
	 "muro.jpg_1", "muro.jpg_2", "muro.jpg_3", "muro.jpg_4",
	 "muro.jpg_5", "muro.jpg_6", "muro.jpg_7", "muro.jpg_8",
	 "indestructible.jpg_1", "valcom.png_1", "valcom.png_2",
	 "valcom.png_3", "valcom_dest.gif_1", "destruccion.png_1",
	 "destruccion.png_2", "destruccion.png_3", "destruccion.png_4",
	 "destruccion.png_5", "minvo.gif_1", "minvo.gif_2",
	 "minvo.gif_3", "minvo_dest.gif_1", "dahl.gif_1", "dahl.gif_2",
	 "dahl.gif_3", "dahl.gif_4", "dahl.gif_5",
	 "pildora_bomba.png_1", "nada.png_1", "llama.gif_1",
	 "llama.gif_2", "llama.gif_3", "llama.gif_4", "llama.gif_5",
	 "llama.gif_6", "llama.gif_7", "llama.gif_8", "llama.gif_9",
	 "llama.gif_10", "llama.gif_11", "llama.gif_12", "llama.gif_13",
	 "llama.gif_14", "llama.gif_15", "llama.gif_16", "llama.gif_17",
	 "llama.gif_18", "llama.gif_19", "llama.gif_20", "llama.gif_21",
	 "llama.gif_22", "llama.gif_23", "llama.gif_24", "llama.gif_25",
	 "llama.gif_26", "llama.gif_27", "llama.gif_28", "llama.gif_29",
	 "llama.gif_30", "llama.gif_31", "llama.gif_32",
	 "bomber_dest.gif_1", "bomber_dest.gif_2", "bomber_dest.gif_3",
	 "bomber_dest.gif_4", "bomber_up_down.gif_1",
	 "bomber_up_down.gif_2", "bomber_up_down.gif_3",
	 "bomber_up_down.gif_4", "bomber_up_down.gif_5",
	 "bomber_up_down.gif_6", "bomber_der.gif_1", "bomber_der.gif_2",
	 "bomber_der.gif_3", "bomber_izq.gif_1", "bomber_izq.gif_2",
	 "bomber_izq.gif_3", "bomber2_dest.gif_1", "bomber2_dest.gif_2",
	 "bomber2_dest.gif_3", "bomber2_dest.gif_4",
	 "bomber2_up_down.gif_1", "bomber2_up_down.gif_2",
	 "bomber2_up_down.gif_3", "bomber2_up_down.gif_4",
	 "bomber2_up_down.gif_5", "bomber2_up_down.gif_6",
	 "bomber2_der.gif_1", "bomber2_der.gif_2", "bomber2_der.gif_3",
	 "bomber2_izq.gif_1", "bomber2_izq.gif_2", "bomber2_izq.gif_3",
	 "puerta.png_1", "pontan.png_1", "pontan.png_2", "pontan.png_3",
	 "pontan_dest.png_1", "doria.gif_1", "doria.gif_2", "doria.gif_3"
	 , "doria_dest.gif_1", "pildora_patines.gif_1", "pildora_vida.gif_1"};
	
	 for (int i = 0; i < nomImagenes.length; i++) {
	 tempHash.put(nomImagenes[i], AccesoImagen.getImagen(nomImagenes[i]));
	 }
	 return tempHash;
	 }

	public static BufferedImage getImagen(String path) {
		BufferedImage tempImag = (BufferedImage) sprites.get(path);
		return tempImag;
	}

	public static void main(String[] args) {
		cargarImagenes();
	}
}
