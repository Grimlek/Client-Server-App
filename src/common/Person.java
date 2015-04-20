package common;

import java.io.Serializable;

public final class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstName;
	protected String lastName;
	protected String address;
	protected String phoneNum;
	
	public Person (String firstName, 
			String lastName, 
			String address, 
			String phoneNum) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName () {
		return lastName;
	}
	
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress () {
		return address;
	}
	
	public void setAddress (String address) {
		this.address = address;
	}
	
	public String getPhoneNum () {
		return phoneNum;
	}
	
	public void setPhoneNum (String phoneNum) {
		this.phoneNum = phoneNum;
	}	
}
