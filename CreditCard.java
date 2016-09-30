public class CreditCard {
    // instance variables 
    String cardHolderName;
    double previousBalance;
    double fees;
    double interest;
    double cashAdvances;
    int numberOfPurchases;
    double BALANCE_APR = 0.159;
    int DUE_DATE = 20;
    double LATE_PAYMENT_FEE = 37.0;
    double MIN_INTEREST_AMT = 0.5;
    double CASH_ADV_APR = 0.259;
    
    public CreditCard(String name) {
        cardHolderName = name;
        previousBalance = 0;
        fees = 0;
        interest = 0;
        cashAdvances = 0;
        numberOfPurchases = 0;
    }
    
    public CreditCard(String name, double amt) {
        cardHolderName = name;
        previousBalance = amt;
        fees = 0;
        interest = 0;
        cashAdvances = 0;
        numberOfPurchases = 0;
    }
    
}