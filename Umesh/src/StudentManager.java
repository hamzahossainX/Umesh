import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class StudentManager {
    private static final String STUDENT_FILE = "students.txt";
    private Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addStudent() {
        try (RandomAccessFile file = new RandomAccessFile(STUDENT_FILE, "rw")) {
            System.out.print("Enter student ID (number): ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter program: ");
            String program = scanner.nextLine();

            System.out.print("Enter batch: ");
            String batch = scanner.nextLine();

            System.out.print("Enter CGPA (number): ");
            double cgpa = scanner.nextDouble();
            scanner.nextLine();

            file.seek(file.length());
            String line = id + "," + name + "," + program + "," + batch + "," + cgpa + "\n";
            file.writeBytes(line);

            System.out.println("Student added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing student data.");
        }
    }

    public void viewAllStudents() {
        boolean anyStudent = false;
        try (RandomAccessFile file = new RandomAccessFile(STUDENT_FILE, "r")) {
            String line;
            System.out.println("\nAll Students:");
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    anyStudent = true;
                    System.out.println("ID: " + parts[0] +
                            ", Name: " + parts[1] +
                            ", Program: " + parts[2] +
                            ", Batch: " + parts[3] +
                            ", CGPA: " + parts[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students file.");
            return;
        }

        if (!anyStudent) {
            System.out.println("No Student's Found");
        }
    }


    public void viewStudentById() {
        System.out.print("Enter student ID to search: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        try (RandomAccessFile file = new RandomAccessFile(STUDENT_FILE, "r")) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0]);
                    if (id == searchId) {
                        System.out.println("Student found:");
                        System.out.println("ID: " + parts[0]);
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Program: " + parts[2]);
                        System.out.println("Batch: " + parts[3]);
                        System.out.println("CGPA: " + parts[4]);
                        found = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students file.");
        }

        if (!found) {
            System.out.println("Student ID not found.");
        }
    }
}
