//Wag nalang munang burahin to, para sa Email Verification kasi to, pampaganda lang ganun

package Swings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

public class PanelLoading extends javax.swing.JPanel {

    public PanelLoading() {
        initComponents();
        setOpaque(false);
        setFocusCycleRoot(true);
        setVisible(false);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {  
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g; 
        g2D.setColor(new Color(255, 255, 255));
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2D.fillRect(0, 0, getWidth(), getHeight());
        g2D.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(g); 
        
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1100, 650));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loading.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
