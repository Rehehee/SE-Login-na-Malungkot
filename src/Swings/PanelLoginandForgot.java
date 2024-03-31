//Panel for Login and Forgot Password (yung laman sa gilid goise) 

package Swings;

import Services.ServiceUser;
import Swings.Button;
import Swings.MyTextField;
import Swings.MyPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import real.se.LoginPage;



public class PanelLoginandForgot extends javax.swing.JLayeredPane{
 
    private Button button;
    private ActionListener event;
    
    
    public PanelLoginandForgot() {
        initComponents();
        initForgotPass();
        initLogin();
        login.setVisible(true);
        forgotPass.setVisible(false);
        
        
    }
    
     //Forgot Password textfields and button
    private void initForgotPass(){
        //Used MigLayout para sa mas maayos na pagposition (Nakita ko lang sa yt to)
        
        //Forgot Password Title Label 
        forgotPass.setLayout(new MigLayout("wrap" , "push[center]push", "push[]25[]10[]10[]25[]10[]push")); //Positions of the Forgot Password and Text Fields, respectively
        JLabel label = new JLabel("Forgot Password");
        label.setFont(new Font("Montserrat", 1, 30));
        label.setForeground(new Color(23,26,46));
        forgotPass.add(label); 
        
        //Email Text Field
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/mail.png")));
        txtEmail.setHint("Email");
        forgotPass.add(txtEmail, "w 60%");
        
        //New Password TextField
        MyPasswordField txtNew = new MyPasswordField();
        txtNew.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/pass.png")));
        txtNew.setHint("New Password");
        forgotPass.add(txtNew, "w 60%");
        
        //Confirm Password TextField
        MyPasswordField txtConfirm = new MyPasswordField();
        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/pass.png")));
        txtConfirm.setHint("Confirm Password");
        forgotPass.add(txtConfirm, "w 60%");
        
        //Login Button 
       ImageIcon btnSubmit = new ImageIcon(getClass().getResource("/Assets/btnSubmit.png"));
        Button cmd = new Button(btnSubmit);
        cmd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve email, new password, and confirm password from text fields
            String email = txtEmail.getText().trim();
            String newPassword = txtNew.getText().trim();
            String confirmPassword = txtConfirm.getText().trim();
            
            // Call forgotPass method of LoginPage
            ((LoginPage) SwingUtilities.getWindowAncestor(PanelLoginandForgot.this))
                    .forgotPass(email, newPassword, confirmPassword);
            
        }
    });
        forgotPass.add(cmd, "w 40%, h 40");
        
        //Enter Key Event
        
        txtEmail.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Simulate clicking the submit button
                cmd.doClick();
            }
        }
    });
        
        txtConfirm.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Simulate clicking the submit button
                cmd.doClick();
            }
        }
    });
        
        txtNew.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Simulate clicking the submit button
                cmd.doClick();
            }
        }
    });

    }

         
     
    
    private void initLogin(){
        //Login Elements (Textfield, button, passwordfield layout)
        login.setLayout(new MigLayout("wrap" , "push[center]push", "push[]25[]10[]10[]push")); //Positions of the Login and its Assets
        JLabel label = new JLabel("LOG IN");
        label.setFont(new Font("Montserrat", 1, 30));
        label.setForeground(new Color(23,26,46));
        login.add(label); 
        
        //Login TextField Function 
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        
        //Login Password function 
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        
        //Login Button function 
        ImageIcon btnLogin = new ImageIcon(getClass().getResource("/Assets/btnLogin.png"));
        Button cmd = new Button(btnLogin);
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                char[] enteredPasswordChars = txtPass.getPassword();
                String enteredPassword = new String(enteredPasswordChars).trim();
                ((LoginPage) SwingUtilities.getWindowAncestor(PanelLoginandForgot.this)).login(email, enteredPassword);
               }
        });
        
       
        login.add(cmd, "w 40%, h 40");
        
        //Enter Key Events
        txtEmail.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Simulate clicking the submit button
                cmd.doClick();
            }
        }
    });
        
        txtPass.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Simulate clicking the submit button
                cmd.doClick();
            }
        }
    });
    }
    



    //Self Explanatory
    public void showForgotPass(boolean show){
        if(show){
            forgotPass.setVisible(false);
            login.setVisible(true);
        }else{
            forgotPass.setVisible(true);
            login.setVisible(false);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        forgotPass = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        forgotPass.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout forgotPassLayout = new javax.swing.GroupLayout(forgotPass);
        forgotPass.setLayout(forgotPassLayout);
        forgotPassLayout.setHorizontalGroup(
            forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        forgotPassLayout.setVerticalGroup(
            forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(forgotPass, "card2");
    }// </editor-fold>//GEN-END:initComponents

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel forgotPass;
    private javax.swing.JPanel login;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the owner
     */
    
      
}
