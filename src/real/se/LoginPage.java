//bg = name of the JLayeredPane
package real.se;

import Models.ModelMessage;
import Services.ServiceMail;
import Services.ServiceUser;
import Swings.Messages;
import Swings.PanelCover;
import Swings.PanelLoading;
import Swings.PanelLoginandForgot;
import Swings.PanelVerifyCode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.text.html.parser.DTDConstants;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
public class LoginPage extends javax.swing.JFrame {
    //Variable Declarations 
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US)); //para sa decimal pattern lang to (See: FractionCover)
    private MigLayout layout; //Layout para sa positions and ito yung gamit ko para maging responsive yung Logo 
    private PanelCover cover; 
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private PanelLoginandForgot loginAndForgot;
    private boolean isLogin; 
    private final double addSize = 40; //For computation  (See: if conditions in timing event)
    private final double coverSize = 60; //size nung panel ng pic 
    private final double loginSize = 40; //size nung other side ng panel (yung walang pic)
    private ServiceUser service;
    
    
    public LoginPage() {
        initComponents();
        init();
        
    }

    private void init(){
        service = new ServiceUser("E:\\Netbeans\\UserCreds.txt");
        layout = new MigLayout("fill, insets 0"); //insets 0, meaning sagad hanggang dulo 
        cover = new PanelCover();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode();
        loginAndForgot = new PanelLoginandForgot();
        
        //Timing Target is the animation code 
        TimingTarget target= new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double size = coverSize;
                double fractionLogin; 
                
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                
                if(isLogin){
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                }else{
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                }
                
                if(fraction >= 0.5f){
                    loginAndForgot.showForgotPass(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndForgot, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
            
        };
        
        Animator animator = new Animator(800, target); //timing of the animation (Change the numbers accordingly) Note: Number is in milliseconds
        animator.setAcceleration(0.5f);  
        animator.setDeceleration(0.5f);
        animator.setResolution(0); //Code for smooth transition 
        
        
        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(cover, "width "+ coverSize + "%, pos 0al 0 n 100%"); // 0al = right edge of the window and 100% meaning yung full height ng window
        bg.add(loginAndForgot, "width " + loginSize + "%, pos 1al 0 n 100%"); // 1al = left edge, 100% = full height 
        
        
        //Prevents multiple instance of animations. The animation will only start if and only if the animator is not running
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        });
      
    }
    
    //Forgot Password Functionality
    public void forgotPass(String email, String newPassword, String confirmPassword) {
    if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
        showMessage(Messages.MessageType.ERROR, "Please fill out everything");
        return; // Exit the method early
    }
    
    boolean resetSuccessful = service.resetPassword(email, newPassword, confirmPassword);
    if (resetSuccessful) {
        // Password reset successful
        showMessage(Messages.MessageType.SUCCESS, "Password reset successful!");
    } else if(!newPassword.equals(confirmPassword)){
        showMessage(Messages.MessageType.ERROR, "Password do not match!"); 
    }else {
        // Password reset failed
        showMessage(Messages.MessageType.ERROR, "This is not the Email of the owner");
    }
}

    //LOGIN FUNCTIONALITY
   public void login(String email, String enteredPassword) {
     // Check if email or password is empty
    if (email.isEmpty() || enteredPassword.isEmpty()) {
        showMessage(Messages.MessageType.ERROR, "Don't leave any blanks");
        return; // Exit the method early
    }  
       
    service = new ServiceUser("E:\\Netbeans\\UserCreds.txt"); // Initialize service here
    boolean isAuthenticated = service.authenticate(email, enteredPassword);
    if (isAuthenticated) {
        showMessage(Messages.MessageType.SUCCESS, "Login successful!");
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        timer.setRepeats(false);
        timer.start();
    }else {
        showMessage(Messages.MessageType.ERROR, "Incorrect email or password!");               
    }
}



    //SHOW MESSAGE FUNCTIONALITY
    private void showMessage(Messages.MessageType messageType, String message) {
        Messages ms = new Messages(); //Gumawa ng instance ng Messages na class (wow instance big word)
        ms.showMessage(messageType, message); // Dito tinawag yung showMessage method dun sa Messages class
        
        //Animation event (Idk bakit di nag didisappear pero baka sakin lang yun)
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        
        //Supposed to be para sa disappearance to after magpakita ng error or success message
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
