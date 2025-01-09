package ayakkabiproje0;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException{
    	String giris_degeri;
    	try (Scanner inputobject = new Scanner(System.in)) {
    		musteriFacade facade = new musteriFacade();
			
			System.out.println("Giriş yapmak için 1\nKayıt olmak için 2");
			giris_degeri=inputobject.next();

			//giriş yap
			if(giris_degeri.equalsIgnoreCase("1")) {
				facade.handleGiris();	
			}
			// kayıt ol
			else if(giris_degeri.equalsIgnoreCase("2")) {
				facade.handleKayit();
			}	
			//hatalı giriş
			else {
				facade.handleHata();
			}
		}
    	
    }
}