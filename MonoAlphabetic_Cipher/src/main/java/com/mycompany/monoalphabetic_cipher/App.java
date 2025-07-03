package com.mycompany.monoalphabetic_cipher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
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
        var labelOfCipheredTextField = new Label("Enter ciphered text here: ");

        var labelOfPathOfFileField = new Label("Enter URL of file here: ");
        var labelOfTextOfFileField = new Label("Enter the text you want in the file: ");
        var labelOfKeyField = new Label("Enter the key");
        
        var writeButton = new Button("Write");
        writeButton.setStyle("-fx-background-color: #DA9100");
        writeButton.setPadding(new Insets(10, 20, 10, 20));
        writeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        writeButton.setMinWidth(80);
        
        var buttonSection = new HBox();
        buttonSection.getChildren().addAll( encryptButton, decryptButton, writeButton);
        buttonSection.setAlignment(Pos.CENTER);
        buttonSection.setSpacing(14);
        VBox.setMargin(buttonSection, new Insets(10, 0, 0, 0));
        
        var cipheredTextField = new TextField();        
        var plainTextField = new TextField();
        var pathFileField = new TextField();
        var textOfPathFileField = new TextField();
        var keyTextField = new TextField();
        
        plainTextField.setPadding(new Insets(8));
        cipheredTextField.setPadding(new Insets(8));
        pathFileField.setPadding(new Insets(8));
        textOfPathFileField.setPadding(new Insets(8));
        keyTextField.setPadding(new Insets(8));
        
        plainTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        cipheredTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        pathFileField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        textOfPathFileField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        keyTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        
        plainTextField.setMinWidth(350);
        plainTextField.setMaxWidth(350);
        cipheredTextField.setMinWidth(350);
        cipheredTextField.setMaxWidth(350);
        pathFileField.setMinWidth(350);
        pathFileField.setMaxWidth(350);
        textOfPathFileField.setMinWidth(350);
        textOfPathFileField.setMaxWidth(350);
        keyTextField.setMinWidth(350);
        keyTextField.setMaxWidth(350);
        
        labelOfPlainTextField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfCipheredTextField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfPathOfFileField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfPathOfFileField.setTextFill(Color.web("#DA9100"));
        labelOfPlainTextField.setTextFill(Color.web("#DA9100"));
        labelOfCipheredTextField.setTextFill(Color.web("#DA9100"));
        labelOfTextOfFileField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfTextOfFileField.setTextFill(Color.web("#DA9100"));
        labelOfKeyField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfKeyField.setTextFill(Color.web("#DA9100"));
        
        var fieldsSection = new VBox();
        fieldsSection.getChildren().addAll(labelOfPlainTextField, plainTextField,
             labelOfCipheredTextField, cipheredTextField, labelOfPathOfFileField,
                pathFileField, labelOfTextOfFileField, textOfPathFileField, 
                labelOfKeyField, keyTextField);
        fieldsSection.setSpacing(10);
        VBox.setMargin(fieldsSection, new Insets(10, 0, 10, 40));
        
        
        var labelOfProgram = new Label("Cipherno");
        labelOfProgram.setTextFill(Color.web("#DA9100"));
        labelOfProgram.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        var labelOfOutput = new Label("Output: " + output);
        labelOfOutput.setTextFill(Color.web("#DA9100"));
        labelOfOutput.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        var labelOfAttackProbabilities = new Label("All the probabilities are: ");
        labelOfAttackProbabilities.setTextFill(Color.web("#DA9100"));
        labelOfAttackProbabilities.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        HBox titleSection = new HBox();
        titleSection.getChildren().setAll(labelOfProgram);
        titleSection.setAlignment(Pos.CENTER);
        VBox.setMargin(titleSection, new Insets(30, 0, 10, 0));
        outputSection.getChildren().addAll(labelOfOutput, labelOfAttackProbabilities);
        
        VBox.setMargin(outputSection, new Insets(30, 0, 10, 40));
        outputSection.setSpacing(10);
        VBox box = new VBox(titleSection,
                fieldsSection, buttonSection, outputSection);
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        encryptButton.setOnAction((e) -> {
            
            String regex = "^[a-z]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(keyTextField.getText());
            if(matcher.find()){
                labelOfOutput.setText("Output: " + "Please Enter a valid key");
            }
            else if(keyTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter a key");
            }
            else if(plainTextField.getText() != ""){
                Long startTime = System.nanoTime();
//                labelOfOutput.setText("Output: " + encryptingUsingPlayFair(plainTextField.getText(), (keyTextField.getText())));
                Long endTime = System.nanoTime();
                labelOfOutput.setText(labelOfOutput.getText() + " The time is: " + (endTime - startTime) / 1000 + " microseconds.");
            }
            else if(pathFileField.getText() != ""){
                Long startTime = System.nanoTime();
//                labelOfOutput.setText("Output: " + encryptingUsingPlayFair(getTheStringInsideAFile(pathFileField.getText()), (keyTextField.getText())));
                Long endTime = System.nanoTime();
                labelOfOutput.setText(labelOfOutput.getText() + " The time is: " + (endTime - startTime) / 1000 + " microseconds.");                
            }
            else if(plainTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter something to encrypt!");
            }
        });
        encryptButton.setOnMouseEntered((e) -> {
            encryptButton.setStyle("-fx-background-color: #ffffff");
        });
        encryptButton.setOnMouseExited((e) -> {
            encryptButton.setStyle("-fx-background-color: #DA9100");
        });
        
        decryptButton.setOnAction((e) -> {
            String regex = "^[a-z]+$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(keyTextField.getText());
            if(matcher.find()){
                labelOfOutput.setText("Output: " + "Please Enter a valid key");
            }
            else if(keyTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter a key");
            }
            else if(cipheredTextField.getText() != ""){
                Long startTime = System.nanoTime();
//                labelOfOutput.setText("Output: " + decryptingUsingPlayFair(cipheredTextField.getText(), (keyTextField.getText())));
                Long endTime = System.nanoTime();
                labelOfOutput.setText(labelOfOutput.getText() + " The time is: " + (endTime - startTime) / 1000 + " microseconds.");
            }
            else if(pathFileField.getText() != ""){
                Long startTime = System.nanoTime();
//                labelOfOutput.setText("Output: " + decryptingUsingPlayFair(getTheStringInsideAFile(pathFileField.getText()), (keyTextField.getText())));
                Long endTime = System.nanoTime();
                labelOfOutput.setText(labelOfOutput.getText() + " The time is: " + (endTime - startTime) / 1000 + " microseconds.");
            }
            else if(cipheredTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter something to decrypt!");
            }
        });
        decryptButton.setOnMouseEntered((e) -> {
            decryptButton.setStyle("-fx-background-color: #ffffff");
        });
        decryptButton.setOnMouseExited((e) -> {
            decryptButton.setStyle("-fx-background-color: #DA9100");
        });
        writeButton.setOnAction((e) -> {
            writeIntoAFile(pathFileField.getText(), textOfPathFileField.getText());
            labelOfOutput.setText("Output: " + "The text has been written successfully.");
        });
        writeButton.setOnMouseEntered((e) -> {
            writeButton.setStyle("-fx-background-color: #ffffff");
        });
        writeButton.setOnMouseExited((e) -> {
            writeButton.setStyle("-fx-background-color: #DA9100");
        });
        
        var scene = new Scene(box, 600, 770);
        stage.setResizable(false);
        stage.setScene(scene);
        
        stage.show();
    }
    public static String getTheStringInsideAFile(String path){
        String s = "";
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                s += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void writeIntoAFile(String path, String text){
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static String RemoveDuplicating(String s){
//        String stringWithoutDuplicating = "";
//        for(int i = 0; i < s.length(); i++){
//            if(!(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')){
//                
//            }
//            else if(!stringWithoutDuplicating.contains(s.charAt(i) + "")){
//                stringWithoutDuplicating += s.charAt(i);
//            }
//        }
//        return stringWithoutDuplicating;
//    }
    
//    public static String keyMapContent(String keyWithoutDuplicating){
//        
//        String keyMapContent = keyWithoutDuplicating;
//        for(int i = 97; i < 123; i++){
//            if(i == 106){
//                
//            }
//            else if(!keyMapContent.contains((char)(i) + "")){
//                keyMapContent += (char)(i);
//            }
//        }
//        
//        return keyMapContent;
//    }
  
    public static String getPlainTextWithoutSpecialCharacters(String plainTextWithSpecialCharacters){
        String plainTextWithoutSpecialCharacters = "";
        for(int i = 0; i < plainTextWithSpecialCharacters.length(); i++){
            if(plainTextWithSpecialCharacters.charAt(i) >= 'a' && plainTextWithSpecialCharacters.charAt(i) <= 'z'){
                plainTextWithoutSpecialCharacters += plainTextWithSpecialCharacters.charAt(i);
            }
        }
        return plainTextWithoutSpecialCharacters;
    }
    
    public static void fillMatrixOfPlainText(String plainTextWithoutSpecialCharacters, int[][] matrixOfPlainText){
        int k = 0;
        for(int m = plainTextWithoutSpecialCharacters.length() - 1; m < matrixOfPlainText.length * matrixOfPlainText[0].length; m++){
            plainTextWithoutSpecialCharacters += 'x';
        }
        System.out.println(plainTextWithoutSpecialCharacters);
        for(int i = 0; i < matrixOfPlainText.length; i++){
            for(int j = 0; j < matrixOfPlainText[i].length; j++){
                matrixOfPlainText[i][j] = plainTextWithoutSpecialCharacters.charAt(k) - 97;
                k++;
            }
        }
    }
    public static int getColumnsNumber(String plainTextWithoutSpecialCharacters){
        
        
        
        return 0;
    }
    public static void fillMatrixOfKey(String keyWithoutSpecialCharacters, int [][] matrixOfKey){
        int lengthOfSquareRootOfKeyInInteger = (int)Math.sqrt(keyWithoutSpecialCharacters.length());
        double lengthOfSquareRootOfKeyInDouble = Math.sqrt(keyWithoutSpecialCharacters.length());
        int k = 0;
        if(lengthOfSquareRootOfKeyInDouble == lengthOfSquareRootOfKeyInInteger){
            for(int i = 0; i < matrixOfKey.length; i++){
                for(int j = 0; j < matrixOfKey[i].length; j++){
                    matrixOfKey[i][j] = keyWithoutSpecialCharacters.charAt(k) - 97;
                    k++;
                }
            }
        }
        else{
            for(int i = 0; i < matrixOfKey.length; i++){
                for(int j = 0; j < matrixOfKey[i].length; j++){
                    matrixOfKey[i][j] = keyWithoutSpecialCharacters.charAt(k) - 97;
                    k++;
                }
                k = 0;
            }
        }
    }
    public static int getTheNumberOfRowsInKeyMatrix(String keyWithoutSpecialCharacters){
        int lengthOfSquareRootOfKeyInInteger = (int)Math.sqrt(keyWithoutSpecialCharacters.length());
        double lengthOfSquareRootOfKeyInDouble = Math.sqrt(keyWithoutSpecialCharacters.length());
        if(lengthOfSquareRootOfKeyInDouble == lengthOfSquareRootOfKeyInInteger){
            return lengthOfSquareRootOfKeyInInteger;
        }
        else{
            
        }
        return 0;
    }
    public static void main(String[] args) {
        int [][] matrixOfPlainText = new int[4][3];
        int [][] matrixOfKey = new int[2][2];
        fillMatrixOfKey("abcd", matrixOfKey);
        String plainText = "adlkeijkl";
        String plainTextWithoutSpecialCases = getPlainTextWithoutSpecialCharacters(plainText);
        fillMatrixOfPlainText(getPlainTextWithoutSpecialCharacters(plainText), matrixOfPlainText);
        for(int i = 0; i < matrixOfKey.length; i++){
            for(int j = 0; j < matrixOfKey[i].length; j++){
                System.out.println((char)(matrixOfKey[i][j] + 97));
            }
        }
        launch();
    }
}
