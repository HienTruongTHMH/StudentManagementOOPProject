import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementApp extends JPanel {

    public AnnouncementApp() {
        setLayout(new BorderLayout());

        // Tạo JLabel tiêu đề
        JLabel titleLabel = new JLabel("THÔNG BÁO TỚI LỚP HỌC PHẦN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng thông báo
        String[] columnNames = {"STT", "Thông báo", "Người Gửi", "Ngày"};
        Object[][] data = {
                {"1", "Điểm đã được cập nhật", "Phòng Đào Tạo", "12/12/2024"},
                {"2", "..............", "..............", ".............."},
                {"3", "..............", "..............", ".............."}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(400); // Điều chỉnh độ rộng cột "Thông báo"
        JScrollPane scrollPane = new JScrollPane(table);

        // Nút "Gửi Phúc Khảo"
        JButton button = new JButton("Gửi Phúc Khảo");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(98, 0, 238));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị form phúc khảo khi bấm nút
                showReevaluationForm();
            }
        });

        // Panel chứa nút bấm
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        // Thêm các thành phần vào panel chính
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showReevaluationForm() {
        // Tạo JDialog cho form phúc khảo
        JDialog dialog = new JDialog((Frame) null, "Đơn Phúc Khảo", true);
        dialog.setSize(400, 350);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(250, 30);

        // Họ và Tên
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Họ và Tên: ");
        dialog.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(fieldSize);
        dialog.add(nameField, gbc);

        // MSSV
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel mssvLabel = new JLabel("MSSV: ");
        dialog.add(mssvLabel, gbc);

        gbc.gridx = 1;
        JTextField mssvField = new JTextField();
        mssvField.setPreferredSize(fieldSize);
        dialog.add(mssvField, gbc);

        // Môn học
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel subjectLabel = new JLabel("Môn học: ");
        dialog.add(subjectLabel, gbc);

        gbc.gridx = 1;
        JTextField subjectField = new JTextField();
        subjectField.setPreferredSize(fieldSize);
        dialog.add(subjectField, gbc);

        // Nội dung
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JLabel contentLabel = new JLabel("Nội dung: ");
        dialog.add(contentLabel, gbc);

        gbc.gridx = 1;
        JTextArea contentArea = new JTextArea(5, 20);
        JScrollPane contentScroll = new JScrollPane(contentArea);
        contentScroll.setPreferredSize(new Dimension(250, 80));
        dialog.add(contentScroll, gbc);

        // Nút Gửi
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JButton submitButton = new JButton("Gửi");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(98, 0, 238));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi bấm gửi (có thể lưu thông tin hoặc hiển thị thông báo)
                JOptionPane.showMessageDialog(dialog, "Đơn phúc khảo đã được gửi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });

        dialog.add(submitButton, gbc);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
