package ayakkabiproje0;


//önce context örnekle daha sonra stratejiyi belirle execute et yani algoritmayı çalıştır
public class Context {
	private paymentStrategy Strategy;
	
	public void setStrategy(paymentStrategy stretegy) {
		this.Strategy = stretegy;
	}
	
	public void pay(String urunId) {
		Strategy.pay(urunId);
	}
}
