package ayakkabiproje0;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class anasayfa {
	int value;
	String ayk_id;
	String mark;
	String cins;
	String tur;
	String fiyatondata;
	int aykNo;
	int stokondata;
	int ayknoondata ;
	int sptfns;
	
	List<String> Sepet = new ArrayList<>();
	String eklenecek;
	Scanner anasayfamenu = new Scanner(System.in);
	public void Anasayfa(String email) throws SQLException {
		while(true) {
			System.out.println("------------------------------");
			System.out.println("ürünleri listelemek için 1");
			System.out.println("sepete eklemek için 2");
			System.out.println("Sepetinize gitmek için 3");			
			System.out.println("çıkış yapmak için 4");
			System.out.println("------------------------------");
			this.value = anasayfamenu.nextInt();
			
			//ürünleri listele
			if(value == 1) {
				var sql = "SELECT * FROM ayakkabi ORDER BY ayakkabi_id";
				try (var conn =  DB.connect(); var stmt = conn.createStatement()) {
					var rs = stmt.executeQuery(sql);
					while (rs.next()) {
				        ayk_id = rs.getString("ayakkabi_id");
				        mark = rs.getString("marka");
				        cins =rs.getString("cinsiyet");
				        tur = rs.getString("ayakkabi_turu");
				        fiyatondata = rs.getString("fiyat");
				        stokondata = rs.getInt("stok_durumu");
				        ayknoondata = rs.getInt("ayakkabi_no");
				        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
				        System.out.println("Ayakkabı id ="+ayk_id + "/" +"Ayakkabı Markası="+ mark + "/" + "Cinsiyet =" + cins + "/" +"Ayakkabı Türü =" + tur + "/" + "Fiyat" + fiyatondata +"tl"+ "/" +" stok durumu = " + stokondata +  "/" +" ayakkabı numarası = " + ayknoondata);
					}}
				 catch (SQLException e) {
				        e.printStackTrace();}}
			
			
			// sepete ürün ekleme
			if(value == 2) {
				System.out.println("Sepete eklemek istediğiniz ürünün id no'sunu girin" );
				eklenecek = anasayfamenu.next();
				Sepet.add(eklenecek);
				sepetClass sepetData = new sepetClass();
				sepetData.addSepet(email,eklenecek);
				
				System.out.println(eklenecek + " ürünü sepetinize eklendi.");
				
				}
			
			//sepet görüntüle
			if(value == 3) {
				if (Sepet.size()==0) {
					System.out.println("Sepetinizde ürün yok.");}
				else {
				System.out.println("Sepetteki ürünler : ");
				for (int i =0 ; i<Sepet.size();i++) {
					System.out.println(Sepet.get(i) + " id numaralı ayakabı");}}
				
				System.out.println("alışverişi tamamlamak için 1 \nanasayfaya dönmek için 2");
				sptfns = anasayfamenu.nextInt();
				if(sptfns == 1) {
					siparis Siparis = new siparis();
					Siparis.adresSec(email);
					Siparis.kargoSec();
					Siparis.odemeSec(ayk_id);
					Siparis.executeSiparis();
					}
				else if(sptfns == 2) {
					continue;
				}
			}
			
			
			// exit value
			if(value == 4 ) {
				break;
			}
		}
			
	}
	
}


