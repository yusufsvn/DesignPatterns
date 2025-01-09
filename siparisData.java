package ayakkabiproje0;

import java.sql.SQLException;


public class siparisData {
		
	void exctSiparis(String siparisAdres,String kargoFirma,String odemeTipi) {
        
		var sql = "INSERT INTO siparis "
                + "VALUES(default,?,?,?)";

        try (var conn =  DB.connect();
             var pstmt = conn.prepareStatement(sql)) {

            // bind the values
            pstmt.setString(1,siparisAdres);
            pstmt.setString(2,kargoFirma);
            pstmt.setString(3,odemeTipi);

            // execute the INSERT statement and get the inserted id
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }			
	}
			
}

