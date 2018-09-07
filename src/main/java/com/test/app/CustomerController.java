package com.test.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/rest")
public class CustomerController {

	@Autowired
	CustomerService customerservice;

	@Autowired
	CustomerRepo customerrepo;

	// Display list

	@RequestMapping(value = "/Customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllCustomers() {
		List<Customer> customers = customerservice.findAll();
		if (customers.isEmpty()) {

			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	// Single record retrieval

	@RequestMapping(value = "/Customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id) {
		System.out.println("Fetching Customer with id " + id);
		Customer customer = customerservice.findOne(id);
		{
			if (customer == null) {
				System.out.println("Customer with id " + id + " not found");
				return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
	}
	// new record creation

	@RequestMapping(value = "/Customer", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + customer.getName());
		customerservice.create(customer);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Updating existed record

	@RequestMapping(value = "/Customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> update(@PathVariable("id") int id, @RequestBody Customer customer) {
		System.out.println("Updating Customer " + id);

		Customer currentCustomer = customerservice.findOne(id);
		currentCustomer.setName(customer.getName());
		currentCustomer.setId(customer.getId());
		currentCustomer.setLevel(customer.getLevel());

		customerservice.update(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

	// Deleting a record

	@RequestMapping(value = "/Customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> delete(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting Customer with id " + id);

		Customer customer = customerservice.findOne(id);
		if (customer == null) {
			System.out.println("Unable to delete. Customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		int id1 = (int) id;
		customerservice.delete(id1);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

}
