package bomberman.outin;

import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JLabel;

//Mirar esta direcci�n: http://unpocodejava.wordpress.com/2010/02/03/patron-observer/
/**
 * Esta clase implementar� el reloj de cuenta atr�s que aparece cuando est�s
 * jugando. Hereda de la clase Observable porque queremos conocer los cambios
 * que tienen lugar en esta clase para as� saber cuando la hora del reloj a
 * decrementado.
 * 
 * @author David
 * @version 1.0
 */
public class CuentaAtras extends Observable {

	// Tiempo del reloj
	private int minutos, segundos;
	private boolean parado;
	// Contador
	private static Timer tiempo = null;
	private JLabel reloj;

	/**
	 * Constructor principal de la clase CuentaAtras
	 * 
	 * @param m
	 *            - int, minutos
	 * @param s
	 *            - int, segundos
	 * @throws RelojException
	 */
	public CuentaAtras(int m, int s) throws RelojException {

		reloj = new JLabel();
		parado = true;
		minutos = m;
		segundos = s;
		// Llamamos al m�todo para decrementar
		start();
		/*
		 * En caso de que los minutos o segundos recibidos no esten en un rango
		 * l�gico se lanza excepci�n.
		 */
		if (m < 0 || m > 59)
			throw new RelojException("Minutos fuera de rango");
		if (s < 0 || s > 59)
			throw new RelojException("Segundos fuera de rango");
	}

	/**
	 * Este m�todo activar� el reloj.
	 */
	public void start() {
		// Inicializamos el contador.
		if (tiempo == null) {
			tiempo = new Timer();
		}
		/*
		 * Cada segundo dever� llamar al m�todo tareasRun() que se encargar� de
		 * decrementar el reloj.
		 */
		tiempo.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (!parado)
					tareasRun();
			}
		}, 0, 1000);
	}

	/**
	 * Se encargar� de decrementar un segundo el reloj cuando se le llame.
	 */
	protected void tareasRun() {
		// Si no est� parado se decrementa.
		if (!parado) {
			// Si segundos es distinto de cero se decrementa.
			if (segundos != 0) {
				segundos--;
			} else {
				if (minutos != 0) {
					minutos--;
					segundos = 59;
				} else {
					// Si segundos y minutos son cero hemos acabado.
					minutos = 0;
					segundos = 0;
					parado = true;
				}
			}
			// Ponemos la hora actual en el JLabel.
			reloj.setText("<html><b>Tiempo:</b> " + minutos + " : " + segundos
					+ "</html>");
		}

		// Notifica que se han producido cambios en el reloj.
		this.setChanged();
		// Avisa a sus observadores de que se han producido cambios. Este
		// metodo ya llama a clearChanged().
		this.notifyObservers();
	}

	/**
	 * M�todo para parar el reloj.
	 * 
	 * @param b
	 *            - boolean
	 */
	public void setParado(boolean b) {
		parado = b;
	}

	/**
	 * Fija el tiempo del reloj.
	 * 
	 * @param m
	 *            - int, minutos
	 * @param s
	 *            - int, segundos
	 * @throws RelojException
	 */
	public void setTiempo(int m, int s) throws RelojException {

		/*
		 * En caso de que el tiempo sea il�gico se lanza excepci�n.
		 */
		if (m < 0 || m > 59)
			throw new RelojException("Minutos fuera de rango");
		else
			minutos = m;
		if (s < 0 || s > 59)
			throw new RelojException("Segundos fuera de rango");
		else
			segundos = s;
	}

	/**
	 * Nos dice si se ha acabado el tiempo.
	 * 
	 * @return boolean
	 */
	public boolean isTimeFinished() {
		return (minutos == 0 && segundos == 0);
	}

	/**
	 * Nos devuelve �ps minutos del reloj.
	 * 
	 * @return minutos - int
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Nos devuelve los egundos del reloj.
	 * 
	 * @return segundos - int
	 */
	public int getSegundos() {
		return segundos;
	}

	/**
	 * Nos devuelve el JLabel del relog.
	 * 
	 * @return reloj - JLabel
	 */
	public JLabel getReloj() {
		return reloj;
	}

	/**
	 * Modificamos el JLabel del reloj.
	 * 
	 * @param reloj
	 *            - JLabel
	 */
	public void setReloj(JLabel reloj) {
		this.reloj = reloj;
	}
}
