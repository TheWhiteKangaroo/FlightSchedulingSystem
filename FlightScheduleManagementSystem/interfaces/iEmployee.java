package interfaces;

import java.lang.*;

import entity.*;

public interface iEmployee
{
	public void insertEmployee(Employee e);
	public void removeEmployee(String employeeId);
	public void updateEmployee(Employee e);
	public Employee searchEmployee(String employeeId);
	public String[][] getAllEmployee();
}