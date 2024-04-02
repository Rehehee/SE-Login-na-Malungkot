package Services;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ServiceUser {
    
    private final String filePath;
    private String email;
    private String hashedPassword;
    private String salt;
    private static final int saltLength = 20; 
    
    public ServiceUser(String filePath) {
        this.filePath = filePath;
        loadUserCredentialsFromFile();
    }

    // Loading the credentials from the file
    private void loadUserCredentialsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    if (key.equals("email")) {
                        email = value;
                    } else if (key.equals("hashed_password")) {
                        hashedPassword = value;
                    } else if (key.equals("salt")) {
                        salt = value;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Authenticating the user
    public boolean authenticate(String email, String enteredPassword) {
        if (this.email == null || hashedPassword == null || salt == null) {
            return false; // No user credentials found
        }
        
        String enteredPasswordHashed = hashPassword(enteredPassword, Base64.getDecoder().decode(salt));
        
        return this.email.equals(email) && hashedPassword.equals(enteredPasswordHashed);
    }
    
    // Method for resetting the password
    public boolean resetPassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return false; // Passwords don't match
        }
        
        if (this.email.equals(email)) {
            byte[] newSalt = generateSalt(saltLength);
            String newHashedPassword = hashPassword(newPassword, newSalt);
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println("email:" + email);
                writer.println("hashed_password:" + newHashedPassword);
                writer.println("salt:" + Base64.getEncoder().encodeToString(newSalt));
                return true; // Password reset successful
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Error updating the file
            }
        }
        return false; // Email doesn't match stored email
    }
    
    // Method for hashing the password with salt
    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPasswordBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPasswordBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Method for generating a random salt
    private byte[] generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        return salt;
    }
}
