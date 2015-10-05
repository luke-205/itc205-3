import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import library.entities.Book;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.EBookState;

public class BookTest {
	private ILoan _loan;
	private Book book;

	@Before
	public void setUp() {
		_loan = mock(ILoan.class);
		book = new Book("Test author", "Test title", "Test callnumber", 123456);
	}

	@Test
	public void testBorrow() {
		book.borrow(_loan);
		assertEquals(book.getLoan(), _loan);
		assertEquals(book.getState(), EBookState.ON_LOAN);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBorrowThrowsIllegalArgumentException() {
		book.borrow(null);
	}

	@Test(expected=RuntimeException.class)
	public void testBorrowThrowsRuntimeException() {
		book.borrow(_loan);
		book.borrow(_loan);
	}

	@Test
	public void testReturnBookNotDamaged() {
		book.borrow(_loan);
		book.returnBook(false);
		assertEquals(book.getLoan(), null);
		assertEquals(book.getState(), EBookState.AVAILABLE);
	}

	@Test
	public void testReturnBookDamaged() {
		book.borrow(_loan);
		book.returnBook(true);
		assertEquals(book.getLoan(), null);
		assertEquals(book.getState(), EBookState.DAMAGED);
	}

	@Test(expected=RuntimeException.class)
	public void testReturnBookThrowsRuntimeException() {
		book.returnBook(true);
	}

	@Test
	public void testLose() {
		book.borrow(_loan);
		book.lose();
		assertEquals(book.getState(), EBookState.LOST);
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("BookTest");
	} 
}