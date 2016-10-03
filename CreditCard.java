import java.text.*;

public class CreditCard {
    
    String cardholderName;
    double previousBalance;
    double purchases;
    double balanceTransfers;
    double newBalance; 
    double fees;
    double interest;
    double cashAdvances;
    int numberOfPurchases;
    double BALANCE_APR = 0.159;
    int DUE_DATE = 20;
    double LATE_PAYMENT_FEE = 37.0;
    double MIN_INTEREST_AMT = 0.5;
    double CASH_ADV_APR = 0.259;
    double TRANSFER_MIN_FEE = 5.0;
    double TRANSFER_FEE_PERCENT = 0.03;
    int daysInMonth = 30;
    int daysInYear = 365;
    double minimumPayment = 25.00;
    double minPaymentPercent = 0.015;
    
    // constructors
    public CreditCard(String name) {
        cardholderName = name;
        previousBalance = 0;
        balanceTransfers = 0;
        fees = 0;
        interest = 0;
        cashAdvances = 0;
        numberOfPurchases = 0;
    }
    
    public CreditCard(String name, double amt) {
        cardholderName = name;
        previousBalance = amt;
        fees = 0;
        balanceTransfers = 0;
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
        if (amt < 0) 
            System.out.println("Request Cancled: Credits need to be positive values. Use purchase() instead.");
        else {
            purchases -= amt;
            System.out.println("Credit applied of: " + dollarFormat(amt));
        }
    }
    public void balanceTransfer(double amt) {
        // adds to transfer balance total, moved to total amount due at end of month
        // update transfer balance and fees, fee greater of either $5 or 3% of amt, display message
        if (amt >= 0) {
            balanceTransfers += amt;
            fees += amt * TRANSFER_FEE_PERCENT > TRANSFER_MIN_FEE ? amt * TRANSFER_FEE_PERCENT : TRANSFER_MIN_FEE;
            System.out.println("Balance transfer of " + dollarFormat(amt));
        }
        else
            System.out.println("Request Cancled: balance transfers must be positive.");
    }
    public void purchase (double amt) {
        // update purchase balance and increment number of purchases, display message
        if (amt >= 0) {
            purchases += amt;
            numberOfPurchases++;
            System.out.println("Purchase of " + dollarFormat(amt));
        }
        else 
            System.out.println("Request Cancled: Purchases must be positive amounts. Use applyCredit() instead.");
    }
    public void cashAdvance(double amt, int day) {
        // update cash adv bal & interest, day == day adv made, interest calc apply cash adv apr
        // for days until end of month (30day month, 365 day yr), min interest == $ 0.5
        if (amt >= 0 && 0 < day && day <= 30 ) {
            double advanceInterest = amt * (CASH_ADV_APR / daysInYear) * (daysInMonth - day + 1);
            if (advanceInterest > MIN_INTEREST_AMT) 
                interest += advanceInterest;
            else 
                interest += MIN_INTEREST_AMT;
            cashAdvances += amt;
            System.out.println("Cash advance on Day " + day + ": " + dollarFormat(amt));
        }
        else if (amt < 0)
            System.out.println("Request Cancled: Amount must be positive.");
        else if (day < 0 || day > 30)
            System.out.println("Day out of range, must be 1 - 30.");
    }
    // helper methods 
    private double calcMinimumPayment(double balance) {
        // calc and return min payment, greater of $25 or 1.5% of new balance at end of month
        // this is invoked by printStatement();
        // NOTE: the minimum payment description in the assignment didn't make sense, as it would 
        // impose a $25 minimum payment even in situations where the balance is less than $25,
        // I adjusted it below to avoid this
        if (balance >= 25 )
            return balance * minPaymentPercent > minimumPayment ? balance * minPaymentPercent : minimumPayment;
        else if ( balance > 0)
            return balance;
        else 
            return 0;
    }
    private void resetAmount() {
        // update prveious balance to new balance, reset all other instance vars to 0,
        // invoked by closeBillingPeriod();
        previousBalance += newBalance;
        purchases = 0;
        balanceTransfers = 0;
        newBalance = 0;
        fees = 0;
        interest = 0;
        cashAdvances = 0;
        numberOfPurchases = 0;    
 
    }
    private void printStatement() {
        // display monthly statement, invokes calcMinimumPayment();, 
        // invoked by closeBillingPeriod();
        System.out.println("Previous Balance: \t" + dollarFormat(getPreviousBalance()));
        System.out.println(getNumPurchases() + " Purchase(s): \t\t" + dollarFormat(getPurchases()));
        System.out.println("Advances: \t\t" + dollarFormat(getCashAdvances()));
        System.out.println("Transfers: \t\t" + dollarFormat(getBalanceTransfers()));
        System.out.println("Interest: \t\t" + dollarFormat(getInterest()));
        System.out.println("Fees: \t\t\t" + dollarFormat(getFees()));
        System.out.println("New Balance: \t\t" + dollarFormat(newBalance));
        System.out.println("Minimum Payment: \t" + dollarFormat(calcMinimumPayment(newBalance)));
    }
    // additional mutators
    public void makePayment(double amt, int day) {
        // reduce previous balance by amt, day == day in month payment submitted
        // apply penalty fee of $37 if amt < required min payment, or later than 20th of month
        // invoke calcMinimumPayment(); display message
        if (amt >= 0 && 0 < day && day < 30) {
            if (day > DUE_DATE || amt < calcMinimumPayment(getPreviousBalance())) 
                fees += 37;
            
            previousBalance -= amt;
            System.out.println("Payment on Day " + day + ": " + dollarFormat(amt));
        }
        else if (amt < 0) 
            System.out.println("Request Cancled: amount must be positive.");
        else if (day < 0 || day > 30) 
            System.out.println("Request Cancled: Day out of range, must be 1 - 30.");
    }
    public void closeBillingPeriod() {
        // calc interest on prior balance by apply bal APR, add interest, transfer,
        // purchases, cash adv, fees, prior balance, display curr stmt by invoking printStatement();
        // reset all instance vars as necessary by resetAmounts();
        // do not calculate interest on negative balances (not paying the customer interest)
        // the assignment is ambiguous regarding whether the total interst charge 
        // for the month has a minimum charge of $0.50, or if both balance interest AND 
        // cash advance interest have separate minimum $0.50 charges 
        if (getPreviousBalance() > 0) {
            double currentInterest =  getPreviousBalance() * (BALANCE_APR / 365) * 30;
            if (currentInterest > MIN_INTEREST_AMT)
                interest += currentInterest;
            else 
                interest += MIN_INTEREST_AMT;    
        }
        newBalance += getPreviousBalance() + getInterest() + getBalanceTransfers() + getPurchases() + getCashAdvances() + getFees();
        printStatement();
        resetAmount();
    }
    // 1) update necessary methods to prevent user from entering negative values to parameters
    // by ignorning them, no action taken if amt == neg, or day not 1-30
    // 2) use NumberFormat to display all monetary values in currency format
    // 3) define instance var used to format currency val:

    // create test class
    private String dollarFormat(double amt) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        
        return fmt.format(amt);
    }
    
              
    
}