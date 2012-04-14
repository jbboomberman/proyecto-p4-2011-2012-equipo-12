package bomberman.ventanas;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

//Fig 14 P�gina 20.
public class VentanaInstrucciones extends JFrame{
	private JTabbedPane tP;
	private JPanel personajes;
	private JPanel objetos;
	private JPanel reglas;
	private JPanel objetivos;
	private Color c;
	
	
	public VentanaInstrucciones(){
		tP=new JTabbedPane();
		personajes=new JPanel(new GridLayout(5,1));
		objetos=new JPanel();
		reglas=new JPanel();
		objetivos=new JPanel();
		c=new Color(0, 0, 0, 0);
		
		
		
		this.setSize(600,500);
		this.setTitle("Instrucciones");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//TextAreas
		JTextArea tValcom=new JTextArea("� Valcom: Es el enemigo m�s simple.\n                   Se mover� despacio, f�cil de matar y tiene una puntuaci�n de 100 puntos.\n                   Simplemente rebotar� en los muros y nunca cambiar� de direcci�n.");
		tValcom.setBackground(c);
		tValcom.setEditable(false);
		JTextArea tDahl=new JTextArea("� Dahl: Algo m�s r�pido que los �Valcom� pero este s� cambiar� de vez en cuando de direcci�n.\n              Valdr� 200 puntos.");
		tDahl.setBackground(c);
		tDahl.setEditable(false);
		JTextArea tMinvo=new JTextArea("� Minvo: Este se mover� como los �Valcom�s pero ser� bastante m�s r�pido que todos los dem�s.\n                Valdr� 400 puntos.");
		tMinvo.setBackground(c);
		tMinvo.setEditable(false);
		JTextArea tPontan=new JTextArea("� Pontan: Se mover� a la misma velocidad que los �Valcom� pero ser� capaz de traspasar los destructibles.\n                  Valdr� 1000 puntos.");
		tPontan.setBackground(c);
		tPontan.setEditable(false);
		JTextArea tDoria=new JTextArea("� Doria: Se mover� m�s r�pido que los �Pontan� y podr� traspasar los muros destructibles.\n               Valdr� 2000 puntos.");
		tDoria.setBackground(c);
		tDoria.setEditable(false);
		personajes.add(tValcom);
		personajes.add(tDahl);
	    personajes.add(tMinvo);
	    personajes.add(tPontan);
	    personajes.add(tDoria);
		
		tP.addTab("Personajes",personajes);
		tP.addTab("Objetos", objetos);
		tP.addTab("Reglas", reglas);
		tP.addTab("Objetivos", objetivos);
		
		getContentPane().add(tP);
	}
	
	public static void main(String[]args){
		VentanaInstrucciones v=new VentanaInstrucciones();
		v.setVisible(true);
	}
	
}
