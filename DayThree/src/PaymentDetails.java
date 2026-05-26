 
import java.time.LocalDateTime;
 
// Record: immutable data carrier - auto-generates constructor, getters, equals, hashCode, toString
public record PaymentDetails(
        String transactionId,
        double amount,
        String paymentMethod,
        LocalDateTime timestamp
) {
 
}