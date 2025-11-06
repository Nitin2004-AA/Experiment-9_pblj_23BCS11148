import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // Change DB name
        String user = "root";
        String password = "your_password";

        Scanner sc = new Scanner(System.in);

        try {
            // Step 1: Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false); // Enable manual transaction handling
            System.out.println("✅ Connected to the database!");

            while (true) {
                System.out.println("\n===== PRODUCT MANAGEMENT MENU =====");
                System.out.println("1. Add Product");
                System.out.println("2. Display All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addProduct(con, sc);
                        break;
                    case 2:
                        displayProducts(con);
                        break;
                    case 3:
                        updateProduct(con, sc);
                        break;
                    case 4:
                        deleteProduct(con, sc);
                        break;
                    case 5:
                        con.close();
                        System.out.println("✅ Connection closed. Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("❌ Invalid choice! Try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔸 CREATE
    public static void addProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Product Name: ");
            sc.nextLine(); // consume newline
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            String query = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
