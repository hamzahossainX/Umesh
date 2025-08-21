import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Umesh! Please select:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                registerUser(scanner);
            } else if (choice == 2) {
                if (loginUser(scanner)) {

                    StudentManager studentManager = new StudentManager(scanner);
                    CourseManager courseManager = new CourseManager(scanner);

                    while (true) {
                        System.out.println("\nMenu:");
                        System.out.println("1. Add student");
                        System.out.println("2. View all students");
                        System.out.println("3. View student by ID");
                        System.out.println("4. Assign courses to student");
                        System.out.println("5. View student's courses");
                        System.out.println("6. Logout");
                        System.out.print("Enter choice: ");

                        int menuChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (menuChoice) {
                            case 1:
                                studentManager.addStudent();
                                break;
                            case 2:
                                studentManager.viewAllStudents();
                                break;
                            case 3:
                                studentManager.viewStudentById();
                                break;
                            case 4:
                                courseManager.assignCourses();
                                break;
                            case 5:
                                courseManager.viewStudentCourses();
                                break;
                            case 6:
                                System.out.println("Logged out.");
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                        if (menuChoice == 6) break;
                    }
                } else {
                    System.out.println("Login failed. Try again.");
                }
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                scanner.close();
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }


    private static void registerUser(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        try (RandomAccessFile file = new RandomAccessFile("usernamePassword.txt", "rw")) {

            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(username)) {
                    System.out.println("Username already exists! Try login or choose another username.");
                    return;
                }
            }
            // Append new user
            file.seek(file.length());
            file.writeBytes(username + "," + password + "\n");
            System.out.println("Registration successful! You can now login.");
        } catch (IOException e) {
            System.out.println("Error accessing user file.");
        }
    }

    // Method to login user
    private static boolean loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return LoginManager.verifyAdminAccess(username, password);
    }
}
