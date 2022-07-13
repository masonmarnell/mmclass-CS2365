//Mason Marnell - Project 2 OOP - COS and OPS Completed

package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 



public class Main extends Application
{
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   //-----------------------------
   
   ArrayList<Account> acctArr = new ArrayList<Account>();
   ArrayList<Integer> prodArr = new ArrayList<Integer>();
   double taxRate = 1.07;
   
   int stock1 = 10;
   int reserved1 = 0;
   int stock2 = 10;
   int reserved2 = 0;
   int stock3 = 10;
   int reserved3 = 0;
   
	
   int loggedOnIndex = -1; //used to store the index of the current account in the ArrayList
		//note that later I should check to see if the index is default -1, which means that there is no user logged in
	
   //-----------------------------
   Scene mainScene, logOnScene; //declaring scenes to be able to use them anywhere
   
   @Override
   public void start(Stage primaryStage) throws FileNotFoundException
   {
	   Button b1 = new Button("Log On");
	   Button b2 = new Button("Log Out");
	   Button b3 = new Button("Create Account");
	   Button b4 = new Button("Select Items");
	   Button b5 = new Button("Make Order");
	   Button b6 = new Button("View Order");
	   Button b7 = new Button("Process Order");
	   Button b8 = new Button("Ship Order");
	   Button b9 = new Button("View Stock");
	   Text mainText = new Text("Please Choose An Option: ");
	   
	  
	   VBox vlanding = new VBox(8);
	   vlanding.getChildren().addAll(mainText, b1,b2,b3,b4,b5,b6,b7,b8,b9);
	   
	   mainScene = new Scene(vlanding, 250, 350);
	   
	   b1.setOnAction(e->{
		   System.out.println("test");
	   });
	   
	   primaryStage.setScene(mainScene);
	   primaryStage.setTitle("Main Page");
	   primaryStage.show();
	   
	   //start converting the code here
	   
	   b1.setOnAction(e->{ //log on
		   	
		    Text IDt = new Text("Enter your ID:");
		   	TextField IDtf = new TextField();
		   	Button IDb = new Button("Enter");
		   	
		   	VBox vLogOn = new VBox(8);
		   	vLogOn.getChildren().addAll(IDt,IDtf,IDb);
		   	
		   	logOnScene = new Scene(vLogOn, 200, 300);
		   	primaryStage.setScene(logOnScene);
		   	
		   	IDb.setOnAction(d->{
		   		int foundFlag=0; //flag used for finding the ID
				int foundPWIndex=0;
				for(int i = 0; i<acctArr.size();i++) {
					if(IDtf.getText().equals(acctArr.get(i).getID())) {
						foundFlag=1;
						foundPWIndex=i;
					}
				}
				final int foundPWIndex2 = foundPWIndex;
				if(foundFlag==0) {//this says if flag was not marked as found, start over
					Text IDNotFound = new Text("ID not found, try again.");
					HBox hIDNotFound = new HBox(IDNotFound);
					logOnScene = new Scene(hIDNotFound, 150, 30);
					primaryStage.setScene(logOnScene);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
		
				}
				else {
					Text PWt = new Text("ID Found, enter your password:");
				   	TextField PWtf = new TextField();
				   	Button PWb = new Button("Enter");
				   	
				   	VBox vLogOn2 = new VBox(8);
				   	vLogOn2.getChildren().addAll(PWt,PWtf,PWb);
				   	
				   	logOnScene = new Scene(vLogOn2, 200, 300);
				   	primaryStage.setScene(logOnScene);
				   	
				   	PWb.setOnAction(f->{
				   		if(!PWtf.getText().equals(acctArr.get(foundPWIndex2).getPW())) { //if they are not equal
				   			Text PWNotFound = new Text("Wrong password, try again.");
							HBox hPWNotFound = new HBox(PWNotFound);
							logOnScene = new Scene(hPWNotFound, 150, 30);
							primaryStage.setScene(logOnScene);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							primaryStage.setScene(mainScene);
							
						}
				   		else {
				   		//if program gets to here, the ID and password matches
				   			Text SQt = new Text("Your Security Question is:\n" + acctArr.get(foundPWIndex2).getSQ()
				   					+ "\nEnter your answer for this question");
				   			TextField SQtf = new TextField();
				   			Button SQb = new Button("Enter");
				   			
				   			VBox vLogOn3 = new VBox(8);
						   	vLogOn3.getChildren().addAll(SQt,SQtf,SQb);
						   	
						   	logOnScene = new Scene(vLogOn3, 200, 300);
						   	primaryStage.setScene(logOnScene);
				   			
						   	SQb.setOnAction(g->{
						   		
						   		if(!SQtf.getText().equals(acctArr.get(foundPWIndex2).getSA())) {
									
						   			Text SQNotFound = new Text("Wrong security answer, try again.");
									HBox hSQNotFound = new HBox(SQNotFound);
									logOnScene = new Scene(hSQNotFound, 150, 30);
									primaryStage.setScene(logOnScene);
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									primaryStage.setScene(mainScene);
									
								}
						   		
						   		else {
						   			//if program gets to here, all three things match and the index of the ArrayList is stored
						   			loggedOnIndex = foundPWIndex2;
						   			primaryStage.setTitle("Welcome " + acctArr.get(loggedOnIndex).getID());
						   			primaryStage.setScene(mainScene);
						   			
						   		}
						   		
						   	});
				   		}
				   	});
				}
				
			
		   		
		   	});
		   	
			
	   });
	  
	   b2.setOnAction(e->{ //log out
		   loggedOnIndex = -1;
		   
		    Text logOut = new Text("Successfully logged out.");
			HBox hlogOut = new HBox(logOut);
			Scene logOutScene = new Scene(hlogOut, 150, 30);
			primaryStage.setScene(logOutScene);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			primaryStage.setScene(mainScene);
		   
	   });
	   
	   b3.setOnAction(e->{ //create account
		   Text CAt1 = new Text("Enter your Customer ID:");
		   TextField CAtf1 = new TextField();
		   Text CAt2 = new Text("Enter your password:");
		   TextField CAtf2 = new TextField();
		   Text CAt3 = new Text("Enter your name:");
		   TextField CAtf3 = new TextField();
		   Text CAt4 = new Text("Enter your address");
		   TextField CAtf4 = new TextField();
		   Text CAt5 = new Text("Enter your credit card # with no spaces:");
		   TextField CAtf5 = new TextField();
		   Text CAt6 = new Text("Enter your account type\n(0 for customer, 1 for supplier):");
		   TextField CAtf6 = new TextField();
		   
		   Button CAb = new Button("Enter");
		   
		   VBox vCA = new VBox(8);
		   vCA.getChildren().addAll(CAt1, CAtf1, CAt2, CAtf2, CAt3, CAtf3, CAt4, CAtf4, CAt5, CAtf5, CAt6, CAtf6, CAb);
		   
		   Scene CAScene = new Scene(vCA, 250, 450);
		   
		   primaryStage.setScene(CAScene);
		   
		   CAb.setOnAction(h->{
			   Scene CAScene2;
			   for(int i = 0; i<acctArr.size();i++) {
					if(CAtf1.getText()==acctArr.get(i).getID()) {
						Text CAt1a = new Text("Duplicate account name, try again.");
						HBox hCAt1a = new HBox(CAt1a);
						CAScene2 = new Scene(hCAt1a, 150, 30);
						primaryStage.setScene(CAScene2);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						primaryStage.setScene(mainScene);
					}
				}
			    int digit=0;
				int special=0;
				int upper=0;
			    for(int i = 0;i<CAtf2.getText().length();i++) {
					if(CAtf2.getText().charAt(i)=='0'||CAtf2.getText().charAt(i)=='1'||CAtf2.getText().charAt(i)=='2'||CAtf2.getText().charAt(i)=='3'||CAtf2.getText().charAt(i)=='4'||CAtf2.getText().charAt(i)=='5'||CAtf2.getText().charAt(i)=='6'||CAtf2.getText().charAt(i)=='7'||CAtf2.getText().charAt(i)=='8'||CAtf2.getText().charAt(i)=='9') {
						digit=1;
					}
					if(CAtf2.getText().charAt(i)=='@'||CAtf2.getText().charAt(i)=='#'||CAtf2.getText().charAt(i)=='$'||CAtf2.getText().charAt(i)=='%'||CAtf2.getText().charAt(i)=='&'||CAtf2.getText().charAt(i)=='*') {
						special=1;
					}
					if(Character.getNumericValue(CAtf2.getText().charAt(i)) <= 90 || Character.getNumericValue(CAtf2.getText().charAt(i)) >=65) {
						upper=1;
					}
				}
				if(digit==0 || special==0 || upper==0) {
					Text CAt1a = new Text("Your password needs a digit, special character, and an upper case letter");
					HBox hCAt1a = new HBox(CAt1a);
					CAScene2 = new Scene(hCAt1a, 250, 30);
					primaryStage.setScene(CAScene2);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
				}
				
				Text SQt1 = new Text("(1) What is your mother's maiden name?");
				Text SQt2 = new Text("(2) What was the name of your first pet?");
				Text SQt3 = new Text("(3) What was the name of your high school?");
				Button SQb1 = new Button("1");
				Button SQb2 = new Button("2");
				Button SQb3 = new Button("3");
				
				
				Text SQta = new Text("Type an answer in the box and choose your security question to enter: ");
				TextField SQtfa = new TextField();
				VBox vSQ = new VBox(8);
				
				vSQ.getChildren().addAll(SQt1, SQb1, SQt2, SQb2, SQt3, SQb3, SQta, SQtfa);
				Scene abcd = new Scene(vSQ);
				primaryStage.setScene(abcd);
				
				String sq;
				SQb1.setOnAction(l->{
					acctArr.add(new Account(CAtf1.getText(), CAtf2.getText(), "What is your mother's maiden name?", SQtfa.getText(), CAtf3.getText(), CAtf4.getText(), CAtf5.getText(), Integer.parseInt(CAtf6.getText())));
					
					Scene CAScene3;
					Text CAt1a = new Text("Account created :)");
					HBox hCAt1a = new HBox(CAt1a);
					CAScene3 = new Scene(hCAt1a, 250, 30);
					primaryStage.setScene(CAScene3);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
				});
				
				SQb2.setOnAction(l->{
					acctArr.add(new Account(CAtf1.getText(), CAtf2.getText(), "What was the name of your first pet?", SQtfa.getText(), CAtf3.getText(), CAtf4.getText(), CAtf5.getText(), Integer.parseInt(CAtf6.getText())));
					
					Scene CAScene3;
					Text CAt1a = new Text("Account created :)");
					HBox hCAt1a = new HBox(CAt1a);
					CAScene3 = new Scene(hCAt1a, 250, 30);
					primaryStage.setScene(CAScene3);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
				});
				
				SQb3.setOnAction(l->{
					acctArr.add(new Account(CAtf1.getText(), CAtf2.getText(), "What was the name of your high school?", SQtfa.getText(), CAtf3.getText(), CAtf4.getText(), CAtf5.getText(), Integer.parseInt(CAtf6.getText())));
					
					Scene CAScene3;
					Text CAt1a = new Text("Account created :)");
					HBox hCAt1a = new HBox(CAt1a);
					CAScene3 = new Scene(hCAt1a, 250, 30);
					primaryStage.setScene(CAScene3);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
				});
			   
			   
		   });
		   
		   
	   });
	   
	   b4.setOnAction(e->{ //select items
		   if(loggedOnIndex==-1) {
			    Scene SIScene;
				Text SIt1 = new Text("You are not logged in, try again.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   
		   else {
		   Scene SIScene2;
		   Text SI1 = new Text("Current Product Catalog:");
		   Text SI2 = new Text("1. Gigabyte GeForce GTX 1080 8GB");
		   Text SI3 = new Text("\tDescription:\tA pretty good graphics card.");
		   Text SI4 = new Text("\tRegular Price:\t$400");
		   Text SI5 = new Text("\tSale Price:\t$300");
		  
		   Text SI6 = new Text("2. Intel i5-8600k Hexa-Core CPU");
		   Text SI7 = new Text("\tDescription:\tA pretty good CPU.");
		   Text SI8 = new Text("\tRegular Price:\t$300");
		   Text SI9 = new Text("\tSale Price:\t$200");
		   
		   Text SI10 = new Text("3. BPX 1TB NVMe M.2 SSD");
		   Text SI11 = new Text("\tDescription:\tA pretty good SSD.");
		   Text SI12 = new Text("\tRegular Price:\t$200");
		   Text SI13 = new Text("\tSale Price:\t$100");
		   Text SI14 = new Text(" ");
		   Text SI15 = new Text("Enter which product you would like (1 or 2 or 3):");
		   TextField SItf1 = new TextField();
		   Text SI16 = new Text("Enter how many of the product you would like: ");
		   TextField SItf2 = new TextField();
		   Button SIb = new Button("Enter");
		   Button SIb2 = new Button("Enter and order more");
		   
		   VBox SIv = new VBox(8);
		   SIv.getChildren().addAll(SI1, SI2, SI3, SI4, SI5, SI6, SI7, SI8, SI9, SI10, SI11, SI12, SI13, SI14,SI15, SItf1, SI16, SItf2, SIb, SIb2);
		   
		   SIScene2 = new Scene(SIv);
		   primaryStage.setScene(SIScene2);
		   
		   SIb2.setOnAction(m->{
			   prodArr.add(Integer.parseInt(SItf1.getText()));
			   prodArr.add(Integer.parseInt(SItf2.getText()));
			   primaryStage.setScene(SIScene2);
			   
		   });
		   
		   SIb.setOnAction(n->{ //continue
			   
			   prodArr.add(Integer.parseInt(SItf1.getText()));
			   prodArr.add(Integer.parseInt(SItf2.getText()));
			   VBox SIlist = new VBox(8);
			   
			   double tp = 0;
			   double tpSale = 0;
			   Text SIshow1, SIshow2, SIshow3, SIshow4, SIshow5, SIshow6, SIshow7;
			   
			   for(int i = 0;i<prodArr.size();i+=2) {
				   
				    SIshow1 = new Text("Product selected: ");
				   	SIshow2 = new Text(Integer.toString(prodArr.get(i)));
				   	SIshow3 = new Text("Quantity: ");
				   	SIshow4 = new Text(Integer.toString(prodArr.get(i+1)));
				   	SIshow5 = new Text("Tax multiplier: ");
				   	SIshow6 = new Text(Double.toString(taxRate));
				   	SIshow7 = new Text(" "); //space
			
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
					
					SIlist.getChildren().addAll(SIshow1, SIshow2, SIshow3, SIshow4, SIshow5, SIshow6, SIshow7);
					
				}
			   	
			   Text SItot1 = new Text("Total Price:");
			   Text SItot2 = new Text("$" + Double.toString(tp*1.07));
			   Text SItot3 = new Text("Total Sale Price:");
			   Text SItot4 = new Text("$" + Double.toString(tpSale*1.07));
			   Button SIback = new Button("Confirm and go back.");
			   
			   
			   SIlist.getChildren().addAll(SItot1, SItot2, SItot3, SItot4, SIback);
			   
			   Scene sOrder = new Scene(SIlist);
			   
			   primaryStage.setScene(sOrder);
			   
			   SIback.setOnAction(o->{
				   primaryStage.setScene(mainScene);
			   });
			   	
			   
		   });
	   }
	   });
	   
	   b5.setOnAction(e->{ //make order
		   Scene SIScenea, SIScene2;
		   if(loggedOnIndex==-1) {
			    
				Text SIt1a = new Text("You are not logged in, try again.");
				HBox hSIt1a = new HBox(SIt1a);
				SIScenea = new Scene(hSIt1a, 250, 30);
				primaryStage.setScene(SIScenea);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else {
		   
		   if(prodArr.size()==0) {

				Text SIt2 = new Text("Your selection is empty");
				HBox hSIt2 = new HBox(SIt2);
				SIScene2 = new Scene(hSIt2, 250, 30);
				primaryStage.setScene(SIScene2);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   	
		    Text MAt1 = new Text("Would you like to place your selected order?");
		    Button yes1 = new Button("yes");
		    Button no1 = new Button("no");
		    
		    
		    
			VBox MAv1 = new VBox(8);
			
			MAv1.getChildren().addAll(MAt1,yes1,no1);

			SIScenea = new Scene(MAv1, 1000, 1000);
			primaryStage.setScene(SIScenea);
			
			no1.setOnAction(p->{
				primaryStage.setScene(mainScene);
			});
			
			yes1.setOnAction(p->{
				String[] prodNames = new String[prodArr.size()/2];
				int[] prodQuants = new int[prodArr.size()/2];
				double tpOrder = 0;
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
				
				Text MAt2 = new Text("Please Choose From Delivery Methods:");
				Text MAt3 = new Text("1. Mail (added fee of $3.00)");
				Button MAb1 = new Button("^");
				Text MAt4 = new Text("2. Pick up in store (free)");
			    Button MAb2 = new Button("^");
			    
			    
			    
				VBox MAv2 = new VBox(8);
				
				MAv2.getChildren().addAll(MAt2,MAt3,MAb1,MAt4,MAb2);

				Scene SIScene5 = new Scene(MAv2, 250, 200);
				primaryStage.setScene(SIScene5);
				
				
				final double tpOrderFin = tpOrder;
				MAb1.setOnAction(q->{ //+3 to tporder
					Text cct1 = new Text("Credit Card Authorized and Charged.");
					Text cct2 = new Text("Order Successfully Created");
					
					LocalDateTime currentDateTime = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME; //deleted static
					String date = currentDateTime.format(formatter);
					Random rand = new Random();
					acctArr.get(loggedOnIndex).makeOrder(date, acctArr.get(loggedOnIndex).getID(), prodNames, prodQuants, tpOrderFin+3, rand.nextInt(9999));
					prodArr.clear();
					
					
					VBox ccv4 = new VBox(8);
					ccv4.getChildren().addAll(cct1,cct2);
					Scene ccScene = new Scene(ccv4, 250, 60);
					primaryStage.setScene(ccScene);
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
					
				});
				
				MAb2.setOnAction(q->{
					Text cct1 = new Text("Credit Card Authorized and Charged.");
					Text cct2 = new Text("Order Successfully Created");
					
					LocalDateTime currentDateTime = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME; //deleted static
					String date = currentDateTime.format(formatter);
					Random rand = new Random();
					acctArr.get(loggedOnIndex).makeOrder(date, acctArr.get(loggedOnIndex).getID(), prodNames, prodQuants, tpOrderFin+3, rand.nextInt(9999));
					prodArr.clear();
					
					
					VBox ccv4 = new VBox(8);
					ccv4.getChildren().addAll(cct1,cct2);
					Scene ccScene = new Scene(ccv4, 250, 60);
					primaryStage.setScene(ccScene);
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.setScene(mainScene);
				});
				
			});
		   
		   }
	   });
	   
	   b6.setOnAction(e->{ //view order
		   if(loggedOnIndex==-1) {
			    Scene SIScene;
				Text SIt1 = new Text("You are not logged in, try again.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   
		   else {
		   Text VOt1, VOt2, VOt3, VOt4, VOt5, VOt6;
		   Scene VOScene;
		   VBox VOlist = new VBox(8);
		   for(int i = 0;i<acctArr.get(loggedOnIndex).getSizeOfOrderArray();i++) {
			
				String[] order = new String[8];
				order = acctArr.get(loggedOnIndex).getOrder(i);
				
				
				VOt1 = new Text("Order #" + Integer.toString(i+1));
				VOt2 = new Text("Order Date: " + order[0]);
				VOt3 = new Text("Product Names: " + order[2]);
				VOt4 = new Text("Product Quantities: " + order[3]);
				VOt5 = new Text("Product Total: $" + order[4]);
				if(Integer.parseInt(order[6])==0 && Integer.parseInt(order[6])==0) {
					VOt5 = new Text("Status: Not Processed");
				}
				else if(Integer.parseInt(order[6])==1 && Integer.parseInt(order[6])==0) {
					VOt5 = new Text("Status: Processed");
				}
				else if(Integer.parseInt(order[6])==1) {
					VOt5 = new Text("Status: Shipped");
				}
				else {
					VOt5 = new Text("Status: Unknown");
				}
				
				VOt6 = new Text(" ");
				
				VOlist.getChildren().addAll(VOt1, VOt2, VOt3, VOt4, VOt5, VOt6);
			
				
			}
		   	
		   	Button VOback = new Button("Done");
		   	VOlist.getChildren().addAll(VOback);
		   	VOScene = new Scene(VOlist);
		   	
		   	primaryStage.setScene(VOScene);
		   	
		   	VOback.setOnAction(r->{
		   		primaryStage.setScene(mainScene);
		   	});
		   }
	   });
	   
	   b7.setOnAction(e->{ //process order
		   
		   if(loggedOnIndex==-1) {
			    Scene SIScene;
				Text SIt1 = new Text("You are not logged in, try again.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else if(acctArr.get(loggedOnIndex).getAccountType()==0) {
			    Scene SIScene;
				Text SIt1 = new Text("You do not have a supplier account.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else {
		 //get orders
		   Text PO1, PO2, PO3, PO4, PO5, PO6, PO7, PO8, PO9, P10;  
		   VBox POv = new VBox(8);
			for(int i = 0; i< acctArr.size(); i++) {
				for(int j = 0; j < acctArr.get(i).getSizeOfOrderArray();j++) {
					 
					String[] ord = acctArr.get(i).getOrder(j);
				
					PO1 = new Text("Date: " + ord[0]);
					PO2 = new Text("ID: " + ord[1]);
					PO3 = new Text("Product Names: " + ord[2]);
					PO4 = new Text("Product Quantities: " + ord[3]);
					PO5 = new Text("Total: " + ord[4]);
					PO6 = new Text("Processed: " + ord[6]);
					PO7 = new Text("Shipped: " + ord[7]);
					PO8 = new Text(" ");
					
					POv.getChildren().addAll(PO1, PO2, PO3, PO4, PO5, PO6, PO7, PO8);
					
					
					
					
					
					//here just do gui stuff with the ordTemp object to display all the orders
					
					
				}
			}
			PO9 = new Text("Enter the number in the list that your selected order is: ");
			TextField POtf = new TextField();
			
			Button POb1 = new Button("Enter");
			POv.getChildren().addAll(PO9, POtf, POb1);
			
		    Scene POs = new Scene(POv);
		    primaryStage.setScene(POs);
		    
		    POb1.setOnAction(s->{
		    	int orderListNumber = Integer.parseInt(POtf.getText());
		    	
		    	int outOfStockFlag = 0;
				for(int i = 0; i< acctArr.size(); i++) {
					for(int j = 0; j < acctArr.get(i).getSizeOfOrderArray();j++) {
						if(orderListNumber != 0) { //this ^ big code block just selects the order based on previously mentioned order list ^.
							orderListNumber--;
						}
						else { //this is the code block to do something with the selected order
							Order ordTemp = acctArr.get(i).getOrderAbstract(j);
							for(int k = 0; k < ordTemp.getQuant().length; k++) {	
								if (ordTemp.getProdName()[k].equals("Gigabyte GeForce GTX 1080 8GB")) {
									if(ordTemp.getQuant()[k]>stock1) {
										outOfStockFlag = 1;
										continue;
									}
									else {
										reserved1+=ordTemp.getQuant()[k]; //update the amount of reserved stock
									}
								}
								if (ordTemp.getProdName()[k].equals("Intel i5-8600k Hexa-Core CPU")) {
									if(ordTemp.getQuant()[k]>stock2) {
										outOfStockFlag = 2;
										continue;
									}
									else {
										reserved2+=ordTemp.getQuant()[k]; //update the amount of reserved stock
									}
								}
								if (ordTemp.getProdName()[k].equals("BPX 1TB NVMe M.2 SSD")) {
									if(ordTemp.getQuant()[k]>stock3) {
										outOfStockFlag = 3;
										continue;
									}
									else {
										reserved3+=ordTemp.getQuant()[k]; //update the amount of reserved stock
									}
								}
							}
							Text oos;
							if(outOfStockFlag!=0) {
								oos = new Text("Sorry, items out of stock.");
								
							}
							else {
								ordTemp.setProcessed(1);
								acctArr.get(i).setOrderInArray(j, ordTemp); //set the order as processed
								oos = new Text("Items have been reserved.");
							}
							
							VBox vvv = new VBox(oos);
							Scene POs2 = new Scene(vvv,300,300);
							primaryStage.setScene(POs2);
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							primaryStage.setScene(mainScene);
						}
							
					}
				} //end of use case
		    	
		    });
		   
		   }
	   });
	   
	   b8.setOnAction(e->{ //ship order
		   if(loggedOnIndex==-1) {
			    Scene SIScene;
				Text SIt1 = new Text("You are not logged in, try again.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else if(acctArr.get(loggedOnIndex).getAccountType()==0) {
			    Scene SIScene;
				Text SIt1 = new Text("You do not have a supplier account.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else {
		   
		   //get processed orders
		   Text SO1, SO2, SO3, SO4, SO5, SO6, SO7, SO8, SO9, S10;  
		   VBox SOv = new VBox(8);
			for(int i = 0; i< acctArr.size(); i++) {
				for(int j = 0; j < acctArr.get(i).getSizeOfOrderArray();j++) {
					 
					
					String[] ord = acctArr.get(i).getOrder(j);
					if(Integer.parseInt(ord[6])==1) {
						SO1 = new Text("Date: " + ord[0]);
						SO2 = new Text("ID: " + ord[1]);
						SO3 = new Text("Product Names: " + ord[2]);
						SO4 = new Text("Product Quantities: " + ord[3]);
						SO5 = new Text("Total: " + ord[4]);
					//SO6 = new Text("Processed: " + ord[6]);
						//SO7 = new Text("Shipped: " + ord[7]);
						SO8 = new Text(" ");
					
						SOv.getChildren().addAll(SO1, SO2, SO3, SO4, SO5, SO8);
					}
					
					
					
					
					
					//here just do gui stuff with the ordTemp object to display all the orders
					
					
				}
			}
			
			S10 = new Text("Enter the number in the list that your selected order is: ");
			TextField SOtf = new TextField();
			
			Button SOb1 = new Button("Enter");
			SOv.getChildren().addAll(S10, SOtf, SOb1);
			
		    Scene SOs = new Scene(SOv);
		    primaryStage.setScene(SOs);
		    
		    SOb1.setOnAction(t->{
		    	int ProcOrderListNumber = Integer.parseInt(SOtf.getText());
		    	
		    	for(int i = 0; i< acctArr.size(); i++) {
					for(int j = 0; j < acctArr.get(i).getSizeOfOrderArray();j++) {
						if(acctArr.get(i).getOrderAbstract(j).getProcessed() == 1 && acctArr.get(i).getOrderAbstract(j).getShipped()==0) {
							if(ProcOrderListNumber!=0) {
								ProcOrderListNumber--;
							}
							else {
								Order ordTemp = acctArr.get(i).getOrderAbstract(j);
								ordTemp.setProcessed(0);
								ordTemp.setShipped(1);
								acctArr.get(i).setOrderInArray(j, ordTemp); //set the order as shipped
							}

							
						}
					}
				}
		    	
		    	Text shipped = new Text("Items marked as shipped.");
		    	VBox vvvv = new VBox(shipped);
				Scene POs3 = new Scene(vvvv,300,300);
				primaryStage.setScene(POs3);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		    	
		    });
		    
		   
		   
		   }
	   });
	   
	   b9.setOnAction(e->{ //view stock
		   
		   if(loggedOnIndex==-1) {
			    Scene SIScene;
				Text SIt1 = new Text("You are not logged in, try again.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else if(acctArr.get(loggedOnIndex).getAccountType()==0) {
			    Scene SIScene;
				Text SIt1 = new Text("You do not have a supplier account.");
				HBox hSIt1 = new HBox(SIt1);
				SIScene = new Scene(hSIt1, 250, 30);
				primaryStage.setScene(SIScene);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				primaryStage.setScene(mainScene);
		   }
		   else {
		   Text t1 = new Text("Items in stock: ");
		   Text t2 = new Text("Gigabyte GeForce GTX 1080 8GB");
		   Text t3 = new Text("Free quantity: " + Integer.toString(stock1));
		   Text t4 = new Text("Reserved quantity: " + Integer.toString(reserved1));
		   Text t5 = new Text(" ");
		   Text t6 = new Text("Intel i5-8600k Hexa-Core CPU");
		   Text t7 = new Text("Free quantity: " + Integer.toString(stock2));
		   Text t8 = new Text("Reserved quantity: " + Integer.toString(reserved2));
		   Text t9 = new Text(" ");
		   Text t10 = new Text("");
		   Text t11 = new Text("BPX 1TB NVMe M.2 SSD");
		   Text t12 = new Text("Free quantity: " + Integer.toString(stock3));
		   Text t13 = new Text("Reserved quantity: " + Integer.toString(reserved3));
		   Text t14 = new Text(" ");
		   
		   VBox stockv = new VBox(8);
		   Button stockb = new Button("Done");
		   stockv.getChildren().addAll(t1, t2, t3, t4, t5, t6, t7, t8, t9, t11, t12, t13, t14, stockb);
		   
		   Scene stockScene = new Scene(stockv,500, 1000);
		   primaryStage.setScene(stockScene);
		   }
	   });
      
   }
}