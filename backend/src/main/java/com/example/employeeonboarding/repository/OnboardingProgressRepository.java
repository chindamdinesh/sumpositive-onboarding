package com.example.employeeonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeonboarding.entity.OnboardingProgress;

@Repository
public interface OnboardingProgressRepository extends JpaRepository<OnboardingProgress, Long> {

}
