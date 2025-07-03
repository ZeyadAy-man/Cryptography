package com.mycompany.aes;

import com.mycompany.aes.Convertor;

import com.mycompany.aes.Convertor;

/**
 *
 * @author zizoa
 */
public class AESLogic {
    public static final String[][] AES_S_BOX = {
        {"0x63", "0x7C", "0x77", "0x7B", "0xF2", "0x6B", "0x6F", "0xC5", "0x30", "0x01", "0x67", "0x2B", "0xFE", "0xD7", "0xAB", "0x76"},
        {"0xCA", "0x82", "0xC9", "0x7D", "0xFA", "0x59", "0x47", "0xF0", "0xAD", "0xD4", "0xA2", "0xAF", "0x9C", "0xA4", "0x72", "0xC0"},
        {"0xB7", "0xFD", "0x93", "0x26", "0x36", "0x3F", "0xF7", "0xCC", "0x34", "0xA5", "0xE5", "0xF1", "0x71", "0xD8", "0x31", "0x15"},
        {"0x04", "0xC7", "0x23", "0xC3", "0x18", "0x96", "0x05", "0x9A", "0x07", "0x12", "0x80", "0xE2", "0xEB", "0x27", "0xB2", "0x75"},
        {"0x09", "0x83", "0x2C", "0x1A", "0x1B", "0x6E", "0x5A", "0xA0", "0x52", "0x3B", "0xD6", "0xB3", "0x29", "0xE3", "0x2F", "0x84"},
        {"0x53", "0xD1", "0x00", "0xED", "0x20", "0xFC", "0xB1", "0x5B", "0x6A", "0xCB", "0xBE", "0x39", "0x4A", "0x4C", "0x58", "0xCF"},
        {"0xD0", "0xEF", "0xAA", "0xFB", "0x43", "0x4D", "0x33", "0x85", "0x45", "0xF9", "0x02", "0x7F", "0x50", "0x3C", "0x9F", "0xA8"},
        {"0x51", "0xA3", "0x40", "0x8F", "0x92", "0x9D", "0x38", "0xF5", "0xBC", "0xB6", "0xDA", "0x21", "0x10", "0xFF", "0xF3", "0xD2"},
        {"0xCD", "0x0C", "0x13", "0xEC", "0x5F", "0x97", "0x44", "0x17", "0xC4", "0xA7", "0x7E", "0x3D", "0x64", "0x5D", "0x19", "0x73"},
        {"0x60", "0x81", "0x4F", "0xDC", "0x22", "0x2A", "0x90", "0x88", "0x46", "0xEE", "0xB8", "0x14", "0xDE", "0x5E", "0x0B", "0xDB"},
        {"0xE0", "0x32", "0x3A", "0x0A", "0x49", "0x06", "0x24", "0x5C", "0xC2", "0xD3", "0xAC", "0x62", "0x91", "0x95", "0xE4", "0x79"},
        {"0xE7", "0xC8", "0x37", "0x6D", "0x8D", "0xD5", "0x4E", "0xA9", "0x6C", "0x56", "0xF4", "0xEA", "0x65", "0x7A", "0xAE", "0x08"},
        {"0xBA", "0x78", "0x25", "0x2E", "0x1C", "0xA6", "0xB4", "0xC6", "0xE8", "0xDD", "0x74", "0x1F", "0x4B", "0xBD", "0x8B", "0x8A"},
        {"0x70", "0x3E", "0xB5", "0x66", "0x48", "0x03", "0xF6", "0x0E", "0x61", "0x35", "0x57", "0xB9", "0x86", "0xC1", "0x1D", "0x9E"},
        {"0xE1", "0xF8", "0x98", "0x11", "0x69", "0xD9", "0x8E", "0x94", "0x9B", "0x1E", "0x87", "0xE9", "0xCE", "0x55", "0x28", "0xDF"},
        {"0x8C", "0xA1", "0x89", "0x0D", "0xBF", "0xE6", "0x42", "0x68", "0x41", "0x99", "0x2D", "0x0F", "0xB0", "0x54", "0xBB", "0x16"}
    };
    
    public static final String[][] INV_S_BOX = {
        {"0x52", "0x09", "0x6A", "0xD5", "0x30", "0x36", "0xA5", "0x38", "0xBF", "0x40", "0xA3", "0x9E", "0x81", "0xF3", "0xD7", "0xFB"},
        {"0x7C", "0xE3", "0x39", "0x82", "0x9B", "0x2F", "0xFF", "0x87", "0x34", "0x8E", "0x43", "0x44", "0xC4", "0xDE", "0xE9", "0xCB"},
        {"0x54", "0x7B", "0x94", "0x32", "0xA6", "0xC2", "0x23", "0x3D", "0xEE", "0x4C", "0x95", "0x0B", "0x42", "0xFA", "0xC3", "0x4E"},
        {"0x08", "0x2E", "0xA1", "0x66", "0x28", "0xD9", "0x24", "0xB2", "0x76", "0x5B", "0xA2", "0x49", "0x6D", "0x8B", "0xD1", "0x25"},
        {"0x72", "0xF8", "0xF6", "0x64", "0x86", "0x68", "0x98", "0x16", "0xD4", "0xA4", "0x5C", "0xCC", "0x5D", "0x65", "0xB6", "0x92"},
        {"0x6C", "0x70", "0x48", "0x50", "0xFD", "0xED", "0xB9", "0xDA", "0x5E", "0x15", "0x46", "0x57", "0xA7", "0x8D", "0x9D", "0x84"},
        {"0x90", "0xD8", "0xAB", "0x00", "0x8C", "0xBC", "0xD3", "0x0A", "0xF7", "0xE4", "0x58", "0x05", "0xB8", "0xB3", "0x45", "0x06"},
        {"0xD0", "0x2C", "0x1E", "0x8F", "0xCA", "0x3F", "0x0F", "0x02", "0xC1", "0xAF", "0xBD", "0x03", "0x01", "0x13", "0x8A", "0x6B"},
        {"0x3A", "0x91", "0x11", "0x41", "0x4F", "0x67", "0xDC", "0xEA", "0x97", "0xF2", "0xCF", "0xCE", "0xF0", "0xB4", "0xE6", "0x73"},
        {"0x96", "0xAC", "0x74", "0x22", "0xE7", "0xAD", "0x35", "0x85", "0xE2", "0xF9", "0x37", "0xE8", "0x1C", "0x75", "0xDF", "0x6E"},
        {"0x47", "0xF1", "0x1A", "0x71", "0x1D", "0x29", "0xC5", "0x89", "0x6F", "0xB7", "0x62", "0x0E", "0xAA", "0x18", "0xBE", "0x1B"},
        {"0xFC", "0x56", "0x3E", "0x4B", "0xC6", "0xD2", "0x79", "0x20", "0x9A", "0xDB", "0xC0", "0xFE", "0x78", "0xCD", "0x5A", "0xF4"},
        {"0x1F", "0xDD", "0xA8", "0x33", "0x88", "0x07", "0xC7", "0x31", "0xB1", "0x12", "0x10", "0x59", "0x27", "0x80", "0xEC", "0x5F"},
        {"0x60", "0x51", "0x7F", "0xA9", "0x19", "0xB5", "0x4A", "0x0D", "0x2D", "0xE5", "0x7A", "0x9F", "0x93", "0xC9", "0x9C", "0xEF"},
        {"0xA0", "0xE0", "0x3B", "0x4D", "0xAE", "0x2A", "0xF5", "0xB0", "0xC8", "0xEB", "0xBB", "0x3C", "0x83", "0x53", "0x99", "0x61"},
        {"0x17", "0x2B", "0x04", "0x7E", "0xBA", "0x77", "0xD6", "0x26", "0xE1", "0x69", "0x14", "0x63", "0x55", "0x21", "0x0C", "0x7D"}
    };

    public static final String[] RCON = {"01", "02", "04", "08", "10", "20", "40", "80", "1B", "36"};
    
    
    private static final String[][] MIX_COLUMNS_MATRIX_HEX = {
        {"0x02", "0x03", "0x01", "0x01"},
        {"0x01", "0x02", "0x03", "0x01"},
        {"0x01", "0x01", "0x02", "0x03"},
        {"0x03", "0x01", "0x01", "0x02"}
    };

    private static final String[][] INVERSE_MIX_COLUMNS_MATRIX_HEX = {
            {"0x0E", "0x0B", "0x0D", "0x09"},
            {"0x09", "0x0E", "0x0B", "0x0D"},
            {"0x0D", "0x09", "0x0E", "0x0B"},
            {"0x0B", "0x0D", "0x09", "0x0E"}
    };
    
    public static void addRoundKey(String[][] roundKeyMatrix, String[][] stateMatrix){
        //done
        for(int i = 0; i < stateMatrix.length; i++){
            for(int j = 0; j < stateMatrix[i].length; j++){
                stateMatrix[i][j] = XOR(roundKeyMatrix[i][j], stateMatrix[i][j]);
            }
        }
    }
    public static String XOR(String keyByte, String stateByte){
        //done
        String XORedByte = ""; 
        for(int i = 0; i < keyByte.length(); i++){
            XORedByte += (Convertor.stringBitsToIntegerBits(keyByte.charAt(i) + "") ^ Convertor.stringBitsToIntegerBits(stateByte.charAt(i) + ""));
        }
        return XORedByte;
    }
    public static void substituteBytes(String[][] matrix){
        //done
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                int rowPosition = Convertor.bitsToDecimal(matrix[i][j].substring(0, 4));
                int columnPosition = Convertor.bitsToDecimal(matrix[i][j].substring(4, 8));
                matrix[i][j] = Convertor.hexadecimalToBinaryFormat(AES_S_BOX[rowPosition][columnPosition]);
            }
        }
    }
    public static void inverseSubstituteBytes(String[][] matrix){
        //done
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                int rowPosition = Convertor.bitsToDecimal(matrix[i][j].substring(0, 4));
                int columnPosition = Convertor.bitsToDecimal(matrix[i][j].substring(4, 8));
                matrix[i][j] = (INV_S_BOX[rowPosition][columnPosition]);
            }
        }
    }
    public static void shiftRows(String[][] matrix){
        //done
        String [][] result = new String[4][4];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                result[i][(j - i) >= 0 ? (j - i) : (j - i + 4)] = matrix[i][j];                    
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = result[i][j];
            }
        }
    }
    public static void inverseShiftRows(String[][] matrix){
        //done
        String [][] result = new String[4][4];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                result[i][(j + i) <= 3 ? (j + i) : (j + i - 4)] = matrix[i][j];                    
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = result[i][j];
            }
        }
    }
    public static void keyExpansion(String[][]key, String[]words){
        //Everything here is hexadecimal. :)
        //done
        String temp = "";
        for(int i = 0; i < 4; i++){
            words[i] = Convertor.binaryToHexadecimalFormat(key[0][i] + key[1][i] + key[2][i] + key[3][i]);
        }
        for(int i = 4; i < 44; i++){
            temp = words[i - 1];
            if(i % 4 == 0){
                temp = Convertor.binaryToHexadecimalFormat(XOR(Convertor.hexadecimalToBinaryFormat(substituteWord(rotateWord(temp))), Convertor.hexadecimalToBinaryFormat("0x" + RCON[i / 4 - 1] + "000000")));
            }
            temp = Convertor.hexadecimalToBinaryFormat(temp);
            words[i] = Convertor.binaryToHexadecimalFormat(XOR(temp, Convertor.hexadecimalToBinaryFormat(words[i - 4])));
        }
    }
    public static String rotateWord(String s){
        //done
        s = s.replace("0x", "");
        return "0x" + s.substring(2, 8) + s.charAt(0) + s.charAt(1);
    }
    public static String substituteWord(String s){
        //done
        String wordAfterSubWord = "";
        for(int i = 2; i < s.length(); i += 2){
            int rowPosition = Convertor.hexadecimalToDecimal("0x" + s.charAt(i));
            int columnPosition = Convertor.hexadecimalToDecimal("0x" + s.charAt(i + 1));
            wordAfterSubWord += AES_S_BOX[rowPosition][columnPosition];
        }   
        return ("0x" + wordAfterSubWord.substring(2).replace("0x", ""));
    }    
    public static void mixColumns(String[][] stateHex) {
        //done
        byte[][] temp = new byte[4][4];

        byte[][] state = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = (byte) Integer.parseInt(stateHex[i][j].substring(2), 16);
            }
        }
        byte[][] mixColumnsMatrix = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mixColumnsMatrix[i][j] = (byte) Integer.parseInt(MIX_COLUMNS_MATRIX_HEX[i][j].substring(2), 16);
            }
        }
        
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                temp[r][c] = (byte) (gfMul(mixColumnsMatrix[r][0], state[0][c]) ^
                                     gfMul(mixColumnsMatrix[r][1], state[1][c]) ^
                                     gfMul(mixColumnsMatrix[r][2], state[2][c]) ^
                                     gfMul(mixColumnsMatrix[r][3], state[3][c]));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                stateHex[i][j] = Convertor.hexadecimalToBinaryFormat(String.format("0x%02X", temp[i][j]));
            }
        }
    }
    
    public static void inverseMixColumns(String[][] stateHex) {
        //done
        byte[][] temp = new byte[4][4];

        byte[][] state = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = (byte) Integer.parseInt(stateHex[i][j].substring(2), 16);
            }
        }
        
        byte[][] inverseMixColumnsMatrix = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                inverseMixColumnsMatrix[i][j] = (byte) Integer.parseInt(INVERSE_MIX_COLUMNS_MATRIX_HEX[i][j].substring(2), 16);
            }
        }
        
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                temp[r][c] = (byte) (gfMul(inverseMixColumnsMatrix[r][0], state[0][c]) ^
                                     gfMul(inverseMixColumnsMatrix[r][1], state[1][c]) ^
                                     gfMul(inverseMixColumnsMatrix[r][2], state[2][c]) ^
                                     gfMul(inverseMixColumnsMatrix[r][3], state[3][c]));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                stateHex[i][j] = String.format("0x%02X", temp[i][j]);
            }
        }
    }
    
        public static byte gfMul(int a, byte b) {
        byte result = 0;
        byte temp = b;

        for (int i = 0; i < 8; i++) {
            if ((a & 1) != 0) {
                result ^= temp;
            }
            boolean highBitSet = (temp & 0x80) != 0;
            temp <<= 1;
            if (highBitSet) {
                temp ^= 0x1B;
            }
            a >>= 1;
        }

        return result;
    }
    public static String encrypt(String plainTextInBinary, String key){
        String[] words = new String[44];
        String keyInBinary =  AESLogic.fillerText(Convertor.textToBits(key));
        System.out.println("keyInBinary: " + keyInBinary);
        System.out.println(keyInBinary.length());
        String[][] keyInMatrix = new String[4][4];
        String[][] state = new String[4][4];
        String cipherTextInsideFile = "";
//        String plainTextInBinary = Convertor.textToBits(plainText);
        
        
        Convertor.bitsToMatrix(keyInBinary, keyInMatrix);
        System.out.println("KeyInMatrix");
        Convertor.displayMatrix(keyInMatrix);
        keyExpansion(keyInMatrix, words);
        
        //end of key expansion
        
        if(plainTextInBinary.length() != 128){
            int length = plainTextInBinary.length();
            for(int i = 0; i < Math.abs(128 - length); i++){
                plainTextInBinary = "0" + plainTextInBinary;
            }
        }
        
        //initial add round
        
        System.out.println(words[0] + words[1] + words[2] + words[3]);
        String []arrayOfInitialWords = {words[0], words[1], words[2], words[3]};
        Convertor.wordToMatrix(arrayOfInitialWords, keyInMatrix);
        Convertor.bitsToMatrix(plainTextInBinary, state);
        String [][] result = new String[4][4];

        addRoundKey(state, keyInMatrix);
        

//        Convertor.displayMatrix(state);
//        System.out.println(Convertor.matrixToHexadecimal(state));
//        System.out.println("state1");
       
        result = (Convertor.binaryMatrixToHexadecimalMatrix(state));
//        System.out.println("");
//        Convertor.displayMatrix(result);
//        System.out.println("");
//        Convertor.displayMatrix(state);
        //end of initial add round
        
        for(int i = 1; i < 11; i++){
            String []arrayOfWords = {words[4 * i], words[4 * i + 1], words[4 * i + 2], words[4 * i + 3]};
//            System.out.println(arrayOfWords[0] + " " + arrayOfWords[1] + " " + arrayOfWords[2] + " " + arrayOfWords[3]);
            String [][] resultFromMixColumns = new String[4][4];
            Convertor.wordToMatrix(arrayOfWords, keyInMatrix);
            result = (Convertor.binaryMatrixToHexadecimalMatrix(state));
//            Convertor.displayMatrix(result);
//            System.out.println("");
            
            substituteBytes(state);
            result = (Convertor.binaryMatrixToHexadecimalMatrix(state));
//            Convertor.displayMatrix(result);
//            System.out.println("");
            
            shiftRows(state);
            result = (Convertor.binaryMatrixToHexadecimalMatrix(state));
//            Convertor.displayMatrix(result);
//            System.out.println("");
            
            mixColumns(state);
            result = (Convertor.binaryMatrixToHexadecimalMatrix(state));
//            Convertor.displayMatrix(result);
//            System.out.println("");
            
            addRoundKey(keyInMatrix, state);
            result = (Convertor.binaryMatrixToHexadecimalMatrix(state));
//            Convertor.displayMatrix(result);
//            System.out.println("");
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    cipherTextInsideFile += Convertor.binaryToText(state[k][j]);
                }
            }
            cipherTextInsideFile += (" state " + i + "\n");
//            System.out.println("//////////////////////////////");
        }
        String[] arr = {words[40], words[41], words[42], words[43]};
        Convertor.wordToMatrix(arr, keyInMatrix);
        substituteBytes(state);
        shiftRows(state);
        addRoundKey(keyInMatrix, state);
        
        String cipherText = "";
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                cipherText += Convertor.binaryToText(state[j][i]);
            }
        }
        cipherTextInsideFile += cipherText;
        DB_AES.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\CryptoTest\\Encryption.txt", cipherTextInsideFile);
        return cipherText;
    }
    public static String fillerText(String s){
        int missedLength = (128 - s.length() % 129);
        for(int i = 0; i < missedLength; i++){
            s = '0' + s;
        }
        return s;
    }
    public static String decryption(String cipherTextInBinary, String key){
        String[] words = new String[44];
        String keyInBinary =  AESLogic.fillerText(Convertor.textToBits(key));
        System.out.println("keyInBinary: " + keyInBinary);
        System.out.println(keyInBinary.length());
        String[][] keyInMatrix = new String[4][4];
        String[][] state = new String[4][4];
        String plainTextInsideFile = "";
//        String plainTextInBinary = Convertor.textToBits(plainText);
        
        
        Convertor.bitsToMatrix(keyInBinary, keyInMatrix);
        System.out.println("KeyInMatrix");
        Convertor.displayMatrix(keyInMatrix);
        keyExpansion(keyInMatrix, words);
        
        
        System.out.println(words[40] + words[41] + words[42] + words[43]);
        String []arrayOfInitialWords = {words[40], words[41], words[42], words[43]};
        Convertor.wordToMatrix(arrayOfInitialWords, keyInMatrix);
        Convertor.bitsToMatrix(cipherTextInBinary, state);
        String [][] result = new String[4][4];

        addRoundKey(state, keyInMatrix);
        
        for(int i = 9; i >= 0; i++){
            String []arrayOfWords = {words[4 * i], words[4 * i + 1], words[4 * i + 2], words[4 * i + 3]};
            String [][] resultFromMixColumns = new String[4][4];
            Convertor.wordToMatrix(arrayOfWords, keyInMatrix);
            
            inverseShiftRows(state);
            inverseSubstituteBytes(state);
            addRoundKey(keyInMatrix, state);
            inverseMixColumns(state);
        }
        
        String[] arr = {words[0], words[1], words[2], words[3]};
        Convertor.wordToMatrix(arr, keyInMatrix);
        substituteBytes(state);
        shiftRows(state);
        addRoundKey(keyInMatrix, state);
        
        String plainText = "";
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                plainText += Convertor.binaryToText(state[j][i]);
            }
        }
        plainTextInsideFile += plainText;
        DB_AES.writeIntoAFile("C:\\Users\\zizoa\\Desktop\\CryptoTest\\Decryption.txt", plainTextInsideFile);
        return plainText;
        
    }
}