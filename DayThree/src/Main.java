import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Payment> payments = new ArrayList<>();
        payments.add(new CreditCardPayment(1500.00, "1234567812345678")); // valid: 16 digits
        payments.add(new PayPalPayment(750.50, "trisha@email.com"));      
        payments.add(new BankTransferPayment(200.00, "1234567890"));      // valid: 10 digits

        PaymentType onlineType = new OnlinePaymentType();
        PaymentType offlineType = new OfflinePaymentType();

        System.out.println("Payment Categories:");
        System.out.println("  Credit Card & PayPal -> " + onlineType);
        System.out.println("  Bank Transfer        -> " + offlineType);
        System.out.println();

        Gateway gateway = new PaymentGateway();

        List<PaymentDetails> successfulTransactions = new ArrayList<>();

        int transactionCounter = 1;

        for (Payment payment : payments) {

            Verifiable verifiable = (Verifiable) payment;

            String paymentInfo = getPaymentInfo(payment);

            payment.displayAmount();

            if (verifiable.verifyPaymentDetails(paymentInfo)) {
                gateway.processPayment(payment.getAmount());
                System.out.println("Payment has been processed.");

                PaymentDetails details = new PaymentDetails(
                        "TXN" + transactionCounter++,                        
                        payment.getAmount(),
                        payment.getClass().getSimpleName(),
                        LocalDateTime.now()
                );
                successfulTransactions.add(details);

            } else {
                System.out.println("Verification failed.");
            }

            System.out.println();
        }

        System.out.println("----------------Successful Transaction Records-----------------");
        for (PaymentDetails details : successfulTransactions) {
            System.out.println(details);
        }
    }

    private static String getPaymentInfo(Payment payment) {
        if (payment instanceof CreditCardPayment cc) {
            return cc.getCardNumber();
        } else if (payment instanceof PayPalPayment pp) {
            return pp.getEmail();
        } else if (payment instanceof BankTransferPayment bt) {
            return bt.getAccountNUmber();
        }
        return "";
    }
}