package com.cognixia.jump.blackjack;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		int money = 100;
		int bet = 0;
		boolean simulating = true;
		System.out.println("STARTING BLACKJACK SIMULATION");
		System.out.println("Welcome to Damon's Blackjack for Beginners.");
		System.out.println("You have $" + money + ".");
		Scanner intScanner = new Scanner(System.in);
		while (simulating) {
			if (money == 0) {
				System.out.println("I'm sorry, but you can't afford to play this game.");
				System.out.println("END BLACKJACK SIMULATION");
				simulating = false;
				break;
			}
			System.out.println("How much would you like to bet? 1-" + money + ", or 0 to exit game.");
			if (intScanner.hasNextInt()) {
				bet = intScanner.nextInt();
			}
			if (bet == 0) {
				System.out.println("Thank you for playing.");
				System.out.println("You finished with $" + money + ".");
				System.out.println("END BLACKJACK SIMULATION");
				simulating = false;
			} else if (bet < 0 || bet > money) {
				// Restart loop until valid bet is made.
			} else if (bet > 0 && bet <= money) {
				switch (PlayBlackjack.playBlackjack(intScanner)) {
				case 0:
					money -= bet;
					System.out.println("\nYou lost. You now have $" + money + ".");
					System.out.println("-----------------------------------------------------");
					break;
				case 1:
					money += bet;
					System.out.println("\nYou won! You now have $" + money + ".");
					System.out.println("-----------------------------------------------------");
					break;
				case 2:
					System.out.println("\nIt's a PUSH. You get your bet back. You have $" + money + ".");
					System.out.println("-----------------------------------------------------");
					break;
				case 3:
					money += (bet * 2);
					System.out.println("\nYou WON! You get back DOUBLE your bet. You now have $" + money + ".");
					System.out.println("-----------------------------------------------------");
					break;
				}
			}
		}
		intScanner.close();
	}
}
