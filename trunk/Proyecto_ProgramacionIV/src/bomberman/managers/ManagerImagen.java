package bomberman.managers;

import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ManagerImagen {

	private static URL miUrl;
	private static BufferedImage biImagen;

	public static BufferedImage cargarImagen(String path) {
		miUrl = ManagerImagen.class.getResource(path);
		try {
			biImagen = ImageIO.read(miUrl);
		} catch (IOException e) {
		}
		return biImagen;
	}
}
