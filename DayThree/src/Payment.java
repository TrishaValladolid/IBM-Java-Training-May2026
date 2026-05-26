
public abstract class Payment {
    // Field demonstrating encapsulation
    private double amount;

    // Constructor accepting amount parameter
    public Payment(double amount) {
        this.amount = amount;
    }

    // Abstract method demonstrating abstraction
    public abstract void executePayment();

    // Concrete method to display the amount
    public void displayAmount() {
        System.out.println("Amount: P" + amount);
    }

    // Concrete method to retrieve the amount (demonstrates encapsulation)
    public double getAmount() {
        return amount;
    }
}


/*#### 2. Create an abstract class: `Payment`

**Fields:**
- Amount field (demonstrates encapsulation)

**Constructor:**
- Accepts amount parameter

**Abstract methods:**
- Payment execution method (demonstrates abstraction)

**Concrete methods:**
- Method to display the amount
- Method to retrieve the amount (demonstrates encapsulation) */