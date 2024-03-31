package Services;

import Models.ModelMessage;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMail {

    public ModelMessage sendMain(String toEmail, String code) {
        ModelMessage ms = new ModelMessage(false, "");
        String from = "******@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "******@gmail.com";
        String password = "******";    //  Your email password here
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Verify Code");
            message.setText(code);
            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            if (e.getMessage().equals("Invalid Addresses")) {
                ms.setMessage("Invalid email");
            } else {
                ms.setMessage("Error");
            }
        }
        return ms;
    }
    
     // Method to generate a random verification code
    public String generateVerificationCode() {
        // Define the length of the verification code
        int codeLength = 6; // You can adjust the length as needed
        
        // Define the characters to use for generating the code
        String characters = "0123456789"; // You can include letters if desired
        
        // Create a StringBuilder to store the generated code
        StringBuilder codeBuilder = new StringBuilder();
        
        // Create a random object to generate random numbers
        Random random = new Random();
        
        // Generate the code by randomly selecting characters from the defined set
        for (int i = 0; i < codeLength; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            codeBuilder.append(randomChar);
        }
        
        // Return the generated verification code as a string
        return codeBuilder.toString();
    }
}

