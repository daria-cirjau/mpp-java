package eu.ase.jfxmltest;

import eu.ase.iojson.User;
import eu.ase.sqldao.SqlDAO;
import eu.ase.sqldao.UsersSubscriberReactStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.util.concurrent.SubmissionPublisher;

public class RegistrationFormController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Button submitMThButton;
    @FXML
    private Button submitReactStreamsButton;
    @FXML
    private Button displayButton;

    private static int objectRegistredUsersCount = 0;
    private static SqlDAO sqlDAO;

    public RegistrationFormController() {
        sqlDAO = SqlDAO.getInstance();
    }

    private boolean doValidationGUI(Window window) {
        if(nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Form Error!", "Please enter your name");
            return false;
        }
        if(emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Form Error!", "Please enter your email");
            return false;
        }
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Form Error!", "Please enter your password");
            return false;
        }

        return true;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window window = submitButton.getScene().getWindow();
        if(!doValidationGUI(window)) {
            return;
        }
        objectRegistredUsersCount++;
        System.out.println("Registered User - " + nameField.getText());
        sqlDAO.insertIntoDB(objectRegistredUsersCount, nameField.getText(), emailField.getText(), passwordField.getText());
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration succesful - standard!", "Welcome " + nameField.getText());
    }

    @FXML
    protected void handleSubmitMThButtonAction(ActionEvent event) {
        Window window = submitButton.getScene().getWindow();
        if(!doValidationGUI(window)) {
            return;
        }
        objectRegistredUsersCount++;
        System.out.println("Registered User - " + nameField.getText());
        Runnable rth = () -> {
            sqlDAO.insertIntoDB(objectRegistredUsersCount, nameField.getText(), emailField.getText(), passwordField.getText());
        };
        Thread th = new Thread(rth);
        th.start();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration succesful - multithreading!", "Welcome " + nameField.getText());
    }

    @FXML
    protected void handleSubmitReactStreamsButtonAction(ActionEvent event) {
        Window window = submitButton.getScene().getWindow();
        if(!doValidationGUI(window)) {
            return;
        }
        objectRegistredUsersCount++;
        System.out.println("Registered User - " + nameField.getText());


        try(SubmissionPublisher<User>usersPublisher = new SubmissionPublisher<>()) {
            User u = new User(objectRegistredUsersCount, nameField.getText(), emailField.getText(), passwordField.getText());
            UsersSubscriberReactStream subscriber = new UsersSubscriberReactStream();
            usersPublisher.subscribe(subscriber);
            usersPublisher.submit(u);
        }
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration succesful - reactive streams!", "Welcome " + nameField.getText());
    }

    @FXML
    protected void handleDisplayButtonAction(ActionEvent event) {
        sqlDAO.displayDB();
    }
}
