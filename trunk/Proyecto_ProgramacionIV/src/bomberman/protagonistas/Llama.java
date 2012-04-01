package bomberman.protagonistas;

import java.awt.Graphics2D;

import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Llama extends SpriteDinamico{

	private int maxValue = 0;
	private int izq = 0;
	private int der = 0;
	private int arr = 0;
	private int abj = 0;
	private String ultimo = "final.jpg";
	private String intermedio = "inter.jpg";
	private int[]max;
	
	public Llama(Escenario esce, int[]tempMax, float x, float y){
		super(esce);
		this.escenario = esce;
		this.max = tempMax; 
		this.posX = x;
		this.posY = y;
		ManagerImagen.cargarImagen("llama.gif", "intermedio", 4, 8, 0, 2);
		ManagerImagen.cargarImagen("llama.gif", "ultimo", 4, 8, 0, 3);
	}
	
	public void mover(){
		//super.mover();
		
		for(int j = 0; j < max.length; j++){
			if(max[j] > maxValue)
				maxValue = max[j];
		}
	}
	
	public void paint(Graphics2D g) {
		tiempoTranscurrido = (System.currentTimeMillis() - horaUltimaPintada) / 1000.0F;

		if (tiempoTranscurrido > 0.01F) {
			for(int i = 0; i < maxValue; i++){
				int j;
				//Centro
				//Arriba
				arr++;
				for(j = 0; j < arr; j++){
//					System.out.println(ManagerImagen.getImagen(intermedio));
					g.drawImage(ManagerImagen.getImagen("intermedio"), (int)posX + (33*j),
							 (int)posY, escenario);
				}
				g.drawImage(ManagerImagen.getImagen("ultimo"), 33*arr,
						 (int)posY, escenario);
				
				//Abajo
				abj++;
				for(j = 0; j < arr; j++){
					g.drawImage(ManagerImagen.getImagen("intermedio"), (int)posX - (33*j),
							 (int)posY, escenario);
				}
				g.drawImage(ManagerImagen.getImagen("ultimo"), -33*arr,
						 (int)posY, escenario);
				
				//Derecha
				der++;
				for(j = 0; j < arr; j++){
					g.drawImage(ManagerImagen.getImagen("intermedio"), (int)posX,
							 (int)posY + (33*j), escenario);
				}
				g.drawImage(ManagerImagen.getImagen("ultimo"), (int)posX,
						 33*j, escenario);
				
				//Izquierda
				izq++;
				for(j = 0; j < arr; j++){
					g.drawImage(ManagerImagen.getImagen("intermedio"), (int)posX,
							 (int)posY - (33*j), escenario);
				}
				g.drawImage(ManagerImagen.getImagen("ultimo"), (int)posX,
						 -33*j, escenario);	
			}
			
			horaUltimaPintada = System.currentTimeMillis();
//			if(imagActual == 1){
//				imagActual = 0;
//			}else{
//			imagActual++;
//			}
		}
	}
}
