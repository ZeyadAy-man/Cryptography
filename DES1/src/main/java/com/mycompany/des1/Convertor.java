package com.mycompany.des1;

import java.nio.charset.StandardCharsets;

public class Convertor {
    static String textToHexadecimal(String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }

    static String HexadecimalToBinary(String hexText) {
        StringBuilder binaryString = new StringBuilder(hexText.length() * 4);
        for (char c : hexText.toCharArray()) {
            int decimal = Character.digit(c, 16);
            binaryString.append(String.format("%4s", Integer.toBinaryString(decimal)).replace(' ', '0'));
        }
        return binaryString.toString();
    }

    public static String textToBinary(String text) {
        StringBuilder binary = new StringBuilder();

        for (char c : text.toCharArray()) {
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binary.append(binaryString);
        }

        return binary.toString();
    }

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }


    public static String binaryToText(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteStr = binary.substring(i, Math.min(i + 8, binary.length()));
            byteStr = String.format("%-8s", byteStr).replace(' ', '0');
            int charCode = Integer.parseInt(byteStr, 2);
            text.append((char) charCode);
        }
        return text.toString();
    }

    public static String binaryToHexadecimal(String binary) {
        StringBuilder binaryBuilder = new StringBuilder(binary);
        while (binaryBuilder.length() % 4 != 0) {
            binaryBuilder.insert(0, "0");
        }
        binary = binaryBuilder.toString();

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binary.length(); i += 4) {
            String chunk = binary.substring(i, i + 4);
            int decimal = Integer.parseInt(chunk, 2);
            hex.append(Integer.toHexString(decimal).toLowerCase());
        }

        return hex.toString();
    }

    public static String HexadecimalToText(String hex) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String hexPair = hex.substring(i, i + 2);
            int decimal = Integer.parseInt(hexPair, 16);
            text.append((char) decimal);
        }

        return text.toString();
    }
}
