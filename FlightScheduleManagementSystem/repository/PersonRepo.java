package repository;

import java.lang.*;

import entity.*;
import interfaces.*;

public class PersonRepo implements iPerson
{
	DatabaseConnection dbc;
	
	public PersonRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public Person getPerson(String personId, String pass)
	{
		Person person = null;
		String query = "SELECT personId, password, status,code FROM person WHERE personId='"+personId+"' AND password='"+pass+"';";
		try
		{
			//System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				person = new Person();
				person.setPersonId(dbc.result.getString("personId"));
				person.setPassword(dbc.result.getString("password"));
				person.setStatus(dbc.result.getInt("status"));
				person.setCode(dbc.result.getInt("code"));
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		dbc.closeConnection();
		return person;
	}

	public Person verifyPerson(String personId,int code)
	{
		Person person = null;
		String query = "SELECT personId, password, status,code FROM person WHERE code='"+code+"' AND personId= '"+personId+"';";
		try
		{
			//System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				person = new Person();
				person.setPersonId(dbc.result.getString("personId"));
				person.setPassword(dbc.result.getString("password"));
				person.setStatus(dbc.result.getInt("status"));
				person.setCode(dbc.result.getInt("code"));
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		dbc.closeConnection();
		return person;
	}	

	public void updatePerson(Person p)
	{
		//String query = "UPDATE person SET personId='"+p.getPersonId()+"','"+p.getPassword()+"',status = "+p.getStatus()+","+p.getCode()+" WHERE personId='"+p.getPersonId()+"';";
		String query = "UPDATE person SET personId='"+p.getPersonId()+"', password = '"+p.getPassword()+"', status = "+p.getStatus()+" WHERE personId='"+p.getPersonId()+"'";
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			System.out.println(query);
			dbc.closeConnection();
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		
	}

	public void insertPerson(Person p)
	{
		String query = "INSERT INTO person VALUES ('"+p.getPersonId()+"','"+p.getPassword()+"',"+p.getStatus()+","+p.getCode()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}		
	}
	
	public void deletePerson(String personId)
	{
		String query = "DELETE from person  WHERE personId='"+personId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
}