package com.ho.autotest.model;

public class Vehicle 
{
	String regNum;
	String make;
	String colour;
	String result;
	
	public Vehicle()
	{
		
	}
	

	public Vehicle(String regNum, String make, String color)
	{
		this.regNum = regNum;
		this.colour = color;
		this.make = make;
	}
	
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String color) {
		this.colour = color;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	

	public String toString(){
		return regNum+","+make+","+colour;
	}
	
	public String toCSVString(){
		return regNum+","+make+","+colour+","+result;
	}
		
}
