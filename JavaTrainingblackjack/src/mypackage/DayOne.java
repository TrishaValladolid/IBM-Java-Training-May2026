package mypackage;

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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DayOne dayOne = new DayOne();
		System.out.println(dayOne.blackjack(1, 2)); // 2
		System.out.println(dayOne.blackjack(21, 22)); // 21
		System.out.println(dayOne.blackjack(22, 22)); // 0
		System.out.println(dayOne.blackjack(2, 10)); // 10

	}
}
