import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GlowButton extends JButton {
    private Color base = new Color(0x2D8CFF);
    public GlowButton(String text, Icon icon) {
        super(text, icon);
        setFocusPainted(false);
        setBackground(base);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10,14,10,14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){ setBackground(base.darker()); }
            public void mouseExited(MouseEvent e){ setBackground(base); }
        });
    }
    public GlowButton(String text){ this(text, null); }
}
