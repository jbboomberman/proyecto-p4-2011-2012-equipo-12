package bomberman.protagonistas;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

/**
 * Clase que representa a todos los personajes del juego. De ella heredan varias
 * clases.
 * 
 * @author David
 * @version 1.0
 */
public class Sprite {

	// Posisci�n en el eje X
	protected float posX;
	// Posici�n en el eje Y
	protected float posY;
	// Anchura del Sprite
	protected double anchura;
	// Altura del Sprite
	protected double altura;
	// Escenario donde se pinta el Sprite.
	protected Escenario escenario;
	/*
	 * Array con los nombres de las imagenes cuando el Sprite est� vivo.
	 */
	protected String[] spritesImag;
	/*
	 * Array con los nombres de las imagenes cuando el Sprite se est� muriendo.
	 */
	protected String[] spritesImagDest;
	/*
	 * N�mero de la imagen que se est� viendo en pantalla.
	 */
	protected int imagActual;
	// Velocidad a la que se cambiaran las im�genes
	protected int velocidadPic;

	protected int t;
	// Tiempo transcurrido desde la �ltima pintada
	protected float tiempoTranscurrido;
	// Hora de la �ltima vez que se pinto la imagen
	protected long horaUltimaPintada;
	// Para saber si se est� muriendo
	protected boolean seDestruir;
	// El jugador propietario del Sprite
	protected Jugador jugador;
	//El tama�o genera� de ancho y largo
	protected final int CASILLA = 32;

	/**
	 * Constructor de la clase Sprite
	 * 
	 * @param esce
	 *            - Escenario, donde se pintar�
	 * @param jug
	 *            - Jugador, el propietario
	 */
	public Sprite(Escenario esce, Jugador jug) {
		this.imagActual = 0;
		this.velocidadPic = 10;
		this.t = 0;
		this.escenario = esce;
		this.horaUltimaPintada = 0;
		this.jugador = jug;
		seDestruir = false;
	}

	/**
	 * Para obetener la velocidad con la que se cambian las im�genes.
	 * 
	 * @return velocidadPic - int
	 */
	public int getVelocidadPic() {
		return velocidadPic;
	}

	/**
	 * Para modificar la velocidad con la que se cambian las im�genes.
	 * 
	 * @param velocidadPic
	 *            - int
	 */
	public void setVelocidadPic(int velocidadPic) {
		this.velocidadPic = velocidadPic;
	}

	/**
	 * Para obetener la posici�n en la coordenada X.
	 * 
	 * @return posX - float
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * Para modificar la posici�n en la coordenada X.
	 * 
	 * @param posX
	 *            - float
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * Para obetener la posici�n en la coordenada Y.
	 * 
	 * @return posY - float
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * Para modificar la posici�n en la coordenada Y.
	 * 
	 * @param posY
	 *            - float
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}

	/**
	 * Obtenemos la anchura del Sprite.
	 * 
	 * @return anchura - double
	 */
	public double getAnchura() {
		return anchura;
	}

	/**
	 * Para modificar la anchura del Sprite.
	 * 
	 * @param anchura
	 *            - int
	 */
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	/**
	 * Para obtener la altura del Sprite.
	 * 
	 * @return altura - double
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * Para modificar la altura del Sprite.
	 * 
	 * @param altura
	 *            - int
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Devuelve un array con objetos de la clase String con los nombres de las
	 * im�genes utilizadas cuando el Sprite est� vivo.
	 * 
	 * @return spritesImag - String[]
	 */
	public String[] getSpritesImag() {
		return spritesImag;
	}

	/**
	 * Para modificar el array con objetos de la clase String con los nombres de
	 * las im�genes utilizadas cuando el Sprite est� vivo. Tambi�n calcula la
	 * anchura y altura del Sprite seg�n las medidas m�s grandes de las
	 * imagenes.
	 * 
	 * @param spritesImag
	 *            - String[]
	 */
	public void setSpritesImag(String[] nom) {
		this.spritesImag = nom;
		altura = 0;
		anchura = 0;
		for (int i = 0; i < spritesImag.length; i++) {
			BufferedImage image = ManagerImagen.getImagen(spritesImag[i]);
			if(image == null)
				System.out.println(spritesImag[i]);
			altura = Math.max(altura, image.getHeight());
			anchura = Math.max(anchura, image.getWidth());
		}
	}

	/**
	 * Devuelve el n�mero de imagen utilizada actualmente.
	 * 
	 * @return imagActual - int
	 */
	public int getImagActual() {
		return imagActual;
	}

	/**
	 * Modifica el n�mero de imagen que se est� utilizando.
	 * 
	 * @param imagActual
	 *            - int
	 */
	public void setImagActual(int imagActual) {
		this.imagActual = imagActual;
	}

	/**
	 * Devuelve la variable t.
	 * 
	 * @return t - int
	 */
	public int getT() {
		return t;
	}

	/**
	 * Modifica la variable t.
	 * 
	 * @param t
	 *            - int
	 */
	public void setT(int t) {
		this.t = t;
	}

	/**
	 * Para saber si est� en proceso de destrucci�n.
	 * 
	 * @return seDestruir - boolean
	 */
	public boolean isSeDestruir() {
		return seDestruir;
	}

	/**
	 * Para modificar el estado del Sprite. Destrucci�n - No destrucci�n
	 * 
	 * @param seDestruir
	 *            - boolean
	 */
	public void setSeDestruir(boolean seDestruir) {
		this.seDestruir = seDestruir;
	}

	/**
	 * Devuelve al array con los nombres de las imagenes de destrucci�n.
	 * 
	 * @return spritesImagDest - String[]
	 */
	public String[] getSpriteDestruccion() {
		return spritesImagDest;
	}

	/**
	 * Modifica el array con los nombres de las imagenes de destrucci�n.
	 * 
	 * @param nom
	 *            - String[]
	 */
	public void setSpriteDestruccion(String[] nom) {
		spritesImagDest = nom;
	}

	/**
	 * Sirve para pintar el Sprite en la pantalla. Recibe como par�metro un
	 * objeto de la clase Graphics2D.
	 * 
	 * @param g
	 *            - Graphics2D
	 */
	public void paint(Graphics2D g) {

		/*
		 * En caso de que todav�a no lo hayamos pintado ninguna vez tendremos
		 * que decirle que el tiempo transcurrido es mayor que 0.01F.
		 */
		if (horaUltimaPintada == 0) {
			tiempoTranscurrido = 0.02F;
		} else
			/*
			 * Calculamos el tiempo trasncurrido desde la �ltima vez que lo
			 * pintamos.
			 */
			tiempoTranscurrido = (System.currentTimeMillis() - horaUltimaPintada) / 1000.0F;

		if (tiempoTranscurrido > 0.01F) {
			g.drawImage(ManagerImagen.getImagen(spritesImag[imagActual]),
					(int) posX, (int) posY, escenario);
			horaUltimaPintada = System.currentTimeMillis();
		}
	}

	/**
	 * Este m�todo cambia las imagenes del personaje para que parezca que tiene
	 * vida propia. OJO, no se mueve de posici�n sino que modifica las imagenes
	 * del Sprite para darle vida.
	 */
	public void mover() {
		t++;
		/*
		 * En caso de que hayamos llegado al l�mite para cambiar de imagen y el
		 * personaje no se tenga que destruir.
		 */
		if (t % velocidadPic == 0 && !seDestruir) {
			t = 0;
			imagActual = (imagActual + 1) % spritesImag.length;
		} else if (t % velocidadPic == 0 && seDestruir) {
			/*
			 * En caso de que se tenga que destruir mostramos las im�genes de la
			 * muerte.
			 */
			t = 0;
			imagActual = (imagActual + 1) % spritesImagDest.length;
			if (imagActual == (spritesImagDest.length - 1))
				this.destruir();
		}
	}

	// PUESE SER INTERESANTE
	public boolean colision(Sprite spr) {
		Rectangle tempRect = new Rectangle((int) this.getPosX(),
				(int) this.getPosY(), (int) this.getAnchura(),
				(int) this.getAltura());
		Rectangle tempRect2 = new Rectangle((int) spr.getPosX(),
				(int) spr.getPosY(), (int) spr.getAnchura(),
				(int) spr.getAltura());
		if (tempRect2.intersects(tempRect)) {
			return true;
		} else
			return false;
	}

	/**
	 * Este m�todo se usa para cuando el Sprite tiene que morir. Simplemente
	 * cambia las imagenes a las de destrucci�n y se borra de la lista de
	 * personajes.
	 */
	public void procDestruccion() {
		if (!seDestruir) {
			setSpritesImag(spritesImagDest);
			seDestruir = true;
			imagActual = 0;
		}
	}

	/**
	 * M�todo que se encarga de eliminar el Sprite del escenario.
	 */
	public void destruir() {
		for (Sprite sprTemp : escenario.getLista()) {
			// El mismo objeto
			if (sprTemp == this) {
				escenario.getLista().remove(sprTemp);
				break;
			}
		}
	}

	/**
	 * Devuelve un objeto de la clase Rectangle con la anchura, altura y
	 * posici�n del Sprite.
	 * 
	 * @return Rectangle
	 */
	public Rectangle getRectangle(Sprite spr) {
		Rectangle tempRect;
		/*
		 * En caso de que sea de la clase Bomberman solo tendremos en cuenta la
		 * parte de abajo del personajes ya que se supone que su cabeza no toca
		 * el suelo.
		 */
		if (spr instanceof Bomberman)
			tempRect = new Rectangle((int) spr.getPosX(),
					(int) (spr.getPosY() + (spr.getAltura() / 2)),
					(int) spr.getAnchura(), (int) spr.getAltura() / 2);
		else
			tempRect = new Rectangle((int) spr.getPosX(), (int) spr.getPosY(),
					(int) spr.getAnchura(), (int) spr.getAltura());
		return tempRect;

	}

	/**
	 * Devuelve un objeto de la clase Rectangle pero desde una posici�n
	 * espec�fica.
	 * 
	 * @param spr
	 *            - Sprite
	 * @param x
	 *            - float
	 * @param y
	 *            - float
	 * @return Rectangle
	 */
	public Rectangle getRectangle(Sprite spr, float x, float y) {
		Rectangle tempRect;
		if (spr instanceof Bomberman)
			tempRect = new Rectangle((int) x,
					(int) (y + (spr.getAltura() / 2)), (int) spr.getAnchura(),
					(int) spr.getAltura() / 2);
		else
			tempRect = new Rectangle((int) x, (int) y, (int) spr.getAnchura(),
					(int) spr.getAltura());
		return tempRect;

	}
}
