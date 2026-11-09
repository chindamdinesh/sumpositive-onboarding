package com.example.employeeonboarding.dto;

public record LoginResponseDto(String token, String email, String name, String onboardingStatus) {}