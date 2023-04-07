package com.mycompany.calculator3000;



public class Operation {
    private Operation operationLeft;
    private Operation operationRight;
    private String operator;
    private double value;
    public static final char[] ARR_OPERATORS = {'+', '-', '*', '/','^'};


    public static boolean isOperator(char c) {
        for (char op : ARR_OPERATORS) {
            if (c == op) {
                return true;
            }
        }
        return false;
    }

    private boolean isAddOrSub(char c) {
        return c == ARR_OPERATORS[0] || c == ARR_OPERATORS[1];
    }

    private void checkParenthesis() throws IllegalArgumentException{
        int parenthesis=0;
        for(char c : ARR_OPERATORS) {
            if (c == '(') {
                parenthesis++;
            }
            else if (c == ')') {
                parenthesis--;
            }
        }
        if(parenthesis!=0){
            throw new IllegalArgumentException("Parentesis no balanceados");
        }
    }

    private String removeUselessParenthesis(String str_Operation){
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
        return removeUselessParenthesis(str_Operation.substring(1,str_Operation.length()-1));
    }

    public Operation(String str_Operation) throws IllegalArgumentException {
        str_Operation = removeUselessParenthesis(str_Operation);
        checkParenthesis();
        //System.out.println(str_Operation);
        if (str_Operation.equals("")) {
            value = 0;
            return;
        }

        //guarda el valor númerico si no hay operacionesa
        try {
            value = Double.parseDouble(str_Operation);
            return;
        } catch (NumberFormatException ignored) {
        }

        //añade un 0 si la operación empieza con un + o un -. Para evitar errores con los operadores
        if (isAddOrSub(str_Operation.charAt(0))){
            str_Operation = "0" + str_Operation;
        }

        int parenthesis = 0;
        for (char op : ARR_OPERATORS) {
            char[] charArray = str_Operation.toCharArray();
            //Para que el programa logre respetar la prioridad de los operadores, se recorre la operación de derecha a izquierda
            //y se busca el primer operador que no esté dentro de paréntesis, empezando por la suma. Pareciera contrario
            //a lo usual, pero al ser "recursivo" debe pensarse de esta manera.
            for (int i = charArray.length - 1; i >= 0; i--) {
                char c = charArray[i];
                if ((c == op) && (parenthesis == 0) && !isOperator(charArray[i - 1])) {
                    operationLeft = new Operation(str_Operation.substring(0, i)); //corta por la izq.
                    operationRight = new Operation(str_Operation.substring(i + 1)); //corta por la der.
                    operator = String.valueOf(c);
                    return;
                } else if (c == '(') {
                    parenthesis++;
                } else if (c == ')') {
                    parenthesis--;
                }
            }
        }

        //si no se encuentra un operador, se lanza una excepción
        throw new IllegalArgumentException("Operación no válida");

    }



    //calcula el valor de la operación utilizando los valores de las operaciones izquierda y derecha
    //Aquí toma importancaia la manera en la que se generaron las operaciones.
    // Realmenete se calcula primero la operación más interna y a la izquierda, como lo hacemos normalmente.
    public void calculate() throws IllegalArgumentException {
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
