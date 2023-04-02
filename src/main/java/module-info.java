module com.mycompany.calculator3000 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.calculator3000 to javafx.fxml;
    exports com.mycompany.calculator3000;
}
