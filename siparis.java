package ayakkabiproje0;

import java.sql.SQLException;
import java.util.Scanner;

abstract class siparisAbst{
	String kargoFirma;
	String siparisAdres;
	String siparisDate;
	String odemeYontemi;
	
	abstract void kargoSec();
	abstract void adresSec(String email) throws SQLException;
	abstract void odemeSec(String urunId);
	//veString siparisAdres;ritabanı işlemleri yapsın
	abstract void executeSiparis();
}


class siparis extends siparisAbst{
	Scanner inputObj = new Scanner(System.in);
	
	void kargoSec() {
		System.out.println("istediğiniz kargo firması adını girin");
		kargoFirma = inputObj.next();
	}
	void adresSec(String email) throws SQLException {
			System.out.println("yeni adresi girin");
			siparisAdres = inputObj.next();
	}
	
	void odemeSec(String urunId) {
		Context con = new Context();
		System.out.println("ödeme yöntemi seçin\nbanka kartı:0\nkredi kartı:1");
		String odemeValue = inputObj.next();
		if(odemeValue.equals("0")) {
			con.setStrategy(new bankkartOdeme());
			bankkartOdeme x = new bankkartOdeme();
			odemeYontemi = x.returnOdeme();
			
		}
		else if(odemeValue.equals("1")) {
			con.setStrategy(new kredikartOdeme());
			kredikartOdeme y = new kredikartOdeme();
			odemeYontemi = y.returnOdeme();
		}
		else {
			System.out.println("hatalı giris");
		}
		
		con.pay(urunId);
		
	}
	void executeSiparis() {
		siparisData data = new siparisData();
		data.exctSiparis(siparisAdres,kargoFirma,odemeYontemi);
		
	}


}
