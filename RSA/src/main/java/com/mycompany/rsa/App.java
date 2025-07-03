package com.mycompany.rsa;

import com.mycompany.aes.DB_RSA;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        String output = "";
        String inputFromTextPlain = "";
        var outputSection = new VBox();
        var encryptButton = new Button("Encrypt");
        encryptButton.setStyle("-fx-background-color: #DA9100");
        encryptButton.setPadding(new Insets(10, 20, 10, 20));
        encryptButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        encryptButton.setMinWidth(80);
        var labelOfPlainTextField = new Label("Enter plain text here: ");
        
        var decryptButton = new Button("Decrypt");
        decryptButton.setStyle("-fx-background-color: #DA9100");
        decryptButton.setPadding(new Insets(10, 20, 10, 20));
        decryptButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        decryptButton.setMinWidth(80);

        var isPrimeButton = new Button("Primality test");
        isPrimeButton.setStyle("-fx-background-color: #DA9100");
        isPrimeButton.setPadding(new Insets(10, 20, 10, 20));
        isPrimeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        isPrimeButton.setMinWidth(80);
        var labelOfIsPrimeTextField = new Label("Enter ciphered text here: ");
        
        var labelNumberField = new Label("Enter the number you want to test: ");
        var pathLabel = new Label("Enter the path: ");

        var buttonSection = new HBox();
        buttonSection.getChildren().addAll( encryptButton, decryptButton, isPrimeButton);
        buttonSection.setAlignment(Pos.CENTER);
        buttonSection.setSpacing(14);
        VBox.setMargin(buttonSection, new Insets(10, 0, 0, 0));
        
        
        
        var cipheredTextField = new TextField();        
        var plainTextField = new TextField();
        var isPrimeField = new TextField();
        var pathField = new TextField();
                
        plainTextField.setPadding(new Insets(8));
        cipheredTextField.setPadding(new Insets(8));
        isPrimeField.setPadding(new Insets(8));
        pathField.setPadding(new Insets(8));

        
        plainTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        cipheredTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        isPrimeField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        pathField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));

        
        
        plainTextField.setMinWidth(350);
        plainTextField.setMaxWidth(350);
        cipheredTextField.setMinWidth(350);
        cipheredTextField.setMaxWidth(350);
        pathField.setMaxWidth(350);
        pathField.setMinWidth(350);
        isPrimeField.setMinWidth(350);
        isPrimeField.setMaxWidth(350);
        
        labelOfPlainTextField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfIsPrimeTextField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfPlainTextField.setTextFill(Color.web("#DA9100"));
        labelOfIsPrimeTextField.setTextFill(Color.web("#DA9100"));
        labelNumberField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelNumberField.setTextFill(Color.web("#DA9100"));
        pathLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        pathLabel.setTextFill(Color.web("#DA9100"));
        
        var fieldsSection = new VBox();
        fieldsSection.getChildren().addAll(labelOfPlainTextField, plainTextField,
             labelOfIsPrimeTextField, cipheredTextField, pathLabel, pathField, labelNumberField, isPrimeField);
        fieldsSection.setSpacing(10);
        VBox.setMargin(fieldsSection, new Insets(10, 0, 10, 40));
        
        
        var labelOfProgram = new Label("Cipherno");
        labelOfProgram.setTextFill(Color.web("#DA9100"));
        labelOfProgram.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        var labelOfOutput = new Label("Output: " + output);
        labelOfOutput.setTextFill(Color.web("#DA9100"));
        labelOfOutput.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        HBox titleSection = new HBox();
        titleSection.getChildren().setAll(labelOfProgram);
        titleSection.setAlignment(Pos.CENTER);
        VBox.setMargin(titleSection, new Insets(30, 0, 10, 0));
        outputSection.getChildren().addAll(labelOfOutput);
        
        VBox.setMargin(outputSection, new Insets(30, 0, 10, 40));
        outputSection.setSpacing(10);
        VBox box = new VBox(titleSection,
                fieldsSection, buttonSection, outputSection);
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        
        encryptButton.setOnAction((e) -> {            
            if(pathField.getText() != ""){
                labelOfOutput.setText("The text encrypted successfully!");
                DB_RSA.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\RSA_Test\\Encrypted.txt", RSA_Logic.encryption(DB_RSA.getTheStringInsideAFile(pathField.getText())));                
            }
            else if(plainTextField.getText() != ""){
                labelOfOutput.setText("The text encrypted successfully!");
                DB_RSA.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\RSA_Test\\Encrypted.txt", RSA_Logic.encryption(plainTextField.getText()));
            }
            else{
                labelOfOutput.setText("Put something to encrypt first!");                
            }
        });
        encryptButton.setOnMouseEntered((e) -> {
            encryptButton.setStyle("-fx-background-color: #ffffff");
        });
        encryptButton.setOnMouseExited((e) -> {
            encryptButton.setStyle("-fx-background-color: #DA9100");
        });
        
        decryptButton.setOnAction((e) -> {

            if(pathField.getText() != ""){
                labelOfOutput.setText("The text decrypted successfully!");
                DB_RSA.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\RSA_Test\\Decrypted.txt", RSA_Logic.decryption(DB_RSA.getTheStringInsideAFile(pathField.getText())));                
            }
            else if(cipheredTextField.getText() != ""){
                labelOfOutput.setText("The text decrypted successfully!");
                DB_RSA.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\RSA_Test\\Decrypted.txt", RSA_Logic.decryption(cipheredTextField.getText()));
            }
            else{
                labelOfOutput.setText("Put something to encrypt first!");                
            }
        });
        decryptButton.setOnMouseEntered((e) -> {
            decryptButton.setStyle("-fx-background-color: #ffffff");
        });
        decryptButton.setOnMouseExited((e) -> {
            decryptButton.setStyle("-fx-background-color: #DA9100");
        });
        
        isPrimeButton.setOnAction((e) -> {
        if (isPrimeField.getText().matches("^\\d+$")) {
            if(RSA_Logic.isProbablePrime(new BigInteger(isPrimeField.getText()), 50)){
                labelOfOutput.setText("The number is prime");
            }
            else{
                labelOfOutput.setText("The number is not prime");
            }
        } 
        else {
            System.out.println("Input is not a number-only string.");
        }
        });
        isPrimeButton.setOnMouseEntered((e) -> {
            isPrimeButton.setStyle("-fx-background-color: #ffffff");
        });
        isPrimeButton.setOnMouseExited((e) -> {
            isPrimeButton.setStyle("-fx-background-color: #DA9100");
        });
        
        var scene = new Scene(box, 600, 770);
        stage.setResizable(false);
        stage.setScene(scene);
        
        stage.show();
    }

    public static void main(String[] args) {
        System     .    out      .     println("abcd");
        launch();
    }

}