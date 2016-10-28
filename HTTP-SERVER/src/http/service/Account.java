package http.service;

public class Account{
    public Account(){}

    public Account(String accNumber, double balance){
        this.accNumber = accNumber;
        this.balance = balance;
    }

    private String accNumber;
    private Double balance;

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
