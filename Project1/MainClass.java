//Mason Marnell - OOP Project 1


package P1Package;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class MainClass {

	public static void main(String[] args) {
	ArrayList<Account> acctArr = new ArrayList<Account>();
	ArrayList<Integer> prodArr = new ArrayList<Integer>();
	double taxRate = 1.07;
	
	int loggedOnIndex = -1; //used to store the index of the current account in the ArrayList
		//note that later I should check to see if the index is default -1, which means that there is no user logged in
	int stopflag = 0;
	while (stopflag==0){
		System.out.println();
		System.out.println("Menu Options:");
		System.out.println("Log On (1), Log Out (2), Create Account (3), ");
		System.out.println("Select Items (4), Make Order (5), View Order (6)");
		System.out.println("\nChoose your option: ");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		System.out.println();
		
		
		switch(choice) {
		case 1: //log on
			
			System.out.println("Enter ID: ");
			Scanner idIN1 = new Scanner(System.in);
			String id1 = idIN1.nextLine();
			int foundFlag=0; //flag used for finding the ID
			int foundPWIndex=0;

			for(int i = 0; i<acctArr.size();i++) {
				if(id1.equals(acctArr.get(i).getID())) {
					foundFlag=1;
					foundPWIndex=i;
				}
			}
			
			if(foundFlag==0) {//this says if flag was not marked as found, start over
				System.out.println("ID not found, try again.");
				break;
			}
			
			System.out.println("ID found, enter password: ");
			Scanner pwIN1 = new Scanner(System.in);
			String pw1 = pwIN1.nextLine();
			
			if(!pw1.equals(acctArr.get(foundPWIndex).getPW())) { //if they are not equal
				System.out.println("Wrong password, start over.");
				break;
			}
			//if program gets to here, the ID and password matches
			System.out.println("Your security question is: ");
			System.out.println( acctArr.get(foundPWIndex).getSQ() );
			System.out.println("Enter your answer for this question: ");
			Scanner saIN1 = new Scanner(System.in);
			String sa1 = saIN1.nextLine();
			if(!sa1.equals(acctArr.get(foundPWIndex).getSA())) {
				System.out.println("Wrong security question, start over.");
				break;
			}
			//if program gets to here, all three things match and the index of the ArrayList is stored
			
			loggedOnIndex = foundPWIndex;
			System.out.println("Welcome " + acctArr.get(loggedOnIndex).getID());
			
			break;
		case 2: //log out
			loggedOnIndex=-1;
			
			System.out.println("You have successfully logged out.");
			
			
			break;
		case 3: //create account
			System.out.println("Enter your Customer ID");
			Scanner idIN = new Scanner(System.in);
			String id = idIN.nextLine();
			if(id==null) break;
			//here put the check for other accounts of same name
			for(int i = 0; i<acctArr.size();i++) {
				if(id==acctArr.get(i).getID()) {
					System.out.println("There is already an account with this ID. Try again.");
					break;
				}
			}
			
			System.out.println("Enter your password: ");
			Scanner pwIN = new Scanner(System.in);
			String pw = pwIN.nextLine();
			if(pw.length()<6) break;
			int digit=0;
			int special=0;
			int upper=0;
			for(int i = 0;i<pw.length();i++) {
				if(pw.charAt(i)=='0'||pw.charAt(i)=='1'||pw.charAt(i)=='2'||pw.charAt(i)=='3'||pw.charAt(i)=='4'||pw.charAt(i)=='5'||pw.charAt(i)=='6'||pw.charAt(i)=='7'||pw.charAt(i)=='8'||pw.charAt(i)=='9') {
					digit=1;
				}
				if(pw.charAt(i)=='@'||pw.charAt(i)=='#'||pw.charAt(i)=='$'||pw.charAt(i)=='%'||pw.charAt(i)=='&'||pw.charAt(i)=='*') {
					special=1;
				}
				if(Character.getNumericValue(pw.charAt(i)) <= 90 || Character.getNumericValue(pw.charAt(i)) >=65) {
					upper=1;
				}
			}
			if(digit==0 || special==0 || upper==0) {
				System.out.println("Password invalid, start over.");
				break;
			}
	
			System.out.println("Enter your name: ");
			Scanner cnIN = new Scanner(System.in);
			String cn = cnIN.nextLine();
			if(cn==null) break;
			
			System.out.println("Enter your address (on one line): ");
			Scanner caIN = new Scanner(System.in);
			String ca = caIN.nextLine();
			if(ca==null) break;
			
			System.out.println("Enter your credit card number with no spaces: ");
			Scanner ccIN = new Scanner(System.in);
			String cc = ccIN.nextLine();
			if(cc==null) break;
			
			System.out.println("Choose your security question by picking a number: ");
			System.out.println("(1) What is your mother's maiden name?");
			System.out.println("(2) What was the name of your first pet?");
			System.out.println("(3) What was the name of your high school?");
			Scanner sqIN = new Scanner(System.in);
			String sq;
			int sqint = sqIN.nextInt();
			if(sqint==1) {
				sq = "What is your mother's maiden name?";
			}
			else if(sqint==2) {
				sq = "What was the name of your first pet?";
			}
			else if(sqint==3) {
				sq = "What was the name of your high school?";
			}
			else {
				System.out.println("Invalid security option, start over. ");
				break;
			}
			
			System.out.println("Enter the answer to your security question: ");
			Scanner saIN = new Scanner(System.in);
			String sa = saIN.nextLine();
			if(sa==null) break;
			
			acctArr.add(new Account(id, pw, sq, sa, cn, ca, cc));
			

			
			break;
		case 4: //select items
			if(loggedOnIndex==-1) {
				System.out.println("No user is currently logged in.");
				break;
			}
			System.out.println("Current Product Catalog: ");
			System.out.println("1. Gigabyte GeForce GTX 1080 8GB");
			System.out.println("\tDescription:\tA pretty good graphics card.");
			System.out.println("\tRegular Price:\t$400");
			System.out.println("\tSale Price:\t$300");
			double rp1 = 400;
			double sp1 = 300;
			
			System.out.println("2. Intel i5-8600k Hexa-Core CPU");
			System.out.println("\tDescription:\tA pretty good CPU.");
			System.out.println("\tRegular Price:\t$300");
			System.out.println("\tSale Price:\t$200");
			double rp2 = 300;
			double sp2 = 200;
			
			
			System.out.println("3. BPX 1TB NVMe M.2 SSD");
			System.out.println("\tDescription:\tA pretty good SSD.");
			System.out.println("\tRegular Price:\t$200");
			System.out.println("\tSale Price:\t$100");
			double rp3 = 200;
			double sp3 = 100;
			
			
			int stopFlag = 1;
			while(stopFlag==1) {
				System.out.println("Enter which product you would like (1,2,3,etc.):");
				Scanner prod = new Scanner(System.in);
				int prod1 = prod.nextInt();
				
				System.out.println("Enter how many of the product you would like:");
				Scanner prodNum = new Scanner(System.in);
				int prodNum1 = prodNum.nextInt();
				
				prodArr.add(prod1);
				prodArr.add(prodNum1);
				
				System.out.println("Would you like to select another product? (1 for yes, 0 for no):");
				Scanner cont = new Scanner(System.in);
				int cont1 = prod.nextInt();
				stopFlag=cont1;
			}
			System.out.println();
			
			
			double tp = 0;
			double tpSale = 0;
			for(int i = 0;i<prodArr.size();i+=2) {
				System.out.println("Product selected:");
				System.out.println(prodArr.get(i));
				System.out.println("Quantity:");
				System.out.println(prodArr.get(i+1));
				System.out.println("Tax Multiplier:");
				System.out.println(Double.toString(taxRate));
				
				if(prodArr.get(i)==1) {
					tp+=prodArr.get(i+1)*400;
					tpSale+=prodArr.get(i+1)*300;
				}
				if(prodArr.get(i)==2) {
					tp+=prodArr.get(i+1)*300;
					tpSale+=prodArr.get(i+1)*200;
				}
				if(prodArr.get(i)==3) {
					tp+=prodArr.get(i+1)*200;
					tpSale+=prodArr.get(i+1)*100;
				}
				
			}
			System.out.println("\nTotal Price: ");
			System.out.println("$" + Double.toString(tp*1.07));
			
			System.out.println("Total Sale Price: ");
			System.out.println("$" + Double.toString(tpSale*1.07));
			
		
			break;
		case 5: //make order      //don't forget to clear prodArray at end of order
			
			if(loggedOnIndex==-1) {
				System.out.println("No user is currently logged in.");
				break;
			}
			
			double tpOrder = 0;
			if(prodArr.size()==0) {
				System.out.println("Selection is empty or not logged in.");
				break;
			}
			System.out.println("Would you like to place your order? (1 for yes, 0 for no): ");
			Scanner ord = new Scanner(System.in);
			int ord1 = ord.nextInt();
			if (ord1==0) break;
			
			String[] prodNames = new String[prodArr.size()/2];
			int[] prodQuants = new int[prodArr.size()/2];
			
					
			for(int i = 0;i<prodArr.size();i+=2) {
				if(prodArr.get(i)==1) {
					prodNames[i/2]="Gigabyte GeForce GTX 1080 8GB";
					prodQuants[i/2]=prodArr.get(i+1);
					tpOrder+=prodArr.get(i+1)*400;
				}
				if(prodArr.get(i)==2) {
					prodNames[i/2]="Intel i5-8600k Hexa-Core CPU";
					prodQuants[i/2]=prodArr.get(i+1);
					tpOrder+=prodArr.get(i+1)*300;
				}
				if(prodArr.get(i)==3) {
					prodNames[i/2]="BPX 1TB NVMe M.2 SSD";
					prodQuants[i/2]=prodArr.get(i+1);
					tpOrder+=prodArr.get(i+1)*200;
				}
				
			}
			tpOrder=tpOrder*taxRate;
			
			System.out.println("Please Choose From Delivery Methods:");
			System.out.println("1. Mail (added fee of $3.00");
			System.out.println("2. Pick up in store (free)");
			Scanner orda = new Scanner(System.in);
			int ord1a = orda.nextInt();
			if(ord1a<1 && ord1a > 2) {
				System.out.println("Invalid input, start over.");
				break;
			}
			if (ord1a==1) tpOrder+=3;
			
			System.out.println("Total is $" + Double.toString(tpOrder));
			
			String credit = acctArr.get(loggedOnIndex).getCC();
			
			int ccAuth = 1;
			Random rand = new Random();
			int authNum= rand.nextInt(9999);
			System.out.println("Charging Credit Card...");
			if(ccAuth == 0) {
				System.out.println("Card Authorization Failed, Start Over.");
				break;
			}
			System.out.println("Receiving Authorization Number...");
			System.out.println("");
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME; //deleted static
			String date = currentDateTime.format(formatter);
			
			
			
			acctArr.get(loggedOnIndex).makeOrder(date, acctArr.get(loggedOnIndex).getID(), prodNames, prodQuants, tpOrder, authNum);
			
			System.out.println("Order successfully created.");
			
			prodArr.clear();
			
			
			break;
		case 6: //view order
			
			if(loggedOnIndex==-1) {
				System.out.println("No user is currently logged in.");
				break;
			}
			
			for(int i = 0;i<acctArr.get(loggedOnIndex).getSizeOfOrderArray();i++) {
				System.out.println("Order #" + Integer.toString(i+1));
				String[] order = new String[6];
				order = acctArr.get(loggedOnIndex).getOrder(i);
				System.out.println("Order Date: " + order[0]);
				System.out.println("Product Names: " + order[2]);
				System.out.println("Product Quantities: " + order[3]);
				System.out.println("Product Total: $" + order[4]);
				
			}
			
			
			break;
		case 7: //Debug
			System.out.println("\nDEBUG OPTION\n");
			System.out.println("ID of first account in ArrayList: ");
			System.out.println(acctArr.get(0).getID());
			
			
			break;
		}
	}

	}

}
