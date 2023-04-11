package com.mycompany.calculator3000;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 * Angelo Marín Granados
 * Perdón por el código espagueti en Operation.java
 * Ya no sé como refactorizarlo, y funciona como está.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML());
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main" + ".fxml"));
        return fxmlLoader.load();
    }
    

    public static void main(String[] args) {
        launch();
    }

}