package repository;

import java.lang.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class FlightRepo implements iFlight
{
	DatabaseConnection dbc;
	
	public FlightRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertFlight(Flight f)
	{
		String query = "INSERT INTO flights VALUES ('"+f.getFlightNumber()+"','"+f.getJourneyFrom()+"','"+f.getJourneyDate()+"',"+f.getDepartureTimeHrs()+","+f.getDepartureTimeMins()+",'"+f.getJourneyTo()+"','"+f.getArrivalDate()+"',"+f.getArrivalTimeHrs()+","+f.getArrivalTimeMins()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			//System.out.println(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void removeFlight(String flightNumber)
	{
		String query = "DELETE from flights WHERE flightNumber='"+flightNumber+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			//System.out.println(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void updateFlight(Flight f)
	{
		String query = "UPDATE `flights` SET `journeyFrom`= '"+f.getJourneyFrom()+"',`journeyDate`= '"+f.getJourneyDate()+"',`departureTimeHrs`='"+f.getDepartureTimeHrs()+"',`departureTimeMins`='"+f.getDepartureTimeMins()+"',`journeyTo`='"+f.getJourneyTo()+"',`arrivalDate`='"+f.getArrivalDate()+"',`arrivalTimeHrs`='"+f.getArrivalTimeHrs()+"',`arrivalTimeMins`='"+f.getArrivalTimeMins()+"' WHERE flightNumber='"+f.getFlightNumber()+"';";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			//System.out.println(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public Flight searchFlight(String flightNumber)
	{
		Flight flt=null;
		String query = "SELECT `journeyFrom`, `journeyDate`,`departureTimeHrs`, `departureTimeMins`, `journeyTo`,`arrivalDate`,`arrivalTimeHrs`,`arrivalTimeMins` FROM `flights` WHERE `flightNumber`='"+flightNumber+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			//System.out.println(query);
		
			while(dbc.result.next())
			{
				
				String journeyFrom = dbc.result.getString("journeyFrom");
				String journeyDate = dbc.result.getString("journeyDate");
				int departureTimeHrs = dbc.result.getInt("departureTimeHrs");
				int departureTimeMins = dbc.result.getInt("departureTimeMins");
				String journeyTo = dbc.result.getString("journeyTo");
				String arrivalDate = dbc.result.getString("arrivalDate");
				int arrivalTimeHrs = dbc.result.getInt("arrivalTimeHrs");
				int arrivalTimeMins = dbc.result.getInt("arrivalTimeMins");
				
				flt = new Flight();
				flt.setFlightNumber(flightNumber);
				flt.setJourneyFrom(journeyFrom);
				flt.setJourneyDate(journeyDate);
				flt.setDepartureTimeHrs(departureTimeHrs);
				flt.setDepartureTimeMins(departureTimeMins);
				flt.setJourneyTo(journeyTo);
				flt.setArrivalDate(arrivalDate);
				flt.setArrivalTimeHrs(arrivalTimeHrs);
				flt.setArrivalTimeMins(arrivalTimeMins);
			
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return flt;
	}
	public String [][] searchSchedule(String date){
		ArrayList<Flight> ar = new ArrayList<Flight>();
		String query = "SELECT `flightNumber`,`journeyFrom`, `journeyDate`,`departureTimeHrs`, `departureTimeMins`, `journeyTo`,`arrivalDate`,`arrivalTimeHrs`,`arrivalTimeMins` FROM `flights` WHERE `journeyDate`='"+date+"';";     
		try{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			//System.out.println(query);
			while(dbc.result.next()){
				String flightNumber = dbc.result.getString("flightNumber");
				String journeyFrom = dbc.result.getString("journeyFrom");
				String journeyDate = dbc.result.getString("journeyDate");
				int departureTimeHrs = dbc.result.getInt("departureTimeHrs");
				int departureTimeMins = dbc.result.getInt("departureTimeMins");
				String journeyTo = dbc.result.getString("journeyTo");
				String arrivalDate = dbc.result.getString("arrivalDate");
				int arrivalTimeHrs = dbc.result.getInt("arrivalTimeHrs");
				int arrivalTimeMins = dbc.result.getInt("arrivalTimeMins");

				Flight fght = new Flight(flightNumber,journeyFrom,journeyDate,departureTimeHrs,departureTimeMins,journeyTo,arrivalDate,arrivalTimeHrs,arrivalTimeMins);
				ar.add(fght);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();

		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][9];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Flight)obj[i]).getFlightNumber();
			data[i][1] = ((Flight)obj[i]).getJourneyFrom();
			data[i][2] = ((Flight)obj[i]).getJourneyDate();
			data[i][3] = Integer.toString(((Flight)obj[i]).getDepartureTimeHrs());
			data[i][4] = Integer.toString(((Flight)obj[i]).getDepartureTimeMins());
			data[i][5] = ((Flight)obj[i]).getJourneyTo();
			data[i][6] = ((Flight)obj[i]).getArrivalDate();
			data[i][7] = Integer.toString(((Flight)obj[i]).getArrivalTimeHrs());
			data[i][8] = Integer.toString(((Flight)obj[i]).getArrivalTimeMins());
		}
		return data;
	}


	public String [][] getAllFlight(){
		ArrayList<Flight> ar = new ArrayList<Flight>();
		String query = "SELECT `flightNumber`,`journeyFrom`, `journeyDate`,`departureTimeHrs`, `departureTimeMins`, `journeyTo`,`arrivalDate`,`arrivalTimeHrs`,`arrivalTimeMins` FROM `flights`;";     
		try{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			//System.out.println(query);
			while(dbc.result.next()){
				String flightNumber = dbc.result.getString("flightNumber");
				String journeyFrom = dbc.result.getString("journeyFrom");
				String journeyDate = dbc.result.getString("journeyDate");
				int departureTimeHrs = dbc.result.getInt("departureTimeHrs");
				int departureTimeMins = dbc.result.getInt("departureTimeMins");
				String journeyTo = dbc.result.getString("journeyTo");
				String arrivalDate = dbc.result.getString("arrivalDate");
				int arrivalTimeHrs = dbc.result.getInt("arrivalTimeHrs");
				int arrivalTimeMins = dbc.result.getInt("arrivalTimeMins");

				Flight fght = new Flight(flightNumber,journeyFrom,journeyDate,departureTimeHrs,departureTimeMins,journeyTo,arrivalDate,arrivalTimeHrs,arrivalTimeMins);
				ar.add(fght);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();

		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][9];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Flight)obj[i]).getFlightNumber();
			data[i][1] = ((Flight)obj[i]).getJourneyFrom();
			data[i][2] = ((Flight)obj[i]).getJourneyDate();
			data[i][3] = Integer.toString(((Flight)obj[i]).getDepartureTimeHrs());
			data[i][4] = Integer.toString(((Flight)obj[i]).getDepartureTimeMins());
			data[i][5] = ((Flight)obj[i]).getJourneyTo();
			data[i][6] = ((Flight)obj[i]).getArrivalDate();
			data[i][7] = Integer.toString(((Flight)obj[i]).getArrivalTimeHrs());
			data[i][8] = Integer.toString(((Flight)obj[i]).getArrivalTimeMins());
		}
		return data;
	}
	

}

