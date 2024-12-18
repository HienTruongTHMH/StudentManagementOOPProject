package UI.StudentUI;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        // Thiết lập giao diện người dùng
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Overview Grade Student");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800); // Kích thước cửa sổ
            
            // Thêm panel `OverViewGradeStudent` vào frame
            OverViewGradeStudent panel = new OverViewGradeStudent();
            frame.add(panel);
            
            // Hiển thị cửa sổ
            frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
            frame.setVisible(true);
        });
    }
}
