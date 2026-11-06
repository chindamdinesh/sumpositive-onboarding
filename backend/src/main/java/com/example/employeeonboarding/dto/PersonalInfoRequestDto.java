package com.example.employeeonboarding.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record PersonalInfoRequestDto (
		@NotBlank(message = "Name cannot be Blank!")
		String fullName,
		
		@NotBlank(message = "Phone number is required")
		String phoneNumber,
		
		@NotNull(message = "Date of Birth is required")
		@Past(message = "Date of Birth must be in the past")
		LocalDate dateOfBirth,
		
		@NotBlank(message = "Emergency contact name  is required")
		String emergencyContactName,
		
		@NotBlank(message = "Emergency contact number is required")
		String emergencyContactNumber,
		
		@NotBlank(message = "Address is required")
		String address) {}
