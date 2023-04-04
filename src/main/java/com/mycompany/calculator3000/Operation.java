package com.mycompany.calculator3000;



public class Operation {
    private Operation operationLeft;
    private Operation operationRight;
    private String operator;
    private double value;
    public static final char[] OPERATIONS = {'+', '-', '*', '/'};



    private void checkBrackets(){
        int brackets=0;
        for(char c : OPERATIONS) {
            if (c == '(') {
                brackets++;
            }
            else if (c == ')') {
                brackets--;
            }
        }
        if(brackets!=0){
            throw new IllegalArgumentException("Parentesis no balanceados");
        }
    }

    private String removeUselessBrackets(String str_Operation){
        if (str_Operation.charAt(0) != '(' || str_Operation.charAt(str_Operation.length() - 1) != ')') {
            return str_Operation;
        }

        int brackets=0;
        for(int i=0;i<str_Operation.length();i++){
            if(str_Operation.charAt(i)=='('){
                brackets++;
            }
            else if(str_Operation.charAt(i)==')'){
                brackets--;
            }
            if(brackets==0 && i!=str_Operation.length()-1){
                return str_Operation;
            }
        }

        return removeUselessBrackets(str_Operation.substring(1,str_Operation.length()-1));
    }

    public Operation(String str_Operation) {

        str_Operation = removeUselessBrackets(str_Operation);
        checkBrackets();

        System.out.println(str_Operation);

        //guarda el valor númerico si no hay operaciones

        try {
            value = Double.parseDouble(str_Operation);
            return;
        } catch (NumberFormatException e) {
            //no es un número aún
        }
        if (str_Operation.matches("^[-+*/].*")) {
            operationLeft = new Operation(0);
            operationRight = new Operation(str_Operation.substring(1));
            operator = String.valueOf(str_Operation.charAt(0));
            return;
        }

        int brackets = 0;
        int idx = 0;
        for (char op : OPERATIONS) {
            idx=0;
            for (char c : str_Operation.toCharArray()) {

                if ((c == op) && (brackets == 0)) {
                    operationLeft = new Operation(str_Operation.substring(0, idx)); //corta por la izq.
                    operationRight = new Operation(str_Operation.substring(idx + 1)); //corta por la der.
                    operator = String.valueOf(c);
                    return;
                } else if (c == '(') {
                    brackets++;
                } else if (c == ')') {
                    brackets--;
                }
                idx++;
            }
        }

    }

    public Operation(Operation operationLeft, Operation operationRight, String operator) {
        this.operationLeft = operationLeft;
        this.operationRight = operationRight;
        this.operator = operator;

    }

    private Operation(double value) {
        this.value = value;
    }



    public void calculate() {
        if (operationLeft == null) {
            return;
        }
        switch (operator) {
            case "+":
                value = operationLeft.getValue() + operationRight.getValue();
                break;
            case "-":
                value = operationLeft.getValue() - operationRight.getValue();
                break;
            case "*":
                value = operationLeft.getValue() * operationRight.getValue();
                break;
            case "/":
                value = operationLeft.getValue() / operationRight.getValue();
                break;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }
    public double getValue() {
        calculate();
        return value;
    }
    public String getOperator() {
        return operator;
    }

}
