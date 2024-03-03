public class BankAccount {
    private static double interestRate = 0.05;
    private static double totalBalance = 0;
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        totalBalance += initialBalance;
    }

    public boolean deposit(double amount) {
        balance += amount;
        totalBalance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            totalBalance -= amount;
            return true;
        }
        return false;
    }

    public void calculateInterest() {
        double interest = balance * interestRate;
        deposit(interest);
    }

    public double getBalance() {
        return balance;
    }

    public static double getTotalBalance() {
        return totalBalance;
    }

    public static double getInterestRate() {
        return interestRate;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    @Override
    public String toString() {
        return "Your current balance: $" + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BankAccount account2 = (BankAccount) obj;
        return Double.compare(this.balance, account2.balance) == 0;
    }
}
