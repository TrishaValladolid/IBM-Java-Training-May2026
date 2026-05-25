package dayFour;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        
        // Add at least 10 employee objects
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 52000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 50000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        System.out.println("Added Employees: " + employees);
        System.out.println("");

        // A.) Create a Set using HashSet to track names and keep only the first occurrence of each name
        Set<String> uniqueNamesSet = new HashSet<String>();
        List<Employee> uniqueNamesList = new ArrayList<>();

        for (Employee emp : employees) {
            if (uniqueNamesSet.add(emp.getName())){
                uniqueNamesList.add(emp);
            }  
            
        }
        System.out.println("=== Unique Employees=== "); 
        for (Employee emp : uniqueNamesList) {
            System.out.println(emp);   
        }
         System.out.println();
        // B.) Group employees by department
        Map<String, List<Employee>> departmentGroups = new HashMap<>();
        for (Employee emp : employees) {
            departmentGroups.computeIfAbsent(emp.getDepartment(), k -> new ArrayList<>()).add(emp);
        }
        // Print the grouped employees
        System.out.println("===Employees by Department===");

        for (Map.Entry<String, List<Employee>> entry : departmentGroups.entrySet()) {
            System.out.println(entry.getKey());
            for (Employee emp : entry.getValue()) {
                System.out.println("  - " + emp);
            }
        }

        
        // C.) Find the highest paid employee in each department
        Map<String, Employee> highestPaidByDepartment = new HashMap<>();

        System.out.println("===Highest Paid per Department===");

        for (Employee emp : employees) {
            highestPaidByDepartment.compute(emp.getDepartment(), (dept, currentHighest) -> {
                if (currentHighest == null || emp.getSalary() > currentHighest.getSalary()) {
                    return emp;
                }

                return currentHighest;
            });

        }
        /* 
        for (Employee emp : employees) {
            Employee current = highestPaidByDepartment.get(emp.getDepartment());
            if (current == null || emp.getSalary() > current.getSalary()) {
                highestPaidByDepartment.put(emp.getDepartment(), emp);
            }
        }
        */
        // Print the highest paid employee in each department
        System.out.println("");

        //System.out.println("===Highest Paid per Department===");
        /*for (Map.Entry<String, Employee> entry : highestPaidByDepartment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName() + " | " + entry.getKey() + " | " + entry.getValue().getSalary());
        }*/

        // D.) Sort employees by salary in descending order
        employees.sort((emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary()));

        //E.) Create a set of all unique salaries
        Set<Double> uniqueSalaries = new HashSet<>();
        for (Employee emp : employees){
            uniqueSalaries.add(emp.getSalary());
        }
        List<Double> uniqueSalariesList = new ArrayList<>(uniqueSalaries);
        Collections.sort(uniqueSalariesList);
        System.out.println();

        System.out.println("===Unique salaries (Sorted) ===");

        for (Double salary : uniqueSalariesList){
            System.out.println("$" + salary);
        }
/* 
        TreeSet<Double> uniqueSalariesSet = new TreeSet<>();
        List<Double> uniqueSalariesList = new ArrayList<>();

        for (Employee emp : employees) {
            uniqueSalariesSet.add(emp.getSalary());   
        }
        System.out.println(" ");
        System.out.println("===Unique salaries (Sorted) ===");
        
         for (Double salary : uniqueSalariesList) {
            System.out.println("$" + salary);   
        }
            */
    }
}
