package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView {
    private JFrame frame;
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton;
    private JButton pointButton;
    private JButton clearButton;

    public CalculatorView() {
        frame = new JFrame("Calculator");
        display = new JTextField(16);
        display.setEditable(false);
        numberButtons = new JButton[10];
        operatorButtons = new JButton[4];
        equalsButton = new JButton("=");
        pointButton = new JButton(".");
        clearButton = new JButton("C");

        equalsButton.setFont(new Font("Serif", Font.BOLD, 100));
        clearButton.setFont(new Font("Serif", Font.BOLD, 100));

        String[] operators = {"+", "-", "*", "/"};
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
                    numberButtons[i].setFont(new Font("Serif", Font.BOLD, 100));
        }
        for (int i = 0; i < 4; i++) {
            operatorButtons[i] = new JButton(operators[i]);
            operatorButtons[i].setFont(new Font("Serif", Font.BOLD, 100));
        }

        setupLayout();
    }

    private void setupLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5));
        panel.setSize(500,500);
        panel.add(clearButton);
        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]); // Définir la taille du bouton);
        }
        panel.add(numberButtons[0]);
        pointButton.setFont(new Font("Serif", Font.BOLD, 100));
        panel.add(pointButton);
        panel.add(equalsButton);
        for (JButton operatorButton : operatorButtons) {
            operatorButton.setSize(200, 200); // Définir la taille du bouton
            panel.add(operatorButton); // Ajouter le bouton au panel

        }

        display.setSize(300,200);
        panel.add(display);
        frame.add(panel);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getDisplayText() {
        return display.getText();
    }

    public void setDisplayText(String text) {

        display.setText(text);
        display.setFont(new Font("Serif", Font.BOLD, 50));
    }

    public void addActionListener(ActionListener listener) {
        for (JButton numberButton : numberButtons) {
            numberButton.addActionListener(listener);
        }
        for (JButton operatorButton : operatorButtons) {
            operatorButton.addActionListener(listener);
        }
        equalsButton.addActionListener(listener);
        pointButton.addActionListener(listener);
        clearButton.addActionListener(listener);
    }
}
