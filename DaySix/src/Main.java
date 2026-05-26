import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {

            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "password"
            );

            while (true) {

                System.out.println("\n=== MENU ===");
                System.out.println("[A]dd");
                System.out.println("[V]iew");
                System.out.println("[U]pdate Password");
                System.out.println("[D]elete");
                System.out.println("[Q]uit");
                System.out.print("Enter choice: ");

                String choice = sc.nextLine();

                // ADD
                if (choice.equalsIgnoreCase("A")) {

                    System.out.print("Student ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    System.out.print("First Name: ");
                    String firstname = sc.nextLine();

                    System.out.print("Last Name: ");
                    String lastname = sc.nextLine();

                    String sql = "INSERT INTO student.student VALUES (?, ?, ?, ?, ?, NOW(), NOW())";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, id);
                    ps.setString(2, email);
                    ps.setString(3, password);
                    ps.setString(4, firstname);
                    ps.setString(5, lastname);

                    ps.executeUpdate();

                    System.out.println("Student added!");

                }

                // VIEW
                else if (choice.equalsIgnoreCase("V")) {

                    String sql = "SELECT * FROM student.student";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        System.out.println("--------------------");
                        System.out.println("ID: " + rs.getInt("studentid"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("First Name: " + rs.getString("firstname"));
                        System.out.println("Last Name: " + rs.getString("lastname"));
                        System.out.println("Password: " + rs.getString("password"));
                    }

                }

                // UPDATE PASSWORD
                else if (choice.equalsIgnoreCase("U")) {

                    System.out.print("Enter student ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter new password: ");
                    String newPassword = sc.nextLine();

                    String sql = "UPDATE student.student SET password = ?, dateupdated = NOW() WHERE studentid = ?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, newPassword);
                    ps.setInt(2, id);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Password updated!");
                    } else {
                        System.out.println("Student not found.");
                    }

                }

                // DELETE
                else if (choice.equalsIgnoreCase("D")) {

                    System.out.print("Enter student ID to delete: ");
                    int id = Integer.parseInt(sc.nextLine());

                    String sql = "DELETE FROM student.student WHERE studentid = ?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, id);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Student deletd!");
                    } else {
                        System.out.println("Student not found.");
                    }

                }

                // QUIT
                else if (choice.equalsIgnoreCase("Q")) {

                    con.close();

                    System.out.println("Program ended.");
                    break;
                }

                else {
                    System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        sc.close();

    }
    

}