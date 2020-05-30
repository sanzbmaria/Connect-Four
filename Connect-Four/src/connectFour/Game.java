package connectFour;

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

	//Set variables for the columns m
	//might delete as I havent used them
	private final int COLUM0 = 0;
	private final int COLUM1 = 1;
	private final int COLUM2 = 2; 
	private final int COLUM3 = 3; 
	private final int COLUM4 = 4; 
	private final int COLUM5 = 5; 
	private final int COLUM6 = 6; 
	
	
	//Set the variables for players
	private final int EMPTY = 0;
	private final int PLAYER1 = 1;
	private final int PLAYER2 = 2;
	
	int turn; //stores who's turn is it 

	/*The constructor will get called and will initialize the variables needed */
	public Game() {
		this.board = new int[HEIGHT][WIDTH];
		this.boardNumberDisk = new int[WIDTH];
		init(); // Initializes the game
	}

	//This method initializes all the graphics of the game and the boards to 0
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
	}

	
	
	/*This will update the display every time the players make a move*/
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
			if(this.turn == PLAYER1) {
				this.turn = PLAYER2;
			}
			else {
				this.turn = PLAYER1;
			}
			
		}
		//If the column is full
		else {
			//Display an error and prompt the same user to retry
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
		//there is an error here
		this.boardNumberDisk[col]+= 1; //update the value
		return ret;
	}

	/*Drawing the stuff in the game GUI*/
	private void render(){
		print();
	}

	/*This method will be called after the column buttons are pressed for now its called in lauch*/
	public void run(int col) {
		//safety check

		if(!winner) {
			update(col);
			render(); //not yet implemented

		}
		else {
			if(this.turn == PLAYER1) {
				this.turn = PLAYER2;
			}
			else {
				this.turn = PLAYER1;
			}
			stop(turn);
		}
	}

	//this class will check the array to see if there are 4 disks in a line 
	public void winner(int turn) {
		/*Check if there are 4 of the same player:
		  1. Horizontally
		  2. Vertically
		  3. Diagonally
		 */
		//if there are then 
		this.winner = true;
		//stop the game
		stop(turn);

		//else do nothing
	}

	//Method called when someone wins 
	public void stop(int turn)  {
		//display the winner 
		//end the game
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

	//temp function
	public boolean isWinner(){
		return winner;
	}
	
}


