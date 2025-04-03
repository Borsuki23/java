import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StopwatchFrame());
    }
}

class StopwatchFrame extends JFrame {
    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0;

    public StopwatchFrame() {
        setTitle("Секундомір");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel(formatTime(elapsedTime));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(timeLabel);

        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");
        resetButton = new JButton("Скинути");

        add(startButton);
        add(stopButton);
        add(resetButton);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                timeLabel.setText(formatTime(elapsedTime));
            }
        });

        startButton.addActionListener(e -> timer.start());
        stopButton.addActionListener(e -> timer.stop());
        resetButton.addActionListener(e -> {
            timer.stop();
            elapsedTime = 0;
            timeLabel.setText(formatTime(elapsedTime));
        });

        setVisible(true);
    }

    private String formatTime(int seconds) {
        int mins = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", mins, secs);
    }
}
