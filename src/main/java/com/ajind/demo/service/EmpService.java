package com.ajind.demo.service;

import java.util.ArrayList;

import com.ajind.demo.model.EmpMV;
import com.ajind.demo.model.EmpVM;

/**
 * Service for Employee
 * 
 * @author Akash
 *
 */
public interface EmpService {

	public ArrayList<EmpMV> getAllEmp();

	public EmpMV saveEmp(EmpVM empVM);
}
