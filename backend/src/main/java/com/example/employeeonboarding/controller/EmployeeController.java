package com.example.employeeonboarding.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeonboarding.dto.ChecklistProgressDto;
import com.example.employeeonboarding.dto.DashboardResponseDto;
import com.example.employeeonboarding.dto.PersonalInfoDto;
import com.example.employeeonboarding.dto.ProfileDto;
import com.example.employeeonboarding.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/onboarding")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController (EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/profile")
	public ResponseEntity<ProfileDto> getProfile() {
		
		ProfileDto data = employeeService.getProfile();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponseDto> getDashboard() {
		return ResponseEntity.ok(employeeService.getDashboardData());
	}
	
	// Step 1
	@PutMapping("/personal-info")
	public ResponseEntity<PersonalInfoDto> savePersonalInfo(
			@Valid @RequestBody PersonalInfoDto requestDto) {
		
		return ResponseEntity.ok(employeeService.savePersonalInfo(requestDto));
	}
	
	@GetMapping("/personal-info")
	public ResponseEntity<PersonalInfoDto> getPersonalInfo() {
		
		return ResponseEntity.ok(employeeService.getPersonalInfo());
	}
	
	// Step 2
	@GetMapping("/company-intro")
	public ResponseEntity<String> getCompanyIntro() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getCompanyIntro());
	}
	
	//Step 2
	@PutMapping("/company-intro")
	public ResponseEntity<Boolean> readCompanyIntro() {
		return ResponseEntity.ok(employeeService.readCompanyIntro());
	}
	
	// Step 3
	@GetMapping("/company-policy")
	public ResponseEntity<String> getCompanyPolicy() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getPolicy());
	}
	
	// Step 3
	@PutMapping("/company-policy")
	public ResponseEntity<Boolean> readCompanyPolicy() {
		return ResponseEntity.ok(employeeService.acknowledgePolicy());
	}
	
	// Step 4
	@GetMapping("/joining-checklist")
	public ResponseEntity<ChecklistProgressDto> getJoiningChecklistProgress() {
		return ResponseEntity.ok(employeeService.getChecklistProgress());
	}
	
	//Step 4
	@PutMapping("/joining-checklist")
	public ResponseEntity<ChecklistProgressDto> saveJoiningChecklistProgress(
			@RequestBody ChecklistProgressDto requestDto) {
		return ResponseEntity.ok(employeeService.saveChecklistProgress(requestDto));
	}
	
	// Step 5
	@PostMapping("/final-confirmation")
	public ResponseEntity<String> confirmOnboarding() {
		return ResponseEntity.ok(employeeService.finishOnboarding());
	}
	
	
}