package com.example.employeeonboarding.dto;

import java.time.LocalDateTime;

public record DashboardResponseDto(
		String email,
		String name,
		int completedCount,
		int progressPercentage,
		boolean personalInformationCompleted,
		boolean readCompanyIntroduction,
		boolean acknowledgedCompanyPolicy,
		boolean checklistJoiningDate,
		boolean checklistFirstDay,
		boolean checklistOrientation,
		boolean joiningChecklistCompleted,
		boolean finalConfirmation,
		LocalDateTime updatedAt) {}
