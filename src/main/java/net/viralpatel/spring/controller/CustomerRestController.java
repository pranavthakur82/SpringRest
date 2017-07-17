package net.viralpatel.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.viralpatel.spring.dao.CustomerDAO;
import net.viralpatel.spring.model.Customer;

@RestController
public class CustomerRestController {

	private final Logger LOG = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	private CustomerDAO customerDAO;

	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		LOG.info("getting all customers");
		List<Customer> customerList = customerDAO.getAll();
		
		if(customerList == null || customerList.isEmpty())
		{
			LOG.info("no customers found");
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
		LOG.info("getting customer with id: {}", id);
		Customer customer = customerDAO.get(id);
		
		if (customer == null) {
			LOG.info("customer with id {} not found", id);
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/customers")
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		LOG.info("creating new customer: {}", customer);
		
		if(customerDAO.exists(customer))
		{
			LOG.info("a customer with name " + customer.getFirstName() + " " + customer.getLastName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		customerDAO.create(customer);

		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		
		LOG.info("deleting customer with id: {}", id);
		Customer customer = customerDAO.findById(id);
		
		if (null == customer) {
			LOG.info("Unable to delete. Customer with id {} not found", id);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		customerDAO.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		LOG.info("updating customer: {}", customer);
		
		if (null == customer) {
			LOG.info("Customer with id {} not found", id);
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		customer = customerDAO.update(id, customer);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

}