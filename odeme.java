package ayakkabiproje0;

import java.util.Scanner;

interface paymentStrategy { 
	public void pay(String email);
	public String returnOdeme();
}


class bankkartOdeme implements paymentStrategy{
	String kartNo;
	String cvv; //guvenlik no
	String ay;  //son kullanma tarihi ay
	String yil;	//son kullanma tarihi yıl
	String isimSoyad; //kart üzerindeki isim soyisim
	Scanner inputObj = new Scanner(System.in);
	
	public void pay(String urunId) {
		//input alma
		System.out.println("kart no girin");
		kartNo = inputObj.next();
		System.out.println("güvenlik kodunu girin");
		cvv = inputObj.next();
		System.out.println("son kullanma tarihi ay");
		ay = inputObj.next();
		System.out.println("yıl");
		yil = inputObj.next();
		System.out.println("kart üzerindeki isim");
		isimSoyad = inputObj.next();
		

		
		System.out.println("siparişiniz alındı");
	}
	public String returnOdeme() {
		return "Banka kartı";
	}
	
}
class kredikartOdeme implements paymentStrategy{
	final int maxTaksit =6;
	String kartNo;
	String cvv; //guvenlik no
	String ay;  //son kullanma tarihi ay
	String yil;	//son kullanma tarihi yıl
	String isimSoyad; //kart üzerindeki isim soyisim
	int taksitSay;
	Scanner inputObj = new Scanner(System.in);
	public void pay(String email) {
		//input alma
		System.out.println("kart no girin");
		kartNo = inputObj.next();
		System.out.println("güvenlik kodunu girin");
		cvv = inputObj.next();
		System.out.println("son kullanma tarihi ay");
		ay = inputObj.next();
		System.out.println("yıl");
		yil = inputObj.next();
		System.out.println("kart üzerindeki isim");
		isimSoyad = inputObj.next();

		System.out.println("siparişiniz alındı");
		

	}
	public String returnOdeme() {
		return "Kredi kartı";
	}
	
}