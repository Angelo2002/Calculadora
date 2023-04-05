package com.mycompany.calculator3000;



public class Operation {
    private Operation operationLeft;
    private Operation operationRight;
    private String operator;
    private double value;
    public static final char[] ARR_OPERATORS = {'+', '-', '*', '/','^'};


    private boolean isOperator(char c) {
        for (char op : ARR_OPERATORS) {
            if (c == op) {
                return true;
            }
        }
        return false;
    }

    private boolean isAddorSub(char c) {
        return c == ARR_OPERATORS[0] || c == ARR_OPERATORS[1];
    }

    private void checkBrackets(){
        int brackets=0;
        for(char c : ARR_OPERATORS) {
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

        if (str_Operation.equals("")) {
            value = 0;
            return;
        }


        //guarda el valor númerico si no hay operaciones

        try {
            value = Double.parseDouble(str_Operation);
            return;
        } catch (NumberFormatException e) {
            //no es un número aún
        }
        if (isAddorSub(str_Operation.charAt(0))){
            str_Operation = "0" + str_Operation;
        }

        int brackets = 0;
        for (char op : ARR_OPERATORS) {

            char[] charArray = str_Operation.toCharArray();
            for (int i = charArray.length - 1; i >= 0; i--) {
                char c = charArray[i];
                if ((c == op) && (brackets == 0) && !isOperator(charArray[i - 1])) {
                    operationLeft = new Operation(str_Operation.substring(0, i)); //corta por la izq.
                    operationRight = new Operation(str_Operation.substring(i + 1)); //corta por la der.
                    operator = String.valueOf(c);
                    return;
                } else if (c == '(') {
                    brackets++;
                } else if (c == ')') {
                    brackets--;
                }
            }
        }
        throw new IllegalArgumentException("Operación no válida");

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
            case "^":
                value = Math.pow(operationLeft.getValue(),operationRight.getValue());
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
