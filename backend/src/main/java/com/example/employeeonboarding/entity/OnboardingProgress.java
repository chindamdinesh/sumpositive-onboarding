package com.example.employeeonboarding.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "onboarding_progress")
public class OnboardingProgress {
	
	@Id
	@Column(name = "user_id")
	private Long userId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "user_id")
	private Employee employee;
	
	@Column(nullable = false)
	private boolean personalInformationCompleted;

	@Column(nullable = false)
	private boolean readCompanyIntroduction;
	
	@Column(nullable = false)
	private boolean acknowledgedCompanyPolicy;
	
	@Column(nullable = false)
	private boolean checklistJoiningDate;
	
	@Column(nullable = false)
	private boolean checklistFirstDay;
	
	@Column(nullable = false)
	private boolean checklistOrientation;
	
	@Column(nullable = false)
	private boolean joiningChecklistCompleted;
	
	@Column(nullable = false)
	private boolean finalConfirmation;
	
	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	public OnboardingProgress() {}
	
	public OnboardingProgress(Employee employee) {
		this.employee = employee;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isPersonalInformationCompleted() {
		return personalInformationCompleted;
	}

	public void setPersonalInformationCompleted(boolean personalInformationCompleted) {
		this.personalInformationCompleted = personalInformationCompleted;
	}

	public boolean isReadCompanyIntroduction() {
		return readCompanyIntroduction;
	}

	public void setReadCompanyIntroduction(boolean readCompanyIntroduction) {
		this.readCompanyIntroduction = readCompanyIntroduction;
	}

	public boolean isAcknowledgedCompanyPolicy() {
		return acknowledgedCompanyPolicy;
	}

	public void setAcknowledgedCompanyPolicy(boolean acknowledgedCompanyPolicy) {
		this.acknowledgedCompanyPolicy = acknowledgedCompanyPolicy;
	}

	public boolean isChecklistJoiningDate() {
		return checklistJoiningDate;
	}

	public void setChecklistJoiningDate(boolean checklistJoiningDate) {
		this.checklistJoiningDate = checklistJoiningDate;
	}

	public boolean isChecklistFirstDay() {
		return checklistFirstDay;
	}

	public void setChecklistFirstDay(boolean checklistFirstDay) {
		this.checklistFirstDay = checklistFirstDay;
	}

	public boolean isChecklistOrientation() {
		return checklistOrientation;
	}

	public void setChecklistOrientation(boolean checklistOrientation) {
		this.checklistOrientation = checklistOrientation;
	}

	public boolean isJoiningChecklistCompleted() {
		return joiningChecklistCompleted;
	}

	public void setJoiningChecklistCompleted(boolean joiningChecklistCompleted) {
		this.joiningChecklistCompleted = joiningChecklistCompleted;
	}

	public boolean isFinalConfirmation() {
		return finalConfirmation;
	}

	public void setFinalConfirmation(boolean finalConfirmation) {
		this.finalConfirmation = finalConfirmation;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
