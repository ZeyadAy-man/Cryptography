/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rsa;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
/**
 *
 * @author zizoa
 */
public class RSA_Logic {
    private static final SecureRandom random = new SecureRandom();
    private static final BigInteger [] solution = keyGeneration();
    private static final BigInteger e = new BigInteger("65573");
    private static final BigInteger n = solution[2];
    private static final BigInteger d = solution[1];

    public static boolean isProbablePrime(BigInteger n, int k) {
        // Handle cases such as even numbers, 2, or less than 2
        if (n.compareTo(BigInteger.TWO) < 0) return false;
        if (n.equals(BigInteger.TWO)) return true;
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return false;

        // Write n-1 in the formula 2^r * d
        BigInteger d = n.subtract(BigInteger.ONE);
        int r = 0;
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            r++;
        }

        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < k; i++) {
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rand);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.TWO)) > 0);

            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE)))
                continue;

            boolean passed = false;
            for (int j = 0; j < r - 1; j++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    passed = true;
                    break;
                }
            }

            if (!passed) return false;
        }

        return true;
    }
    
    public static BigInteger[] keyGeneration(){
        
        int bitLength = 1024;
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger p, q, phi, n;
        do{    
            do{
                p = BigInteger.probablePrime(bitLength, random);
                q = BigInteger.probablePrime(bitLength, random);
            }while(p.equals(q) || !isProbablePrime(p, 50) || !isProbablePrime(q, 50));

            n = q.multiply(p);
            phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
//            System     .    out      .     println("abcd");
        
        }while(!e.gcd(phi).equals(BigInteger.ONE));
        
        BigInteger d = e.modInverse(phi);

        BigInteger[] solution = {e, d, n};
        
        return solution;
    }
    public static String encryption(String text){
        
        byte[] messageBytes = text.getBytes(StandardCharsets.UTF_8);
                
        BigInteger m = new BigInteger(1, messageBytes);
        BigInteger c = m.modPow(solution[0], solution[2]);
        
        return Base64.getEncoder().encodeToString(c.toByteArray());
    }
    public static String decryption(String base64Ciphertext){

        byte[] cipherBytes = Base64.getDecoder().decode(base64Ciphertext);

        BigInteger c = new BigInteger(1, cipherBytes);
        BigInteger m = c.modPow(solution[1], solution[2]);

        byte[] messageBytes = m.toByteArray();

        return new String(messageBytes, StandardCharsets.UTF_8);
    }
}
