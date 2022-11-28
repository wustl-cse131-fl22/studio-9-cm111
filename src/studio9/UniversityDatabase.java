package studio9;

import assignment7.Student;

import java.util.HashMap;
import java.util.Map;

public class UniversityDatabase {
	//TODO: Complete this class according to the studio instructions
	private final Map<String,Student> a; 
	
	
	public UniversityDatabase(String string, Student student){
		a = new HashMap<>();
	}
	
	public void addStudent(String accountName, Student student) {
		// TODO
		a.put(accountName, student);

	}

	public int getStudentCount() {
		// TODO
		return a.size();
	}

	public String lookupFullName(String accountName) {
		// TODO: Complete according to studio instructions
		
		if(a.get(accountName)== null) {
			return null;
		} else {
			return a.get(accountName).getFullName();
		}
		
	}

	public double getTotalBearBucks() {
		// TODO
		return 0.0;
	}
}
