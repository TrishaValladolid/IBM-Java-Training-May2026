
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library lib = new Library();

        // Add at least 3 books
        lib.addBook(new Book("Twilight 1", "Stephenie Meyer", true));
        lib.addBook(new Book("Twilight 2", "Stephenie Meyer", true));
        lib.addBook(new Book("Twilight 3", "Stephenie Meyer", true));

        while (true) {
            System.out.println("\nLibrary menu:");
            System.out.println("1. Show all books");
            System.out.println("2. Add a book");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> lib.showAllBooks();
                case "2" -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine().trim();
                    lib.addBook(new Book(title, author, true));
                }
                case "3" -> {
                    System.out.print("Enter title to borrow: ");
                    String title = scanner.nextLine().trim();
                    lib.borrowBooks(title);
                }
                case "4" -> {
                    System.out.print("Enter title to return: ");
                    String title = scanner.nextLine().trim();
                    lib.returnBook(title);
                }
                case "5" -> {
                    System.out.println("Goodbye.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }
}
