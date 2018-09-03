package com.ajind.demo.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajind.demo.model.EmpMV;
import com.ajind.demo.model.EmpVM;
import com.ajind.demo.repository.EmpRepository;
import com.ajind.demo.service.EmpService;

/**
 * Implementation of {@code EmpService}
 * 
 * @author Akash
 *
 */
@Service
public class EmpSeviceImpl implements EmpService {

	@Autowired
	private EmpRepository empRepository;

	private static final Logger log = LoggerFactory.getLogger(EmpSeviceImpl.class);

	@Override
	public ArrayList<EmpMV> getAllEmp() {

		log.info("In getAllEmp of Service Impl");
		return empRepository.getAllEmployee();
	}

	@Override
	public EmpMV saveEmp(EmpVM empVM) {

		log.info("In saveEmp of Service Impl");
		return empRepository.saveEmp(empVM);
	}

}
