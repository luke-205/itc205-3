import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import library.interfaces.entities.IBook;
import library.daos.BookHelper;
import library.interfaces.daos.IBookHelper;

public class BookHelperTest {
	IBookHelper bookHelper;

	@Before
	public void setUp() {
		bookHelper = new BookHelper();
	}

	@After
	public void tearDown() {
		bookHelper = null;
	}

	@Test
	public void testMakeBook() {
		IBook book = bookHelper.makeBook("Test author", "Test title", "Test callnumber", 1);

		assertEquals("Test author", book.getAuthor());
		assertEquals("Test title", book.getTitle());
		assertEquals("Test callnumber", book.getCallNumber());
		assertEquals(1, book.getID());
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("BookHelperTest");
	}

}