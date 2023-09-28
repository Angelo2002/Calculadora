package com.mycompany.calculator3000;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainController {

    double ANS = 0;

    @FXML
    private TextField equation;
    @FXML
    private Button btn_equal;


    @FXML
    private void resolve() {
        try {
            String input = getInput();
            Operation operation = new Operation(input);
            // System.out.println(operation.getOperator()); // TODO remover
            ANS = operation.getValue();
            if (ANS==Double.POSITIVE_INFINITY || ANS==Double.NEGATIVE_INFINITY){
                equation.setText("Tiende a infinito");
            }
            else
                equation.setText(String.valueOf(ANS));
        } catch (Exception e) {
            equation.setText("Operación no válida");
        }

    }

    private String getInput() {
        String input = equation.getText();
        input = input.replace("÷", "/").replace(" ", "");
        input = input.replace("×", "*").replace("ANS", String.valueOf(ANS));
        return input;
    }

    //Pone el texto del botón en el TextField
    @FXML
    private void type_on_screen(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        equation.setText(equation.getText() + btn.getText());
    }

    @FXML
    private void clear_entry() {
        equation.setText("");
    }



    //Borra el último caracter del TextField si no es un operador, parentesis o no es un número
    //Además revisa la tecla Enter y Escape para que funcionen como esperado
    @FXML
    private void key_pressed(KeyEvent keyEvent) {
        String text = getInput();
        if (text.length() > 0) {
            char last_char = text.charAt(text.length() - 1);
            if (!Character.isDigit(last_char) && !(last_char == '.'))
                if (Operation.notAnOperator(last_char) && last_char != '(' && last_char != ')') {
                    equation.setText(text.substring(0, text.length() - 1));
                    equation.positionCaret(equation.getText().length());
                }
        }
        switch (keyEvent.getCode()){
            case ENTER:
                btn_equal.fire();
                equation.positionCaret(equation.getText().length());
                break;
            case ESCAPE:
                clear_entry();
                break;
        }
    }
}


