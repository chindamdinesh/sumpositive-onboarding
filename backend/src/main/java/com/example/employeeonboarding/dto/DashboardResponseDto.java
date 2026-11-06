package com.example.employeeonboarding.dto;

import java.time.LocalDateTime;

public record DashboardResponseDto(String email, int completedCount, boolean personalInformationCompleted, boolean readCompanyIntroduction,
			boolean acknowledgedCompanyPolicy, boolean checklistJoiningDate, boolean checklistFirstDay, boolean checklistOrientation, boolean joiningChecklistCompleted,
			boolean finalConfirmation, LocalDateTime updatedAt) {}
