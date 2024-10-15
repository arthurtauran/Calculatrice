package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Classe pour l'affichage de la calculatrice
public class CalculatriceView {

    private JFrame fenetre;
    private JTextField zoneSaisie;
    private JButton[] boutonsNumeriques;
    private JButton[] boutonsOperation;
    private JButton boutonEgal;
    private JButton boutonVirgule;
    private JButton boutonChangeSigne;
    private JButton boutonClear;

    // Prépare les objets graphiques de la calculatrice
    public CalculatriceView() {

        fenetre = new JFrame("Calculatrice");
        boutonsNumeriques = new JButton[10];
        boutonsOperation = new JButton[4];
        boutonEgal = new JButton("=");
        boutonVirgule = new JButton(".");
        boutonClear = new JButton("C");
        boutonChangeSigne = new JButton("(-/+)");
        zoneSaisie = new JTextField();

        boutonEgal.setFont(new Font("Serif", Font.BOLD, 50));
        boutonClear.setFont(new Font("Serif", Font.BOLD, 50));
        boutonChangeSigne.setFont(new Font("Serif", Font.BOLD, 30));
        boutonVirgule.setFont(new Font("Serif", Font.BOLD, 50));
        zoneSaisie.setFont(new Font("Serif", Font.BOLD, 35));
        zoneSaisie.setEditable(false);

        String[] operators = {"+", "-", "*", "/"};
        for (int i = 0; i < 10; i++) {
            boutonsNumeriques[i] = new JButton(String.valueOf(i));
            boutonsNumeriques[i].setFont(new Font("Serif", Font.BOLD, 50));
        }
        for (int i = 0; i < 4; i++) {
            boutonsOperation[i] = new JButton(operators[i]);
            boutonsOperation[i].setFont(new Font("Serif", Font.BOLD, 50));
        }

        affichageCalculatrice();
    }

    // Affiche la calculatrice
    private void affichageCalculatrice() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Ajout des boutons
        panel.add(boutonClear);
        for (int i = 1; i <= 9; i++) {
            panel.add(boutonsNumeriques[i]);
        }
        panel.add(boutonsNumeriques[0]);
        panel.add(boutonVirgule);
        panel.add(boutonChangeSigne);
        panel.add(boutonEgal);
        for (JButton operatorButton : boutonsOperation) {
            panel.add(operatorButton);
        }

        // Ajout zone de saisie
        panel.add(zoneSaisie);
        fenetre.setContentPane(panel);
        fenetre.setSize(700,700);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    // Renvoie la zone de saisie
    public String renvoiZoneSaisie() {

        return zoneSaisie.getText();
    }

    // Affiche la saisie et le résultat des opérations
    public void afficheZoneSaisie(String text) {

        zoneSaisie.setText(text);
    }

    // Mise en place des écouteurs d'evénements pour les boutons
    public void addActionListener(ActionListener listener) {

        for (JButton boutonNumerique : boutonsNumeriques) {
            boutonNumerique.addActionListener(listener);
        }
        for (JButton boutonOperation : boutonsOperation) {
            boutonOperation.addActionListener(listener);
        }

        boutonChangeSigne.addActionListener(listener);
        boutonEgal.addActionListener(listener);
        boutonVirgule.addActionListener(listener);
        boutonClear.addActionListener(listener);
    }
}
