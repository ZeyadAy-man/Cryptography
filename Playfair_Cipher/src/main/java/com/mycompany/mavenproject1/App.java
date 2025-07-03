package com.mycompany.mavenproject1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
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
        
        var attackButton = new Button("Attack");
        attackButton.setStyle("-fx-background-color: #DA9100");
        attackButton.setPadding(new Insets(10, 20, 10, 20));
        attackButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        attackButton.setMinWidth(80);
        var labelOfPathOfFileField = new Label("Enter URL of file here: ");
        var labelOfTextOfFileField = new Label("Enter the text you want in the file: ");
        var labelOfKeyField = new Label("Enter the key");
        
        var writeButton = new Button("Write");
        writeButton.setStyle("-fx-background-color: #DA9100");
        writeButton.setPadding(new Insets(10, 20, 10, 20));
        writeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        writeButton.setMinWidth(80);
        
        var keyButton = new Button("Key");
        keyButton.setStyle("-fx-background-color: #DA9100");
        keyButton.setPadding(new Insets(10, 20, 10, 20));
        keyButton.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        keyButton.setMinWidth(80);
        
        var buttonSection = new HBox();
        buttonSection.getChildren().addAll(attackButton, encryptButton, decryptButton, writeButton, keyButton);
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
            
            String regex = "\\D";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(keyTextField.getText());
            if(matcher.find()){
                labelOfOutput.setText("Output: " + "Please Enter a valid key");
            }
            else if(keyTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter a key");
            }
            else if(plainTextField.getText() != ""){
//                labelOfOutput.setText("Output: " + EncryptingAnyStringOfCharacters(plainTextField.getText(), Integer.parseInt(keyTextField.getText())));
            }
            else if(pathFileField.getText() != ""){
//                labelOfOutput.setText("Output: " + EncryptingAnyStringOfCharacters(getTheStringInsideAFile(pathFileField.getText()), Integer.parseInt(keyTextField.getText())));
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
            String regex = "\\D";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(keyTextField.getText());
            if(matcher.find()){
                labelOfOutput.setText("Output: " + "Please Enter a valid key");
            }
            else if(keyTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter a key");
            }
            else if(cipheredTextField.getText() != ""){
//                labelOfOutput.setText("Output: " + DecryptingAnyStringOfCharacters(cipheredTextField.getText(), Integer.parseInt(keyTextField.getText())));
            }
            else if(pathFileField.getText() != ""){
//                labelOfOutput.setText("Output: " + DecryptingAnyStringOfCharacters(getTheStringInsideAFile(pathFileField.getText()), Integer.parseInt(keyTextField.getText())));
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
        attackButton.setOnAction((e) -> {      
            if(cipheredTextField.getText() != ""){
//                labelOfAttackProbabilities.setText("All the probabilities are: " + attack(cipheredTextField.getText()));
            }
            else if(pathFileField.getText() != ""){
//                labelOfAttackProbabilities.setText("All the probabilities are: " + attack(getTheStringInsideAFile(pathFileField.getText())));
            }
            else if(cipheredTextField.getText() == ""){
                labelOfAttackProbabilities.setText("All the probabilities are: " + "Please enter ciphered text to attack!");
            }
            else if(cipheredTextField.getText() != "" && plainTextField.getText() != ""){
                labelOfAttackProbabilities.setText("All the probabilities are: " + "The key is " + (char)(Math.abs(plainTextField.getText().charAt(0) - cipheredTextField.getText().charAt(0))));                
            }
        });
        keyButton.setOnAction((e) -> {
            if(cipheredTextField.getText() != "" && plainTextField.getText() != ""){
                labelOfAttackProbabilities.setText("All the probabilities are: " + "The key is " + (int)(Math.abs(plainTextField.getText().charAt(0) - cipheredTextField.getText().charAt(0))));                
            }
            else if(pathFileField.getText() != "" && plainTextField.getText() != ""){
                labelOfAttackProbabilities.setText("All the probabilities are: " + "The key is " + (int)(Math.abs(plainTextField.getText().charAt(0) - getTheStringInsideAFile(pathFileField.getText()).charAt(0))));                                
            }
            else if(pathFileField.getText() != "" && cipheredTextField.getText() != ""){
                labelOfAttackProbabilities.setText("All the probabilities are: " + "The key is " + (int)(Math.abs(cipheredTextField.getText().charAt(0) - getTheStringInsideAFile(pathFileField.getText()).charAt(0))));                                
            }
        });
        writeButton.setOnAction((e) -> {
            writeIntoAFile(pathFileField.getText(), textOfPathFileField.getText());
            labelOfOutput.setText("Output: " + "The text has been written successfully.");
        });
        attackButton.setOnMouseEntered((e) -> {
            attackButton.setStyle("-fx-background-color: #ffffff");
        });
        attackButton.setOnMouseExited((e) -> {
            attackButton.setStyle("-fx-background-color: #DA9100");
        });
        writeButton.setOnMouseEntered((e) -> {
            writeButton.setStyle("-fx-background-color: #ffffff");
        });
        writeButton.setOnMouseExited((e) -> {
            writeButton.setStyle("-fx-background-color: #DA9100");
        });
        keyButton.setOnMouseEntered((e) -> {
            keyButton.setStyle("-fx-background-color: #ffffff");
        });
        keyButton.setOnMouseExited((e) -> {
            keyButton.setStyle("-fx-background-color: #DA9100");
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
    public static String RemoveDuplicating(String s){
        String stringWithoutDuplicating = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                
            }
            else if(!stringWithoutDuplicating.contains(s.charAt(i) + "")){
                stringWithoutDuplicating += s.charAt(i);
            }
        }
        return stringWithoutDuplicating;
    }
    public static String keyMapContent(String keyWithoutDuplicating){
        
        String keyMapContent = keyWithoutDuplicating;
        for(int i = 97; i < 123; i++){
            if(i == 106){
                
            }
            else if(!keyMapContent.contains((char)(i) + "")){
                keyMapContent += (char)(i);
            }
        }
        
        return keyMapContent;
    }
    public static String fillerTextPlain(String textPlainWithoutFiller){                
        int i = 0, j = 1;
        while(i < textPlainWithoutFiller.length() && j < textPlainWithoutFiller.length()){
            if(textPlainWithoutFiller.charAt(i) == textPlainWithoutFiller.charAt(j)){
                textPlainWithoutFiller = textPlainWithoutFiller.substring(0, i + 1) + "x" + textPlainWithoutFiller.substring(j, textPlainWithoutFiller.length());
                i += 2;
                j += 2;
            }
            else{
                i++;
                j++;
            }
        }
        
        if(textPlainWithoutFiller.length() % 2 != 0){
            textPlainWithoutFiller += 'x';
        }
        return textPlainWithoutFiller;
    }
    public static String encryptingUsingPlayFair(String textPlainWithoutFiller, String keyWithDuplicating){
        char[][] keyMapContentArray = new char[5][5];
        String textPlainWithFiller = fillerTextPlain(textPlainWithoutFiller);
        String keyWithoutDuplicating = RemoveDuplicating(keyWithDuplicating);
        String keyMapContent = keyMapContent(keyWithoutDuplicating);
        String cipheredText = "";
        int k = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                keyMapContentArray[i][j] = keyMapContent.charAt(k);
                k++;
            }
        }
        int i1 = 0, j1 = 0;
        int i2 = 0, j2 = 0;
        for(int i = 0; i < textPlainWithFiller.length(); i += 2){
            for(int j = 0; j < 5; j++){
                for(int l = 0; l < 5; l++){
                    if(textPlainWithFiller.charAt(i) == keyMapContentArray[j][l]){
                        i1 = j;
                        j1 = l;
                    }
                    if(textPlainWithFiller.charAt(i + 1) ==  keyMapContentArray[j][l]){
                        i2 = j;
                        j2 = l;
                    }
                }
            }
            if(j1 == j2){
                if(i1 == 4){
                    cipheredText += keyMapContentArray[0][j1];
                }
                else{
                    cipheredText += keyMapContentArray[i1 + 1][j1];                
                }
                if(i2 == 4){
                    cipheredText += keyMapContentArray[0][j2];                
                }
                else{
                    cipheredText += keyMapContentArray[i2 + 1][j2];                
                }
            }
            else if(i1 == i2){
                if(j1 == 4){
                    cipheredText += keyMapContentArray[i1][0];
                }
                else{
                    cipheredText += keyMapContentArray[i1][j1 + 1];
                }
                if(j2 == 4){
                    cipheredText += keyMapContentArray[i2][0];
                }
                else{
                    cipheredText += keyMapContentArray[i2][j2 + 1];   
                }
            }
            else{
                cipheredText += keyMapContentArray[i1][j2] + "" + keyMapContentArray[i2][j1];
            }
        }
        
        return cipheredText ;
    }
    
        //not done yet
        public static String decryptingUsingPlayFair(String cipheredText, String keyWithDuplicating){
        char[][] keyMapContentArray = new char[5][5];
        String keyWithoutDuplicating = RemoveDuplicating(keyWithDuplicating);
        String keyMapContent = keyMapContent(keyWithoutDuplicating);
        String textPlain = "";
        int k = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                keyMapContentArray[i][j] = keyMapContent.charAt(k);
                k++;
            }
        }
        int i1 = 0, j1 = 0;
        int i2 = 0, j2 = 0;
        for(int i = 0; i < cipheredText.length(); i += 2){
            for(int j = 0; j < 5; j++){
                for(int l = 0; l < 5; l++){
                    if(cipheredText.charAt(i) == keyMapContentArray[j][l]){
                        i1 = j;
                        j1 = l;
                    }
                    if(cipheredText.charAt(i + 1) ==  keyMapContentArray[j][l]){
                        i2 = j;
                        j2 = l;
                    }
                }
            }
            if(j1 == j2){
                if(i1 == 0){
                    textPlain += keyMapContentArray[4][j1];
                }
                else{
                    textPlain += keyMapContentArray[i1 - 1][j1];                
                }
                if(i2 == 0){
                    textPlain += keyMapContentArray[4][j2];                
                }
                else{
                    textPlain += keyMapContentArray[i2 - 1][j2];                
                }
            }
            else if(i1 == i2){
                if(j1 == 0){
                    textPlain += keyMapContentArray[i1][4];
                }
                else{
                    textPlain += keyMapContentArray[i1][j1 - 1];
                }
                if(j2 == 0){
                    textPlain += keyMapContentArray[i2][4];
                }
                else{
                    textPlain += keyMapContentArray[i2][j2 - 1];   
                }
            }
            else{
                textPlain += keyMapContentArray[i1][j2] + "" + keyMapContentArray[i2][j1];
            }
        }
        
        return textPlain ;
    }
    public static void main(String[] args) {
        char[][] keyMapContentArray = new char[5][5];
        String keyWithDuplicating = "neso academy";
        String keyWithoutDuplicating = RemoveDuplicating(keyWithDuplicating);
        String keyMapContent = keyMapContent(keyWithoutDuplicating);
        int k = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                keyMapContentArray[i][j] = keyMapContent.charAt(k);
                k++;
            }
        }
//        encryptingUsingPlayFair("aaaabxy");
        
//        System.out.println(RemoveDuplicating("neso academy")); //aa aa bx yx -> ax ax ax ab xy
        launch();
    }
}
