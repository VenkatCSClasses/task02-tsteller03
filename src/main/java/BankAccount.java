
public class BankAccount {

    private String email;
    private double balance;

    public BankAccount(String email, double balance) {
        if (!isEmailValid(email) || !isAmountValid(balance)) {
            throw new IllegalArgumentException("Invalid email or balance");
        }

        this.email = email;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public void deposit(double amount) {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid deposit amount");
        }

        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        balance -= amount;
    }

    public void transfer(BankAccount account, double amount)
            throws InsufficientFundsException {

        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid transfer amount");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        this.balance -= amount;
        account.balance += amount;
    }

    public static boolean isAmountValid(double amount) {
        if (amount < 0) {
            return false;
        }

        // round to two decimal places
        double rounded = Math.round(amount * 100.0) / 100.0;

        // allow small floating-point tolerance
        return Math.abs(amount - rounded) < 1e-9;
    }

    public static boolean isEmailValid(String email) {

        if (email == null || email.length() == 0) {
            return false;
        }

        int atCount = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                atCount++;
            }
        }

        if (atCount != 1) {
            return false;
        }

        int atIndex = email.indexOf('@');

        String prefix = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        if (prefix.length() == 0 || domain.length() == 0) {
            return false;
        }

        if (!validPart(prefix) || !validPart(domain)) {
            return false;
        }

        int lastDot = domain.lastIndexOf('.');
        if (lastDot <= 0 || lastDot == domain.length() - 1) {
            return false;
        }

        String suffix = domain.substring(lastDot + 1);
        if (suffix.length() < 2) {
            return false;
        }

        return true;
    }

    private static boolean validPart(String part) {

        char previous = '\0';

        for (int i = 0; i < part.length(); i++) {
            char c = part.charAt(i);

            boolean isLetterOrDigit =
                    (c >= 'a' && c <= 'z') ||
                    (c >= 'A' && c <= 'Z') ||
                    (c >= '0' && c <= '9');

            boolean isAllowedSpecial =
                    (c == '.') || (c == '-') || (c == '_');

            if (!(isLetterOrDigit || isAllowedSpecial)) {
                return false;
            }

            // cannot start or end with special characters
            if ((i == 0 || i == part.length() - 1) && isAllowedSpecial) {
                return false;
            }

            // cannot repeat special characters
            if (isAllowedSpecial && c == previous) {
                return false;
            }

            previous = c;
        }

        return true;
    }
}
