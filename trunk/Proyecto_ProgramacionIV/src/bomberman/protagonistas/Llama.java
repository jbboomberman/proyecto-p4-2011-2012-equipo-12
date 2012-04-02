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
	private int[]max;
	
	public Llama(Escenario esce, int[]tempMax, float x, float y){
		super(esce);
		this.escenario = esce;
		this.max = tempMax; 
		this.posX = x;
		this.posY = y;
		
		//CUIDADO ESTAMOS CARGANDO DEMASIADO
		ManagerImagen.cargarImagen("llama.gif", "intermedio_anc", 4, 8, 0, 2);
		ManagerImagen.cargarImagen("llama.gif", "ultimo_izq", 4, 8, 0, 3);
		ManagerImagen.cargarImagen("llama.gif", "ultimo_der", 4, 8, 0, 4);
		ManagerImagen.cargarImagen("llama.gif", "ultimo_arr", 4, 8, 0, 6);
		ManagerImagen.cargarImagen("llama.gif", "ultimo_abj", 4, 8, 0, 7);
		ManagerImagen.cargarImagen("llama.gif", "intermedio_alt", 4, 8, 0, 5);
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
			for(int i = 0; i < maxValue*2; i++){
				//Centro
				//Arriba
				if(arr < max[0]){
					for(int j = 0; j < arr; j++){
					g.drawImage(ManagerImagen.getImagen("intermedio_alt"), (int)posX,
							 (int)posY - (33*j), escenario);
					}
					g.drawImage(ManagerImagen.getImagen("ultimo_arr"), (int)posX,
							-33*arr, escenario);
					arr++;
				}
				else if (max[0] > 0){
					for(int j = 0; j < arr; j++){
						g.drawImage(ManagerImagen.getImagen("intermedio_alt"), (int)posX,
								 (int)posY - (33*j), escenario);
						}
						g.drawImage(ManagerImagen.getImagen("ultimo_arr"), (int)posX,
								-33*arr, escenario);
						arr--;
						max[0]--;
				}
				
				
				//Abajo
				
				if(abj < max[1]){
					g.drawImage(ManagerImagen.getImagen("intermedio_alt"), (int)posX ,
							 (int)posY + (33*abj), escenario);
					g.drawImage(ManagerImagen.getImagen("ultimo_abj"), (int)posX,
							 33*abj, escenario);
					abj++;
				}
				
				
				//Derecha
				
				if(der < max[2]){
					g.drawImage(ManagerImagen.getImagen("intermedio_anc"), (int)posX + (33*der),
							 (int)posY, escenario);
					g.drawImage(ManagerImagen.getImagen("ultimo_der"), 33*der,
							 (int)posY, escenario);
					der++;
				}
				
				
				//Izquierda
				izq++;
				if(izq < max[3]){
					g.drawImage(ManagerImagen.getImagen("intermedio_anc"), (int)posX - (33*izq),
							 (int)posY, escenario);
					g.drawImage(ManagerImagen.getImagen("ultimo_izq"), -33*izq,
							(int)posY, escenario);
				}
			
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
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
