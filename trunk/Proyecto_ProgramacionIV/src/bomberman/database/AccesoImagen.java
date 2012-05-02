package bomberman.database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import bomberman.database.GestionBD;

public class AccesoImagen {

	public static void insertarImagen(BufferedImage imagen, String nombre, String formato) {
		PreparedStatement stat;
		byte[] imageDatos;
		try {
			stat = GestionBD.conectar().prepareStatement("INSERT INTO IMAGEN VALUES(?, ?);");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( imagen, formato, baos );
			baos.flush();
			imageDatos = baos.toByteArray();
			baos.close();
			stat.setString(1, nombre);
			stat.setBytes(2, imageDatos);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getImagen(String nombre) {
		byte[] imageDatos;
		BufferedImage bImageFromConvert = null;

		try {
			PreparedStatement stat = GestionBD.conectar()
					.prepareStatement("SELECT * FROM IMAGEN WHERE NOMBRE = ?");
			stat.setString(1, nombre);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				imageDatos = rs.getBytes(2);
			}
			else
				return null;
			InputStream in = new ByteArrayInputStream(imageDatos);
			try{
				bImageFromConvert = ImageIO.read(in);
			}catch(IOException e){
				e.printStackTrace();
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bImageFromConvert;
	}
	
	public static void elimImagen(String nom){
		try {
			PreparedStatement stat = GestionBD.conectar()
					.prepareStatement("DELETE FROM IMAGEN WHERE NOMBRE = ?");
			stat.setString(1, nom);
			stat.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
