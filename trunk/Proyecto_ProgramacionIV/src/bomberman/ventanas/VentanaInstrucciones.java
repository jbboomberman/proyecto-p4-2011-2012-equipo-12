package bomberman.ventanas;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

//Fig 14 Página 20.
public class VentanaInstrucciones extends JFrame {
	private JTabbedPane tP;
	private JPanel personajes;
	private JPanel objetos;
	private JPanel reglas;
	private JPanel objetivos;
	private Color c;
	private JScrollPane sPersonajes;
	private JTextPane panelTexto;
	
	
	
	public VentanaInstrucciones() {

		tP = new JTabbedPane();
		personajes = new JPanel();
		personajes.setLayout(new BoxLayout(personajes,BoxLayout.Y_AXIS));
		objetos = new JPanel();
		objetos.setLayout(new BoxLayout(objetos,BoxLayout.Y_AXIS));
		reglas = new JPanel(new BorderLayout());
		objetivos = new JPanel(new BorderLayout());
		c=personajes.getBackground();
		panelTexto = new JTextPane();

		this.setSize(600, 500);
		this.setTitle("Instrucciones");
		this.setLocationRelativeTo(null);

		//TextAreas PERSONAJES
		JTextArea tValcom=new JTextArea("\n        – Valcom: Es el enemigo más simple. Se moverá despacio, fácil de matar y tiene una puntuación de 100 puntos. Simplemente rebotará en los muros y nunca cambiará de dirección.");
     	tValcom.setBackground(c);
		tValcom.setLineWrap(true);
		tValcom.setWrapStyleWord(true);
		tValcom.setEditable(false);
		tValcom.setOpaque(true);

		JTextArea tDahl=new JTextArea("        – Dahl: Algo más rápido que los del tipo ‘Valcom’ pero este sí cambiará de vez en cuando de dirección. Valdrá 200 puntos.");
		tDahl.setBackground(c);
		tDahl.setLineWrap(true);
		tDahl.setWrapStyleWord(true);
		tDahl.setEditable(false);

		JTextArea tMinvo=new JTextArea("        – Minvo: Este se moverá como los ‘Valcom’s pero será bastante más rápido que todos los demás. Valdrá 400 puntos.");
		tMinvo.setBackground(c);
		tMinvo.setLineWrap(true);
		tMinvo.setWrapStyleWord(true);
		tMinvo.setEditable(false);

		JTextArea tPontan=new JTextArea("        – Pontan: Se moverá a la misma velocidad que los ‘Valcom’ pero será capaz de traspasar los destructibles. Valdrá 1000 puntos.");
		tPontan.setBackground(c);
		tPontan.setLineWrap(true);
		tPontan.setWrapStyleWord(true);
		tPontan.setEditable(false);

		JTextArea tDoria=new JTextArea("        – Doria: Se moverá más rápido que los ‘Pontan’ y podrá traspasar los muros destructibles. Valdrá 2000 puntos.");
		tDoria.setBackground(c);
		tDoria.setLineWrap(true);
		tDoria.setWrapStyleWord(true);
		tDoria.setEditable(false);

		
		//TextAreas OBJETOS
		JTextArea tB=new JTextArea("\n        – Bombas: La explosión de estas e lo que logrará acabar con los enemigos y destruir los muros destructibles.");
		tB.setBackground(c);
		tB.setLineWrap(true);
		tB.setWrapStyleWord(true);
		tB.setEditable(false);
		JTextArea tMD=new JTextArea("        – Muros destructibles: Muros que el personaje no puede traspasar pero si destruir con las bombas.");
		tMD.setBackground(c);
		tMD.setLineWrap(true);
		tMD.setWrapStyleWord(true);
		tMD.setEditable(false);
		JTextArea tMI=new JTextArea("        – Muros indestructibles: Muros que el personaje no puede ni traspasar ni destruir con las bombas.");
		tMI.setBackground(c);
		tMI.setLineWrap(true);
		tMI.setWrapStyleWord(true);
		tMI.setEditable(false);
		JTextArea tPi=new JTextArea("        – Píldoras: Al cogerlas obtendremos habilidades especiales.");
		tPi.setBackground(c);
		tPi.setLineWrap(true);
		tPi.setWrapStyleWord(true);
		tPi.setEditable(false);
		JTextArea tDet=new JTextArea("        – Detonador: Te permite hacer explotar la bomba cuando tú quieras mientras no haya explotado ella sola.");
		tDet.setBackground(c);
		tDet.setLineWrap(true);
		tDet.setWrapStyleWord(true);
		tDet.setEditable(false);
		JTextArea tPat=new JTextArea("        – Patines: Te permiten correr el doble de rápido.");
		tPat.setBackground(c);
		tPat.setLineWrap(true);
		tPat.setWrapStyleWord(true);
		tPat.setEditable(false);
		JTextArea tW=new JTextArea("        – Wall-walker: Te permite atravesar los muros que sean de tipo destruible.");
		tW.setBackground(c);
		tW.setLineWrap(true);
		tW.setWrapStyleWord(true);
		tW.setEditable(false);
		JTextArea tSal=new JTextArea("        – SalvaLlama: Te hace inmune a las explosiones de tus bombas durante 30 segundos.");
		tSal.setBackground(c);
		tSal.setLineWrap(true);
		tSal.setWrapStyleWord(true);
		tSal.setEditable(false);
		JTextArea tVi=new JTextArea("        – Vida: Te da una vida más.");
		tVi.setBackground(c);
		tVi.setLineWrap(true);
		tVi.setWrapStyleWord(true);
		tVi.setEditable(false);
		JTextArea tMB=new JTextArea("        – MultiBomba: Aumenta en 1 el número de bombas que puedes poner a la vez.");
		tMB.setBackground(c);
		tMB.setLineWrap(true);
		tMB.setWrapStyleWord(true);
		tMB.setEditable(false);
		JTextArea tPu=new JTextArea("        – Puertas: Estarán escondidas debajo de los muros destructibles. Hay que tener en cuenta que las puertas pueden ser destruidas por las bombas lo cual impedirá que nos pasemos el nivel.");
		tPu.setBackground(c);
		tPu.setLineWrap(true);
		tPu.setWrapStyleWord(true);
		tPu.setEditable(false);
		
		//TextArea REGLAS
		
		JTextArea tReglas=new JTextArea("\n        El personaje principal es un robot al cual le han contado que si sale de las entrañas de la tierra y sale a la superficie logrará convertirse en humano. Por este motivo, el objetivo del protagonista es buscar las salidas que le llevarán a la superficie, tendrá que superar todos los niveles para llegar a la superficie. Aun así, necesitará matar a todos los enemigos de cada nivel para que las puertas queden abiertas, las cuales estarán escondidas debajo de los muros destructibles. El jugador empezará con tres vidas y en caso de que sea alcanzado por una bomba o monstruo perderá una de ellas. También perderá una vida en caso de que destruya la única puerta de salida que hay en cada nivel.");
		tReglas.setBackground(c);
		tReglas.setLineWrap(true);
		tReglas.setWrapStyleWord(true);
		tReglas.setEditable(false);
		
		//TextArea OBJETIVOS
		
		JTextArea tObjetivos=new JTextArea("\n        El objetivo del personaje principal (un robot), será escapar de las entrañas de la tierra porque corre el rumor de que los robots que consigan llegar a la superficie se convertirán en humanos. Para lograr este objetivo deberán pasar por distintos laberintos donde tendrá que matar a todos los enemigos y buscar la puerta que le lleve al próximo nivel, cada nivel tendrá un tiempo máximo para ser completado.");
		tObjetivos.setBackground(c);
		tObjetivos.setLineWrap(true);
		tObjetivos.setWrapStyleWord(true);
		tObjetivos.setEditable(false);
		
		//IMAGENES PERSONAJES
		
		//Imagen Valcom
		ImageIcon i=new ImageIcon("src/bomberman/resources/valcom_inst.png");
		JPanel jV=new JPanel();
		JLabel jL=new JLabel();
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

		
	
		//IMAGENES OBJETOS
		
		//Imagen bomba
		ImageIcon ibomba=new ImageIcon("src/bomberman/resources/bomba_inst.png");
		JPanel pBomba=new JPanel();
		JLabel lBomba=new JLabel();
		lBomba.setIcon(ibomba);
		pBomba.add(lBomba);
		
		//Imagen muro destructible
		ImageIcon iMD=new ImageIcon("src/bomberman/resources/muro_dest.png");
		JPanel pMD=new JPanel();
		JLabel lMD=new JLabel();
		lMD.setIcon(iMD);
		pMD.add(lMD);
		
		//Imagen muro indestructible
		ImageIcon iMI=new ImageIcon("src/bomberman/resources/muro_indest.png");
		JPanel pMI=new JPanel();
		JLabel lMI=new JLabel();
		lMI.setIcon(iMI);
		pMI.add(lMI);
		
		//Imagen detonador
		ImageIcon iDet=new ImageIcon("src/bomberman/resources/detonador.png");
		JPanel pDet=new JPanel();
		JLabel lDet=new JLabel();
		lDet.setIcon(iDet);
		pDet.add(lDet);
		
		//Imagen multi bomba
		ImageIcon iMB=new ImageIcon("src/bomberman/resources/multi_bomba.png");
		JPanel pMB=new JPanel();
		JLabel lMB=new JLabel();
		lMB.setIcon(iMB);
		pMB.add(lMB);
		
		//Imagen patines
		ImageIcon iPat=new ImageIcon("src/bomberman/resources/patines.png");
		JPanel pPat=new JPanel();
		JLabel lPat=new JLabel();
		lPat.setIcon(iPat);
		pPat.add(lPat);
		
		//Imagen salva llama
		ImageIcon iSal=new ImageIcon("src/bomberman/resources/salva_llama.png");
		JPanel pSal=new JPanel();
		JLabel lSal=new JLabel();
		lSal.setIcon(iSal);
		pSal.add(lSal);
		
		//Imagen vida
		ImageIcon iVi=new ImageIcon("src/bomberman/resources/vida.png");
		JPanel pVi=new JPanel();
		JLabel lVi=new JLabel();
		lVi.setIcon(iVi);
		pVi.add(lVi);
		
		//Imagen wall walker
		ImageIcon iW=new ImageIcon("src/bomberman/resources/wall_walker.png");
		JPanel pW=new JPanel();
		JLabel lW=new JLabel();
		lW.setIcon(iW);
		pW.add(lW);
		
		
		
	
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
	    sPersonajes =new JScrollPane(personajes);
	    
	    
	    // Objetos
	    
	    objetos.add(tB);
	    objetos.add(pBomba);
	    objetos.add(tMD);
	    objetos.add(pMD);
	    objetos.add(tMI);
	    objetos.add(pMI);
	    objetos.add(tPi);
	    objetos.add(tDet);
	    objetos.add(pDet);
	    objetos.add(tPat);
	    objetos.add(pPat);
	    objetos.add(tW);
	    objetos.add(pW);
	    objetos.add(tSal);
	    objetos.add(pSal);
	    objetos.add(tVi);
	    objetos.add(pVi);
	    objetos.add(tMB);
	    objetos.add(pMB);
		JScrollPane sObjetos=new JScrollPane(objetos);
		
		// Reglas
		
		reglas.add(tReglas);
		
		// Objetivos
		
		objetivos.add(tObjetivos);

		tP.addTab("Personajes", sPersonajes);
		tP.addTab("Objetos", sObjetos);
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
