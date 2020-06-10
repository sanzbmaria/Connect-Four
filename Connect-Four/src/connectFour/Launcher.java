package connectFour;


public class Launcher {

	public static Game game;

	public static void main(String[] args) {
		//Author @Maria
		//Version 05/30/2020


		Game game = new Game(900, 800, "Connect Four");
		//Display display = new Display("Connect Four",300 , 300, game);
		//game.SetDisplay(display);

		Display display = new Display(600,681, "Connect Four", game);
		game.SetDisplay(display);

	}


}

