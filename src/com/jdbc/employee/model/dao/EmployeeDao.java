package com.jdbc.employee.model.dao;




import static com.jdbc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jdbc.employee.model.dto.Department;
import com.jdbc.employee.model.dto.Employee;
import com.jdbc.employee.model.dto.Job;

public class EmployeeDao {

	private Properties sql=new Properties();
	private Object Employee;
	{
		String path=EmployeeDao.class.getResource("/sql/employee/employee_sql.properties").getPath();
		try(FileReader fr=new FileReader(path);){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Employee> selectEmployeeAll(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=this.sql.getProperty("selectEmployeeAll");
		List<Employee> employees=new ArrayList();
		Employee e=new Employee();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				e=getEmployee(rs);
				e.setDepartment(selectDepartment(conn,e.getDeptCode()));
				e.setJob(selectJob(conn,e.getJobCode()));
				employees.add(e);
			}
			
		}catch(SQLException g) {
			g.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return employees;
	}
	
	public List<Department> selectDepartment(Connection conn,String deptId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Department> depts=new ArrayList();
		String sql=this.sql.getProperty("selectDepartment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, deptId);
			rs=pstmt.executeQuery();
			while(rs.next()) depts.add(getDepartment(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return depts;
	}
	
	public List<Job> selectJob(Connection conn,String jobcode){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Job> jobs=new ArrayList();
		String sql=this.sql.getProperty("selectJob");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, jobcode);
			rs=pstmt.executeQuery();
			while(rs.next()) jobs.add(getJob(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return jobs;
	}
	
	public List<Employee> selectSearchEmployee(Connection conn,Map map){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=this.sql.getProperty("selectSearchEmployee");
		sql=sql.replace("#COL",(String)map.get("col"));
		List<Employee> employees=new ArrayList();
		Employee e=new Employee();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+(String)map.get("keyword")+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				e=getEmployee(rs);
				e.setDepartment(selectDepartment(conn,e.getDeptCode()));
				e.setJob(selectJob(conn,e.getJobCode()));
				employees.add(e);
			}
		}catch(SQLException g) {
			g.printStackTrace();	
		}finally {
			close(rs);
			close(pstmt);
		}
		return employees;
	}
	
	public List<Employee> selectSearchBySalaryEmployee(Connection conn,Map map){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=this.sql.getProperty("selectSearchBySalaryEmployee");
		sql=sql.replace("#COL",(String)map.get("col"));
		List<Employee> employees=new ArrayList();
		Employee e=new Employee();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(int)map.get("keyword"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				e=getEmployee(rs);
				e.setDepartment(selectDepartment(conn,e.getDeptCode()));
				e.setJob(selectJob(conn,e.getJobCode()));
				employees.add(e);
			}
		}catch(SQLException g) {
			g.printStackTrace();	
		}finally {
			close(rs);
			close(pstmt);
		}
		return employees;
	}

	public int insertEmployee(Connection conn,Employee e) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=this.sql.getProperty("insertEmployee");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmpId());
			pstmt.setString(2, e.getEmpName());
			pstmt.setString(3, e.getEmpNo());
			pstmt.setString(4, e.getEmail());
			pstmt.setString(5, e.getPhone());
			pstmt.setString(6, e.getDeptCode());
			pstmt.setString(7, e.getJobCode());
			pstmt.setString(8, e.getSalLevel());
			pstmt.setInt(9, e.getSalary());
			pstmt.setInt(10, e.getBonus());
			pstmt.setString(11, e.getManagerId());
			pstmt.setString(12, null);
			
			result=pstmt.executeUpdate();
		}catch(SQLException g) {
			g.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateEmployee(Connection conn, Employee e) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=this.sql.getProperty("updateEmployee");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, e.getJobCode());
			pstmt.setString(2, e.getDeptCode());
			pstmt.setInt(3, e.getSalary());
			pstmt.setString(4, e.getPhone());
			pstmt.setString(5, e.getEmail());
			pstmt.setString(6, e.getEmpId());
			
			result=pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteEmployee(Connection conn, Employee e) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=this.sql.getProperty("deleteEmployee");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmpId());
			
			result=pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	public Employee getEmployee(ResultSet rs) throws SQLException{
		Employee e=new Employee();
		e.setEmpId(rs.getString("emp_id"));
		e.setEmpName(rs.getString("emp_name"));
		e.setEmpNo(rs.getString("emp_no"));
		e.setEmail(rs.getString("email"));
		e.setPhone(rs.getString("phone"));
		e.setDeptCode(rs.getString("dept_code"));
		e.setJobCode(rs.getString("job_code"));
		e.setSalLevel(rs.getString("sal_level"));
		e.setSalary(rs.getInt("salary"));
		e.setBonus(rs.getInt("bonus"));
		e.setManagerId(rs.getString("manager_id"));
		e.setHireDate(rs.getDate("hire_date"));
		e.setEntDate(rs.getDate("ent_date"));
		e.setEntYn(rs.getString("ent_yn").charAt(0));
		return e;
	}
	public Department getDepartment(ResultSet rs) throws SQLException{
		Department d=new Department();
		d.setDeptId(rs.getString("dept_id"));
		d.setDeptTitle(rs.getString("dept_title"));
		d.setLocationId(rs.getString("location_id"));
		return d;
	}
	
	public Job getJob(ResultSet rs) throws SQLException{
		Job j=new Job();
		j.setJobCode(rs.getString("job_code"));
		j.setJobName(rs.getString("job_name"));
		return j;
	}
}
