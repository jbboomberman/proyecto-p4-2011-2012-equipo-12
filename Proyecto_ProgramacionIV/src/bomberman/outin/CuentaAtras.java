package bomberman.outin;

import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;

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

	protected int minutos, segundos;
	protected int recarga;
	protected boolean parado;
	protected static Timer tiempo = null;

	public CuentaAtras(int m, int s) throws RelojException {

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
		}, 0, 100);
	}

	protected void tareasRun() {
		if (!parado) {
			if (segundos != 0) {
				segundos--;
			} else {
				if (minutos != 0) {
					minutos--;
				} else {
					minutos = 59;
				}
				segundos = 59;
			}
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
}
