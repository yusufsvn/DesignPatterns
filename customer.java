package ayakkabiproje0;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


interface customer{
	public void girisYap(String email,String password);
	public String kayitOl(String email,String isim,String soyisim,String password,String telefon,String adres,String kullaniciTipi);
}


class StandartCustomer implements customer{
	String email;
	String password;
	String isim;
	String soyisim;
	String telefon;
	String adres;
	String musteriTipi;
	String kullaniciTip; 
	ArrayList<String> customer=new ArrayList<String>();
	
	String emailondata;
	String passwordondata;
	
	//databaseden email ve password getir ve customer listesine atama yapar
	public void girisYap(String email,String password) {
		this.email=email;
		this.password=password;
		
		var sql = "SELECT email, pass FROM musteri ORDER BY email";
		try (var conn =  DB.connect(); var stmt = conn.createStatement()) {
			var rs = stmt.executeQuery(sql);
			while (rs.next()) {
		        emailondata=rs.getString("email");
		        passwordondata=rs.getString("pass");
				 
				 
				customer.add(emailondata);
				customer.add(passwordondata);
		        }
		 } 
		 catch (SQLException e) {
		            e.printStackTrace();
		        }
	}
	
	public String kayitOl(String email,String isim,String soyisim,String password,String telefon,String adres,String kullanicitipi) {
		this.email=email;
		this.adres= adres;
		this.isim=isim;
		this.password=password;
		this.soyisim=soyisim;
		this.telefon= telefon;
		this.kullaniciTip = kullanicitipi;
		
		StandartCustomer obj = new StandartCustomer();
		obj.girisYap(email, password);
		
		int x = 0;
		while(x < obj.customer.size()) {
			if (email.equals(obj.customer.get(x))){
				return "girilen email ile kayıtlı bir hesap zaten mevcut.";		
				}
			x=x+2;
		}
			
		
		//kayit ol sql kısmı
		var sql = "INSERT INTO musteri(email,isim,soyisim,pass,telefon,adres,kullanicitipi) "
	            + "VALUES(?,?,?,?,?,?,?)";
		
	    try (var conn =  DB.connect();
	         var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	
	        // bind the values
	        pstmt.setString(1,email);
	        pstmt.setString(2,isim);
	        pstmt.setString(3,soyisim);
	        pstmt.setString(4,password);
	        pstmt.setString(5,telefon);
	        pstmt.setString(6,adres);
	        pstmt.setString(7,kullaniciTip);
	        
	
	        // execute the INSERT statement and get the inserted id
	        int insertedRow = pstmt.executeUpdate();
	        if (insertedRow > 0) {
	            var rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	                System.out.println("email:"+rs.getString(1)); ; 
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	   
		
	return "eklendi";
}		
	
	
	//girisYap() çağırıldıktan sonra doğrulama için gerekli çağırmayı yap
	public int verikontrol() {
		int i = 0;
		while(i!=customer.size()) {
			if(email.equals(customer.get(i))){
				if(password.equals(customer.get(i+1))) {
					//System.out.println("bilgiler doğru");;
					return 1;
					
				}
				else {
					//System.out.println("şifre yanlış");
					return 0;
				}
			}
			i=i+2;
			}
		return -1;
		
	}
	
}

class KurumsalCustomer implements customer{
	String email;
	String password;
	String isim;
	String soyisim;
	String telefon;
	String adres;
	String musteriTipi;
	String kullaniciTip; 
	Scanner inputobject1 = new Scanner(System.in);
	StandartCustomer obj = new StandartCustomer();
	
	
	public void girisYap(String email,String password) {
		obj.girisYap(email, password);
	}
	
	public int veriDogrula() {
		int exitValue = obj.verikontrol();
		return exitValue;
		
	}
	
	public String kayitOl(String email,String isim,String soyisim,String password,String telefon,String adres,String kullaniciTipi) {
		//bu kısmı customerda kurumsala eklenebilir
		System.out.println("Şirket ismi");
		String companyName = inputobject1.next();

		
		
		//kontrol aynı email var mı yok mu
		obj.girisYap(email, password);
		int x = 0;
		while(x < obj.customer.size()) {
			if (email.equals(obj.customer.get(x))){
				return "girilen email ile kayıtlı bir hesap zaten mevcut.";		
				}
			x=x+2;
		}
		
		String exitValue = obj.kayitOl(email, isim, soyisim, password, telefon, adres, companyName);
		return exitValue;
	}
}


class AdminUser implements customer{
	
	private StandartCustomer obj = new StandartCustomer();
	
	public void girisYap(String email,String password) {
		obj.girisYap(email, password);
	}
	public int veriDogrula() {
		int exitValue = obj.verikontrol();
		return exitValue;
		
	}
	public String kayitOl(String email,String isim,String soyisim,String password,String telefon,String adres,String kullaniciTipi) {
		return "burada gerçekten hiçbir şey olmayacak";
	}

	
	
	
}