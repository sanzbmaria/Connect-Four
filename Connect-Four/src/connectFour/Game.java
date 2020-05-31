package connectFour;

import static java.lang.System.exit;

//This is the main part of the game
public class Game{
	
	private Display display;
	
	private int[][] board; //This will be the board that we will use to store the state 
	private int[] boardNumberDisk; //this will store the number of disk on each column to check if it is full 

	public int column;
	public final int WIDTH = 7;
	public final int HEIGHT = 6;

	boolean winner = false;
	boolean newGame = false; //This should be updated if the new game  button is pressed

	
	//variables for players
	private final int EMPTY = 0;
	private final int PLAYER1 = 1;
	private final int PLAYER2 = 2;
	
	int turn; //stores who's turn is it
	int lastTurn;

	/* THIS ARE THE MAIN METHODS BEING CALLED */


	/*CALLED BY LAUNCHER : it will initialize the variables needed */
	public Game() {
		this.board = new int[HEIGHT][WIDTH];
		this.boardNumberDisk = new int[WIDTH];
		init(); // Initializes the game
	}

	/*CALLED BY GAME: initializes all the graphics of the game and the boards to 0 */
	private void init(){
		//This loop initializes all positions to EMPTY;
		for(int i = 0; i < HEIGHT; i++ ) {
			for(int j = 0; j < WIDTH; j++) {
				this.board[i][j] = EMPTY;
			}
		}
	
		//After making sure everything is EMPTY we can create the Display
		//this.display = new Display();

		//Initialize the turn to player 1
		this.turn = PLAYER1;
		this.lastTurn = -1; //As it is the first time the game runs there is no last turn player
	}

	/*TEMP CALLED BY LAUNCHER (SHOULD BE CALLED BY COLUMN BUTTONS) : Runs the game*/
	public void run(int col) {
		//Should we double check no one has won yet?

		if(!winner) {
			update(col);
			render(); //not yet implemented
		}
		else {
			//Then the winner was the past player
			stop(lastTurn);
		}
	}
	
	/*CALLED BY RUN: This will update the values every time the players make a move*/
	private void update(int col){

		/*It would be nice to check that is a valid input but because on the final version it will
		run with buttons there is no need just be careful while testing */

		//this wont be needed when playing with the GUI
		this.column = col -1; //The user will input starting at idx 1 but the arr starts at idx 0

		int tempRow;
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
		//If the column is full
		else {
			//Display an error and prompt the same user to retry
		}		
	}


	//this class will check the array to see if there are 4 disks in a line 
	public void winner(int turn) {
		//IF THERE ARE WINNERS DONT FORGET TO SET THIS
		//this.winner = true;
		//stop the game
		//stop(turn);

/*		*//*HORIZONTAL -> if *//*
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {

			}
		}*/


		//VERTICAL
		/*The vertical checking works by first cheking the 1d array where the number of disk each column has is saved
		* if the number of disks is less than 3 then we know there is no possibility of anyone winning vertically on that
		* column so it is skipped, IF the number is greather than 3 then we will check the last position with the current
		* if they are the same we will add to the counter, if they are different the counter gets reset. When the counter \
		* gets to 4 we know the player has won! */
		int sum= 0;
		int temp;
		for (int i = 0; i < WIDTH ; i++) {
			if( this.boardNumberDisk[i] > 3){
				temp = board[HEIGHT-1][i];
				for (int j = HEIGHT-1; j >= 0  ; j--) {
					if(temp == board[j][i]){
						sum++;
						temp = board[j][i];
					}
					else{
						sum = 0;
					}
					if (sum == 4)
						stop(turn);
				}
			}
		}



		// backslash diagonal

		// forward slash

		//else do nothing
	}


	//Method called when someone wins 
	public void stop(int turn)  {
		//display the winner 
		//end the game
		System.out.println("Player " + turn + "WON the game!");
		exit(1);
	}

	/*CALLED BY RUN: Drawing the stuff in the game GUI*/
	private void render(){
		print();
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
		return this.boardNumberDisk[col] == HEIGHT;
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
	
}


