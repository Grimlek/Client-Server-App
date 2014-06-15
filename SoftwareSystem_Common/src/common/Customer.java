package common;

import java.io.Serializable;


public class Customer extends Person implements Serializable{
	
	
	private int id;

	Customer (String firstName, String lastName, String address, String phoneNum){
		
		this.id = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
		
	}
	
	public Customer (int id, String firstName, String lastName, String address, String phoneNum){
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
		
	}

	public int getId() {
		return id;
	}
	
	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	    // Use system property rather than \n for better compatibility
	    String nl = System.getProperty("line.separator");
	    
	    sb.append(this.firstName).append(" ").append(this.lastName).append(nl);
	    sb.append(this.address).append(nl);
	    sb.append(this.phoneNum).append(nl);
	    
	    return sb.toString();
	    
	  }

}
