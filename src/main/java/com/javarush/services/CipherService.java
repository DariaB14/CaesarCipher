package com.javarush.services;

import com.javarush.model.Alphabet;

public class CipherService {

    public char encrypt(char symbol, int key) {
        Integer curIndex= Alphabet.ALPHABET_MAP.get(symbol);
        if (curIndex==null){
            return symbol;
        }
        int newIndex=(curIndex+key)%Alphabet.LENGTH;
        return Alphabet.ALPHABET[newIndex];
    }
    public char decrypt(char symbol, int key) {
        Integer curIndex=Alphabet.ALPHABET_MAP.get(symbol);
        if (curIndex==null){
            return symbol;
        }
        int newIndex=(curIndex-key+Alphabet.LENGTH)%Alphabet.LENGTH;
        return Alphabet.ALPHABET[newIndex];
    }
}
