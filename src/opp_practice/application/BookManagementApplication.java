package practice.application;

import java.util.Scanner;

import com.bookmgm.service.BookService;

import practice.service.DefaultBookService;

public class BookManagementApplication {
	
	public Scanner scan;
	public static final int REGISTER = 1;
	public static final int LIST = 2;
	public static final int SEARCH = 3;
	public static final int UPADATE = 4;
	public static final int DELETE = 5;
	public static final int EXIT = 6;
	public BookService service;
	
	public BookManagementApplication() {
		scan = new Scanner(System.in);
		service = (BookService) new DefaultBookService(this);
		showMenu();
	}
	
	/**
	 * 메뉴 출력
	 */
	public void showMenu() {
		System.out.println("===========도서관리===========");
		System.out.println("전체 도서수: "+service.getCount());
		String labels[] = {"도서 등록","도서 목록 조회","도서 검색","도서 수정","도서 삭제","종료"};
		for(int i=0;i<labels.length;i++) {
			System.out.println((i+1)+". "+labels[i]);
		}
		
		System.out.println("===========================");
		selectMenu();
	}
	
	public void selectMenu() {
		System.out.println("메뉴(숫자)> ");
		if(scan.hasNextInt()) {
			switch(scan.nextInt()) {
				case REGISTER:
					service.register();
					break;
				case LIST:
					service.list();
					break;
				case SEARCH:
					service.search();
					break;
				case UPADATE:
					service.update();
					break;
				case DELETE:
					service.delete();
					break;
				case EXIT:
					service.exit();
					break;
				default:
					System.out.println(">메뉴 준비중 입니다.");
					selectMenu();
			}
		} else {
			System.out.println("잘못된 형식 입력입니다. 다시 선택해 주세요");
			scan.next();
			selectMenu();
		}	
	}
	
	public static void main(String[] args) {
		new BookManagementApplication();
	}

}
