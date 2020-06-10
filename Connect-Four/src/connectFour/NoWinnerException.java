package connectFour;

public class NoWinnerException extends Exception {
    public NoWinnerException() {
        super("Its a draw! ");
    }
}
