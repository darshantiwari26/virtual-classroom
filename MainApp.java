import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    CardLayout card = new CardLayout();
    JPanel container = new JPanel(card);

    public String currentRole = null;
    public String currentUser = null;
    public String currentCode = null; // selected class code (teacher or student)

    public WelcomePanel welcome;
    public SignInPanel signIn;
    public LoginPanel login;
    public TeacherDashboard teacherDashboard;
    public StudentDashboard studentDashboard;

    public MainApp() {
        super("Virtual Classroom — Full Multi-Class + Modern UI");
        Storage.init();
        Theme.installUIFont("Segoe UI", 14);

        welcome = new WelcomePanel(this);
        signIn = new SignInPanel(this);
        login = new LoginPanel(this);
        teacherDashboard = new TeacherDashboard(this);
        studentDashboard = new StudentDashboard(this);

        container.add(welcome, "welcome");
        container.add(signIn, "signin");
        container.add(login, "login");
        container.add(teacherDashboard, "teacher");
        container.add(studentDashboard, "student");

        setJMenuBar(buildMenuBar());

        add(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 760);
        setLocationRelativeTo(null);
        Theme.applyAppTheme(this.getRootPane());
        setVisible(true);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu view = new JMenu("View");
        JCheckBoxMenuItem dark = new JCheckBoxMenuItem("Dark Mode");
        dark.setSelected(Theme.isDark());
        dark.addActionListener(e -> {
            Theme.toggle();
            Theme.applyAppTheme(this.getRootPane());
            refreshDashboards();
            SwingUtilities.updateComponentTreeUI(this);
        });
        view.add(dark);
        bar.add(view);
        return bar;
    }

    public void go(String name) { card.show(container, name); }
    public void setSession(String role, String user, String code) {
        currentRole = role; currentUser = user; currentCode = code;
    }
    public void refreshDashboards() {
        if (teacherDashboard != null) teacherDashboard.refresh();
        if (studentDashboard != null) studentDashboard.refresh();
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(MainApp::new); }
}
