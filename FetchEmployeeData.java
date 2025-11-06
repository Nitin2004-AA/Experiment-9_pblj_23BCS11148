import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/testdb"; // replace testdb with your DB name
        String user = "root";
        String password = "your_password";

        try {
            // Step 1: Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database successfully!");

            // Step 3: Create a statement
            Statement stmt = con.createStatement();

            // Step 4: Execute query
            String query = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(query);

            // Step 5: Display data
            System.out.println("\nEmployee Details:");
            System.out.println("----------------------------------");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + " | " + name + " | " + salary);
            }

            // Step 6: Close all resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("----------------------------------");
            System.out.println("✅ Data fetched and connection closed successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println(" MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Database error!");
            e.printStackTrace();
        }
    }
}
