package bomberman.outin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import bomberman.managers.ManagerImagen;

public class LeerMapa {
	
	private static FileInputStream fichero;
	private static BufferedReader brF;
	private static Character inputLine2;
	private static ArrayList<Character> arListCharacter;
	
	public static ArrayList<Character> LeerMapaJuego(String nom){
		try{
		fichero = new FileInputStream(ManagerImagen.class.getClassLoader().getResource(
				"bomberman/resources/" + nom).getPath());
		brF = new BufferedReader( new InputStreamReader(fichero) );
		while((inputLine2 = (char)brF.read()) != null){
			arListCharacter.add(inputLine2);
		}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
			brF.close();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try{
				fichero.close();
				}catch(IOException e2){
					e2.printStackTrace();
				}
			}
		}
		return arListCharacter;
	}
}
