package com.kh.jdbc.day02.student.view;

import java.util.*;

import com.kh.jdbc.day02.student.controller.StudentController;
import com.kh.jdbc.day02.student.model.vo.Student;

public class StudentView {
	
	private StudentController controller;
	
	public StudentView() {
		controller = new StudentController();
	}
	
	public void startProgram() {
		Student student = null;
		List<Student> sList = null;
		String studentId = "";
		int result = -1;
		end :
		while(true) {
			int choice = printMenu();
			switch(choice) {
			case 1: 
				sList = controller.printStudentList();
				if(!sList.isEmpty()) {
					showAllStudents(sList);
				} else {
					displayError("학생 정보가 조회되지 않습니다.");
				}
				break;
			case 2: 
				studentId = inputStudentId();
				student = controller.printStudentById(studentId);
				if(student != null) {
					showStudent(student);
				} else {
					displayError("학생 정보가 존재하지 않습니다.");
				}
				break;
			case 3: 
				String studentName = inputStudentName();
				sList = controller.printStudentsByName(studentName);
				if(!sList.isEmpty()) {
					showAllStudents(sList);
				} else {
					displayError("학생 정보가 조회되지 않습니다.");
				}
				break;
			case 4: 
				// INSERT INTO STUDENT_TBL VALUES('khuser01', 'pass01', '일용자', 'M', 11,
				// 'khuser01@naver.com', '01065432123', '서울시 중구 남대문로 120', '독서,수영', SYSDATE);
				student = inputStudent();
				result = controller.insertStudent(student);
				if(result > 0) {
					// 성공 메세지 출력
					displaySuccess("학생 정보 등록 성공");
				} else {
					// 실패 메세지 출력
					displayError("학생 정보 등록 실패");
				}
				break;
			case 5: 
				studentId = inputStudentId();
				student = modifyStudent();
				result = controller.modifyStudent(studentId, student);
				if(result > 0) {
					displaySuccess("학생 정보 수정 성공");
				} else {
					displayError("학생 정보 수정 실패");
				}
				break;
			case 6: 
				studentId = inputStudentId();
				result = controller.deleteStudent(studentId);
				if(result > 0) {
					// 성공 메세지 출력
					displaySuccess("학생 정보 삭제 성공");
				} else {
					// 실패 메세지 출력
					displayError("학생 정보 삭제 실패");
				}
				break;
			case 0: 
				break end;
			}
		}
	}
	
	private Student modifyStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 정보 수정 =====");
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("비밀번호 : ");
		String studentPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 공백 제거, 엔터 제거
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();
		Student student = new Student(studentId, studentPw, email, phone, address, hobby);
		return student;
	}

	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 관리 프로그램 =====");
		System.out.println("1. 학생 전체 조회 ");
		System.out.println("2. 학생 아이디로 조회 ");
		System.out.println("3. 학생 이름으로 조회 ");
		System.out.println("4. 학생 정보 등록 ");
		System.out.println("5. 학생 정보 수정 ");
		System.out.println("6. 학생 정보 삭제 ");
		System.out.println("0. 프로그램 종료 ");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;
	}

	private String inputStudentId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 아이디로 조회 =====");
		System.out.print("학생 아이디 입력 : ");
		String studentId = sc.next();
		return studentId;
	}

	private String inputStudentName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 학생 이름으로 조회 =====");
		System.out.print("학생 이름 입력 : ");
		String studentName = sc.next();
		return studentName;
	}

	private Student inputStudent() {
			Scanner sc = new Scanner(System.in);
			System.out.println("===== 학생 정보 등록 =====");
			System.out.print("아이디 : ");
			String studentId = sc.next();
			System.out.print("비밀번호 : ");
			String studentPw = sc.next();
			System.out.print("이름 : ");
			String studentName = sc.next();
			System.out.print("성별 : ");
			char gender = sc.next().charAt(0);
			System.out.print("나이 : ");
			int age = sc.nextInt();
			System.out.print("이메일 : ");
			String email = sc.next();
			System.out.print("전화번호 : ");
			String phone = sc.next();
			System.out.print("주소 : ");
			sc.nextLine(); // 공백 제거, 엔터 제거
			String address = sc.nextLine();
			System.out.print("취미(,로 구분) : ");
			String hobby = sc.next();
	//		Student student = new Student();
	//		student.setStudentId(studentId);
			Student student = new Student(studentId, studentPw, studentName, gender,
					age, email, phone, address, hobby);
			return student;
		}

	private void displaySuccess(String message) {
		System.out.println("[서비스 성공] : " + message);
	}

	private void displayError(String message) {
		System.out.println("[서비스 실패] : " + message);
	}

	private void showAllStudents(List<Student> sList) {
		System.out.println("===== 학생 전체 정보 출력 =====");
		for(Student student : sList) {
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %s, 이메일 : %s"
					+ ", 전화번호 : %s, 주소 : %s, 취미 : %s, 가입날짜 : %s\n",
					student.getStudentName(), student.getAge(), student.getStudentId(),
					student.getGender(), student.getEmail(), student.getPhone(),
					student.getAddress(), student.getHobby(), student.getEnrollDate());
		}
	}

	private void showStudent(Student student) {
		System.out.println("===== 학생 정보 출력(아이디로 조회) =====");
		System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %s, 이메일 : %s"
				+ ", 전화번호 : %s, 주소 : %s, 취미 : %s, 가입날짜 : %s\n",
				student.getStudentName(), student.getAge(), student.getStudentId(),
				student.getGender(), student.getEmail(), student.getPhone(),
				student.getAddress(), student.getHobby(), student.getEnrollDate());
	}
}
