package com.nn.vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.vehicles.model.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Integer>  {

}
