package net.viralpatel.spring.dao;

import java.util.List;

import net.viralpatel.spring.model.Customer;

public interface CustomerDAO {

	/**
	 * Returns list of customers from dummy database.
	 * 
	 * @return list of customers
	 */
	List<Customer> getAll();

	/**
	 * Return customer object for given id from dummy database. If customer is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            customer id
	 * @return customer object for given id
	 */
	Customer get(Long id);
	
	/**
	 * Create new customer in dummy database. Updates the id and insert new
	 * customer in list.
	 * 
	 * @param customer
	 *            Customer object
	 * @return customer object with updated id
	 */
	Customer create(Customer customer);

	/**
	 * Delete the customer object from dummy database. If customer not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the customer id
	 * @return id of deleted customer object
	 */
	Long delete(Long id);

	/**
	 * Update the customer object for given id in dummy database. If customer
	 * not exists, returns null
	 * 
	 * @param id
	 * @param customer
	 * @return customer object with id
	 */
	Customer update(Long id, Customer customer);
	
	/**
	 * Checks if the customer already exists in dummy database.
	 * 
	 * @param customer
	 *            Customer object
	 * @return true if customer already exists and false if not
	 */
	boolean exists(Customer customer);
	
	Customer findById(Long id);

	Customer findByName(String name);

}
