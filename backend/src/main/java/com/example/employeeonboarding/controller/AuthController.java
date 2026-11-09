package com.example.employeeonboarding.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeonboarding.dto.LoginRequestDto;
import com.example.employeeonboarding.dto.LoginResponseDto;
import com.example.employeeonboarding.dto.RegisterRequestDto;
import com.example.employeeonboarding.dto.RegisterResponseDto;
import com.example.employeeonboarding.service.AuthService;
import com.example.employeeonboarding.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> registerUser(
			@Valid @RequestBody RegisterRequestDto requestDto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(requestDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginUser(
			@Valid @RequestBody LoginRequestDto requestDto) {
		
		return ResponseEntity.status(HttpStatus.OK).body(authService.login(requestDto));
	}
	
	
}
