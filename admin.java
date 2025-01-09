package ayakkabiproje0;
import java.util.Scanner;

public class admin {
	String urunId;
	int inputValue;
	Scanner obj = new Scanner(System.in);
	adminAnasayfa admin = new adminAnasayfa();
	
	public void adminAnasayfamet() {
		while(true) {
		System.out.println("-----------------------------------------------");
		System.out.println("stok eklemek :1");
		System.out.println("stok azaltmak :2");
		System.out.println("stok sorgula :3");
		System.out.println("Urun sil :4");
		System.out.println("Urun ekle :5");
		System.out.println("Fiyat güncelleme :6");
		System.out.println("yapmak istediğiniz işlemin numarasını giriniz");
		
		inputValue = obj.nextInt();
		
		//verileri listelere aktarıyor
		admin.databasetolist();
		
				
			if(inputValue == 1) {
				System.out.println("ürün id sini girin");
				admin.urunId = obj.next();
				System.out.println("arttırma miktarını girin");
				int ekleSayi = obj.nextInt();
				admin.stokEkle(admin.urunId,ekleSayi);
				admin.listtoDatabase();
				
				
			}
			else if(inputValue == 2) {
				System.out.println("ürün id sini girin");
				admin.urunId = obj.next();
				System.out.println("azaltma miktarını girin");
				int azaltSayi = obj.nextInt();
				admin.stokAzalt(admin.urunId,azaltSayi);
				admin.listtoDatabase();
				
				
			}
			else if(inputValue == 3) {
				System.out.println("ürün id sini girin");
				admin.urunId = obj.next();
				String stokSonuc = Integer.toString(admin.stokSorgula(admin.urunId));
				System.out.println(stokSonuc);
			}
			else if(inputValue == 4) {
				System.out.println("ürün id sini girin");
				admin.urunId = obj.next();
				admin.deleteUrun(admin.urunId);
				System.out.println("azaltıldı");
			}
			else if(inputValue == 5 && !admin.urunListedemi(admin.urunId)) {
				System.out.println("ürün id sini girin");
				admin.urunId = obj.next();
				System.out.println("ürünün markası");
				admin.marka = obj.next();
				System.out.println("ürünün cinsiyeti");
				admin.cinsiyet = obj.next();
				System.out.println("türü");
				admin.ayakkabi_turu = obj.next();
				System.out.println("fiyat bilgisi");
				admin.fiyat = obj.nextInt();
				System.out.println("stok adedi");
				admin.stok_durumu =obj.nextInt();
				System.out.println("ayakkabi no");
				admin.ayakkabi_no = obj.nextInt();				
				String addurunreturn = admin.addUrun(admin.urunId, admin.marka,admin.cinsiyet, admin.ayakkabi_turu, admin.fiyat, admin.stok_durumu, admin.ayakkabi_no);
				System.out.println(addurunreturn);
				break;
				
			}
			else if(inputValue == 6) {
				System.out.println("ürün id sini girin");
				urunId = obj.next();
				System.out.println("Yeni fiyatı girin");
				int yeniFiyat = obj.nextInt();
				admin.fiyatGuncelle(urunId,yeniFiyat);
				admin.listtoDatabase();
				System.out.println( "azaltıldı");
				
			}
			
			else if(inputValue == 0) {
				break;
			}
		}
	}

}
