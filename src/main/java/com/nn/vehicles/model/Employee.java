package com.nn.vehicles.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name")
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name = "qualification_id", nullable = false)
	private Qualification qualification;
	
	@ManyToOne
	@JoinColumn(name = "repair_shop_id", nullable = false)
	private RepairShop repairShop;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", nullable = true)
	User user;

	public Employee() {
	}
	

	public Employee(String firstName, String lastName, Qualification qualification, RepairShop repairShop, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.qualification = qualification;
		this.repairShop = repairShop;
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Qualification getQualification() {
		return qualification;
	}


	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}


	public RepairShop getRepairShop() {
		return repairShop;
	}


	public void setRepairShop(RepairShop repairShop) {
		this.repairShop = repairShop;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}