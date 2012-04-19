package bomberman.ventanas;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

//Fig 14 P�gina 20.
public class VentanaInstrucciones extends JFrame {
	private JTabbedPane tP;
	private JPanel personajes;
	private JPanel objetos;
	private JPanel reglas;
	private JPanel objetivos;

	// private Color c;

	public VentanaInstrucciones() {

		tP = new JTabbedPane();
		personajes = new JPanel(new GridLayout(10, 1));
		objetos = new JPanel();
		reglas = new JPanel();
		objetivos = new JPanel();
		// c=new Color(0, 0, 0, 0);

		this.setSize(600, 500);
		this.setTitle("Instrucciones");
		this.setLocationRelativeTo(null);

		// TextAreas
		JTextArea tValcom = new JTextArea(
				"� Valcom: Es el enemigo m�s simple. Se mover� despacio, f�cil de matar y tiene una puntuaci�n de 100 puntos. Simplemente rebotar� en los muros y nunca cambiar� de direcci�n.");
		// tValcom.setBackground(c);
		tValcom.setLineWrap(true);
		tValcom.setWrapStyleWord(true);
		tValcom.setEditable(false);
		JTextArea tDahl = new JTextArea(
				"� Dahl: Algo m�s r�pido que los del tipo �Valcom� pero este s� cambiar� de vez en cuando de direcci�n. Valdr� 200 puntos.");
		// tDahl.setBackground(c);
		tDahl.setLineWrap(true);
		tDahl.setWrapStyleWord(true);
		tDahl.setEditable(false);
		JTextArea tMinvo = new JTextArea(
				"� Minvo: Este se mover� como los �Valcom�s pero ser� bastante m�s r�pido que todos los dem�s. Valdr� 400 puntos.");
		// tMinvo.setBackground(c);
		tMinvo.setLineWrap(true);
		tMinvo.setWrapStyleWord(true);
		tMinvo.setEditable(false);
		JTextArea tPontan = new JTextArea(
				"� Pontan: Se mover� a la misma velocidad que los �Valcom� pero ser� capaz de traspasar los destructibles. Valdr� 1000 puntos.");
		// tPontan.setBackground(c);
		tPontan.setLineWrap(true);
		tPontan.setWrapStyleWord(true);
		tPontan.setEditable(false);
		JTextArea tDoria = new JTextArea(
				"� Doria: Se mover� m�s r�pido que los �Pontan� y podr� traspasar los muros destructibles. Valdr� 2000 puntos.");
		// tDoria.setBackground(c);
		tDoria.setLineWrap(true);
		tDoria.setWrapStyleWord(true);
		tDoria.setEditable(false);

		// Imagen Valcom
		ImageIcon i = new ImageIcon("src/bomberman/resources/valcom_inst.png");
		JPanel jV = new JPanel();
		JLabel jL = new JLabel();
		jL.setIcon(i);
		jV.add(jL);

		// Imagen dahl
		ImageIcon i2 = new ImageIcon("src/bomberman/resources/dahl_inst.png");
		JPanel jD = new JPanel();
		JLabel jL2 = new JLabel();
		jL2.setIcon(i2);
		jD.add(jL2);

		// Imagen Minvo
		ImageIcon i3 = new ImageIcon("src/bomberman/resources/minvo_inst.png");
		JPanel jM = new JPanel();
		JLabel jL3 = new JLabel();
		jL3.setIcon(i3);
		jM.add(jL3);

		// Imagen Pontan
		ImageIcon i4 = new ImageIcon("src/bomberman/resources/pontan_inst.png");
		JPanel jP = new JPanel();
		JLabel jL4 = new JLabel();
		jL4.setIcon(i4);
		jP.add(jL4);

		// Imagen Doria
		ImageIcon i5 = new ImageIcon("src/bomberman/resources/doria_inst.png");
		JPanel jDo = new JPanel();
		JLabel jL5 = new JLabel();
		jL5.setIcon(i5);
		jDo.add(jL5);

		// Personajes
		personajes.add(tValcom);
		personajes.add(jV);
		personajes.add(tDahl);
		personajes.add(jD);
		personajes.add(tMinvo);
		personajes.add(jM);
		personajes.add(tPontan);
		personajes.add(jP);
		personajes.add(tDoria);
		personajes.add(jDo);
		JScrollPane sPersonajes = new JScrollPane(personajes);

		tP.addTab("Personajes", sPersonajes);
		tP.addTab("Objetos", objetos);
		tP.addTab("Reglas", reglas);
		tP.addTab("Objetivos", objetivos);

		getContentPane().add(tP);
		this.setVisible(false);

	}

	public static void main(String[] args) {
		VentanaInstrucciones v = new VentanaInstrucciones();
		v.setVisible(true);
	}

}
