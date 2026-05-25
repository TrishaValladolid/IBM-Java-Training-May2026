package dayFour;

public class Employee {
    // Fields
    private String name;
    private String department;
    private double salary;

    // Constructor 
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getName(){
        return name;
    }

    public String getDepartment(){
        return department;
    }

    public double getSalary(){
        return salary;
    }

    // toString() override
    @Override
    public String toString(){
        return name + " | " + department + " | $" + salary;
    }
}