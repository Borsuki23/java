import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class PaintPanel extends JPanel {
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private Color currentColor = Color.BLACK;
    private boolean eraserMode = false;

    public PaintPanel() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                colors.add(eraserMode ? Color.WHITE : currentColor);
                repaint();
            }
        });
    }

    public void setColor(Color color) {
        currentColor = color;
        eraserMode = false;
    }

    public void enableEraser() {
        eraserMode = true;
    }

    public void clear() {
        points.clear();
        colors.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < points.size(); i++) {
            g.setColor(colors.get(i));
            g.fillOval(points.get(i).x, points.get(i).y, 5, 5);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Paint");
        PaintPanel paintPanel = new PaintPanel();
        JPanel controlPanel = new JPanel();

        JButton blackButton = new JButton("Black");
        JButton redButton = new JButton("Red");
        JButton blueButton = new JButton("Blue");
        JButton greenButton = new JButton("Green");
        JButton eraserButton = new JButton("Eraser");
        JButton clearButton = new JButton("Clear");

        blackButton.addActionListener(e -> paintPanel.setColor(Color.BLACK));
        redButton.addActionListener(e -> paintPanel.setColor(Color.RED));
        blueButton.addActionListener(e -> paintPanel.setColor(Color.BLUE));
        greenButton.addActionListener(e -> paintPanel.setColor(Color.GREEN));
        eraserButton.addActionListener(e -> paintPanel.enableEraser());
        clearButton.addActionListener(e -> paintPanel.clear());

        controlPanel.add(blackButton);
        controlPanel.add(redButton);
        controlPanel.add(blueButton);
        controlPanel.add(greenButton);
        controlPanel.add(eraserButton);
        controlPanel.add(clearButton);

        frame.setLayout(new BorderLayout());
        frame.add(paintPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
