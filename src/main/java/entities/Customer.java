package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.GenerationType;

/**
 * Created by csexton on 7/16/16.
 */
@Entity
@Table(name = "Customer")
@NamedQueries({
    @NamedQuery(name="Customer.findByAddress",
                query="SELECT c FROM Customer c where c.address = :address"),
    @NamedQuery(name="Customer.findByPhoneNum",
    			query="SELECT c FROM Customer c where c.phoneNum = :phoneNum")
})
public class Customer {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNum;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
