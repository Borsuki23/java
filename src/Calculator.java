import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private JTextField display;
    private JTextArea historyArea;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";
    private final String HISTORY_FILE = "history.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Поле для поточних обчислень
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*",
                "1", "2", "3", "-", "0", "C", "=", "+"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(Color.LIGHT_GRAY);
            button.setOpaque(true);
            button.setBorderPainted(false);

            button.addActionListener(new ButtonClickListener(text));
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Історія обчислень
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setPreferredSize(new Dimension(200, frame.getHeight()));
        frame.add(scrollPane, BorderLayout.EAST);

        // Завантаження історії
        loadHistory();

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private String value;

        public ButtonClickListener(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            handleInput(value);
        }
    }

    private void handleInput(String value) {
        if (value.matches("[0-9]") || value.equals(".")) {
            currentInput += value;
            display.setText(currentInput);
        } else if (value.equals("C")) {
            currentInput = "";
            firstOperand = 0;
            operator = "";
            display.setText("");
        } else if (value.equals("=")) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = calculate(firstOperand, secondOperand, operator);
                String expression = firstOperand + " " + operator + " " + secondOperand + " = " + result;

                display.setText(String.valueOf(result));
                appendToHistory(expression);

                currentInput = "";
                operator = "";
            }
        } else {
            if (!currentInput.isEmpty()) {
                firstOperand = Double.parseDouble(currentInput);
                operator = value;
                currentInput = "";
            }
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : 0;
            default: return 0;
        }
    }

    private void appendToHistory(String entry) {
        try (FileWriter fw = new FileWriter(HISTORY_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(entry);
            bw.newLine();
            historyArea.append(entry + "\n");
        } catch (IOException e) {
            System.err.println("Помилка запису в історію: " + e.getMessage());
        }
    }

    private void loadHistory() {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                historyArea.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Помилка читання історії: " + e.getMessage());
        }
    }
}
