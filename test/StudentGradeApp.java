import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeApp {
    private JFrame frame;
    private JTable summaryTable, detailTable;
    private JComboBox<String> yearComboBox, semesterComboBox;
    private JPanel detailPanel;
    private JScrollPane detailScrollPane;
    private JTable evaluationTable;

    public StudentGradeApp() {
        initializeUI();
    }

    private void initializeUI() {
        // Tạo Frame
        frame = new JFrame("Student Grade Dashboard");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Tiêu đề chính
        JLabel titleLabel = new JLabel("Tổng hợp kết quả học tập và rèn luyện", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 51, 153));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Bảng tổng hợp kết quả học tập
        summaryTable = createTable(new String[]{
                "Học kỳ", "Số tín chỉ đăng ký", "Số tín chỉ tích lũy", "GPA", "CPA",
                "Điểm TBC học bổng", "Điểm rèn luyện", "Xếp loại học tập"
        }, new Object[][]{
                {"Học kỳ 1, 2024-2025", "20.0", "20.0", "3.5", "3.5", "8.9", "89", "Giỏi"},
                {"Học kỳ 2, 2024-2025", "18.0", "38.0", "3.2", "3.3", "8.5", "88", "Giỏi"},
                {"Học kỳ 3, 2024-2025", "24.0", "62.0", "3.7", "3.4", "9.2", "90", "Giỏi"},
                {"Học kỳ 1, 2025-2026", "20.0", "82.0", "3.6", "3.5", "8.8", "89", "Giỏi"}
        });
        frame.add(new JScrollPane(summaryTable), BorderLayout.CENTER);

        // Panel chi tiết kết quả học tập
        detailPanel = new JPanel();
        detailPanel.setLayout(new BorderLayout());
        
        JLabel detailTitle = new JLabel("Chi tiết kết quả học tập", SwingConstants.CENTER);
        detailTitle.setFont(new Font("Arial", Font.BOLD, 16));
        detailTitle.setForeground(new Color(0, 51, 153));
        detailPanel.add(detailTitle, BorderLayout.NORTH);

        // ComboBox chọn năm học và học kỳ
        JPanel comboBoxPanel = new JPanel();
        yearComboBox = new JComboBox<>(new String[]{"2024 - 2025", "2025 - 2026", "2026 - 2027"});
        semesterComboBox = new JComboBox<>(new String[]{"Học kỳ 1", "Học kỳ 2", "Học kỳ 3"});

        comboBoxPanel.add(new JLabel("Năm học:"));
        comboBoxPanel.add(yearComboBox);
        comboBoxPanel.add(new JLabel("Học kỳ:"));
        comboBoxPanel.add(semesterComboBox);
        detailPanel.add(comboBoxPanel, BorderLayout.CENTER);

        // Bảng chi tiết kết quả học tập
        detailTable = createTable(new String[]{
                "STT", "Mã học phần", "Tên học phần", "Số tín chỉ", "Điểm CC", "Điểm BT",
                "Điểm CH", "Điểm DA", "Điểm GK", "Điểm CK", "Điểm T10", "Điểm chữ", "Kiểm tra"
        }, new Object[][]{
                {"1", "BEB23122", "Philosophy of Marxism and Leninism", "3.0", "7.8", "7.8", "9.3", "8.2", "8.5", "8.0", "B", "✓"},
                {"2", "CSB25017", "Object Oriented Programming", "5.0", "7.8", "7.8", "8.0", "7.5", "8.5", "8.2", "A", ""},
                {"3", "DSB13001", "Probabilities and Statistics", "4.0", "7.8", "7.8", "8.0", "7.8", "8.5", "8.2", "A", ""},
                {"4", "CSB25020", "Math for Computer Programming", "5.0", "7.8", "7.8", "8.0", "7.8", "8.5", "8.2", "A", "✓"},
        });
        detailScrollPane = new JScrollPane(detailTable);
        detailPanel.add(detailScrollPane, BorderLayout.SOUTH);

        frame.add(detailPanel, BorderLayout.SOUTH);

        // Bảng Thang điểm đánh giá
        JPanel evaluationPanel = new JPanel(new BorderLayout());
        JLabel evaluationTitle = new JLabel("Thang Điểm Đánh Giá", SwingConstants.CENTER);
        evaluationTitle.setFont(new Font("Arial", Font.BOLD, 14));
        evaluationPanel.add(evaluationTitle, BorderLayout.NORTH);
        
        evaluationTable = createTable(new String[]{"Điểm chữ", "Điểm số", "Điểm thang 10", "Xếp loại"},
                new Object[][]{
                        {"A", "4", "8.5 - 10", "Giỏi"},
                        {"B", "3", "7.0 - 8.4", "Khá"},
                        {"C", "2", "5.5 - 6.9", "Trung Bình"},
                        {"D", "1", "4.0 - 5.4", "Trung Bình Yếu"},
                        {"F", "0", "< 4.0", "Kém"}
                });
        evaluationPanel.add(new JScrollPane(evaluationTable), BorderLayout.CENTER);
        frame.add(evaluationPanel, BorderLayout.EAST);

        // Ghi chú
        JLabel noteLabel = new JLabel("Ghi chú: CC - Chuyên cần, BT - Bài tập, CH - Chuyên đề, DA - Đồ án, GK - Giữa kỳ, CK - Cuối kỳ");
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(noteLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JTable createTable(String[] columnNames, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(174, 198, 207));
        table.setRowHeight(25);
        return table;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeApp());
    }
}
