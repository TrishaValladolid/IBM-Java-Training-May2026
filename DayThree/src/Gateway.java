
// Sealed superclass allowing limited subclasses
public abstract sealed class Gateway permits PaymentGateway {
    // Common functionality for all gateways
    public void connect() {
        System.out.println("Connecting to gateway...");
    }

    public abstract void processPayment(double amount);
}