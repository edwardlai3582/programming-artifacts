package com.google.code.datastrut;

/*
Jennifer Bailey: Final Project, Spring 2000
This program is a java applet that will convert
a mathematical expression from infix notation into
reverse Polish or Postfix notation which is a form suitable
for stack manipulation.
*/

//Here are all of the input statements

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

//This is the major class, it is also registered as the listener
public class Simulator extends Applet implements ActionListener, ItemListener{
    
    //Here are the checkboxes that pick which kind of conversion will be done on the data
    
    Checkbox Infix = new Checkbox("Infix to Postfix");
    Checkbox Postfix = new Checkbox("View In Stack");
    CheckboxGroup InfixOrPostfix = new CheckboxGroup();
    
    //Here are the textfields that input and output the expressions
    TextField InputExpression = new TextField(25);
    TextField OutputResult = new TextField(25);
    
    //Labels for the textfields
    Label Input = new Label("Input Expression");
    Label Output = new Label("Result");
    Label ExampleLabel;
    
    //One Button to clear the text one button to convert the expression
    Button ConvertExpression = new Button("Convert Expression");
    Button ClearText = new Button("Clear Text");
    Button Close;
    //Button close;
    
    public Frame Example;
    
    char StackArray[];
    Button stack[];
    int i = 9;
    int j = 0;
    int StackPointer;
    int k = 0;
    char Operation = '0';
    int ExpressionLength;
    public String Answer;
    
    public void init(){
        //This lays out the applet nicely
        setSize(300, 100);
        setLayout(new GridLayout(4, 2));
        Infix.setCheckboxGroup(InfixOrPostfix);
        Postfix.setCheckboxGroup(InfixOrPostfix);
        add(Input);
        Input.requestFocus();
        add(InputExpression);
        add(Output);
        add(OutputResult);
        Infix.setCheckboxGroup(InfixOrPostfix);
        Postfix.setCheckboxGroup(InfixOrPostfix);
        add(Infix);
        add(Postfix);
        add(ConvertExpression);
        add(ClearText);
        
        //This adds listeners to the checkbuttons and buttons
        ConvertExpression.addActionListener(this);
        ClearText.addActionListener(this);
        Infix.addItemListener(this);
        Postfix.addItemListener(this);
        InfixOrPostfix.setSelectedCheckbox(Infix);
    }
    
    //When a button is pressed this method takes care of the action, either
    //clearing the text or going out to the method that does the mathmatical
    //manipulation
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand() == "Clear Text"){
            InputExpression.setText("");
            OutputResult.setText("");
            
        }else if (e.getActionCommand() == "Convert Expression"){

            Object whichConversion = InfixOrPostfix.getSelectedCheckbox();
            if (whichConversion.equals(Infix)){
                ConvertInfixToPostfix();
            }else if (whichConversion.equals(Postfix)){
            }
        }else if (e.getActionCommand() == "Next"){
            ProgressArray(StackArray);
        }
    }
    
    //This method is set up to receive an item event but haven't decided what I want
    //to do with it yet when an itemevent is received.
    public void itemStateChanged(ItemEvent i){
        Object whichConversion = i.getItem();
        if (whichConversion.equals("Infix to Postfix")){
            //Postfix.setState(true);
            //Infix.setState(false);
        }else if (whichConversion.equals("View In Stack")){
            ConvertInfixToPostfix();
            ViewInStack();
        }
    }

    //This method is called when we are converting the expression from
    //infix to Postfix
    void ConvertInfixToPostfix(){
        String Expression = InputExpression.getText();
        //This stores the length of the expression
        ExpressionLength = Expression.length();
        //This converts the Expression into seperate characters stored in a char[]
        StackArray = Expression.toCharArray();
        
        //This finds the * and / operations and if they are * or / then
        //it switches the values in the array into postfix form, if not,
        //it leaves them the same
        for (int i = 1; i < ExpressionLength; i++){
            if (StackArray[i] == '/' || StackArray[i] =='*'){
                char temp = StackArray[i];
                StackArray[i] = StackArray[i+1];
                StackArray[i+1] = temp;
                i++;
            }
        //This method does the + and - conversion after the / * is completed.
        //it moves through the STackArray and moves the operation into Operation
        //then moves through each element shuffling left until encountering another
        //+ - when it then deposites the old operation and picks up hte new one
        // and continues shuffling left until hte end of the expression.
        }
        Operation = '0';
        for (StackPointer = 0; StackPointer < ExpressionLength - 1; StackPointer++){
            int NextPointer = StackPointer + 1;
            if (StackArray[StackPointer] == '+' || StackArray[StackPointer] == '-'){
                while (StackPointer < ExpressionLength -1 && StackArray[NextPointer] != '+' &&
                StackPointer < ExpressionLength-1 && StackArray[NextPointer] != '-'){
                    Operation = StackArray[StackPointer];
                    StackArray[StackPointer] = StackArray[NextPointer];
                    StackArray[NextPointer] = Operation;
                    StackPointer++;
                    NextPointer++;
                }
                int Temp = Operation;
                Operation = StackArray[StackPointer];
            }
        }
        //This converts answer back to a string value and outputs the answer
        String Answer = new String(StackArray);
        OutputResult.setText(Answer);
        
        //StackExample example = new StackExample(StackArray);
        //StackExample Example = new StackExample();
        //Example.setBounds(100 ,300, 200, 600);
        //Example.show();
        
    }
    
    void ViewInStack(){
        StackExample(StackArray);
    }

    public void StackExample(char[] TheAnswer){
        
        Frame Example = new Frame();
        ExampleLabel = new Label("Appears in memory as:");
        Close = new Button("Next");
        Close.addActionListener(this);
        Example.setBounds(50,50,150,350);
        Example.setLayout(new GridLayout(12,1));
        Example.add(ExampleLabel);
        stack = new Button[10];
        for (int i = 0; i < 10; i++){
            stack[i] = new Button("");
            Example.add(stack[i]);
        }
        Example.add(Close);
        Example.show();
    }

    public void ProgressArray(char[] StackArray){
        
        if (StackArray[k] != '+' && StackArray[k] != '-' && StackArray[k] != '*' && StackArray[k] != '/'){
            stack[i].setLabel(""+StackArray[k]);
            k++;
            i--;
        } else {
            if (StackArray[k] == '+'){
                ExampleLabel.setText("Operation = +");
                i++;
                Double x = new Double(stack[i+1].getLabel());
                double OperandA = x.doubleValue();
                Double y = new Double(stack[i].getLabel());
                double OperandB = y.doubleValue();
                double Result = OperandA + OperandB;
                stack[i+1].setLabel(String.valueOf(Result));
                stack[i].setLabel(stack[i-1].getLabel());
                k++;
                
                //  String borrow = stack[7].getLabel();
                //stack[8].setLabel(borrow);
                    
            } else if (StackArray[k] == '-')    {
                ExampleLabel.setText("Operation = -");
                i++;
                Double x = new Double(stack[i+1].getLabel());
                double OperandA = x.doubleValue();
                Double y = new Double(stack[i].getLabel());
                double OperandB = y.doubleValue();
                double Result = OperandA - OperandB;
                stack[i+1].setLabel(String.valueOf(Result));
                stack[i].setLabel(stack[i-1].getLabel());
                k++;
            } else if (StackArray[k] == '*'){
                ExampleLabel.setText("Operation = *");
                i++;
                Double x = new Double(stack[i+1].getLabel());
                double OperandA = x.doubleValue();
                Double y = new Double(stack[i].getLabel());
                double OperandB = y.doubleValue();
                double Result = OperandA * OperandB;
                stack[i+1].setLabel(String.valueOf(Result));
                stack[i].setLabel(stack[i-1].getLabel());
                k++;
            } else if (StackArray[k] == '/'){
                ExampleLabel.setText("Operation = /");
                i++;
                Double x = new Double(stack[i+1].getLabel());
                double OperandA = x.doubleValue();
                Double y = new Double(stack[i].getLabel());
                double OperandB = y.doubleValue();
                if (OperandB == 0){
                    NoZero();
                }
                double Result = OperandA / OperandB;
                stack[i+1].setLabel(String.valueOf(Result));
                stack[i].setLabel(stack[i-1].getLabel());
                k++;
            }
        }
    }

    public void NoZero(){
        ExampleLabel.setText("Can't Divide By Zero");
        ExampleLabel.setForeground(Color.red);
        Close.setEnabled(false);
    }
    
    public static void main(String[] args) {
        new Simulator().init();
    }
}

