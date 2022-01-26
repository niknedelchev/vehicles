package com.nn.vehicles.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.repository.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import com.nn.vehicles.model.Brand;

@DataJpaTest
class BrandRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager ;
	@Autowired
	private BrandRepository brandRepository;
	
	@Test
	void findAllTest() {
		
		Brand brand1 = new Brand("Chrysler", null);
		Brand brand2 = new Brand("Dacia", null);
		testEntityManager.persistAndFlush(brand1);
		testEntityManager.persistAndFlush(brand2);
		List<Brand> tmp = brandRepository.findAll();
		assertThat(brandRepository.findAll().size()).isEqualTo(2);
	}
	
}
