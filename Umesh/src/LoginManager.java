

import java.io.RandomAccessFile;
import java.io.IOException;

    public class LoginManager {
        private static final String USER_FILE = "usernamePassword.txt";

        // Check if username and password match any entry in the user file
        public static boolean verifyAdminAccess(String username, String password) {
            try (RandomAccessFile file = new RandomAccessFile(USER_FILE, "r")) {
                String line;
                while ((line = file.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String user = parts[0].trim();
                        String pass = parts[1].trim();
                        if (user.equals(username) && pass.equals(password)) {
                            return true;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading user file.");
            }
            return false;
        }
    }









