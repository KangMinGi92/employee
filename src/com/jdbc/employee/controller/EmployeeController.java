package com.jdbc.employee.controller;

import java.util.List;
import java.util.Map;

import com.jdbc.employee.model.dto.Employee;
import com.jdbc.employee.model.service.EmployeeService;
import com.jdbc.employee.view.MainView;

public class EmployeeController {
	private MainView view=new MainView();
	private EmployeeService service=new EmployeeService();
	
	public void mainMenu() {
		view.mainMenu();
	}
	
	public void selectEmployeeAll() {
		List<Employee> employees=service.selectEmployeeAll();
		view.printEmployees(employees);
	}
	
	public void selectSearchEmployee(){
		Map map=view.inputSearch();
		List<Employee> employees=service.selectSearchEmployee(map);
		view.printEmployees(employees);
	}

	public void selectSearchBySalaryEmployee() {
		Map map=view.inputSearch();
		List<Employee> employees=service.selectSearchBySalaryEmployee(map);
		view.printEmployees(employees);
	}
	
	public void insertEmployee() {
		Employee e=view.inputEmployee();
		int result=service.insertEmployee(e);
		view.printMsg(result>0?"사원등록 성공":"사원등록실패");
	}
	
	public void updateEmployee() {
		Employee e=view.updateEmployee();
		int result=service.updateEmployee(e);
		view.printMsg(result>0?"사원정보수정 성공":"사원정보수정 실패");
	}
	
	public void deleteEmployee() {
		Employee e=view.deleteEmployee();
		int result=service.deleteEmployee(e);
		view.printMsg(result>0?"사원탈퇴 성공":"사원탈퇴 실패");
	}
	
	
	
}
