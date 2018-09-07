package com.test.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerrepo;

	@Override
	public Customer create(Customer customer) {
		return customerrepo.save(customer);
	}

	@Override
	public Customer findOne(int id) {
		return customerrepo.findOne(id);
	}

	@Override
	public void delete(int id) {
		customerrepo.delete(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerrepo.findAll();
	}

	@Override
	public Customer update(Customer customer) {
		return customerrepo.save(customer);
	}

}
