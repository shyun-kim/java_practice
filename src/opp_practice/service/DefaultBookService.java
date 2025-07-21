package practice.service;

import java.util.List;
import java.util.Random;

import practice.application.BookManagementApplication;
import practice.model.Book;
import practice.repository.BookRepository;

public class DefaultBookService implements BookService{
	BookManagementApplication bma;
	BookRepository repository;
	
	public DefaultBookService() {}
	public DefaultBookService(BookManagementApplication bma) {
		this.bma=bma;
//		selectRepository();
	}
	
	/**
	 * 도서관 선택
	 */
//	public void selectRepository(){
//		System.out.println("================================");
//		System.out.println("1. 교육센터\t 2. 알라딘\t 3. 예스24");
//		System.out.println("================================");
//		System.out.print("도서관 선택>");
//		int rno = bma.scan.nextInt();
//		if(rno == 1) {
//			repository = new InMemoryBookRepository();
//		} else if(rno == 2) {
//			repository = new AladinBookRepository();
//		} else if(rno == 3) {
//			repository = new Yes24BookRepository();
//		}
//	}
	/**
	 * 도서 생성
	 */
	public Book createBook() {
		Random rd = new Random();
		Book book = new Book();
		book.setId(String.valueOf(rd.nextInt(1000,9999)));
		
		System.out.print("도서명> ");
		book.setName(bma.scan.next());

		System.out.print("저자> ");
		book.setAuthor(bma.scan.next());
		
		System.out.print("가격> ");
		book.setPrice(bma.scan.nextInt());
		return book;
	}
	/**
	 * 도서 생성(수정시)
	 * @param book
	 */
	public Book createBook(Book book) {
		System.out.print("도서명> ");
		book.setName(bma.scan.next());
		
		System.out.print("저자> ");
		book.setAuthor(bma.scan.next());
		
		System.out.print("가격> ");
		book.setPrice(bma.scan.nextInt());
		return book;
	}
	
	
	/**
	 * 도서 등록
	 */
	@Override
	public void register() {
//		selectRepository();
		Book book = createBook();
		if(repository.insert(book)) {
			System.out.println(">도서 등록 완료");
		} else {
			System.out.println(">도서 등록 실패");
		}
		bma.showMenu();
	}
	
	/**
	 * 도서 조회
	 */
	@Override
	public void list() {
		if(getCount() != 0) {
			List<Book> library = repository.selectAll();
			library.forEach(book -> {
				System.out.print("["+book.getId()+"]\t");
				System.out.print(book.getName()+"\t");
				System.out.print(book.getAuthor()+"\t");
				System.out.print(book.getPrice()+"\t");
			});
		} else {
			System.out.println(">등록된 도서가 없습니다.");
		}
		bma.showMenu();
	}
	
	@Override
	public void search() {
		if(getCount() !=0) {
			System.out.println("도서 번호(4자리)> ");
			Book book = repository.select(bma.scan.next());
			if (book != null) {
				printBook(book);
			}
		}
	}
	
	@Override
	public void update() {
		if(getCount() !=0) {
			System.out.println("도서번호> ");
			Book book = repository.select(bma.scan.next());
			if(book != null) {
				repository.update(createBook(book));
				System.out.println("도서 수정 완료");
				printBook(book);
			} else {
				System.out.println(">검색된 도서가 없습니다.");
			}
		} else {
			System.out.println("등록된 도서가 없습니다.");
		}
		bma.showMenu();
	}
	
	/**
	 *  도서 출력
	 */
	public void printBook(Book book) {
		System.out.println("-------------------------------------------------");
		System.out.print("["+book.getId()+"]\t");
		System.out.print(book.getName()+"\t");
		System.out.print(book.getAuthor()+"\t");
		System.out.print(book.getPrice()+"\n");
		System.out.println("-------------------------------------------------");
	}
	
	@Override
	public void delete() {
		if(getCount() != 0) {
			System.out.print("도서번호(4자리)> ");
			Book book = repository.select(bma.scan.next());
			if(book !=null) {
//				repository.remove(book.getId());
				repository.remove(book);
				System.out.println("✅ 도서 삭제 완료");
			} else {
				System.out.println(">>검색된 도서가 없습니다.");
			}
		} else {
			System.out.println(">>등록된 도서가 없습니다.");
		}
		bma.showMenu();
	}
	
	@Override
	public void exit() {
		System.out.println("시스템이 종료됩니다.");
		System.exit(0);
	}
	
	@Override
	public int getCount() {
		return repository.getCount();
	}
}
