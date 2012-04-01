package bomberman.outin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompCorreo {

	public static boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern
				.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			System.out.println("[" + mat.group() + "]");
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(CompCorreo.isEmail("davi@dgmail.com"));
	}

}
