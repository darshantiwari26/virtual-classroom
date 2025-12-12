import javax.swing.*;
import java.awt.*;

public class Theme {
    private static boolean DARK = false;

    public static Color bg() { return DARK ? new Color(0x121212) : new Color(0xFCEFF9); }
    public static Color card() { return DARK ? new Color(0x1E1E1E) : new Color(0xFFD6E8); }
    public static Color text() { return DARK ? new Color(0xEAEAEA) : new Color(0x5A0E49); }
    public static Color primary() { return new Color(0x4A90E2); }
    public static Color success() { return new Color(0xFFC8DD); }
    public static Color warn() { return new Color(0xF5D76E); }

    public static boolean isDark() { return DARK; }
    public static void toggle() { DARK = !DARK; }

    public static void applyAppTheme(JComponent root) {
        root.setBackground(bg()); root.setForeground(text());
        for (Component c : root.getComponents()) applyRec(c);
    }
    private static void applyRec(Component c) {
        c.setBackground(bg()); c.setForeground(text());
        if (c instanceof JComponent) for (Component cc : ((JComponent)c).getComponents()) applyRec(cc);
    }

    public static void installUIFont(String name, int size) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        Font f = new Font(name, Font.PLAIN, size);
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(f));
            }
        }
    }
}
