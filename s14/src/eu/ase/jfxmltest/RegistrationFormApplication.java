package eu.ase.jfxmltest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;

// --module-path $PATH_TO_LIB --add-modules javafx.controls,javafx.fxml
public class RegistrationFormApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fxmlStream = new FileInputStream("registration_form.fxml");
        Parent root = loader.load(fxmlStream);
        stage.setTitle("Registration form fxml app");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
