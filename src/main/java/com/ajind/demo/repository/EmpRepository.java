package com.ajind.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

@Repository													 // used for database manipulation
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
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			} catch (NullPointerException ne) {
				// TODO Auto-generated catch block
				ne.printStackTrace();
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

			PreparedStatement statement = connection.prepareStatement(
					" INSERT INTO employees ( empid,  fname ,lname,gender,contact,password) VALUES(?,?,?,?,?,?)");
			statement.setString(1, empVM.getEmpId());
			statement.setString(2, empVM.getFname());
			statement.setString(3, empVM.getLname());
			statement.setString(4, empVM.getGender());
			statement.setString(5, empVM.getContact());
			statement.setString(6, empVM.getPassword());

			log.info("Saving Data...");

			int i = statement.executeUpdate();
			log.info(i + " record inserted");

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
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			} catch (NullPointerException ne) {
				// TODO Auto-generated catch block
				ne.printStackTrace();
			}
		}
		log.info("Goodbye!");
		EmpMV empMV = new EmpMV();
		empMV.setEmpId(empVM.getEmpId());
		empMV.setFname(empVM.getFname());
		empMV.setLname(empVM.getLname());
		empMV.setContact(empVM.getContact());
		empMV.setGender(empVM.getGender());
		empMV.setPassword(empVM.getPassword());
		return empMV;
	}

}
