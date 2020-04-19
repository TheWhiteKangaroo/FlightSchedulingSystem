package interfaces;

import java.lang.*;
import entity.*;

public interface iPerson
{
	Person getPerson(String personId, String password);
	void insertPerson(Person p);
	void updatePerson(Person p);
	void deletePerson(String personId);
	Person verifyPerson(String personId,int code);
}