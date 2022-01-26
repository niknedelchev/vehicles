package com.nn.vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.vehicles.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>  {

}
