package com.jdbc.employee.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jdbc.employee.controller.EmployeeController;
import com.jdbc.employee.model.dto.Employee;

public class MainView {
	
	public void mainMenu() {
		EmployeeController controller=new EmployeeController();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("==== 사원 관리 프로그램 ====");
			System.out.println("1.전체 사원조회");
			System.out.println("2.사원조회(1.부서 2.직책 3.이름 4.급여(크다/작다)");
			System.out.println("3.사원 등록");
			System.out.println("4.사원수정(직책,부서,급여,전화번호,이메일)");
			System.out.println("5.사원삭제");
			System.out.println("6.부서관리(1.등록 2.수정 3.삭제");
			System.out.println("7.직책관리(1.등록 2.수정 3.삭제");
			System.out.println("0.프로그램 종료");
			System.out.print("메뉴 선택 : ");
			int cho=sc.nextInt();
			switch(cho) {
			case 1 : controller.selectEmployeeAll(); break;
			case 2 : controller.selectSearchEmployee(); break;
			case 3 : controller.insertEmployee();break;
			case 4 : controller.updateEmployee();break;
			case 5 : controller.deleteEmployee();break;
			case 6 : break;
			case 7 : break;
			case 0 : System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("없는 메뉴입니다. 다시 선택해주세요"); break;
			}
		}
	}
	
	public void printEmployees(List<Employee> employees) {
		System.out.println("==== 조회한 결과 ====");
		if(employees.isEmpty()) {
			System.out.println("조회한 결과 없습니다.");
		}
		else {employees.forEach(e->System.out.println(e));
		}	
		System.out.println("===================");
	}
	
	public void printMsg(String msg) {
		System.out.println("==== 조회된 결과 ====");
		System.out.println(msg);
		System.out.println("===================");
	}
	public Employee inputEmployee() {
		Scanner sc=new Scanner(System.in);
		Employee e=new Employee();
		System.out.println("==== 사원등록 ====");
		System.out.print("사원아이디 : ");
		e.setEmpId(sc.nextLine());
		System.out.print("사원이름 : ");
		e.setEmpName(sc.nextLine());
		System.out.print("사원주민번호 : ");
		e.setEmpNo(sc.nextLine());
		System.out.print("이메일 : ");
		e.setEmail(sc.nextLine());
		System.out.print("핸드폰 번호 : ");
		e.setPhone(sc.nextLine());
		System.out.print("부서코드 : ");
		e.setDeptCode(sc.nextLine());
		System.out.print("직책코드 : ");
		e.setJobCode(sc.nextLine());
		System.out.print("급여레벨 : ");
		e.setSalLevel(sc.nextLine());
		System.out.print("급여 : ");
		e.setSalary(sc.nextInt());
		System.out.print("보너스 : ");
		e.setBonus(sc.nextInt());
		sc.nextLine();
		System.out.print("매니저 아이디 : ");
		e.setManagerId(sc.nextLine());
		return e;
	}
	public Employee updateEmployee() {
		Employee e=new Employee();
		Scanner sc=new Scanner(System.in);
		System.out.println("====사원정보 수정====");
		System.out.print("수정할 사원ID : ");
		e.setEmpId(sc.next());
		System.out.print("직책 : ");
		e.setJobCode(sc.next());
		System.out.print("부서 : ");
		e.setDeptCode(sc.next());
		System.out.print("급여 : ");
		e.setSalary(sc.nextInt());
		System.out.print("핸드폰 번호 : ");
		e.setPhone(sc.next());
		System.out.print("이메일 : ");
		e.setEmail(sc.next());		
		return e;
	}
	public Employee deleteEmployee() {
		Employee e=new Employee();
		Scanner sc=new Scanner(System.in);
		System.out.println("====퇴사====");
		System.out.print("퇴사한 사원ID : ");
		e.setEmpId(sc.next());
		return e;
	}
	
	public Map inputSearch() {
		Scanner sc=new Scanner(System.in);
		System.out.println("==== 사원조회 항목별 검색 ====");
		System.out.print("항목 (1.부서 2.직책 3.이름 4.급여(크고작고) : ");
		int colCho=sc.nextInt();
		sc.nextLine();
		String col="";
		switch(colCho) {
			case 1 : col="dept_title"; break;
			case 2 : col="job_name"; break;
			case 3 : col="emp_name"; break;
			case 4 : return inputSalary();
			default : System.out.println("잘못입력했습니다. 다시 입력해주세요"); break;
		}
		System.out.print("검색어 : ");
		String keyword=sc.nextLine();
		
		return Map.of("col",col,"keyword",keyword);
	}
	
	public Map inputSalary() {
		EmployeeController controller=new EmployeeController();
		Scanner sc=new Scanner(System.in);
		System.out.println("==== 급여 항목별 검색 ====");
		System.out.print("급여(1.크다 2.작다) : ");
		int colCho=sc.nextInt();
		sc.nextLine();
		String col="salary";
		int keyword=0;
		System.out.print("기준급여 : ");
		keyword=sc.nextInt();
		switch(colCho) {
			case 1 : break;
			case 2 : break;
			default : System.out.println("잘못입력했습니다. 다시 입력해주세요"); break;
		}

		return Map.of("col",col,"keyword",keyword);
	}
	
	public void departmentManager() {
		
	}
}
