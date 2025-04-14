import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Calculator {
    private JTextField display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";
    private final String historyFilePath = "history.txt"; // Псевдо JSON

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};

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

        // При запуску показуємо історію в консолі
        readHistory();

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
                saveToHistory(expression);
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

    private void saveToHistory(String record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFilePath, true))) {
            writer.write("{\"entry\": \"" + record + "\"}\n"); // Псевдо JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHistory() {
        File file = new File(historyFilePath);
        if (file.exists()) {
            System.out.println("🔎 Історія обчислень:");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("— Кінець історії —\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Історія ще не створена.");
        }
    }
}
