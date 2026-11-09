package com.example.employeeonboarding.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeonboarding.dto.LoginRequestDto;
import com.example.employeeonboarding.dto.LoginResponseDto;
import com.example.employeeonboarding.dto.RegisterRequestDto;
import com.example.employeeonboarding.dto.RegisterResponseDto;
import com.example.employeeonboarding.entity.Employee;
import com.example.employeeonboarding.entity.OnboardingProgress;
import com.example.employeeonboarding.exception.ConflictException;
import com.example.employeeonboarding.exception.UnauthorizedException;
import com.example.employeeonboarding.repository.EmployeeRepository;
import com.example.employeeonboarding.repository.OnboardingProgressRepository;
import com.example.employeeonboarding.security.JwtService;

@Service
public class AuthService {
	
	private final EmployeeRepository employeeRepository;
	private final OnboardingProgressRepository progressRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(EmployeeRepository employeeRepository, OnboardingProgressRepository progressRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
		this.employeeRepository = employeeRepository;
		this.progressRepository = progressRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public RegisterResponseDto register(RegisterRequestDto dto) {
		
		if (employeeRepository.existsByEmail(dto.email())) {
			throw new ConflictException("Email is already registered");
		}
		
		Employee employee = new Employee();
		employee.setName(dto.name());
		employee.setEmail(dto.email());
		employee.setPassword(passwordEncoder.encode(dto.password()));
		Employee savedEmployee = employeeRepository.save(employee);
		
		OnboardingProgress progress = new OnboardingProgress(savedEmployee);
		progressRepository.save(progress);
		
		return new RegisterResponseDto("Registered successfully! Login with your credentials.");
	}

	public LoginResponseDto login(LoginRequestDto loginDto) {
		
		Employee emp = employeeRepository.findByEmail(loginDto.email())
				.orElseThrow(() -> new UnauthorizedException("Invalid email or password!"));
		
		if (!passwordEncoder.matches(loginDto.password(), emp.getPassword())) {
			throw new ConflictException("Invalid email or password");
		}
		
		final String token = jwtService.generateToken(emp.getUserId(), emp.getEmail());
		
		boolean isCompleted = progressRepository.findById(emp.getUserId())
				.map(OnboardingProgress::isFinalConfirmation)
				.orElse(false);
		
		String status = isCompleted ? "Completed" : "In Progress";
		
		return new LoginResponseDto(token, emp.getEmail(), emp.getName(), status);
	}
}