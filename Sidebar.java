import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Sidebar extends JPanel {
    private int targetWidth = 220;
    private int currentWidth = 0;
    private final Timer timer;

    public Sidebar() {
        setLayout(new GridLayout(0,1,8,8));
        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        setBackground(Theme.card());
        setPreferredSize(new Dimension(0, 0));
        timer = new Timer(8, (ActionEvent e) -> animate());
    }

    public void addItem(JComponent comp) { add(comp); }
    public void clearItems() { removeAll(); revalidate(); repaint(); }

    public void toggle() {
        if (timer.isRunning()) return;
        if (currentWidth == 0) targetWidth = 220; else targetWidth = 0;
        timer.start();
    }

    private void animate() {
        if (currentWidth < targetWidth) currentWidth += 20;
        else if (currentWidth > targetWidth) currentWidth -= 20;
        if (Math.abs(currentWidth - targetWidth) <= 20) { currentWidth = targetWidth; timer.stop(); }
        setPreferredSize(new Dimension(currentWidth, getHeight()));
        revalidate(); repaint();
    }

    protected void paintComponent(Graphics g) { super.paintComponent(g); setBackground(Theme.card()); }
}
