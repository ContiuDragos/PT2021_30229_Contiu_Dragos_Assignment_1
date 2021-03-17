package com.company;

import Polynomial.Monom;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClass {

    private Model model = new Model();
    @Test
    public void testClass()
    {
        model.setPolynom1("5*x^3+1*x^2+5");
        model.setPolynom2("3*x^2+1");
        model.breakFirstPolynom();
        model.breakSecondPolynom();

        ArrayList<Monom> resultList = new ArrayList<Monom>();
        ArrayList<Monom> auxList = new ArrayList<Monom>();
        resultList.add(new Monom(5,3));
        resultList.add(new Monom(4,2));
        resultList.add(new Monom(6,0));

        model.add();
        assertTrue(model.getMonomList1().toString().equals("+5*x^3+4*x^2+6"));
        model.sub();
        assertTrue(model.getMonomList1().toString().equals("+5*x^3+1*x^2+5"));
        model.mul();
        assertTrue(model.getMonomList1().toString().equals("+15*x^5+3*x^4+5*x^3+16*x^2+5"));
        assertTrue(model.div().toString().equals("+5*x^3+1*x^2+5"));

        model.setPolynom1("+15*x^5+3*x^4+5*x^3+16*x^2+5");
        model.breakFirstPolynom();
        model.deriv();
        assertTrue(model.getMonomList1().toString().equals("+75*x^4+12*x^3+15*x^2+32*x^1"));

        model.integ();
        assertTrue(model.getMonomList1().toString().equals("+15*x^5+3*x^4+5*x^3+16*x^2"));

    }
}
