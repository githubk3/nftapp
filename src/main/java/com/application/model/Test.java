package com.application.model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Bucket db = new Bucket();
		double data = db.calculateTotalVolume("opensea", "22/12/2023");
		System.out.println(data);
		
	}
}
