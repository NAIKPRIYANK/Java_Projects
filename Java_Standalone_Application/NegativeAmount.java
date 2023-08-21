package bankaccountapplication;

public class NegativeAmount extends Exception {

	public NegativeAmount() {
        super("Invalid amount --> amount cannot be negative");
    }
}
