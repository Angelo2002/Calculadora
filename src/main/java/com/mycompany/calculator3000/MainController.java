package com.mycompany.calculator3000;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MainController {


    @FXML
    private TextField equation;


    @FXML
    private void resolve() {
        try{
            //take textfield input, solve it and display it in the textfield
            String input = equation.getText();
            input=input.replace("÷","/").replace(" ","");
            Operation operation = new Operation(input);
            System.out.println(operation.getOperator()); // TODO remover
            double result = operation.getValue();
            equation.setText(String.valueOf(result));

        }catch (Exception e){
            equation.setText("Error");
        }



    }


    @FXML
    private void type_on_screen(ActionEvent actionEvent){
        Button btn = (Button) actionEvent.getSource();
        equation.setText(equation.getText() + btn.getText());
    }

    @FXML
    private void verify_input(KeyEvent keyEvent) {

    }
}
