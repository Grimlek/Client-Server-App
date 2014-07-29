package common;

import java.io.Serializable;

public final class Customer extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;

	public Customer (int id, String firstName, String lastName, String address, String phoneNum){
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
		
	}

	public int getId () {
		return id;
	}
}
