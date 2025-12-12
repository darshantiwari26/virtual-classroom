import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int radius = 24;
    private Color start;
    private Color end;

    public RoundedPanel(Color start, Color end) {
        this.start = start; this.end = end;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0,0,start, 0,getHeight(), end);
        g2.setPaint(gp);
        g2.fillRoundRect(0,0,getWidth(),getHeight(), radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }
}
