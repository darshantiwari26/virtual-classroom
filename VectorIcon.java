import javax.swing.*;
import java.awt.*;

public class VectorIcon implements Icon {
    public enum Kind {UPLOAD, DELETE, BACK, VIDEO, DOC, PLUS}

    private final Kind kind;
    private final int size;

    public VectorIcon(Kind kind, int size) {
        this.kind = kind; this.size = size;
    }
    public int getIconWidth(){ return size; }
    public int getIconHeight(){ return size; }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Theme.text());
        int s=size;
        switch(kind){
            case UPLOAD:
                g2.drawRect(x+s/4, y+s/2, s/2, s/3);
                g2.drawLine(x+s/2, y+s/6, x+s/2, y+s/2);
                g2.drawLine(x+s/2, y+s/6, x+s/3, y+s/3);
                g2.drawLine(x+s/2, y+s/6, x+2*s/3, y+s/3);
                break;
            case DELETE:
                g2.drawRect(x+s/4, y+s/3, s/2, s/2);
                g2.drawLine(x+s/3, y+s/3, x+2*s/3, y+2*s/3);
                g2.drawLine(x+2*s/3, y+s/3, x+s/3, y+2*s/3);
                break;
            case BACK:
                g2.drawLine(x+s/3, y+s/2, x+2*s/3, y+s/2);
                g2.drawLine(x+s/3, y+s/2, x+s/2, y+s/3);
                g2.drawLine(x+s/3, y+s/2, x+s/2, y+2*s/3);
                break;
            case VIDEO:
                g2.drawRect(x+s/5, y+s/4, s*3/5, s/2);
                Polygon p = new Polygon();
                p.addPoint(x+s/2, y+s/3);
                p.addPoint(x+2*s/3, y+s/2);
                p.addPoint(x+s/2, y+2*s/3);
                g2.fill(p);
                break;
            case DOC:
                g2.drawRect(x+s/4, y+s/4, s/2, s/2);
                g2.drawLine(x+s/3, y+s/3, x+2*s/3, y+s/3);
                g2.drawLine(x+s/3, y+s/2, x+2*s/3, y+s/2);
                break;
            case PLUS:
                g2.drawLine(x+s/2, y+s/4, x+s/2, y+3*s/4);
                g2.drawLine(x+s/4, y+s/2, x+3*s/4, y+s/2);
                break;
        }
        g2.dispose();
    }
}
