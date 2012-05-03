package bomberman.ventanas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import bomberman.database.AccesoImagen;

/**
 * Ventana que nos mostrar� las instrucciones del juego.
 * @author David
 * @version 1.0
 */
public class VentanaInstrucciones extends JDialog{
	
	private static final long serialVersionUID = -7461719037402108362L;
	private JTextPane paneltexto;
	private JScrollPane scroll;
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
	private JTabbedPane jtPesta�as;

	/**
	 * Constructor principal de la clase VentanaInstrucciones.
	 */
	public VentanaInstrucciones()
	{
		paneltexto = new JTextPane();
		
		//Inicializamos los String con sus respectivas frases.
		textValcom = "     - Valcom: Es el enemigo m�s simple. Se mover� despacio, f�cil de matar y tiene una puntuaci�n de 100 puntos. Simplemente rebotar� en los muros y nunca cambiar� de direcci�n.\n";
		textDahl = "        � Dahl: Algo m�s r�pido que los del tipo �Valcom� pero este s� cambiar� de vez en cuando de direcci�n. Valdr� 200 puntos.\n";
		textMinvo = "        � Minvo: Este se mover� como los �Valcom�s pero ser� bastante m�s r�pido que todos los dem�s. Valdr� 400 puntos.\n";
		textPontan = "        � Pontan: Se mover� a la misma velocidad que los �Valcom� pero ser� capaz de traspasar los destructibles. Valdr� 1000 puntos.\n";
		textDoria = "        � Doria: Se mover� m�s r�pido que los �Pontan� y podr� traspasar los muros destructibles. Valdr� 2000 puntos.\n";
		textBomb = "        � Bombas: La explosi�n de estas es lo que lograr� acabar con los enemigos y destruir los muros destructibles.\n";
		textMuroDes = "        � Muros destructibles: Muros que el personaje no puede traspasar pero si destruir con las bombas.\n";
		textMuroIndes = "        � Muros indestructibles: Muros que el personaje no puede ni traspasar ni destruir con las bombas.\n";
		textPildora = "        � P�ldoras: Al cogerlas obtendremos habilidades especiales.\n";
		textDetona = "        � Detonador: Te permite hacer explotar la bomba cuando t� quieras mientras no haya explotado ella sola.\n";
		textPati = "        � Patines: Te permiten correr el doble de r�pido.\n";
		textWall = "        � Wall-walker: Te permite atravesar los muros que sean de tipo destruible.\n";
		textSalva = "        � SalvaLlama: Te hace inmune a las explosiones de tus bombas durante 30 segundos.\n";
		textVida = "        � Vida: Te da una vida m�s.\n";
		textMulti = "        � MultiBomba: Aumenta en 1 el n�mero de bombas que puedes poner a la vez.\n";
		textPuerta = "        � Puertas: Estar�n escondidas debajo de los muros destructibles. Hay que tener en cuenta que las puertas pueden ser destruidas por las bombas lo cual impedir� que nos pasemos el nivel.\n";
		jtPesta�as = new JTabbedPane();
		
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
		 * Cuando utilizamos el m�todo insertString() del interface Document tenemos que coger
		 * la excepci�n BadLocationException (se genera cuando en un documento pedimos una posici�n
		 * que no existe).
		 */
		try{
			/*Metemos texto en el JTextPane.
			 * Los par�metros de entrada del m�todo insertString son:
			 * 	- int offset Para indicar en que direcci�n del documento meter el texto.
			 * 	- String str El texto que queremos insertar.
			 * 	- AttributeSet a El estilo que le queremos aplicar al texto.
			 */
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textValcom, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textDahl, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textMinvo, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textPontan, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textDoria, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textBomb, normal);
			paneltexto.setCaretPosition(paneltexto.getStyledDocument().getLength());
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textMuroDes, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textMuroIndes, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textPildora, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textDetona, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textPati, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textWall, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textSalva, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textVida, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textMulti, normal);
			paneltexto.getStyledDocument().insertString(paneltexto.getStyledDocument().getLength(), textPuerta, normal);
//			
//			//Nos colocamos en la �ltima posici�n.
//			paneltexto.setCaretPosition(paneltexto.getStyledDocument().getLength());
//			
//			//Metemos ImageIcons en el JTextPane.
//			meterImagen("misil.gif", imgProt);
//			meterImagen("avion_peque�o.gif", imgProt);
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
			paneltexto.setEditable(false);
		}catch(BadLocationException e){
			JOptionPane.showMessageDialog(new JDialog(), "Error al intentar crear la ventana 'VentanaInstrucciones'","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		//Para que la barra del ScrollPane empiece por el principio ponemos CaretPosition a 0.
		paneltexto.setCaretPosition(0);
		//Metemos el JTextPane dentro del JScrollPane.
		scroll = new JScrollPane(paneltexto);
		jtPesta�as.add(scroll);
		//Metemos el componente en en contenedor principal.
		getContentPane().add(jtPesta�as);
		
		//Metemos nuestro parametros preferidos de la ventana.
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que
		 * tenga un tama�o m�ximo. De esta manera puede seguir funcionando
		 * a la perfecci�n en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(450, 200));
		this.setSize(500, 500);
		this.setModal(true);
		this.setTitle("Instrucciones");
		/*
		 * Como lo �nico que quiero es que est� ventana se cierre cuando
		 * damos a la X, entonces con esta linea consigo que la ejecuci�n
		 * de la ventana se pare. Muy �til si lo �nico que se quiere es
		 * cerrar la evntana.
		 */
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	/**
	 * M�todo que nos inserta las imagenes en el objeto JTextPane.
	 * @param nom - String, nombre de la imagen y formato
	 * @param miURL - Objeto URL para utilizar.
	 */
	private void meterImagen(String nom)
	{
		paneltexto.insertIcon(new ImageIcon(AccesoImagen.getImagenBytes(nom)));
	}

	public static void main(String[] args) {
		VentanaInstrucciones v = new VentanaInstrucciones();
		v.setVisible(true);
	}

}
