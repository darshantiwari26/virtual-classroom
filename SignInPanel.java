import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class SignInPanel extends JPanel {
    public SignInPanel(MainApp app) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(16,16,16,16));
        setBackground(Theme.bg());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JButton back = new JButton(" Back", new VectorIcon(VectorIcon.Kind.BACK, 16));
        back.addActionListener(e -> app.go("welcome"));
        top.add(back, BorderLayout.WEST);

        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
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
        JButton submit = new GlowButton("Create Account", new VectorIcon(VectorIcon.Kind.UPLOAD, 18));
        JButton toLogin = new JButton("Go to Login");
        actions.add(submit); actions.add(toLogin);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        toLogin.addActionListener(e -> app.go("login"));

        submit.addActionListener(e -> {
            String r = Objects.toString(role.getSelectedItem(),"Student");
            String n = name.getText().trim();
            String p = new String(pass.getPassword());
            if (n.isEmpty() || p.isEmpty()) { JOptionPane.showMessageDialog(this,"Name and Password required"); return; }
            try {
                if (r.equals("Teacher")) {
                    if (Storage.teacherExists(n)) { JOptionPane.showMessageDialog(this, "Teacher already exists. Login instead."); return; }
                    Storage.createTeacher(n, p);
                    app.setSession("teacher", n, "");
                    JOptionPane.showMessageDialog(this, "Teacher created. Please login and create your first class.");
                    app.go("login");
                } else {
                    if (Storage.studentExists(n)) { JOptionPane.showMessageDialog(this, "Student already exists. Login instead."); return; }
                    Storage.createStudent(n, p);
                    JOptionPane.showMessageDialog(this, "Student created. Please login and join using your teacher's code(s).");
                    app.go("login");
                }
            } catch (IOException ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        });
    }
}
