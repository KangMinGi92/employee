package com.jdbc.employee.model.service;




import static com.jdbc.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.jdbc.employee.model.dao.EmployeeDao;
import com.jdbc.employee.model.dto.Employee;
public class EmployeeService {

	private EmployeeDao dao=new EmployeeDao();
	
	
	public List<Employee> selectEmployeeAll() {
		Connection conn=getConnection();
		List<Employee> employees=dao.selectEmployeeAll(conn);
		close(conn);
		return employees;
	}
	
	public List<Employee> selectSearchEmployee(Map map){
		Connection conn=getConnection();
		List<Employee> employees=dao.selectSearchEmployee(conn,map);
		close(conn);
		return employees;
	}
	
	public List<Employee> selectSearchBySalaryEmployee(Map map){
		Connection conn=getConnection();
		List<Employee> employees=dao.selectSearchBySalaryEmployee(conn,map);
		close(conn);
		return employees;
	}

	public int insertEmployee(Employee e) {
		Connection conn=getConnection();
		int result=dao.insertEmployee(conn,e);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateEmployee(Employee e) {
		Connection conn=getConnection();
		int result=dao.updateEmployee(conn,e);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteEmployee(Employee e) {
		Connection conn=getConnection();
		int result=dao.deleteEmployee(conn,e);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
}
