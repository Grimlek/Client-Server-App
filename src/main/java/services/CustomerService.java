package services;

import java.util.List;
import javax.persistence.TypedQuery;
import entities.Customer;

/**
 * Created by csexton on 7/18/16.
 */
public class CustomerService extends GenericService<Customer, Integer>{

    public CustomerService() {
        super(Customer.class);
    }

	public List<Customer> findByAddress(String address) {
		 TypedQuery<Customer> query =
				 super.getManager().createNamedQuery("Customer.findByAddress", Customer.class);
		 query.setParameter("address", address);
		 return query.getResultList();
	}

	public List<Customer> findByPhoneNum(String phoneNum) {
		 TypedQuery<Customer> query =
				 super.getManager().createNamedQuery("Customer.findByPhoneNum", Customer.class);
		 query.setParameter("phoneNum", phoneNum);
		 return query.getResultList();
	}
}
