package concurrency;

public class SecureBankAccount {
    private double balance;

    public SecureBankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized double getBalance() {
        return balance;
    }
}