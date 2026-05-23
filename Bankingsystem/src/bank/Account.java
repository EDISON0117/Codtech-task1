package bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    String number;
    String name;
    String type;
    double balance;
    String pin;
    boolean active;
    List<String> history;

    Account(String number, String name, String type, double balance, String pin) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.pin = pin;
        this.active = true;
        this.history = new ArrayList<>();
        history.add("Opening deposit: +" + balance);
    }
}