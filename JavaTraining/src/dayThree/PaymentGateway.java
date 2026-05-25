package dayThree;


// Non-sealed subclass allows further extension freely
public non-sealed class PaymentGateway extends Gateway {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of amount: P" + amount);
    }
}