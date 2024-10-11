package controller;

import model.CalculatorModel;
import view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    private int operation = 0;
    private double nb = 0;
    private int res = 0;
    private boolean n = true;
    private String saisie="";

    public CalculatorController(CalculatorModel model, CalculatorView view) {

        this.model = model;
        this.view = view;
        this.view.addActionListener(new ButtonClickListener());
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

            try {

                if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")){

                    saisie = saisie + command;
                    System.out.println("Saisie="+saisie);
                    if (command.equals(".")) {
                        view.setDisplayText(view.getDisplayText() + ".");
                    }
                    else {
                        nb = Double.parseDouble(saisie);
                        System.out.println("Nombre ="+ nb);
                        res = Integer.parseInt(command);
                        if (operation == 0) {
                            model.setNombre1(nb);

                        }   else {
                            model.setNombre2(nb);
                        }

                        view.setDisplayText(view.getDisplayText() + res);
                    }

                } else if (command.equals("(-)")) {
                    saisie = saisie + command;
                    view.setDisplayText(view.getDisplayText() + "-");
                } else if (command.equals("C")) {

                    operation = 0;
                    nb = 0;
                    saisie = "";
                    model.reset();
                    view.setDisplayText("");

                } else if (command.equals("=")) {

                    model.calculer(model.getOperateur());
                    double res = model.getResultat();
                    saisie = "";

                    // On vérifie si le nombre est un entier
                    if ((res) == (long) res) {
                        view.setDisplayText(String.valueOf((int) model.getResultat()));
                    } else {
                        view.setDisplayText(String.valueOf(model.getResultat()));
                    }

                } else if (command.equals("+") || command.equals("*") || command.equals("/") || command.equals("-")) {

                    System.out.println("Opérateur arithmétique ");
                    saisie ="";
                    operation = 1;
                    nb = 0;
                    model.setOperateur(command.charAt(0));
                    model.setResultat(Double.parseDouble(view.getDisplayText()));
                    view.setDisplayText(view.getDisplayText() + command);
                }
            }catch (Exception ex) {
               view.setDisplayText("Error");
            }
        }
    }
}
