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
	private static HashMap<String, BufferedImage> sprites = init();

	private static HashMap<String, BufferedImage> init()
	{
		return new HashMap<String, BufferedImage>();
	}
	
	public static BufferedImage cargarImagen(String path) {
		miUrl = ManagerImagen.class.getResource(path);
		try {
			biImagen = ImageIO.read(miUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return biImagen;
	}
	
	public static BufferedImage getImagen(String path)
	{
		BufferedImage tempImag = (BufferedImage)sprites.get(path);
		if(tempImag == null)
		{
			tempImag = cargarImagen(path);
			sprites.put(path, tempImag);
		}
		return tempImag;
	}
}
