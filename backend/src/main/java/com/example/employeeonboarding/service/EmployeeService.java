package com.example.employeeonboarding.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeonboarding.dto.ChecklistProgressDto;
import com.example.employeeonboarding.dto.DashboardResponseDto;
import com.example.employeeonboarding.dto.PersonalInfoDto;
import com.example.employeeonboarding.dto.ProfileDto;
import com.example.employeeonboarding.entity.Employee;
import com.example.employeeonboarding.entity.OnboardingProgress;
import com.example.employeeonboarding.entity.PersonalInformation;
import com.example.employeeonboarding.exception.ConflictException;
import com.example.employeeonboarding.exception.UnauthorizedException;
import com.example.employeeonboarding.repository.EmployeeRepository;
import com.example.employeeonboarding.repository.OnboardingProgressRepository;
import com.example.employeeonboarding.repository.PersonalInformationRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final OnboardingProgressRepository progressRepository;
	private final PersonalInformationRepository personalInfoRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository, OnboardingProgressRepository progressRepository, PersonalInformationRepository personalInfoRepository) {
		this.employeeRepository = employeeRepository;
		this.progressRepository = progressRepository;
		this.personalInfoRepository = personalInfoRepository;
	}
	
	public ProfileDto getProfile() {
		Long userId = getCurrentUserId();
		Employee emp = getEmployeeById(userId);
		OnboardingProgress progress = getProgressByUserId(userId);
		
		String status = progress.isFinalConfirmation() ? "Completed" : "In Progress";
		return new ProfileDto(emp.getName(), emp.getEmail(), status);
	}
	
	@Transactional
	public PersonalInfoDto savePersonalInfo(PersonalInfoDto dto) {
		Long userId = getCurrentUserId();
		Employee emp = getEmployeeById(userId);
		
		PersonalInformation info = personalInfoRepository.findById(userId)
				.orElseGet(() -> {
					PersonalInformation newInfo = new PersonalInformation(emp);
					return newInfo;
				});
		
		info.setFullName(dto.fullName());
		info.setPhoneNumber(dto.phoneNumber());
		info.setDateOfBirth(dto.dateOfBirth());
		info.setEmergencyContactName(dto.emergencyContactName());
		info.setEmergencyContactNumber(dto.emergencyContactNumber());
		info.setAddress(dto.address());
		info.setUpdatedAt(LocalDateTime.now());
		
		personalInfoRepository.save(info);
		
		// Update Step 1
		OnboardingProgress progress = getProgressByUserId(userId);
		progress.setPersonalInformationCompleted(true);
		progress.setUpdatedAt(LocalDateTime.now());
		progressRepository.save(progress);

		return dto;
	}

	public PersonalInfoDto getPersonalInfo() {
        Long userId = getCurrentUserId();
        return personalInfoRepository.findById(userId)
                .map(info -> new PersonalInfoDto(
                        info.getFullName(),
                        info.getPhoneNumber(),
                        info.getDateOfBirth(),
                        info.getEmergencyContactName(),
                        info.getEmergencyContactNumber(),
                        info.getAddress()
                ))
                .orElseGet(() -> {
                    Employee employee = getEmployeeById(userId);
                    return new PersonalInfoDto(employee.getName(), "", null, "", "", "");
                });
    }
	
	public DashboardResponseDto getDashboardData() {
		Long userId = getCurrentUserId();
		Employee emp = getEmployeeById(userId);
		OnboardingProgress progress = getProgressByUserId(userId);

		int completedCount = 0;
		if (progress.isPersonalInformationCompleted()) completedCount++;
		if (progress.isReadCompanyIntroduction()) completedCount++;
		if (progress.isAcknowledgedCompanyPolicy()) completedCount++;
		if (progress.isJoiningChecklistCompleted()) completedCount++;
		if (progress.isFinalConfirmation()) completedCount++;
		
		int progressPercentage = 20 * completedCount;
		
		return new DashboardResponseDto(
				emp.getEmail(),
				emp.getName(),
				completedCount, progressPercentage,
				progress.isPersonalInformationCompleted(),
				progress.isReadCompanyIntroduction(),
				progress.isAcknowledgedCompanyPolicy(),
				progress.isChecklistJoiningDate(),
				progress.isChecklistFirstDay(),
				progress.isChecklistOrientation(),
				progress.isJoiningChecklistCompleted(),
				progress.isFinalConfirmation(),
				progress.getUpdatedAt()
		);
	}

	@Transactional
	public boolean readCompanyIntro() {
		OnboardingProgress progress = getProgressByUserId(getCurrentUserId());
		progress.setReadCompanyIntroduction(true);
		progress.setUpdatedAt(LocalDateTime.now());
		progressRepository.save(progress);
		return true;
	}

	public String getCompanyIntro() {
		return "Placeholder text for company introduction";
	}

	@Transactional
	public boolean acknowledgePolicy() {
		OnboardingProgress progress = getProgressByUserId(getCurrentUserId());
		progress.setAcknowledgedCompanyPolicy(true);
		progress.setUpdatedAt(LocalDateTime.now());
		progressRepository.save(progress);
		return true;
	}
	
	public String getPolicy() {
		return "Placeholder text for company policy";
	}


	public ChecklistProgressDto getChecklistProgress() {
		OnboardingProgress p = getProgressByUserId(getCurrentUserId());
		return new ChecklistProgressDto(p.isChecklistJoiningDate(), p.isChecklistFirstDay(), p.isChecklistOrientation());
	}

	@Transactional
	public ChecklistProgressDto saveChecklistProgress(ChecklistProgressDto dto) {
		OnboardingProgress progress = getProgressByUserId(getCurrentUserId());
		
		progress.setChecklistJoiningDate(dto.joiningDate());
		progress.setChecklistFirstDay(dto.firstDayInstruction());
		progress.setChecklistOrientation(dto.orientationInformation());
		
		boolean allThreeChecked = dto.joiningDate() && dto.firstDayInstruction() && dto.orientationInformation();
		progress.setJoiningChecklistCompleted(allThreeChecked);
		
		if (!allThreeChecked) {
			progress.setFinalConfirmation(false);
			progress.setCompletedAt(null);
		}
		
		progress.setUpdatedAt(LocalDateTime.now());
		progressRepository.save(progress);
		
		return dto;
	}
	
	@Transactional
	public String finishOnboarding() {
		OnboardingProgress progress = getProgressByUserId(getCurrentUserId());
		
		if (progress.isFinalConfirmation()) 
			return "Onboarding already completed! This message shouldn't be visible.";
		
		boolean allFourStepsFinished = progress.isPersonalInformationCompleted()
				&& progress.isReadCompanyIntroduction()
				&& progress.isAcknowledgedCompanyPolicy()
				&& progress.isJoiningChecklistCompleted();
		
				if (!allFourStepsFinished) {
					throw new ConflictException("Cannot complete onboarding: Please ensure that Steps 1 to 4 are completed first.");
				}
		
		progress.setFinalConfirmation(true);
		LocalDateTime finishedTime = LocalDateTime.now();
		progress.setCompletedAt(finishedTime);
		progress.setUpdatedAt(finishedTime);
		progressRepository.save(progress);
		
		return "Onboarding completed successfully!";
	}
	

	// Helper: Extract authenticated userId from Spring SecurityContext
    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Long userId)) {
            throw new UnauthorizedException("Unauthorized: Please log in");
        }
        return userId;
    }

    private Employee getEmployeeById(Long userId) {
    	return employeeRepository.findById(userId)
    			.orElseThrow(() -> new UnauthorizedException("User does not exist!"));
    	};
    
    private OnboardingProgress getProgressByUserId(Long userId) {
    	return progressRepository.findById(userId)
    			.orElseThrow(() -> new UnauthorizedException("Progress record not found!"));
    }

}
