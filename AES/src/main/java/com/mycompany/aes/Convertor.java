/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author zizoa
 */
public class Convertor {
    public static String textToBits(String text) {
        //done
        String bits = "";
        for(int i = 0; i < text.length(); i++){
            int length = (int)(text.charAt(i));
            if(length - 128 >= 0){
                bits += "1";
                length -= 128;
            }else{
                bits += "0";
            }
            if(length - 64 >= 0){
                bits += "1";
                length -= 64;
            }else{
                bits += "0";
            }
            if(length - 32 >= 0){
                bits += "1";
                length -= 32;
            }else{
                bits += "0";
            }
            if(length - 16 >= 0){
                bits += "1";
                length -= 16;
            }else{
                bits += "0";
            }
            if(length - 8 >= 0){
                bits += "1";
                length -= 8;
            }else{
                bits += "0";
            }
            if(length - 4 >= 0){
                bits += "1";
                length -= 4;
            }else{
                bits += "0";
            }
            if(length - 2 >= 0){
                bits += "1";
                length -= 2;
            }else{
                bits += "0";
            }
            if(length == 1){
                bits += "1";
            }else{
                bits += "0";
            }
        }

        return bits.toString();
    }
    
    public static String binaryToText(String binaryString) {
        String text = "";

        int decimal = Integer.parseInt(binaryString, 2);
        text += (char) decimal;
        
        return text;
    }
    
    public static void bitsToMatrix(String bits, String[][] matrix){
        //done
        int m = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = bits.substring(m, m + 8);
                m += 8;
            }
        }
    }
    public static void displayMatrix(String [][] matrix){
        //done
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static int bitsToDecimal(String bits){
        return Integer.parseInt(bits, 2);
    }
    public static String decimalToBits(int decimal){
        String bits = Integer.toBinaryString(decimal);
        if(bits.length() == 1){
            return "000" + bits;
        }
        else if(bits.length() == 2){
            return "00" + bits;
        }
        else if(bits.length() == 3){
            return "0" + bits;
        }
        return bits;
    }
    //done
    public static String binaryToHexadecimal(String binary){
        int length = binary.length();
        String hexadecimal = "";
        if(length % 4 == 1){
            binary = "000" + binary;
        }
        if(length % 4 == 2){
            binary = "00" + binary;
        }
        if(length % 4 == 3){
            binary = "0" + binary;
        }
        length = binary.length();
        for(int i = 0; i < length; i += 4){
            int decimal = Integer.parseInt(binary.substring(i, i + 4), 2);
            hexadecimal += Integer.toHexString(decimal).toUpperCase();
        }
        return hexadecimal;
    }
    public static int stringBitsToIntegerBits(String bits){
        //done
        return Integer.parseInt(bits);
    }
    public static String binaryToHexadecimalFormat(String bits){
        //done
        long decimal = Long.parseLong(bits, 2);
        String hexadecimal = "";
        if(bits.length() / 4 == 2){
             hexadecimal = String.format("0x%02X", decimal);
        }
        else if(bits.length() / 4 == 8){
             hexadecimal = String.format("0x%08X", decimal);
        }
        else if(bits.length() / 4 == 32){
             hexadecimal = String.format("0x%032X", decimal);
        }
        return hexadecimal;
    }
    public static String hexadecimalToBinaryFormat(String hexadecimal){
        //done
        hexadecimal = hexadecimal.replace("0x", "");
        long decimal = Long.parseLong(hexadecimal, 16);
        String binary = "";
        if(hexadecimal.length() == 2){
            binary = String.format("%8s", Long.toBinaryString(decimal)).replace(' ', '0');
        }
        else if(hexadecimal.length() == 1){
            binary = String.format("%4s", Long.toBinaryString(decimal)).replace(' ', '0');
        }
        else if(hexadecimal.length() == 8){
            binary = String.format("%32s", Long.toBinaryString(decimal)).replace(' ', '0');
        }
        return binary;
    }
    public static int hexadecimalToDecimal(String hexadecimal){
        //done
        return bitsToDecimal(hexadecimalToBinaryFormat(hexadecimal));
    }
    public static void wordToMatrix(String[] words, String[][] keyInMatrix){
        int characterPosition = 2;
        int wordPosition = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                keyInMatrix[j][i] = Convertor.hexadecimalToBinaryFormat("0x" + words[wordPosition].charAt(characterPosition) + words[wordPosition].charAt(characterPosition + 1));
                System.out.print("Character: " + words[wordPosition].charAt(characterPosition) + words[wordPosition].charAt(characterPosition + 1) + " ");
                characterPosition += 2;
            }
            System.out.println("");
            characterPosition = 2;
            wordPosition++;
        }
        System.out.println("");
    }
    public static String[][] binaryMatrixToHexadecimalMatrix(String[][] s){
        String [][] result = new String[4][4];
        for(int i = 0; i < s.length; i++){
            for(int j = 0; j < s[i].length; j++){
                result[i][j] = s[i][j];
                result[i][j] = binaryToHexadecimalFormat(result[i][j]);
            }
        }
        return result;
    }
    public static String[][] hexadecimalMatrixToBinaryMatrix(String[][] s){
        String [][] result = new String[4][4];
        for(int i = 0; i < s.length; i++){
            for(int j = 0; j < s[i].length; j++){
                result[i][j] = s[i][j];
                result[i][j] = hexadecimalToBinaryFormat(result[i][j]);                
            }
        }
        return result;
    }
    public static String matrixToHexadecimal(String[][] state){
        String hexadecimal = "";
        for(int i = 0; i < state.length; i++){
            for(int j = 0; j < state.length; j++){
                hexadecimal += Convertor.binaryToHexadecimalFormat(state[j][i]);
            }
        }
        return "0x" + hexadecimal;
    }
}
