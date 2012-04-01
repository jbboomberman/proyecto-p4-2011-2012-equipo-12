package bomberman.protagonistas;

import bomberman.managers.Escenario;

public class SpriteDinamico extends Sprite {

	protected int deltaX;
	protected int deltaY;
	protected int velocidad;

	public SpriteDinamico(Escenario esce) {
		super(esce);
	}

	protected boolean seSale() {
		return (posX < 0 || posY < 0 || posX > (Escenario.ANCHURA - this.getAnchura()) || posY > (Escenario.ALTURA - this.getAltura()));
	}

	protected void rebota() {
		if (posX < 0) {
			this.setPosX(0);
		} else if (posX > Escenario.ANCHURA - this.getAnchura()) {
			this.setPosX((int)(Escenario.ANCHURA - this.getAnchura()));
		}
		if (posY < 0) {
			this.setPosY(0);
		} else if (posY > Escenario.ALTURA - this.getAltura()) {
			this.setPosY((int)(Escenario.ALTURA - this.getAltura()));
		}
	}
	public void mover(){
		super.mover();
		if(seSale()){
			rebota();
		}
	}
}
