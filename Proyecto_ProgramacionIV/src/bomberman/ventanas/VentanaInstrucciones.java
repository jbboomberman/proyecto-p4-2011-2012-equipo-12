package bomberman.ventanas;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import bomberman.database.AccesoImagen;

/**
 * Ventana que nos mostrará las instrucciones del juego.
 * @author David
 * @version 1.0
 */
public class VentanaInstrucciones extends JDialog{
	
	private static final long serialVersionUID = -7461719037402108362L;
	private JTextPane panelTexto1;
	private JTextPane panelTexto2;
	private JTextPane panelTexto3;
	private JTextPane panelTexto4;
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JScrollPane scroll3;
	private JScrollPane scroll4;
	private SimpleAttributeSet normal;
	private SimpleAttributeSet negrita;
	private SimpleAttributeSet apartado;
	private String textValcom;
	private String textDahl;
	private String textMinvo;
	private String textPontan;
	private String textDoria;
	private String textBomb;
	private String textMuroDes;
	private String textMuroIndes;
	private String textPildora;
	private String textDetona;
	private String textPati;
	private String textWall;
	private String textSalva;
	private String textVida;
	private String textMulti;
	private String textPuerta;
	private String textReglas;
	private String textObjetivos;
	private JTabbedPane jtPestañas;

	/**
	 * Constructor principal de la clase VentanaInstrucciones.
	 */
	public VentanaInstrucciones()
	{
		panelTexto1 = new JTextPane();
		panelTexto2 = new JTextPane();
		panelTexto3 = new JTextPane();
		panelTexto4 = new JTextPane();
		
		//Inicializamos los String con sus respectivas frases.
		textValcom = "     - Valcom: Es el enemigo más simple. Se moverá despacio, fácil de matar y tiene una puntuación de 100 puntos. Simplemente rebotará en los muros y nunca cambiará de dirección.\n";
		textDahl = "        – Dahl: Algo más rápido que los del tipo ‘Valcom’ pero este sí cambiará de vez en cuando de dirección. Valdrá 200 puntos.\n";
		textMinvo = "        – Minvo: Este se moverá como los ‘Valcom’s pero será bastante más rápido que todos los demás. Valdrá 400 puntos.\n";
		textPontan = "        – Pontan: Se moverá a la misma velocidad que los ‘Valcom’ pero será capaz de traspasar los destructibles. Valdrá 1000 puntos.\n";
		textDoria = "        – Doria: Se moverá más rápido que los ‘Pontan’ y podrá traspasar los muros destructibles. Valdrá 2000 puntos.\n";
		textBomb = "        – Bombas: La explosión de estas es lo que logrará acabar con los enemigos y destruir los muros destructibles.\n";
		textMuroDes = "        – Muros destructibles: Muros que el personaje no puede traspasar pero si destruir con las bombas.\n";
		textMuroIndes = "        – Muros indestructibles: Muros que el personaje no puede ni traspasar ni destruir con las bombas.\n";
		textPildora = "        – Píldoras: Al cogerlas obtendremos habilidades especiales.\n";
		textDetona = "        – Detonador: Te permite hacer explotar la bomba cuando tú quieras mientras no haya explotado ella sola.\n";
		textPati = "        – Patines: Te permiten correr el doble de rápido.\n";
		textWall = "        – Wall-walker: Te permite atravesar los muros que sean de tipo destruible.\n";
		textSalva = "        – SalvaLlama: Te hace inmune a las explosiones de tus bombas durante 30 segundos.\n";
		textVida = "        – Vida: Te da una vida más.\n";
		textMulti = "        – MultiBomba: Aumenta en 1 el número de bombas que puedes poner a la vez.\n";
		textPuerta = "        – Puertas: Estarán escondidas debajo de los muros destructibles. Hay que tener en cuenta que las puertas pueden ser destruidas por las bombas lo cual impedirá que nos pasemos el nivel.\n";
		textReglas = "El personaje principal es un robot al cual le han contado que si sale de las entrañas de la tierra y sale a la superficie logrará convertirse en humano. Por este motivo, el objetivo del protagonista es buscar las salidas que le llevarán a la superficie, tendrá que superar todos los niveles para llegar a la superficie. Aun así, necesitará matar a todos los enemigos de cada nivel para que las puertas queden abiertas, las cuales estarán escondidas debajo de los muros destructibles. El jugador empezará con tres vidas y en caso de que sea alcanzado por una bomba o monstruo perderá una de ellas. También perderá una vida en caso de que destruya la única puerta de salida que hay en cada nivel.";
		textObjetivos = "        El objetivo del personaje principal (un robot), será escapar de las entrañas de la tierra porque corre el rumor de que los robots que consigan llegar a la superficie se convertirán en humanos. Para lograr este objetivo deberán pasar por distintos laberintos donde tendrá que matar a todos los enemigos y buscar la puerta que le lleve al próximo nivel, cada nivel tendrá un tiempo máximo para ser completado.";
		jtPestañas = new JTabbedPane();
		
		/*
		 * La clase SimpleAttributeSet nos sirve para darle propiedades a los textos
		 * que insertamos en los JTextPane, por ejemplo: Negrita, cursiva, color, subrayado...
		 */
		normal = new SimpleAttributeSet();
		StyleConstants.setItalic(normal, true);
		negrita = new SimpleAttributeSet();
		StyleConstants.setBold(negrita, true);
		apartado = new SimpleAttributeSet();
		StyleConstants.setBold(apartado, true);
		StyleConstants.setUnderline(apartado, true);
		StyleConstants.setFontSize(apartado, 15);
		
		/*
		 * Cuando utilizamos el método insertString() del interface Document tenemos que coger
		 * la excepción BadLocationException (se genera cuando en un documento pedimos una posición
		 * que no existe).
		 */
		try{
			/*Metemos texto en el JTextPane.
			 * Los parámetros de entrada del método insertString son:
			 * 	- int offset Para indicar en que dirección del documento meter el texto.
			 * 	- String str El texto que queremos insertar.
			 * 	- AttributeSet a El estilo que le queremos aplicar al texto.
			 */
			panelTexto1.getStyledDocument().insertString(panelTexto1.getStyledDocument().getLength(), textValcom, normal);
			panelTexto1.getStyledDocument().insertString(panelTexto1.getStyledDocument().getLength(), textDahl, normal);
			panelTexto1.getStyledDocument().insertString(panelTexto1.getStyledDocument().getLength(), textMinvo, normal);
			panelTexto1.getStyledDocument().insertString(panelTexto1.getStyledDocument().getLength(), textPontan, normal);
			panelTexto1.getStyledDocument().insertString(panelTexto1.getStyledDocument().getLength(), textDoria, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textBomb, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textMuroDes, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textMuroIndes, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textPildora, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textDetona, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textPati, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textWall, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textSalva, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textVida, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textMulti, normal);
			panelTexto2.getStyledDocument().insertString(panelTexto2.getStyledDocument().getLength(), textPuerta, normal);
			panelTexto3.getStyledDocument().insertString(panelTexto3.getStyledDocument().getLength(), textReglas, normal);
			panelTexto4.getStyledDocument().insertString(panelTexto4.getStyledDocument().getLength(), textObjetivos, normal);
//			
//			//Nos colocamos en la última posición.
//			paneltexto.setCaretPosition(paneltexto.getStyledDocument().getLength());
//			
//			//Metemos ImageIcons en el JTextPane.
//			meterImagen("misil.gif", imgProt);
//			meterImagen("avion_pequeño.gif", imgProt);
//			meterImagen("avion_grande.gif", imgProt);
//			meterImagen("heroe.gif", imgProt);
//			
//			//Seguimos metiendo texto.
//			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), apartado2, apartado);
//			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), texto3, normal);
//			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), apartado3, apartado);
//			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), texto4, normal);
//			paneltexto.setCaretPosition(paneltexto.getStyledDocument().getLength());
//			paneltexto.setCaretPosition(paneltexto.getStyledDocument().getLength());
//			
//			//Metemos imagen.
//			meterImagen("teclas.jpg", imgProt);
//			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength() + 1, texto5, normal);
//			paneltexto.setCaretPosition(paneltexto.getStyledDocument().getLength());
//			meterImagen("barra.jpg", imgProt);
			
			//No queremos que el texto se pueda editar.
			panelTexto1.setEditable(false);
			panelTexto2.setEditable(false);
			panelTexto3.setEditable(false);
			panelTexto4.setEditable(false);
			
		}catch(BadLocationException e){
			JOptionPane.showMessageDialog(new JDialog(), "Error al intentar crear la ventana 'VentanaInstrucciones'","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		//Para que la barra del ScrollPane empiece por el principio ponemos CaretPosition a 0.
		panelTexto1.setCaretPosition(0);
		panelTexto2.setCaretPosition(0);
		panelTexto3.setCaretPosition(0);
		panelTexto4.setCaretPosition(0);
		
		//Metemos el JTextPane dentro del JScrollPane.
		scroll1 = new JScrollPane(panelTexto1);
		scroll2 = new JScrollPane(panelTexto2);
		scroll3 = new JScrollPane(panelTexto3);
		scroll4 = new JScrollPane(panelTexto4);
		
		jtPestañas.add("Personajes", scroll1);
		jtPestañas.add("Objetos", scroll2);
		jtPestañas.add("Reglas", scroll3);
		jtPestañas.add("Objetivos", scroll4);
		
		//Metemos el componente en en contenedor principal.
		getContentPane().add(jtPestañas);
		
		//Metemos nuestro parametros preferidos de la ventana.
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que
		 * tenga un tamaño máximo. De esta manera puede seguir funcionando
		 * a la perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(450, 200));
		this.setSize(500, 500);
		this.setModal(true);
		this.setTitle("Instrucciones");
		/*
		 * Como lo único que quiero es que está ventana se cierre cuando
		 * damos a la X, entonces con esta linea consigo que la ejecución
		 * de la ventana se pare. Muy útil si lo único que se quiere es
		 * cerrar la evntana.
		 */
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	/**
	 * Método que nos inserta las imagenes en el objeto JTextPane.
	 * @param nom - String, nombre de la imagen y formato
	 * @param miURL - Objeto URL para utilizar.
	 */
	private void meterImagen(String nom)
	{
		panelTexto1.insertIcon(new ImageIcon(AccesoImagen.getImagenBytes(nom)));
	}

	public static void main(String[] args) {
		VentanaInstrucciones v = new VentanaInstrucciones();
		v.setVisible(true);
	}

}
