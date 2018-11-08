package com.wipro.ccbill.entity;

import java.util.Date;

import com.wipro.ccbill.exception.InputValidationException;

public class CreditCardBill {
	private String creditCardNo;
	private String customerId;
	private Date billDate;
	private Transaction monthTransactions[];
	
 	public CreditCardBill () {
 		
 	}
 	public CreditCardBill (String creditCardNo, String customerId, Date BillDate, Transaction monthTransactions[]) {
 		this.creditCardNo=creditCardNo;
 		this.customerId=customerId;
 		this.billDate=BillDate;
 		this.monthTransactions=monthTransactions;
 	}
 	public double getTotalAmount(String transactionType) {
 		double totalAmount=0.0;
 		if(transactionType.equals("DB") ) {
 			for(int i=0;i<monthTransactions.length;i++) {
					if((monthTransactions[i].getTransactionType()).equals("DB")) {
						
						totalAmount=totalAmount+monthTransactions[i].getTransactionAmount();
					}
 			}
 		}
 			if(transactionType.equals("CR")) {
 				for(int i=0;i<monthTransactions.length;i++) {
					if((monthTransactions[i].getTransactionType()).equals("CR")) {
						totalAmount=totalAmount+monthTransactions[i].getTransactionAmount();
					}
				}
				}
 		
 	
 		return totalAmount ;
 	}
 	public double calculateBillAmount()  {
		double totalAmountSpend=0.0;
			double totalAmountPaid=0.0;
			double outstandingamount = 0.0;
			double interestCalculated=0.0;
 		try {
			if(validateData().equals("valid")) {
				if(monthTransactions.length>0) {
	
					
					totalAmountSpend=getTotalAmount("DB");
				//	System.out.println(totalAmountSpend);
					totalAmountPaid=getTotalAmount("CR");
					//System.out.println(totalAmountPaid);
					
					outstandingamount=totalAmountSpend-totalAmountPaid;
					//System.out.println(outstandingamount);
					interestCalculated=((outstandingamount * 19.9) /(100*12));
					
					return interestCalculated+outstandingamount;	
				}
				else {
					return 0.0;
				}
				
			}
		} catch (InputValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
 		
 			
 		
		
 		
 	}
 	public String validateData() throws InputValidationException{
 		try {
 		if(creditCardNo!=null && creditCardNo.length()==16 && customerId!=null && customerId.length()>=6){
 			return "valid";
 		}
 		else {
 			throw new InputValidationException();
 		}
 		}catch(Exception e) {
 			return "Invalid";
 		
 		}
 		
 	}


}
