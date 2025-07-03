package com.mycompany.aes;
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

    final static String[][] MIX_COLUMNS_MATRIX_HEX = {
        {"0x02", "0x03", "0x01", "0x01"},
        {"0x01", "0x02", "0x03", "0x01"},
        {"0x01", "0x01", "0x02", "0x03"},
        {"0x03", "0x01", "0x01", "0x02"}
    };
    
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
        var keyTextField = new TextField();
        
        plainTextField.setPadding(new Insets(8));
        cipheredTextField.setPadding(new Insets(8));
        keyTextField.setPadding(new Insets(8));
        
        plainTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        cipheredTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        keyTextField.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        
        plainTextField.setMinWidth(350);
        plainTextField.setMaxWidth(350);
        cipheredTextField.setMinWidth(350);
        cipheredTextField.setMaxWidth(350);

        keyTextField.setMinWidth(350);
        keyTextField.setMaxWidth(350);
        
        labelOfPlainTextField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfCipheredTextField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfPlainTextField.setTextFill(Color.web("#DA9100"));
        labelOfCipheredTextField.setTextFill(Color.web("#DA9100"));
        labelOfKeyField.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelOfKeyField.setTextFill(Color.web("#DA9100"));
        
        var fieldsSection = new VBox();
        fieldsSection.getChildren().addAll(labelOfPlainTextField, plainTextField,
             labelOfCipheredTextField, cipheredTextField, labelOfKeyField, keyTextField);
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
            String plainTextInBinary = AESLogic.fillerText(Convertor.textToBits(plainTextField.getText()));
            String encryptedText = "";
            int numberOfParts = plainTextInBinary.length() / 128;
            if(keyTextField.getText() == ""){
                labelOfOutput.setText("Output: " + "Please Enter a key");
            }
            else{
                for(int i = 0; i < numberOfParts; i++){
                    encryptedText = AESLogic.encrypt(plainTextInBinary.substring(i * 128, (i + 1) * 128), keyTextField.getText()).substring(0, 16);
                }
                labelOfOutput.setText("result is: " + encryptedText);
                System.out.println(encryptedText.length());
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
//            else if(pathFileField.getText() != ""){
//                Long startTime = System.nanoTime();
////                labelOfOutput.setText("Output: " + decryptingUsingPlayFair(getTheStringInsideAFile(pathFileField.getText()), (keyTextField.getText())));
//                Long endTime = System.nanoTime();
//                labelOfOutput.setText(labelOfOutput.getText() + " The time is: " + (endTime - startTime) / 1000 + " microseconds.");
//            }
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
            String s = keyTextField.getText();
            String fileText = "";
            String [][] matrix = new String[4][4];  
            String [] subKeys = new String[44];
            if(s.length() < 16){
                s = Convertor.textToBits(s);
                int length = 128 - s.length();
                for(int i = 0; i < length; i++){
                    s = "0" + s;
                }
                Convertor.bitsToMatrix(s, matrix);
                System.out.println("lol");
                AESLogic.keyExpansion(matrix, subKeys);
                for(int i = 0; i < subKeys.length; i++){
                     fileText += subKeys[i] + "\n";
                }
                DB_AES.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\CryptoTest\\KeyExpansion.txt", fileText);
                labelOfOutput.setText("Output: " + "The text has been written successfully.");                
            }
            else{
                s = Convertor.textToBits(s.substring(0, 16));
                System.out.println(s);
                Convertor.bitsToMatrix(s, matrix);
                AESLogic.keyExpansion(matrix, subKeys);
                for(int i = 0; i < subKeys.length; i++){
                     fileText += subKeys[i] + "\n";
                }
                DB_AES.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\CryptoTest\\KeyExpansion.txt", fileText);
                labelOfOutput.setText("Output: " + "The text has been written successfully.");
            }

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
//        String x = Convertor.textToBits("Zeyad ayman");
        String [][]matrix = new String[4][4];
//        Convertor.bitsToMatrix("11010010010101001110100001110100010101100110101001100001011011100011000100101110011000001110101001010011110001000010111101001001", matrix);
//        Convertor.displayMatrix(matrix);
//        System.out.println(x);
//        System.out.println(Convertor.bitsToText(x));
//        System.out.println(AESLogic.XOR("01110001", "11101110"));
//        stage.show();
        String [][] a = {
            {"11001010", "10101100", "11110000", "00011111"},
            {"11100011", "01011010", "10010101", "11100101"},
            {"00011100", "11110101", "11000011", "10101010"},
            {"10111001", "01101010", "10001111", "11010101"}
        };
        String [][] b = {
            {"00000000", "11110000", "01010101", "00110011"},
            {"00011111", "11001100", "11100011", "01010101"},
            {"11000000", "10110101", "10011010", "01111000"},
            {"00101110", "10110011", "11111100", "10001010"}
        };
        String [][] zeroMatrix = {
            {"01010011", "01010010", "01011001", "00110100"},
            {"01000101", "01000101", "00110001", "00110101"},
            {"01000011", "01001011", "00110010", "00110110"},
            {"01010101", "01000101", "00110011", "00111000"}
        };
        
        String[][] stateHex = {
            {"0x87", "0xF2", "0x4D", "0x97"},
            {"0x6E", "0x4C", "0x90", "0xEC"},
            {"0x46", "0xE7", "0x4A", "0xC3"},
            {"0xA6", "0x8C", "0xD8", "0x95"}
        };
//        AESLogic.shiftRows(stateHex);
//        Convertor.displayMatrix(stateHex);
        String binary = Convertor.textToBits("abcdefghijklmmno");
        Convertor.bitsToMatrix(binary, matrix);
//        String [] words = new String[44];
//        AESLogic.keyExpansion(matrix, words);
        String [][] c = new String[4][4];
        String [][] m = new String[4][4]; 
        String[][] resultHex = new String[4][4];
        
//        AESLogic.mixColumns(stateHex);
//        Convertor.displayMatrix(stateHex);
//        System.out.println(Convertor.matrixToHexadecimal(stateHex));
//        Convertor.displayMatrix(resultHex);
//        System.out.println(AESLogic.encrypt("abcde", "a"));
        String [] words = {"0x00ab00cd", "0x00000000", "0x00000000", "0x00000000"};
        String [][] testMatrix = {
            {"00000000", "00000000", "00000000", "00000000"},
            {"00000000", "00000000", "00000000", "00000000"},
            {"00000000", "00000000", "00000000", "00000000"},
            {"00000000", "00000000", "00000000", "00000000"}
        };
        String [][] result = new String[4][4];
        String [][] keyInMatrix = new String[4][4];
        Convertor.wordToMatrix(words, keyInMatrix);
        Convertor.displayMatrix(keyInMatrix);
//        AESLogic.substituteBytes(testMatrix);
//        result = (Convertor.binaryMatrixToHexadecimalMatrix(testMatrix));
//        Convertor.displayMatrix(result);
//        System.out.println("");
//        
//        AESLogic.shiftRows(testMatrix);
//        result = (Convertor.binaryMatrixToHexadecimalMatrix(testMatrix));
//        Convertor.displayMatrix(result);
//        System.out.println("");
//        
        
//        AESLogic.inverseMixColumns(stateHex);
//        Convertor.displayMatrix(stateHex);
//        System.out.println("lol");
//        result = (Convertor.binaryMatrixToHexadecimalMatrix(stateHex));
//        Convertor.displayMatrix(stateHex);
//        System.out.println("");
//        AESLogic.mixColumns(stateHex);
//        AESLogic.inverseShiftRows(matrix, result);
//        System.out.println("##########################");
//        Convertor.displayMatrix(Convertor.binaryMatrixToHexadecimalMatrix(stateHex));
        
        
//        result = Convertor.hexadecimalMatrixToBinaryMatrix(result);
//        AESLogic.inverseMixColumns(result);
//        System.out.println("lol");
//        result = (Convertor.binaryMatrixToHexadecimalMatrix(result));
        
//        
//        AESLogic.addRoundKey(keyInMatrix, testMatrix);
//        result = (Convertor.binaryMatrixToHexadecimalMatrix(testMatrix));
//        Convertor.displayMatrix(result);
//        System.out.println("");
        //0x9B9898C9 0xF9FBFBAA 0x9B9898C9 0xF9FBFBAA
//        Convertor.wordToMatrix(words, c);
//        Convertor.displayMatrix(keyInMatrix);
//        System.out.println(AESLogic.encrypt("", ""));
//        DB_AES.writeIntoAFile("", output);
//        System.out.println(Convertor.binaryToText("11001000"));
//        Convertor.wordToMatrix({}, keyInMatrix);
    }
    public static void main(String[] args) {
        launch();
    }

}