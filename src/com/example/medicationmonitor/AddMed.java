package com.example.medicationmonitor;


public class AddMed {
	
	private String name;


	public AddMed() {
		this.setName(name);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	  @Override
	    public String toString() {
	        return "[Name=" + name + "]";
	    }
	
}








