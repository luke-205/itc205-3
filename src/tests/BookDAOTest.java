import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.List;

import library.daos.BookHelper;
import library.daos.BookDAO;
import library.entities.Book;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookDAOTest {
	IBookHelper spyHelper;

	@Before
	public void setUp() {
		spyHelper = spy(new BookHelper());
	}

	@After
	public void tearDown() {
		spyHelper = null;
	}

	@Test
	public void testAddBook() {
		BookDAO dao = new BookDAO(spyHelper);
		dao.addBook("Test author", "Test title", "Test callnumber");
		verify(spyHelper).makeBook(anyString(), anyString(), anyString(), anyInt());
	}

	@Test
	public void testGetBookByID() {
		BookDAO dao = new BookDAO(spyHelper);
		IBook book = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook testBook = dao.getBookByID(book.getID());
		assertEquals(book, testBook);
	}

	@Test
	public void testGetBookByIDReturnsNull() {
		BookDAO dao = new BookDAO(spyHelper);
		IBook book1 = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = spyHelper.makeBook("Test author", "Test title", "Test callnumber", 2);
		IBook testBook = dao.getBookByID(book2.getID());
		assertNull(testBook);
	}

	@Test
	public void testFindBooksByAuthor() {
		BookDAO dao = new BookDAO(spyHelper);
		IBook book1 = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = dao.addBook("Test author", "Test title", "Test callnumber");
		List<IBook> bookList = dao.findBooksByAuthor("Test author");
		List<IBook> testList = Arrays.asList(book1, book2);
		assertThat(bookList, is(testList));
	}

	@Test
	public void testFindBooksByTitle() {
		BookDAO dao = new BookDAO(spyHelper);
		IBook book1 = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = dao.addBook("Test author", "Test title", "Test callnumber");
		List<IBook> bookList = dao.findBooksByTitle("Test title");
		List<IBook> testList = Arrays.asList(book1, book2);
		assertThat(bookList, is(testList));
	}

	@Test
	public void testFindBooksByAuthorTitle() {
		BookDAO dao = new BookDAO(spyHelper);
		IBook book1 = dao.addBook("Test author", "Test title", "Test callnumber");
		IBook book2 = dao.addBook("Test author", "Test title", "Test callnumber");
		List<IBook> bookList = dao.findBooksByAuthorTitle("Test author", "Test title");
		List<IBook> testList = Arrays.asList(book1, book2);
		assertThat(bookList, is(testList));
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("BookDAOTest");
	}
}