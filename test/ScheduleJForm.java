import Login_test.Connection.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduleJForm extends JFrame {
    private JComboBox<String> yearComboBox;
    private JComboBox<String> semesterComboBox;
    private JComboBox<String> weekComboBox;
    private JTable scheduleTable;
    private DefaultTableModel tableModel;

    public ScheduleJForm() {
        setTitle("Lịch Học");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Top Panel for ComboBoxes
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Year ComboBox
        yearComboBox = new JComboBox<>();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        yearComboBox.addItem(currentYear + "-" + (currentYear + 1));
        topPanel.add(new JLabel("Năm học: "));
        topPanel.add(yearComboBox);

        // Semester ComboBox
        semesterComboBox = new JComboBox<>();
        loadSemesters(); // Load semesters from database
        topPanel.add(new JLabel("Học kỳ: "));
        topPanel.add(semesterComboBox);

        // Week ComboBox
        weekComboBox = new JComboBox<>();
        loadWeeks(); // Load weeks into ComboBox
        weekComboBox.addActionListener(e -> updateSchedule());
        topPanel.add(new JLabel("Tuần: "));
        topPanel.add(weekComboBox);

        // Table for Schedule
        String[] columnNames = {"Thứ/Ngày", "Tiết 1", "Tiết 2", "Tiết 3", "Tiết 4", "Tiết 5", "Tiết 6", "Tiết 7", "Tiết 8", "Tiết 9", "Tiết 10", "Tiết 11", "Tiết 12", "Tiết 13", "Tiết 14", "Tiết 15", "Tiết 16"};
        tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        updateSchedule();
    }

    private void loadSemesters() {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MaHocKy, TenHocKy FROM HocKy")) {

            while (rs.next()) {
                semesterComboBox.addItem(rs.getString("TenHocKy"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadWeeks() {
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 7, 29);
        LocalDate endDate = startDate.plusYears(1).withMonth(7).withDayOfMonth(21);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (!startDate.isAfter(endDate)) {
            LocalDate weekEnd = startDate.plusDays(6);
            weekComboBox.addItem("Tuần " + formatter.format(startDate) + " - " + formatter.format(weekEnd));
            startDate = startDate.plusWeeks(1);
        }
    }

    private void updateSchedule() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Get selected week
        int selectedIndex = weekComboBox.getSelectedIndex();
        if (selectedIndex == -1) return;

        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 7, 29).plusWeeks(selectedIndex);
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            String dayName = "Thứ " + (i + 2) + " (" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";

            // Query schedule data for this date
            Object[] rowData = new Object[17];
            rowData[0] = dayName;
            try (Connection conn = DatabaseConnection.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "SELECT MocThoiGian.Tiet, HocPhan.TenHocPhan, LopHP.PhongHoc FROM LichHoc " +
                         "JOIN MocThoiGian ON LichHoc.MaThoiGian = MocThoiGian.MaThoiGian " +
                         "JOIN HocPhan ON LichHoc.MaHocPhan = HocPhan.MaHocPhan " +
                         "JOIN LopHP ON LichHoc.MaLopHP = LopHP.MaLopHP " +
                         "WHERE LichHoc.NgayHoc = ?")) {
                stmt.setDate(1, java.sql.Date.valueOf(date));
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int period = rs.getInt("Tiet");
                    String subject = rs.getString("TenHocPhan");
                    String room = rs.getString("PhongHoc");
                    rowData[period] = subject + " (" + room + ")";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseConnection.getInstance().connectToDatabase();
                new ScheduleJForm().setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
