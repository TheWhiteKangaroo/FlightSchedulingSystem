package entity;

import java.lang.*;

public class Employee
{
	private String employeeId;
	private String employeeName;
	private String employeeDesignation;
	private double employeeSalary;
	
	public Employee(){}

	public Employee(String employeeId,String employeeName,String employeeDesignation,double employeeSalary)
	{
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDesignation = employeeDesignation;
		this.employeeSalary = employeeSalary;
	}
	//setMethods
	public void setEmployeeId(String employeeId) {this.employeeId = employeeId;}
	public void setEmployeeName(String employeeName) {this.employeeName = employeeName;}
	public void setEmployeeDesignation(String employeeDesignation) {this.employeeDesignation = employeeDesignation;}
	public void setEmployeeSalary(double employeeSalary) {this.employeeSalary = employeeSalary;}
	
	//getMethods
	public String getEmployeeId(){ return employeeId; }
	public String getEmployeeName() { return employeeName;}
	public String getEmployeeDesignation() { return employeeDesignation;}
	public double getEmployeeSalary() { return employeeSalary; }
}