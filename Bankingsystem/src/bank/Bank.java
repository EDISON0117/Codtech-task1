package bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    Map<String, bank.Account> accounts = new HashMap<>();
    int count = 1000;

    Account create(String name, String type, double amount, String pin) {
        if (amount < 500) {
            System.out.println("Minimum deposit is 500.");
            return null;
        }
        String num = "ACC" + (++count);
        Account a = new Account(num, name, type, amount, pin);
        accounts.put(num, a);
        return a;
    }

    Account get(String num) {
        return accounts.get(num.toUpperCase());
    }

    Account login(String num, String pin) {
        Account a = get(num);
        if (a == null)          { System.out.println("Account not found."); return null; }
        if (!a.active)          { System.out.println("Account is closed."); return null; }
        if (!a.pin.equals(pin)) { System.out.println("Wrong PIN.");         return null; }
        return a;
    }

    void deposit(Account a, double amount) {
        if (amount <= 0) { System.out.println("Invalid amount."); return; }
        a.balance += amount;
        a.history.add("Deposit: +" + amount + " | Balance: " + a.balance);
        System.out.println("Deposited. New balance: " + a.balance);
    }

    void withdraw(Account a, double amount) {
        if (amount <= 0)              { System.out.println("Invalid amount.");        return; }
        if (a.balance - amount < 500) { System.out.println("Insufficient balance."); return; }
        a.balance -= amount;
        a.history.add("Withdrawal: -" + amount + " | Balance: " + a.balance);
        System.out.println("Withdrawn. New balance: " + a.balance);
    }

    void transfer(Account from, String toNum, double amount) {
        Account to = get(toNum);
        if (to == null)                  { System.out.println("Target account not found."); return; }
        if (!to.active)                  { System.out.println("Target account is closed."); return; }
        if (from.balance - amount < 500) { System.out.println("Insufficient balance.");     return; }
        from.balance -= amount;
        to.balance   += amount;
        from.history.add("Transfer to "   + toNum       + ": -" + amount + " | Balance: " + from.balance);
        to.history.add(  "Transfer from " + from.number + ": +" + amount + " | Balance: " + to.balance);
        System.out.println("Transfer successful.");
    }

    void showStatement(Account a) {
        System.out.println("\n--- Statement: " + a.number + " ---");
        for (String h : a.history) System.out.println("  " + h);
        System.out.println("Current Balance: " + a.balance);
    }

    void changePin(Account a, String oldPin, String newPin) {
        if (!a.pin.equals(oldPin)) { System.out.println("Wrong current PIN.");        return; }
        if (newPin.length() < 4)   { System.out.println("PIN must be min 4 digits."); return; }
        a.pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    void close(Account a) {
        a.active = false;
        System.out.println("Account " + a.number + " closed.");
    }

    void showAll() {
        if (accounts.isEmpty()) { System.out.println("No accounts."); return; }
        System.out.println("\n--- All Accounts ---");
        for (Account a : accounts.values()) {
            System.out.println(
                    a.number + " | " + a.name + " | " + a.type +
                            " | Balance: " + a.balance +
                            " | " + (a.active ? "Active" : "Closed")
            );
        }
    }
}