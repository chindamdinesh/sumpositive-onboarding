package com.example.employeeonboarding.dto;

import java.util.List;

public record StepProgress(Boolean Step1, boolean Step2, boolean Step3, List<Boolean> Step4, boolean Step5) {}
