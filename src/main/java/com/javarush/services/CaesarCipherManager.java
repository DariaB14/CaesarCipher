package com.javarush.services;

import com.javarush.exceptions.CipherException;
import com.javarush.exceptions.KeyValidationException;
import com.javarush.services.decrypt.BruteForce;
import com.javarush.services.decrypt.StatisticalAnalyzer;
import com.javarush.io.FileManager;
import com.javarush.validation.Validator;

import java.io.IOException;

public class CaesarCipherManager {
    private final CipherService cipherService;
    private final TextChipherService textChipherService;
    private final FileManager fileManager;
    private final Validator validator;
    private final BruteForce bruteForce;
    private final StatisticalAnalyzer statisticalAnalazer;

    public CaesarCipherManager(){
        this(new CipherService(),
                new TextChipherService(new CipherService()),
                new FileManager(),
                new Validator(),
                new BruteForce(new TextChipherService(new CipherService()), new FileManager()),
                new StatisticalAnalyzer(new TextChipherService(new CipherService()), new FileManager()));
    }

    public CaesarCipherManager(CipherService cipherService, TextChipherService textChipherService, FileManager fileManager, Validator validator, BruteForce bruteForce, StatisticalAnalyzer statisticalAnalazer) {
        this.cipherService = cipherService;
        this.textChipherService = textChipherService;
        this.fileManager = fileManager;
        this.validator = validator;
        this.bruteForce=bruteForce;
        this.statisticalAnalazer=statisticalAnalazer;
    }

    public void encryptFile(String inputFile, String outputFile, int key) throws IOException {
        try{
            Validator.isInputFileExistsAndReadable(inputFile);
            Validator.isOutputFileExistsAndWriteable(outputFile);
            Validator.isValidKey(key);

            String originalText=fileManager.readFile(inputFile);
            String encryptedText=textChipherService.encryptText(originalText, key);

            fileManager.writeFile(encryptedText, outputFile);
        } catch(IllegalArgumentException e){
            throw new KeyValidationException("Ошибка валидации ключа");
        }
    }

    public void decryptFile(String inputFile, String outputFile, int key) throws IOException {
        try {
            Validator.isInputFileExistsAndReadable(inputFile);
            Validator.isOutputFileExistsAndWriteable(outputFile);
            Validator.isValidKey(key);

            String encryptedText = fileManager.readFile(inputFile);
            String decryptedText = textChipherService.decryptText(encryptedText, key);

            fileManager.writeFile(decryptedText, outputFile);
        } catch (IllegalArgumentException e) {
            throw new KeyValidationException("Ошибка валидации ключа");
        }
    }

    public int bruteForce(String encryptedFile, String outputFile) throws IOException{
       try{
           Validator.isInputFileExistsAndReadable(encryptedFile);
           Validator.isOutputFileExistsAndWriteable(outputFile);

           return bruteForce.decryptByBruteForce(encryptedFile, outputFile);
       } catch (IllegalArgumentException e){
           throw new CipherException("Невозможно расшифровать");
       }
    }

    public int statisticalDecrypt(String encryptedFile, String sampleFile, String outputFile) throws IOException{
        try {
            Validator.isInputFileExistsAndReadable(encryptedFile);
            Validator.isInputFileExistsAndReadable(sampleFile);
            Validator.isOutputFileExistsAndWriteable(outputFile);

            return statisticalAnalazer.decrypt(encryptedFile, sampleFile, outputFile);
        } catch (IllegalArgumentException e){
            throw new CipherException("Невозможно расшифровать");
        }

    }
}
