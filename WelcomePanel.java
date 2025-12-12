import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WelcomePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new GradientPaint(0,0,new Color(0xFFAFCC), getWidth(), getHeight(), new Color(0xBDE0FE)));
        g2.fillRect(0,0,getWidth(),getHeight());
    }
    
    public WelcomePanel(MainApp app) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(16,16,16,16));
        setBackground(Theme.bg());

        JLabel title = new JLabel("Virtual Classroom", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Theme.text());
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1,2,16,16));
        center.setOpaque(false);

        RoundedPanel left = new RoundedPanel(Theme.primary(), new Color(0x7AB6FF));
        RoundedPanel right = new RoundedPanel(Theme.success(), new Color(0x8BE29D));

        JButton signIn = new GlowButton("Sign In", new VectorIcon(VectorIcon.Kind.DOC, 18));
        JButton login = new GlowButton("Login", new VectorIcon(VectorIcon.Kind.BACK, 18));
        signIn.addActionListener(e -> app.go("signin"));
        login.addActionListener(e -> app.go("login"));

        left.add(new JLabel("New here? Create an account")); left.add(Box.createVerticalStrut(8)); left.add(signIn);
        right.add(new JLabel("Already registered? Login")); right.add(Box.createVerticalStrut(8)); right.add(login);

        center.add(left); center.add(right);
        add(center, BorderLayout.CENTER);
    }
}
