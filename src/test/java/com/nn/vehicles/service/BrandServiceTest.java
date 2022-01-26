package com.nn.vehicles.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.repository.BrandRepository;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BrandServiceTest {
	
    @MockBean
    private BrandRepository brandRepository;

    @Autowired
    private BrandService brandService;


	@Test
	void test() {
		int id =1;
		
		Brand brand = new Brand();
		brand.setName("Moskvich");
		
		Mockito.when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
		
		Brand brandDTO = brandService.findById(id).get();
		
		assertThat(brandDTO.getId()).isEqualTo(brand.getId());
	}

}
