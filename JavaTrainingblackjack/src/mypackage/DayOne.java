package mypackage;
import java.util.Scanner;

public class DayOne {

	public int blackjack(int a, int b) {
		
		if (a > 21 && b > 21) {
			return 0;
		} else if  (a > 21) {
			return b;
		} else if (b > 21) {
			return a;
		} else {
			return Math.max(a,b);
		}
	}
	
	public void dayOfTheWeek(int day) {
		/*switch(day) {
			case 1: 
				System.out.println("Monday");
				break;
			case 2: 
				System.out.println("TUesday");
				break;
			case 3: 
				System.out.println("Wednesday");
				break;
			case 4: 
				System.out.println("Thursday");
				break;
			case 5: 
				System.out.println("Friday");
				break;
			case 6: 
				System.out.println("Saturday");
				break;
			case 7: 
				System.out.println("Sunday");
				break;
		}*/
		
		String result = switch (day) {
			case 1 -> "Monday";
			case 2 -> "Tuesday";
			case 3 -> "Wednesday";
			case 4 -> "Thursday";
			case 5 -> "Friday";
			case 6 -> "Saturday";
			case 7 -> "Sunday";
			default -> "Error";			
		};
		System.out.println(result);
	}
	
	public void displayPyramid(int size) {
		int i = 1;
		int j = 1;
		
		/*for ( i = 1; i <= size; i++) {        // outer loop - controls rows
	            for ( j = 1; j <= i; j++) {       // inner loop - controls numbers
	                System.out.print(j + " ");
	            }
	            System.out.println();
	        }*/
		 
		  do {
            for (j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
            i++;
        } while (i <= size);
        
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		DayOne dayOne = new DayOne();
		int num = 0;
		
		/* BLACKJACK
		System.out.println(dayOne.blackjack(1, 2)); // 2
		System.out.println(dayOne.blackjack(21, 22)); // 21
		System.out.println(dayOne.blackjack(22, 22)); // 0
		System.out.println(dayOne.blackjack(2, 10)); // 10
		System.out.println();
		*/
		
		/* SWITCH & PATTERN MATCHING
		while (true) {
			System.out.println("Enter a number from 1-7: ");
			num = sc.nextInt();
			if (num >= 1 && num <=7 ) break;
			else {
				System.out.println("Invalid day number.");
			}
		}
		dayOne.dayOfTheWeek(num);
		*/
		
		// LOOPS
		while (true) {
			System.out.println("Enter a number from 1-20: ");
			num = sc.nextInt();
			if (num >= 1 && num <=20 ) break;
			else {
				System.out.println("Invalid number.");
			}
		}
		dayOne.displayPyramid(num);
		
		
		sc.close();
	}
}
