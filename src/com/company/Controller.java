package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Model model;
    private View view;
    public Controller(Model model, View view) {
        this.model=model;
        this.view=view;

        view.addAdditionListener(new AdditionListener());
        view.addSubtractionListener(new SubtractionListener());
        view.addMultiplicationListener(new MultiplicationListener());
        view.addDivisionListener(new DivisionListener());
        view.addDerivationListener(new DerivationListener());
        view.addIntegrationListener(new IntegrationListener());
        view.addResetListener(new ResetListener());
    }

    class AdditionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String polynom1 = view.getFirstPolynom();
            String polynom2 = view.getSecondPolynom();

            model.setPolynom1(polynom1);
            model.setPolynom2(polynom2);
            model.breakFirstPolynom();
            model.breakSecondPolynom();
            model.add();
            view.setResult(model.getMonomList1().toString());
        }
    }

    class SubtractionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String polynom1 = view.getFirstPolynom();
            String polynom2 = view.getSecondPolynom();

            model.setPolynom1(polynom1);
            model.setPolynom2(polynom2);
            model.breakFirstPolynom();
            model.breakSecondPolynom();
            model.sub();
            view.setResult(model.getMonomList1().toString());
        }
    }

    class MultiplicationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String polynom1 = view.getFirstPolynom();
            String polynom2 = view.getSecondPolynom();

            model.setPolynom1(polynom1);
            model.setPolynom2(polynom2);
            model.breakFirstPolynom();
            model.breakSecondPolynom();
            model.mul();
            view.setResult(model.getMonomList1().toString());
        }
    }

    class DivisionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String polynom1 = view.getFirstPolynom();
            String polynom2 = view.getSecondPolynom();

            model.setPolynom1(polynom1);
            model.setPolynom2(polynom2);
            model.breakFirstPolynom();
            model.breakSecondPolynom();
            view.setResult(model.div().toString());
        }
    }
    class DerivationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String polynom1 = view.getFirstPolynom();

            model.setPolynom1(polynom1);
            model.breakFirstPolynom();
            model.deriv();
            view.setResult(model.getMonomList1().toString());
        }
    }

    class IntegrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String polynom1 = view.getFirstPolynom();

            model.setPolynom1(polynom1);
            model.breakFirstPolynom();
            model.integ();
            view.setResult(model.getMonomList1().toString());
        }
    }

    class ResetListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.reset();
            model.getMonomList1().clear();
            model.getMonomList2().clear();
        }
    }

}
