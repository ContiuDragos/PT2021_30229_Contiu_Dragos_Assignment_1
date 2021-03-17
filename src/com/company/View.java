package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private Model model;
    private JButton multiplication = new JButton("*");
    private JButton addition = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton division = new JButton("/");
    private JButton derivate = new JButton("Deriv");
    private JButton integrate = new JButton("Integ");
    private JButton reset = new JButton("Reset");

    private JTextField polynom1 = new JTextField();
    private JTextField polynom2 = new JTextField();
    private JTextField resultTxt = new JTextField();

    private JLabel labelFirstPol = new JLabel("First polynomial P1(x)");
    private JLabel labelSecondPol = new JLabel("Second polynomial P2(x)");
    private JLabel labelResult = new JLabel("Result:");


    public View(Model model)
    {
        this.setTitle("Polynomial Calculator MVC");
        this.setMinimumSize(new Dimension(750,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.model=model;

        this.addContent(labelFirstPol);
    }

    public void  addContent(JLabel labelFirstPol)
    {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(1,2));

        polynom1.setMinimumSize(new Dimension(50,100));
        polynom1.setPreferredSize(new Dimension(50,100));
        JPanel leftContent = new JPanel();
        leftContent.setLayout(new GridLayout(3,1));

        JPanel firstLeft = new JPanel();
        firstLeft.setLayout(new GridLayout(2,1));
        firstLeft.add(labelFirstPol);
        firstLeft.add(polynom1);

        JPanel secondLeft = new JPanel();
        secondLeft.setLayout(new GridLayout(2,1));
        secondLeft.add(labelSecondPol);
        secondLeft.add(polynom2);

        JPanel thirdLeft = new JPanel();
        thirdLeft.setLayout(new GridLayout(2,1));
        thirdLeft.add(labelResult);
        thirdLeft.add(resultTxt);

        leftContent.add(firstLeft);
        leftContent.add(secondLeft);
        leftContent.add(thirdLeft);


        JPanel rightContent = new JPanel();
        rightContent.setLayout(new GridLayout(3,1));

        JPanel firstRightPanel = new JPanel();
        firstRightPanel.setLayout(new GridLayout(5,1));
        JLabel rule = new JLabel("Rules");
        JLabel rule1 = new JLabel("1. The monomial must have a coefficient");
        JLabel rule2 = new JLabel("2. All the powers must be written except 0");
        JLabel rule3 = new JLabel("3. Ex: 1*x^3+2*x^1+x^-1");
        JLabel rule4 = new JLabel("4. For integration and derivation insert just the first polynomial");
        firstRightPanel.add(rule);
        firstRightPanel.add(rule1);
        firstRightPanel.add(rule2);
        firstRightPanel.add(rule3);
        firstRightPanel.add(rule4);

        JPanel secondRightPanel = new JPanel();
        JLabel secondRightLabel = new JLabel("Operations");
        secondRightPanel.add(secondRightLabel);

        JPanel thirdRightPanel = new JPanel();
        thirdRightPanel.setLayout(new GridLayout(2,3));
        thirdRightPanel.add(addition);
        thirdRightPanel.add(subtraction);
        thirdRightPanel.add(division);
        thirdRightPanel.add(multiplication);
        thirdRightPanel.add(derivate);
        thirdRightPanel.add(integrate);
        thirdRightPanel.add(reset);

        rightContent.add(firstRightPanel);
        rightContent.add(secondRightPanel);
        rightContent.add(thirdRightPanel);

        content.add(leftContent);
        content.add(rightContent);

        this.setContentPane(content);
    }

    public void reset()
    {
        resultTxt.setText("");
        polynom1.setText("");
        polynom2.setText("");
    }

    public String getFirstPolynom()
    {
        return polynom1.getText();
    }

    public String getSecondPolynom()
    {
        return polynom2.getText();
    }

    public void setResult(String result)
    {
        this.resultTxt.setText(result);
    }

    void addAdditionListener(ActionListener action)
    {
        addition.addActionListener(action);
    }

    void addSubtractionListener(ActionListener action)
    {
        subtraction.addActionListener(action);
    }

    void addMultiplicationListener(ActionListener action)
    {
        multiplication.addActionListener(action);
    }

    void addDivisionListener(ActionListener action)
    {
        division.addActionListener(action);
    }

    void addDerivationListener(ActionListener action)
    {
        derivate.addActionListener(action);
    }

    void addIntegrationListener(ActionListener action)
    {
        integrate.addActionListener(action);
    }

    void addResetListener(ActionListener action)
    {
        reset.addActionListener(action);
    }
}
