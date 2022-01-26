package com.nn.vehicles.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.BrandModel;
import com.nn.vehicles.model.Customer;
import com.nn.vehicles.model.Employee;
import com.nn.vehicles.model.Qualification;
import com.nn.vehicles.model.Repair;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.model.RepairType;
import com.nn.vehicles.model.Role;
import com.nn.vehicles.model.User;
import com.nn.vehicles.model.Vehicle;
import com.nn.vehicles.repository.BrandModelRepository;
import com.nn.vehicles.repository.BrandRepository;
import com.nn.vehicles.repository.CustomerRepository;
import com.nn.vehicles.repository.EmployeeRepository;
import com.nn.vehicles.repository.QualificationRepository;
import com.nn.vehicles.repository.RepairRepository;
import com.nn.vehicles.repository.RepairShopRepository;
import com.nn.vehicles.repository.RepairTypeRepository;
import com.nn.vehicles.repository.UserRepository;
import com.nn.vehicles.repository.VehicleRepository;

@Profile("!test")
@Service
public class Initializer implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private BrandModelRepository brandModelRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private QualificationRepository qualificationRepository;
	@Autowired
	private RepairShopRepository repairShopRepository;
	@Autowired
	private RepairTypeRepository repairTypeRepository;
	@Autowired
	private RepairRepository repairRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private UserService userService;
	

	@Override
	public void run(String... args) throws Exception {
		
		List<Brand> brands = new ArrayList<>();
		List<BrandModel> brandModels = new ArrayList<>();
		List<RepairType> repairTypes = new ArrayList<>();
		List<Qualification> qualifications = new ArrayList<>();
		List<RepairShop> repairShops = new ArrayList<>();
		List<Vehicle> vehicles = new ArrayList<>();
		List<Repair> repairs = new ArrayList<>();
		
		Brand brand1 = new Brand("Mercedes", null);
		Brand brand2 = new Brand("BMW", null);
		Brand brand3 = new Brand("Mazda", null);
		Brand brand4 = new Brand("Opel", null);
		Brand brand5 = new Brand("Audi", null);
		
		brands.add(brand1);
		brands.add(brand2);
		brands.add(brand3);
		brands.add(brand4);
		brands.add(brand5);
		
		BrandModel brandModel1 = new BrandModel("C class", brand1, null);
		BrandModel brandModel2 = new BrandModel("G class", brand1, null);
		BrandModel brandModel3 = new BrandModel("520", brand2, null);
		BrandModel brandModel4 = new BrandModel("X5", brand2, null);
		BrandModel brandModel5 = new BrandModel("6", brand3, null);
		BrandModel brandModel6 = new BrandModel("3", brand3, null);
		BrandModel brandModel7 = new BrandModel("Insignia", brand4, null);
		BrandModel brandModel8 = new BrandModel("Vectra", brand4, null);
		BrandModel brandModel9 = new BrandModel("A3", brand5, null);
		BrandModel brandModel10 = new BrandModel("A6", brand5, null);
		
		brandModels.add(brandModel1);
		brandModels.add(brandModel2);
		brandModels.add(brandModel3);
		brandModels.add(brandModel4);
		brandModels.add(brandModel5);
		brandModels.add(brandModel6);
		brandModels.add(brandModel7);
		brandModels.add(brandModel8);
		brandModels.add(brandModel9);
		brandModels.add(brandModel10);
		
		this.brandRepository.saveAll(brands);
		this.brandModelRepository.saveAll(brandModels);

		
		RepairType rType1 = new RepairType("Windows change", null, null);
		RepairType rType2 = new RepairType("Outside body", null, null);		
		RepairType rType3 = new RepairType("Oil and filters replacement", null, null);
		RepairType rType4 = new RepairType("Brakes fix", null, null);
		RepairType rType5 = new RepairType("Exhaust system fix", null, null);
		RepairType rType6 = new RepairType("Gears fix", null, null);
		RepairType rType7 = new RepairType("Motor fix", null, null);
		RepairType rType8 = new RepairType("Electric system fix", null, null);
		
		repairTypes.add(rType1);
		repairTypes.add(rType2);
		repairTypes.add(rType3);
		repairTypes.add(rType4);
		repairTypes.add(rType5);
		repairTypes.add(rType6);
		repairTypes.add(rType7);
		repairTypes.add(rType8);
	
		Qualification qual1 = new Qualification("A", null, null);
		Qualification qual2 = new Qualification("B", null, null);
		Qualification qual3 = new Qualification("C", null, null);
		Qualification qual4 = new Qualification("D", null, null);
		
		Set<RepairType> quals_and_rTypes_1 = new HashSet<>();
		quals_and_rTypes_1.add(rType1);
		quals_and_rTypes_1.add(rType2);
		quals_and_rTypes_1.add(rType3);
		
		Set<RepairType> quals_and_rTypes_2 = new HashSet<>();
		quals_and_rTypes_2.add(rType3);
		quals_and_rTypes_2.add(rType4);
		quals_and_rTypes_2.add(rType5);
		
		Set<RepairType> quals_and_rTypes_3 = new HashSet<>();
		quals_and_rTypes_3.add(rType4);
		quals_and_rTypes_3.add(rType5);
		quals_and_rTypes_3.add(rType6);
		
		Set<RepairType> quals_and_rTypes_4 = new HashSet<>();
		quals_and_rTypes_4.add(rType4);
		quals_and_rTypes_4.add(rType5);
		quals_and_rTypes_4.add(rType6);
		quals_and_rTypes_4.add(rType7);
		quals_and_rTypes_4.add(rType8);
	
		qual1.setRepairTypes(quals_and_rTypes_1);
		qual2.setRepairTypes(quals_and_rTypes_2);
		qual3.setRepairTypes(quals_and_rTypes_3);
		qual4.setRepairTypes(quals_and_rTypes_4);
		
		qualifications.add(qual1);
		qualifications.add(qual2);
		qualifications.add(qual3);
		qualifications.add(qual4);

		this.repairTypeRepository.saveAll(repairTypes);
		this.qualificationRepository.saveAll(qualifications);
		
		RepairShop rShop1 = new RepairShop(null, null, "Musagenitsa", null);
		RepairShop rShop2 = new RepairShop(null, null, "Dianabad", null);
		RepairShop rShop3 = new RepairShop(null, null, "Mladost", null);
		RepairShop rShop4 = new RepairShop(null, null, "Center", null);
		RepairShop rShop5 = new RepairShop(null, null, "Livadite", brand2);
		
		repairShops.add(rShop1);
		repairShops.add(rShop2);
		repairShops.add(rShop3);
		repairShops.add(rShop4);
		repairShops.add(rShop5);
	
		this.repairShopRepository.saveAll(repairShops);
		
		// Create users
		userService.registerAdmin("admin", "admin#");
				
		Employee emp1 = userService.registerEmployee("Emp", "Emploev1", "empl1","employee#", qual1, rShop1);
		Employee emp2 = userService.registerEmployee("Emp", "Emploev2", "empl2","employee#", qual2, rShop1);
		
		Employee emp3 = userService.registerEmployee("Emp", "Emploev3", "empl3","employee#", qual2, rShop2);
		Employee emp4 = userService.registerEmployee("Emp", "Emploev4", "empl4","employee#", qual3, rShop2);
		
		Employee emp5 = userService.registerEmployee("Emp", "Emploev5", "empl5","employee#", qual3, rShop3);
		Employee emp6 = userService.registerEmployee("Emp", "Emploev6", "empl6","employee#", qual4, rShop3);
		
		Employee emp7 = userService.registerEmployee("Emp", "Emploev7", "empl7","employee#", qual1, rShop4);
		Employee emp8 = userService.registerEmployee("Emp", "Emploev8", "empl8","employee#", qual4, rShop4);
		
		Employee emp9 = userService.registerEmployee("Emp", "Emploev9", "empl9","employee#", qual1, rShop5);
		Employee emp10 = userService.registerEmployee("Emp", "Emploev10", "empl10","employee#", qual4, rShop5);

		Customer cust1 = userService.registerCustomer("Cust", "Custemerov1", "cust1","customer#");
		Customer cust2 = userService.registerCustomer("Cust", "Custemerov2", "cust2","customer#");
		Customer cust3 = userService.registerCustomer("Cust", "Custemerov3", "cust3","customer#");
		Customer cust4 = userService.registerCustomer("Cust", "Custemerov4", "cust4","customer#");


		//vehicles
		Vehicle veh1 = new Vehicle("CA6823BM", brandModel1, 2005, cust1);
		Vehicle veh2 = new Vehicle("CA4235BC", brandModel5, 2009, cust1);
		Vehicle veh3 = new Vehicle("CA3943BH", brandModel6, 2007, cust1);
		Vehicle veh4 = new Vehicle("CA2897BP", brandModel3, 2015, cust2);
		Vehicle veh5 = new Vehicle("CA7246BC", brandModel5, 2008, cust2);
		Vehicle veh6 = new Vehicle("CA9612BA", brandModel9, 2016, cust2); //audi
		Vehicle veh7 = new Vehicle("CA2865BH", brandModel10, 2015, cust3); //audi
		Vehicle veh8 = new Vehicle("CA3445CM", brandModel2, 2005, cust3);
		Vehicle veh9 = new Vehicle("CA4679AM", brandModel3, 2014, cust3);
		Vehicle veh10 = new Vehicle("CA8973PM", brandModel10, 2006, cust4); //audi
		Vehicle veh11 = new Vehicle("CA8814EM", brandModel7, 2007, cust4);
		Vehicle veh12 = new Vehicle("CA9883OM", brandModel8, 2016, cust4);
		
		vehicles.add(veh1);
		vehicles.add(veh2);
		vehicles.add(veh3);
		vehicles.add(veh4);
		vehicles.add(veh5);
		vehicles.add(veh6);
		vehicles.add(veh7);
		vehicles.add(veh8);
		vehicles.add(veh9);
		vehicles.add(veh10);
		vehicles.add(veh11);
		vehicles.add(veh12);
	
		this.vehicleRepository.saveAll(vehicles);
		
		//repairs
		Repair repair1 = new Repair(LocalDate.of(2019, 6, 25), LocalTime.of(15, 29), 423.45, rShop5, rType2, veh6, true); //a
		Repair repair2 = new Repair(LocalDate.of(2019, 7, 15), LocalTime.of(15, 29), 153.25, rShop1, rType5, veh2, true);
		Repair repair3 = new Repair(LocalDate.of(2019, 9, 5), LocalTime.of(15, 29), 288.45, rShop2, rType4, veh4, true);
		Repair repair4 = new Repair(LocalDate.of(2019, 10, 12), LocalTime.of(15, 29), 387.55, rShop3, rType7, veh8, true);
		Repair repair5 = new Repair(LocalDate.of(2019, 11, 7), LocalTime.of(15, 29), 115.65, rShop4, rType3, veh5, true);
		Repair repair6 = new Repair(LocalDate.of(2019, 11, 15), LocalTime.of(15, 29), 980.95, rShop1, rType4, veh11, true);
		Repair repair7 = new Repair(LocalDate.of(2019, 12, 23), LocalTime.of(15, 29), 365.00, rShop2, rType5, veh12, true);
		Repair repair8 = new Repair(LocalDate.of(2020, 1, 12), LocalTime.of(15, 29), 452.12, rShop5, rType6, veh7, true);   //a
		Repair repair9 = new Repair(LocalDate.of(2020, 1, 22), LocalTime.of(15, 29), 1099.93, rShop3, rType7, veh1, true);
		Repair repair10 = new Repair(LocalDate.of(2020, 2, 24), LocalTime.of(15, 29), 820.15, rShop4, rType6, veh2, true);
		Repair repair11 = new Repair(LocalDate.of(2020, 2, 25), LocalTime.of(15, 29), 446.65, rShop1, rType3, veh3, true);
		Repair repair12 = new Repair(LocalDate.of(2020, 3, 11), LocalTime.of(15, 29), 328.45, rShop5, rType8, veh10, true); //a
		Repair repair13 = new Repair(LocalDate.of(2020, 3, 16), LocalTime.of(15, 29), 982.55, rShop3, rType8, veh4, true);
		Repair repair14 = new Repair(LocalDate.of(2020, 5, 25), LocalTime.of(15, 29), 435.35, rShop4, rType7, veh5, true);
		Repair repair15 = new Repair(LocalDate.of(2020, 6, 24), LocalTime.of(15, 29), 292.65, rShop5, rType3, veh6, true); //a
		Repair repair16 = new Repair(LocalDate.of(2020, 7, 12), LocalTime.of(15, 29), 322.25, rShop5, rType1, veh7, true); //a
		Repair repair17 = new Repair(LocalDate.of(2020, 8, 24), LocalTime.of(15, 29), 540.15, rShop2, rType4, veh8, true);
		Repair repair18 = new Repair(LocalDate.of(2020, 9, 7), LocalTime.of(15, 29), 240.65, rShop3, rType5, veh9, true);
		Repair repair19 = new Repair(LocalDate.of(2020, 9, 9), LocalTime.of(15, 29), 320.47, rShop4, rType2, veh11, true);
		Repair repair20 = new Repair(LocalDate.of(2020, 12, 28), LocalTime.of(15, 29), 460.25, rShop1, rType4, veh12, true);
				
		repairs.add(repair1);
		repairs.add(repair2);
		repairs.add(repair3);
		repairs.add(repair4);
		repairs.add(repair5);
		repairs.add(repair6);
		repairs.add(repair7);
		repairs.add(repair8);
		repairs.add(repair9);
		repairs.add(repair10);
		repairs.add(repair11);
		repairs.add(repair12);
		repairs.add(repair13);
		repairs.add(repair14);
		repairs.add(repair15);
		repairs.add(repair16);
		repairs.add(repair17);
		repairs.add(repair18);
		repairs.add(repair19);
		repairs.add(repair20);
	
		this.repairRepository.saveAll(repairs);
	
	}

}
