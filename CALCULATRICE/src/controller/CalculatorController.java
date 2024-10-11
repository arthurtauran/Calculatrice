
2536333333333333333333333
        .package controller;

import model.CalculatorModel;
import view.CalculatorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    private int operation = 0;
    private int nb = 0;
    private int res = 0;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.view.addEventHandler(new ButtonClickListener());
    }

    class ButtonClickListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            int multiples = 10;
            boolean v = false;
            String command = ((Button) e.getSource()).getText();

            try {
                if (command.equals(".")) {
                    model.virgul();
                    v = true;
                    if (operation == 0) {
                        model.setNombre1(nb);
                    } else {
                        model.setNombre2(nb);
                    }
                }
                if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                    if (v) {
                        nb = nb * (multiples - model.virgul()) + Integer.parseInt(command);
                    } else {
                        nb = nb * multiples + Integer.parseInt(command);
                    }

                    res = Integer.parseInt(command);
                    if (operation == 0) {
                        model.setNombre1(nb);
                    } else {
                        model.setNombre2(nb);
                    }

                    view.setDisplayText(view.getDisplayText() + res);

                } else if (command.equals("C")) {
                    operation = 0;
                    nb = 0;
                    model.reset();
                    view.setDisplayText("");

                } else if (command.equals("=")) {
                    model.calculer(model.getOperateur());
                    double result = model.getResultat();
                    if (result == (long) result) {
                        view.setDisplayText(String.valueOf((int) model.getResultat()));
                    } else {
                        view.setDisplayText(String.valueOf(model.getResultat()));
                    }

                } else {
                    v = false;
                    operation = 1;
                    nb = 0;
                    model.setOperateur(command.charAt(0));
                    model.setResultat(Double.parseDouble(view.getDisplayText()));
                    view.setDisplayText(view.getDisplayText() + command);
                    model.resetvirgul();
                }
            } catch (Exception ex) {
                view.setDisplayText("Error");
            }
        }
    }
}