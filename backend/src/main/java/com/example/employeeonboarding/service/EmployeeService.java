package com.example.employeeonboarding.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.example.employeeonboarding.dto.DashboardResponseDto;
import com.example.employeeonboarding.dto.LoginRequestDto;
import com.example.employeeonboarding.dto.LoginResponseDto;
import com.example.employeeonboarding.dto.PersonalInfoRequestDto;
import com.example.employeeonboarding.dto.PersonalInfoResponseDto;
import com.example.employeeonboarding.dto.ProfileDto;
import com.example.employeeonboarding.dto.RegisterRequestDto;
import com.example.employeeonboarding.dto.RegisterResponseDto;
import com.example.employeeonboarding.dto.UserDto;

import jakarta.validation.Valid;

@Service
public class EmployeeService {
	
	public EmployeeService() {
		
	}

	public PersonalInfoResponseDto savePersonalInfo(PersonalInfoRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public PersonalInfoResponseDto getPersonalInfo() {

		return null;
	}
	
	public DashboardResponseDto getDashboardData() {
		// TODO Auto-generated method stub
		return null;
	}

	public RegisterResponseDto register(RegisterRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public LoginResponseDto login(@Valid LoginRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean readIntro() {
		return true;
	}

	public String getCompanyIntro() {
		return "PlaceholderText";
	}

	public String getPolicy() {
		return "";
	}

	public boolean acknowledgePolicy() {
		return true;
	}

	public List<Boolean> getChecklistProgress() {
		
		return null;
	}

	public List<Boolean> saveChecklistProgress() {
		
		return null;
	}

	public ProfileDto getProfile(UserDto requestDto) {
		
		return null;
	}

	public String finishOnboarding() {
		return null;
	}


}
