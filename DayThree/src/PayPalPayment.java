
public class PayPalPayment extends Payment implements Verifiable {
    private String email;

    // constructor
    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }

    @Override
    public boolean verifyPaymentDetails(String paymentInfo) {
        if (paymentInfo.contains("@")) {
            return true;
        }
        return false;
    }

    public void processPayment() {
        System.out.println("Processing credit card payment...");
    }

    // getter for email
    public String getEmail() {
        return email;
    }

    @Override
    public void executePayment() {
        processPayment();
    }
}




