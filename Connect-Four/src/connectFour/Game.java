package connectFour;

//This is the main part of the game
public class Game{
	
	private Display display;
	
	private int[][] board; //This will be the board that we will use to store the state 
	private int[] boardNumberDisk; //this will store the number of disk on each column to check if it is full 
	
	public static int width = 7;
	public static  int height = 6;
	public String  title;
	private boolean running = false;
	
	static boolean winner = false; 
	boolean newGame = false; //This should be updated if the new game is pressed 
	
	//Set variables for the columns 
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
		this.board = new int[width][height];
		this.boardNumberDisk = new int[width];
		init(); // Initializes the game
	}

	//This method initializes all the graphics of the game
	private void init(){
		//This loop initializes all positions to EMPTY;
		for(int i = 0; i < width; i++ ) {
			for(int j = 0; j < height; j++) {
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
		/*1. Check who's turn is it
		  2. IF (column selected is not full)
	             check boardNumberDisk[col] for current location 
	             	 update boardNumberDisk[col] += 1;
	                 this will return row-1 //for now lets call it row
	             update board[col][row]  
	      3. check the current board to see if there is a winner 
	      			IF winner then display the winner and end the game
	      4. update the turn 
	      			IF(turn == PLAYER1) 
	      				turn == PLAYER2
      				else 
      					turn == PLAYER1  */
		 
		if(!isFull(col)) {
			//Update the board with the respective player
			this.board[col][locationToPlace(col)] = this.turn; 
			//check if winner 
			
			//Update the turn to the next player
			if(this.turn == PLAYER1) {
				this.turn = PLAYER2;
			}
			else {
				this.turn = PLAYER1;
			}
			
		}
		else {
			//Display an error and prompt the same user to retry
		}		
	}
	
	private  boolean isFull(int col) {
		if(this.boardNumberDisk[col] == height) {
			return true;
		}
		return false;
	}
	
	private int locationToPlace(int col) {
		//Arrays start [0][0] at the right corner but we start filling from the bottom up therefore to find the next location
		int ret = this.height - this.boardNumberDisk[col];
		this.boardNumberDisk[col]+= 1; //update the value
		return ret;
	}

	/*Drawing the stuff in the game*/
	private static void render(){
		
	}

	/*This method will be called after the column buttons are pressed*/
	public void run(int col) {
		//safety check
		System.out.println("Clicked col" + col);
		
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
		
		//if there are then 
		this.winner = true;
		//stop the game
		stop(turn);
	}

	//Method called when someone wins 
	public void stop(int turn)  {
		//display the winner 
		//end the game
	}
	
	
}


