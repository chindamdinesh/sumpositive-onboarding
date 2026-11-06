package com.example.employeeonboarding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping
	public String hello() {
		return "index";
	}
	
}
