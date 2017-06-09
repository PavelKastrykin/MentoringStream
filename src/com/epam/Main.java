package com.epam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	private static Author[] authors;
	private static Book[] books;

	private static void createAuthors(int quantity) {
		authors = new Author[quantity];
		for(int i = 0; i < quantity; i++) {
			authors[i] = new Author(UUID.randomUUID().toString().replaceAll("-", ""), (short)(i * 2));
		}
	}

	private static void createBooks(int quantity) {
		books = new Book[quantity];
		for(int i = 0; i < quantity; i++) {
			books[i] = new Book(UUID.randomUUID().toString().replaceAll("-", ""), i * 10 + 10);
		}
	}

	private static void flushAuthorsBooks(Author[] authors, Book[] books) {
		for(int i = 0; i < authors.length; i++) {
			int quantityOfBooksForCurrentAuthor = ThreadLocalRandom.current().nextInt(1, 6);
			for (int j = 0; j < quantityOfBooksForCurrentAuthor; j++) {
				int bookIndex = ThreadLocalRandom.current().nextInt(0, books.length);
				if (!authors[i].getBooks().contains(books[bookIndex])) {
					authors[i].getBooks().add(books[bookIndex]);
					books[bookIndex].getAuthors().add(authors[i]);
				}
			}
		}
	}

	public static void main(String[] args) {
		createAuthors(100);
		createBooks(100);
		flushAuthorsBooks(authors, books);
		System.out.println();

		//#1
		System.out.println("Checking if have books with q-ty pages over 200:---------");
		boolean haveBooksWithQtyPagesOver200 = Arrays.stream(books)
				.anyMatch(book -> book.getNumberOfPages() > 200);
		System.out.println(haveBooksWithQtyPagesOver200);

		System.out.println();
		//#2
		System.out.println("Find books with min and max number of pages:-----");
		Book bookWithMinNumberOfPages = Arrays.stream(books)
				.min(Comparator.comparing(Book::getNumberOfPages)).get();
		Book bookWithMaxNumberOfPages = Arrays.stream(books)
				.max(Comparator.comparing(Book::getNumberOfPages)).get();
		System.out.println(bookWithMinNumberOfPages.getTitle() + " - " + bookWithMinNumberOfPages.getNumberOfPages() + " pages");
		System.out.println(bookWithMaxNumberOfPages.getTitle() + " - " + bookWithMaxNumberOfPages.getNumberOfPages() + " pages");

		System.out.println();
		//#3
		System.out.println("Filter books with only single author:-----------");
		Arrays.stream(books)
				.filter(book -> book.getAuthors().size() == 1)
				.forEach(book -> System.out.println(book.getTitle()));

		System.out.println();
		//#4
		System.out.println("Sort the books by number of pages:---------------");
		Arrays.stream(books)
				.sorted(Comparator.comparing(Book::getNumberOfPages))
				.forEach(book -> System.out.println(book.getTitle() + " - " + book.getNumberOfPages() + " pages."));

		System.out.println();
		//#4_2
		System.out.println("Sort the books by title:---------------");
		Arrays.stream(books)
				.sorted(Comparator.comparing(Book::getTitle))
				.forEach(book -> System.out.println(book.getTitle()));

		System.out.println();
		//#5 - get list of all titles
		List<String> listOfAllTitles = Arrays.stream(books)
				.map(Book::getTitle)
				.collect(Collectors.toList());

		System.out.println();
		//#6
		System.out.println("Printing all titles:--------------");
		listOfAllTitles.forEach(System.out::println);

		System.out.println();
		//#7
		System.out.println("Get distinct List of all authors:------------");
		Set<Author> distinctListOfAllAuthors = Arrays.stream(books)
				.flatMap(book -> book.getAuthors().stream())
				.collect(Collectors.toSet());

	}
}
