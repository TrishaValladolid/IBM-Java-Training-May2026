
import java.util.*;

public class Library {
	// fields
	//List<String> books = new ArrayList<>();
	List<Book> books = new ArrayList<>();
	
	// addBook method
	public void addBook(Book b) {
		books.add(b);
		System.out.println("Book added");
	}
	 	
	//showAllBooks method
	public void showAllBooks() {
		System.out.println("List of books: ");
		for (Book book : books) {
			System.out.println(book.getInfo());
		}
			
	}
	
	//borrowBook method
	public int borrowBooks(String title) {
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				if (book.borrowBook()) {
					System.out.println("Book borrowed");
					return 1;
				}
				System.out.println("Book is already borrowed");
				return 0;
			}
		}
		System.out.println("Book not found");
		return 0;
	}

	//returnBook method
	public int returnBook(String title) {
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				if (book.returnBook()) {
					System.out.println("Book returned");
					return 1;
				}
				System.out.println("Book was not borrowed");
				return 0;
			}
		}
		System.out.println("Book not found");
		return 0;
	}
}
