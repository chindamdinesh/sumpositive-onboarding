package com.example.employeeonboarding.dto;

public record PersonalInfoResponseDto(String fullName, String email, String phoneNumber, String address,
		String dateOfBirth, String emergencyContactName, String emergencyContactNumber) {}
