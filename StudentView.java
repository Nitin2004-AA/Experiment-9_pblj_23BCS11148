import java.util.List;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    Student s = new Student(name, dept, marks);
                    if (dao.addStudent(s))
                        System.out.println(" Student added successfully!");
                    else
                        System.out.println(" Failed to add student.");
                    break;

                case 2:
                    List<Student> list = dao.getAllStudents();
                    System.out.println("\n--- Student Records ---");
                    System.out.printf("%-10s %-20s %-15s %-10s\n", "ID", "Name", "Department", "Marks");
                    System.out.println("-----------------------------------------------------------");
                    for (Student st : list) {
                        System.out.println(st);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String newDept = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    double newMarks = sc.nextDouble();
                    Student s2 = new Student(idU, newName, newDept, newMarks);
                    if (dao.updateStudent(s2))
                        System.out.println(" Student updated successfully!");
                    else
                        System.out.println(" Update failed.");
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int idD = sc.nextInt();
                    if (dao.deleteStudent(idD))
                        System.out.println(" Student deleted successfully!");
                    else
                        System.out.println(" Deletion failed or ID not found.");
                    break;

                case 5:
                    System.out.println(" Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }
        }
    }
}
