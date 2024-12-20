package DashBoard_test.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class menu extends JComponent {

    private menuEvent event;

    public menuEvent getEvent() {
        return event;
    }

    public void setEvent(menuEvent event) {
        this.event = event;
    }
    private MigLayout layout;
    private String[][] menuItems = new String[][]{
        {"Dashboard"},
        {"Anouncement"},
        {"Personal", "Schedule", "Kết quả học tập", "Phúc Khảo", "Thông tin cá nhân"},
        {"Đăng kí", "Đăng kí tín chỉ", "Lớp học đăng kí", "Chương trình học"},
        {"Đăng Xuất"}
    };

    public menu() {
        init();
    }

    public String[][] getMenuItems() {
        return menuItems; 
    }

    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        //Init MenuItem
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }
    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/DashBoard_test/icon/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
        menuItem item = new menuItem(menuName, index, length > 1);
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addSubMenu(item, index, length, getComponentZOrder(item));
                    } else { // Ẩn Menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    if (event != null) {
                        event.selected(index, 0);
                        //header.updateHeader(menuItems[index][0]);
                    }
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(menuItem item, int index, int length, int indexZorder) {
        JPanel jpanel = new JPanel(new MigLayout("wrap 1, fillx, inset 0 , gapy 0", "fill"));
        jpanel.setName(index + "");
        jpanel.setOpaque(false);
        for (int i = 1; i < length; i++) {
            menuItem subItem = new menuItem(menuItems[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (event != null) {
                        event.selected(index, subItem.getIndex());
                    }
                }
            });
            subItem.initSubMenu(i, length);
            jpanel.add(subItem);
        }
        add(jpanel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        menuAnimation.showMenu(jpanel, item, layout, true);
    }

    private void hideMenu(menuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                menuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    public void setSelectedIndex(int index, int subItem) {
        for (Component com : getComponents()) {
            if (com instanceof menuItem) { // Kiểm tra nếu là menuItem
                menuItem item = (menuItem) com;
                if (item.getIndex() == index) { // Tìm đúng menu
                    item.setSelectedIndex(subItem); // Đặt sub-menu được chọn
                    if (event != null) {
                        event.selected(index, subItem); // Kích hoạt sự kiện nếu có
                    }
                    break;
                }
            } else if (com instanceof JPanel) { // Nếu là JPanel (sub-menu)
                JPanel panel = (JPanel) com;
                if (panel.getName() != null && panel.getName().equals(index + "")) {
                    for (Component subCom : panel.getComponents()) {
                        if (subCom instanceof menuItem) {
                            menuItem subItemMenu = (menuItem) subCom;
                            if (subItemMenu.getSubMenuIndex() == subItem) {
                                subItemMenu.setSelectedIndex(subItem); // Đặt sub-menu
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(48, 124, 235));
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }
}
