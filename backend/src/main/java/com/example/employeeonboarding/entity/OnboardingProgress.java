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
	private boolean personal_information_completed;

	@Column(nullable = false)
	private boolean read_company_introduction;
	
	@Column(nullable = false)
	private boolean acknowledged_company_policy;
	
	@Column(nullable = false)
	private boolean checklist_joining_date;
	
	@Column(nullable = false)
	private boolean checklist_first_day;
	
	@Column(nullable = false)
	private boolean checklist_orientation;
	
	@Column(nullable = false)
	private boolean joining_checklist_completed;
	
	@Column(nullable = false)
	private boolean final_confirmation;
	
	@Column(nullable = false)
	private LocalDateTime updated_at;
	
	@Column(nullable = false)
	private LocalDateTime created_at;
	
	public OnboardingProgress() {}

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

	public boolean isPersonal_information_completed() {
		return personal_information_completed;
	}

	public void setPersonal_information_completed(boolean personal_information_completed) {
		this.personal_information_completed = personal_information_completed;
	}

	public boolean isRead_company_introduction() {
		return read_company_introduction;
	}

	public void setRead_company_introduction(boolean read_company_introduction) {
		this.read_company_introduction = read_company_introduction;
	}

	public boolean isAcknowledged_company_policy() {
		return acknowledged_company_policy;
	}

	public void setAcknowledged_company_policy(boolean acknowledged_company_policy) {
		this.acknowledged_company_policy = acknowledged_company_policy;
	}

	public boolean isChecklist_joining_date() {
		return checklist_joining_date;
	}

	public void setChecklist_joining_date(boolean checklist_joining_date) {
		this.checklist_joining_date = checklist_joining_date;
	}

	public boolean isChecklist_first_day() {
		return checklist_first_day;
	}

	public void setChecklist_first_day(boolean checklist_first_day) {
		this.checklist_first_day = checklist_first_day;
	}

	public boolean isChecklist_orientation() {
		return checklist_orientation;
	}

	public void setChecklist_orientation(boolean checklist_orientation) {
		this.checklist_orientation = checklist_orientation;
	}

	public boolean isJoining_checklist_completed() {
		return joining_checklist_completed;
	}

	public void setJoining_checklist_completed(boolean joining_checklist_completed) {
		this.joining_checklist_completed = joining_checklist_completed;
	}

	public boolean isFinal_confirmation() {
		return final_confirmation;
	}

	public void setFinal_confirmation(boolean final_confirmation) {
		this.final_confirmation = final_confirmation;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
}
