// extend `Payment` and implement `Verifiable`


public class CreditCardPayment extends Payment implements Verifiable {
    private String cardNumber;

    // constructor 
    public CreditCardPayment(double amount, String cardNumber) {
        // super() must be the first statement in the subclass constructor.
        super(amount);
        this.cardNumber = cardNumber;
    }

    public boolean verify() {
        if (cardNumber.length() == 16) {
            return true;
        }
        return false;
    }

    public void processPayment() {
        System.out.println("Processing credit card payment...");
    }

    // getter for cardNumber 
    public String getCardNumber() {
        return cardNumber;
    }

     @Override
    public void executePayment() {
        // delegate to processPayment (fulfills Payment.executePayment())
        processPayment();
    }

    @Override
    public boolean verifyPaymentDetails(String details) {
        // simple verification: check provided details match card number pattern or equal stored
        if (details == null) return false;
        if (details.equals(cardNumber)) return true;
        return details.matches("\\d{16}");
    }
}
