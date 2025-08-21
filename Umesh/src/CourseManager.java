import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class CourseManager {
    private static final String COURSE_FILE = "courseForstudents.txt";
    private Scanner scanner;

    public CourseManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void assignCourses() {
        try (RandomAccessFile file = new RandomAccessFile(COURSE_FILE, "rw")) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter course 1: ");
            String c1 = scanner.nextLine();

            System.out.print("Enter course 2: ");
            String c2 = scanner.nextLine();

            System.out.print("Enter course 3: ");
            String c3 = scanner.nextLine();

            System.out.print("Enter course 4: ");
            String c4 = scanner.nextLine();

            file.seek(file.length());
            String line = id + "," + c1 + "," + c2 + "," + c3 + ","+ c4 +"\n";
            file.writeBytes(line);

            System.out.println("Courses assigned.");
        } catch (IOException e) {
            System.out.println("Error writing courses data.");
        }
    }

    public void viewStudentCourses() {
        System.out.print("Enter student ID to view courses: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        try (RandomAccessFile file = new RandomAccessFile(COURSE_FILE, "r")) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0]);
                    if (id == searchId) {
                        System.out.println("Courses for student ID " + id + ":");
                        System.out.println("1. " + parts[1]);
                        System.out.println("2. " + parts[2]);
                        System.out.println("3. " + parts[3]);
                        System.out.println("4. " + parts[4]);
                        found = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading courses file.");
        }

        if (!found) {
            System.out.println("No courses found for this student.");
        }
    }
}
