package repository;

import java.lang.*;
import java.util.ArrayList;

import entity.*;
import interfaces.*;

public class EmployeeRepo implements iEmployee
{
	DatabaseConnection dbc;
	
	public EmployeeRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertEmployee(Employee e)
	{
		String query = "INSERT INTO employee VALUES ('"+e.getEmployeeId()+"','"+e.getEmployeeName()+"','"+e.getEmployeeDesignation()+"',"+e.getEmployeeSalary()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			//System.out.println(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	
	public void updateEmployee(Employee e)
	{
		String query = "UPDATE employee SET employeeName='"+e.getEmployeeName()+"', designation = '"+e.getEmployeeDesignation()+"', salary = "+e.getEmployeeSalary()+" WHERE empId='"+e.getEmployeeId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}


	public Employee searchEmployee(String employeeId)
	{
		Employee emp = null;
		String query = "SELECT `empId`, `employeeName`, `designation`, `salary` FROM `employee` WHERE `empId`='"+employeeId+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String name = dbc.result.getString("employeeName");
				String designation = dbc.result.getString("designation");
				double salary = dbc.result.getDouble("salary");
				
				emp = new Employee();
				emp.setEmployeeId(employeeId);
				emp.setEmployeeName(name);
				emp.setEmployeeDesignation(designation);
				emp.setEmployeeSalary(salary);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return emp;
	}


	public void removeEmployee(String employeeId)
	{
		String query = "DELETE from employee WHERE empId='"+employeeId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}


	public String[][] getAllEmployee()
	{
		ArrayList<Employee> ar = new ArrayList<Employee>();
		String query = "SELECT * FROM employee;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String empId = dbc.result.getString("empId");
				String name = dbc.result.getString("employeeName");
				String designation = dbc.result.getString("designation");
				double salary = dbc.result.getDouble("salary");
				
				Employee e = new Employee(empId,name,designation,salary);
				ar.add(e);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][4];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Employee)obj[i]).getEmployeeId();
			data[i][1] = ((Employee)obj[i]).getEmployeeName();
			data[i][2] = ((Employee)obj[i]).getEmployeeDesignation();
			data[i][3] = (((Employee)obj[i]).getEmployeeSalary())+"";
		}
		return data;
	}
}