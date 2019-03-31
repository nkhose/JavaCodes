package com.atmdispense;

import java.util.Scanner;

class ATM {

	public int totalAmount, amount,modAmount;
	public final int[] currDenom={500,50,20};
	public int[] currNo={10,20,30};
	public int[] count={0,0,0};
	
	public ATM(){
		super();
	}
	
	public void calTotalAmount(){
		for(int i=0;i<currDenom.length;i++){
			totalAmount= totalAmount +currDenom[i]*currNo[i];
		}
	}
	
	int ioamount = 0;
	int ioamount1= 0;

	public void withdrwalCash(){
		
		ioamount = amount;
		if(amount>=20 && amount<=500 && amount%10 == 0){
			
			int sumChecker = 0;
			for(int i=0;i<currDenom.length;i++){
				if(currDenom[i]<=amount){
					int noteCount=amount/currDenom[i];
					if(currNo[i]>0 ){
						count[i]=noteCount>=currNo[i]?currNo[i]:noteCount;
						currNo[i]=noteCount>=currNo[i]?0:currNo[i]-noteCount;
						totalAmount=totalAmount-(currDenom[i]*count[i]);
						amount=amount-(count[i]*currDenom[i]);
					}
					System.out.println(currDenom[i]+" x "+count[i]+" = "+(currDenom[i]*count[i]));
					sumChecker = sumChecker +(currDenom[i]*count[i]);
					//System.out.println(sumChecker +" and "+ioamount);
				}
			}
			int remainder = ioamount -sumChecker;
			System.out.println("\nsumChecker value is "+sumChecker+ " which is not match with Entered Amount in ATM");
			System.out.println("Other Possible Combinations are:");
			
			if (remainder==10){
				int remaingBalance = ioamount-20;
					
				 while (remaingBalance % 50 != 0) {
					 	remaingBalance = remaingBalance-20;
			        }
					//System.out.println("\nFinal remaingBalance is:::: " +remaingBalance);
					int NewRemaingBalance = remaingBalance/50;
					//System.out.println("New remaingBalance reminder: " +NewRemaingBalance);
					System.out.println("50 x "+ NewRemaingBalance+ " = " +remaingBalance);
					
					remaingBalance = ioamount - remaingBalance;
					NewRemaingBalance = remaingBalance/20;
					System.out.println("20 x "+ NewRemaingBalance+ " = " +remaingBalance);
			}
		
			if(sumChecker == amount){
				displayNote();
			} 
			
		}else{
			System.out.println("ATM cannot dispense this Amount, Please choose another Amount");
		}
	}

	private void displayNote() {
		// TODO Auto-generated method stub
			for(int i=0;i<count.length;i++){
					if( count[i]!=0){
						System.out.println(currDenom[i]+" x "+count[i]+" = "+(currDenom[i]*count[i]));
					}
				}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ATM a =new ATM();
		
		boolean valid = true;
		
		do {
			System.out.print("\nEnter Amount in ATM::");
			Scanner sc=new Scanner(System.in);
			a.amount=sc.nextInt();
			a.calTotalAmount();
			a.withdrwalCash();
		} while (valid);

	}

}