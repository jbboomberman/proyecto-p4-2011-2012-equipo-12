package bomberman.ventanas;

import java.awt.*;
import java.net.*;
import javax.swing.*;

/**
 * Este JPanel modificado tiene como objetivo pintar una imagen en el fondo del
 * panel.
 * 
 * @author David
 * @version 1.0
 */
public class PanelFondo extends JPanel {

	private static final long serialVersionUID = -7461719037402108362L;
	private ImageIcon imagen;
	private URL directorio;
	private Container container;

	/**
	 * Constructor principal de la clase PanelFondo.
	 * 
	 * @param path
	 *            - String. Path donde está la imagen.
	 * @param con
	 *            - Container. En que container hay que visualizar la imagen,
	 *            nos servirá para saber que medidas tiene y así redimensionar
	 *            la imagen en todo momento.
	 */
	public PanelFondo(String path, Container con) {
		directorio = getClass().getResource(path);
		imagen = new ImageIcon(directorio);
		container = con;
	}

	/**
	 * Método que sobreescribe el método paintComponent de la clase JPanel.
	 * 
	 * @param g
	 *            - Graphics. Para dibujar los componentes.
	 */
	public void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g; // El Graphics realmente es
											// Graphics2D
			// Esto nos ayuda a que la imagen se redimensione correctamente sin
			// perder calidad.
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

		}
		// Le indicamos que dibuje la imagen en la ventana.
		g.drawImage(imagen.getImage(), 0, 0, container.getWidth(),
				container.getHeight(), null);
		/*
		 * Le indicamos al panel que no dibuje su fondo por defecto, porque sino
		 * nos taparía nuestra imagen.
		 */
		setOpaque(false);
		// Le indicamos que pinte el resto de los componentes.
		super.paintComponent(g);

	}
}
