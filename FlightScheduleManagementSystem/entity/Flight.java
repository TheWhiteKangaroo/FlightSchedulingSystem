package entity;

import java.lang.*;

public class Flight
{
	private String flightNumber;
	private String journeyFrom;
	private String journeyDate;
	private int departureTimeHrs;
	private int departureTimeMins;
	private String journeyTo;
	private String arrivalDate;
	private int arrivalTimeHrs;
	private int arrivalTimeMins;
	
	public Flight(){}
	public Flight(String flightNumber,String journeyFrom,String journeyDate, int departureTimeHrs, int departureTimeMins,String journeyTo,String arrivalDate,int arrivalTimeHrs,int arrivalTimeMins)
	{
		this.flightNumber = flightNumber;
		this.journeyFrom = journeyFrom;
		this.journeyDate = journeyDate;
		this.departureTimeHrs = departureTimeHrs;
		this.departureTimeMins=departureTimeMins;
		this.journeyTo = journeyTo;
		this.arrivalDate = arrivalDate;
		this.arrivalTimeHrs = arrivalTimeHrs;
		this.arrivalTimeMins = arrivalTimeMins;
		
	}
	//setMethods
	public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber;}
	public void setJourneyFrom(String journeyFrom) { this.journeyFrom = journeyFrom; }
	public void setJourneyDate(String journeyDate){this.journeyDate=journeyDate;}
	public void setDepartureTimeHrs(int departureTimeHrs) {this.departureTimeHrs = departureTimeHrs;}
	public void setDepartureTimeMins(int departureTimeMins) {this.departureTimeMins = departureTimeMins;}
	public void setJourneyTo(String journeyTo) {this.journeyTo = journeyTo; }
	public void setArrivalDate(String arrivalDate){this.arrivalDate=arrivalDate;}
	public void setArrivalTimeHrs(int arrivalTimeHrs) {this.arrivalTimeHrs = arrivalTimeHrs;}
	public void setArrivalTimeMins(int arrivalTimeMins) {this.arrivalTimeMins = arrivalTimeMins;}

	//getMethods
	public String getFlightNumber(){ return flightNumber;}
	public String getJourneyFrom() {return journeyFrom;}
	public String getJourneyDate(){return journeyDate;}
	public int getDepartureTimeHrs() {return departureTimeHrs;}
	public int getDepartureTimeMins() {return departureTimeMins;}
	public String getJourneyTo() {return journeyTo;}
	public String getArrivalDate(){return arrivalDate;}
	public int getArrivalTimeHrs(){return arrivalTimeHrs;}
	public int getArrivalTimeMins() {return arrivalTimeMins;}
}