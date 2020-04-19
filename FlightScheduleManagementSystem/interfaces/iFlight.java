package interfaces;

import java.lang.*;

import entity.*;

public interface iFlight
{
	public void insertFlight(Flight f);
	public void removeFlight(String flightNumber);
	public void updateFlight(Flight f);
	public Flight searchFlight(String flightNumber);
	public String [][] searchSchedule(String date);
	public String[][] getAllFlight();
}