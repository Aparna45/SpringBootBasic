package com.test.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class Customer {

	@Id
	private int id;
	@Column
	private String name;
	@Column
	private int level;

	@OneToOne
	private Address address;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String name, int level, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", level=" + level + ", address=" + address + "]";
	}
	
	
}
