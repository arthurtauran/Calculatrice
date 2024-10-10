package model;

public class CalculatorModel {
    private double nombre1 = 0;
    private char operateur = '+';
    private double nombre2;
    private double resultat;
    private int virgul = 0;
    private String error;


    public void setVirgul(char virgul) {

        this.virgul = virgul;
    }

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

    public int getVirgul() {
        return this.virgul;
    }

    public void calculer(char operateur) {

        System.out.println(nombre1+nombre2);

        switch (operateur) {
            case '+':
                resultat = nombre1 + nombre2;
                break;
            case '-':
                resultat = nombre1 - nombre2;
                break;
            case '*':
                resultat = nombre1 * nombre2;
                break;
            case '/':
                if (nombre2 == 0) {
                    error = "Division par zéro impossible";
                }
                resultat = nombre1 / nombre2;
                break;
            case '=':
                // Do nothing; resultat is already calculated
                break;
            default:
                break;
        }
        nombre1 = resultat;
    }

    public int virgul() {
        virgul *= 10;
        return virgul;
    }

    public void resetvirgul() {
        virgul = 1;
    }

    public void reset() {
        resultat = 0;
        nombre1 = 0;
        nombre2 = 0;
        operateur = '+';
    }
}
