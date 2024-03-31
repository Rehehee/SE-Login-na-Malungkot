//Para dun sa nag papop after entering

package Swings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Messages extends javax.swing.JPanel {

    private MessageType messageType = MessageType.SUCCESS;
    private boolean show;
    
    //setShow = visibility of the message
     public void setShow(boolean b) {
        this.show = show;
    }
    
    //isShow = Is the panel being shown currently
    public boolean isShow() {
        return show;
    }
    
    public Messages() {
        initComponents();
        setOpaque(false);
        setVisible(false);
              
    }
    
    //Eto yung tinatawag sa Login Page (See: private void showMessage) para makuha yung mga icons and such
    public void showMessage(MessageType messageType, String message){
        this.messageType = messageType;
        lblMessage.setText(message);
        
        if(messageType==MessageType.SUCCESS){
            lblMessage.setIcon(new ImageIcon(getClass().getResource("/Icons/success.png")));
        }else{
            lblMessage.setIcon(new ImageIcon(getClass().getResource("/Icons/error.png")));
        }   
    }

    //Eto yung mukha bro 
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        if(messageType == MessageType.SUCCESS){
            g2D.setColor(new Color(15,174,37)); 
        }else{
            g2D.setColor(new Color(240,52,53));
        }
        
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2D.fillRect(0, 0, getWidth(), getHeight());
        g2D.setComposite(AlphaComposite.SrcOver);
        g2D.setColor(new Color(255,255,255));
        g2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g);
        
        
    }

   
                
 
    public static enum MessageType {
         SUCCESS, ERROR

}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();

        lblMessage.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}
