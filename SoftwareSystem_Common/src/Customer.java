public class Customer extends Person{
	
	private int id;

	Customer (String firstName, String lastName, String address, String phoneNum){
		
		this.id = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
		
	}
	
	Customer (int id, String firstName, String lastName, String address, String phoneNum){
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
		
	}

	public int getId() {
		return id;
	}

}
