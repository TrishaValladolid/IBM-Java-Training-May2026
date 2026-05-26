
public class BankTransferPayment extends Payment implements Verifiable {
    private String accountNumber;

    // constructor
    public BankTransferPayment(double amount,String accountNumber){ 
        super(amount);
        this.accountNumber = accountNumber;
    }

    public boolean verify() {
        if (accountNumber.length()==10){
            return true;
        }
        return false;
    }

    public void processPayment(){
                System.out.println("Processing bank transfer...");
    }

    //getter
    public String getAccountNUmber(){
        return accountNumber;
    }

    @Override
    public void executePayment() {
        processPayment();
    }

    @Override
    public boolean verifyPaymentDetails(String paymentInfo) {
       if (paymentInfo.equals(this.accountNumber) && paymentInfo.length() == 10){
        return true;
       }
        return  false;
    }
}