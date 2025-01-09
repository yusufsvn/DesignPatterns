package ayakkabiproje0;

import java.sql.SQLException;
import java.util.Scanner;

public class girisyap {
	Scanner inputobject = new Scanner(System.in);
	String kullaniciTipi;
	StandartCustomer customer = new StandartCustomer();
	KurumsalCustomer customer1 = new KurumsalCustomer();
	anasayfa ana = new anasayfa();
	
	public void girisYap() throws SQLException {
		
		System.out.println("standart için: 0 kurumsal için: 1");
		kullaniciTipi=inputobject.next();
		//standart ve kurumsal kullanıcı giriş
		if(kullaniciTipi.equalsIgnoreCase("0")) {	
			
			//email input 
			System.out.println("email girin");
			String email = inputobject.next();
			//password input
			System.out.println("şifre girin");
			String pass = inputobject.next();
			
			customer.girisYap(email, pass);
			if(customer.verikontrol()==1) {
				System.out.println("giriş başarılı");				
				ana.Anasayfa(email);
	
				}
			
			if(customer.verikontrol()==0) {
				System.out.println("şifre yanlış");
			}
			if(customer.verikontrol()==-1) {
				System.out.println("kullanıcı bulunamadı");
				}
		}
		else if(kullaniciTipi.equalsIgnoreCase("1")) {
			//email input 
			System.out.println("email girin");
			String email = inputobject.next();
			//password input
			System.out.println("şifre girin");
			String pass = inputobject.next();
			
			customer1.girisYap(email, pass);
			if(customer1.veriDogrula()==1) {
				System.out.println("giriş başarılı");
				ana.Anasayfa(email);}
				//anasayfaya geçsin
			
			if(customer1.veriDogrula()==0) {
				System.out.println("şifre yanlış");
			}
			if(customer1.veriDogrula()==-1) {
				System.out.println("kullanıcı bulunamadı");
				}
		}
		//admin kullanıcı tipi ise admin menusu
		else if(kullaniciTipi.equalsIgnoreCase("admin")) {
			admin adminX = new admin();
			adminX.adminAnasayfamet();
			
		}
		else {
			hata s = new hata();
			s.hatax();
		}
	}
}
