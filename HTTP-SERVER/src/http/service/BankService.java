package http.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcin on 27.10.2016.
 */
public class BankService implements IBankService{

    public static Map<String, Account> bank = new HashMap<>();
    @Override
    public void createAccount(Account account) {
        System.out.println("created");
    }

    @Override
    public void transfer(String sourcAccNumber, String destAccNumber, Double amount) {

    }

    @Override
    public Double getAccountBalance(String accNumber) {
        return 456.45;
    }
    @Override
    public Account getAccount(String accNumber){
        return new Account("asdasd",12.5);
    }
}
