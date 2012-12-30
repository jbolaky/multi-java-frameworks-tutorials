package com.mytutorials.spring.batch.domain;

public class Author {

	private String firstName;

	private String lastName;
	
	private String bookTitle;

	private Book book;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		boolean equals = false;

		if (obj != null && ((Author) obj).getFirstName().equals(this.firstName)
				&& ((Author) obj).getLastName().equals(this.lastName)) {

			equals = true;
		}

		return equals;
	}

	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", lastName=" + lastName
				+ ", bookTitle=" + bookTitle + ", book=" + book + "]";
	}

}
