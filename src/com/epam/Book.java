package com.epam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
	private String title;
	private Integer numberOfPages;
	private List<Author> authors = new ArrayList<>();

	public Book() {}

	public Book(String title, int numberOfPages) {
		this.title = title;
		this.numberOfPages = numberOfPages;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Integer getNumberOfPages()
	{
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages)
	{
		this.numberOfPages = numberOfPages;
	}

	public List<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<Author> authors)
	{
		this.authors = authors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Book book = (Book) o;
		return numberOfPages == book.numberOfPages &&
				Objects.equals(title, book.title) &&
				Objects.equals(authors, book.authors);
	}

	@Override public int hashCode()
	{
		return Objects.hash(title, numberOfPages, authors);
	}
}
