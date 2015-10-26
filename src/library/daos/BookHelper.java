package library.daos;

import library.interfaces.entities.IBook;
import library.entities.Book;
import library.interfaces.daos.IBookHelper;

public class BookHelper implements IBookHelper {
	
	public IBook makeBook(String author, String title, String callNumber, int id) {
		IBook book = new Book(author, title, callNumber, id);
		return book;
	}

}
