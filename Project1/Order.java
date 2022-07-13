package P1Package;

public class Order {
	private String date;
	private String ID;
	private String[] prodName;
	private int[] quant;
	private double total;
	private int auth;
	


	public Order(String d, String id, String[] p, int[]q, double t, int a) {
		date = d;
		ID = id;
		prodName = p;
		quant = q;
		total = t;
		auth = a;
	}

	public String getDate() {
		return date;
	}
	public String getID() {
		return ID;
	}
	public String[] getProdName() {
		return prodName;
	}
	public int[] getQuant() {
		return quant;
	}
	public double getTotal() {
		return total;
	}
	public int getAuth() {
		return auth;
	}
	
}