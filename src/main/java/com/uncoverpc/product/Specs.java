package com.uncoverpc.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Specs {
	@JsonProperty("use")
	private String use;
	@JsonProperty("battery")
	private int battery;
	@JsonProperty("color")
	private String color;
	@JsonProperty("link")
	private String link;
	@JsonProperty("ram")
	private int ram;
	@JsonProperty("screenSize")
	private int screenSize;
	@JsonProperty("storage")
	private int storage;
	
	public Specs(String use, int battery, String color, String link, int ram, int screenSize, int storage) {
		super();
		this.use = use;
		this.battery = battery;
		this.color = color;
		this.link = link;
		this.ram = ram;
		this.screenSize = screenSize;
		this.storage = storage;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	@Override
	public String toString() {
		return "Specs [use=" + use + ", battery=" + battery + ", color=" + color + ", link=" + link + ", ram=" + ram
				+ ", screenSize=" + screenSize + ", storage=" + storage + "]";
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}
	
	
	
}
