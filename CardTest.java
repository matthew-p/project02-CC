public class CardTest {
    // class to test CreditCard class
    public static void main(String[] args) {
        CreditCard brenda = new CreditCard("Brenda Stern");
        int errors = 0;
        // multiple purchases
        brenda.purchase(100);
        brenda.purchase(2.35);
        if(brenda.getPurchases() != 102.35) {
            System.out.println(" Error with purchase");
            errors++;
        }
        // negative purchase
        brenda.purchase(-10);
        if (brenda.getPurchases() != 102.35) {
            System.out.println(" Error with negative purchase");
            errors++;
        }
        brenda.applyCredit(-5.05);
        if (brenda.getPurchases() != 102.35) {
            System.out.println("Error with negative applyCredit");
            errors++;
        }
        brenda.applyCredit(10.00);
        if (brenda.getPurchases() != 92.35) {
            System.out.println("Error with positive applyCredit");
            errors++;
        }
        brenda.balanceTransfer(-50.00);
        if (brenda.getBalanceTransfers() != 0) {
            System.out.println("Error with negative balanceTransfer");
            errors++;
        }
        brenda.balanceTransfer(1.0);
        if (brenda.getBalanceTransfers() != 1.0) {
            System.out.println("Error with balanceTransfer");
            errors++;
        }
        if (brenda.getFees() != 5.0) {
            System.out.println("Error with transfer minimum fee");
            errors++;
        }
        brenda.balanceTransfer(200.0);
        if (brenda.getBalanceTransfers() != 201.0) {
            System.out.println("Error with multiple transfers");
            errors++;
        }
        if (brenda.getFees() != 11.0) {
            System.out.println("Error with transfer fees");
            errors++;
        }
        brenda.cashAdvance(-20.0, 5);
        if (brenda.getCashAdvances() > 0) {
            System.out.println("Error with negative cash advance");
            errors++;
        }
        brenda.cashAdvance(20.0, 35); 
        if (brenda.getCashAdvances() > 0) {
            System.out.println("Error with >date out of range");
            errors++;
        }
        brenda.cashAdvance(20.0, -4);
        if (brenda.getCashAdvances() > 0) {
            System.out.println("Errors with <date out of range");
            errors++;
        }
        brenda.cashAdvance(10.0, 10);
        if (brenda.getCashAdvances() != 100.0 && brenda.getInterest() != 0.5) {
            System.out.println("Error in valid cash advance");
            errors++;
        }
        brenda.closeBillingPeriod();
        if (brenda.getPreviousBalance() != 92.35 + 201.0 + 11.0 + 10.0 + 0.5) {
            System.out.println("Error with totaling monthly balance: ");
            System.out.println("Expected: " + 314.85 + " recieved balance: " + brenda.getPreviousBalance());
            errors++;
        }
        brenda.makePayment(-10.0, 3);
        if (brenda.getPreviousBalance() != 314.85) {
            System.out.println("Error in -payment");
            errors++;
        }
        brenda.makePayment(10.0, 33);
        if (brenda.getPreviousBalance() != 314.85) {
            System.out.println("Error in day range");
            errors++;
        }
        brenda.makePayment(14.85, 19);
        if (brenda.getPreviousBalance() != 300.00 && brenda.getFees() != 37) {
            System.out.println("Error in +payment");
            errors++;
        }
        
        
        System.out.println(errors + " errors detected");
    }
    
}