import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BallGameSimple extends JPanel implements ActionListener, MouseListener {

    class Ball {
        int x, y, size, dx, dy;
        Color color;

        Ball(int x, int y, int size, int dx, int dy, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }

        void move() {
            x += dx;
            y += dy;
            if (x < 0 || x + size > getWidth()) dx = -dx;
            if (y < 0 || y + size > getHeight()) dy = -dy;
        }

        boolean isClicked(int mx, int my) {
            return mx >= x && mx <= x + size && my >= y && my <= y + size;
        }
    }

    ArrayList<Ball> balls;
    javax.swing.Timer timer;
    Random random;

    BallGameSimple() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        addMouseListener(this);
        balls = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < 10; i++) {
            balls.add(makeBall());
        }

        timer = new javax.swing.Timer(20, this);
        timer.start();
    }

    Ball makeBall() {
        int size = random.nextInt(20) + 10;
        int x = random.nextInt(800 - size);
        int y = random.nextInt(600 - size);
        int dx = random.nextInt(5) + 1;
        int dy = random.nextInt(5) + 1;
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return new Ball(x, y, size, dx, dy, color);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball b : balls) {
            g.setColor(b.color);
            g.fillOval(b.x, b.y, b.size, b.size);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (Ball b : balls) {
            b.move();
        }
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        ArrayList<Ball> newBalls = new ArrayList<>();
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            if (b.isClicked(e.getX(), e.getY())) {
                balls.remove(i);
                for (int j = 0; j < 3; j++) {
                    newBalls.add(makeBall());
                }
                break;
            }
        }
        balls.addAll(newBalls);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Game Simple");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BallGameSimple());
        frame.pack();
        frame.setVisible(true);
    }
}
