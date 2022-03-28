package com.calculator.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataSet {
	private double loanAmount, interestRate, deferRate;
	private int loanTerm;
	private int calcMethod;
	
	public ArrayList <DataEntity> dataArray;

	public void calculateData() {
		dataArray = new ArrayList <>();
		switch(calcMethod)
		{
		case 0:
		{
			double var1 = interestRate * Math.pow((interestRate + 1), loanTerm);
			double var2 = Math.pow((1 + interestRate), loanTerm) - 1;
			double tempValue = loanAmount * ((double)var1 / (double)var2);
			double tempLoanAmount = loanAmount;
			for(int i = 0; i < loanTerm; ++i)
			{
				double tempInterest = tempLoanAmount * interestRate;
				double tempPrincipal = tempValue - tempInterest;
				tempLoanAmount -= tempPrincipal;
				dataArray.add(new DataEntity(i+1, tempPrincipal, tempInterest, tempLoanAmount));
			}
			break;
		}
		case 1:
		{
			double valPrincipal = loanAmount / loanTerm;
			double tempLoanAmount = loanAmount;
			for(int i = 0; i < loanTerm; ++i)
			{
				double valInterest = tempLoanAmount * (interestRate / 12);
				tempLoanAmount = tempLoanAmount - valPrincipal;
				dataArray.add(new DataEntity(i+1, valPrincipal, valInterest, tempLoanAmount));
			}
			break;
		}
		}
	}
	
	public void calculateData(int deferStartingMonth, int deferLength) {
		dataArray = new ArrayList <>();
		switch(calcMethod)
		{
		case 0:
		{
			double var1 = interestRate * Math.pow((interestRate + 1), loanTerm);
			double var2 = Math.pow((1 + interestRate), loanTerm) - 1;
			double tempValue = loanAmount * ((double)var1 / (double)var2);
			double tempLoanAmount = loanAmount;
			for(int i = 0; i < loanTerm + deferLength; ++i)
			{
				if(i + 1 == deferStartingMonth)
				{
					for(int j = 0; j < deferLength; ++j)
					{
						double tempInterest = tempLoanAmount * deferRate;
						dataArray.add(new DataEntity(i+1, 0, tempInterest, tempLoanAmount));
						++i;
					}
				}
				double tempInterest = tempLoanAmount * interestRate;
				double tempPrincipal = tempValue - tempInterest;
				tempLoanAmount -= tempPrincipal;
				dataArray.add(new DataEntity(i+1, tempPrincipal, tempInterest, tempLoanAmount));
			}
			break;
		}
		case 1:
		{
			double valPrincipal = loanAmount / loanTerm;
			double tempLoanAmount = loanAmount;
			for(int i = 0; i < loanTerm + deferLength; ++i)
			{
				if(i + 1 == deferStartingMonth)
				{
					for(int j = 0; j < deferLength; ++j)
					{
						double tempInterest = tempLoanAmount * interestRate;
						dataArray.add(new DataEntity(i+1, 0, tempInterest, tempLoanAmount));
						++i;
					}
				}
				double valInterest = tempLoanAmount * (interestRate / 12);
				tempLoanAmount = tempLoanAmount - valPrincipal;
				dataArray.add(new DataEntity(i+1, valPrincipal, valInterest, tempLoanAmount));
			}
			break;
		}
		}
	}
	
	public void printToFile(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		for(DataEntity dataEntity : dataArray)
		{
			printWriter.print(dataEntity.toString());
		}
		
		printWriter.close();
	}
	
	public void changeLoanAmount(String value) {
		loanAmount = Double.parseDouble("0" + value);
	}
	
	
	public void changeInterestRate(String value) {
		double tempValue = Double.parseDouble("0" + value) / 100;
		interestRate = tempValue / 12;
	}
	
	public void changeDeferRate(String value) {
		double tempValue = Double.parseDouble("0" + value) / 100;
		deferRate = tempValue / 12;
	}
	
	public void changeLoanTerm(String valueY, String valueM) {
		int temp = Integer.parseInt("0" + valueM);
		temp += Integer.parseInt("0" + valueY) * 12;
		loanTerm = temp;
	}
	
	public void changeCalcMethod(String value) {
		if(value == "Line")
		{
			calcMethod = 1;
			return;
		}
		calcMethod = 0;
	}
	
	public int howManyTerms() {
		return loanTerm;
	}
	
}