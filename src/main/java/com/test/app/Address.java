package com.test.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Address {

	@Id
	@Column
	int A_id;
	@Column
	String details;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(int a_id, String details) {
		super();
		A_id = a_id;
		this.details = details;
	}

	public int getA_id() {
		return A_id;
	}

	public void setA_id(int a_id) {
		A_id = a_id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Address [A_id=" + A_id + ", details=" + details + "]";
	}

}
