package practice;

import java.util.Scanner;

public class ScoreMgmSystemPractice {
	//Field
	String admin;
	StudentPractice student;// = new Student(); //최초에 호출하는것도 가능하나, 사용 직전에 호출하는것이 메모리적으로 효율적.
	Scanner input = new Scanner(System.in);
	StudentPractice slist[] = new StudentPractice[10];
	boolean stuInput = true;
	boolean showOuput = true;
	int count = 0;
	
	//Cons
	public ScoreMgmSystemPractice() {
		
	}
	
	//Method
	//메뉴 선택
	public void showMenu() {
		System.out.println("----------------------------------------------");
		System.out.println("\t더조은 고등학교 1-1 성적관리");
		System.out.println("----------------------------------------------");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 리스트 출력");
		System.out.println("3. 학생 성적 검색");
		System.out.println("4. 학생 성적 수정");
		System.out.println("5. 학생 삭제");
		System.out.println("9. 프로그램 종료");
		System.out.println("----------------------------------------------");
		
		System.out.print("메뉴 선택(숫자)> ");
		// 1.
//		int menu = input.nextInt();
//		choiceMenu(menu);

		menuCheck(input.nextInt());
		
		
	}
	
	//선택한 메뉴에 따라 기능별 메소드 호출(분기)
	public void menuCheck(int menu) {
		System.out.println("choiceMenu --> "+menu);
		
		switch(menu) {
		case 1:
			insert();
			break;
		case 2:
			showList();
			break;
		case 3:
			search();
			System.out.println("검색");
			break;
		case 4:
			update();
			System.out.println("수정");
			break;
		case 5:
			remove();
			System.out.println("삭제");
			break;
		case 9:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		default :
			System.out.println("잘못된 접근입니다.");
			showMenu();
		}
	}
	
	public void insert() {
//		student=new Student();
		student = new StudentPractice();
		int i =0;
		
		while(stuInput) {
				System.out.print("학생명> ");
				student.name[i]=input.next();
				System.out.print("국어> ");
				student.kor[i]=input.nextInt();
				System.out.print("영어> ");
				student.eng[i]=input.nextInt();
				System.out.print("수학> ");
				student.math[i]=input.nextInt();
				System.out.println(">> 등록완료");
				i++;
				while (stuInput) {
					System.out.print("계속 입력하시겠습니까?(y/n)> ");
					String check = input.next();
					if(check.equals("y")) {
						break;
					} else if (check.equals("n")) {
						stuInput = false;
					}
					else {
						System.out.println("잘못된 입력입니다.");
					}
				}
		}
		showMenu();
	}
	public void showList() {
		int i=0;
		for (int j=0;j<student.name.length;j++) {
			System.out.println("<"+(i+1)+">");
			System.out.println("학생명\t국어\t영어\t수학\t총점\t평균");
			System.out.print(student.name[i]+"\t");
			System.out.print(student.kor[i]+"\t");
			System.out.print(student.eng[i]+"\t");
			System.out.print(student.math[i]+"\t");
			student.getTot();
			System.out.print(student.tot[i]+"\t");
			student.getAvg();
			System.out.print(student.avg[i]+"\n");
			i++;
		}
		
	}
	
	public void update() {
		System.out.println("update");
	}
	public void remove() {
		System.out.println("remove");
	}
	public void search() {
		System.out.println("search");
	}
}
