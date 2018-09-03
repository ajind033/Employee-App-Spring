package com.ajind.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ajind.demo.model.EmpMV;
import com.ajind.demo.model.EmpVM;

/**
 * DB connection for Employee
 * 
 * @author Akash
 *
 */
@Repository				//used for database manipulation
public class EmpRepository {

	private static Connection connection = null;
	private static Statement statement = null;

	private static final Logger log = LoggerFactory.getLogger(EmpRepository.class);

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_records?verifyServerCertificate=false&useSSL=true&";

	private static final String USER = "demo";
	private static final String PASS = "demo";

	public ArrayList<EmpMV> getAllEmployee() {

		EmpMV empMV = null;
		ArrayList<EmpMV> employeeList = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);

			log.info("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			log.info("Creating statement...");
			statement = connection.createStatement();

			log.info("Getting Data...");
			String sql = "SELECT * FROM Employees";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				empMV = new EmpMV();
				empMV.setEmpId(resultSet.getString("empid"));
				empMV.setFname(resultSet.getString("fname"));
				empMV.setLname(resultSet.getString("lname"));
				empMV.setGender(resultSet.getString("gender"));
				empMV.setContact(resultSet.getString("contact"));
				empMV.setPassword(resultSet.getString("password"));
				employeeList.add(empMV);

			}

			resultSet.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println(se.getMessage());

			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			log.info("Closing the connection");
			try {
				statement.close();
				connection.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
		log.info("Goodbye!");
		return employeeList;
	}

	public Object saveEmp(EmpVM empVM) {

		try {
			Class.forName(JDBC_DRIVER);

			log.info("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			log.info("Creating statement...");
			statement = connection.createStatement();
			
			log.info("Saving Data...");
			String sql = " INSERT INTO employees ( empid,  fname ,lname,gender,contact,password) VALUES ( '"
					+ empVM.getEmpId() + "', '" + empVM.getFname() + "','" + empVM.getLname() + "','"
					+ empVM.getGender() + "','" + empVM.getContact() + "', '" + empVM.getPassword() + "' )";

			int a = statement.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return se.getMessage();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			log.info("Closing the connection");
			try {
				statement.close();
				connection.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
		log.info("Goodbye!");
		EmpMV empMV= new EmpMV();
		empMV.setEmpId(empVM.getEmpId());
		empMV.setFname(empVM.getFname());
		empMV.setLname(empVM.getLname());
		empMV.setContact(empVM.getContact());
		empMV.setGender(empVM.getGender());
		empMV.setPassword(empVM.getPassword());
		return empMV;
	}

}
