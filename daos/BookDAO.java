package library.daos;

import java.util.List;
import java.util.ArrayList;

import library.interfaces.entities.IBook;
import library.interfaces.daos.IBookHelper;
import library.interfaces.daos.IBookDAO;

public class BookDAO implements IBookDAO {

	private int nextID = 0;
	private IBookHelper helper;
	private List<IBook> bookList = new ArrayList<IBook>();

	BookDAO(IBookHelper helper) {
		if(helper == null)
			throw new IllegalArgumentException("Helper is null");
		this.helper = helper;
	}
	
	public IBook addBook(String author, String title, String callNo) {
		IBook book = helper.makeBook(author, title, callNo, nextID);
		nextID++;
		bookList.add(book);
		return book;
	}
	
	public IBook getBookByID(int id) {
		for(IBook book : bookList)
			if(book.getID() == id)
				return book;
		return null;
	}
	
	public List<IBook> listBooks() {
		return bookList;
	}
	
	public List<IBook> findBooksByAuthor(String author) {
		List<IBook> books = new ArrayList<IBook>();

		for(IBook book : bookList)
			if(book.getAuthor() == author)
				books.add(book);

		return books;
	}

	public List<IBook> findBooksByTitle(String title) {
		List<IBook> books = new ArrayList<IBook>();

		for(IBook book : bookList)
			if(book.getTitle() == title)
				books.add(book);

		return books;
	}
	
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		List<IBook> books = new ArrayList<IBook>();

		for(IBook book : bookList)
			if(book.getAuthor() == author && book.getTitle() == title)
				books.add(book);

		return books;
	}

}
