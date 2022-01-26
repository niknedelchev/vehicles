package com.nn.vehicles.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "repairs")
public class Repair {

	@Id
	@Column(name = "repair_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "repair_date")
	private LocalDate repairDate;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "repair_time")
	private LocalTime repairTime;
	
	@DecimalMin(value = "0.00", message = "Price cannot be negative.")
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "repair_shop_id", nullable = false)
	private RepairShop repairShop;
	
	@ManyToOne
	@JoinColumn(name = "repair_type_id", nullable = false)
	private RepairType repairType;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id", nullable=true)
	private Vehicle	vehicle;
	
	@Column(name = "is_processed")
	private boolean isProcessed;
	
	public Repair() {
	}

	public Repair(LocalDate repairDate, LocalTime repairTime, double price, RepairShop repairShop, RepairType repairType, Vehicle vehicle, boolean isProcessed) {
		this.repairDate = repairDate;
		this.repairTime = repairTime;
		this.price = price;
		this.repairShop = repairShop;
		this.repairType = repairType;
		this.vehicle = vehicle;
		this.isProcessed = isProcessed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(LocalDate repairDate) {
		this.repairDate = repairDate;
	}
	
	public LocalTime getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(LocalTime repairTime) {
		this.repairTime = repairTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public RepairShop getRepairShop() {
		return repairShop;
	}

	public void setRepairShop(RepairShop repairShop) {
		this.repairShop = repairShop;
	}

	public RepairType getRepairType() {
		return repairType;
	}

	public void setRepairType(RepairType repairType) {
		this.repairType = repairType;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
}
