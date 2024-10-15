package controller;

import model.CalculatriceModel;
import view.CalculatriceView;

// import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalculatriceController {
    private CalculatriceModel model;
    private CalculatriceView view;
    private int operation = 0;
    private double nb = 0;
    private int res = 0;
    private double res1;
    private double res2;
    private boolean virgule = true;
    private String saisie="0";
    private String nbre1, nbre2;

    public CalculatriceController(CalculatriceModel model, CalculatriceView view) {

        this.model = model;
        this.view = view;
        this.view.afficheZoneSaisie("0");
        this.view.addActionListener(new ButtonClickListener());
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

            try {

                // Saisie d'un chiffre ou de la virgule
                if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {

                    // les chiffres et la virgule saisis sont stockées dans la String saisie, avant d'être converti en nombre
                    saisie = saisie + command;

                    // Saisie de la virgule (pour la 1ère fois dans la saisie du nombre)
                    if (command.equals(".") && virgule) {

                        virgule = false;
                        // si on est sur le 1er nombre de l'operation, on affiche le 1er nombre (donc saisie), sinon on affiche le 1er nombre, l'opérateur et le 2ème nombre jusqu'à la virgule
                        if (operation == 0)
                            view.afficheZoneSaisie(saisie);
                        else
                            view.afficheZoneSaisie(view.renvoiZoneSaisie() + ".");
                    } else {

                        // on convertie saisie (String) en double
                        nb = Double.parseDouble(saisie);
                        res = Integer.parseInt(command);
                        if (operation == 0) {
                            model.setNombre1(nb);

                        } else {
                            model.setNombre2(nb);
                        }

                        // Si 0 est affiché (lorsqu'on démarre la calculatrice par exemple), le caractère saisi remplace 0
                        if ((saisie.charAt(0) == '0') && (operation == 0) && (virgule == true)){
                            view.afficheZoneSaisie(String.valueOf(res));
                            saisie = String.valueOf(res);
                        } else
                            view.afficheZoneSaisie(view.renvoiZoneSaisie() + res);
                    }

                } else if (command.equals("(-/+)")){ // Saisie du bouton permettant de changer de signe

                    nb = -nb;

                    // Si on change le signe du 1er nombre de l'opération
                    if (operation == 0) {

                        model.setNombre1(nb);

                        // Si nb est un entier
                        if (nb == (long) nb)
                            view.afficheZoneSaisie(String.valueOf((int)nb));
                        else
                            view.afficheZoneSaisie(String.valueOf(nb));

                    } else { // Si on change le signe du 2ème nombre de l'opération

                        model.setNombre2(nb);

                        res1 = model.getNombre1();
                        res2 = model.getNombre2();

                        // Si res1 est un entier
                        if ((res1 == (long) res1))
                            nbre1 = String.valueOf( String.valueOf((int) res1));
                        else
                            nbre1 = String.valueOf(res1);

                        // Si res2 est un entier
                        if ((res2 == (long) res2))
                            nbre2 = String.valueOf( String.valueOf((int) res2));
                        else
                            nbre2 = String.valueOf(res2);

                        view.afficheZoneSaisie(nbre1 + model.getOperateur() + nbre2);
                    }


                } else if (command.equals("C")) { // Saisie de la touche de remise à zéro

                    operation = 0;
                    nb = 0;
                    saisie = "0";        
                    virgule = true;
                    model.reset();
                    view.afficheZoneSaisie("0");

                } else if (command.equals("=")) { // Saisie de la touche =

                    virgule = true;

                    // on ne lance le calcul que si il y a eu une opération
                    if (operation == 1) {

                        model.calculer(model.getOperateur());
                        double res = model.getResultat();
                        nb = res;

                        saisie = "0";
                        operation = 0;

                        DecimalFormat df;

                        // On formatte le résultat à afficher (10 chiffres max. après la virgule, les 0 inutiles ne sont pas affichés
                        df = new DecimalFormat("#.##########");
                        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                        view.afficheZoneSaisie(df.format(res));
                    }

                } else if (command.equals("+") || command.equals("*") || command.equals("/") || command.equals("-")) {  // Saisie de l'opérateur

                    virgule = true;
                    saisie ="";
                    operation = 1;
                    nb = 0;
                    model.setOperateur(command.charAt(0));
                    model.setResultat(Double.parseDouble(view.renvoiZoneSaisie()));
                    view.afficheZoneSaisie(view.renvoiZoneSaisie() + command);
                }
            }

            catch (ArithmeticException ex) { // Message affiché dqans la zone de saisie si division par zéro
                view.afficheZoneSaisie("Erreur: Division par zéro non autorisée");
            }

            catch (Exception ex) {
                view.afficheZoneSaisie("Erreur");
            }
        }
    }
}
