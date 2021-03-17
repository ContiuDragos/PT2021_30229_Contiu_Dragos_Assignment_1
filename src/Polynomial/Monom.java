package Polynomial;


public class Monom implements Comparable{

    private int coeficient;
    private int exponent;
    private String coeficientIntDiv;
    private boolean integrationBoolean = false;

    public Monom(int coeficient, int exponent)
    {
        this.coeficient=coeficient;
        this.exponent=exponent;
        this.coeficientIntDiv="";
    }

    public int getCoeficient() {
        return coeficient;
    }

    public void setCoeficientIntegration(String coeficientIntegration) {
        this.coeficientIntDiv = coeficientIntegration;
    }

    public void setIntegrationBoolean(boolean integrationBoolean) {
        this.integrationBoolean = integrationBoolean;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public String toString()
    {
        String result = "";
        if(integrationBoolean) {
            result += this.coeficientIntDiv;
        }
        else {
            result += coeficient;
        }
        if(exponent!=0)
            result +="*x^"+exponent;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return -(this.exponent-((Monom) o).exponent);
    }

    public static Monom mul (Monom a, Monom b)
    {
        int coef, exp;
        coef = a.getCoeficient() * b.getCoeficient();
        exp = a.getExponent() + b.getExponent();
        return new Monom(coef,exp);
    }
}
