import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginPanel extends JPanel {
    public LoginPanel(MainApp app) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(16,16,16,16));
        setBackground(Theme.bg());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JButton back = new JButton(" Back", new VectorIcon(VectorIcon.Kind.BACK, 16));
        back.addActionListener(e -> app.go("welcome"));
        top.add(back, BorderLayout.WEST);

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Theme.text());
        top.add(title, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8,8,8,8);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx=0; gc.gridy=0; form.add(new JLabel("Role:"), gc);
        gc.gridx=1; JComboBox<String> role = new JComboBox<>(new String[]{"Teacher","Student"}); form.add(role, gc);
        gc.gridx=0; gc.gridy=1; form.add(new JLabel("Name:"), gc);
        gc.gridx=1; JTextField name = new JTextField(18); form.add(name, gc);
        gc.gridx=0; gc.gridy=2; form.add(new JLabel("Password:"), gc);
        gc.gridx=1; JPasswordField pass = new JPasswordField(18); form.add(pass, gc);

        JPanel actions = new JPanel(); actions.setOpaque(false);
        JButton loginBtn = new GlowButton("Login");
        actions.add(loginBtn);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            String r = Objects.toString(role.getSelectedItem(),"Student");
            String n = name.getText().trim();
            String p = new String(pass.getPassword());
            if (n.isEmpty() || p.isEmpty()) { JOptionPane.showMessageDialog(this,"Name and Password required"); return; }
            try {
                if (r.equals("Teacher")) {
                    String[] row = Storage.teacherAuth(n, p);
                    if (row == null) { JOptionPane.showMessageDialog(this, "Invalid credentials"); return; }
                    java.util.List<String> codes = Storage.getTeacherCodes(n);
                    if (codes.isEmpty()) {
                        // ask to create first class code (6-digit unique)
                        String code = JOptionPane.showInputDialog(this, "Create your 6-digit class code:");
                        if (code == null || code.trim().isEmpty()) return;
                        try {
                            Storage.addTeacherClassCode(n, code.trim());
                            codes = Storage.getTeacherCodes(n);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                            return;
                        }
                    }
                    for (String c : codes) Storage.ensureClassDirs(c);
                    app.setSession("teacher", n, codes.get(0));
                    app.teacherDashboard.refresh();
                    JOptionPane.showMessageDialog(this, "Welcome, "+n+". Selected class: "+codes.get(0));
                    app.go("teacher");
                } else {
                    String[] row = Storage.studentAuth(n, p);
                    if (row == null) { JOptionPane.showMessageDialog(this, "Invalid credentials"); return; }
                    List<String> codes = Storage.getStudentCodes(n);
                    if (codes.isEmpty()) {
                        String code = JOptionPane.showInputDialog(this, "Enter your teacher's 6-digit class code:");
                        if (code == null || code.trim().isEmpty()) return;
                        try {
                            Storage.addStudentClassCode(n, code.trim());
                            codes = Storage.getStudentCodes(n);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                            return;
                        }
                    }
                    for (String c : codes) Storage.ensureClassDirs(c);
                    app.setSession("student", n, codes.get(0));
                    app.studentDashboard.refresh();
                    app.go("student");
                }
            } catch (IOException ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        });
    }
}
