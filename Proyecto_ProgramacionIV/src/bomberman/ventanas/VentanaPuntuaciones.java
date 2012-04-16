package bomberman.ventanas;

import javax.swing.*;

//Fig 12 Página 17
public class VentanaPuntuaciones extends JFrame{

	public VentanaPuntuaciones(){
		this.setSize(300,300);
		
		JTextArea j=new JTextArea(3,2);
		j.setLineWrap(true);
		getContentPane().add(j);
	}
	public static void main(String[]args){
		VentanaPuntuaciones v=new VentanaPuntuaciones();
		v.setVisible(true);
	}
}
