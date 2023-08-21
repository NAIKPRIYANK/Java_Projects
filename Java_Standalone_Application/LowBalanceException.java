package bankaccountapplication;

public class LowBalanceException extends Exception {

	public LowBalanceException() {
        super("Low balance: balance is below the minimum requirement");
    }
}
