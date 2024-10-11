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
    private boolean virgule = true;
    private String saisie="";
    private String resultat="";
    private StringBuilder sum = new StringBuilder();

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
                    System.out.println(virgule);
                    System.out.println("Saisie="+saisie);
                    if (command.equals(".") && virgule == true) {

                        virgule = false;
                        if (saisie == "."){
                            System.out.println("iiiiiiiiiiiii");
                            view.setDisplayText(view.getDisplayText() + "0.");
                        }
                        else {
                            view.setDisplayText(saisie);
                        }

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
                    if (saisie == ""){
                        saisie = saisie + "-";
                        view.setDisplayText(view.getDisplayText() + "-");
                    }else {
                        saisie = saisie + "+";
                        view.setDisplayText(view.getDisplayText() + "+");
                    }
                } else if (command.equals("C")) {

                    operation = 0;
                    nb = 0;
                    saisie = "";
                    virgule = true;
                    model.reset();
                    view.setDisplayText("");

                } else if (command.equals("=")) {

                    virgule = true;
                    model.calculer(model.getOperateur());
                    double res = model.getResultat();
                    System.out.println("Resultat ="+res);
                    saisie = "";


                    resultat = String.valueOf(res);
                    int i=resultat.length()-1;
                    int j = 0;
                    do{
                        j=j+1;
                        i=i-1;
                    }while(resultat.charAt(i)=='0');
                    System.out.println(j);
                    if(j>=10){
                        while(j>0) {
                            resultat = resultat.substring(0, resultat.length() - 1); // Retirer le dernier caractère
                            j=j-1;
                            System.out.println(j);
                        }
                    }
                    // On vérifie si le nombre est un entier
                    if ((res) == (long) res) {
                        view.setDisplayText(String.valueOf((int) model.getResultat()));
                    } else if (model.getOperateur() == '/' && model.getNombre2() == '0') {
                        view.setDisplayText("Erreur Division par zéro n'est pas autorisée.");
                    }

                    else{
                            view.setDisplayText(String.valueOf(res));
                        }


                } else if (command.equals("+") || command.equals("*") || command.equals("/") || command.equals("-")) {

                    virgule = true;
                    System.out.println("Opérateur arithmétique ");
                    saisie ="";
                    operation = 1;
                    nb = 0;
                    model.setOperateur(command.charAt(0));
                    model.setResultat(Double.parseDouble(view.getDisplayText()));
                    view.setDisplayText(view.getDisplayText() + command);
                }
            }catch (ArithmeticException ex) {
                view.setDisplayText("Erreur : Division par zéro n'est pas autorisée.");
            }  catch (Exception ex) {
                view.setDisplayText("Error");
            }
        }
    }
}
