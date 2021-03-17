package com.company;

import Polynomial.Monom;
import Polynomial.Polynom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

    private String polynom1;
    private String polynom2;
    private Polynom monomList1 = new Polynom();
    private Polynom monomList2 = new Polynom();
    public Model()
    {
        reset();
    }

    public void reset()
    {
        polynom1="";
        polynom2="";
        monomList1.clear();
        monomList2.clear();
    }

    public void setPolynom1(String polynom1) {
        this.polynom1 = polynom1;
    }

    public void setPolynom2(String polynom2) {
        this.polynom2 = polynom2;
    }

    public Polynom getMonomList1() {
        return monomList1;
    }

    public Polynom getMonomList2() {
        return monomList2;
    }

    public void breakFirstPolynom() {
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(polynom1);
        while (matcher.find()) {
            int coef, exp = 0;
            if (matcher.group(0).equals("x")) {///fara coeficient
                coef = 1;
                if (matcher.find())
                    exp = Integer.parseInt(matcher.group(0));
                monomList1.add(new Monom(coef, exp)); }
            else { ///cu coeficient
                coef = Integer.parseInt(matcher.group(0));
                if (matcher.find()) {
                    ///daca nu e termen liber
                    if (!matcher.group(0).equals("x")) {
                        monomList1.add(new Monom(coef, 0));
                        coef = Integer.parseInt(matcher.group(0)); }
                    if (matcher.find()) {
                        if(matcher.group(0).equals("x")) {
                            matcher.find(); }
                        exp = Integer.parseInt(matcher.group(0));
                        monomList1.add(new Monom(coef, exp)); } }
                else
                    monomList1.add(new Monom(coef,0)); } }
        monomList1.sort();
    }

    public void breakSecondPolynom()
    {
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(polynom2);
        while (matcher.find()) {
            int coef, exp = 0;
            if (matcher.group(0).equals("x")) { ///fara coeficient
                coef = 1;
                if (matcher.find())
                    exp = Integer.parseInt(matcher.group(0));
                monomList2.add(new Monom(coef, exp)); }
            else { coef = Integer.parseInt(matcher.group(0));       ///cu coeficient
                if (matcher.find()) {
                    ///daca nu e termen liber
                    if (!matcher.group(0).equals("x")) {
                        monomList2.add(new Monom(coef, 0));
                        coef = Integer.parseInt(matcher.group(0)); }
                    if (matcher.find()) {
                        if(matcher.group(0).equals("x")) {
                            matcher.find(); }
                        exp = Integer.parseInt(matcher.group(0));
                        monomList2.add(new Monom(coef, exp)); } }
                else
                    monomList2.add(new Monom(coef,0)); } }
        monomList2.sort();
    }

    public void add()
    {
        for(Monom i: this.monomList2.getPolynom())
        {
            monomList1.add(i);
        }
        monomList1.sort();
        if(monomList1.getPolynom().size()==0)
            monomList1.add(new Monom(0,0));
    }

    public void sub()
    {
        for(Monom i: this.monomList2.getPolynom())
        {
            i.setCoeficient(-i.getCoeficient());
            monomList1.add(i);
        }
        monomList1.sort();
        for(Monom i: monomList2.getPolynom())
            i.setCoeficient(-i.getCoeficient());
        if(monomList1.getPolynom().size()==0)
            monomList1.add(new Monom(0,0));

    }

    public void mul()
    {
        Polynom polynomResult;
        polynomResult = Polynom.mul(this.monomList1,this.monomList2);
        polynomResult.sort();
        monomList1.getPolynom().clear();
        for(Monom i: polynomResult.getPolynom())
            monomList1.add(i);
        if(monomList1.getPolynom().size()==0)
            monomList1.add(new Monom(0,0));
    }

    public void deriv()
    {
        for(Monom i: monomList1.getPolynom())
        {
            if(i.getExponent()>0)
            {
                i.setCoeficient(i.getExponent()*i.getCoeficient());
                i.setExponent(i.getExponent()-1);
            }
            else
            {
                if(i.getExponent()<0)
                {
                    i.setCoeficient(i.getExponent()*i.getCoeficient());
                    i.setExponent(i.getExponent()-1);
                }
                else {
                    i.setCoeficient(0);
                }
            }
        }
        monomList1.resize();
        if(monomList1.getPolynom().size()==0)
            monomList1.add(new Monom(0,0));
    }

    public void integ() {
        for(Monom i: monomList1.getPolynom()) {
            if(i.getExponent()==0)    ///daca e termen liber
                i.setExponent(1);
            else { if(i.getExponent()>0) {///daca are exponentul pozitiv//
                    i.setExponent(i.getExponent()+1);
                    if(i.getCoeficient()%(i.getExponent())==0) { ///daca coeficientul se imparte la viitorul exponent, modific coeficientul
                        i.setCoeficient(i.getCoeficient()/(i.getExponent()));
                    } else {    ///altfel pun rezultatul intr-un string
                        i.setCoeficientIntegration(i.getCoeficient()+"/"+i.getExponent());
                        i.setIntegrationBoolean(true);
                    }
                } else { ///daca are exponentul negativ
                    if(i.getExponent()==-1) { ///integrala din 1/x e log(x)
                        i.setCoeficientIntegration(i.getCoeficient()+"*log(x)");
                        i.setExponent(0);
                        i.setIntegrationBoolean(true);
                    } else {
                        i.setExponent(i.getExponent()+1);
                        if(i.getCoeficient()%i.getExponent()==0)   ///daca se imparte coeficientul la viitorul exponent
                            i.setCoeficient(-i.getCoeficient()/i.getExponent());
                        else {
                            i.setCoeficientIntegration("-"+i.getCoeficient()+"/"+i.getExponent());
                            i.setIntegrationBoolean(true); } } } } }
        monomList1.resize();
    }

    public Polynom div()
    {   Polynom result = new Polynom();
        int coefResult = 0;
        int expoResult = 0;
        int counter=1;
        while(monomList1.getPolynom().get(0).getExponent() >= monomList2.getPolynom().get(0).getExponent()) {
            int coefPolynom2=monomList2.getPolynom().get(0).getCoeficient();
            int coefPolynom1=monomList1.getPolynom().get(0).getCoeficient();
            if(coefPolynom1 % coefPolynom2!= 0) {
                for(Monom i: monomList1.getPolynom())
                    i.setCoeficient(i.getCoeficient()*coefPolynom2); }
            expoResult = monomList1.getPolynom().get(0).getExponent() - monomList2.getPolynom().get(0).getExponent();
            coefResult = coefPolynom1/coefPolynom2;
            result.add(new Monom(coefResult, expoResult));
            for(Monom i : result.getPolynom())
                i.setCoeficient(-i.getCoeficient());
            for(Monom i : monomList2.getPolynom())
                monomList1.add(Polynomial.Monom.mul(result.getPolynom().get(result.getPolynom().size()-1),i));
            monomList1.sort();
            for(Monom i : result.getPolynom())
                i.setCoeficient(-i.getCoeficient());
            if(monomList1.getPolynom().size()==0)
                return result; }
        counter=monomList2.getPolynom().get(0).getCoeficient();
        for (Monom i : result.getPolynom()) {
            if (i.getCoeficient() % monomList2.getPolynom().get(0).getCoeficient() == 0)
                i.setCoeficient(i.getCoeficient() / counter);
            else {
                i.setIntegrationBoolean(true);
                i.setCoeficientIntegration(i.getCoeficient() + "/" + counter ); }
            counter = counter * monomList2.getPolynom().get(0).getCoeficient(); }
        for (Monom i : monomList1.getPolynom()) {
            if (i.getCoeficient() % monomList2.getPolynom().get(0).getCoeficient() == 0)
                i.setCoeficient(i.getCoeficient() / (counter / monomList2.getPolynom().get(0).getCoeficient()));
            else {
                i.setIntegrationBoolean(true);
                i.setCoeficientIntegration(i.getCoeficient() + "/" + (counter * monomList2.getPolynom().get(0).getCoeficient())); }
            result.add(i); }
        return result; }
}
