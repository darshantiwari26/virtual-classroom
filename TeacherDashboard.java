import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class TeacherDashboard extends JPanel {
    private final MainApp app;
    private final Sidebar sidebar = new Sidebar();

    private JComboBox<String> codeDropdown;
    private JButton newClassBtn;
    private JTextArea noticeArea;

    private JList<File> assignmentsList;
    private JList<File> videosList;
    private JList<File> submissionsList;

    public TeacherDashboard(MainApp app) {
        this.app = app;
        setLayout(new BorderLayout());
        JButton logout=new JButton("Logout"); logout.addActionListener(e->app.go("welcome")); add(logout, BorderLayout.NORTH);
        setBorder(new EmptyBorder(8,8,8,8));
        setBackground(Theme.bg());

        // Top Bar
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JButton back = new JButton(" Back", new VectorIcon(VectorIcon.Kind.BACK, 16));
        back.addActionListener(e -> app.go("login"));

        // class controls
        JPanel centerTop = new JPanel(new FlowLayout(FlowLayout.CENTER)); centerTop.setOpaque(false);
        codeDropdown = new JComboBox<>();
        codeDropdown.addActionListener(e -> {
            String sel = (String) codeDropdown.getSelectedItem();
            if (sel != null) {
                app.currentCode = sel;
                refresh();
            }
        });
        newClassBtn = new JButton("Create New Class", new VectorIcon(VectorIcon.Kind.PLUS, 16));
        newClassBtn.addActionListener(e -> createNewClass());
        centerTop.add(new JLabel("Class: "));
        centerTop.add(codeDropdown);
        centerTop.add(newClassBtn);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT)); left.setOpaque(false);
        left.add(back);
        top.add(left, BorderLayout.WEST);
        top.add(centerTop, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        // Sidebar content (dynamic per class)
        add(sidebar, BorderLayout.WEST);

        // Main content (cards)
        JPanel center = new JPanel(new GridLayout(2,2,12,12));
        center.setOpaque(false);

        // Blue Card: Upload + View/Delete Assignments
        RoundedPanel blue = new RoundedPanel(Theme.primary(), new Color(0x7AB6FF));
        blue.add(new JLabel("Assignments"));
        JButton uploadAssign = new GlowButton("Upload", new VectorIcon(VectorIcon.Kind.UPLOAD, 18));
        blue.add(uploadAssign);
        assignmentsList = new JList<>(new DefaultListModel<>());
        JScrollPane asp = new JScrollPane(assignmentsList); asp.setPreferredSize(new Dimension(280,200));
        blue.add(asp);
        JButton openA = new JButton("Open"); JButton delA = new JButton("Delete", new VectorIcon(VectorIcon.Kind.DELETE,16));
        JPanel aBtns = new JPanel(); aBtns.setOpaque(false); aBtns.add(openA); aBtns.add(delA);
        blue.add(aBtns);

        // Green Card: Upload + View/Delete Videos
        RoundedPanel green = new RoundedPanel(Theme.success(), new Color(0x8BE29D));
        green.add(new JLabel("Video Lectures"));
        JButton uploadVideo = new GlowButton("Upload", new VectorIcon(VectorIcon.Kind.UPLOAD, 18));
        green.add(uploadVideo);
        videosList = new JList<>(new DefaultListModel<>());
        JScrollPane vsp = new JScrollPane(videosList); vsp.setPreferredSize(new Dimension(280,200));
        green.add(vsp);
        JButton openV = new JButton("Open"); JButton delV = new JButton("Delete", new VectorIcon(VectorIcon.Kind.DELETE,16));
        JPanel vBtns = new JPanel(); vBtns.setOpaque(false); vBtns.add(openV); vBtns.add(delV);
        green.add(vBtns);

        // Yellow Card: Student Submissions
        RoundedPanel yellow = new RoundedPanel(Theme.warn(), new Color(0xFFE9A3));
        yellow.add(new JLabel("Student Submissions"));
        submissionsList = new JList<>(new DefaultListModel<>());
        JScrollPane ssp = new JScrollPane(submissionsList); ssp.setPreferredSize(new Dimension(280,260));
        yellow.add(ssp);
        JButton openS = new JButton("Open");
        yellow.add(openS);

        // Notice Card
        RoundedPanel notice = new RoundedPanel(Theme.card(), Theme.card());
        notice.add(new JLabel("Notice (visible to all)"));
        noticeArea = new JTextArea(6, 24);
        noticeArea.setLineWrap(true); noticeArea.setWrapStyleWord(true);
        notice.add(new JScrollPane(noticeArea));
        JButton post = new GlowButton("Post Notice");
        notice.add(post);

        center.add(blue); center.add(green); center.add(yellow); center.add(notice);

        add(center, BorderLayout.CENTER);

        // Actions
        uploadAssign.addActionListener(e -> uploadTo("assignments"));
        uploadVideo.addActionListener(e -> uploadTo("videos"));
        openA.addActionListener(e -> { File f = assignmentsList.getSelectedValue(); if (f!=null) FileUtils.openFile(f); });
        openV.addActionListener(e -> { File f = videosList.getSelectedValue(); if (f!=null) FileUtils.openFile(f); });
        openS.addActionListener(e -> { File f = submissionsList.getSelectedValue(); if (f!=null) FileUtils.openFile(f); });
        delA.addActionListener(e -> deleteSelected(assignmentsList));
        delV.addActionListener(e -> deleteSelected(videosList));
        post.addActionListener(e -> { Storage.writeNotice(app.currentCode, noticeArea.getText()); JOptionPane.showMessageDialog(this,"Notice posted."); });
    }

    private void createNewClass() {
        String code = JOptionPane.showInputDialog(this, "Enter a unique 6-digit class code:");
        if (code == null || code.trim().isEmpty()) return;
        try {
            Storage.addTeacherClassCode(app.currentUser, code.trim());
            app.currentCode = code.trim();
            refresh();
            JOptionPane.showMessageDialog(this, "Class "+code+" created.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteSelected(JList<File> list) {
        File f = list.getSelectedValue();
        if (f != null && f.exists()) {
            int c = JOptionPane.showConfirmDialog(this, "Delete selected file?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                f.delete();
                refresh();
            }
        }
    }

    private void uploadTo(String bucket) {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        int res = fc.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File[] files = fc.getSelectedFiles();
            File destDir = new File("uploads/" + app.currentCode + "/" + bucket);
            destDir.mkdirs();
            int count = 0;
            for (File f : files) {
                try {
                    Path dest = new File(destDir, f.getName()).toPath();
                    Files.copy(f.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                    count++;
                } catch (IOException ex) { ex.printStackTrace(); }
            }
            JOptionPane.showMessageDialog(this, "Uploaded " + count + " file(s) to " + bucket + ".");
            refresh();
        }
    }

    public void refresh() {
        try {
            java.util.List<String> codes = Storage.getTeacherCodes(app.currentUser);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (String c : codes) model.addElement(c);
            codeDropdown.setModel(model);
            if (!codes.isEmpty()) {
                String select = app.currentCode != null ? app.currentCode : codes.get(0);
                app.currentCode = select;
                codeDropdown.setSelectedItem(select);
            }
        } catch (Exception e) {}

        // sidebar
        sidebar.clearItems();
        sidebar.addItem(new JLabel("My Classes"));
        try {
            for (String c : Storage.getTeacherCodes(app.currentUser)) {
                JButton b = new JButton(c);
                b.addActionListener(e -> { app.currentCode = c; codeDropdown.setSelectedItem(c); refresh(); });
                sidebar.addItem(b);
            }
        } catch (Exception ignored) {}

        // lists + notice
        if (app.currentCode == null || app.currentCode.isEmpty()) return;

        File aDir = new File("uploads/" + app.currentCode + "/assignments");
        DefaultListModel<File> aModel = new DefaultListModel<>();
        for (File f : Storage.listFiles(aDir)) aModel.addElement(f);
        assignmentsList.setModel(aModel);

        File vDir = new File("uploads/" + app.currentCode + "/videos");
        DefaultListModel<File> vModel = new DefaultListModel<>();
        for (File f : Storage.listFiles(vDir)) vModel.addElement(f);
        videosList.setModel(vModel);

        File subsRoot = new File("uploads/" + app.currentCode + "/submissions");
        DefaultListModel<File> sModel = new DefaultListModel<>();
        if (subsRoot.exists()) {
            for (File studentDir : subsRoot.listFiles()) {
                if (studentDir.isDirectory()) {
                    for (File f : Storage.listFiles(studentDir)) sModel.addElement(f);
                }
            }
        }
        submissionsList.setModel(sModel);

        noticeArea.setText(Storage.readNotice(app.currentCode));

        revalidate(); repaint();
    }
}
