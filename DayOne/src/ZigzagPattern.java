import java.util.Scanner;

public class ZigzagPattern {
	public int displayGrid(int size) {
		int num = 1;
	  
	    for (int row = 0; row < size; row++) {
	        if (row % 2 == 0) { //EVEN
	            // Left to right
	            for (int col = 0; col < size; col++) {
	                System.out.printf("%4d", num++); 
	            }
	            
	        } else { // ODD
	            // Right to left
	            int[] tempRow = new int[size]; // new array 
	            for (int col = 0; col < size; col++) {
	                tempRow[col] = num++;
	            
	            }
	            for (int col = size - 1; col >= 0; col--) { // out of bounds error 
	                System.out.printf("%4d", tempRow[col]);
	            }
	        }
	        System.out.println("");
	    }
	
		
		return 0;
	}
	
    
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    ZigzagPattern zigzagpattern = new ZigzagPattern();
	    
	    int size = 0;
	    System.out.println("Enter grid size: ");
	    size = scanner.nextInt();
	    
	    zigzagpattern.displayGrid(size);
	    scanner.close();
	}
	
}
