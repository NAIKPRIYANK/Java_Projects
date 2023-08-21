//package bankaccountapplication;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class BankAccount {
//    private int accNo;
//    private String custName;
//    private String accType;
//    private float balance;
//
//    public BankAccount(int accNo, String custName, String accType, float balance) throws LowBalanceException, NegativeAmount {
//        this.accNo = accNo;
//        this.custName = custName;
//        this.accType = accType;
//        
//        if (balance < 0) {
//        	try{
//            throw new NegativeAmount();
//        }catch(NegativeAmount e){
//        	System.out.println("Your amount is invalid, It is in negative state,please wirte valid amount");
//        }
//        	
//        }
//        	else if (accType.equals("Saving") && balance < 1000) {
//        	try{	
//        		throw new LowBalanceException();
//        	}catch(LowBalanceException e){
//        		System.out.println("Your amount is under the 1000.For Saving account we need to write amount above the 1000");
//        	
//        	}
//        	
//        }   else if (accType.equals("Current") && balance < 5000) {
//        	try{	
//        		throw new LowBalanceException();
//        	}catch(LowBalanceException e){
//        		System.out.println("Your amount is under the 5000.For Current account we need to write amount above the 5000");
//        	}
//        } else {
//            this.balance = balance;
//        }
//    }
//    
//
//    public int getAccNo() {
//		return accNo;
//	}
//
//
//	public String getCustName() {
//		return custName;
//	}
//
//
//	public String getAccType() {
//		return accType;
//	}
//
//
//	public void deposit(float amount) throws NegativeAmount {
//        if (amount < 0) {
//        	try{
//                throw new NegativeAmount();
//           
//            }catch(NegativeAmount e){
//            	System.out.println("Your amount is invalid, It is in negative state,please wirte valid amount");
//            	
//            }
//        } else {
//            balance = balance+ amount;
//        }
//    }
//
//    public void withdraw(float amount) throws InsufficientFundsException, NegativeAmount {
//        if (amount < 0) {
//        	try{
//                throw new NegativeAmount();
//            }catch(NegativeAmount e){
//            	System.out.println("Your amount is invalid, It is negative state,please wirte valid amount");
//            }
//        } else if (accType.equals("Saving") && this.balance - amount < 1000) {
//        	try{
//            throw new InsufficientFundsException();
//        	}catch(InsufficientFundsException e){
//        		System.out.println("Your amount is under the 1000.For Saving account we need to write amount above the 1000");
//        	}
//        } else if (accType.equals("Current") && this.balance - amount < 5000) {
//        	try{	
//        		throw new LowBalanceException();
//        	}catch(LowBalanceException e){
//        		System.out.println("Your amount is under the 5000.For Current account we need to write amount above the 5000");
//        	}
//        } else {
//            balance=balance-amount;
//        }
//    }
//
//    public float getBalance() throws LowBalanceException {
//        if (accType.equals("Saving") && this.balance < 1000) {
//        	try{	
//        		throw new LowBalanceException();
//        	}catch(LowBalanceException e){
//        		System.out.println("Your amount is under the 1000.For Saving account we need to write amount above the 1000");
//        	}
//        } else if (accType.equals("Current") && this.balance < 5000) {
//        	try{	
//        		throw new LowBalanceException();
//        	}catch(LowBalanceException e){
//        		System.out.println("Your amount is under the 5000.For Current account we need to write amount above the 5000");
//        	}
//        } else {
//            return balance;
//        }
//        return 0;
//    }
//    
//    public static void main(String[] args) {
//    	Connection con=null;
//    	try {
//		
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BankAccount account[] = new BankAccount[10];
//    	int x = 0;
//    	int stop =1;
//    	Class.forName("oracle.jdbc.driver.OracleDriver");
//		String url ="jdbc:oracle:thin:@localhost:1521:XE";
//		con = DriverManager.getConnection(url, "system", "system");
//		Scanner sc = new Scanner(System.in);
//    	
//    	
//    	
//    	 
//		do {
//			     System.out.println("Choose an option:");
//			     System.out.println("1. Create Bank Account");
//			     System.out.println("2. Get Balance");
//			     System.out.println("3. Withdraw");
//			     System.out.println("4. Deposit");
//			     System.out.println("5. Exit");
//
//			     int choice = Integer.parseInt(br.readLine());
//
//			     switch (choice) {
//			         case 1:
//			             System.out.print("Enter account number: ");
//			             int accNo = Integer.parseInt(br.readLine());
//			             System.out.print("Enter account holder (Customer Name): ");
//			             String custName = br.readLine();
//			             System.out.println("Enter account Type (Current || Saving): ");
//			             String accType = br.readLine();
//			             System.out.println("Enter Balance of your Account: ");
//			             float balance = Float.parseFloat(br.readLine());
//			             String sql ="insert into account1 values(?,?,?,?)";//parameterized query
//			 			 PreparedStatement ps = con.prepareStatement(sql);//only one time compile then continue parsing is there
//			 			 ps.setInt(1, accNo);
//			 			 ps.setString(2, custName);
//			 			 ps.setString(3, accType);
//			 			 ps.setFloat(4,balance );
//			 			 int rows=ps.executeUpdate();
//			 			 if(rows>0){
//							System.out.println("Employee record inserted succesfully!!!");
//						 }
//						
//			 			
//			             if(x < 10) {
//			            	 account[x] = new BankAccount(accNo, custName, accType, balance);
//			            	 x++;
//			             } else {
//			            	 System.out.println("The maximum number of accounts has been reached !!!");
//			             }
//			             System.out.println("=_=---Account is succesfully created!!!---=_=");
//			             break;
//			         case 2:
//			        	 
//			        	 if (x!=0) {
//						 		System.out.println("Enter the account number of the customer to get balance : ");
//									 int Ac = Integer.parseInt(br.readLine());
//									 for (BankAccount account1 : account) {
//										 try {
//											 if (account1.getAccNo() == Ac) {
//												 System.out.println("Current balance: " + account1.getBalance());
//												 break;
//									 
//
//									 } 
//								
//									 } catch (NullPointerException e) {
//										 System.out.println("Account number is not valid,There is no any account of this account number!");
//									 }
//								 }
//							 }
//						 else{
//							 System.out.println("No account created yet, First create the account.");
//							 
//						 }
//
//						 break;
//						 
//			         case 3:
////						 for (BankAccount account2 : account) {
//							 if (x == 0 ) {
//								 System.out.println("No account created yet, first create the account.");
//							 } else {
//								 System.out.println("Enter the account number of the customer to withdraw amount : ");
//								 int Ac = Integer.parseInt(br.readLine());
//								 for (BankAccount account1 : account) {
//									 try {
//
//										 if (account1.getAccNo() == Ac) {
//											 System.out.print("Enter amount to withdraw: ");
//											 float amount = Float.parseFloat(br.readLine());
//											 account1.withdraw(amount);
//											 System.out.println("=_=---Withdrawn Succesfully!!!---=_=  ");
//											 break;
//										 }
//									 } catch (NullPointerException e) {
//										 System.out.println("Account number is not valid,There is no any account of this account number!");
//										 break;
//									 }
//
//								 }
//							 }
//
//							 break;
//
//			         case 4:
//			             	if (x==0) {
//			             		System.out.println("No account created yet, First create the account.");
//			             	} else {
//			             		System.out.println("Enter the account number of the customer to deposit amount : ");
//			             		int Ac = Integer.parseInt(br.readLine());
//			             		for(BankAccount account1 : account) {
//			             			try {
//			             				if(account1.getAccNo() == Ac) {
//			             					System.out.print("Enter amount to deposit: ");
//			             					float amount = Float.parseFloat(br.readLine());
//			             					account1.deposit(amount);
//			             					System.out.println("=_=---Deposit succesfully!!!---=_=");
//			             					break;
//									 }
//								} catch (NullPointerException e) {
//									System.out.println("Account number is not valid,There is no any account of this account number!");
//									break;
//								}
//			            	 }
//			             }
//			             break;
//			         case 5:
//			             System.out.println("Exiting program");
//			             System.exit(0);
//			         default:
//			             System.out.println("Error: invalid choice");
//			             break;
//			     }
//			 }while(stop==1);
//		} catch (NumberFormatException e) {
//			System.out.println("Please write a ammount in a proper Format!!!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (LowBalanceException e) {
//			e.printStackTrace();
//		} catch (NegativeAmount e) {
//			e.printStackTrace();
//		} catch (InsufficientFundsException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}finally{
//			try {
//				if(con!=null)
//					con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//    	
//
//	}
//}

