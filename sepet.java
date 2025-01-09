package ayakkabiproje0;
import java.sql.SQLException;

interface sepet{
	public int addSepet(String email,String Ayakkabi_no);
}

class sepetClass implements sepet{
	// sepet tablosuna ürün ekleme
	public  int addSepet(String email,String Ayakkabi_no) {
		var sql = "INSERT INTO sepet(email, ayakkabi_id) "
                + "VALUES(?,?)";
	
	    try (var conn =  DB.connect();
	         var pstmt = conn.prepareStatement(sql)) {
	    	

		     pstmt.setString(1,email);
		     pstmt.setString(2,Ayakkabi_no);
		     pstmt.addBatch();
	    	
	    	pstmt.executeBatch();
	    	return 1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	   return -1;
	}
	
	
}