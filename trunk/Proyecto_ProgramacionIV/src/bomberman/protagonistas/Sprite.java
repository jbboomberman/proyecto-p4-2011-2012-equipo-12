package bomberman.protagonistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Sprite {

	protected float posX;
	protected float posY;
	protected double anchura;
	protected double altura;
	protected Escenario escenario;
	protected String[] spritesImag;
	protected String[] spritesImagDest;
	protected int imagActual;
	protected int velocidadPic;
	protected int t;
	protected float tiempoTranscurrido;
	protected long horaUltimaPintada;
	protected boolean seDestruir;
	protected Jugador jugador;

	public Sprite(Escenario esce, Jugador jug) {
		this.imagActual = 0;
		this.velocidadPic = 15;
		this.t = 0;
		this.escenario = esce;
		this.horaUltimaPintada = 0;
		this.jugador = jug;
	}

	public int getVelocidadPic() {
		return velocidadPic;
	}

	public void setVelocidadPic(int velocidadPic) {
		this.velocidadPic = velocidadPic;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public double getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String[] getSpritesImag() {
		return spritesImag;
	}

	public void setSpritesImag(String[] spritesImag) {
		this.spritesImag = spritesImag;
	}

	public int getImagActual() {
		return imagActual;
	}

	public void setImagActual(int imagActual) {
		this.imagActual = imagActual;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public void paint(Graphics2D g) {
		if(horaUltimaPintada == 0){
			tiempoTranscurrido = 0.02F;
		}
		else
			tiempoTranscurrido = (System.currentTimeMillis() - horaUltimaPintada) / 1000.0F;

		if (tiempoTranscurrido > 0.01F) {
			g.drawImage(ManagerImagen.getImagen(spritesImag[imagActual]),
					(int) posX, (int) posY, escenario);
				horaUltimaPintada = System.currentTimeMillis();
		}
	}

	public void setSprites(String[] personajes) {
		spritesImag = personajes;

	}

	public Rectangle getBounds() {
		return new Rectangle((int) posX, (int) posY, (int) anchura,
				(int) altura);
	}

	public void mover() {
		t++;
		/*
		 * En caso de que hayamos llegado al límite para
		 * cambiar de imagen y el personaje no se tenga que
		 * destruir.
		 */
		if (t % velocidadPic == 0 && !seDestruir) {
			t = 0;
			imagActual = (imagActual + 1) % spritesImag.length;
		}else if (t % velocidadPic == 0 && seDestruir){
			t = 0;
			imagActual = (imagActual + 1) % spritesImagDest.length;
			if(imagActual == (spritesImagDest.length - 1))
				this.destruir();
		}
	}

	public void setSpriteNombres(String[] nom) {
		spritesImag = nom;
		altura = 0;
		anchura = 0;
		for (int i = 0; i < spritesImag.length; i++) {
			BufferedImage image = ManagerImagen.getImagen(spritesImag[i]);
			altura = Math.max(altura, image.getHeight());
			anchura = Math.max(anchura, image.getWidth());
		}
	}
//PUESE SER INTERESANTE
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
	 * Este método se usa para cuando el Sprite tiene que
	 * morir. Simplemente cambia las imagenes a las de
	 * destrucción y se borra de la lista de personajes.
	 */
	public void procDestruccion(){
		setSpritesImag(spritesImagDest);
		seDestruir = true;
		imagActual = 0;
	}
	public void destruir(){
		for(Sprite sprTemp : escenario.getLista()){
			//El mismo objeto
			if(sprTemp == this){
				escenario.getLista().remove(sprTemp);
				break;
			}
		}
	}
	
	public Rectangle getRectangle(Sprite spr){
		Rectangle tempRect;
		if(spr instanceof Bomberman)
			tempRect = new Rectangle((int)spr.getPosX(), (int)(spr.getPosY() + (spr.getAltura()/2)), (int)spr.getAnchura(), (int)spr.getAltura()/2);
		else
			tempRect = new Rectangle((int)spr.getPosX(), (int)spr.getPosY(), (int)spr.getAnchura(), (int)spr.getAltura());
		return tempRect;

	}
	
	public Rectangle getRectangle(Sprite spr, float x, float y){
		Rectangle tempRect;
		if(spr instanceof Bomberman)
			tempRect = new Rectangle((int)x, (int)(y + (spr.getAltura()/2)), (int)spr.getAnchura(), (int)spr.getAltura()/2);
		else
			tempRect = new Rectangle((int)x, (int)y, (int)spr.getAnchura(), (int)spr.getAltura());
		return tempRect;

	}
}
