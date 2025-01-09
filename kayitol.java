package ayakkabiproje0;

import java.util.Scanner;

public class kayitol {
	String kullaniciTipi;
	KurumsalCustomer customer1 = new KurumsalCustomer();
	StandartCustomer customer = new StandartCustomer();
	Scanner inputobject = new Scanner(System.in);
	
	public void Kayitol() {
	
		//email input 
		System.out.println("email girin");
		String email = inputobject.next();
		//password input
		System.out.println("şifre girin");
		String password = inputobject.next();
		//isim input
		System.out.println("isim girin");
		String isim = inputobject.next();
		//soyisim input
		System.out.println("soyisim girin");
		String soyisim = inputobject.next();
		//adres input
		System.out.println("adres girin");
		String adres = inputobject.next();
		//telefon input
		System.out.println("telefon girin");
		String telefon = inputobject.next();
		
		// eğer kurumsalsa şirket ismi eklesin
		if(kullaniciTipi.equalsIgnoreCase("1") ) {
			customer1.kayitOl(email,isim,soyisim,password,telefon,adres,kullaniciTipi);
			
		}
		// normal ise normal kayıt ol 
		else if(kullaniciTipi.equalsIgnoreCase("0") ) {
			customer.kayitOl(email, isim, soyisim, password, telefon, adres,kullaniciTipi);
		}
		else {
			hata ss = new hata();
			ss.hatax();
		}
	}
}
