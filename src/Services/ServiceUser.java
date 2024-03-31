package Services;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ServiceUser {
    private final String filePath;
    private String email;
    private String hashedPassword;

    public ServiceUser(String filePath) {
        this.filePath = filePath;
        loadUserCredentialsFromFile();
    }

    //Loading the Credentials that I put in the txt file
    private void loadUserCredentialsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    email = parts[0];
                    String storedPassword = parts[1];
                    // Check if the stored password is already hashed
                    if (storedPassword.length() != 64) {
                        // If not hashed, hash the password and update the file
                        hashedPassword = hashPassword(storedPassword);
                        // Update the file with the hashed password
                        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                            writer.println(email + ":" + hashedPassword);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Password is already hashed, no need to hash again
                        hashedPassword = storedPassword;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Authentication of THE LOGIN
    public boolean authenticate(String email, String enteredPassword) {
        if (this.email == null || hashedPassword == null) {

            return false; // No user credentials found
        }
        String enteredPasswordHashed = hashPassword(enteredPassword);
        boolean isAuthenticated = this.email.equals(email) && hashedPassword.equals(enteredPasswordHashed);
        return isAuthenticated;
}
    
    //Method for Resetting the password
    public boolean resetPassword(String email, String newPassword, String confirmPassword) {
        // Check if the new password matches the confirmation password
        if (!newPassword.equals(confirmPassword)) {
            return false; // Passwords don't match
        }
        // Update the password only if the email matches the stored email
        if (this.email.equals(email)) {
            hashedPassword = hashPassword(newPassword);
            // Update the file with the new hashed password
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println(email + ":" + hashedPassword);
                return true; // Password reset successful
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Error updating the file
            }
        }
        return false; // Email doesn't match stored email
    }
     
    //Method for hashing the password 
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    
    }

}
