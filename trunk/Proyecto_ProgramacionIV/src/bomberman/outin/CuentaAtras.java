package bomberman.outin;

import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

//Mirar esta dirección: http://unpocodejava.wordpress.com/2010/02/03/patron-observer/
/**
 * Esta clase implementará e reloj de cuenta atrás que aparece cuando estás
 * jugando. Hereda de la clase Observable porque queremos conocer los cambios
 * que tienen lugar en esta clase para así saber cuando la hora del reloj a
 * decrementado.
 * 
 * @author David
 */
public class CuentaAtras extends Observable {

	private int minutos, segundos;
	private int recarga;
	private boolean parado;
	private static Timer tiempo = null;
	private JLabel reloj;

	public CuentaAtras(int m, int s) throws RelojException {

		reloj = new JLabel();
		parado = true;
		minutos = m;
		segundos = s;

		if (m < 0 || m > 59)
			throw new RelojException("Minutos fuera de rango");
		if (s < 0 || s > 59)
			throw new RelojException("Segundos fuera de rango");
		recarga = 0;
	}

	public void start() {
		if (tiempo == null) {
			tiempo = new Timer();
		}
		parado = false;
		tiempo.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				tareasRun();
			}
		}, 0, 1000);
	}

	protected void tareasRun() {
		if (!parado) {
			if (segundos != 0) {
				segundos--;
			} else {
				if (minutos != 0) {
					minutos--;
					segundos = 59;
				} else {
					//ACABAR
					minutos = 0;
					segundos = 0;
					parado = true;
				}
			}
			reloj.setText("<html><b>Tiempo:</b> " + minutos + " : " + segundos + "</html>");
		}

		// Notifica que se han producido cambios en el reloj.
		this.setChanged();
		// Avisa a sus observadores de que se han producido cambios. Este
		// metodo ya llama a clearChanged().
		this.notifyObservers();
		// Evitamos números negativos
	}

	public void setStopped(boolean b) {
		parado = b;
	}

	public void setTime(int m, int s) throws RelojException {

		if (m < 0 || m > 59)
			throw new RelojException("Minutos fuera de rango");
		else
			minutos = m;
		if (s < 0 || s > 59)
			throw new RelojException("Segundos fuera de rango");
		else
			segundos = s;
	}

	public void recharge() {
		int simul, resto;
		simul = segundos + recarga;
		resto = simul % 3600;
		minutos += resto / 60;
		segundos = resto % 60;
	}

	public boolean isTimeFinished() {
		// MIRAR ESTO
		return true;
	}

	public void setRecharge(int segs) {
		recarga = segs;
	}

	public int getMinutes() {
		return minutos;
	}

	public int getSeconds() {
		return segundos;
	}
	
	public JLabel getReloj() {
		return reloj;
	}

	public void setReloj(JLabel reloj) {
		this.reloj = reloj;
	}

	public static void main(String []args){
		try{
			JFrame frame = new JFrame();
		CuentaAtras prueba = new CuentaAtras(1, 10);
		frame.add(prueba.getReloj());
		prueba.start();
		
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		}catch(RelojException e){
			e.printStackTrace();
		}
	}
}
