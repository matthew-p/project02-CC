public class CardTest {
    // class to test CreditCard class
    public static void main(String[] args) {
        CreditCard brenda = new CreditCard("Brenda Stern");
        int errors = 0;
        // multiple purchases
        brenda.purchase(100);
        brenda.purchase(2.35);
        if(brenda.getPurchases() != 102.35) {
            System.out.println(" ERROR! with purchase");
            errors++;
        }
        // negative purchase
        brenda.purchase(-10);
        if (brenda.getPurchases() != 102.35) {
            System.out.println(" ERROR! with negative purchase");
        }
        
        // make 10-15 total unit tests total
        
        System.out.println(errors + " errors detected");
    }
    
}