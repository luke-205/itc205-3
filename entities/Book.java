package library.entities;

import library.interfaces.entities.*;

public class Book implements IBook {

	private String author;
	private String title;
	private String callNumber;
	private int bookID;
	private EBookState state;
	private ILoan loan;

	public Book(String author, String title, String callNumber, int bookID) {
		if(!(sane(author, title, callNumber, bookID)))
			throw new IllegalArgumentException("Invalid parameters");

		this.author = author;
		this.title = title;
		this.callNumber = callNumber;
		this.bookID = bookID;
		this.state = EBookState.AVAILABLE;
		this.loan = null;
	}

	private boolean sane(String author, String title, String callNumber, int bookID) {
		return (author != null && !author.isEmpty() && title != null && !title.isEmpty() &&
		   		callNumber != null && !callNumber.isEmpty() && bookID > 0)
	}

	public void borrow(ILoan loan) {
		if(state != EBookState.AVAILABLE)
			throw new RuntimeException("Book currently not available");
		this.loan = loan;
		state = EBookState.ON_LOAN;
	}
	
	public ILoan getLoan() {
		return loan;
	}
	
	public void returnBook(boolean damaged) {
		if(state != EBookState.ON_LOAN)
			throw new RuntimeException("Book is currently not on loan");
		this.loan = null;
		if(damaged) {
			state = EBookState.DAMAGED;
		} else {
			state = EBookState.AVAILABLE;
		}
	}
	
	public void lose() {
		if(state != EBookState.ON_LOAN)
			throw new RuntimeException("Book is currently not lost");
		state = EBookState.LOST;
	}
	
	public void repair() {
		if(state != EBookState.DAMAGED)
			throw new RuntimeException("Book is currently not damaged");
		state = EBookState.AVAILABLE;
	}
	
	public void dispose() {
		if(state != EBookState.AVAILABLE || state != EBookState.DAMAGED || state != EBookState.LOST)
			throw new RuntimeException("No reason to dispose of book");
		state = EBookState.DISPOSED;
	}	
	
	public EBookState getState() {
		return state;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCallNumber() {
		return callNumber;
	}
	
	public int getID() {
		return bookID;
	}

}