package com.cognixia.jump.blackjack;

import java.util.Scanner;

public class PlayBlackjack {

	public static int playBlackjack(Scanner userInput) {

		int playerWon = 0;
		String response = userInput.nextLine();
		Deck playingDeck = new Deck();
		Deck playerHand = new Deck();
		Deck dealerHand = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		playerHand.draw(playingDeck);
		playerHand.draw(playingDeck);
		dealerHand.draw(playingDeck);
		dealerHand.draw(playingDeck);
		System.out.println("-----------------------------------------------------");
		System.out.println("Your hand, with a value of " + playerHand.cardsValue() + ":");
		System.out.println(playerHand.toString());
		System.out.println("\nThe Dealer's hand:\n\n" + dealerHand.getCard(0).toString() + " and one facedown.");
		System.out.println("-----------------------------------------------------");

		if (playerHand.cardsValue() < 21 && dealerHand.cardsValue() == 21) {
			System.out.println("The Dealer reveals his hand. Value of: " + dealerHand.cardsValue());
			System.out.println(dealerHand.toString());
			System.out.println("The Dealer was dealt Blacjack.");
			System.out.println("-----------------------------------------------------");
			playerWon = 0;
		} else if (playerHand.cardsValue() == 21 && dealerHand.cardsValue() < 21) {
			System.out.println("You were dealt Blackjack.");
			System.out.println("The Dealer reveals his hand. Value of: " + dealerHand.cardsValue());
			System.out.println(dealerHand.toString());
			System.out.println("-----------------------------------------------------");
			playerWon = 3;
		} else if (playerHand.cardsValue() == 21 && dealerHand.cardsValue() == 21) {
			System.out.println("You were dealt Blackjack.");
			System.out.println("The Dealer reveals his hand. Value of: " + dealerHand.cardsValue());
			System.out.println(dealerHand.toString());
			System.out.println("The Dealer was also dealt Blackjack");
			System.out.println("-----------------------------------------------------");
			playerWon = 2;
		}
		while (playerHand.cardsValue() < 21) {
			if (!response.equalsIgnoreCase("H") || !response.equalsIgnoreCase("S")) {
				System.out.println("Would you like to Hit (H) or Stand (S)?");
				response = userInput.nextLine();
			}
			if (response.equalsIgnoreCase("H")) {
				playerHand.draw(playingDeck);
				System.out.println("-----------------------------------------------------");
				System.out.println("You drew a: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
				System.out.println("Your hand is valued at: " + playerHand.cardsValue());
				System.out.println("Your new hand:\n " + playerHand.toString());
				System.out.println("-----------------------------------------------------");

				if (playerHand.cardsValue() > 21) {
					System.out.println("You Busted Buster. Hand value is: " + playerHand.cardsValue());
					playerWon = 0;
					break;
				}
				if (playerHand.cardsValue() == 21) {
					System.out.println("You have Blackjack!");
					System.out.println("-----------------------------------------------------");
					while (dealerHand.cardsValue() < 17) {
						System.out.println("-----------------------------------------------------");
						System.out.println("The Dealer hits");
						dealerHand.draw(playingDeck);
						System.out.println(
								"The Dealer drew a " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
					}
					System.out.println("The Dealer reveals his hand value of: " + dealerHand.cardsValue());
					System.out.println(dealerHand.toString());
					if (dealerHand.cardsValue() == 21) {
						System.out.println("The Dealer hit Blackjack! It's a tie! But,...");
						System.out.println("-----------------------------------------------------");
						playerWon = 0;
						break;
					} else if (dealerHand.cardsValue() > 21) {
						System.out.println("The Dealer busted!");
						System.out.println("-----------------------------------------------------");
						playerWon = 1;
						break;
					} else {
						
						System.out.println("The Dealer came up short.");
						System.out.println("-----------------------------------------------------");
						playerWon = 1;
						break;
					}
				}
			}
			if (response.equalsIgnoreCase("S")) {
				while (dealerHand.cardsValue() < 17) {
					System.out.println("-----------------------------------------------------");
					System.out.println("The Dealer hits");
					dealerHand.draw(playingDeck);
					System.out.println("The Dealer drew a " + dealerHand.getCard(dealerHand.deckSize() - 1).toString() + "\n");
				}
				System.out.println("The Dealer reveals his hand value of: " + dealerHand.cardsValue());
				System.out.println(dealerHand.toString());
				if (dealerHand.cardsValue() > 21) {
					System.out.println("\nThe Dealer busted!");
					System.out.println("-----------------------------------------------------");
					playerWon = 1;
					break;
				} else if (dealerHand.cardsValue() == 21) {
					System.out.println("\nThe Dealer hit Blackjack!");
					System.out.println("-----------------------------------------------------");
					playerWon = 0;
					break;
				} else if (dealerHand.cardsValue() > playerHand.cardsValue()) {
					playerWon = 0;
					break;
				} else if (dealerHand.cardsValue() < playerHand.cardsValue()) {
					playerWon = 1;
					break;
				} else if (dealerHand.cardsValue() == playerHand.cardsValue()) {
					playerWon = 0;
					break;
				}
			}
		}
		return playerWon;
	}
}
