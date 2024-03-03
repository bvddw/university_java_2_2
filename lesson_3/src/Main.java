public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1500);
        System.out.println("Total balance across all accounts: $" + BankAccount.getTotalBalance());
        if (account1.deposit(500)) {
            System.out.println("Successfully deposit $500 on your first account.");
        } else {
            System.out.println("Problem with deposit $500 on your first account.");
        }
        if (account1.withdraw(500)) {
            System.out.println("Successfully withdraw $500 on your first account.");
        } else {
            System.out.println("Not enough money on your first account.");
        }
        if (account1.withdraw(1500)) {
            System.out.println("Successfully withdraw $1500 on your first account.");
        } else {
            System.out.println("Not enough money on your first account.");
        }
        if (account2.deposit(500)) {
            System.out.println("Successfully deposit $500 on your second account.");
        } else {
            System.out.println("Problem with deposit $500 on your second account.");
        }
        if (account2.withdraw(1000)) {
            System.out.println("Successfully withdraw $1000 on your second account.");
        } else {
            System.out.println("Not enough money on your second account.");
        }
        account1.calculateInterest();
        System.out.println(account1);
        BankAccount.setInterestRate(0.07);
        account2.calculateInterest();
        System.out.println(account2);
        System.out.println("Total balance across all accounts: $" + BankAccount.getTotalBalance());
    }
}