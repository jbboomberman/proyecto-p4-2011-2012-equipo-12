package bomberman.ventanas;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

//Fig 14 Página 20.
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
		JTextArea tValcom=new JTextArea("– Valcom: Es el enemigo más simple.\n                   Se moverá despacio, fácil de matar y tiene una puntuación de 100 puntos.\n                   Simplemente rebotará en los muros y nunca cambiará de dirección.");
		tValcom.setBackground(c);
		tValcom.setEditable(false);
		JTextArea tDahl=new JTextArea("– Dahl: Algo más rápido que los ‘Valcom’ pero este sí cambiará de vez en cuando de dirección.\n              Valdrá 200 puntos.");
		tDahl.setBackground(c);
		tDahl.setEditable(false);
		JTextArea tMinvo=new JTextArea("– Minvo: Este se moverá como los ‘Valcom’s pero será bastante más rápido que todos los demás.\n                Valdrá 400 puntos.");
		tMinvo.setBackground(c);
		tMinvo.setEditable(false);
		JTextArea tPontan=new JTextArea("– Pontan: Se moverá a la misma velocidad que los ‘Valcom’ pero será capaz de traspasar los destructibles.\n                  Valdrá 1000 puntos.");
		tPontan.setBackground(c);
		tPontan.setEditable(false);
		JTextArea tDoria=new JTextArea("– Doria: Se moverá más rápido que los ‘Pontan’ y podrá traspasar los muros destructibles.\n               Valdrá 2000 puntos.");
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
