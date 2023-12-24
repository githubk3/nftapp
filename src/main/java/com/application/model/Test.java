package com.application.model;


public class Test {
	public static void main(String[] args) {
		Bucket db = new Bucket();
		double data = db.calculateTotalVolume("binance", "17-12-2023");
		System.out.print(data);
		
		
		
	}
}
