package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoExtras {

	public static void setExtra(String nom, boolean estado){
		PreparedStatement stat;
		try {
			if(nom.equals("email"))
				stat = GestionBD.conectar().prepareStatement(
					"UPDATE EXTRAS SET EMAIL = ?;");
			else
				stat = GestionBD.conectar().prepareStatement(
				"UPDATE EXTRAS SET SONIDO = ?;");
			stat.setBoolean(1, estado);
			stat.executeUpdate();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getExtra(String nom){
		PreparedStatement stat;
		boolean esta = false;
		try {
			stat = GestionBD.conectar().prepareStatement(
			"SELECT * FROM EXTRAS;");
			ResultSet rs = stat.executeQuery();
			if(nom.equals("email"))
				esta = rs.getBoolean(3);
			else
				esta = rs.getBoolean(2);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return esta;
	}
}
