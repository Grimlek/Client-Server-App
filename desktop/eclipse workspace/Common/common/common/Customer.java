package common;

import java.io.Serializable;

public final class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;
	protected final Person person;

	public Customer (int id, 
			Person person)
	{
		this.id = id;
		this.person = person;
	}

	public int getId () {
		return id;
	}
	
	public Person getPerson () {
		return person;
	}
}
