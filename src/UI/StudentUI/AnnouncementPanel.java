package UI.StudentUI;

import DashBoard_test.scrollWin11.ScrollBarWin11UI;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class AnnouncementPanel extends JPanel {
public AnnouncementPanel() {
        setLayout(new MigLayout("wrap, fill", "[grow]", "[]10[grow][]"));

        // Tạo JLabel tiêu đề
        JLabel titleLabel = new JLabel("Thông Báo Tới Học Sinh", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, "growx, align center");

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

        // Tùy chỉnh hiển thị của bảng
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(98, 0, 238));
        table.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // STT
        columnModel.getColumn(1).setPreferredWidth(400); // Thông báo
        columnModel.getColumn(2).setPreferredWidth(150); // Người Gửi
        columnModel.getColumn(3).setPreferredWidth(100); // Ngày

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, "grow");
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarWin11UI());

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
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button);
        add(buttonPanel, "dock south");
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
        JComboBox<String> subjectDropdown = new JComboBox<>(new String[]{"Toán", "Lý", "Hóa", "Sinh", "Văn"});
        subjectDropdown.setPreferredSize(fieldSize);
        dialog.add(subjectDropdown, gbc);

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
        contentScroll.getVerticalScrollBar().setUI(new ScrollBarWin11UI());
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
                if (contentArea.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nội dung không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    //MessageAlerts.getInstance().showMessage("Lỗi", "Nội dung không được để trống!", MessageAlerts.MessageType.ERROR);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Đơn phúc khảo đã được gửi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    //MessageAlerts.getInstance().showMessage("Lỗi", "Nội dung không được để trống!", MessageAlerts.MessageType.SUCCESS);
                    dialog.dispose();
                }
            }
        });

        dialog.add(submitButton, gbc);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
