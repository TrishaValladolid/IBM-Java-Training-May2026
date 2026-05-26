
public class Book {
	private String title; 
	private String author;
	private Boolean available;
	
	// constructor to set title, author, and availability
	public Book(String title, String author, Boolean available) {
		this.title = title;
		this.author = author;
		this.available = available;
	}
	
	//getter
	public String getTitle() {
		return title;
	}
	
	//getter
	public String getAuthor() {
		return author;
	}	
		
	//getter
	public Boolean getAvailablity() {
		 return available;
	}
			
	// borrowBook method
	public boolean borrowBook() {
		if (available) {
			available = false;
			return true;
		}
		return false;
	}
	
	// returnBook method
	public boolean returnBook() {
		if (!available) {
			available = true;
			return true;
		}
		return false;
	}
	
	// getInfo method
	public String getInfo() {
		return "Title: " + title + "\nAuthor: " + author + "\nAvailability: " + available;
	}
}
