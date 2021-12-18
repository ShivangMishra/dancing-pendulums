package dancingPendulums;

import java.awt.*;

public class Bob {
    double x, y;
    double d;
    Color color;
    public Bob(double x, double y, double d, Color color) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.color = color;
    }
    public void draw(Graphics g, int ox, int oy, double scale) {
        Graphics2D g2 = (Graphics2D) g;
        Color c = g.getColor();
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.fillOval(ox + (int) ((x - d / 2) * scale), oy + (int) ((y - d / 2) * scale), (int) (d * scale), (int) (d * scale));
        g2.setColor(color.darker());
        g2.drawOval(ox + (int) ((x - d / 2) * scale), oy + (int) ((y - d / 2) * scale), (int) (d * scale), (int) (d * scale));
    }
}
