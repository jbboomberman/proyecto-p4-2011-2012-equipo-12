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

/**
 * Esta clase encarga de gestionar la tabla Imagen.
 * @author David
 * @version 1.0
 */
public class AccesoImagen {

	/**
	 * Este método inserta la imagen que se recibe como parámetro
	 * con su parámetro y el nombre que tendrá.
	 * @param imagen - BufferedImage
	 * @param nombre - String
	 * @param formato - String
	 */
	public static void insertarImagen(BufferedImage imagen, String nombre, String formato) {
		PreparedStatement stat;
		//Array de bytes donde se pasará la imagen.
		byte[] imageDatos;
		try {
			stat = GestionBD.conectar().prepareStatement("INSERT INTO IMAGEN VALUES(?, ?);");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( imagen, formato, baos );
			baos.flush();
			//La imagen ya está en el array.
			imageDatos = baos.toByteArray();
			baos.close();
			//La pasamos a la tabla
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
	
	/**
	 * Se encarga de devolver la imagen que se le ha
	 * pedido por nombre.
	 * @param nombre - String
	 * @return BufferedImage
	 */
	public static BufferedImage getImagen(String nombre) {
		//Array de bytes donde se pasará la imagen.
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
				//Ya está en el BufferedImage
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
	
	/**
	 * Eliminamos una imagen que especifiquemos por
	 * su nombre bajo los parámetros de entrada.
	 * @param nom - String
	 */
	public static void elimImagen(String nom){
		try {
			PreparedStatement stat = GestionBD.conectar()
					.prepareStatement("DELETE FROM IMAGEN WHERE NOMBRE = ?");
			stat.setString(1, nom);
			stat.executeUpdate();
			GestionBD.desconectar();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve una imagen en formato array de bytes.
	 * @param nombre - String
	 * @return byte[]
	 */
	public static byte[] getImagenBytes(String nombre){
		byte[] imageDatos = null;

		try {
			PreparedStatement stat = GestionBD.conectar()
					.prepareStatement("SELECT * FROM IMAGEN WHERE NOMBRE = ?");
			stat.setString(1, nombre);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				imageDatos = rs.getBytes(2);
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageDatos;
	}
}
