package dayThree;
 
// Final: cannot be extended further
public final class OnlinePaymentType extends PaymentType {
 
    public OnlinePaymentType() {
        super();
    }
 
    @Override
    public String toString() {
        return "OnlinePaymentType: Credit Card / PayPal";
    }
}
 