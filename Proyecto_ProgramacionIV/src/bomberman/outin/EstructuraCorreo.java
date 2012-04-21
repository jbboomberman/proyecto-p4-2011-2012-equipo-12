package bomberman.outin;

public class EstructuraCorreo {

	public static String getEstructura(String nomJug){
		String texto = "<html> Estimado Sr/Sra " + nomJug + "Usted" +
		"jugó una partida al videojuego BombermanAddict  la fecha de FECHA	" +
		"en la que obtuvo la siguiente puntuación. Gracias por particpar. </html>";
		
		return texto;
	}
}
