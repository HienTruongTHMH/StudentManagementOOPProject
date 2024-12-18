
package Login_test.Component;

import Login_test.swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;


public class PanelCover extends javax.swing.JPanel {
    
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel titleLable;
    private JLabel descriLabel;
    private JLabel descri1Label;
    private ButtonOutLine button;
    private boolean isLogin;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[Center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }
    
    private void init() {
        titleLable = new JLabel("Welcome back!");
        titleLable.setFont(new Font("sansserif", 1, 30));
        titleLable.setForeground(new Color(245, 245, 245));
        add(titleLable);
        
        descri1Label = new JLabel("Gang gang geng geng");
        descri1Label.setForeground(new Color(245, 245, 245));
        add(descri1Label);
        
        descriLabel = new JLabel("Login with your personal");
        descriLabel.setForeground(new Color(245, 245, 245));
        add(descriLabel);
        
        button = new ButtonOutLine();
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(255, 255, 255));
        button.setText("STUDENT");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
        add(button, "w 60%, h 40");
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
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, new Color(48, 124, 235), 0, getHeight(), new Color(12, 106, 185));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }
    
    public void selectedLeft(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        Login(false);
        layout.setComponentConstraints(titleLable, "pad 0 -" + v + "% 0 0");    
        layout.setComponentConstraints(descriLabel, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descri1Label, "pad 0 -" + v + "% 0 0"); 
    }
    
    public void selectedRight(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        Login(false);
        layout.setComponentConstraints(titleLable, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descriLabel, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descri1Label, "pad 0 -" + v + "% 0 0");
    }
    
    public void LoginLeft(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        Login(true);
        layout.setComponentConstraints(titleLable, "pad 0 " + v + "% 0 " + v + "%"); 
        layout.setComponentConstraints(descriLabel, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descri1Label, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    public void LoginRight(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        Login(true);
        layout.setComponentConstraints(titleLable, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descri1Label, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descriLabel, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    private void Login(boolean login) {
        if(this.isLogin != login) {
            if(login) {
                titleLable.setText("Hello, Friend!");
                descri1Label.setText("ehhehehe");
                descriLabel.setText("Take your grade and feel good of it");
                button.setText("TEACHER");
            } else {
                titleLable.setText("Welcome Back!");
                descri1Label.setText("Gang gang geng geng");
                descriLabel.setText("Login with your personal");
                button.setText("STUDENT");
            } 
            this.isLogin = login;
        }
        layout.setComponentConstraints(descri1Label, "pad 0 0 0 0");  // bỏ padding  
        layout.setComponentConstraints(descriLabel, "pad 0 0 0 0");
        revalidate();  
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
