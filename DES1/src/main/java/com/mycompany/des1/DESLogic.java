package com.mycompany.des1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DESLogic {

    public static String encrypt(String text, String key) {
        text = Convertor.textToHexadecimal(text);
        String binaryText = Convertor.HexadecimalToBinary(text);
        String[] blocks = getBlocks(binaryText);
        key = Convertor.textToHexadecimal(key);
        String keyBinary = Convertor.HexadecimalToBinary(key);
        keyBinary = getValidKey(keyBinary);
        String[] subKeys = generateSubKeys(keyBinary, true);
        int index = 0;
        for (String block : blocks) {
            String permutedBlock = Permutations.initialPermutation(block);
            String L = permutedBlock.substring(0, 32);
            String R = permutedBlock.substring(32);
            for (int i = 0; i < 16; i++) {
                String round = round(L, R, subKeys[i]);
                L = round.substring(0, 32);
                R = round.substring(32);
            }
            String result = Permutations.swap(L + R);
            result = Permutations.inverseInitialPermutation(result);
            result = Convertor.binaryToHexadecimal(result);
            blocks[index++] = result;
        }

        return String.join("", blocks);
    }

    public static String decrypt(String text, String key) {
        String binaryText = Convertor.HexadecimalToBinary(text);
        String[] blocks = getBlocks(binaryText);
        key = Convertor.textToHexadecimal(key);
        String keyBinary = Convertor.HexadecimalToBinary(key);
        keyBinary = getValidKey(keyBinary);
        String[] subKeys = generateSubKeys(keyBinary, false);

        int index = 0;
        for (String block : blocks) {
            String permutedBlock = Permutations.initialPermutation(block);
            String L = permutedBlock.substring(0, 32);
            String R = permutedBlock.substring(32);

            for (int i = 15; i >= 0; i--) {
                String round = round(L, R, subKeys[i]);
                L = round.substring(0, 32);
                R = round.substring(32);
            }

            String result = Permutations.swap(L + R);
            result = Permutations.inverseInitialPermutation(result);
            result = Convertor.binaryToHexadecimal(result);
            result = Convertor.HexadecimalToText(result);
            blocks[index++] = result;
        }
        return String.join("", blocks).trim();
    }
    public static String shiftLeft(String text, int roundShift) {
        int [] shift = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        return text.substring(shift[roundShift]) + text.substring(0, shift[roundShift]);
    }

    public static String round(String L, String R, String key) {
        return R + xor(L, f(R, key));
    }

    public static String f(String R, String key) {
        R = Permutations.expansionPermutation(R);
        String result = xor(R, key);
        result = Permutations.substitutionBox(result);
        result = Permutations.permutationFunction(result);
        return result;
    }

    public static String xor(String  b1, String b2) {
        StringBuilder result = new StringBuilder(b1.length());

        for (int i = 0; i < b1.length(); i++) {
            char bit1 = b1.charAt(i);
            char bit2 = b2.charAt(i);

            result.append(bit1 == bit2 ? '0' : '1');
        }

        return result.toString();
    }

    public static String[] getBlocks(String binaryText) {
        if (binaryText.length() % 64 != 0) {
            binaryText += "0".repeat(64 - binaryText.length() % 64);
        }

        String[] blocks = new String[binaryText.length() / 64];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = binaryText.substring(i * 64, (i + 1) * 64);
        }
        return blocks;
    }

    public static String getValidKey(String keyBinary) {
        if (keyBinary.length() > 64) {
            return keyBinary.substring(0, 64);
        } else if (keyBinary.length() < 64) {
            return  "0".repeat(64 - keyBinary.length()) + keyBinary;
        } else {
            return keyBinary;
        }
    }

    public static String[] generateSubKeys(String keyBinary, boolean writeToFile) {
        String[] subKeys = new String[16];
        keyBinary = Permutations.permutationChoice1(keyBinary);
        String C = keyBinary.substring(0, 28);
        String D = keyBinary.substring(28);

        for (int i = 0; i < 16; i++) {
            C = shiftLeft(C, i);
            D = shiftLeft(D, i);
            subKeys[i] = Permutations.permutationChoice2(C + D);
        }

        if (writeToFile) {
            writeKeysToFile(subKeys);
        }
        return subKeys;
    }

    public static void writeKeysToFile(String[] subKeys) {
        File file = new File("keys.txt");
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i = 0; i < subKeys.length; i++) {
                pw.write("Round " + (i + 1) + ( i >= 9 ? ": " : " : ") + Convertor.binaryToHexadecimal(subKeys[i]) + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}