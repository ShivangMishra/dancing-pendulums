package dancingPendulums;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Pendulum {
    double length;
    Bob bob;
    double theta; // the angle which the bob makes with the vertical
    double v; // velocity at which the bob is currently moving
    double timeElapsed; // total time elapsed since this pendulum

    public Pendulum(double length, double theta, double bobD, Color bobColor) {
        this.length = length;
        this.theta = theta;
        this.timeElapsed = 0;
        this.bob = new Bob(length * Math.sin(theta), length * Math.cos(theta), bobD, bobColor);
    }

    public void draw(Graphics g, int ox, int oy, double scale) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(25, 25, 25));
        g2.drawLine(ox, oy, ox + (int) (bob.x * scale), oy + (int) (bob.y * scale));
        bob.draw(g, ox, oy, scale);
    }

    public void update(double g, double dt) {
        double a = -g * Math.sin(theta) / length;
        v += a * dt;
        theta += v * dt;
        timeElapsed = (timeElapsed + dt);
        bob.x = length * Math.sin(theta);
        bob.y = length * Math.cos(theta);
    }

}
