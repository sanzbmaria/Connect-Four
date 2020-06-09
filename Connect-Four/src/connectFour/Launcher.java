package connectFour;

import java.util.Scanner;

public class Launcher {

	public static Game game;
	
	public static void main(String[] args) {
		//Author @Maria
		//Version 05/30/2020


		int temp;

		Game game = new Game(300, 300, "Connect Four");
		game.start();
		game.print();


	}



}

