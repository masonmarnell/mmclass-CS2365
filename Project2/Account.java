package application;

import java.util.ArrayList;

public class Account {
	private String ID;
	private String pw;
	private String secQuestion;
	private String secAnswer;
	private String custoName;
	private String custoAddy;
	private String creditCard;
	private ArrayList<Order> orderArr = new ArrayList<Order>(); //changed to private
	
	private int accountType;
	
	public Account(String i, String p, String sq, String sa, String name, String addy, String cc, int at ) {
		ID = i;
		pw = p;
		secQuestion = sq;
		secAnswer = sa;		
		custoName = name;
		custoAddy = addy;
		creditCard = cc;
		accountType = at;
		//System.out.println("account created");
		
	}
	
	public String getID() {
		return ID;
	}
	
	public String getPW() {
		return pw;
	}
	
	public String getSQ() {
		return secQuestion;
	}
	
	public String getSA() {
		return secAnswer;
	}
	
	public String getCustoName() {
		return custoName;
	}
	
	public String getCustoAddy() {
		return custoAddy;
	}
	public String getCC() {
		return creditCard;
	}
	public int getAccountType() {
		return accountType;
	}
	
	
	
	public String[] getAll() {
		String[] all = new String[7];
		all[0] = ID;
		all[1] = pw;
		all[2] = secQuestion;
		all[3] = secAnswer;
		all[4] = custoName;
		all[5] = custoAddy;
		all[6] = creditCard;
		return all;
	}
	
	public void makeOrder(String d, String id, String[] p, int[] q, double t, int a) {
		orderArr.add(new Order(d, id, p, q, t, a));
	}
	
	public String[] getOrder(int index) {
		String[] ord = new String[8];
		ord[0] = orderArr.get(index).getDate();
		ord[1] = orderArr.get(index).getID();
		
		String ord2 = "";
		for(int i = 0; i < orderArr.get(index).getProdName().length;i++) {
			ord2 += orderArr.get(index).getProdName()[i] + ", ";
		}
		ord[2] = ord2;
		
		String ord3 = "";
		for(int i = 0; i < orderArr.get(index).getQuant().length;i++) {
			ord3 += orderArr.get(index).getQuant()[i] + ", ";
		}
		ord[3] = ord3;
		ord[4] = Double.toString(orderArr.get(index).getTotal());
		ord[5] = Integer.toString(orderArr.get(index).getAuth());
		ord[6] = Integer.toString(orderArr.get(index).getProcessed());
		ord[7] = Integer.toString(orderArr.get(index).getShipped());
		return ord;
	}
	
	public int getSizeOfOrderArray() {
		return orderArr.size();
	}
	public Order getOrderAbstract(int index) {
		return orderArr.get(index);
	}
	public void setOrderInArray(int index, Order ord) {
		orderArr.set(index, ord);
	}
	
	
	
}
