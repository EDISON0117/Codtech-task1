package bank;

import java.util.Scanner;

public class Main {
    static Scanner sc   = new Scanner(System.in);
    static bank.Bank bank = new Bank();

    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("      JAVA BANKING SYSTEM     ");
        System.out.println("==============================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. View All Accounts (Admin)");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            String ch = sc.nextLine();

            if      (ch.equals("1")) createAccount();
            else if (ch.equals("2")) userMenu();
            else if (ch.equals("3")) bank.showAll();
            else if (ch.equals("4")) { System.out.println("Goodbye!"); break; }
            else                       System.out.println("Invalid choice.");
        }
    }

    static void createAccount() {
        System.out.println("\n--- CREATE ACCOUNT ---");

        System.out.print("Full Name: ");
        String name = sc.nextLine();

        System.out.println("Account Type:");
        System.out.println("  1. Savings");
        System.out.println("  2. Checking");
        System.out.println("  3. Fixed Deposit");
        System.out.print("Choose: ");
        String t    = sc.nextLine();
        String type = t.equals("1") ? "Savings" : t.equals("2") ? "Checking" : "Fixed Deposit";

        System.out.print("Initial Deposit (min 500): ");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.print("Set PIN (min 4 digits): ");
        String pin = sc.nextLine();

        Account a = bank.create(name, type, amount, pin);
        if (a != null) {
            System.out.println("Account created!");
            System.out.println("Your Account Number: " + a.number);
        }
    }

    static void userMenu() {
        System.out.print("\nAccount Number: ");
        String num = sc.nextLine();
        System.out.print("PIN: ");
        String pin = sc.nextLine();

        Account a = bank.login(num, pin);
        if (a == null) return;

        System.out.println("Welcome, " + a.name + "!");

        while (true) {
            System.out.println("\n--- ACCOUNT MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. Change PIN");
            System.out.println("7. Close Account");
            System.out.println("8. Logout");
            System.out.print("Choose: ");
            String ch = sc.nextLine();

            if (ch.equals("1")) {
                System.out.println("Account: " + a.number);
                System.out.println("Name   : " + a.name);
                System.out.println("Type   : " + a.type);
                System.out.println("Balance: " + a.balance);

            } else if (ch.equals("2")) {
                System.out.print("Deposit Amount: ");
                double amt = Double.parseDouble(sc.nextLine());
                bank.deposit(a, amt);

            } else if (ch.equals("3")) {
                System.out.print("Withdraw Amount: ");
                double amt = Double.parseDouble(sc.nextLine());
                bank.withdraw(a, amt);

            } else if (ch.equals("4")) {
                System.out.print("Transfer to Account Number: ");
                String to  = sc.nextLine();
                System.out.print("Amount: ");
                double amt = Double.parseDouble(sc.nextLine());
                bank.transfer(a, to, amt);

            } else if (ch.equals("5")) {
                bank.showStatement(a);

            } else if (ch.equals("6")) {
                System.out.print("Current PIN: ");
                String old = sc.nextLine();
                System.out.print("New PIN: ");
                String np  = sc.nextLine();
                bank.changePin(a, old, np);

            } else if (ch.equals("7")) {
                System.out.print("Confirm close account? (yes/no): ");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    bank.close(a);
                    break;
                }

            } else if (ch.equals("8")) {
                System.out.println("Logged out.");
                break;

            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}