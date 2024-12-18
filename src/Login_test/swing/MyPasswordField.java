package Login_test.swing;

import static com.sun.java.accessibility.util.SwingEventMonitor.addDocumentListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyPasswordField extends JPasswordField {

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    private boolean isPasswordVisible = false;
    private final ImageIcon eyeOpenIcon = new ImageIcon("/Login_test/icon/eye.png"); //Đường dẫn đến biểu tượng hiển thị mật khẩu  
    private final ImageIcon eyeClosedIcon = new ImageIcon("/Login_test/icon/eye_hide.png"); //Đường dẫn đến biểu tượng ẩn mật khẩu

    public MyPasswordField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));

        addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                repaint();
            }
        });

        //Thêm MouseListener để xử lý việc nhấp vào biểu tượng hụp  
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isMouseOverSuffixIcon(e)) {
                    togglePasswordVisibility(); //Gọi phương thức để đổi trạng thái hiển thị mật khẩu  
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 240, 247));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        super.paintComponent(g);
        paintIcon(g);
        //super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getPassword().length == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = isPasswordVisible ? eyeOpenIcon.getImage() : eyeClosedIcon.getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }

    private void initBorder() {
        int left = 15;
        int right = 15;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            setEchoChar('\u0000'); // Hiển thị mật khẩu  
        } else {
            setEchoChar('*'); //Ẩn mật khẩu  
        }
        repaint(); //Làm mới trường để cập nhật biểu tượng  
    }

    //Phương thức kiểm tra chuột có nằm trên biểu tượng không   
    private boolean isMouseOverSuffixIcon(MouseEvent e) {
        int iconWidth = suffixIcon != null ? suffixIcon.getIconWidth() : 0;
        return e.getX() > (getWidth() - iconWidth - 10) && e.getX() < (getWidth() - 10);
    }

}
