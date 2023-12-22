package com.application.model;

public class NFTInterval {
	private double floorPrice;
	private double change;
	private double volume;
	private int sales;
	private int trending;
	private String datetime;

	public NFTInterval(double floorPice, double change, double volume, int sales, int trending, String datetime) {
		this.floorPrice = floorPice;
		this.change = change;
		this.volume = volume;
		this.sales = sales;
		this.trending = trending;
		this.datetime = datetime;
	}

	public double getFloorPrice() {
		return floorPrice;
	}

	public double getChange() {
		return change;
	}

	public int getSales() {
		return sales;
	}

	public int getTrending() {
		return trending;
	}

	public String getDatetime() {
		return datetime;
	}

	public double getVolume() {
		return volume;
	}
}
