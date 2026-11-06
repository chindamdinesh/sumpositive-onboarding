package com.example.employeeonboarding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeonboarding.dto.DashboardResponseDto;
import com.example.employeeonboarding.dto.PersonalInfoRequestDto;
import com.example.employeeonboarding.dto.RegisterRequestDto;
import com.example.employeeonboarding.dto.UserResponseDto;
import com.example.employeeonboarding.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/onboarding")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController (EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> registerUser(
			@Valid @RequestBody RegisterRequestDto requestDto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.register(requestDto));
	}
	
	@PutMapping("/profile")
	public ResponseEntity<PersonalInfoRequestDto> savePersonalInfo(
			@RequestBody PersonalInfoRequestDto requestDto) {
		
		PersonalInfoRequestDto saved = employeeService.savePersonalInfo(requestDto);
		return ResponseEntity.status(HttpStatus.OK).body(saved);
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponseDto> getDashboard() {
		DashboardResponseDto data = employeeService.getDashboardData();
		return ResponseEntity.ok(data);
	}
	
}