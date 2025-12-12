import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class StudentDashboard extends JPanel {
    private final MainApp app;
    private final Sidebar sidebar = new Sidebar();

    private JComboBox<String> codeDropdown;
    private JTabbedPane classTabs;
    private JButton joinBtn;

    public StudentDashboard(MainApp app) {
        this.app = app;
        setLayout(new BorderLayout());
        JButton attendance=new JButton("Mark Attendance"); attendance.addActionListener(e->Storage.markAttendance(app.currentUser, app.currentCode)); add(attendance, BorderLayout.SOUTH);
        JButton logout=new JButton("Logout"); logout.addActionListener(e->app.go("welcome")); add(logout, BorderLayout.NORTH);
        setBorder(new EmptyBorder(8,8,8,8));
        setBackground(Theme.bg());

        // Top bar
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JButton back = new JButton(" Back", new VectorIcon(VectorIcon.Kind.BACK, 16));
        back.addActionListener(e -> app.go("login"));
        JButton menu = new JButton("Menu");
        menu.addActionListener(e -> sidebar.toggle());
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT)); left.setOpaque(false);
        left.add(back); left.add(menu);

        // multi-class controls
        JPanel centerTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); centerTop.setOpaque(false);
        codeDropdown = new JComboBox<>();
        codeDropdown.addActionListener(e -> {
            String sel = (String) codeDropdown.getSelectedItem();
            if (sel != null) {
                app.currentCode = sel;
                selectTabForCode(sel);
                refresh(); // reload lists for selected tab
            }
        });
        joinBtn = new JButton("Join New Class", new VectorIcon(VectorIcon.Kind.PLUS, 16));
        joinBtn.addActionListener(e -> joinNewClass());
        centerTop.add(new JLabel("Class: "));
        centerTop.add(codeDropdown);
        centerTop.add(joinBtn);

        top.add(left, BorderLayout.WEST);
        top.add(centerTop, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        // Sidebar (dynamic: one item per class code)
        add(sidebar, BorderLayout.WEST);

        // Tabs (one tab per class code)
        classTabs = new JTabbedPane();
        add(classTabs, BorderLayout.CENTER);
    }

    private void selectTabForCode(String code) {
        for (int i = 0; i < classTabs.getTabCount(); i++) {
            if (classTabs.getTitleAt(i).equals(code)) {
                classTabs.setSelectedIndex(i);
                return;
            }
        }
    }

    private JPanel buildClassPanel(String code) {
        JPanel grid = new JPanel(new GridLayout(2,2,12,12));
        grid.setOpaque(false);

        // Assignments Card (blue)
        RoundedPanel blue = new RoundedPanel(Theme.primary(), new Color(0x7AB6FF));
        blue.add(new JLabel("Assignments ("+code+")"));
        JList<File> assignmentsList = new JList<>(new DefaultListModel<>());
        JScrollPane asp = new JScrollPane(assignmentsList); asp.setPreferredSize(new Dimension(280,200));
        JButton openA = new JButton("Open");
        blue.add(asp); blue.add(openA);

        // Videos Card (green)
        RoundedPanel green = new RoundedPanel(Theme.success(), new Color(0x8BE29D));
        green.add(new JLabel("Video Lectures ("+code+")"));
        JList<File> videosList = new JList<>(new DefaultListModel<>());
        JScrollPane vsp = new JScrollPane(videosList); vsp.setPreferredSize(new Dimension(280,200));
        JButton openV = new JButton("Open");
        green.add(vsp); green.add(openV);

        // Upload Submissions Card (yellow)
        RoundedPanel yellow = new RoundedPanel(Theme.warn(), new Color(0xFFE9A3));
        yellow.add(new JLabel("Upload Assignment ("+code+")"));
        JButton upload = new GlowButton("Choose & Upload", new VectorIcon(VectorIcon.Kind.UPLOAD,18));
        JList<File> mySubmissionsList = new JList<>(new DefaultListModel<>());
        JScrollPane ssp = new JScrollPane(mySubmissionsList); ssp.setPreferredSize(new Dimension(280,160));
        JButton del = new JButton("Delete", new VectorIcon(VectorIcon.Kind.DELETE,16));
        yellow.add(upload); yellow.add(ssp); yellow.add(del);

        // Notice Card
        RoundedPanel notice = new RoundedPanel(Theme.card(), Theme.card());
        notice.add(new JLabel("Notice (from teacher)"));
        JTextArea noticeView = new JTextArea(6, 24);
        noticeView.setEditable(false);
        notice.add(new JScrollPane(noticeView));

        grid.add(blue); grid.add(green); grid.add(yellow); grid.add(notice);

        // Actions scoped to this code
        openA.addActionListener(e -> {
            File f = assignmentsList.getSelectedValue();
            if (f!=null) FileUtils.openFile(f);
        });
        openV.addActionListener(e -> {
            File f = videosList.getSelectedValue();
            if (f!=null) FileUtils.openFile(f);
        });
        upload.addActionListener(e -> uploadSubmission(code));
        del.addActionListener(e -> deleteSubmission(code, mySubmissionsList));

        // Initial load
        loadListsForCode(code, assignmentsList, videosList, mySubmissionsList, noticeView);

        return grid;
    }

    private void loadListsForCode(String code, JList<File> assignmentsList, JList<File> videosList, JList<File> mySubmissionsList, JTextArea noticeView) {
        File aDir = new File("uploads/" + code + "/assignments");
        DefaultListModel<File> aModel = new DefaultListModel<>();
        for (File f : Storage.listFiles(aDir)) aModel.addElement(f);
        assignmentsList.setModel(aModel);

        File vDir = new File("uploads/" + code + "/videos");
        DefaultListModel<File> vModel = new DefaultListModel<>();
        for (File f : Storage.listFiles(vDir)) vModel.addElement(f);
        videosList.setModel(vModel);

        File myDir = Storage.studentSubmissionDir(code, app.currentUser);
        DefaultListModel<File> sModel = new DefaultListModel<>();
        for (File f : Storage.listFiles(myDir)) sModel.addElement(f);
        mySubmissionsList.setModel(sModel);

        noticeView.setText(Storage.readNotice(code));
    }

    private void uploadSubmission(String code) {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        int res = fc.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File[] files = fc.getSelectedFiles();
            File destDir = Storage.studentSubmissionDir(code, app.currentUser);
            int count = 0;
            for (File f : files) {
                try {
                    Path dest = new File(destDir, f.getName()).toPath();
                    Files.copy(f.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                    count++;
                } catch (IOException ex) { ex.printStackTrace(); }
            }
            JOptionPane.showMessageDialog(this, "Uploaded " + count + " file(s) to class " + code + ".");
            refresh();
        }
    }

    private void deleteSubmission(String code, JList<File> mySubmissionsList) {
        File f = mySubmissionsList.getSelectedValue();
        if (f != null && f.exists()) {
            int c = JOptionPane.showConfirmDialog(this, "Delete selected submission?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                f.delete();
                refresh();
            }
        }
    }

    private void rebuildSidebar(List<String> codes) {
        sidebar.clearItems();
        sidebar.addItem(new JLabel("Classes"));
        for (String c : codes) {
            JButton b = new JButton(c);
            b.addActionListener(e -> {
                codeDropdown.setSelectedItem(c);
                app.currentCode = c;
                selectTabForCode(c);
                refresh();
            });
            sidebar.addItem(b);
        }
        sidebar.revalidate(); sidebar.repaint();
    }

    private void rebuildTabs(List<String> codes) {
        classTabs.removeAll();
        for (String c : codes) {
            classTabs.addTab(c, buildClassPanel(c));
        }
        if (!codes.isEmpty()) {
            String select = app.currentCode != null ? app.currentCode : codes.get(0);
            app.currentCode = select;
            codeDropdown.setSelectedItem(select);
            selectTabForCode(select);
        }
    }

    public void joinNewClass() {
        String code = JOptionPane.showInputDialog(this, "Enter 6-digit class code:");
        if (code == null || code.trim().isEmpty()) return;
        code = code.trim();
        try {
            Storage.addStudentClassCode(app.currentUser, code);
            app.currentCode = code;
            refresh();
            JOptionPane.showMessageDialog(this, "Joined class " + code);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public void refresh() {
        try {
            List<String> codes = Storage.getStudentCodes(app.currentUser);
            // dropdown
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (String c : codes) model.addElement(c);
            codeDropdown.setModel(model);
            // sidebar entries
            rebuildSidebar(codes);
            // tabs
            rebuildTabs(codes);
        } catch (Exception e) {
            // ignore minor errors in refresh
        }
        revalidate(); repaint();
    }
}
