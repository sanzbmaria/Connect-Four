package connectFour;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static java.lang.System.exit;

//Same as Game but with threads (Im trying to implement it)
public class Game implements Runnable{

	private int width;
	private int height;
	private String title;
	private Thread thread; //For threads

	private Display display;

	private int[][] board; //This will be the board that we will use to store the state
	private int[] boardNumberDisk; //this will store the number of disk on each column to check if it is full

	public int column;
	public final int WIDTH = 7;
	public final int HEIGHT = 6;

	boolean winner = false;
	boolean newGame = false; //This should be updated if the new game  button is pressed
	boolean running = false;

	//variables for players
	private final int EMPTY = 0;
	private final int PLAYER1 = 1;
	private final int PLAYER2 = 2;

	int turn; //stores who's turn is it
	int lastTurn;

	private BufferStrategy bs;
	private Graphics g;

	/* THIS ARE THE MAIN METHODS BEING CALLED */


	/*CALLED BY LAUNCHER : it will initialize the variables needed */
	public Game(int width, int height, String title) {
		this.board = new int[HEIGHT][WIDTH];
		this.boardNumberDisk = new int[WIDTH];
		this.width = width;
		this.height = height;
		this.title = title;


			//This loop initializes all positions to EMPTY;
		for(int i = 0; i < HEIGHT; i++ ) {
			for(int j = 0; j < WIDTH; j++) {
				this.board[i][j] = EMPTY;
			}
		}


		//Initialize the turn to player 1
		winner = false;
		this.turn = PLAYER1;
		this.lastTurn = -1; //As it is the first time the game runs there is no last turn player
	}


	public void restart(){
		winner = false;
		this.board = new int[HEIGHT][WIDTH];
		this.boardNumberDisk = new int[WIDTH];
		this.width = width;
		this.height = height;
		this.title = title;


		//This loop initializes all positions to EMPTY;
		for(int i = 0; i < HEIGHT; i++ ) {
			for(int j = 0; j < WIDTH; j++) {
				this.board[i][j] = EMPTY;
			}
		}


		//Initialize the turn to player 1
		this.turn = PLAYER1;
		this.lastTurn = -1; //As it is the first time the game runs there is no last turn player
	}

	/*CALLED BY RUN: This will update the values every time the players make a move*/
	public void update(int col){

		/*It would be nice to check that is a valid input but because on the final version it will
		run with buttons there is no need just be careful while testing */

		//this wont be needed when playing with the GUI
		this.column = col; //The user will input starting at idx 1 but the arr starts at idx 0

		int tempRow;
		try{
			if(isFull(column) == true)
				throw new InvalidColumnException();

		}
		catch (InvalidColumnException e){
			//@angel Popup up message to try again in another column
			//temp
			System.out.println("Try again");
			return;
		}



		//Check if the column selected is not full
		if(!isFull(column)) {
			//IF not ful -> Update the board with the respective player
			tempRow = locationToPlace(column) -1 ; //get the next empty row to place the disk
			this.board[tempRow][column] = this.turn; //update that row with that player's number
			//check if winner there is a winner
			winner(this.turn); //if there is a winner then winner function will stop the game
			//If the game has not stopped then update the turn to the next player
			changePlayers(this.turn);
		}


		//Check if all columns are full
		try{
			if(noWinner())
				throw new NoWinnerException();

		}
		catch (NoWinnerException e){
			//@angel Popup up message regarding draw
			System.out.println("Its a draw!");
			exit(1);
		}
	}



	/*CALLED BY RUN: Drawing the stuff in the game GUI*/
	private void render (){
		print();
		display.paintingUpdate();
	}
	//this class will check the array to see if there are 4 disks in a line
	public void winner(int turn) {
		//IF THERE ARE WINNERS DONT FORGET TO SET THIS
		//this.winner = true;
		//stop the game
		//stop(turn);

		int sum= 0;
		int temp;

		//HORIZONTAL
		temp = 0;
		sum = 0;
		for (int i = 0; i < WIDTH-1 ; i++) {
			temp = board[i][0];
			for (int j = 1 ; j < HEIGHT ; j++) {
				if(temp == board[i][j]){
					if(temp != 0){
						sum++;
					}
				}
				else{
					sum = 0;
				}
				temp = board[i][j];
				if (sum == 3)
					endGame(turn);
			}
		}

		//This only needed if a column is < 3
		if(boardNumberDisk[0] > 3 ||  boardNumberDisk[1] > 3 || boardNumberDisk[2] > 3 || boardNumberDisk[3] > 3
				|| boardNumberDisk[4] > 3 || boardNumberDisk[5] > 3 || boardNumberDisk[6] > 3){

			//VERTICAL
			/*The vertical checking works by first cheking the 1d array where the number of disk each column has is saved
			 * if the number of disks is less than 3 then we know there is no possibility of anyone winning vertically on that
			 * column so it is skipped, IF the number is greather than 3 then we will check the last position with the current
			 * if they are the same we will add to the counter, if they are different the counter gets reset. When the counter \
			 * gets to 4 we know the player has won! */

			for (int i = 0; i < WIDTH ; i++) {
				if( this.boardNumberDisk[i] > 3){
					temp = board[HEIGHT-1][i];
					for (int j = HEIGHT-1; j >= 0  ; j--) {
						if(temp == board[j][i]){
							sum++;
						}
						else{
							sum = 0;
						}
						temp = board[j][i];
						if (sum == 4)
							endGame(turn);
					}
				}
			}

			//DIAGONAL 1
			for (int i = 0; i < HEIGHT; i++) {
				for (int j = 0; j < WIDTH; j++) {
					if (board[i][j] != 0 && (j - 3 > 0 ) && (i + 3 < HEIGHT)) {
						sum = board[i][j] + board[i + 1][j - 1] + board[i + 2][j - 2] + board[i + 3][j - 3];
						if ((sum == PLAYER1 * 4) && (board[i + 1][j - 1] != 0) && (board[i + 2][j - 2] != 0) && (board[i + 3][j - 3] != 0)) {
							endGame(PLAYER1);
						}
						if ((sum == PLAYER2 * 4) && (board[i + 1][j - 1] != 0) && (board[i + 2][j - 2] != 0) && (board[i + 3][j - 3] != 0)) {
							endGame(PLAYER2);
						}
					}
				}
			}

			//DIAGONAL 2
			for (int i = 0; i < HEIGHT; i++) {
				for (int j = 0; j < WIDTH; j++) {
					if (board[i][j] != 0 && (j + 3 < WIDTH) && (i + 3 < HEIGHT)) {
						sum = board[i][j] + board[i + 1][j + 1] + board[i + 2][j + 2] + board[i + 3][j + 3];
						if ((sum == PLAYER1 * 4) && (board[i + 1][j + 1] != 0) && (board[i + 2][j + 2] != 0) && (board[i + 3][j + 3] != 0)) {
							endGame(PLAYER1);
						}
						if ((sum == PLAYER2 * 4) && (board[i + 1][j + 1] != 0) && (board[i + 2][j + 2] != 0) && (board[i + 3][j + 3] != 0)) {
							endGame(PLAYER2);
						}
					}
				}
			}
		}

	}



	//Method called when someone wins
	public void endGame(int turn)  {
		System.out.println("Player " + turn + " WON the game!");
		print();
		render();

		winner = true;
		stop();
	}

	/* HELPER METHODS*/
	private void changePlayers(int currentTurn){
		if(this.turn == PLAYER1) {
			this.turn = PLAYER2;
			this.lastTurn = PLAYER1;
		}
		else {
			this.turn = PLAYER1;
			this.lastTurn = PLAYER2;
		}
	}

	//This function checks if the column selected by the user is full or not
	private  boolean isFull(int col) {
		if (col > 0)
			return this.boardNumberDisk[col] == HEIGHT;
		else return false;
	}

	//This function returns the next empty row to place the disk
	private int locationToPlace(int col) {
		//Arrays start [0][0] at the right corner but we start filling from the bottom up therefore to find the next location
		int ret = this.HEIGHT - this.boardNumberDisk[col];
		this.boardNumberDisk[col]+= 1; //update the value
		return ret;
	}


	//Temp print until we make the GUI
	public void print(){

		for(int i = 0; i < WIDTH; i++){
			System.out.print(i+1 + " " );
		}
		System.out.println();
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	//temp function used by launcher
	public boolean isWinner(){
		return winner;
	}

	private boolean noWinner(){
		for (int i = 0; i < WIDTH ; i++) {
			if(boardNumberDisk[i] < HEIGHT)
				return false;
		}
		return true;
	}

	public void SetDisplay(Display display) {
		this.display = display;
	}




	public void runNT(int col){
		update(col);
		render();
	}

	@Override
	public synchronized void run(){

	}

	public synchronized void start() {
		if(running )
			return;
		running = true;
		thread = new Thread(thread);
		thread.start();
	}

	public int location(int r, int c) {
		return board[r][c];
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}
