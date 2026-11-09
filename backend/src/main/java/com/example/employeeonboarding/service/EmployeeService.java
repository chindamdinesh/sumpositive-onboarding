package com.example.employeeonboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeeonboarding.dto.ChecklistProgressDto;
import com.example.employeeonboarding.dto.DashboardResponseDto;
import com.example.employeeonboarding.dto.PersonalInfoDto;
import com.example.employeeonboarding.dto.ProfileDto;

@Service
public class EmployeeService {
	
	public EmployeeService() {
		
	}

	public PersonalInfoDto savePersonalInfo(PersonalInfoDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public PersonalInfoDto getPersonalInfo() {

		return null;
	}
	
	public DashboardResponseDto getDashboardData() {
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

	public List<Boolean> saveChecklistProgress(ChecklistProgressDto requestDto) {
		
		return null;
	}

	public ProfileDto getProfile() {
		
		return null;
	}

	public String finishOnboarding() {
		return null;
	}


}
