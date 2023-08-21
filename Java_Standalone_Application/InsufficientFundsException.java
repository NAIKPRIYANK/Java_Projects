package bankaccountapplication;
public class InsufficientFundsException extends Exception{

	public InsufficientFundsException() {
        super("Insufficient funds: minimum balance requirement not met");
    }
}
