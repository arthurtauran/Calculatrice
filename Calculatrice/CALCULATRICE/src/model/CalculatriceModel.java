package model;

public class CalculatriceModel {
    private double nombre1 = 0;
    private double nombre2 = 0;
    private char operateur = '+';
    private double resultat;

    public void setNombre1(double nombre1) {
        this.nombre1 = nombre1;
    }

    public void setNombre2(double nombre2) {
        this.nombre2 = nombre2;
    }

    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

    public void setOperateur(char operateur) {
        this.operateur = operateur;
    }

    public double getNombre1() {
        return this.nombre1;
    }

    public double getNombre2() {
        return this.nombre2;
    }

    public double getResultat() {
        return this.resultat;
    }

    public char getOperateur() {
        return this.operateur;
    }

    // Fonction qui réalise le calcul
    public void calculer(char operateur) {

        switch (operateur) {
            case '+': // Somme
                resultat = nombre1 + nombre2;
                break;
            case '-': // Soustraction
                resultat = nombre1 - nombre2;
                break;
            case '*': // Multiplication
                resultat = nombre1 * nombre2;
                break;
            case '/': // Division
                // Test pour éviter la division par zéro - Un message est affiché dans la zone de saisie (voir Controller)
                if(nombre2==0){
                    throw new ArithmeticException();
                }
                resultat = nombre1 / nombre2;
                break;
            default:
                break;
        }
        nombre1 = resultat;
        nombre2 = 0;
    }

    // Fonction qui réinitialise les variables de la classe
    public void reset() {
        nombre1 = 0;
        nombre2 = 0;
        resultat = 0;
        operateur = '+';
    }
}
