package dayThree;
 
// Sealed class - only permits OnlinePaymentType and OfflinePaymentType to extend it
public abstract sealed class PaymentType permits OnlinePaymentType, OfflinePaymentType {
 
}
 