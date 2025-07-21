package opp_practice.repository;

import java.util.List;
import opp_practice.model.Book;

public interface BookRepository {
	
	boolean insert(Book book);
	List<Book> selectAll();
	Book select(String id);
	void update(Book book);
	void remove(String id);
	void remove(Book book);
	int getCount();
	

}
