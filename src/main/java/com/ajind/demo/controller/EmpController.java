package com.ajind.demo.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajind.demo.model.EmpMV;
import com.ajind.demo.model.EmpVM;
import com.ajind.demo.service.EmpService;

/**
 * 
 * REST Controller for Employee
 * 
 * @author Akash
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/Employee")
public class EmpController {

	@Autowired
	private EmpService empService;

	private static final Logger log = LoggerFactory.getLogger(EmpController.class);

	/*
	 * GET All Employee Details
	 */
	@GetMapping("/getAllEmployee")
	public ArrayList<EmpMV> getAllEmp() {

		log.info("in getAllEmp of Controller");
		return empService.getAllEmp();
	}

	/*
	 * POST new Employee
	 */
	@PostMapping("/saveEmp")
	public EmpMV saveEmp(@RequestBody EmpVM empVM) {

		log.info("in saveEmp of Controller");
		return empService.saveEmp(empVM);

	}
}