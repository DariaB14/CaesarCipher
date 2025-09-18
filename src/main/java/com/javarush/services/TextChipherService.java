package com.javarush.services;

public class TextChipherService {
    private final CipherService cipherService;

    public TextChipherService(CipherService cipherService){
        this.cipherService=cipherService;
    }

    public String encryptText(String text, int key){
        StringBuilder encryptedText=new StringBuilder(text.length());

        for(char symbol : text.toCharArray()) {
            char encryptedChar = cipherService.encrypt(symbol, key);
            encryptedText.append(encryptedChar);
        }
        return encryptedText.toString();
    }

    public String decryptText(String text, int key){
        StringBuilder decryptedText=new StringBuilder(text.length());

        for(char symbol : text.toCharArray()) {
            char decryptedChar = cipherService.decrypt(symbol, key);
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }
}
