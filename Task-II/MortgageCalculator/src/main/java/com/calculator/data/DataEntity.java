package com.calculator.data;

import java.text.DecimalFormat;

public class DataEntity {
	private int month;
	private double principal;
	private double interest; 
	private double loanAmount;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public DataEntity(int month, double Principal, double Interest, double loanAmount) {
		this.month = month;
		this.principal = Principal;
		this.interest = Interest;
		this.loanAmount = loanAmount;
	}
	
	public double getPrincipal() {
		return Math.round(principal * 100) / 100;
	}
	
	public double getInterest() {
		return Math.round(interest * 100) / 100;
	}
	
	public double getLoanAmount() {
		return Math.round(loanAmount * 100) / 100;
	}

	public int getMonth() {
		return month; 
	}
	
	public double getPaymentValue() {
		return principal + interest;
	}

	@Override
	public String toString() {
		return "Month: " + month + ", Principal: Eur" + df.format(principal) + ", Interest: Eur" + df.format(interest) + ", Loan amount remaining: Eur" + df.format(loanAmount) + "\n";
	}
	
}
