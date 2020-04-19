package entity;

import java.lang.*;

public class Person
{
	private String personId;
	private String password;
	private int status;
	private int code;
	
	public Person(){}
	public Person(String personId, String password, int status,int code)
	{
		this.personId = personId;
		this.password = password;
		this.status = status;
		this.code=code;
	}
	
	public void setPersonId(String personId){this.personId = personId;}
	public void setPassword(String password){this.password = password;}
	public void setStatus(int status){this.status = status;}
	public void setCode(int code){this.code=code;}
	
	public String getPersonId(){return personId;}
	public String getPassword(){return password;}
	public int getStatus(){return status;}
	public int getCode(){return code;}
}