package assignment7;

//TODO: Copy a working Student class from Assignment 7 over this file.
public class Student {
	
	private String firstName;
	private String lastName;
	private int SID;
	private int attemptedCredits;
	private int passingCredits;
	private double totalGradeQualityPoints;
	private double bearBucksBalance;
	
	//constructors
	public Student(String initFirstName, String initLastName, int initSID) {
		firstName = initFirstName;
		lastName = initLastName;
		SID = initSID;
		attemptedCredits = 0;
		passingCredits = 0;
		totalGradeQualityPoints = 0.0;
		bearBucksBalance = 0;
	}
	
	//getters
	/**
	 * get the full name of this student
	 * @return the full name of this student
	 */
	public String getFullName() { return firstName + " " + lastName; }
	
	/**
	 * get the id of this student
	 * @return student id
	 */
	public int getId() { return SID; }
	
	/**
	 * get the total attempted credits of this student
	 * @return the total attempted credits
	 */
	public int getTotalAttemptedCredits() { return attemptedCredits; }
	
	/**
	 * get the total passing credits of this student
	 * @return the total passing credits of this student
	 */
	public int getTotalPassingCredits() { return passingCredits; }
	
	/**
	 * get the bearbucks balance of this student
	 * @return the bearbucks balance of this student
	 */
	public double getBearBucksBalance() { return bearBucksBalance; }
	
	
	//setters
	/**
	 * change the first name of this student
	 * @param changeFirstName 
	 */
	public void setFirstName(String changeFirstName) {
		firstName = changeFirstName;
	}
	
	/**
	 * change the last name of this student
	 * @param changeLastName
	 */
	public void setLastName(String changeLastName) {
		lastName = changeLastName;
	}
	
	/**
	 * increase the amount of balance for this student
	 * @param amount
	 */
	public void depositBearBucks(double amount) {
		bearBucksBalance += amount;
	}
	
	/**
	 * decrease the amount of balance for this student
	 * @param amount
	 */
	public void deductBearBucks(double amount) {
		bearBucksBalance -= amount;
	}

	/**
	 * set the amount of balance for this student
	 * @param amount
	 */
	public void setBearBucksBalance(double amount) {
		bearBucksBalance = amount;
	}
	
	
	//methods
	/**
	 * get the class standing of this student based on the total passing credits
	 * @return "first year" if the passing credits is less than 30;
	 * 			"sophomore" if the passing credits is equal to or greater than 30 and less than 60;
	 * 			"junior" if the passing credits is equal to or greater than 60 and less than 90;
	 * 			"senior" if the passing credits is equal to or greater than 90;
	 */
	public String getClassStanding() {
		if (passingCredits < 30) {
			return "First Year";
		}
		else if (passingCredits >= 30 && passingCredits < 60) {
			return "Sophomore";
		}
		else if (passingCredits >= 60 && passingCredits < 90) {
			return "Junior";
		} else {
			return "Senior";
		}
	}
	
	/**
	 * update relevant variables including the total passing credits, the total attempted credits and total grade quality points based on the grade and credits;
	 * if the grade is equal to or greater than 1.7, the credits will be added into the total passing credits;
	 * no matter what grade it is, we will update the total attempted credits and total grade quality points;
	 * -- add credits to the total attempted credits; add (grade * credits) to the total grade quality points;
	 * @param grade - the course grade(0-4)
	 * @param credits - the number of credits for a course
	 */
	public void submitGrade(double grade, int credits) {
		if (grade >= 1.7) {
			passingCredits += credits;
		}
		attemptedCredits += credits;
		totalGradeQualityPoints += grade * credits;
	}
	
	
	/**
	 * calculate the GPA of this student
	 * GPA = the total grade quality points / the attempted credits
	 * @return the GPA of this student
	 */
	public double calculateGradePointAverage() {
		return totalGradeQualityPoints / attemptedCredits;
	}
	
	
	/**
	 * check whether this student is eligible for PhiBetaKappa based on the total passing credits and GPA of this student;
	 * it is eligible if: 1) passing credit is at least 98 and gpa is at least 3.6; 2) passing credit is at least 75 and gpa is at least 3.8;
	 * @return if the student is eligible - return true; if not, return false;
	 */
	public boolean isEligibleForPhiBetaKappa() {
		if (passingCredits>= 98 && calculateGradePointAverage() >= 3.6) {
			return true;
		}
		else if(passingCredits>= 75 && calculateGradePointAverage() >= 3.8) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * calculate how much this student can cash out his/her money in account; set balance to 0 after cash out;
	 * steps:
	 * --calculate the amount minus admin fee ("original balance - 10") first, then compare it with 0: if less than 0, return 0;if greater than 0, return that number;
	 * --set balance to 0 after cash out;
	 * @return the amount of money that this student can cash out;
	 */
	public double cashOutBearBucks() {
		double results = Math.max(0, bearBucksBalance - 10); 
		this.bearBucksBalance = 0.0; 
		return results;	
	}
	
	
	/**
	 * create a student class for this student's child.
	 * steps:
	 * --set the child's last name: if isHyphenated is true, then the child's last name is "this student's last name - other student's last name; if false, the child's last name is this student's last name;
	 * --set the child's balance: the child's balance = parents' total balance when they cash out;
	 * --create a new student class, then assign the last name, balance and given id to that new Student class, then return that class
	 * @param firstName - the child's first name;
	 * @param other -  the other parent[the other student class];
	 * @param isHyphenated - the child's last name is hyphenated or not; --determine the child's last name;
	 * @param id -- the child's student id;
	 * @return the child student class
	 */
	public Student createLegacy(String firstName, Student other, boolean isHyphenated, int id) { 
		String lname = "";
		if (isHyphenated == true) {
			lname = this.lastName + "-" + other.lastName;
		} else { 
			lname = this.lastName;
		}
		
		double balance = this.cashOutBearBucks() + other.cashOutBearBucks();
		
		Student child = new Student(firstName, lname, id);
		child.setBearBucksBalance(balance);
		
		return child;
	}
	
	
	/**
	 * return the full name and id of this student;
	 */
	public String toString() {
		return this.getFullName() + " " + this.getId();
	}
	
	
}

