package connectFour;

import java.util.Scanner;

public class Launcher {

	public static Game game;
	
	public static void main(String[] args) {
		//Author @Maria
		//Version 05/30/2020
		game = new Game();//Starts the game
		int angel = 21;
		//Temporary loop to play the game until we make the GUI

		Scanner scan = new Scanner(System.in);
		int temp;
		game.print();
		while(!game.isWinner() ){

			System.out.println("In which column do you want to place the disk? ");
			temp = scan.nextInt();
			game.run(temp);
		}

	}

	

	public Game getGame() {
		return game; 
	}
	

}

