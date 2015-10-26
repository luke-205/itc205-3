import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Date;

import library.entities.Book;
import library.interfaces.entities.IBook;
import library.interfaces.entities.EBookState;
import library.entities.Member;
import library.interfaces.entities.IMember;
import library.entities.Loan;
import library.interfaces.entities.ILoan;

public class BookLoanTest {
	ILoan loan;
	IBook book;
	IMember member;

	@Before
	public void setUp() {
		member = new Member("Test firstname", "Test lastname", "Test phonenumber", "Test email", 124578);
		book = new Book("Test author", "Test title", "Test callnumber", 123456);
		loan = new Loan(book, member, new Date(), new Date(System.currentTimeMillis() + 86400000));
	}

	@After
	public void tearDown() {
		member = null;
		book = null;
		loan = null;
	}

	@Test
	public void testBorrow() {
		book.borrow(loan);
		assertEquals(book.getLoan(), loan);
		assertEquals(book.getState(), EBookState.ON_LOAN);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBorrowThrowsIllegalArgumentException() {
		book.borrow(null);
	}

	@Test(expected=RuntimeException.class)
	public void testBorrowThrowsRuntimeException() {
		book.borrow(loan);
		book.borrow(loan);
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("BookLoanTest");
	}
}