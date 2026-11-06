package com.example.employeeonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeonboarding.entity.PersonalInformation;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long>{

}
