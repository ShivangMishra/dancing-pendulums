package dancingPendulums;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A simple Object Oriented Pendulum Waves simulation.  
 * Based on a Pendulum Waves Demonstration at Harvard University - https://sciencedemonstrations.fas.harvard.edu/presentations/pendulum-waves
 *
 * @author Shivang Mishra
 */
public class DancingPendulums extends JPanel {
    private Timer timer; // timer for updating the simulation as well as the GUI
    ArrayList<Pendulum> pendulums;
    ArrayList<Color> colors; // for bobs

    /*
     * Note that in this simulation, the quantities are not necessarily in SI units or to scale.
     * This can be improved in future commits, but as of now, it's not the focus of this simple project.
     * */
    final double g = 9.8; // acceleration due to gravity.
    final double dt = 0.5; // shortest time interval

    int n = 40; // number of pendulums
    int f0 = 25; // frequency of the longest pendulum
    double l0 = 500; // length of the longest pendulum
    double theta = -Math.PI / 8; // initial vertical angle
    double bobD = 10; // diameter of each bob

    // origin coordinates
    int ox = 540;
    int oy = -400;
    // oy is negative so that more pendulum bobs can be actually drawn inside the panel.
    // We are not interested in seeing the suspension point and the strings(the physics ones).

    public static void main(String[] args) {
        DancingPendulums p = new DancingPendulums();
        JFrame window = new JFrame();
        window.setTitle("Dancing Pendulums - A Pendulum waves simulation, Shivang M.");
        window.setContentPane(p);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(1080, 720));
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public DancingPendulums() {
        setBackground(new Color(16, 16, 16)); // background color is kept separate from the bob colors.

        colors = new ArrayList<>();
        colors.add(new Color(43, 130, 201));
        colors.add(new Color(44, 202, 144));
        colors.add(new Color(245, 238, 158));
        colors.add(new Color(255, 238, 136));
        colors.add(new Color(251, 185, 65));
        colors.add(new Color(251, 96, 66));
        colors.add(new Color(216, 17, 89));

        pendulums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Color color = colors.get(i % colors.size());
            /*
             * Each successive pendulum performs more oscillations than the previous pendulum.
             * Length of a pendulum is inversely proportional to the square of its frequency.
             * */
            double length = l0 * 625f / ((f0 + i * 0.3) * (f0 + i * 0.3));
            pendulums.add(new Pendulum(length, theta, bobD, color));
        }
        // init the update timer
        timer = new Timer(25, e -> {
            for (Pendulum p : pendulums) {
                p.update(g, dt);
                repaint();
            }
        });
        timer.start();
    }

    /**
     * Updates the GUI.
     * It's a good practice to keep all the 'drawing' code in this function.
     *
     * @param g graphics context
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double scale = 2; // to suit the GUI
        for (Pendulum p : pendulums) {
            p.draw(g, ox, oy, scale);
        }
    }
}
