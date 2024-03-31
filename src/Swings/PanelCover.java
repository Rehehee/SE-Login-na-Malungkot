//Landing Page Code to

package Swings;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import Swings.Button;
import java.text.DecimalFormat;

public class PanelCover extends javax.swing.JPanel {
    
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private BufferedImage image; //Used for holding and loading image from a file (See: loadImage()) 
    private ActionListener event;
    private MigLayout layout;
    private Boolean isLogin = false;
    private Button button;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        loadImage();
        
        layout = new MigLayout("wrap, fill", "[center]" , "push[]-500[]push");
        setLayout(layout);
        
        init();
    }
   
    //Code para makita yung button sa bottom left and bigyan ng event after click yun 
    private void init(){
        
        ImageIcon btnIcon = new ImageIcon(getClass().getResource("/Assets/btnForgotPass.png"));
        button = new Button(btnIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login(true);
                event.actionPerformed(ae);
            }
        });
        add(button, "w 70%, h 40"); 
    }
     
    //loading of image
    private void loadImage() {
        try {
            image = ImageIO.read(new File("E:\\Netbeans\\Image Resources\\Client's Logo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

       
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; //Graphics Class so I can use drawImage method
        
        //If statement to see, if there is an image stored 
        //If there is an image, draw that shit equal to the width and height of the panel 
        if (image != null) {
            g2D.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    public void addEvent(ActionListener event){
        this.event = event;
    }
    
   //This will execute once we Clicked at the button 
   private void login(boolean login) {
    if (this.isLogin != login) {
        if (login) {
            ImageIcon btnIcon = new ImageIcon(getClass().getResource("/Assets/bckToLogin.png"));
            button.setIcon(btnIcon);
        } else {
            ImageIcon btnIcon = new ImageIcon(getClass().getResource("/Assets/btnForgotPass.png"));
            button.setIcon(btnIcon);
        }
        this.isLogin = login;
    } else {
        // Toggle login state
        this.isLogin = !this.isLogin;
        // Change button icon based on login state
        if (this.isLogin) {
            ImageIcon btnIcon = new ImageIcon(getClass().getResource("/Assets/bckToLogin.png"));
            button.setIcon(btnIcon);
        } else {
            ImageIcon btnIcon = new ImageIcon(getClass().getResource("/Assets/btnForgotPass.png"));
            button.setIcon(btnIcon);
        }
    }
}


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

