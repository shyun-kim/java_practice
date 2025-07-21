package practice.repository;

import java.util.List;
import practice.model.Book;

public interface BookRepository {
	
	boolean insert(Book book);
	List<Book> selectAll();
	void remove(String id);
	void remove(Book book);
	void update(Book book);
	Book select(String id);
	int getCount();
	

}
