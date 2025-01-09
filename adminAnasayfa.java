package ayakkabiproje0;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class adminAnasayfa {
	
	String urunId;String marka;String cinsiyet;String ayakkabi_turu;int fiyat;int stok_durumu;int ayakkabi_no;
	
	ArrayList<String> ayakkabiId = new ArrayList<String>();
	ArrayList<Integer> fiyatList = new ArrayList<Integer>();
	ArrayList<Integer> stokList = new ArrayList<Integer>();
	
	public void databasetolist() {
		var sql = "SELECT ayakkabi_id,fiyat,stok_durumu FROM ayakkabi ORDER BY ayakkabi_id";
		try (var conn =  DB.connect(); var stmt = conn.createStatement()) {
			var rs = stmt.executeQuery(sql);
			while (rs.next()) {
		        String ayakkabi_id = rs.getString("ayakkabi_id");
		        int fiyatOnData = rs.getInt("fiyat");
		        int stokDurum = rs.getInt("stok_durumu");
				 		
				ayakkabiId.add(ayakkabi_id);
				fiyatList.add(fiyatOnData);
				stokList.add(stokDurum);
		        }
		 } 
		 catch (SQLException e) {
		            e.printStackTrace();
		        }
	}
	
	public int listtoDatabase() {
		
		for(int i=0;i<ayakkabiId.size();) {
			int fiyat = fiyatList.get(i);
			int stokDurum = stokList.get(i);
			String ayakkabi_id = ayakkabiId.get(i);
			
			var sql  = "UPDATE ayakkabi "
                + "SET fiyat = ? ,stok_durumu= ? "
                + "WHERE ayakkabi_id = ?";

	        int affectedRows = 0;
	
	        try (var conn  = DB.connect();
	             var pstmt = conn.prepareStatement(sql)) {
	        	
	        	
	        	pstmt.setInt(1,fiyat);
	            pstmt.setInt(2,stokDurum);
	            pstmt.setString(3, ayakkabi_id);
	            
	
	            affectedRows = pstmt.executeUpdate();
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return affectedRows;
		}
		return 0;
	}
	
	//urunun stok bilgiler hakkında değişiklik yapar
	public int stokEkle(String urunId,int ekleSayi) {
		int index = ayakkabiId.indexOf(urunId);
		int mevcutStok = stokList.get(index);
		stokList.set(index, mevcutStok+ekleSayi);
		System.out.println("eski stok değeri = " + mevcutStok);
		System.out.println("yeni stok değeri = " + (mevcutStok + ekleSayi));
		return mevcutStok + ekleSayi;

	}
	public int stokAzalt(String urunId,int azaltSayi) {
		int index = ayakkabiId.indexOf(urunId);
		int mevcutStok = stokList.get(index);
		stokList.set(index, mevcutStok-azaltSayi);
		System.out.println("eski stok değeri = " + mevcutStok);
		System.out.println("yeni stok değeri = " + (mevcutStok - azaltSayi));
		return mevcutStok - azaltSayi;
		
	}
	//urun id li urunun stok bilgisini ekrana yazdır
	public int stokSorgula(String urunId) {
		int index = ayakkabiId.indexOf(urunId);
		int mevcutStok = stokList.get(index);
		System.out.println("mevcut stok durumu = " + mevcutStok);
		return mevcutStok;
		
	}
	//databaseden urunId li urunu bul ve veritabanından sil
	public int deleteUrun(String UrunId) {
		var sql  = "DELETE FROM ayakkabi WHERE ayakkabi_id=?";

        try (var conn  = DB.connect();
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, UrunId);

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
		
		
	}
	public int fiyatGuncelle(String urunId,int yeniFiyat) {
		int index = ayakkabiId.indexOf(urunId);
		fiyatList.set(index, yeniFiyat);
		int yeniFiyatre =  fiyatList.get(index);
		System.out.println(yeniFiyatre);
		return yeniFiyatre;
		
		
	}
	public String addUrun(String urunId,String marka,String cinsiyet,String ayakkabi_turu,int fiyat,int stok_durumu,int ayakkabi_no) {
		//database insert into yapılıp veri eklenecek
			
		//kayit ol sql kısmı
		var sql = "INSERT INTO ayakkabi(ayakkabi_id,marka,cinsiyet,ayakkabi_turu,fiyat,stok_durumu,ayakkabi_no) "
	            + "VALUES(?,?,?,?,?,?,?)";
		
	    try (var conn =  DB.connect();
	         var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	
	        // bind the values
	        pstmt.setString(1,urunId);
	        pstmt.setString(2,marka);
	        pstmt.setString(3,cinsiyet);
	        pstmt.setString(4,ayakkabi_turu);
	        pstmt.setInt(5,fiyat);
	        pstmt.setInt(6,stok_durumu);
	        pstmt.setInt(7,ayakkabi_no);
	        
	
	        // execute the INSERT statement and get the inserted id
	        int insertedRow = pstmt.executeUpdate();
	        if (insertedRow > 0) {
	            var rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	                System.out.println("email:"+rs.getString(1)); ; 
	            }
	        }
	        return "eklendi";
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "ekleme başarısız";
	}
	public boolean urunListedemi(String urunId) {
		var sql = "SELECT ayakkabi_id FROM ayakkabi ORDER BY ayakkabi_id";
		try (var conn =  DB.connect(); var stmt = conn.createStatement()) {
			var rs = stmt.executeQuery(sql);
			while (rs.next()) {
		        String ayakkabiIdOnData=rs.getString("ayakkabi_id");
				ayakkabiId.add(ayakkabiIdOnData);
				
		        }
		 } 
		 catch (SQLException e) {
		            e.printStackTrace();
		        }
		if(ayakkabiId.contains(urunId)) {
			return true;
		}
		return false;
	}
}
