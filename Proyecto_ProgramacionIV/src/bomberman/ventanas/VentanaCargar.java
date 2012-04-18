package bomberman.ventanas;

import javax.swing.JTable;

//Fig 11  Página 15.  Tiene una JTable.
public class VentanaCargar {

	private JTable jtPuntu;
	
	public VentanaCargar(){
		jtPuntu = new JTable(new TableModelPuntuaciones(5, 5));
	}
}
