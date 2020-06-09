package connectFour;

public class InvalidColumnException extends Exception {
    public InvalidColumnException(){
        super("This column is full");
    }
}
