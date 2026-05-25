package dayThree;

interface Verifiable {
    //- A method to verify payment details (returns true/false)
    boolean verifyPaymentDetails(String paymentInfo); //parameter can be any string representing payment details (e.g., card number, email)

    

}


/*  Define the interface
interface Animal {
    // Constant (public, static, final by default)
    int DEFAULT_AGE = 0;

    // Abstract method (public and abstract by default)
    void makeSound();

    // Default method (has a body, can be overridden)
    default void sleep() {
        System.out.println("Sleeping...");
    }

    // Static method (belongs to the interface itself)
    static void info() {
        System.out.println("All animals have some common traits.");
    }
}

// Implement the interface in a class
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

// Another class implementing the same interface
class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

public class InterfaceExample {
    public static void main(String[] args) {
        // Using interface reference
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.makeSound(); // Woof! Woof!
        dog.sleep();     // Sleeping...

        cat.makeSound(); // Meow!
        cat.sleep();     // Sleeping...

        // Calling static method from interface
        Animal.info();
    }
}
*/