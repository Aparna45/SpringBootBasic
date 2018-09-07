package com.test.app;

import java.util.List;

public interface CustomerService {

	public Customer create(Customer Customer);

	public void delete(int id);

	public List<Customer> findAll();

	public Customer update(Customer Customer);

	public Customer findOne(int id);

}
