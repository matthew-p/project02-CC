public class CreditCard {
    // instance variables 
    String cardholderName;
    double previousBalance;
    double purchases;
    double balanceTransfers;
    double newBalance; // optional instance variable 
    double fees;
    double interest;
    double cashAdvances;
    int numberOfPurchases;
    double BALANCE_APR = 0.159;
    int DUE_DATE = 20;
    double LATE_PAYMENT_FEE = 37.0;
    double MIN_INTEREST_AMT = 0.5;
    double CASH_ADV_APR = 0.259;
    
    // constructors
    public CreditCard(String name) {
        cardholderName = name;
        previousBalance = 0;
        fees = 0;
        interest = 0;
        cashAdvances = 0;
        numberOfPurchases = 0;
    }
    
    public CreditCard(String name, double amt) {
        cardholderName = name;
        previousBalance = amt;
        fees = 0;
        interest = 0;
        cashAdvances = 0;
        numberOfPurchases = 0;
    }
    // accessor methods
    public double getPreviousBalance() {
        return previousBalance;
    }
    public double getFees() {
        return fees;
    }
    public double getInterest() {
        return interest;
    }
    public double getPurchases() {
        return purchases;
    }
    public double getBalanceTransfers() {
        return balanceTransfers;
    }
    public double getCashAdvances() {
        return cashAdvances;
    }
    public int getNumPurchases() {
        return numberOfPurchases;
    }
    public String getName() {
        return cardholderName;
    }
    // mutator methods
    public void applyCredit (double amt) {
        // reduce purchases by amt, display message
        purchases -= amt;
        System.out.println("Credit: " + dollarFormat(amt));
    }
    public void balanceTransfer(double amt) {
        // adds to transfer balance total, moved to total amount due at end of month
        // update transfer balance and fees, fee greater of either $5 or 3% of amt, display message
    }
    public void purchase (double amt) {
        // update purchase balance and increment number of purchases, display message
    }
    public void cashAdvance(double amt, int day) {
        // update cash adv bal & interest, day == day adv made, interest calc apply cash adv apr
        // for days until e nd of month (30day month, 365 day yr), min interest == $ 0.5
    }
    // helper methods 
    private double calcMinimumPayment() {
        // calc and return min payment, greater of $25 or 1.5% of new balance at end of month
        // this is invoked by printStatement();
    }
    private void resetAmount() {
        // update prveious balance to new balance, reset all other instance vars to 0,
        // invoked by closeBillingPeriod();
    }
    private void printStatement() {
        // display monthly statement, invokes calcMinimumPayment();, 
        // invoked by closeBillingPeriod();
    }
    // additional mutators
    public void makePayment(double amt, int day) {
        // reduce previous balance by amt, day == day in month payment submitted
        // apply penalty fee of $37 if amt < required min payment, or later than 20th of month
        // invoke calcMinimumPayment(); display message
    }
    public void closeBillingPeriod() {
        // calc interest on prior balance by apply bal APR, add interest, transfer,
        // purchases, cash adv, fees, prior balance, display curr stmt by invoking printStatement();
        // reset all instance vars as necessary by resetAmounts();
    }
    // 1) update necessary methods to prevent user from entering negative values to parameters
    // by ignorning them, no action taken if amt == neg, or day not 1-30
    // 2) use NumberFormat to display all monetary values in currency format
    // 3) define instance var used to format currency val:
    // private NumberFormat = fmt = NumberFormat.getCurrencyInstance();
    // fmt.format(inputAmount);
    // create test class
    private String dollarFormat(double amt) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return fmt.
    }
    
    
    
    
    
    
    
    
    
    
    
    
}