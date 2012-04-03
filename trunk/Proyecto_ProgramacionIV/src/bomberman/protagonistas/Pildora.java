package bomberman.protagonistas;

import bomberman.managers.Escenario;

public class Pildora extends SpriteEstatico {

	private String tipo;

	public Pildora(Escenario esce, String tip) {
		super(esce);
		this.tipo = tip;
	}
}
