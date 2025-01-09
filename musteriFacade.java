package ayakkabiproje0;

import java.sql.SQLException;

public class musteriFacade {
	public void handleGiris() throws SQLException {
        girisyap giris = new girisyap();
        giris.girisYap();
    }
    public void handleKayit() throws SQLException {
        kayitol kayit = new kayitol();
        kayit.Kayitol();
    }
    
    public void handleHata() {
        hata hatamesaj = new hata();
        hatamesaj.hatax();
    }
}
