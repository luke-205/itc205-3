import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import library.entities.Book;
import library.interfaces.entities.IBook;
import library.daos.BookDAO;
import library.interfaces.daos.IBookDAO;
import library.daos.BookHelper;
import library.interfaces.daos.IBookHelper;

import java.util.List;
import java.util.Arrays;

public class BookDAOHelperTest {
	IBook book;
	IBookDAO dao;
	IBookHelper bookHelper;

	@Before
	public void setUp() {
		bookHelper = new BookHelper();
		dao = new BookDAO(bookHelper);
	}

	@After
	public void tearDown() {
		bookHelper = null;
		dao = null;
	}

	@Test
	public void testAddBook() {
		book = dao.addBook("Test author", "Test title", "Test callnumber");
		assertEquals("Test author", book.getAuthor());
		assertEquals("Test title", book.getTitle());
		assertEquals("Test callnumber", book.getCallNumber());
		assertEquals(1, book.getID());
	}

	@Test
	public void testGetBookByID() {
		book = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook testBook = dao.getBookByID(1);
		assertEquals(book, testBook);
	}

	@Test
	public void testFindBookByAuthor() {
		book = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = dao.addBook("Test author", "Test title", "Test callnumber");
		List<IBook> bookList = dao.findBooksByAuthor("Test author");
		List<IBook> testList = Arrays.asList(book, book2);
		assertThat(bookList, is(testList));
	}

	@Test
	public void testFindBooksByTitle() {
		book = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = dao.addBook("Test author", "Test title", "Test callnumber");
		List<IBook> bookList = dao.findBooksByTitle("Test title");
		List<IBook> testList = Arrays.asList(book, book2);
		assertThat(bookList, is(testList));
	}

	@Test
	public void testFindBooksByAuthorTitle() {
		book = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = dao.addBook("Test author", "Test title", "Test callnumber");
		List<IBook> bookList = dao.findBooksByAuthorTitle("Test author", "Test title");
		List<IBook> testList = Arrays.asList(book, book2);
		assertThat(bookList, is(testList));
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("BookDAOHelperTest");
	}
}