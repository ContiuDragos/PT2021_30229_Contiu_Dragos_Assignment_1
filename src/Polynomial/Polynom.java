package Polynomial;

import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
    private ArrayList<Monom> polynom = new ArrayList<>();

    public ArrayList<Monom> getPolynom() {
        return polynom;
    }

    public void add(Monom m)
    {
        polynom.add(m);
    }

    public void show()
    {
        for (Monom i:polynom)
            System.out.println(i.toString());
        System.out.println();
    }

    public void resize()
    {
        for(int i=0; i<polynom.size()-1; i++) {
            for (int j = i + 1; j < polynom.size(); j++) {
                if (polynom.get(i).getExponent() == polynom.get(j).getExponent()) {
                    polynom.get(i).setCoeficient(polynom.get(i).getCoeficient() + polynom.get(j).getCoeficient());
                    polynom.remove(j);
                    j--;
                }
            }
        }
        polynom.removeIf(i -> i.getCoeficient() == 0);
    }
    public void sort()
    {
        Collections.sort(polynom);
        this.resize();
    }

    public String toString() {
        String result="";
        for( Monom i: this.polynom) {
            if (i.getCoeficient() < 0)
                result += i.toString();
            else
                result += "+" + i.toString();
        }
        return result;
    }

    public static Polynom mul(Polynom a, Polynom b)
    {
        Polynom c = new Polynom();
        for(Monom i: a.getPolynom())
        {
            for (Monom j: b.getPolynom())
                c.add(Monom.mul(i,j));
        }
        return c;
    }

    public void clear()
    {
        polynom.clear();
    }
}
